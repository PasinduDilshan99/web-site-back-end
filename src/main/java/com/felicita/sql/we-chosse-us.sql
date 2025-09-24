CREATE TABLE why_choose_us_status (
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

-- Sample status entries
INSERT INTO why_choose_us_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'Visible in the frontend', 1, 1),
('HIDDEN', 'Hidden in the frontend', 2, 1);


CREATE TABLE why_choose_us (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    title VARCHAR(100),
    sub_title VARCHAR(100),
    description TEXT,
    image_url VARCHAR(255),
    icon_url VARCHAR(255),
    clicked_url VARCHAR(255),
    color VARCHAR(20),
    why_choose_us_status_id INT NOT NULL,
    `order` INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (why_choose_us_status_id) REFERENCES why_choose_us_status(id)
);


INSERT INTO why_choose_us (
    name, title, sub_title, description, image_url, icon_url, clicked_url, color, why_choose_us_status_id, `order`, created_by
)
VALUES
('quality-service', 'Quality Service', 'Exceeding Expectations', 'We provide top-notch services that meet the highest standards.', '/images/why-choose-us/quality.jpg', '/icons/quality-icon.png', '/services', '#0073D2', 1, 1, 1),
('expert-team', 'Expert Team', 'Professionals You Can Trust', 'Our team consists of highly skilled professionals dedicated to your success.', '/images/why-choose-us/team.jpg', '/icons/team-icon.png', '/team', '#16A34A', 1, 2, 1),
('innovation', 'Innovation', 'Leading in Technology', 'We bring innovative solutions to solve complex challenges and improve efficiency.', '/images/why-choose-us/innovation.jpg', '/icons/innovation-icon.png', '/innovation', '#F59E0B', 1, 3, 1);


SELECT 
	wcs.id AS CARD_ID,
    wcs.name AS CARD_NAME,
    wcs.title AS CARD_TITLE,
    wcs.sub_title AS CARD_SUB_TITLE,
    wcs.description AS CARD_DESCRIPTION,
    wcs.image_url AS CARD_IMAGE_URL,
    wcs.icon_url AS CARD_ICON_URL,
    wcs.clicked_url AS CARD_CLICKED_URL,
    wcus.name as CARD_STATUS,
    cs.name AS CARD_STATUS_STATUS,
    wcs.color AS CARD_COLOR,
    wcs.order AS CARD_ORDER,
	wcs.created_at AS CARD_CREATED_AT,
    wcs.created_by AS CARD_CREATED_BY,
    wcs.updated_at AS CARD_UPDATED_AT,
    wcs.updated_by AS CARD_UPDATED_BY,
    wcs.terminated_at AS CARD_TERMINATED_AT,
    wcs.terminated_by AS CARD_TERMINATED_BY
FROM why_choose_us wcs
LEFT JOIN why_choose_us_status wcus
ON wcs.why_choose_us_status_id = wcus.id
LEFT JOIN common_status cs
ON wcus.common_status_id = cs.id;
