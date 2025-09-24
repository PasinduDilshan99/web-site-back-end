use travelagencydb;

CREATE TABLE hero_section_status (
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
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

INSERT INTO hero_section_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'Slider is visible on homepage', 1, 1),
('HIDDEN', 'Slider is not visible', 2, 1);

CREATE TABLE hero_section (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    title VARCHAR(100),
    subtitle VARCHAR(100),
    description TEXT,
    primary_button_text VARCHAR(50),
    primary_button_link VARCHAR(255),
    secondary_button_text VARCHAR(50),
    secondary_button_link VARCHAR(255),
    hero_section_status_id INT NOT NULL,
    `order` INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (hero_section_status_id) REFERENCES hero_section_status(id)
);

INSERT INTO hero_section (
    name, image_url, title, subtitle, description, 
    primary_button_text, primary_button_link, 
    secondary_button_text, secondary_button_link, 
    hero_section_status_id, `order`, created_by
)
VALUES
('slider-1', '/images/hero-section-images/slider-1.jpg', 'Welcome to Our', 'Amazing World', 'Discover extraordinary experiences and create unforgettable memories with our premium services', 'Get Started', '/get-started', 'Learn More', '/learn-more', 1, 1, 1),
('slider-2', '/images/hero-section-images/slider-2.jpg', 'Explore New', 'Possibilities', 'Join thousands of satisfied customers who have transformed their lives with our innovative solutions', 'Start Now', '/start-now', 'View Demo', '/demo', 1, 2, 1),
('slider-3', '/images/hero-section-images/slider-3.jpg', 'Your Journey', 'Begins Here', 'Take the first step towards excellence with our comprehensive range of professional services', 'Join Us', '/join', 'Contact', '/contact', 1, 3, 1);


SELECT 
	hs.id AS IMAGE_ID,
    hs.name AS IMAGE_NAME,
    hs.image_url AS IMAGE_URL,
    hs.title AS IMAGE_TITLE,
    hs.subtitle AS IMAGE_SUB_TITLE,
    hs.description AS ImAGE_DESCRIPTION,
    hs.primary_button_text AS IMAGE_PRIMARY_BUTTON_TEXT,
    hs.primary_button_link AS IMAGE_PRIMARY_BUTTON_LINK,
    hs.secondary_button_text AS IMAGE_SECONDRY_BUTTON_TEXT,
    hs.secondary_button_link AS IMAGE_SECONDRY_BUTTON_LINK,
    hss.name AS IMAGE_STATUS,
    cs.name AS IMAGE_STATUS_STATUS,
    hs.order AS IMAGE_ORDER,
    hs.created_at AS IMAGE_CREATED_AT,
    hs.created_by AS IMAGE_CREATED_BY,
    hs.updated_at AS IMAGE_UPDATED_AT,
    hs.updated_by AS IMAGE_UPDATED_BY,
    hs.terminated_at AS IMAGE_TERMINATED_AT,
    hs.terminated_by AS IMAGE_TERMINATED_BY
FROM hero_section hs
LEFT JOIN hero_section_status hss
ON hs.hero_section_status_id = hss.id
lEFT JOIN common_status cs
ON hss.common_status_id = cs.id;
