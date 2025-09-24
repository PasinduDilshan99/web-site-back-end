use travelagencydb;

CREATE TABLE review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    tour_id INT NULL,
    package_id INT NULL,
    rating TINYINT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    status_id INT NOT NULL, -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_tour FOREIGN KEY (tour_id) REFERENCES tour(tour_id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_review_package FOREIGN KEY (package_id) REFERENCES package(package_id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_review_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE review_image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    status_id INT NOT NULL, -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,

    CONSTRAINT fk_review_image_review FOREIGN KEY (review_id) REFERENCES review(review_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_image_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE reaction_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,         -- e.g., LIKE, LOVE, WOW
    description VARCHAR(255),
    icon VARCHAR(255),
    status_id INT NOT NULL,            -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,

    CONSTRAINT fk_reaction_type_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE review_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    user_id INT NOT NULL,
    reaction_type_id INT NOT NULL,
    status_id INT NOT NULL, -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_review_reaction_review FOREIGN KEY (review_id) REFERENCES review(review_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_reaction_user FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_reaction_type FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_review_reaction_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    UNIQUE (review_id, user_id, reaction_type_id) -- prevent duplicate same reaction
);


CREATE TABLE review_comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    user_id INT NOT NULL,
    parent_id INT NULL,
    comment_text TEXT NOT NULL,
    status_id INT NOT NULL, -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_review_comment_review FOREIGN KEY (review_id) REFERENCES review(review_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_comment_user FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_comment_parent FOREIGN KEY (parent_id) REFERENCES review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_review_comment_status FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);



-- Sample review for a tour
INSERT INTO review (user_id, tour_id, package_id, rating, comment, status_id)
VALUES 
(1, 1, NULL, 5, 'Amazing tour! Highly recommend.', 1),
(2, 1, NULL, 4, 'Great experience but could improve food.', 1),
(3, NULL, 2, 5, 'Loved the package, very well organized.', 1);

INSERT INTO review_image (review_id, image_url, status_id, created_by)
VALUES
(1, 'https://example.com/images/review1_1.jpg', 1, 1),
(1, 'https://example.com/images/review1_2.jpg', 1, 1),
(3, 'https://example.com/images/review3_1.jpg', 1, 3);

INSERT INTO reaction_type (name, description, icon, status_id, created_by)
VALUES
('LIKE', 'Thumbs up', '👍', 1, 1),
('LOVE', 'Love it', '❤️', 1, 1),
('WOW', 'Surprised', '😮', 1, 1),
('HELPFUL', 'Helpful review', '💡', 1, 1);



INSERT INTO review_reaction (review_id, user_id, reaction_type_id, status_id)
VALUES
(1, 2, 1, 1), -- user 2 liked review 1
(1, 3, 2, 1), -- user 3 loved review 1
(2, 1, 4, 1), -- user 1 marked review 2 as helpful
(3, 2, 1, 1); -- user 2 liked review 3


-- Parent comments
INSERT INTO review_comment (review_id, user_id, parent_id, comment_text, status_id)
VALUES
(1, 2, NULL, 'Totally agree! Best tour ever!', 1),
(1, 3, NULL, 'Nice review, planning to join next month.', 1),
(3, 1, NULL, 'Thanks for the info!', 1);

-- Replies to comments
INSERT INTO review_comment (review_id, user_id, parent_id, comment_text, status_id)
VALUES
(1, 1, 1, 'Glad you enjoyed it too!', 1),  -- reply to comment_id 1
(1, 1, 2, 'Looking forward to seeing you there!', 1); -- reply to comment_id 2




SELECT
    r.review_id,
    r.rating,
    r.comment AS review_description,
    r.created_at AS review_created_at,
    r.updated_at AS review_updated_at,
    cs.name AS review_status,
    u.user_id,
    u.username,
    u.first_name,
    u.last_name,
    u.image_url AS user_avatar,
    t.tour_id,
    t.name AS tour_name,
    p.package_id,
    p.name AS package_name,
    ri.id AS image_id,
    ri.image_url,
    rr.id AS reaction_id,
    rt.name AS reaction_type,
    ru.user_id AS reacted_by_user_id,
    ru.username AS reacted_by_username,
    rc.id AS comment_id,
    rc.comment_text,
    rc.parent_id AS comment_parent_id,
    cu.user_id AS comment_user_id,
    cu.username AS comment_user_name,
    rc.created_at AS comment_created_at
FROM review r
LEFT JOIN common_status cs ON r.status_id = cs.id
LEFT JOIN user u ON r.user_id = u.user_id
LEFT JOIN tour t ON r.tour_id = t.tour_id
LEFT JOIN package p ON r.package_id = p.package_id
LEFT JOIN review_image ri ON ri.review_id = r.review_id AND ri.status_id = 1
LEFT JOIN review_reaction rr ON rr.review_id = r.review_id AND rr.status_id = 1
LEFT JOIN reaction_type rt ON rr.reaction_type_id = rt.id
LEFT JOIN user ru ON rr.user_id = ru.user_id
LEFT JOIN review_comment rc ON rc.review_id = r.review_id AND rc.status_id = 1
LEFT JOIN user cu ON rc.user_id = cu.user_id
ORDER BY r.review_id, ri.id, rr.id, rc.id;









