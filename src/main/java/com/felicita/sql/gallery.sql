-- Table: gallery_status
CREATE TABLE gallery_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT NOT NULL, -- FK to common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_gallery_status_common_status FOREIGN KEY (status_id)
        REFERENCES common_status(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Table: gallery
CREATE TABLE gallery (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INT NOT NULL, -- FK to gallery_status
    location VARCHAR(255),
    image_link VARCHAR(500),
    image_owner VARCHAR(255),
    image_source VARCHAR(255),
    image_source_link VARCHAR(500),
    color VARCHAR(50),
    hover_color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_gallery_status FOREIGN KEY (status_id)
        REFERENCES gallery_status(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);


-- Sample gallery_status (3 statuses)
INSERT INTO gallery_status (name, description, status_id, created_by)
VALUES
('Open', 'Gallery is open for visitors', 1, 1),
('Under Maintenance', 'Gallery temporarily closed', 2, 1),
('Closed', 'Gallery permanently closed', 3, 1);

-- Sample galleries (20 rows)
INSERT INTO gallery (name, description, status_id, location, image_link, image_owner, image_source, image_source_link, color, hover_color, created_by)
VALUES
('Art Expo 2025', 'Modern art exhibition', 1, 'Colombo', 'https://example.com/images/art1.jpg', 'John Doe', 'Photographer', 'https://photographer.com', '#FFFFFF', '#FF5733', 1),
('Historical Gallery', 'Artifacts from the 18th century', 2, 'Kandy', 'https://example.com/images/history1.jpg', 'Jane Smith', 'Museum', 'https://museum.com', '#F0F0F0', '#C70039', 1),
('Photography Showcase', 'Best of landscape photography', 1, 'Galle', 'https://example.com/images/photo1.jpg', 'Alice Brown', 'Photographer', 'https://alicephotos.com', '#FFFFFF', '#900C3F', 1),
('Sculpture Center', 'Contemporary sculptures', 1, 'Negombo', 'https://example.com/images/sculpt1.jpg', 'Bob Lee', 'Artist', 'https://boblee.com', '#FAFAFA', '#33FF57', 1),
('Digital Arts Hub', 'Digital and 3D art', 1, 'Colombo', 'https://example.com/images/digital1.jpg', 'Clara White', 'Digital Artist', 'https://clara.com', '#F5F5F5', '#3357FF', 1),
('Ancient Artifacts', 'Artifacts from ancient Sri Lanka', 1, 'Anuradhapura', 'https://example.com/images/artifact1.jpg', 'Museum', 'Museum', 'https://museum.lk', '#EDEDED', '#FF33A1', 1),
('Local Artists Expo', 'Showcase for local artists', 1, 'Matara', 'https://example.com/images/local1.jpg', 'John Doe', 'Gallery', 'https://localgallery.com', '#FFFFFF', '#33FFA5', 1),
('Modern Painting', 'Modern paintings collection', 1, 'Colombo', 'https://example.com/images/paint1.jpg', 'Sarah Connor', 'Painter', 'https://paintings.com', '#FFF5F5', '#FF5733', 1),
('Photography Contest', 'Annual photography contest', 2, 'Kandy', 'https://example.com/images/photo2.jpg', 'Alice Brown', 'Contest', 'https://contest.com', '#F0F0F0', '#FF33AA', 1),
('Sculpture Workshop', 'Hands-on sculpture workshop', 1, 'Galle', 'https://example.com/images/sculpt2.jpg', 'Bob Lee', 'Workshop', 'https://workshop.com', '#FAFAFA', '#33FFAA', 1),
('Heritage Gallery', 'Preserving heritage arts', 1, 'Jaffna', 'https://example.com/images/heritage1.jpg', 'Jane Smith', 'Museum', 'https://heritage.lk', '#FFFFFF', '#FFAA33', 1),
('Marine Life Exhibit', 'Underwater photography', 1, 'Trincomalee', 'https://example.com/images/marine1.jpg', 'Alice Brown', 'Photographer', 'https://alicephotos.com', '#E0FFFF', '#33CCFF', 1),
('Flora & Fauna', 'Wildlife photography gallery', 1, 'Haputale', 'https://example.com/images/wildlife1.jpg', 'John Doe', 'Photographer', 'https://wildlife.com', '#FFFFFF', '#33FFCC', 1),
('Street Art Expo', 'Urban street art', 1, 'Colombo', 'https://example.com/images/street1.jpg', 'Clara White', 'Artist', 'https://clara.com', '#F5F5F5', '#FF33FF', 1),
('Interactive Art', 'Interactive installations', 2, 'Kandy', 'https://example.com/images/interactive1.jpg', 'Bob Lee', 'Artist', 'https://boblee.com', '#FAFAFA', '#33AAFF', 1),
('Sustainable Art', 'Art using recycled materials', 1, 'Matara', 'https://example.com/images/sustainable1.jpg', 'Sarah Connor', 'Artist', 'https://art.com', '#FFFFFF', '#33FF33', 1),
('Cultural Artifacts', 'Artifacts from local culture', 1, 'Anuradhapura', 'https://example.com/images/cultural1.jpg', 'Jane Smith', 'Museum', 'https://museum.lk', '#F0F0F0', '#FF3333', 1),
('Experimental Arts', 'Avant-garde art collection', 1, 'Colombo', 'https://example.com/images/experimental1.jpg', 'Clara White', 'Artist', 'https://clara.com', '#FFFFFF', '#9933FF', 1),
('Art & Technology', 'Blend of art and tech', 1, 'Negombo', 'https://example.com/images/tech1.jpg', 'Bob Lee', 'Artist', 'https://boblee.com', '#FAFAFA', '#33FFFF', 1),
('Miniature Art', 'Miniature models and art', 1, 'Galle', 'https://example.com/images/mini1.jpg', 'Alice Brown', 'Artist', 'https://alicephotos.com', '#FFFFFF', '#FF6633', 1);




SELECT 
    g.id AS image_id,
    g.name AS image_name,
    g.description AS image_description,
    g.location,
    g.image_link,
    g.image_owner,
    g.image_source,
    g.image_source_link,
    g.color,
    g.hover_color,
    gs.name AS image_status,
    cs.name AS iamge_status_status,
    g.created_at,
    g.updated_at,
    g.terminated_at
FROM gallery g
JOIN gallery_status gs ON g.status_id = gs.id
JOIN common_status cs ON gs.status_id = cs.id
WHERE g.terminated_at IS NULL
ORDER BY g.id;









