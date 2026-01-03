CREATE TABLE tour_day_accommodation (
    tour_day_accommodation_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,
    day INT NOT NULL,
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
    CONSTRAINT uq_tour_day UNIQUE (tour_id, day),
    CONSTRAINT fk_tour_day_accommodation_tour
        FOREIGN KEY (tour_id)
        REFERENCES tour(tour_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_tour_day_accommodation_hotel
        FOREIGN KEY (hotel_id)
        REFERENCES service_provider(service_provider_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    CONSTRAINT fk_tour_day_accommodation_transport
        FOREIGN KEY (transport_id)
        REFERENCES vehicles(vehicle_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);


INSERT INTO tour_day_accommodation (
    tour_id, day,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id, transport_id,
    other_notes
) VALUES (
    1, 1,
    TRUE, 'Breakfast at hotel restaurant',
    TRUE, 'Lunch at local restaurant',
    FALSE, NULL,
    TRUE, 'Tea at hotel lobby',
    TRUE, 'Tea during evening travel',
    FALSE, NULL,
    1, 1,
    'Early start, light meals'
);


INSERT INTO tour_day_accommodation (
    tour_id, day,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id, transport_id,
    other_notes
) VALUES (
    1, 2,
    TRUE, 'Breakfast at hotel',
    FALSE, NULL,
    TRUE, 'Dinner at beach restaurant',
    FALSE, NULL,
    TRUE, 'Evening tea at hotel',
    TRUE, 'Snacks during travel',
    2, 1,
    'Check-out and transfer'
);


INSERT INTO tour_day_accommodation (
    tour_id, day,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id, transport_id,
    other_notes
) VALUES (
    2, 1,
    TRUE, 'Hotel buffet breakfast',
    TRUE, 'Lunch en route',
    TRUE, 'Dinner at resort',
    TRUE, 'Morning tea stop',
    TRUE, 'Evening tea at hotel',
    FALSE, NULL,
    1, 2,
    'Arrival day'
);


INSERT INTO tour_day_accommodation (
    tour_id, day,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id, transport_id,
    other_notes
) VALUES (
    2, 2,
    TRUE, 'Breakfast at hotel',
    TRUE, 'Lunch at city restaurant',
    TRUE, 'Dinner cultural show',
    FALSE, NULL,
    TRUE, 'Tea during sightseeing',
    TRUE, 'Snacks provided',
    1, 2,
    'Full sightseeing day'
);


INSERT INTO tour_day_accommodation (
    tour_id, day,
    breakfast, breakfast_description,
    lunch, lunch_description,
    dinner, dinner_description,
    morning_tea, morning_tea_description,
    evening_tea, evening_tea_description,
    snacks, snack_note,
    hotel_id, transport_id,
    other_notes
) VALUES (
    2, 3,
    TRUE, 'Final day breakfast',
    FALSE, NULL,
    FALSE, NULL,
    FALSE, NULL,
    FALSE, NULL,
    FALSE, NULL,
    2, 2,
    'Departure day'
);

