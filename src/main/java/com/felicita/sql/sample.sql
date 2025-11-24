CREATE TABLE user_profile_sidebar (
                                      id INT PRIMARY KEY AUTO_INCREMENT,
                                      parent_id INT DEFAULT NULL,
                                      name VARCHAR(100) NOT NULL,
                                      description VARCHAR(255),
                                      privilege_id INT,
                                      status_id INT,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      created_by INT,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      updated_by INT,
                                      terminated_at TIMESTAMP,
                                      terminated_by INT,
                                      FOREIGN KEY (parent_id) REFERENCES user_profile_sidebar(id)
                                          ON UPDATE CASCADE ON DELETE CASCADE,
                                      FOREIGN KEY (privilege_id) REFERENCES privileges(id)
                                          ON UPDATE CASCADE ON DELETE RESTRICT,
                                      FOREIGN KEY (status_id) REFERENCES common_status(id)
                                          ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO user_profile_sidebar (name, description, privilege_id, status_id)
VALUES
    ('Profile', 'View and edit your profile', 1, 1),
    ('Reviews', 'Your reviews', 1, 1),
    ('Coupons & Offers', 'Your available coupons', 1, 1),
    ('Wallet', 'Your wallet details', 1, 1),
    ('Browsing History', 'View browsing history', 1, 1),
    ('Account Security', 'Security settings', 2, 1),
    ('Notifications', 'Manage notifications', 1, 1),
    ('Wish List', 'Your saved items', 1, 1);

-- Step 1: Get the parent id for 'Reviews'
SET @parentId := (SELECT id FROM user_profile_sidebar WHERE name = 'Reviews');

-- Step 2: Insert sub-items using the variable
INSERT INTO user_profile_sidebar (parent_id, name, description, privilege_id, status_id)
VALUES
    (@parentId, 'Package Reviews', 'Your package reviews', 1, 1),
    (@parentId, 'Tour Reviews', 'Your tour reviews', 1, 1),
    (@parentId, 'Activity Reviews', 'Your activity reviews', 1, 1),
    (@parentId, 'Destination Reviews', 'Your destination reviews', 1, 1);