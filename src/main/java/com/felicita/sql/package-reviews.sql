use travelagencydb;

CREATE TABLE package_review_comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_review_id INT NOT NULL,
    user_id INT NOT NULL,
    parent_comment_id INT NULL,               -- for replies (self join)
    comment TEXT NOT NULL,
    status INT NOT NULL,                      -- FK → common_status (e.g. active, hidden, deleted)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    
    FOREIGN KEY (package_review_id) REFERENCES package_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES package_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE package_review_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    package_review_id INT NOT NULL,
    user_id INT NOT NULL,
    reaction_type_id INT NOT NULL,          -- FK → reaction_type
    status INT NOT NULL,                    -- FK → common_status (active, removed)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    
    FOREIGN KEY (package_review_id) REFERENCES package_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
        
    UNIQUE (package_review_id, user_id, reaction_type_id)  -- prevent duplicate reactions
);


CREATE TABLE package_review_comment_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,
    user_id INT NOT NULL,
    reaction_type_id INT NOT NULL,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    
    FOREIGN KEY (comment_id) REFERENCES package_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
        
    UNIQUE (comment_id, user_id, reaction_type_id)  -- prevent duplicate reactions per comment
);

INSERT INTO package_review_comment 
(package_review_id, user_id, parent_comment_id, comment, status, created_by)
VALUES
-- Root comments on Review 1
(1, 2, NULL, 'Totally agree! The guide was super friendly.', 1, 2),
(1, 3, NULL, 'We had a similar experience last month, amazing value.', 1, 3),

-- Replies to comments under Review 1
(1, 1, 1, 'Glad to hear that! Our guide was Chaminda, he was great.', 1, 1),

-- Comments under Review 2
(2, 1, NULL, 'We stayed in the same hotel, and it was very clean.', 1, 1),
(2, 3, NULL, 'The food was indeed fantastic, especially the breakfast!', 1, 3),

-- Reply to comment under Review 2
(2, 2, 4, 'Yes! The buffet was delicious 😋', 1, 2);


INSERT INTO package_review_reaction 
(package_review_id, user_id, reaction_type_id, status, created_by)
VALUES
-- Review 1 reactions
(1, 1, 1, 1, 1),  -- User 1 liked Review 1
(1, 2, 2, 1, 2),  -- User 2 loved Review 1
(1, 3, 1, 1, 3),  -- User 3 liked Review 1

-- Review 2 reactions
(2, 1, 2, 1, 1),  -- User 1 loved Review 2
(2, 2, 1, 1, 2);  -- User 2 liked Review 2


INSERT INTO package_review_comment_reaction
(comment_id, user_id, reaction_type_id, status, created_by)
VALUES
-- Comment 1: User 2 commented on Review 1
(1, 1, 1, 1, 1),   -- User 1 liked it
(1, 3, 2, 1, 3),   -- User 3 loved it

-- Comment 3: Reply by User 1 under Comment 1
(3, 2, 1, 1, 2),   -- User 2 liked the reply

-- Comment 4: User 1 comment on Review 2
(4, 3, 1, 1, 3),   -- User 3 liked it

-- Comment 6: Reply by User 2 under Comment 4
(6, 1, 2, 1, 1);   -- User 1 loved it




