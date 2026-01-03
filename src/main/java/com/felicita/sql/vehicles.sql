-- 1️⃣ Lookup Tables
CREATE TABLE fuel_types (
    fuel_type_id INT PRIMARY KEY AUTO_INCREMENT,
    fuel_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT
);

CREATE TABLE transmission_types (
    transmission_type_id INT PRIMARY KEY AUTO_INCREMENT,
    transmission_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT
);

CREATE TABLE air_conditioning_types (
    ac_type_id INT PRIMARY KEY AUTO_INCREMENT,
    ac_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT
);

CREATE TABLE vehicle_document_types (
    document_type_id INT PRIMARY KEY AUTO_INCREMENT,
    document_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_required BOOLEAN DEFAULT FALSE,
    validity_period_months INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT
);

-- 2️⃣ Vehicle Specifications
CREATE TABLE vehicle_specifications (
    specification_id INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT,
    generation VARCHAR(50),
    body_type VARCHAR(50),
    price DECIMAL(12,2),
    engine_type VARCHAR(50),
    engine_capacity VARCHAR(20),
    horsepower_hp DECIMAL(8,2),
    torque_nm DECIMAL(8,2),
    transmission_type_id INT,
    fuel_type_id INT,
    electric_range_km DECIMAL(8,2),
    drivetrain VARCHAR(50),
    top_speed_kmh DECIMAL(10,2),
    acceleration_0_100 DECIMAL(5,2),
    co2_emissions_g_km DECIMAL(8,2),
    doors INT,
    seat_capacity INT,
    dimensions VARCHAR(100),
    wheelbase_mm DECIMAL(8,2),
    weight_kg DECIMAL(10,2),
    wheel_size VARCHAR(20),
    tire_type VARCHAR(50),
    upholstery_type VARCHAR(100),
    ac_type_id INT,
    sunroof_type ENUM('None', 'Standard', 'Panoramic'),
    cruise_control_type ENUM('None', 'Standard', 'Adaptive'),
    entertainment_features TEXT,
    comfort_features TEXT,
    ncap_safety_rating INT,
    airbags_count INT,
    parking_camera ENUM('None', 'Rear', '360-degree'),
    lane_departure_warning BOOLEAN,
    safety_features TEXT,
    fuel_tank_capacity_liters DECIMAL(10,2),
    warranty_years INT,
    image_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (transmission_type_id) REFERENCES transmission_types(transmission_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (fuel_type_id) REFERENCES fuel_types(fuel_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (ac_type_id) REFERENCES air_conditioning_types(ac_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 3️⃣ Vehicle Specification Images
CREATE TABLE vehicle_specification_images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    specification_id INT NOT NULL, 
    image_url VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (specification_id) REFERENCES vehicle_specifications(specification_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 4️⃣ Vehicles
CREATE TABLE vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    registration_number VARCHAR(20) UNIQUE NOT NULL,
    specification_id INT,
    purchase_date DATE,
    purchase_price DECIMAL(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (specification_id) REFERENCES vehicle_specifications(specification_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

ALTER TABLE vehicles
ADD COLUMN status_id INT DEFAULT 1,
ADD COLUMN owner_id INT,
ADD COLUMN assigned_driver_id INT,
ADD CONSTRAINT fk_vehicles_status
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
ADD CONSTRAINT fk_vehicles_owner
    FOREIGN KEY (owner_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
ADD CONSTRAINT fk_vehicles_driver
    FOREIGN KEY (assigned_driver_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE SET NULL;


-- 5️⃣ Vehicle Images
CREATE TABLE vehicle_images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 6️⃣ Vehicle Details (1:1)
CREATE TABLE vehicle_details (
    vehicle_details_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL UNIQUE,
    chassis_number VARCHAR(50),
    engine_number VARCHAR(50),
    registration_date DATE,
    insurance_company VARCHAR(100),
    insurance_policy_number VARCHAR(50),
    insurance_expiry_date DATE,
    emission_test_number VARCHAR(50),
    emission_expiry_date DATE,
    permit_number VARCHAR(50),
    permit_expiry_date DATE,
    warranty_expiry_date DATE,
    gps_tracking_id VARCHAR(100),
    last_service_date DATE,
    next_service_date DATE,
    mileage INT,
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 7️⃣ Vehicle Service History
CREATE TABLE vehicle_service_history (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    service_date DATE NOT NULL,
    service_center VARCHAR(150),
    service_type VARCHAR(100),
    odometer_reading INT,
    cost DECIMAL(10,2),
    description TEXT,
    next_service_due DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 8️⃣ Vehicle Service Images
CREATE TABLE vehicle_service_images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    service_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (service_id) REFERENCES vehicle_service_history(service_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 9️⃣ Vehicle Inspections
CREATE TABLE vehicle_inspections (
    inspection_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    inspection_type ENUM('Emission', 'Safety', 'Annual', 'Other'),
    inspection_date DATE,
    expiry_date DATE,
    inspection_center VARCHAR(150),
    result ENUM('Pass', 'Fail', 'Pending'),
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 🔟 Vehicle Documents
CREATE TABLE vehicle_documents (
    document_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    document_type_id INT,
    document_name VARCHAR(100),
    file_url VARCHAR(255),
    issue_date DATE,
    expiry_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (document_type_id) REFERENCES vehicle_document_types(document_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 1️⃣1️⃣ Vehicle Fuel Records
CREATE TABLE vehicle_fuel_records (
    fuel_record_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    refuel_date DATE NOT NULL,
    fuel_type_id INT,
    quantity_liters DECIMAL(10,2),
    cost DECIMAL(10,2),
    odometer_reading INT,
    refuel_station VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fuel_type_id) REFERENCES fuel_types(fuel_type_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 1️⃣2️⃣ Vehicle Assignments
CREATE TABLE vehicle_assignments (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    purpose VARCHAR(255),
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE vehicle_assignments
ADD COLUMN driver_id INT,
ADD FOREIGN KEY (driver_id) REFERENCES user(user_id)
    ON UPDATE CASCADE ON DELETE SET NULL;


-- 1️⃣3️⃣ Vehicle Usage Log
CREATE TABLE vehicle_usage_log (
    usage_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    package_id INT,
    tour_id INT,
    start_datetime DATETIME NOT NULL,
    end_datetime DATETIME,
    start_odometer INT,
    end_odometer INT,
    route_description VARCHAR(255),
    purpose VARCHAR(255),
    fuel_used_liters DECIMAL(10,2),
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    terminated_at TIMESTAMP NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 1️⃣4️⃣ Vehicle Maintenance Schedule
CREATE TABLE vehicle_maintenance_schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    maintenance_type VARCHAR(100),
    interval_km INT,
    interval_days INT,
    last_maintenance_date DATE,
    next_due_date DATE,
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    terminated_at TIMESTAMP NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 1️⃣5️⃣ Vehicle Locations
CREATE TABLE vehicle_locations (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    latitude DECIMAL(10,6),
    longitude DECIMAL(10,6),
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);


REATE TABLE vehicle_type (
    vehicle_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    CONSTRAINT fk_vehicle_type_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

ALTER TABLE vehicles
ADD COLUMN vehicle_type_id INT NOT NULL AFTER registration_number;

INSERT INTO vehicle_type (name, description, status_id)
VALUES
('Car', 'Standard passenger car', 1),
('Van', 'Tourist van', 1),
('Bus', 'Large tour bus', 1),
('Jeep', 'Off-road vehicle', 1);


ALTER TABLE vehicles
ADD CONSTRAINT fk_vehicles_vehicle_type
    FOREIGN KEY (vehicle_type_id)
    REFERENCES vehicle_type(vehicle_type_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;
