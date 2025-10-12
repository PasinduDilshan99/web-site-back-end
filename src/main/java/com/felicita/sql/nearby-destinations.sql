use travelagencydb;

CREATE TABLE nearby_destinations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    nearby_destination_id INT NOT NULL,
    distance_km DECIMAL(5, 2) NULL,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id),
    FOREIGN KEY (nearby_destination_id) REFERENCES destination(destination_id),
    CONSTRAINT unique_nearby_pair UNIQUE (destination_id, nearby_destination_id)
);


INSERT INTO nearby_destinations (destination_id, nearby_destination_id, distance_km) VALUES
-- 1. Kitulgala
(1, 4, 36.0),   -- Adams Peak
(1, 14, 53.0),  -- Kandy
(1, 2, 58.0),   -- Horton Plains
(1, 22, 87.0),  -- Nuwara Eliya
(1, 7, 94.0),   -- Udawalawe

-- 2. Horton Plains
(2, 22, 24.0),  -- Nuwara Eliya
(2, 24, 30.0),  -- Nuwara Eliya Hakgala
(2, 21, 54.0),  -- Ella
(2, 4, 65.0),   -- Adams Peak
(2, 23, 63.0),  -- Haputale

-- 3. Knuckles Range
(3, 14, 41.0),  -- Kandy
(3, 4, 73.0),   -- Adams Peak
(3, 22, 82.0),  -- Nuwara Eliya
(3, 11, 85.0),  -- Sigiriya
(3, 13, 92.0),  -- Polonnaruwa

-- 4. Adams Peak
(4, 1, 36.0),   -- Kitulgala
(4, 14, 52.0),  -- Kandy
(4, 22, 65.0),  -- Nuwara Eliya
(4, 7, 76.0),   -- Udawalawe
(4, 6, 93.0),   -- Yala

-- 5. Sinharaja Forest
(5, 7, 74.0),   -- Udawalawe
(5, 6, 96.0),   -- Yala
(5, 18, 93.0),  -- Bentota
(5, 17, 104.0), -- Unawatuna
(5, 16, 116.0), -- Mirissa

-- 6. Yala
(6, 10, 21.0),  -- Bundala
(6, 7, 66.0),   -- Udawalawe
(6, 16, 88.0),  -- Mirissa
(6, 20, 123.0), -- Arugam Bay
(6, 15, 122.0), -- Galle Fort

-- 7. Udawalawe
(7, 6, 66.0),   -- Yala
(7, 5, 74.0),   -- Sinharaja
(7, 4, 76.0),   -- Adams Peak
(7, 22, 85.0),  -- Nuwara Eliya
(7, 18, 97.0),  -- Bentota

-- 8. Wilpattu
(8, 12, 66.0),  -- Anuradhapura
(8, 9, 111.0),  -- Minneriya
(8, 11, 134.0), -- Sigiriya
(8, 13, 156.0), -- Polonnaruwa
(8, 19, 170.0), -- Trincomalee

-- 9. Minneriya
(9, 11, 32.0),  -- Sigiriya
(9, 12, 88.0),  -- Anuradhapura
(9, 13, 62.0),  -- Polonnaruwa
(9, 19, 109.0), -- Trincomalee
(9, 14, 92.0),  -- Kandy

-- 10. Bundala
(10, 6, 21.0),  -- Yala
(10, 16, 86.0), -- Mirissa
(10, 7, 88.0),  -- Udawalawe
(10, 17, 99.0), -- Unawatuna
(10, 18, 111.0),-- Bentota

-- 11. Sigiriya
(11, 9, 32.0),  -- Minneriya
(11, 13, 51.0), -- Polonnaruwa
(11, 14, 72.0), -- Kandy
(11, 12, 75.0), -- Anuradhapura
(11, 19, 118.0),-- Trincomalee

-- 12. Anuradhapura
(12, 8, 66.0),  -- Wilpattu
(12, 11, 75.0), -- Sigiriya
(12, 9, 88.0),  -- Minneriya
(12, 13, 104.0),-- Polonnaruwa
(12, 19, 135.0),-- Trincomalee

-- 13. Polonnaruwa
(13, 11, 51.0), -- Sigiriya
(13, 9, 62.0),  -- Minneriya
(13, 12, 104.0),-- Anuradhapura
(13, 14, 96.0), -- Kandy
(13, 19, 89.0), -- Trincomalee

-- 14. Kandy
(14, 3, 41.0),  -- Knuckles
(14, 11, 72.0), -- Sigiriya
(14, 22, 77.0), -- Nuwara Eliya
(14, 1, 53.0),  -- Kitulgala
(14, 4, 52.0),  -- Adams Peak

-- 15. Galle Fort
(15, 17, 6.0),  -- Unawatuna
(15, 18, 54.0), -- Bentota
(15, 16, 35.0), -- Mirissa
(15, 5, 95.0),  -- Sinharaja
(15, 6, 122.0), -- Yala

-- 16. Mirissa
(16, 15, 35.0), -- Galle
(16, 17, 34.0), -- Unawatuna
(16, 10, 86.0), -- Bundala
(16, 6, 88.0),  -- Yala
(16, 18, 73.0), -- Bentota

-- 17. Unawatuna
(17, 15, 6.0),  -- Galle
(17, 16, 34.0), -- Mirissa
(17, 18, 60.0), -- Bentota
(17, 5, 104.0), -- Sinharaja
(17, 10, 99.0), -- Bundala

-- 18. Bentota
(18, 17, 60.0), -- Unawatuna
(18, 15, 54.0), -- Galle
(18, 16, 73.0), -- Mirissa
(18, 5, 93.0),  -- Sinharaja
(18, 7, 97.0),  -- Udawalawe

-- 19. Trincomalee
(19, 13, 89.0), -- Polonnaruwa
(19, 9, 109.0), -- Minneriya
(19, 12, 135.0),-- Anuradhapura
(19, 20, 175.0),-- Arugam Bay
(19, 11, 118.0),-- Sigiriya

-- 20. Arugam Bay
(20, 6, 123.0), -- Yala
(20, 9, 145.0), -- Minneriya
(20, 13, 149.0),-- Polonnaruwa
(20, 19, 175.0),-- Trincomalee
(20, 21, 134.0),-- Ella

-- 21. Ella
(21, 22, 55.0), -- Nuwara Eliya
(21, 23, 25.0), -- Haputale
(21, 25, 28.0), -- Dambatenne
(21, 24, 59.0), -- Hakgala
(21, 2, 54.0),  -- Horton Plains

-- 22. Nuwara Eliya
(22, 24, 8.0),  -- Hakgala
(22, 2, 24.0),  -- Horton Plains
(22, 14, 77.0), -- Kandy
(22, 21, 55.0), -- Ella
(22, 4, 65.0),  -- Adams Peak

-- 23. Haputale
(23, 25, 10.0), -- Dambatenne
(23, 21, 25.0), -- Ella
(23, 22, 30.0), -- Nuwara Eliya
(23, 2, 63.0),  -- Horton Plains
(23, 24, 41.0), -- Hakgala

-- 24. Hakgala
(24, 22, 8.0),  -- Nuwara Eliya
(24, 2, 30.0),  -- Horton Plains
(24, 21, 59.0), -- Ella
(24, 23, 41.0), -- Haputale
(24, 25, 49.0), -- Dambatenne

-- 25. Dambatenne
(25, 23, 10.0), -- Haputale
(25, 21, 28.0), -- Ella
(25, 24, 49.0), -- Hakgala
(25, 22, 60.0), -- Nuwara Eliya
(25, 2, 70.0);  -- Horton Plains

SELECT
    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location,
    d.latitude,
    d.longitude,
    dc.category AS category_name,
    dc.description AS category_description,
    cs.name AS status_name,
    a.id AS activity_id,
    a.name AS activity_name,
    a.description AS activity_description,
    a.activities_category,
    a.duration_hours,
    a.available_from,
    a.available_to,
    a.price_local,
    a.price_foreigners,
    a.min_participate,
    a.max_participate,
    a.season,
    di.id AS image_id,
    di.name AS image_name,
    di.description AS image_description,
    di.image_url,
    nd.id AS nearby_id,
    nd.distance_km,
    nd_d.destination_id AS nearby_destination_id,
    nd_d.name AS nearby_destination_name,
    nd_d.location AS nearby_destination_location,
    nd_d.latitude AS nearby_destination_latitude,
    nd_d.longitude AS nearby_destination_longitude
FROM destination d
LEFT JOIN destination_categories dc 
    ON d.destination_category = dc.id
LEFT JOIN common_status cs 
    ON d.status = cs.id
LEFT JOIN activities a 
    ON d.destination_id = a.destination_id
LEFT JOIN destination_images di 
    ON d.destination_id = di.destination_id
LEFT JOIN nearby_destinations nd 
    ON d.destination_id = nd.destination_id
LEFT JOIN destination nd_d 
    ON nd.nearby_destination_id = nd_d.destination_id;

