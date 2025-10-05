use travelagencydb;

-- FOOTER TABLE
CREATE TABLE footer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    color VARCHAR(50),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_footer_status FOREIGN KEY (status) REFERENCES common_status(id)
);

-- FOOTER SUB ITEMS
CREATE TABLE footer_sub_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    footer_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    icon VARCHAR(255),
    link_url VARCHAR(255),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_footer_sub_footer FOREIGN KEY (footer_id) REFERENCES footer(id) ON DELETE CASCADE,
    CONSTRAINT fk_footer_sub_status FOREIGN KEY (status) REFERENCES common_status(id)
);

-- SOCIAL MEDIA
CREATE TABLE social_media (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    link VARCHAR(255) NOT NULL,
    icon_url VARCHAR(255),
    color VARCHAR(50),
    hover_color VARCHAR(50),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_social_status FOREIGN KEY (status) REFERENCES common_status(id)
);

-- FOOTER OTHERS
CREATE TABLE footer_others (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    link_url VARCHAR(255),
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_footer_others_status FOREIGN KEY (status) REFERENCES common_status(id)
);

-- FOOTER DATA
INSERT INTO footer (title, description, color, status, created_by)
VALUES
('Services', 'Our service-related links', '#333333', 1, 1),
('Quick Links', 'Useful navigation links', '#444444', 1, 1),
('Contacts', 'Get in touch with us', '#555555', 1, 1);

-- FOOTER SUB ITEMS

-- Services Sub Items
INSERT INTO footer_sub_items (footer_id, name, description, icon, link_url, status, created_by)
VALUES
(1, 'Home', 'Back to homepage', 'home', '/', 1, 1),
(1, 'Tour Packages', 'Explore our packages', 'suitcase', '/tour-packages', 1, 1),
(1, 'Destinations', 'Top destinations to visit', 'map-marker', '/destinations', 1, 1),
(1, 'Activities', 'Things to do', 'hiking', '/activities', 1, 1),
(1, 'About Us', 'Know more about us', 'info-circle', '/about', 1, 1),
(1, 'Blog', 'Travel stories and news', 'blog', '/blog', 1, 1),
(1, 'Contact', 'Reach out to us', 'phone', '/contact', 1, 1);

-- Quick Links Sub Items
INSERT INTO footer_sub_items (footer_id, name, description, icon, link_url, status, created_by)
VALUES
(2, 'Marvellous Sri Lanka', 'Special travel theme', 'star', '/marvellous-sri-lanka', 1, 1),
(2, 'Romantic Sri Lanka', 'Romantic getaway packages', 'heart', '/romantic-sri-lanka', 1, 1),
(2, 'Booking Terms & Conditions', 'Read our terms', 'file-contract', '/terms', 1, 1);

-- Contacts Sub Items
INSERT INTO footer_sub_items (footer_id, name, description, icon, link_url, status, created_by)
VALUES
(3, 'Colombo', 'Our main office location', 'map-marker', '#', 1, 1),
(3, '+94 11111111', 'Phone contact', 'phone', 'tel:+9411111111', 1, 1),
(3, 'felicita@example.com', 'Email contact', 'envelope', 'mailto:felicita@example.com', 1, 1);

-- SOCIAL MEDIA DATA
INSERT INTO social_media (name, description, link, icon_url, color, hover_color, status, created_by)
VALUES
('YouTube', 'Watch our travel videos', 'https://youtube.com/yourchannel', '/icons/youtube.png', '#FF0000', '#CC0000', 1, 1),
('Instagram', 'Check our Instagram', 'https://instagram.com/yourpage', '/icons/instagram.png', '#E1306C', '#C13584', 1, 1),
('Facebook', 'Follow us on Facebook', 'https://facebook.com/yourpage', '/icons/facebook.png', '#4267B2', '#365899', 1, 1),
('Twitter', 'Follow us on Twitter', 'https://twitter.com/yourpage', '/icons/twitter.png', '#1DA1F2', '#0d95e8', 1, 1),
('LinkedIn', 'Connect with us on LinkedIn', 'https://linkedin.com/company/yourpage', '/icons/linkedin.png', '#0077B5', '#005582', 1, 1);

INSERT INTO footer_others (name, description, link_url, status, created_by)
VALUES
('copyright', '© 2025 Felicita. All rights reserved.', '/faq', 1, 1),
('Privacy Policy', 'Privacy Policy', '/help', 1, 1),
('Terms of Service', 'Terms of Service', '/blog', 1, 1),
('Sitemap', 'Sitemap', '/careers', 1, 1);






-- Get footer sections with sub items
SELECT 
    f.id AS footer_id,
    f.title AS footer_title,
    f.description AS footer_description,
    f.color AS footer_color,
    cs1.name AS footer_status,
    s.id AS sub_item_id,
    s.name AS sub_item_name,
    s.description AS sub_item_description,
    s.icon AS sub_item_icon,
    s.link_url AS sub_item_url,
    cs2.name AS sub_item_status
FROM footer f
LEFT JOIN footer_sub_items s ON f.id = s.footer_id
LEFT JOIN common_status cs1
ON cs1.id = f.status 
LEFT JOIN common_status cs2
ON cs2.id = f.status; 

-- Get social media
SELECT 
    sm.id,
    sm.name,
    sm.description,
    sm.link,
    sm.icon_url,
    sm.color,
    sm.hover_color,
    cs.name
FROM social_media sm
LEFT JOIN common_status cs
ON cs.id = sm.status;
 
-- Get footer others
SELECT 
    fo.id, 
    fo.name, 
    fo.description, 
    fo.link_url,
    cs.name AS status_name
FROM footer_others fo
LEFT JOIN common_status cs
    ON cs.id = fo.status;













