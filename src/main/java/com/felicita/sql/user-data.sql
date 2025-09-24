USE travelagencydb;

-- 1. COUNTRY
INSERT INTO country (name, description, status_id, created_by)
VALUES
('Sri Lanka', 'Island country in South Asia', 1, 1),
('India', 'Neighboring country', 1, 1);

-- 2. RELIGION
INSERT INTO religion (name, description, status_id, created_by)
VALUES
('Buddhism', 'Buddhist religion', 1, 1),
('Hinduism', 'Hindu religion', 1, 1);

-- 3. VERIFIED STATUS
INSERT INTO verified_status (name, description, status_id, created_by)
VALUES
('Pending', 'Verification pending', 1, 1),
('Verified', 'Successfully verified', 1, 1),
('Rejected', 'Verification failed', 1, 1);

-- 4. GENDER
INSERT INTO gender (name, description, status_id, created_by)
VALUES
('Male', 'Male gender', 1, 1),
('Female', 'Female gender', 1, 1);

-- 5. USER TYPE
INSERT INTO user_type (name, description, projects_access, status_id, created_by)
VALUES
('Admin', 'System administrator', 'ALL', 1, 1),
('Customer', 'Regular system user', 'BOOKING', 1, 1);

-- 6. USER STATUS
INSERT INTO user_status (name, description, status_id, created_by)
VALUES
('Active', 'User account active', 1, 1),
('Suspended', 'User account suspended', 2, 1);

-- 7. WALLET STATUS
INSERT INTO wallet_status (name, description, status_id, created_by)
VALUES
('Open', 'Wallet is active', 1, 1),
('Closed', 'Wallet is closed', 2, 1);

-- 8. WALLET
INSERT INTO wallet (wallet_number, amount, wallet_status_id, created_by)
VALUES
('WALLET1001', 5000.00, 1, 1),
('WALLET1002', 0.00, 1, 1);

-- 9. ADDRESS
INSERT INTO address (number, address_line1, address_line2, city, district, province, country_id, postal_code)
VALUES
('221B', 'Baker Street', 'Colombo 07', 'Colombo', 'Colombo', 'Western', 1, '00700'),
('10', 'MG Road', 'Koramangala', 'Bangalore', 'Bangalore', 'Karnataka', 2, '560095');

-- 10. USER
INSERT INTO user (
    username, password, first_name, last_name, address_id, nic, gender_id,
    passport_number, driving_license_number, email, mobile_number1, mobile_number2,
    region_id, religion_id, date_of_birth, image_url, user_status_id,
    benefits_points_count, wallet_id, user_type_id
)
VALUES
('pasindu', 'hashedpassword123', 'Pasindu', 'Dilshan', 1, '199912345V', 1,
 'N1234567', 'B9876543', 'pasindu@example.com', '0712345678', '0776543210',
 1, 1, '1999-12-15', 'http://example.com/image1.jpg', 1,
 100, 1, 2),

('adminuser', 'securehash456', 'Admin', 'User', 2, '200045678V', 2,
 'M7654321', 'D1239876', 'admin@example.com', '0751234567', '0769876543',
 2, 2, '1985-05-20', 'http://example.com/image2.jpg', 1,
 500, 2, 1);

-- 11. EMAIL VERIFIED
INSERT INTO email_verified (user_id, send_code, typed_code, status_id)
VALUES
(1, 'ABC123', 'ABC123', 2),  -- Pasindu, Verified
(2, 'XYZ789', NULL, 1);      -- Admin, Pending

-- 12. MOBILE VERIFIED
INSERT INTO mobile_verified (user_id, send_code, typed_code, status_id)
VALUES
(1, 'MOB111', 'MOB111', 2),  -- Pasindu, Verified
(2, 'MOB222', 'WRONG', 3);   -- Admin, Rejected
