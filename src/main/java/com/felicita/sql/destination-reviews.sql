-- Table to store reviews for destinations
CREATE TABLE destination_review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    user_id INT NOT NULL,
    review_text TEXT,
    rating DECIMAL(3,2),  -- e.g., 4.5
    status INT NOT NULL,   -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Table to store images associated with a review, with name and description
CREATE TABLE destination_review_images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255) NOT NULL,
    status INT NOT NULL,  -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (review_id) REFERENCES destination_review(review_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


-- Table to store comments on reviews
CREATE TABLE destination_review_comment (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text TEXT NOT NULL,
    status INT NOT NULL,  -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (review_id) REFERENCES destination_review(review_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Table to store reactions on reviews
CREATE TABLE destination_review_reaction (
    review_reaction_id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    user_id INT NOT NULL,
    reaction_type_id INT NOT NULL,  -- FK -> reaction_type
    status INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (review_id) REFERENCES destination_review(review_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Table to store reactions on comments
CREATE TABLE destination_review_comment_reaction (
    comment_reaction_id INT PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,
    user_id INT NOT NULL,
    reaction_type_id INT NOT NULL,  -- FK -> reaction_type
    status INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    FOREIGN KEY (comment_id) REFERENCES destination_review_comment(comment_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

ALTER TABLE destination_review_comment
ADD COLUMN parent_comment_id INT NULL,
ADD FOREIGN KEY (parent_comment_id) REFERENCES destination_review_comment(comment_id)
    ON UPDATE CASCADE ON DELETE SET NULL;



-- ------------------------------
-- Sample Destination Reviews
-- ------------------------------
INSERT INTO destination_review (destination_id, user_id, review_text, rating, status, created_by)
VALUES
(1, 1, 'Amazing place to visit! Highly recommend.', 4.5, 1, 1),
(2, 2, 'Beautiful scenery but crowded on weekends.', 4.0, 1, 2),
(3, 1, 'A must-see attraction.', 5.0, 1, 1),
(4, 2, 'Good place for family trips.', 4.2, 1, 2),
(5, 1, 'Nice, but a bit pricey.', 3.8, 1, 1);

-- ------------------------------
-- Sample Destination Review Images
-- ------------------------------
INSERT INTO destination_review_images (review_id, name, description, image_url, status, created_by)
VALUES
(1, 'Sunset View', 'Beautiful sunset over the lake', 'https://example.com/images/review1_img1.jpg', 1, 1),
(1, 'Entrance', 'Main entrance of the destination', 'https://example.com/images/review1_img2.jpg', 1, 1),
(2, 'Scenic Spot', 'Panoramic view of the park', 'https://example.com/images/review2_img1.jpg', 1, 2),
(3, 'Fountain', 'Historic fountain in the center', 'https://example.com/images/review3_img1.jpg', 1, 1),
(4, 'Playground', 'Kids enjoying the playground', 'https://example.com/images/review4_img1.jpg', 1, 2);

-- ------------------------------
-- Sample Destination Review Comments
-- ------------------------------
INSERT INTO destination_review_comment (review_id, user_id, comment_text, status, created_by)
VALUES
(1, 2, 'Totally agree! It was amazing when I visited.', 1, 2),
(2, 1, 'Yes, weekends are always busy here.', 1, 1),
(3, 2, 'Thanks for the recommendation!', 1, 2),
(4, 1, 'Great tip for family trips.', 1, 1),
(5, 2, 'Good to know, I might wait for discounts.', 1, 2);

-- ------------------------------
-- Sample Destination Review Reactions
-- ------------------------------
INSERT INTO destination_review_reaction (review_id, user_id, reaction_type_id, status, created_by)
VALUES
(1, 2, 1, 1, 2),  -- User 2 likes review 1
(2, 1, 2, 1, 1),  -- User 1 loves review 2
(3, 2, 1, 1, 2),
(4, 1, 1, 1, 1),
(5, 2, 2, 1, 2);

-- ------------------------------
-- Sample Destination Review Comment Reactions
-- ------------------------------
INSERT INTO destination_review_comment_reaction (comment_id, user_id, reaction_type_id, status, created_by)
VALUES
(1, 1, 1, 1, 1),  -- User 1 likes comment 1
(2, 2, 2, 1, 2),  -- User 2 loves comment 2
(3, 1, 1, 1, 1),
(4, 2, 2, 1, 2),
(5, 1, 1, 1, 1);






















