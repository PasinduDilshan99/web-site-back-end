use travelagencydb;

CREATE TABLE tour_review_comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_review_id INT NOT NULL,           -- FK -> tour_review
    parent_comment_id INT NULL,            -- for threaded replies
    user_id INT NOT NULL,                  -- FK -> user
    comment TEXT NOT NULL,
    status INT NOT NULL,                   -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,

    FOREIGN KEY (tour_review_id) REFERENCES tour_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES tour_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);



CREATE TABLE tour_review_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tour_review_id INT NOT NULL,           -- FK -> tour_review
    user_id INT NOT NULL,                  -- FK -> user
    reaction_type_id INT NOT NULL,         -- FK -> reaction_type
    status INT NOT NULL,                   -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,

    UNIQUE (tour_review_id, user_id, reaction_type_id),  -- prevent duplicates
    FOREIGN KEY (tour_review_id) REFERENCES tour_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE tour_review_comment_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,               -- FK -> tour_review_comment
    user_id INT NOT NULL,                  -- FK -> user
    reaction_type_id INT NOT NULL,         -- FK -> reaction_type
    status INT NOT NULL,                   -- FK -> common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,

    UNIQUE (comment_id, user_id, reaction_type_id),  -- prevent duplicate reactions
    FOREIGN KEY (comment_id) REFERENCES tour_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


INSERT INTO tour_review_comment 
(tour_review_id, parent_comment_id, user_id, comment, status, created_by)
VALUES
(1, NULL, 2, 'Absolutely agree! The view from the top was breathtaking.', 1, 2),
(1, NULL, 2, 'Was it too crowded when you went?', 1, 3),
(1, 2, 1, 'Not really, we went early morning — best time!', 1, 1), -- reply to comment #2
(2, NULL, 1, 'Ella is such a peaceful place. Loved the Nine Arches Bridge!', 1, 1),
(2, NULL, 1, 'Did you visit Little Adam’s Peak as well?', 1, 3);

INSERT INTO tour_review_reaction 
(tour_review_id, user_id, reaction_type_id, status, created_by)
VALUES
(1, 1, 1, 1, 1), -- user 1 liked review 1
(1, 2, 2, 1, 2), -- user 2 loved review 1
(2, 1, 1, 1, 1), -- user 1 liked review 2
(2, 2, 3, 1, 3);  -- user 3 reacted "WOW" to review 2



INSERT INTO tour_review_comment_reaction
(comment_id, user_id, reaction_type_id, status, created_by)
VALUES
(1, 1, 1, 1, 1), -- user 1 liked user 2’s comment
(2, 1, 3, 1, 1), -- user 1 reacted "WOW" to comment 2
(3, 2, 2, 1, 3), -- user 3 loved user 1’s reply
(4, 2, 1, 1, 2), -- user 2 liked user 1’s comment on review 2
(5, 1, 3, 1, 1); -- user 1 reacted "WOW" to user 3’s comment on review 2
