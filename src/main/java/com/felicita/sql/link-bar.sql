CREATE TABLE link_bar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    link_bar_type_id INT NOT NULL,            
    icon_url VARCHAR(100),
    link_url VARCHAR(255),
    link_bar_status_id INT NOT NULL,          
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (link_bar_type_id) REFERENCES link_bar_type(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    FOREIGN KEY (link_bar_status_id) REFERENCES link_bar_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO link_bar (name, description, link_bar_type_id, icon_url, link_url, link_bar_status_id, created_by)
VALUES
('Home', 'Home page link', 1, '/icons/home.png', '/home', 1, 1),
('Dashboard', 'Admin dashboard link', 2, '/icons/dashboard.png', '/dashboard', 1, 1),
('Settings', 'Settings link', 2, '/icons/settings.png', '/settings', 2, 1),
('Contact Us', 'Footer contact link', 3, '/icons/contact.png', '/contact', 1, 1);



SELECT 
    lb.name AS NAME,
    lb.description AS DESCRIPTION,
    lbt.name AS TYPE_NAME,
    cs1.name AS TYPE_STATUS,
    lb.icon_url AS ICON_URL,
    lb.link_url AS LINK_URL,
    lbs.name AS ITEM_STATUS,
    cs2.name AS ITEM_STATUS_STATUS,
    lb.created_at AS CREATED_AT,
    lb.created_by AS CREATED_BY,
    lb.updated_at AS UPDATED_AT,
    lb.updated_by AS UPDATED_BY,
    lb.terminated_at AS TERMINATED_AT,
    lb.terminated_by AS TERMINATED_BY
FROM link_bar lb
LEFT JOIN link_bar_status lbs 
    ON lb.link_bar_status_id = lbs.id
LEFT JOIN link_bar_type lbt 
    ON lb.link_bar_type_id = lbt.id
LEFT JOIN common_status cs1 
    ON lbt.common_status_id = cs1.id
LEFT JOIN common_status cs2 
    ON lbs.common_status_id = cs2.id;

















