CREATE TABLE package_day_accommodation (
    package_day_accommodation_id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,
    day_number INT NOT NULL,
    breakfast BOOLEAN DEFAULT FALSE,
    breakfast_description TEXT,
    lunch BOOLEAN DEFAULT FALSE,
    lunch_description TEXT,
    dinner BOOLEAN DEFAULT FALSE,
    dinner_description TEXT,
    morning_tea BOOLEAN DEFAULT FALSE,
    morning_tea_description TEXT,
    evening_tea BOOLEAN DEFAULT FALSE,
    evening_tea_description TEXT,
    snacks BOOLEAN DEFAULT FALSE,
    snack_note TEXT,
    hotel_id INT NULL,
    transport_id INT NULL,
    other_notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    CONSTRAINT uq_package_day UNIQUE (package_id, day_number),
    CONSTRAINT fk_pda_package
        FOREIGN KEY (package_id)
        REFERENCES packages(package_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_pda_hotel
        FOREIGN KEY (hotel_id)
        REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    CONSTRAINT fk_pda_transport
        FOREIGN KEY (transport_id)
        REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);




INSERT INTO package_day_accommodation (
    package_id, day_number,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id,
    transport_id,
    other_notes
) VALUES
(1, 1,
 TRUE, 'Hotel buffet breakfast',
 TRUE, 'Lunch at local restaurant',
 TRUE, 'Dinner at hotel',
 TRUE, 'Tea at hotel lobby',
 TRUE, 'Tea during sightseeing',
 TRUE, 'Light snacks provided',
 1,
 1,
 'Arrival day and city tour'),

(1, 2,
 TRUE, 'Breakfast at hotel',
 TRUE, 'Lunch en route',
 FALSE, NULL,
 FALSE, NULL,
 TRUE, 'Evening tea at viewpoint',
 FALSE, NULL,
 1,
 1,
 'Departure day');


INSERT INTO package_day_accommodation (
    package_id, day_number,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id,
    transport_id,
    other_notes
) VALUES
(2, 1,
 TRUE, 'Breakfast at eco lodge',
 TRUE, 'Traditional Sri Lankan lunch',
 TRUE, 'BBQ dinner',
 FALSE, NULL,
 TRUE, 'Tea by the lake',
 TRUE, 'Trail snacks',
 2,
 2,
 'Nature walk and lake visit'),

(2, 2,
 TRUE, 'Breakfast at lodge',
 FALSE, NULL,
 TRUE, 'Dinner at nearby hotel',
 TRUE, 'Morning tea during hike',
 FALSE, NULL,
 FALSE, NULL,
 2,
 2,
 'Hiking and relaxation');

INSERT INTO package_day_accommodation (
    package_id, day_number,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id,
    transport_id,
    other_notes
) VALUES
(3, 1,
 TRUE, 'Breakfast at beach resort',
 TRUE, 'Seafood lunch',
 TRUE, 'Candlelight dinner',
 FALSE, NULL,
 TRUE, 'Beachside tea',
 TRUE, 'Fruit snacks',
 1,
 1,
 'Beach activities and leisure'),

(3, 2,
 TRUE, 'Breakfast at resort',
 TRUE, 'Lunch at beach cafe',
 FALSE, NULL,
 TRUE, 'Morning tea at resort',
 FALSE, NULL,
 FALSE, NULL,
 1,
 1,
 'Checkout and sightseeing');






