-- =============================
-- Table: promotion_type
-- =============================
CREATE TABLE promotion_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status_id INT NOT NULL DEFAULT 1, -- references common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT,
    terminate_at TIMESTAMP NULL,
    terminate_by BIGINT,
    CONSTRAINT fk_promotion_type_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- =============================
-- Table: promotions_requirements
-- =============================
CREATE TABLE promotions_requirements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    min_person INT DEFAULT 1,
    max_person INT DEFAULT NULL,
    min_price DECIMAL(10,2) DEFAULT 0,
    max_price DECIMAL(10,2) DEFAULT NULL,
    discount_type ENUM('percentage','amount') NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL,
    eligible_customer_group VARCHAR(100) DEFAULT NULL,
    payment_method_restriction VARCHAR(100) DEFAULT NULL,
    booking_period_start DATE DEFAULT NULL,
    booking_period_end DATE DEFAULT NULL,
    travel_period_start DATE DEFAULT NULL,
    travel_period_end DATE DEFAULT NULL,
    status_id INT NOT NULL DEFAULT 1, -- references common_status
    CONSTRAINT fk_req_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- =============================
-- Table: promotions
-- =============================
CREATE TABLE promotions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tour_id INT NULL,  -- nullable if promotion applies to all tours
    description TEXT,
    requirement_id BIGINT NULL,
    status_id INT NOT NULL DEFAULT 1,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    promotion_code VARCHAR(50) UNIQUE,
    promotion_type_id BIGINT NOT NULL,
    apply_to ENUM('tour','category','all') DEFAULT 'tour',
    is_public BOOLEAN DEFAULT TRUE,
    priority INT DEFAULT 0,
    max_usage_limit INT DEFAULT NULL,
    per_user_limit INT DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT,
    terminate_at TIMESTAMP NULL,
    terminate_by BIGINT,
    CONSTRAINT fk_promo_type FOREIGN KEY (promotion_type_id) REFERENCES promotion_type(id),
    CONSTRAINT fk_requirement FOREIGN KEY (requirement_id) REFERENCES promotions_requirements(id),
    CONSTRAINT fk_promo_status FOREIGN KEY (status_id) REFERENCES common_status(id),
    CONSTRAINT fk_promo_tour FOREIGN KEY (tour_id) REFERENCES tour(tour_id)
);

-- =============================
-- Sample Promotion Types
-- =============================
INSERT INTO promotion_type (name, description, status_id, created_by)
VALUES
('Percentage Discount', 'Discount by percentage', 1, 1),
('Fixed Amount Discount', 'Discount by fixed amount', 1, 1),
('Buy One Get One', 'BOGO promotion', 1, 1);

-- =============================
-- Sample Promotions Requirements
-- =============================
INSERT INTO promotions_requirements (description, min_person, max_person, min_price, max_price, discount_type, discount_value, status_id)
VALUES
('Group discount for 2-5 people', 2, 5, 100, 500, 'percentage', 10, 1),
('Flat discount for medium groups', 3, 10, 150, 1000, 'amount', 50, 1),
('Early bird discount', 1, 10, 50, 500, 'percentage', 15, 1),
('Weekend special', 2, 8, 100, 700, 'amount', 25, 1),
('Holiday offer', 1, 6, 120, 600, 'percentage', 20, 1);

-- =============================
-- Sample Promotions
-- =============================

-- For Colombo City Tour (tour_id = 1)
INSERT INTO promotions (tour_id, description, requirement_id, status_id, start_date, end_date, promotion_code, promotion_type_id, apply_to, is_public, priority, max_usage_limit, per_user_limit, created_by)
VALUES
(1, 'Early Bird Colombo Discount', 3, 1, '2025-09-25', '2025-11-30', 'COLOMBOEB10', 1, 'tour', TRUE, 1, 100, 2, 1),
(1, 'Colombo Group Saver', 1, 1, '2025-10-01', '2025-12-01', 'COLOMBOSAVE', 2, 'tour', TRUE, 2, 50, 1, 1),
(1, 'Colombo Weekend Special', 4, 1, '2025-11-01', '2025-12-02', 'COLOMBOWEEK', 2, 'tour', TRUE, 3, 30, 1, 1);

-- For Kandy Cultural Tour (tour_id = 2)
INSERT INTO promotions (tour_id, description, requirement_id, status_id, start_date, end_date, promotion_code, promotion_type_id, apply_to, is_public, priority, max_usage_limit, per_user_limit, created_by)
VALUES
(2, 'Kandy Early Bird Offer', 3, 1, '2025-09-25', '2025-11-30', 'KANDYEB15', 1, 'tour', TRUE, 1, 80, 2, 1),
(2, 'Kandy Holiday Special', 5, 1, '2025-12-01', '2025-12-07', 'KANDYHOL20', 1, 'tour', TRUE, 2, 50, 1, 1);





SELECT 
    p.id AS promotion_id,
    p.promotion_code,
    p.description AS promotion_description,
    p.start_date,
    p.end_date,
    p.apply_to,
    p.is_public,
    p.priority,
    p.max_usage_limit,
    p.per_user_limit,
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    t.start_date AS tour_start_date,
    t.end_date AS tour_end_date,
    t.price_per_person,
    pt.name AS promotion_type,
    pt.description AS promotion_type_description,
    pr.min_person,
    pr.max_person,
    pr.min_price,
    pr.max_price,
    pr.discount_type,
    pr.discount_value,
    pr.eligible_customer_group,
    pr.payment_method_restriction,
    pr.booking_period_start,
    pr.booking_period_end,
    pr.travel_period_start,
    pr.travel_period_end,
    cs.name AS promotion_status
FROM promotions p
LEFT JOIN tour t ON p.tour_id = t.tour_id
LEFT JOIN promotion_type pt ON p.promotion_type_id = pt.id
LEFT JOIN promotions_requirements pr ON p.requirement_id = pr.id
LEFT JOIN common_status cs ON p.status_id = cs.id
ORDER BY t.tour_id, p.priority;

















