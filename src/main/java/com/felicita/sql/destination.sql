use travelagencydb;

CREATE TABLE destination_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
	image_url VARCHAR(500) NOT NULL,
    common_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_dest_category_status FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Sample Data
INSERT INTO destination_category (name, description, common_status_id, created_by)
VALUES
('Beach', 'Beach destinations with coastal beauty', 1, 1),
('Mountain', 'Mountain and hiking destinations', 1, 1),
('City', 'Urban cities and cultural experiences', 1, 1);

CREATE TABLE destination (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    common_status_id INT NOT NULL,
    destination_category_id INT NOT NULL,
    location VARCHAR(255),
    rating DECIMAL(2,1) CHECK (rating >= 0 AND rating <= 5),
    popularity INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_dest_status FOREIGN KEY (common_status_id) REFERENCES common_status(id),
    CONSTRAINT fk_dest_category FOREIGN KEY (destination_category_id) REFERENCES destination_category(id)
);

-- Sample Data
INSERT INTO destination (name, description, common_status_id, destination_category_id, location, rating, popularity, created_by)
VALUES
('Mirissa Beach', 'Famous for whale watching and golden sands', 1, 1, 'Sri Lanka', 4.7, 120, 1),
('Ella Rock', 'Beautiful hiking trail with panoramic views', 1, 2, 'Sri Lanka', 4.8, 95, 1),
('Colombo City', 'Capital city with cultural and modern lifestyle', 1, 3, 'Sri Lanka', 4.3, 200, 1);

CREATE TABLE destination_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150),
    description VARCHAR(255),
    image_url VARCHAR(500) NOT NULL,
    destination_id INT NOT NULL,
    common_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_dest_img FOREIGN KEY (destination_id) REFERENCES destination(id),
    CONSTRAINT fk_dest_img_status FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Sample Data
INSERT INTO destination_images (name, description, image_url, destination_id, common_status_id, created_by)
VALUES
('Mirissa Sunset', 'Sunset view at Mirissa Beach', 'https://example.com/images/mirissa-sunset.jpg', 1, 1, 1),
('Ella Rock View', 'Scenic view from Ella Rock', 'https://example.com/images/ella-rock.jpg', 2, 1, 1),
('Colombo Skyline', 'Skyline view of Colombo at night', 'https://example.com/images/colombo-skyline.jpg', 3, 1, 1);

INSERT INTO destination_images (name, description, image_url, destination_id, common_status_id, created_by)
VALUES
('Mirissa Sunset', 'Sunset view at Mirissa Beach', 'https://example.com/images/mirissa-sunset.jpg', 1, 1, 1),
('Ella Rock View', 'Scenic view from Ella Rock', 'https://example.com/images/ella-rock.jpg', 1, 1, 1),
('Colombo Skyline', 'Skyline view of Colombo at night', 'https://example.com/images/colombo-skyline.jpg', 1, 1, 1);


CREATE TABLE trending_destinations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,

    -- Foreign keys
    CONSTRAINT fk_trending_destination FOREIGN KEY (destination_id) REFERENCES destination(id),
    CONSTRAINT fk_trending_status FOREIGN KEY (status_id) REFERENCES common_status(id)
);

-- Insert trending destinations
INSERT INTO trending_destinations (destination_id, status_id, created_by)
VALUES
(1, 1, 1), -- Mirissa Beach, ACTIVE
(2, 1, 1), -- Ella Rock, ACTIVE
(3, 2, 1); -- Colombo City, INACTIVE



SELECT
	d.id AS DESTINATION_ID,
    d.name AS DESTINATION_NAME,
    d.description AS DESTINATION_DESCRIPTION,
    cs2.name AS DESTINATION_STATUS,
    dc.name AS DESTINATION_CATEGORY,
    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
    cs1.name AS DESTINATION_CATEGORY_STATUS,
    d.location AS DESTINATION_LOCATION,
    d.rating AS DESTINATION_RATING,
    d.popularity AS DESTINATION_POPULARITY,
	d.created_at AS DESTINATION_CREATED_AT,
    d.created_by AS DESTINATION_CREATED_BY,
    d.updated_at AS DESTINATION_UPDATED_AT,
    d.updated_by AS DESTINATION_UPDATED_BY,
    d.terminated_at AS DESTINATION_TERMINATED_AT,
    d.terminated_by AS DESTINATION_TERMINATED_BY
FROM destination d
LEFT JOIN destination_category dc
ON d.destination_category_id = dc.id
LEFT JOIN destination_images di
ON d.id = di.destination_id
LEFT JOIN common_status cs1
ON dc.common_status_id = cs1.id
LEFT JOIN common_status cs2
ON d.common_status_id = cs2.id
LEFT JOIN common_status cs3
ON di.common_status_id = cs3.id;


SELECT
    d.id AS DESTINATION_ID,
    d.name AS DESTINATION_NAME,
    d.description AS DESTINATION_DESCRIPTION,
    cs2.name AS DESTINATION_STATUS,
    dc.name AS DESTINATION_CATEGORY,
    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
    cs1.name AS DESTINATION_CATEGORY_STATUS,
    d.location AS DESTINATION_LOCATION,
    d.rating AS DESTINATION_RATING,
    d.popularity AS DESTINATION_POPULARITY,
    d.created_at AS DESTINATION_CREATED_AT,
    d.created_by AS DESTINATION_CREATED_BY,
    d.updated_at AS DESTINATION_UPDATED_AT,
    d.updated_by AS DESTINATION_UPDATED_BY,
    d.terminated_at AS DESTINATION_TERMINATED_AT,
    d.terminated_by AS DESTINATION_TERMINATED_BY,
    di.id AS IMAGE_ID,
    di.name AS IMAGE_NAME,
    di.description AS IMAGE_DESCRIPTION,
    di.image_url AS IMAGE_URL,
    cs3.name AS IMAGE_STATUS
FROM destination d
LEFT JOIN destination_category dc
    ON d.destination_category_id = dc.id
LEFT JOIN destination_images di
    ON d.id = di.destination_id
LEFT JOIN common_status cs1
    ON dc.common_status_id = cs1.id
LEFT JOIN common_status cs2
    ON d.common_status_id = cs2.id
LEFT JOIN common_status cs3
    ON di.common_status_id = cs3.id;


SELECT 
    dc.id AS CATEGORY_ID,
    dc.name AS CATEGORY_NAME,
    dc.description AS CATEGORY_DESCRIPTION,
    cs.name AS CATEGORY_STATUS,
    dc.created_at AS CATEGORY_CREATED_AT,
    dc.created_by AS CATEGORY_CREATED_BY,
    dc.updated_at AS CATEGORY_UPDATED_AT,
    dc.updated_by AS CATEGORY_UPDATED_BY,
    dc.terminated_at AS CATEGORY_TERMINATED_AT,
    dc.terminated_by AS CATEGORY_TERMINATED_BY
FROM destination_category dc
LEFT JOIN common_status cs
    ON dc.common_status_id = cs.id;


SELECT
    d.id AS DESTINATION_ID,
    d.name AS DESTINATION_NAME,
    d.description AS DESTINATION_DESCRIPTION,
    cs2.name AS DESTINATION_STATUS,
    dc.name AS DESTINATION_CATEGORY,
    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
    cs1.name AS DESTINATION_CATEGORY_STATUS,
    d.location AS DESTINATION_LOCATION,
    d.rating AS DESTINATION_RATING,
    d.popularity AS DESTINATION_POPULARITY,
    d.created_at AS DESTINATION_CREATED_AT,
    d.created_by AS DESTINATION_CREATED_BY,
    d.updated_at AS DESTINATION_UPDATED_AT,
    d.updated_by AS DESTINATION_UPDATED_BY,
    d.terminated_at AS DESTINATION_TERMINATED_AT,
    d.terminated_by AS DESTINATION_TERMINATED_BY,
    di.id AS IMAGE_ID,
    di.name AS IMAGE_NAME,
    di.description AS IMAGE_DESCRIPTION,
    di.image_url AS IMAGE_URL,
    cs3.name AS IMAGE_STATUS
FROM destination d
LEFT JOIN destination_category dc
    ON d.destination_category_id = dc.id
LEFT JOIN destination_images di
    ON d.id = di.destination_id
LEFT JOIN common_status cs1
    ON dc.common_status_id = cs1.id
LEFT JOIN common_status cs2
    ON d.common_status_id = cs2.id
LEFT JOIN common_status cs3
    ON di.common_status_id = cs3.id
WHERE dc.id = 1;


SELECT
    d.id AS DESTINATION_ID,
    d.name AS DESTINATION_NAME,
    d.description AS DESTINATION_DESCRIPTION,
    cs2.name AS DESTINATION_STATUS,
    dc.name AS DESTINATION_CATEGORY,
    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
    cs1.name AS DESTINATION_CATEGORY_STATUS,
    d.location AS DESTINATION_LOCATION,
    d.rating AS DESTINATION_RATING,
    d.popularity AS DESTINATION_POPULARITY,
    d.created_at AS DESTINATION_CREATED_AT,
    d.created_by AS DESTINATION_CREATED_BY,
    d.updated_at AS DESTINATION_UPDATED_AT,
    d.updated_by AS DESTINATION_UPDATED_BY,
    d.terminated_at AS DESTINATION_TERMINATED_AT,
    d.terminated_by AS DESTINATION_TERMINATED_BY,
    di.id AS IMAGE_ID,
    di.name AS IMAGE_NAME,
    di.description AS IMAGE_DESCRIPTION,
    di.image_url AS IMAGE_URL,
    cs3.name AS IMAGE_STATUS
FROM destination d
LEFT JOIN destination_category dc
    ON d.destination_category_id = dc.id
LEFT JOIN destination_images di
    ON d.id = di.destination_id
LEFT JOIN common_status cs1
    ON dc.common_status_id = cs1.id
LEFT JOIN common_status cs2
    ON d.common_status_id = cs2.id
LEFT JOIN common_status cs3
    ON di.common_status_id = cs3.id
WHERE d.id = 1;

SELECT
    d.id AS DESTINATION_ID,
    d.name AS DESTINATION_NAME,
    d.description AS DESTINATION_DESCRIPTION,
    cs2.name AS DESTINATION_STATUS,
    dc.name AS DESTINATION_CATEGORY,
    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
    cs1.name AS DESTINATION_CATEGORY_STATUS,
    d.location AS DESTINATION_LOCATION,
    d.rating AS DESTINATION_RATING,
    d.popularity AS DESTINATION_POPULARITY,
    d.created_at AS DESTINATION_CREATED_AT,
    d.created_by AS DESTINATION_CREATED_BY,
    d.updated_at AS DESTINATION_UPDATED_AT,
    d.updated_by AS DESTINATION_UPDATED_BY,
    d.terminated_at AS DESTINATION_TERMINATED_AT,
    d.terminated_by AS DESTINATION_TERMINATED_BY,
    di.id AS IMAGE_ID,
    di.name AS IMAGE_NAME,
    di.description AS IMAGE_DESCRIPTION,
    di.image_url AS IMAGE_URL,
    cs3.name AS IMAGE_STATUS,
    td.id AS TRENDING_ID,
    td.created_at AS TRENDING_CREATED_AT,
    td.created_by AS TRENDING_CREATED_BY,
    td.updated_at AS TRENDING_UPDATED_AT,
    td.updated_by AS TRENDING_UPDATED_BY,
    td.terminated_at AS TRENDING_TERMINATED_AT,
    td.terminated_by AS TRENDING_TERMINATED_BY,
    cs4.name AS TRENDING_STATUS
FROM trending_destinations td
INNER JOIN destination d
    ON td.destination_id = d.id
LEFT JOIN destination_category dc
    ON d.destination_category_id = dc.id
LEFT JOIN destination_images di
    ON d.id = di.destination_id
LEFT JOIN common_status cs1
    ON dc.common_status_id = cs1.id
LEFT JOIN common_status cs2
    ON d.common_status_id = cs2.id
LEFT JOIN common_status cs3
    ON di.common_status_id = cs3.id
LEFT JOIN common_status cs4
    ON td.status_id = cs4.id;












