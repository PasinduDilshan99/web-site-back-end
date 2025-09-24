USE TravelAgencyDB;

CREATE TABLE nav_bar_status (
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
    CONSTRAINT fk_navbarstatus_commonstatus FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

CREATE TABLE nav_bar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL, -- FK to nav_bar_status
    link_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_navbar_navbarstatus FOREIGN KEY (status_id) REFERENCES nav_bar_status(id)
);

INSERT INTO nav_bar_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'Nav bar item is visible', 1, 1),   -- common_status_id 1 = ACTIVE
('HIDDEN', 'Nav bar item is hidden', 2, 1),     -- common_status_id 2 = INACTIVE
('REMOVED', 'Nav bar item removed', 3, 1);      -- common_status_id 3 = TERMINATED


-- Insert NAV BAR items
INSERT INTO nav_bar (name, description, status_id, link_url, created_by)
VALUES
('Home', 'Homepage link', 1, '/home', 1),              -- VISIBLE
('About Us', 'About us page link', 1, '/about-us', 1), -- VISIBLE
('Destination', 'Destinations page link', 1, '/destination', 1), 
('Blogs', 'Travel blogs and articles', 1, '/blogs', 1),
('Contact Us', 'Contact information and form', 1, '/contact-us', 1),
('FAQ', 'Frequently Asked Questions', 1, '/faq', 1),
('Sri Lankan Tours', 'Tour packages in Sri Lanka', 1, '/sri-lankan-tours', 1),
('Reviews', 'Customer reviews and testimonials', 1, '/reviews', 1),
('Offers', 'Special travel offers and discounts', 1, '/offers', 1),
('Packages', 'Available travel packages', 1, '/packages', 1),
('Gallery', 'Photo and video gallery', 1, '/gallery', 1),
('Plan Your Trip', 'Trip planning section', 1, '/plan-your-trip', 1),
('Activities', 'Things to do and activities', 1, '/activities', 1);


SELECT 
	nb.name AS NAME,
    nb.description AS DESCRIPTION,
    nbs.name AS STATUS,
    cs.name AS NAV_BAR_STATUS_STATUS,
    nb.link_url AS LINK_URL,
    nb.created_at AS CREATED_AT,
    nb.created_by AS CREATED_BY,
    nb.updated_at AS UPDATED_AT,
    nb.updated_by AS UPDATED_BY,
    nb.terminated_at AS TERMINATED_AT,
    nb.terminated_by AS TERMINATED_BY
FROM nav_bar nb
LEFT JOIN nav_bar_status nbs
ON nb.status_id = nbs.id
LEFT JOIN common_status cs
ON nbs.common_status_id = cs.id;

