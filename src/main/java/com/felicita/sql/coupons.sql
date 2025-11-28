USE travelagencydb;

-- Coupon Status Master Table
CREATE TABLE coupon_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    status_category ENUM('allocated', 'used', 'expired', 'cancelled', 'pending') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT
);

-- Coupon Rule Types Master Table
CREATE TABLE coupon_rule_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    data_type ENUM('number', 'date', 'string', 'boolean', 'list') NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Coupon Failure Reasons Master Table
CREATE TABLE coupon_failure_reason (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    category ENUM('validation', 'eligibility', 'system', 'usage_limit') NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Coupon Types (e.g., Seasonal, Referral, First-time, Loyalty)
CREATE TABLE coupon_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Coupon Applicable For (e.g., Specific Package, All Packages, Specific User Type)
CREATE TABLE coupon_applicable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Main Coupons Table
CREATE TABLE coupon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    coupon_type_id INT NOT NULL,
    status_id INT NOT NULL,
    discount_type ENUM('percentage', 'fixed') NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL,
    minimum_cart_value DECIMAL(10,2) DEFAULT 0,
    maximum_discount DECIMAL(10,2),
    applicable_id INT NOT NULL,
    valid_from DATETIME NOT NULL,
    valid_until DATETIME NOT NULL,
    usage_limit_per_coupon INT DEFAULT NULL,
    usage_limit_per_user INT DEFAULT 1,
    total_usage_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id),
    FOREIGN KEY (applicable_id) REFERENCES coupon_applicable(id),
    FOREIGN KEY (coupon_type_id) REFERENCES coupon_type(id)
);

-- Coupon Applicability Details (Many-to-Many relationships)
CREATE TABLE coupon_applicable_package (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_id INT NOT NULL,
    package_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (coupon_id) REFERENCES coupon(id) ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status_id) REFERENCES common_status(id),
    UNIQUE KEY unique_coupon_package (coupon_id, package_id)
);

CREATE TABLE coupon_applicable_user_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_id INT NOT NULL,
    user_type_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (coupon_id) REFERENCES coupon(id) ON DELETE CASCADE,
    FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id),
    FOREIGN KEY (status_id) REFERENCES common_status(id),
    UNIQUE KEY unique_coupon_user_type (coupon_id, user_type_id)
);

-- Coupon User Allocation (When a coupon is assigned to a user)
CREATE TABLE coupon_user_allocation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    coupon_id INT NOT NULL,	
    coupon_status_id INT NOT NULL,
    allocated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME NOT NULL,
    used_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (coupon_id) REFERENCES coupon(id),
    FOREIGN KEY (coupon_status_id) REFERENCES coupon_status(id),
    UNIQUE KEY unique_coupon_user_allocation (user_id, coupon_id)
);

-- Coupon Usage History
CREATE TABLE coupon_usage_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_user_allocation_id INT NOT NULL,
    order_id INT,
    package_id INT,
    original_amount DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2) NOT NULL,
    final_amount DECIMAL(10,2) NOT NULL,
    used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (coupon_user_allocation_id) REFERENCES coupon_user_allocation(id),
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Coupon Rules Table
CREATE TABLE coupon_rule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_id INT NOT NULL,
    coupon_rule_type_id INT NOT NULL,
    rule_value VARCHAR(255),
    operator ENUM('equals', 'greater_than', 'less_than', 'between', 'in_list'),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (coupon_id) REFERENCES coupon(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id),
    FOREIGN KEY (coupon_rule_type_id) REFERENCES coupon_rule_type(id)
);

-- Coupon Failure Logs Table
CREATE TABLE coupon_failure_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_code VARCHAR(50) NOT NULL,
    user_id INT NULL,
    ip_address VARCHAR(45),
    user_agent TEXT,
    coupon_failure_reason_id INT NOT NULL,
    cart_value DECIMAL(10,2),
    attempted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    package_id INT NULL,
    additional_info TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (coupon_failure_reason_id) REFERENCES coupon_failure_reason(id)
);

-- Coupon User Restrictions Table
CREATE TABLE coupon_user_restriction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coupon_id INT NOT NULL,
    user_id INT NOT NULL,
    restriction_type ENUM('whitelist', 'blacklist'),
    reason TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (coupon_id) REFERENCES coupon(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (status_id) REFERENCES common_status(id),
    UNIQUE KEY unique_coupon_user_restriction (coupon_id, user_id)
);


-- Insert Coupon Statuses
INSERT INTO coupon_status (name, description, status_category, created_by) VALUES
('Allocated', 'Coupon has been allocated to user', 'allocated', 1),
('Active', 'Coupon is active and available for use', 'allocated', 1),
('Used', 'Coupon has been successfully used', 'used', 1),
('Expired', 'Coupon has expired', 'expired', 1),
('Cancelled', 'Coupon has been cancelled', 'cancelled', 1),
('Pending Verification', 'Coupon pending verification', 'pending', 1),
('Redeemed', 'Coupon has been redeemed', 'used', 1),
('Partially Used', 'Coupon partially used (for multi-use coupons)', 'used', 1);

-- Insert Coupon Rule Types
INSERT INTO coupon_rule_type (name, description, data_type, status_id, created_by) VALUES
('min_booking_days', 'Minimum number of booking days required', 'number', 1, 1),
('max_booking_days', 'Maximum number of booking days allowed', 'number', 1, 1),
('specific_departure_date', 'Applicable only for specific departure dates', 'date', 1, 1),
('advance_booking_days', 'Must be booked X days in advance', 'number', 1, 1),
('group_size_min', 'Minimum group size required', 'number', 1, 1),
('group_size_max', 'Maximum group size allowed', 'number', 1, 1),
('specific_destination', 'Applicable only for specific destinations', 'string', 1, 1),
('weekday_only', 'Valid only for weekday bookings', 'boolean', 1, 1),
('weekend_only', 'Valid only for weekend bookings', 'boolean', 1, 1),
('seasonal_period', 'Valid during specific seasonal periods', 'date', 1, 1);

-- Insert Coupon Failure Reasons
INSERT INTO coupon_failure_reason (name, description, category, status_id, created_by) VALUES
('invalid_code', 'Coupon code does not exist', 'validation', 1, 1),
('expired', 'Coupon has expired', 'validation', 1, 1),
('usage_limit_reached', 'Coupon usage limit reached', 'usage_limit', 1, 1),
('minimum_cart_value', 'Cart value below minimum requirement', 'eligibility', 1, 1),
('not_applicable', 'Coupon not applicable for selected items', 'eligibility', 1, 1),
('user_restriction', 'User not eligible for this coupon', 'eligibility', 1, 1),
('package_restriction', 'Package not eligible for this coupon', 'eligibility', 1, 1),
('date_restriction', 'Booking date not within valid range', 'eligibility', 1, 1),
('already_used', 'Coupon already used by this user', 'usage_limit', 1, 1),
('inactive_coupon', 'Coupon is not active', 'validation', 1, 1),
('user_type_restriction', 'User type not eligible', 'eligibility', 1, 1),
('system_error', 'Technical error during validation', 'system', 1, 1);

-- Insert Coupon Types
INSERT INTO coupon_type (name, description, status_id, created_by) VALUES
('Seasonal', 'Seasonal offers and holiday discounts', 1, 1),
('Referral', 'Referral program coupons for inviting friends', 1, 1),
('First-time', 'Special discounts for first-time users', 1, 1),
('Loyalty', 'Reward coupons for loyal customers', 1, 1),
('Promotional', 'General promotional coupons', 1, 1),
('Abandoned Cart', 'Coupons to recover abandoned bookings', 1, 1),
('Birthday', 'Special birthday discounts for users', 1, 1);

-- Insert Coupon Applicable Types
INSERT INTO coupon_applicable (name, description, status_id, created_by) VALUES
('All Packages', 'Applicable to all travel packages', 1, 1),
('Specific Packages', 'Applicable only to selected packages', 1, 1),
('User Type Specific', 'Applicable to specific user types', 1, 1),
('First-time Users', 'Applicable only for first booking', 1, 1),
('Destination Specific', 'Applicable only for specific destinations', 1, 1),
('Seasonal Packages', 'Applicable only for seasonal packages', 1, 1);

-- Insert Sample Coupons
INSERT INTO coupon (coupon_code, name, description, coupon_type_id, status_id, discount_type, discount_value, minimum_cart_value, maximum_discount, applicable_id, valid_from, valid_until, usage_limit_per_coupon, usage_limit_per_user, created_by) VALUES
('WELCOME20', 'Welcome Discount', '20% off for new customers', 3, 1, 'percentage', 20.00, 100.00, 50.00, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1000, 1, 1),
('SUMMER25', 'Summer Special', '25% off on summer packages', 1, 1, 'percentage', 25.00, 200.00, 100.00, 2, '2024-06-01 00:00:00', '2024-08-31 23:59:59', 500, 2, 1),
('FIXED50', 'Flat Discount', '$50 off on any booking', 5, 1, 'fixed', 50.00, 300.00, NULL, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59', NULL, 1, 1),
('REFER30', 'Referral Bonus', '30% off for referred friends', 2, 1, 'percentage', 30.00, 150.00, 75.00, 3, '2024-01-01 00:00:00', '2024-12-31 23:59:59', NULL, 1, 1),
('LOYAL15', 'Loyalty Reward', '15% off for loyal customers', 4, 1, 'percentage', 15.00, 100.00, 30.00, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 200, 1, 1),
('BDAY25', 'Birthday Special', '25% off on your birthday month', 7, 1, 'percentage', 25.00, 50.00, 100.00, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59', NULL, 1, 1);

-- Insert Coupon Rules
INSERT INTO coupon_rule (coupon_id, coupon_rule_type_id, rule_value, operator, status_id, created_by) VALUES
(1, 1, '3', 'greater_than', 1, 1), -- WELCOME20: min 3 booking days
(2, 7, 'Bali,Thailand,Malaysia', 'in_list', 1, 1), -- SUMMER25: specific destinations
(2, 9, 'true', 'equals', 1, 1), -- SUMMER25: weekend only
(3, 4, '7', 'greater_than', 1, 1), -- FIXED50: book 7 days in advance
(4, 6, '10', 'less_than', 1, 1), -- REFER30: max group size 10
(5, 5, '2', 'greater_than', 1, 1), -- LOYAL15: min group size 2
(6, 1, '2', 'greater_than', 1, 1); -- BDAY25: min 2 booking days

-- Insert Coupon User Restrictions
INSERT INTO coupon_user_restriction (coupon_id, user_id, restriction_type, reason, status_id, created_by) VALUES
(1, 1, 'whitelist', 'VIP customer special access', 1, 1),
(2, 2, 'blacklist', 'User abused previous coupons', 1, 1),
(3, 3, 'whitelist', 'Employee special discount', 1, 1),
(4, 1, 'whitelist', 'Top referrer bonus', 1, 1);

-- Insert Coupon Package Applicability
INSERT INTO coupon_applicable_package (coupon_id, package_id, status_id, created_by) VALUES
(2, 1, 1, 1), -- SUMMER25 applicable to package 1
(2, 2, 1, 1), -- SUMMER25 applicable to package 2
(2, 3, 1, 1), -- SUMMER25 applicable to package 3
(4, 1, 1, 1); -- REFER30 applicable to package 1

-- Insert Coupon User Type Applicability
INSERT INTO coupon_applicable_user_type (coupon_id, user_type_id, status_id, created_by) VALUES
(3, 1, 1, 1), -- FIXED50 for user type 1
(4, 2, 1, 1), -- REFER30 for user type 2
(5, 1, 1, 1); -- LOYAL15 for user type 1

-- Insert Coupon User Allocations (using new coupon_status_id)
INSERT INTO coupon_user_allocation (user_id, coupon_id, coupon_status_id, expires_at, created_by) VALUES
(1, 1, 1, '2024-12-31 23:59:59', 1), -- Allocated
(1, 2, 2, '2024-08-31 23:59:59', 1), -- Active
(2, 3, 2, '2024-12-31 23:59:59', 1), -- Active
(2, 5, 1, '2024-12-31 23:59:59', 1), -- Allocated
(3, 4, 2, '2024-12-31 23:59:59', 1), -- Active
(3, 6, 2, '2024-12-31 23:59:59', 1); -- Active

-- Insert Coupon Usage History (marking some coupons as used)
INSERT INTO coupon_usage_history (coupon_user_allocation_id, order_id, package_id, original_amount, discount_amount, final_amount, status_id, created_by) VALUES
(1, 1001, 1, 500.00, 100.00, 400.00, 1, 1), -- WELCOME20 used
(3, 1002, 2, 600.00, 50.00, 550.00, 1, 1); -- FIXED50 used

-- Update the used_at timestamp and coupon_status for the used coupons
UPDATE coupon_user_allocation SET used_at = NOW(), coupon_status_id = 3 WHERE id = 1; -- Used
UPDATE coupon_user_allocation SET used_at = NOW(), coupon_status_id = 3 WHERE id = 3; -- Used

-- Update total usage count in coupon table
UPDATE coupon SET total_usage_count = 1 WHERE id = 1;
UPDATE coupon SET total_usage_count = 1 WHERE id = 3;

-- Insert Sample Coupon Failure Logs
INSERT INTO coupon_failure_log (coupon_code, user_id, ip_address, coupon_failure_reason_id, cart_value, package_id, additional_info) VALUES
('WELCOME20', 1, '192.168.1.100', 4, 50.00, 1, '{"required_minimum": 100, "user_cart": 50}'),
('EXPIRED99', NULL, '192.168.1.101', 2, 200.00, 2, '{"coupon_expiry": "2023-12-31"}'),
('SUMMER25', 2, '192.168.1.102', 7, 300.00, 3, '{"allowed_packages": [1,2,4], "selected_package": 3}'),
('WELCOME20', 3, '192.168.1.103', 9, 150.00, 1, '{"user_previous_usage": true}'),
('FIXED50', 1, '192.168.1.104', 8, 400.00, 2, '{"advance_booking_required": 7, "user_booking_days": 3}');