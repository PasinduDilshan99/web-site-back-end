-- ==============================
-- PACKAGE TYPE TABLE
-- ==============================
CREATE TABLE package_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL,              -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_package_type_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- ==============================
-- PACKAGE TABLE
-- ==============================
CREATE TABLE package (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    tour_id INT NOT NULL,                -- FK -> tour
    name VARCHAR(150) NOT NULL,
    description TEXT,
    total_price DECIMAL(10,2),
    discount_percentage DECIMAL(5,2),
    start_date DATE,
    end_date DATE,
    color VARCHAR(20),
    hover_color VARCHAR(20),
    min_person_count INT,
    max_person_count INT,
    package_status_id INT NOT NULL,      -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_package_tour FOREIGN KEY (tour_id) REFERENCES tour(tour_id),
    CONSTRAINT fk_package_status FOREIGN KEY (package_status_id) REFERENCES common_status(id)
);

-- ==============================
-- PACKAGE IMAGE TABLE
-- ==============================
CREATE TABLE package_image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_id INT NOT NULL,             -- FK -> package
    image_url VARCHAR(500) NOT NULL,
    status_id INT NOT NULL,              -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_package_image_package FOREIGN KEY (package_id) REFERENCES package(package_id),
    CONSTRAINT fk_package_image_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);


-- ==============================
-- SAMPLE DATA FOR PACKAGE_TYPE
-- ==============================
INSERT INTO package_type (name, description, status_id, created_by)
VALUES
('Standard Package', 'Basic tour package with standard amenities', 1, 1),
('Premium Package', 'Includes luxury amenities and special experiences', 1, 1),
('Family Package', 'Designed for family groups with special discounts', 1, 1);

-- ==============================
-- SAMPLE DATA FOR PACKAGE
-- ==============================
INSERT INTO package (tour_id, name, description, total_price, discount_percentage, start_date, end_date, color, hover_color, min_person_count, max_person_count, package_status_id, created_by)
VALUES
(1, 'Standard Tour Package', 'Basic package for the tour', 500.00, 0, '2025-10-01', '2025-10-05', '#FF5733', '#FFBD69', 1, 10, 1, 1),
(1, 'Premium Tour Package', 'Luxury package with extra benefits', 1000.00, 10, '2025-10-01', '2025-10-05', '#33FF57', '#69FFBD', 1, 5, 1, 1),
(1, 'Family Tour Package', 'Package designed for families', 1500.00, 15, '2025-10-01', '2025-10-05', '#3357FF', '#69BDFF', 2, 6, 1, 1);

-- ==============================
-- SAMPLE DATA FOR PACKAGE_IMAGE
-- ==============================
INSERT INTO package_image (package_id, image_url, status_id, created_by)
VALUES
(1, 'https://example.com/images/package1_img1.jpg', 1, 1),
(1, 'https://example.com/images/package1_img2.jpg', 1, 1),
(2, 'https://example.com/images/package2_img1.jpg', 1, 1),
(3, 'https://example.com/images/package3_img1.jpg', 1, 1),
(3, 'https://example.com/images/package3_img2.jpg', 1, 1);



SELECT 
    p.package_id,
    p.name AS package_name,
    p.description AS package_description,
    p.total_price,
    p.discount_percentage,
    p.start_date,
    p.end_date,
    p.color,
    p.hover_color,
    p.min_person_count,
    p.max_person_count,
    ps.name AS package_status,
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    t.start_date AS tour_start_date,
    t.end_date AS tour_end_date,
    t.duration_days,
    t.max_people AS tour_max_people,
    t.min_people AS tour_min_people,
    t.price_per_person,
    ts.name AS tour_status,
    GROUP_CONCAT(pi.image_url) AS package_images
FROM package p
INNER JOIN tour t ON p.tour_id = t.tour_id
INNER JOIN common_status ps ON p.package_status_id = ps.id
INNER JOIN common_status ts ON t.tour_status_id = ts.id
LEFT JOIN package_image pi ON pi.package_id = p.package_id AND pi.status_id = 1
GROUP BY p.package_id;





SELECT 
    p.package_id,
    p.name AS package_name,
    p.description AS package_description,
    p.total_price,
    p.discount_percentage,
    p.start_date AS package_start_date,
    p.end_date AS package_end_date,
    p.color,
    p.hover_color,
    p.min_person_count,
    p.max_person_count,
    ps.name AS package_status,
    pt.id AS package_type_id,
    pt.name AS package_type_name,
    pt.description AS package_type_description,
    pt_status.name AS package_type_status,
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    t.start_date AS tour_start_date,
    t.end_date AS tour_end_date,
    t.duration_days,
    t.max_people AS tour_max_people,
    t.min_people AS tour_min_people,
    t.price_per_person,
    ts.name AS tour_status,
    pi.id AS image_id,
    pi.image_url,
    pi_status.name AS image_status
FROM package p
INNER JOIN tour t ON p.tour_id = t.tour_id
INNER JOIN common_status ps ON p.package_status_id = ps.id
INNER JOIN common_status ts ON t.tour_status_id = ts.id
LEFT JOIN package_type pt ON pt.id = p.package_id
LEFT JOIN common_status pt_status ON pt.status_id = pt_status.id
LEFT JOIN package_image pi ON pi.package_id = p.package_id
LEFT JOIN common_status pi_status ON pi.status_id = pi_status.id
ORDER BY p.package_id, pi.id;























