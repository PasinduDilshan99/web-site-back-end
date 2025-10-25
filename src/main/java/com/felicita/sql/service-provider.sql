-- ============================================================
-- EXECUTION ORDER: 1 - Reference tables first
-- ============================================================

-- 1. currency
CREATE TABLE currency (
    currency_id INT PRIMARY KEY AUTO_INCREMENT,
    currency_code VARCHAR(3) NOT NULL UNIQUE,
    currency_name VARCHAR(100) NOT NULL,
    symbol VARCHAR(10),
    exchange_rate DECIMAL(10,4) DEFAULT 1.0000,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 2. service_provider_type
CREATE TABLE service_provider_type (
    service_provider_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 3. service_provider_operating_hours_status
CREATE TABLE service_provider_operating_hours_status (
    operating_status_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 4. meal_type
CREATE TABLE meal_type (
    meal_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 5. service_provider_room_type
CREATE TABLE service_provider_room_type (
    room_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 6. amenity_type
CREATE TABLE amenity_type (
    amenity_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    icon_class VARCHAR(100),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 7. service_provider_review_rating_category
CREATE TABLE service_provider_review_rating_category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 8. service_provider_social_verification_status
CREATE TABLE service_provider_social_verification_status (
    verification_status_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ============================================================
-- EXECUTION ORDER: 2 - Main service_provider table
-- ============================================================

-- 9. service_provider
CREATE TABLE service_provider (
    service_provider_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    service_provider_type_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    address VARCHAR(255),
    contact_number VARCHAR(50),
    email VARCHAR(150),
    website_url VARCHAR(255),
    check_in_time TIME,
    check_out_time TIME,
    star_rating TINYINT,
    currency_id INT NOT NULL,
    cancellation_policy TEXT,
    minimum_advance_booking_hours INT DEFAULT 24,
    establishment_year YEAR,
    total_rooms INT DEFAULT 0,
    total_employees INT,
    awards_certifications TEXT,
    languages_spoken JSON,
    parking_facility BOOLEAN DEFAULT FALSE,
    parking_capacity INT,
    wifi_available BOOLEAN DEFAULT FALSE,
    pet_friendly BOOLEAN DEFAULT FALSE,
    check_in_instructions TEXT,
    special_instructions TEXT,
    approval_status_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (service_provider_type_id) REFERENCES service_provider_type(service_provider_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (approval_status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ============================================================
-- EXECUTION ORDER: 3 - Dependent tables (reference service_provider)
-- ============================================================

-- 10. service_provider_approval
CREATE TABLE service_provider_approval (
    approval_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    approved_by INT,
    approval_status_id INT NOT NULL,
    approval_comment TEXT,
    approved_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (approval_status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (approved_by) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 11. service_provider_destination
CREATE TABLE service_provider_destination (
    id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    destination_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    UNIQUE KEY unique_provider_destination (service_provider_id, destination_id)
);

-- 12. service_provider_location
CREATE TABLE service_provider_location (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    google_place_id VARCHAR(255),
    timezone VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 13. service_provider_operating_hours
CREATE TABLE service_provider_operating_hours (
    hours_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    day_of_week TINYINT NOT NULL,
    opens_at TIME,
    closes_at TIME,
    is_24_hours BOOLEAN DEFAULT FALSE,
    operating_status_id INT NOT NULL,
    special_note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (operating_status_id) REFERENCES service_provider_operating_hours_status(operating_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 14. service_provider_meal
CREATE TABLE service_provider_meal (
    meal_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    meal_type_id INT NOT NULL,
    description TEXT,
    local_price DECIMAL(10,2),
    foreign_price DECIMAL(10,2),
    currency_id INT NOT NULL,
    discount_percentage DECIMAL(5,2) DEFAULT 0.00,
    discount_requirements TEXT,
    serves_people INT DEFAULT 1,
    cuisine_type VARCHAR(100),
    dietary_tags JSON,
    preparation_time INT,
    is_chef_special BOOLEAN DEFAULT FALSE,
    is_spicy BOOLEAN DEFAULT FALSE,
    spice_level TINYINT,
    serving_size VARCHAR(100),
    calories INT,
    allergens TEXT,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (meal_type_id) REFERENCES meal_type(meal_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 15. service_provider_room
CREATE TABLE service_provider_room (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    room_type_id INT NOT NULL,
    room_number VARCHAR(50),
    description TEXT,
    capacity INT,
    room_size DECIMAL(6,2),
    bed_type VARCHAR(100),
    number_of_beds INT,
    number_of_bathrooms INT DEFAULT 1,
    max_adults INT,
    max_children INT,
    smoking_allowed BOOLEAN DEFAULT FALSE,
    is_accessible BOOLEAN DEFAULT FALSE,
    local_price_per_night DECIMAL(10,2),
    foreign_price_per_night DECIMAL(10,2),
    currency_id INT NOT NULL,
    discount_percentage DECIMAL(5,2) DEFAULT 0.00,
    discount_requirements TEXT,
    room_floor INT,
    view_type VARCHAR(100),
    has_balcony BOOLEAN DEFAULT FALSE,
    has_air_conditioning BOOLEAN DEFAULT FALSE,
    has_tv BOOLEAN DEFAULT FALSE,
    has_minibar BOOLEAN DEFAULT FALSE,
    has_safe BOOLEAN DEFAULT FALSE,
    has_kitchenette BOOLEAN DEFAULT FALSE,
    internet_access BOOLEAN DEFAULT FALSE,
    room_quality_rating TINYINT,
    extra_bed_available BOOLEAN DEFAULT FALSE,
    extra_bed_charge DECIMAL(10,2) DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (room_type_id) REFERENCES service_provider_room_type(room_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 16. service_provider_package
CREATE TABLE service_provider_package (
    service_provider_package_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    local_price DECIMAL(10,2),
    foreign_price DECIMAL(10,2),
    currency_id INT NOT NULL,
    discount_percentage DECIMAL(5,2) DEFAULT 0.00,
    discount_requirements TEXT,
    duration_days INT,
    start_date DATE,
    end_date DATE,
    min_persons INT DEFAULT 1,
    max_persons INT,
    includes_children BOOLEAN DEFAULT FALSE,
    max_children_included INT DEFAULT 0,
    is_customizable BOOLEAN DEFAULT FALSE,
    booking_deadline_days INT,
    package_code VARCHAR(50) UNIQUE,
    package_category VARCHAR(100),
    season_type VARCHAR(50),
    advance_booking_days INT,
    cancellation_policy TEXT,
    refund_policy TEXT,
    terms_conditions TEXT,
    highlights JSON,
    special_note TEXT,
    requirements TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 17. service_provider_amenity
CREATE TABLE service_provider_amenity (
    provider_amenity_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    amenity_type_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    local_additional_charge DECIMAL(10,2) DEFAULT 0,
    foreign_additional_charge DECIMAL(10,2) DEFAULT 0,
    currency_id INT NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (amenity_type_id) REFERENCES amenity_type(amenity_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 18. service_provider_facility
CREATE TABLE service_provider_facility (
    facility_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    facility_name VARCHAR(255) NOT NULL,
    description TEXT,
    is_available BOOLEAN DEFAULT TRUE,
    special_note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 19. service_provider_seasonal_pricing
CREATE TABLE service_provider_seasonal_pricing (
    seasonal_price_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    local_multiplier DECIMAL(3,2) DEFAULT 1.00,
    foreign_multiplier DECIMAL(3,2) DEFAULT 1.00,
    description TEXT,
    requirements TEXT,
    special_note TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 20. service_provider_contact_person
CREATE TABLE service_provider_contact_person (
    contact_person_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    designation VARCHAR(100),
    email VARCHAR(150),
    phone VARCHAR(50),
    mobile VARCHAR(50),
    is_primary BOOLEAN DEFAULT FALSE,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 21. service_provider_bank
CREATE TABLE service_provider_bank (
    bank_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    account_holder_name VARCHAR(255) NOT NULL,
    account_number VARCHAR(100) NOT NULL,
    branch_name VARCHAR(255),
    branch_code VARCHAR(50),
    swift_code VARCHAR(50),
    iban VARCHAR(100),
    currency_id INT NOT NULL,
    is_primary BOOLEAN DEFAULT FALSE,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (currency_id) REFERENCES currency(currency_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 22. service_provider_tax_configuration
CREATE TABLE service_provider_tax_configuration (
    tax_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    tax_name VARCHAR(100) NOT NULL,
    tax_percentage DECIMAL(5,2) NOT NULL,
    tax_number VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    applies_to_rooms BOOLEAN DEFAULT TRUE,
    applies_to_meals BOOLEAN DEFAULT TRUE,
    applies_to_packages BOOLEAN DEFAULT TRUE,
    applies_to_amenities BOOLEAN DEFAULT TRUE,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 23. service_provider_commission_settings
CREATE TABLE service_provider_commission_settings (
    commission_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    commission_percentage DECIMAL(5,2) NOT NULL,
    applies_to_rooms BOOLEAN DEFAULT TRUE,
    applies_to_meals BOOLEAN DEFAULT TRUE,
    applies_to_packages BOOLEAN DEFAULT TRUE,
    minimum_commission_amount DECIMAL(10,2) DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 24. service_provider_document
CREATE TABLE service_provider_document (
    document_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    document_type VARCHAR(100) NOT NULL,
    document_name VARCHAR(255) NOT NULL,
    document_url VARCHAR(255) NOT NULL,
    issue_date DATE,
    expiry_date DATE,
    is_verified BOOLEAN DEFAULT FALSE,
    verified_by INT,
    verified_at TIMESTAMP NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (verified_by) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 25. service_provider_booking_restrictions
CREATE TABLE service_provider_booking_restrictions (
    restriction_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    restriction_type VARCHAR(50) NOT NULL,
    min_stay_nights INT DEFAULT 1,
    max_stay_nights INT,
    start_date DATE,
    end_date DATE,
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 26. service_provider_statistics
CREATE TABLE service_provider_statistics (
    stats_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    total_bookings INT DEFAULT 0,
    total_revenue DECIMAL(15,2) DEFAULT 0,
    average_rating DECIMAL(3,2) DEFAULT 0,
    total_reviews INT DEFAULT 0,
    occupancy_rate DECIMAL(5,2) DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE KEY unique_provider_stats (service_provider_id)
);

-- 27. service_provider_review
CREATE TABLE service_provider_review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    user_id INT NOT NULL,
    rating TINYINT NOT NULL,
    title VARCHAR(255),
    comment TEXT,
    is_approved BOOLEAN DEFAULT FALSE,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_rating_range CHECK (rating BETWEEN 1 AND 5)
);

-- 28. service_provider_social
CREATE TABLE service_provider_social (
    social_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    platform VARCHAR(50) NOT NULL,
    profile_url VARCHAR(255),
    verification_status_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (verification_status_id) REFERENCES service_provider_social_verification_status(verification_status_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 29. accepted_payment_methods
CREATE TABLE accepted_payment_methods (
    payment_method_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    method_type VARCHAR(50) NOT NULL,
    method_details JSON,
    is_available BOOLEAN DEFAULT TRUE,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 30. service_provider_image
CREATE TABLE service_provider_image (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_name VARCHAR(255),
    image_description TEXT,
    caption VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_id) REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ============================================================
-- EXECUTION ORDER: 4 - Tables that reference other dependent tables
-- ============================================================

-- 31. service_provider_meal_image
CREATE TABLE service_provider_meal_image (
    meal_image_id INT PRIMARY KEY AUTO_INCREMENT,
    meal_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_name VARCHAR(255),
    image_description TEXT,
    caption VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (meal_id) REFERENCES service_provider_meal(meal_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 32. service_provider_room_feature
CREATE TABLE service_provider_room_feature (
    room_feature_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    feature_name VARCHAR(255) NOT NULL,
    feature_value VARCHAR(255),
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (room_id) REFERENCES service_provider_room(room_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 33. service_provider_room_amenity
CREATE TABLE service_provider_room_amenity (
    room_amenity_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    amenity_type_id INT NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    additional_notes TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (room_id) REFERENCES service_provider_room(room_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (amenity_type_id) REFERENCES amenity_type(amenity_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    UNIQUE KEY unique_room_amenity (room_id, amenity_type_id)
);

-- 34. service_provider_room_availability
CREATE TABLE service_provider_room_availability (
    availability_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    date DATE NOT NULL,
    available_rooms INT DEFAULT 0,
    booked_rooms INT DEFAULT 0,
    local_price_for_date DECIMAL(10,2),
    foreign_price_for_date DECIMAL(10,2),
    requirements TEXT,
    special_note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    UNIQUE KEY unique_room_date (room_id, date),
    FOREIGN KEY (room_id) REFERENCES service_provider_room(room_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 35. service_provider_room_image
CREATE TABLE service_provider_room_image (
    room_image_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_name VARCHAR(255),
    image_description TEXT,
    caption VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (room_id) REFERENCES service_provider_room(room_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 36. service_provider_package_feature
CREATE TABLE service_provider_package_feature (
    package_feature_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_package_id INT NOT NULL,
    feature_name VARCHAR(255) NOT NULL,
    feature_value VARCHAR(255),
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_package_id) REFERENCES service_provider_package(service_provider_package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 37. service_provider_package_inclusion
CREATE TABLE service_provider_package_inclusion (
    inclusion_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_package_id INT NOT NULL,
    inclusion_name VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_package_id) REFERENCES service_provider_package(service_provider_package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 38. service_provider_package_image
CREATE TABLE service_provider_package_image (
    package_image_id INT PRIMARY KEY AUTO_INCREMENT,
    service_provider_package_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_name VARCHAR(255),
    image_description TEXT,
    caption VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_provider_package_id) REFERENCES service_provider_package(service_provider_package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 39. service_provider_facility_image
CREATE TABLE service_provider_facility_image (
    facility_image_id INT PRIMARY KEY AUTO_INCREMENT,
    facility_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_name VARCHAR(255),
    image_description TEXT,
    caption VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (facility_id) REFERENCES service_provider_facility(facility_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 40. service_provider_review_rating_detail
CREATE TABLE service_provider_review_rating_detail (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    category_id INT NOT NULL,
    rating TINYINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (review_id) REFERENCES service_provider_review(review_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES service_provider_review_rating_category(category_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ============================================================
-- Performance Indexes (Corrected)
-- ============================================================
CREATE INDEX idx_service_provider_type ON service_provider(service_provider_type_id);
CREATE INDEX idx_service_provider_status ON service_provider(status_id);
CREATE INDEX idx_service_provider_approval ON service_provider(approval_status_id);
CREATE INDEX idx_service_provider_email ON service_provider(email);
CREATE INDEX idx_service_provider_currency ON service_provider(currency_id);

CREATE INDEX idx_room_provider ON service_provider_room(service_provider_id);
CREATE INDEX idx_room_status ON service_provider_room(status_id);
CREATE INDEX idx_room_type ON service_provider_room(room_type_id);

CREATE INDEX idx_package_provider ON service_provider_package(service_provider_id);
CREATE INDEX idx_package_dates ON service_provider_package(start_date, end_date);
CREATE INDEX idx_package_status ON service_provider_package(status_id);
CREATE INDEX idx_package_dates_active ON service_provider_package(start_date, end_date, status_id);

CREATE INDEX idx_room_availability ON service_provider_room_availability(date, available_rooms);
CREATE INDEX idx_meal_available ON service_provider_meal(availa-- ble, status_id);
CREATE INDEX idx_review_rating_approved ON service_provider_review(rating, is_approved, status_id);

CREATE INDEX idx_contact_primary ON service_provider_contact_person(service_provider_id, is_primary);
CREATE INDEX idx_bank_primary ON service_provider_bank(service_provider_id, is_primary);