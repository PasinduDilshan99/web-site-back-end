CREATE TABLE activity_review_comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_review_id INT NOT NULL,        -- FK to activities_review
    user_id INT NOT NULL,                   -- FK to user
    parent_comment_id INT NULL,             -- For threaded replies (self-reference)
    comment TEXT NOT NULL,
    status INT NOT NULL,                    -- FK to common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,

    FOREIGN KEY (activity_review_id) REFERENCES activities_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES activity_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE activity_review_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    activity_review_id INT NOT NULL,        -- FK to activities_review
    user_id INT NOT NULL,                   -- FK to user
    reaction_type_id INT NOT NULL,          -- FK to reaction_type
    status INT NOT NULL,                    -- FK to common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,

    FOREIGN KEY (activity_review_id) REFERENCES activities_review(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    UNIQUE KEY uq_review_user_reaction (activity_review_id, user_id)
);

CREATE TABLE activity_review_comment_reaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,                -- FK to activity_review_comment
    user_id INT NOT NULL,                   -- FK to user
    reaction_type_id INT NOT NULL,          -- FK to reaction_type
    status INT NOT NULL,                    -- FK to common_status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,

    FOREIGN KEY (comment_id) REFERENCES activity_review_comment(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reaction_type_id) REFERENCES reaction_type(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (status) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    UNIQUE KEY uq_comment_user_reaction (comment_id, user_id)
);

INSERT INTO activity_review_comment 
(activity_review_id, user_id, comment, status, created_by)
VALUES
-- Comments on review 1
(1, 1, 'Absolutely agree! The sunrise was breathtaking.', 1, 1),
(1, 2, 'Did you find the climb difficult? Planning to go next month.', 1, 2),

-- Nested reply to second comment
(1, 1, 'It was a bit challenging but totally worth it.', 1, 1),

-- Comments on review 2
(2, 2, 'Wow! Sounds amazing, which park was this?', 1, 2),
(2, 1, 'This is on my bucket list, thanks for sharing!', 1, 1);



INSERT INTO activity_review_reaction 
(activity_review_id, user_id, reaction_type_id, status, created_by)
VALUES
-- Reactions to review 1
(1, 1, 1, 1, 1),   -- User 1 likes review 1
(1, 2, 2, 1, 2),   -- User 2 loves review 1

-- Reactions to review 2
(2, 1, 1, 1, 1),   -- User 1 likes review 2
(2, 2, 3, 1, 2);   -- User 2 reacts WOW to review 2



INSERT INTO activity_review_comment_reaction
(comment_id, user_id, reaction_type_id, status, created_by)
VALUES
-- Reactions to comments on review 1
(1, 2, 1, 1, 2),   -- User 2 likes comment 1
(2, 1, 2, 1, 1),   -- User 1 loves comment 2
(3, 2, 1, 1, 2),   -- User 2 likes reply to comment 2

-- Reactions to comments on review 2
(4, 1, 3, 1, 1),   -- User 1 reacts WOW to comment 4
(5, 2, 1, 1, 2);   -- User 2 likes comment 5

