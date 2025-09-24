-- Accommodation Status Table
CREATE TABLE accommodation_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    common_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_common_status
        FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Accommodation Table
CREATE TABLE accommodation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    sub_title VARCHAR(255),
    description TEXT,
    icon_url VARCHAR(255),
    color VARCHAR(20),
    hover_color VARCHAR(20),
    link_url VARCHAR(255),
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_accommodation_status
        FOREIGN KEY (status_id) REFERENCES accommodation_status(id)
);

-- Sample data for accommodation_status
INSERT INTO accommodation_status (name, description, common_status_id, created_by)
VALUES 
('AVAILABLE', 'Accommodation is available for booking', 1, 1),
('BOOKED', 'Accommodation is currently booked', 1, 1),
('UNDER_MAINTENANCE', 'Accommodation under maintenance', 2, 1),
('TERMINATED', 'Accommodation terminated', 3, 1);

-- Sample data for accommodation
INSERT INTO accommodation 
(title, sub_title, description, icon_url, color, hover_color, link_url, status_id, created_by)
VALUES
('Ocean View Villa', 'Luxury Stay', 'A beautiful villa with an ocean view.', '/icons/villa.png', '#ffffff', '#f0f0f0', '/accommodation/ocean-view-villa', 1, 1),
('Mountain Cabin', 'Cozy Cabin', 'A cozy cabin in the mountains.', '/icons/cabin.png', '#eeeeee', '#cccccc', '/accommodation/mountain-cabin', 1, 1),
('City Apartment', 'Central Location', 'Apartment in the heart of the city.', '/icons/apartment.png', '#dddddd', '#bbbbbb', '/accommodation/city-apartment', 2, 1),
('Lake House', 'Private Retreat', 'A peaceful house by the lake.', '/icons/lake-house.png', '#cccccc', '#aaaaaa', '/accommodation/lake-house', 3, 1);



SELECT 
	ac.id AS ACCOMMADATION_ID,
    ac.title AS ACCOMMADATION_TITLE,
    ac.sub_title AS ACCOMMADATION_SUB_TITLE,
    ac.description AS ACCOMMADATION_DESCRIPTION,
    ac.icon_url AS ACCOMMADATION_ICON_URL,
    ac.color AS ACCOMMADATION_COLOR,
    ac.hover_color AS ACCOMMADATION_HOVER_COLOR,
    ac.link_url AS ACCOMMADATION_LINK_URL,
    acs.name AS ACCOMMADATION_STATUS,
    cs.name AS ACCOMMADATION_STATUS_STATUS,
	ac.created_at AS ACCOMMADATION_CREATED_AT,
    ac.created_by AS ACCOMMADATION_CREATED_BY,
    ac.updated_at AS ACCOMMADATION_UPDATED_AT,
    ac.updated_by AS ACCOMMADATION_UPDATED_BY,
    ac.terminated_at AS ACCOMMADATION_TERMINATED_AT,
    ac.terminated_by AS ACCOMMADATION_TERMINATED_BY
FROM accommodation ac
LEFT JOIN accommodation_status acs
ON ac.status_id = acs.id
LEFT JOIN common_status cs
ON acs.common_status_id = cs.id;





















