CREATE TABLE tour_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,   -- e.g., 'LOCAL', 'FOREIGN'
    description VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_tour_type_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);


CREATE TABLE tour_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    status_id INT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_tour_cat_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);

CREATE TABLE tour (
    tour_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    tour_type_id INT NOT NULL,                -- references tour_type table
    tour_category_id INT NOT NULL,
    duration_days INT NOT NULL,
    start_date DATE,
    end_date DATE,
    start_location_id INT NOT NULL,
    end_location_id INT NOT NULL,
    max_people INT,
    min_people INT,
    price_per_person DECIMAL(10,2),
    tour_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_tour_category FOREIGN KEY (tour_category_id) REFERENCES tour_category(id),
    CONSTRAINT fk_tour_status FOREIGN KEY (tour_status_id) REFERENCES common_status(id),
    CONSTRAINT fk_tour_start_location FOREIGN KEY (start_location_id) REFERENCES destination(id),
    CONSTRAINT fk_tour_end_location FOREIGN KEY (end_location_id) REFERENCES destination(id),
    CONSTRAINT fk_tour_type FOREIGN KEY (tour_type_id) REFERENCES tour_type(id)
);

CREATE TABLE tour_image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150),
    tour_id INT NOT NULL,
    description TEXT,
    status_id INT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_tour_image_tour FOREIGN KEY (tour_id)
        REFERENCES tour (tour_id),
    CONSTRAINT fk_tour_image_status FOREIGN KEY (status_id)
        REFERENCES common_status (id)
);

CREATE TABLE tour_destination (
    tour_id INT NOT NULL,
    destination_id INT NOT NULL,
    PRIMARY KEY (tour_id, destination_id),
    CONSTRAINT fk_td_tour FOREIGN KEY (tour_id) REFERENCES tour(tour_id),
    CONSTRAINT fk_td_destination FOREIGN KEY (destination_id) REFERENCES destination(id)
);

INSERT INTO tour_type (name, description, status_id, created_by) VALUES
('LOCAL', 'Local tours within the country', 1, 1),
('FOREIGN', 'Tours to foreign countries', 1, 1);

INSERT INTO tour_category (name, description, status_id, image_url, created_by) VALUES
('Adventure', 'Adventure and thrill tours', 1, 'adventure.jpg', 1),
('Cultural', 'Cultural and heritage tours', 1, 'cultural.jpg', 1),
('Beach', 'Relaxing beach tours', 1, 'beach.jpg', 1);

INSERT INTO destination (name, description, common_status_id, destination_category_id, location, rating, popularity, created_by) VALUES
('Colombo', 'Capital city of Sri Lanka', 1, 1, 'Sri Lanka', 4.5, 100, 1),
('Kandy', 'Cultural capital', 1, 2, 'Sri Lanka', 4.7, 80, 1),
('Galle', 'Historic coastal city', 1, 3, 'Sri Lanka', 4.6, 60, 1),
('Paris', 'City of lights', 1, 2, 'France', 4.9, 120, 1);

INSERT INTO tour (name, description, tour_type_id, tour_category_id, duration_days, start_date, end_date, start_location_id, end_location_id, max_people, min_people, price_per_person, tour_status_id, created_by) VALUES
('Colombo City Tour', 'Explore the capital city of Sri Lanka', 1, 2, 2, '2025-12-01', '2025-12-02', 1, 1, 20, 5, 150.00, 1, 1),
('Kandy Cultural Tour', 'Visit temples and cultural sites in Kandy', 1, 2, 3, '2025-12-05', '2025-12-07', 1, 2, 15, 5, 200.00, 1, 1),
('Paris Highlights', 'Famous landmarks of Paris', 2, 2, 5, '2026-01-10', '2026-01-15', 4, 4, 25, 10, 1200.00, 1, 1);

INSERT INTO tour_image (name, tour_id, description, status_id, image_url, created_by) VALUES
('Colombo City Tour Image', 1, 'Main attractions of Colombo', 1, 'colombo.jpg', 1),
('Kandy Cultural Tour Image', 2, 'Temples and cultural sites', 1, 'kandy.jpg', 1),
('Paris Highlights Image', 3, 'Eiffel Tower and Louvre', 1, 'paris.jpg', 1);

INSERT INTO tour_destination (tour_id, destination_id) VALUES
(1, 1),   -- Colombo City Tour -> Colombo
(2, 1),   -- Kandy Cultural Tour -> Colombo (start)
(2, 2),   -- Kandy Cultural Tour -> Kandy (end)
(3, 4);   -- Paris Highlights -> Paris


SELECT 
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    tt.name AS tour_type,
    tc.name AS tour_category,
    t.duration_days,
    t.start_date,
    t.end_date,
    ds_start.name AS start_location,
    ds_end.name AS end_location,
    t.max_people,
    t.min_people,
    t.price_per_person,
    cs.name AS tour_status,
    ti.image_url AS tour_image,
    GROUP_CONCAT(td.destination_id) AS destination_ids,
    t.created_at,
    t.created_by,
    t.updated_at,
    t.updated_by,
    t.terminated_at,
    t.terminated_by
FROM tour t
INNER JOIN tour_type tt ON t.tour_type_id = tt.id
INNER JOIN tour_category tc ON t.tour_category_id = tc.id
INNER JOIN common_status cs ON t.tour_status_id = cs.id
INNER JOIN destination ds_start ON t.start_location_id = ds_start.id
INNER JOIN destination ds_end ON t.end_location_id = ds_end.id
LEFT JOIN tour_image ti ON t.tour_id = ti.tour_id
LEFT JOIN tour_destination td ON t.tour_id = td.tour_id
GROUP BY t.tour_id, ti.id
ORDER BY t.tour_id;

SELECT 
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    tt.name AS tour_type,
    tc.name AS tour_category,
    t.duration_days,
    t.start_date,
    t.end_date,
    ds_start.name AS start_location,
    ds_end.name AS end_location,
    t.max_people,
    t.min_people,
    t.price_per_person,
    cs.name AS tour_status,
    ti.id AS image_id,
    ti.name AS image_name,
    ti.image_url AS image_url,
    ti.description AS image_description,
    cs_img.name AS image_status,
    GROUP_CONCAT(DISTINCT td.destination_id) AS destination_ids, -- destination IDs as CSV
    t.created_at,
    t.created_by,
    t.updated_at,
    t.updated_by,
    t.terminated_at,
    t.terminated_by
FROM tour t
INNER JOIN tour_type tt ON t.tour_type_id = tt.id
INNER JOIN tour_category tc ON t.tour_category_id = tc.id
INNER JOIN common_status cs ON t.tour_status_id = cs.id
INNER JOIN destination ds_start ON t.start_location_id = ds_start.id
INNER JOIN destination ds_end ON t.end_location_id = ds_end.id
LEFT JOIN tour_image ti ON t.tour_id = ti.tour_id
LEFT JOIN common_status cs_img ON ti.status_id = cs_img.id
LEFT JOIN tour_destination td ON t.tour_id = td.tour_id
GROUP BY t.tour_id, ti.id
ORDER BY t.tour_id, ti.id;





















