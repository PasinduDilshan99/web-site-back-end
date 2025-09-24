-- 1. Activities Category Table
CREATE TABLE activities_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    color VARCHAR(20),
    hover_color VARCHAR(20),
    common_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_activities_cat_status FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- 2. Activities Table
CREATE TABLE activities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    activities_category_id INT NOT NULL,
    duration_hours INT,
    price DECIMAL(10,2),
    max_participants INT,
    min_participants INT,
    common_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_activities_destination FOREIGN KEY (destination_id) REFERENCES destination(id),
    CONSTRAINT fk_activities_category FOREIGN KEY (activities_category_id) REFERENCES activities_category(id),
    CONSTRAINT fk_activities_status FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- 3. Activities History Table
CREATE TABLE activities_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activities_id INT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    price DECIMAL(10,2),
    participants_count INT DEFAULT 0,
    guide VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_activities_history_activity FOREIGN KEY (activities_id) REFERENCES activities(id)
);

-- 4. Activities History Images Table
CREATE TABLE activities_history_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activities_history_id INT NOT NULL,
    name VARCHAR(150),
    description TEXT,
    image_url VARCHAR(255) NOT NULL,
    image_owner VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_activities_history_image FOREIGN KEY (activities_history_id) REFERENCES activities_history(id)
);


CREATE TABLE activities_requirements (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activities_id INT NOT NULL,
    requirement_type VARCHAR(100) NOT NULL, -- e.g., "Minimum Age", "Equipment", "Fitness Level"
    requirement_value VARCHAR(255) NOT NULL, -- e.g., "18", "Helmet", "Intermediate"
    description TEXT, -- optional detailed explanation
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_activities_requirement_activity FOREIGN KEY (activities_id) REFERENCES activities(id)
);


INSERT INTO activities_category (name, description, image_url, color, hover_color, common_status_id, created_by)
VALUES
('Adventure', 'High-adrenaline outdoor activities', '/images/categories/adventure.jpg', '#FF5733', '#FF7F50', 1, 1),
('Relaxation', 'Peaceful and calm activities', '/images/categories/relaxation.jpg', '#33FF57', '#7FFF50', 1, 1),
('Cultural', 'Cultural and historical tours', '/images/categories/cultural.jpg', '#3357FF', '#507FFF', 1, 1);


INSERT INTO activities (destination_id, name, description, activities_category_id, duration_hours, price, max_participants, min_participants, common_status_id, created_by)
VALUES
(1, 'Mountain Hiking', 'Hike the beautiful mountains', 1, 6, 50.00, 20, 2, 1, 1),
(1, 'River Rafting', 'Exciting river rafting experience', 1, 3, 70.00, 10, 1, 1, 1),
(2, 'Beach Yoga', 'Relaxing yoga sessions by the beach', 2, 2, 30.00, 15, 1, 1, 1),
(3, 'Historical City Tour', 'Explore the rich history of the city', 3, 4, 40.00, 25, 1, 1, 1);

INSERT INTO activities_requirements (activities_id, requirement_type, requirement_value, description)
VALUES
(1, 'Minimum Age', '12', 'Participants must be at least 12 years old'),
(1, 'Fitness Level', 'Intermediate', 'Requires moderate fitness'),
(2, 'Swimming Ability', 'Yes', 'Participants must know how to swim'),
(3, 'Yoga Mat', 'Required', 'Bring your own yoga mat'),
(4, 'Walking Shoes', 'Required', 'Comfortable shoes for walking');


INSERT INTO activities_history (activities_id, name, description, start_date, end_date, price, participants_count, guide)
VALUES
(1, 'Mountain Hiking - Morning Session', 'Morning hiking session in the mountains', '2025-12-01', '2025-12-01', 50.00, 5, 'John Doe'),
(1, 'Mountain Hiking - Afternoon Session', 'Afternoon hiking session', '2025-12-01', '2025-12-01', 50.00, 8, 'Jane Smith'),
(2, 'River Rafting Adventure', 'Thrilling rafting experience', '2025-12-05', '2025-12-05', 70.00, 4, 'Mike Johnson'),
(3, 'Beach Yoga Morning', 'Sunrise yoga session', '2025-12-10', '2025-12-10', 30.00, 10, 'Sara Lee');


INSERT INTO activities_history_images (activities_history_id, name, description, image_url, image_owner)
VALUES
(1, 'Mountain Peak', 'View from the mountain peak', '/images/activities/mountain_peak.jpg', 'John Doe'),
(1, 'Trail View', 'Scenic trail during hike', '/images/activities/trail_view.jpg', 'John Doe'),
(2, 'Afternoon Hike', 'Hikers on the mountain', '/images/activities/afternoon_hike.jpg', 'Jane Smith'),
(3, 'Rafting Fun', 'Group rafting', '/images/activities/rafting_fun.jpg', 'Mike Johnson'),
(4, 'Sunrise Yoga', 'Yoga by the beach', '/images/activities/sunrise_yoga.jpg', 'Sara Lee');



SELECT
    a.id AS activity_id,
    a.name AS activity_name,
    a.description AS activity_description,
    a.duration_hours,
    a.price AS activity_price,
    a.max_participants,
    a.min_participants,
    ac.id AS category_id,
    ac.name AS category_name,
    ac.description AS category_description,
    ac.image_url AS category_image,
    d.id AS destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location AS destination_location,
    d.rating AS destination_rating,
    cs.id AS status_id,
    cs.name AS status_name,
    cs.description AS status_description,
    ar.requirement_type,
    ar.requirement_value,
    ar.description AS requirement_description,
    ah.id AS history_id,
    ah.name AS history_name,
    ah.start_date,
    ah.end_date,
    ah.price AS history_price,
    ah.participants_count,
    ah.guide,
    ahi.id AS history_image_id,
    ahi.name AS history_image_name,
    ahi.image_url AS history_image_url,
    ahi.image_owner AS history_image_owner
FROM activities a
INNER JOIN activities_category ac ON a.activities_category_id = ac.id
INNER JOIN destination d ON a.destination_id = d.id
INNER JOIN common_status cs ON a.common_status_id = cs.id
LEFT JOIN activities_requirements ar ON a.id = ar.activities_id
LEFT JOIN activities_history ah ON a.id = ah.activities_id
LEFT JOIN activities_history_images ahi ON ah.id = ahi.activities_history_id
ORDER BY a.id, ah.start_date;

SELECT 
    ac.id,
    ac.name,
    ac.description,
    ac.image_url,
    ac.color,
    ac.hover_color,
    cs.name AS status_name,
    ac.created_at,
    ac.created_by,
    ac.updated_at,
    ac.updated_by
FROM activities_category ac
JOIN common_status cs ON ac.common_status_id = cs.id;

