use travelagencydb;


-- ==============================
-- COUNTRY TABLE
-- ==============================
CREATE TABLE country (
    country_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- RELIGION TABLE
-- ==============================
CREATE TABLE religion (
    religion_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- VERIFIED STATUS TABLE
-- ==============================
CREATE TABLE verified_status (
    verified_status_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- GENDER TABLE
-- ==============================
CREATE TABLE gender (
    gender_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- USER TYPE TABLE
-- ==============================
CREATE TABLE user_type (
    user_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    projects_access VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- USER STATUS TABLE
-- ==============================
CREATE TABLE user_status (
    user_status_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- WALLET STATUS TABLE
-- ==============================
CREATE TABLE wallet_status (
    wallet_status_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- WALLET TABLE
-- ==============================
CREATE TABLE wallet (
    wallet_id INT PRIMARY KEY AUTO_INCREMENT,
    wallet_number VARCHAR(100) UNIQUE NOT NULL,
    amount DECIMAL(15,2) DEFAULT 0.00,
    wallet_status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (wallet_status_id) REFERENCES wallet_status(wallet_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- ADDRESS TABLE
-- ==============================
CREATE TABLE address (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(50),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    district VARCHAR(100),
    province VARCHAR(100),
    country_id INT,
    postal_code VARCHAR(20),
    FOREIGN KEY (country_id) REFERENCES country(country_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- USER TABLE
-- ==============================
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    middle_name VARCHAR(100),
    last_name VARCHAR(100),
    address_id INT,
    nic VARCHAR(20) UNIQUE,
    gender_id INT,
    passport_number VARCHAR(50),
    driving_license_number VARCHAR(50),
    email VARCHAR(150) UNIQUE,
    mobile_number1 VARCHAR(20),
    mobile_number2 VARCHAR(20),
    region_id INT,
    religion_id INT,
    date_of_birth DATE,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_status_id INT,
    benefits_points_count INT DEFAULT 0,
    wallet_id INT,
    user_type_id INT,
    FOREIGN KEY (address_id) REFERENCES address(address_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (gender_id) REFERENCES gender(gender_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (region_id) REFERENCES country(country_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (religion_id) REFERENCES religion(religion_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_status_id) REFERENCES user_status(user_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- EMAIL VERIFIED TABLE
-- ==============================
CREATE TABLE email_verified (
    email_verified_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    send_code VARCHAR(50),
    typed_code VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES verified_status(verified_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==============================
-- MOBILE VERIFIED TABLE
-- ==============================
CREATE TABLE mobile_verified (
    mobile_verified_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    send_code VARCHAR(50),
    typed_code VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES verified_status(verified_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


