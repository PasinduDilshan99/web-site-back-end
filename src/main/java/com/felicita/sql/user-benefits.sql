use travelagencydb;
CREATE TABLE benefit_type (
    benefit_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_benefit_type_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE user_level_benefit (
    benefit_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    benefit_value DECIMAL(10,2),       -- e.g., 10% discount or 100 currency cashback
    benefit_type_id INT NOT NULL,      -- FK -> benefit_type
    valid_from DATE,
    valid_to DATE,
    status_id INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_user_level_benefit_type FOREIGN KEY (benefit_type_id) REFERENCES benefit_type(benefit_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_user_level_benefit_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE user_level_benefit_mapping (
    user_level_id INT NOT NULL,        -- FK -> user_level
    benefit_id INT NOT NULL,           -- FK -> user_level_benefit
    status_id INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    PRIMARY KEY (user_level_id, benefit_id),
    CONSTRAINT fk_mapping_user_level FOREIGN KEY (user_level_id) REFERENCES user_level(user_level_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_mapping_benefit FOREIGN KEY (benefit_id) REFERENCES user_level_benefit(benefit_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_mapping_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE user_level (
    user_level_id INT PRIMARY KEY AUTO_INCREMENT,
    level VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_user_level_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


INSERT INTO benefit_type (name, description, status_id, created_by)
VALUES
('DISCOUNT', 'Percentage discount on bookings', 1, 1),
('CASHBACK', 'Cashback on payments', 1, 1),
('FREE_SERVICE', 'Free service or add-on', 1, 1);


INSERT INTO user_level (level, description, status_id, created_by)
VALUES
('Silver', 'Entry-level user with basic benefits', 1, 1),
('Gold', 'Premium user with better benefits', 1, 1),
('Platinum', 'Top-tier user with maximum benefits', 1, 1);


INSERT INTO user_level_benefit (name, description, benefit_value, benefit_type_id, valid_from, valid_to, status_id, created_by)
VALUES
('10% Discount on Tours', '10% off on any tour booking', 10, 1, '2025-01-01', '2025-12-31', 1, 1),
('5% Cashback', '5% cashback on payments above $100', 5, 2, '2025-01-01', '2025-12-31', 1, 1),
('Free Airport Pickup', 'Free airport pickup service', 0, 3, '2025-01-01', '2025-12-31', 1, 1),
('15% Discount on Packages', '15% discount for Gold users', 15, 1, '2025-01-01', '2025-12-31', 1, 1),
('10% Cashback', '10% cashback for Platinum users', 10, 2, '2025-01-01', '2025-12-31', 1, 1),
('Free Hotel Upgrade', 'Complimentary room upgrade', 0, 3, '2025-01-01', '2025-12-31', 1, 1);


-- Silver level benefits
INSERT INTO user_level_benefit_mapping (user_level_id, benefit_id, status_id, created_by)
VALUES
(1, 1, 1, 1),  -- 10% Discount on Tours
(1, 2, 1, 1),  -- 5% Cashback
(1, 3, 1, 1);  -- Free Airport Pickup

-- Gold level benefits
INSERT INTO user_level_benefit_mapping (user_level_id, benefit_id, status_id, created_by)
VALUES
(2, 4, 1, 1),  -- 15% Discount on Packages
(2, 2, 1, 1),  -- 5% Cashback
(2, 3, 1, 1);  -- Free Airport Pickup

-- Platinum level benefits
INSERT INTO user_level_benefit_mapping (user_level_id, benefit_id, status_id, created_by)
VALUES
(3, 5, 1, 1),  -- 10% Cashback
(3, 6, 1, 1),  -- Free Hotel Upgrade
(3, 4, 1, 1);  -- 15% Discount on Packages



SELECT
    ul.user_level_id,
    ul.level AS user_level_name,
    ul.description AS user_level_description,
    cs1.name AS user_level_status,
    ulb.benefit_id,
    ulb.name AS benefit_name,
    ulb.description AS benefit_description,
    ulb.benefit_value,
    bt.benefit_type_id AS benefit_type_id,
    bt.name AS benefit_type,
    bt.description AS benefit_type_description,
    cs3.name AS benefit_type_status,
    ulb.valid_from,
    ulb.valid_to,
    cs2.name AS benefit_status
FROM user_level ul
LEFT JOIN common_status cs1
    ON ul.status_id = cs1.id
INNER JOIN user_level_benefit_mapping ulbm 
    ON ul.user_level_id = ulbm.user_level_id
    -- AND ulbm.status_id = 1
INNER JOIN user_level_benefit ulb 
    ON ulbm.benefit_id = ulb.benefit_id
    -- AND ulb.status_id = 1
    -- AND CURDATE() BETWEEN ulb.valid_from AND ulb.valid_to
INNER JOIN benefit_type bt 
    ON ulb.benefit_type_id = bt.benefit_type_id
    AND bt.status_id = 1
LEFT JOIN common_status cs2
    ON ulb.status_id = cs2.id
LEFT JOIN common_status cs3
    ON bt.status_id = cs3.id
ORDER BY ul.user_level_id, ulb.benefit_id;





SELECT
    ul.user_level_id,
    ul.level AS user_level_name,
    ul.description AS user_level_description,
    cs1.name AS user_level_status,
    ulb.benefit_id,
    ulb.name AS benefit_name,
    ulb.description AS benefit_description,
    ulb.benefit_value,
    ulb.valid_from,
    ulb.valid_to,
    cs2.name AS benefit_status,
    bt.benefit_type_id,
    bt.name AS benefit_type_name,
    bt.description AS benefit_type_description,
    cs3.name AS benefit_type_status
FROM user_level ul
LEFT JOIN common_status cs1
    ON ul.status_id = cs1.id
INNER JOIN user_level_benefit_mapping ulbm 
    ON ul.user_level_id = ulbm.user_level_id
INNER JOIN user_level_benefit ulb 
    ON ulbm.benefit_id = ulb.benefit_id
INNER JOIN benefit_type bt 
    ON ulb.benefit_type_id = bt.benefit_type_id
    -- AND bt.status_id = 1
LEFT JOIN common_status cs2
    ON ulb.status_id = cs2.id
LEFT JOIN common_status cs3
    ON bt.status_id = cs3.id
ORDER BY ul.user_level_id, ulb.benefit_id;









