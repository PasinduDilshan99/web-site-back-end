CREATE TABLE tour_inclusion (
    tour_inclusion_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    inclusion_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_tour_inclusion_tour
        FOREIGN KEY (tour_id)
        REFERENCES tour(tour_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_tour_inclusion_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE tour_exclusion (
    tour_exclusion_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    exclusion_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_tour_exclusion_tour
        FOREIGN KEY (tour_id)
        REFERENCES tour(tour_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_tour_exclusion_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);


CREATE TABLE tour_condition (
    tour_condition_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    condition_text TEXT NOT NULL,
    display_order INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT fk_tour_condition_tour
        FOREIGN KEY (tour_id)
        REFERENCES tour(tour_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_tour_condition_status
        FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE tour_travel_tips (
    tour_travel_tip_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
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
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


INSERT INTO tour_inclusion (tour_id, inclusion_text, status_id, created_by) VALUES
(1, 'Daily breakfast included', 1, 1),
(1, 'Private air-conditioned vehicle', 1, 1),
(1, 'English speaking tour guide', 1, 1),
(1, 'Airport pick-up and drop-off', 1, 1),
(1, 'Entrance fees to attractions', 1, 1),
(1, 'Hotel accommodation', 1, 1),
(1, 'Government taxes included', 1, 1),
(1, 'Bottled water during travel', 1, 1),
(1, 'Fuel and parking charges', 1, 1),
(1, 'Driver accommodation and meals', 1, 1);

INSERT INTO tour_exclusion (tour_id, exclusion_text, status_id, created_by) VALUES
(1, 'International airfare', 1, 1),
(1, 'Visa fees', 1, 1),
(1, 'Personal expenses', 1, 1),
(1, 'Lunch and dinner unless specified', 1, 1),
(1, 'Travel insurance', 1, 1),
(1, 'Tips and gratuities', 1, 1),
(1, 'Camera and video permits', 1, 1),
(1, 'Optional activities', 1, 1),
(1, 'Early check-in or late check-out', 1, 1),
(1, 'Anything not mentioned in inclusions', 1, 1);

INSERT INTO tour_condition (tour_id, condition_text, status_id, created_by) VALUES
(1, 'Minimum two passengers required', 1, 1),
(1, 'Rates are subject to availability', 1, 1),
(1, 'Prices may change during peak season', 1, 1),
(1, 'Cancellation charges apply', 1, 1),
(1, 'Valid passport required', 1, 1),
(1, 'Tour itinerary may change due to weather', 1, 1),
(1, 'Children must be accompanied by adults', 1, 1),
(1, 'No refunds for unused services', 1, 1),
(1, 'Check-in time as per hotel policy', 1, 1),
(1, 'Payment must be completed before arrival', 1, 1);


INSERT INTO tour_travel_tips
(tour_id, tip_title, tip_description, display_order, status_id, created_by)
VALUES
(1, 'Footwear', 'Comfortable walking shoes are recommended', 1, 1, 1),
(1, 'Sun Protection', 'Carry sunscreen, sunglasses, and a hat', 2, 1, 1),
(1, 'Clothing', 'Modest clothing required for temples', 3, 1, 1),
(1, 'Hydration', 'Keep a reusable water bottle', 4, 1, 1),
(1, 'Documents', 'Carry passport copies and travel insurance', 5, 1, 1);



