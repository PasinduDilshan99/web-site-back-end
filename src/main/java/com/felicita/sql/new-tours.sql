use travelagencydb;

-- =============================================
-- CORE TABLES
-- =============================================

-- Seasons Table
CREATE TABLE seasons (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour Type Table
CREATE TABLE tour_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour Category Table
CREATE TABLE tour_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package Type Table
CREATE TABLE package_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour Table
CREATE TABLE tour (
    tour_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    tour_type INT,
    tour_category INT,
    duration INT,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    start_location VARCHAR(255),
    end_location VARCHAR(255),
    season INT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_type) REFERENCES tour_type(id),
    FOREIGN KEY (tour_category) REFERENCES tour_category(id),
    FOREIGN KEY (season) REFERENCES seasons(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Packages Table
CREATE TABLE packages (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    total_price DECIMAL(10, 2),
    discount_percentage DECIMAL(5, 2),
    start_date DATE,
    end_date DATE,
    color VARCHAR(50),
    status INT NOT NULL,
    hover_color VARCHAR(50),
    min_person_count INT,
    max_person_count INT,
    price_per_person DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Destination Categories Table
CREATE TABLE destination_categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Destination Table
CREATE TABLE destination (
    destination_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    destination_category INT,
    location VARCHAR(255),
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_category) REFERENCES destination_categories(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);


-- Activity Category Table
CREATE TABLE activity_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);


-- Activities Table
CREATE TABLE activities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    activities_category VARCHAR(100),
    duration_hours DECIMAL(5, 2),
    available_from TIME,
    available_to TIME,
    price_local DECIMAL(10, 2),
    price_foreigners DECIMAL(10, 2),
    min_participate INT,
    max_participate INT,
    season VARCHAR(255),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- SCHEDULE TABLES
-- =============================================

-- Tour Schedule Table
CREATE TABLE tour_schedule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    tour_id INT NOT NULL,
    assume_start_date DATE,
    assume_end_date DATE,
    duration_start INT,
    duration_end INT,
    special_note TEXT,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities Schedule Table
CREATE TABLE activities_schedule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    activity_id INT NOT NULL,
    assume_start_date DATE,
    assume_end_date DATE,
    duration_hours_start DECIMAL(5, 2),
    duration_hours_end DECIMAL(5, 2),
    special_note TEXT,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package Schedule Table
CREATE TABLE package_schedule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    package_id INT NOT NULL,
    assume_start_date DATE,
    assume_end_date DATE,
    duration_start INT,
    duration_end INT,
    special_note TEXT,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- FEATURES & REQUIREMENTS TABLES
-- =============================================

-- Features Table
CREATE TABLE features (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255),
    description TEXT,
    status INT NOT NULL,
    color VARCHAR(50),
    special_note TEXT,
    hover_color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities Requirement Table
CREATE TABLE activities_requirement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255),
    description TEXT,
    status INT NOT NULL,
    color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- HISTORY TABLES
-- =============================================

-- Tour History Table
CREATE TABLE tour_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_schedule_id INT NOT NULL,
    package_id INT,
    number_of_participate INT,
    rating DECIMAL(3, 2),
    duration INT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    color VARCHAR(50),
    hover_color VARCHAR(50),
    start_date DATE,
    end_date DATE,
    vehicle_number VARCHAR(255),
    driver_id INT,
    guide_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_schedule_id) REFERENCES tour_schedule(id),
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities History Table
CREATE TABLE activities_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    number_of_participate INT,
    activity_start DATETIME,
    activity_end DATETIME,
    rating DECIMAL(3, 2),
    special_note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activity_schedule_id) REFERENCES activities_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package History Table
CREATE TABLE package_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_schedule_id INT NOT NULL,
    number_of_participate INT,
    rating DECIMAL(3, 2),
    duration INT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    color VARCHAR(50),
    hover_color VARCHAR(50),
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_schedule_id) REFERENCES package_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- REVIEW TABLES
-- =============================================

-- Tour Review Table
CREATE TABLE tour_review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    review TEXT,
    rating DECIMAL(3, 2),
    description TEXT,
    status INT NOT NULL,
    number_of_participate INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_schedule_id) REFERENCES tour_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities Review Table
CREATE TABLE activities_review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    review TEXT,
    rating DECIMAL(3, 2),
    description TEXT,
    status INT NOT NULL,
    number_of_participate INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activity_schedule_id) REFERENCES activities_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package Review Table
CREATE TABLE package_review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    review TEXT,
    rating DECIMAL(3, 2),
    description TEXT,
    status INT NOT NULL,
    number_of_participate INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_schedule_id) REFERENCES package_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- IMAGE TABLES
-- =============================================

-- Seasons Images Table
CREATE TABLE seasons_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    season_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (season_id) REFERENCES seasons(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour Images Table
CREATE TABLE tour_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package Images Table
CREATE TABLE package_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    image_url VARCHAR(500),
    color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_id) REFERENCES packages(package_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Destination Images Table
CREATE TABLE destination_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Destination Categories Images Table
CREATE TABLE destination_categories_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_categories_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_categories_id) REFERENCES destination_categories(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities Images Table
CREATE TABLE activities_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour History Images Table
CREATE TABLE tour_history_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    image_url VARCHAR(500),
    color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_schedule_id) REFERENCES tour_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities History Images Table
CREATE TABLE activities_history_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activities_history_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activities_history_id) REFERENCES activities_history(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package History Images Table
CREATE TABLE package_history_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_schedule_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_schedule_id) REFERENCES package_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Tour Review Images Table
CREATE TABLE tour_review_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_review_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (tour_review_id) REFERENCES tour_review(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Activities Review Images Table
CREATE TABLE activities_review_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activities_review_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (activities_review_id) REFERENCES activities_review(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Package Review Images Table
CREATE TABLE package_review_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_review_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_review_id) REFERENCES package_review(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- =============================================
-- INDEXES FOR BETTER PERFORMANCE
-- =============================================

-- Status indexes
-- CREATE INDEX idx_tour_status ON tour(status);
-- CREATE INDEX idx_packages_status ON packages(status);
-- CREATE INDEX idx_destination_status ON destination(status);
-- CREATE INDEX idx_activities_status ON activities(status);

-- Foreign key indexes
-- CREATE INDEX idx_packages_tour_id ON packages(tour_id);
-- CREATE INDEX idx_activities_destination_id ON activities(destination_id);
-- CREATE INDEX idx_tour_schedule_tour_id ON tour_schedule(tour_id);
-- CREATE INDEX idx_activities_schedule_activity_id ON activities_schedule(activity_id);
-- CREATE INDEX idx_package_schedule_package_id ON package_schedule(package_id);

-- Date indexes for scheduling
-- CREATE INDEX idx_tour_schedule_dates ON tour_schedule(assume_start_date, assume_end_date);
-- CREATE INDEX idx_activities_schedule_dates ON activities_schedule(assume_start_date, assume_end_date);
-- CREATE INDEX idx_package_schedule_dates ON package_schedule(assume_start_date, assume_end_date);