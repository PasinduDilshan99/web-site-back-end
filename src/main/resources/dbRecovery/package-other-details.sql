CREATE TABLE package_inclusion (
    package_inclusion_id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    inclusion_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_package_inclusion_tour
        FOREIGN KEY (package_id)
        REFERENCES packages(package_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_package_inclusion_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE package_exclusion (
    package_exclusion_id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    exclusion_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_package_exclusion_tour
        FOREIGN KEY (package_id)
        REFERENCES packages(package_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_package_exclusion_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);


CREATE TABLE package_condition (
    package_condition_id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    condition_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_package_condition_tour
        FOREIGN KEY (package_id)
        REFERENCES packages(package_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_package_condition_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE package_travel_tips (
    package_travel_tip_id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    tip_title VARCHAR(150),          -- Optional (e.g. "Footwear")
    tip_description TEXT NOT NULL,   -- "Comfortable shoes"
    display_order INT DEFAULT 1,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (package_id) REFERENCES packages(package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);




-- =========================================
-- PACKAGE INCLUSIONS
-- =========================================
INSERT INTO package_inclusion (package_id, inclusion_text, display_order, status_id, created_by, updated_by)
VALUES
(1, 'Airport pickup and drop', 1, 1, 1, 1),
(1, '5-star hotel accommodation', 2, 1, 1, 1),
(1, 'All meals included', 3, 1, 1, 1),
(1, 'Guided city tours', 4, 1, 1, 1),
(1, 'Travel insurance', 5, 1, 1, 1),
(1, 'Free Wi-Fi in hotels', 6, 1, 1, 1),
(1, 'Welcome drink on arrival', 7, 1, 1, 1),
(1, 'Airport lounge access', 8, 1, 1, 1),
(1, 'Complimentary spa session', 9, 1, 1, 1),
(1, 'Souvenir gift pack', 10, 1, 1, 1),

(2, 'Airport pickup and drop', 1, 1, 1, 1),
(2, '3-star hotel accommodation', 2, 1, 1, 1),
(2, 'Breakfast only', 3, 1, 1, 1),
(2, 'City tour by bus', 4, 1, 1, 1),
(2, 'Travel insurance', 5, 1, 1, 1),
(2, 'Free Wi-Fi in hotel', 6, 1, 1, 1),
(2, 'Welcome drink on arrival', 7, 1, 1, 1),
(2, 'Discount vouchers', 8, 1, 1, 1),
(2, 'Local snacks', 9, 1, 1, 1),
(2, 'Souvenir keychain', 10, 1, 1, 1);

-- =========================================
-- PACKAGE EXCLUSIONS
-- =========================================
INSERT INTO package_exclusion (package_id, exclusion_text, display_order, status_id, created_by, updated_by)
VALUES
(1, 'Airfare to destination', 1, 1, 1, 1),
(1, 'Personal expenses', 2, 1, 1, 1),
(1, 'Optional excursions', 3, 1, 1, 1),
(1, 'Tips for guides', 4, 1, 1, 1),
(1, 'Alcoholic beverages', 5, 1, 1, 1),
(1, 'Laundry services', 6, 1, 1, 1),
(1, 'Room service', 7, 1, 1, 1),
(1, 'Medical expenses', 8, 1, 1, 1),
(1, 'Visa fees', 9, 1, 1, 1),
(1, 'Travel upgrades', 10, 1, 1, 1),

(2, 'Airfare to destination', 1, 1, 1, 1),
(2, 'Personal expenses', 2, 1, 1, 1),
(2, 'Optional excursions', 3, 1, 1, 1),
(2, 'Tips for guides', 4, 1, 1, 1),
(2, 'Alcoholic beverages', 5, 1, 1, 1),
(2, 'Laundry services', 6, 1, 1, 1),
(2, 'Room service', 7, 1, 1, 1),
(2, 'Medical expenses', 8, 1, 1, 1),
(2, 'Visa fees', 9, 1, 1, 1),
(2, 'Travel upgrades', 10, 1, 1, 1);

-- =========================================
-- PACKAGE CONDITIONS
-- =========================================
INSERT INTO package_condition (package_id, condition_text, display_order, status_id, created_by, updated_by)
VALUES
(1, 'Booking must be made 30 days in advance', 1, 1, 1, 1),
(1, 'Cancellations up to 7 days before arrival', 2, 1, 1, 1),
(1, 'Maximum 2 children under 12 free', 3, 1, 1, 1),
(1, 'Package cannot be combined with other offers', 4, 1, 1, 1),
(1, 'Pets not allowed', 5, 1, 1, 1),
(1, 'Valid passport required', 6, 1, 1, 1),
(1, 'Travel insurance mandatory', 7, 1, 1, 1),
(1, 'Check-in from 2 PM', 8, 1, 1, 1),
(1, 'Check-out before 12 PM', 9, 1, 1, 1),
(1, 'No smoking in rooms', 10, 1, 1, 1),

(2, 'Booking must be made 15 days in advance', 1, 1, 1, 1),
(2, 'Cancellations up to 3 days before arrival', 2, 1, 1, 1),
(2, 'Maximum 1 child under 12 free', 3, 1, 1, 1),
(2, 'Package cannot be combined with other offers', 4, 1, 1, 1),
(2, 'Pets not allowed', 5, 1, 1, 1),
(2, 'Valid passport required', 6, 1, 1, 1),
(2, 'Travel insurance recommended', 7, 1, 1, 1),
(2, 'Check-in from 3 PM', 8, 1, 1, 1),
(2, 'Check-out before 11 AM', 9, 1, 1, 1),
(2, 'No smoking in rooms', 10, 1, 1, 1);

-- =========================================
-- PACKAGE TRAVEL TIPS
-- =========================================
INSERT INTO package_travel_tips (package_id, tip_title, tip_description, display_order, status_id, created_by, updated_by)
VALUES
(1, 'Footwear', 'Comfortable walking shoes', 1, 1, 1, 1),
(1, 'Clothing', 'Light cotton clothes for daytime', 2, 1, 1, 1),
(1, 'Clothing', 'Warm jacket for evenings', 3, 1, 1, 1),
(1, 'Documents', 'Carry passport and ID copies', 4, 1, 1, 1),
(1, 'Health', 'Bring necessary medications', 5, 1, 1, 1),
(1, 'Gadgets', 'Camera and chargers', 6, 1, 1, 1),
(1, 'Money', 'Local currency for small expenses', 7, 1, 1, 1),
(1, 'Weather', 'Sunscreen and hat for sun protection', 8, 1, 1, 1),
(1, 'Transport', 'Use public transport apps', 9, 1, 1, 1),
(1, 'Etiquette', 'Respect local customs', 10, 1, 1, 1),

(2, 'Footwear', 'Comfortable shoes for walking', 1, 1, 1, 1),
(2, 'Clothing', 'Casual clothes suitable for sightseeing', 2, 1, 1, 1),
(2, 'Documents', 'Carry ID and copies', 3, 1, 1, 1),
(2, 'Health', 'Bring essential medications', 4, 1, 1, 1),
(2, 'Money', 'Local currency for small expenses', 5, 1, 1, 1),
(2, 'Gadgets', 'Phone charger and power bank', 6, 1, 1, 1),
(2, 'Weather', 'Umbrella or raincoat if rainy season', 7, 1, 1, 1),
(2, 'Transport', 'Use local taxis or buses', 8, 1, 1, 1),
(2, 'Etiquette', 'Respect local customs', 9, 1, 1, 1),
(2, 'Snacks', 'Pack light snacks for trips', 10, 1, 1, 1);











