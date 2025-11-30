-- Package Wish List
CREATE TABLE package_wishlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES packages(package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Package Wish List History
CREATE TABLE package_wishlist_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    wishlist_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES packages(package_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (wishlist_id) REFERENCES package_wishlist(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tour Wish List
CREATE TABLE tour_wishlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    tour_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tour Wish List History
CREATE TABLE tour_wishlist_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    tour_id INT NOT NULL,
    wishlist_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (wishlist_id) REFERENCES tour_wishlist(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Destination Wish List
CREATE TABLE destination_wishlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    destination_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Destination Wish List History
CREATE TABLE destination_wishlist_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    destination_id INT NOT NULL,
    wishlist_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destination(destination_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (wishlist_id) REFERENCES destination_wishlist(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Activities Wish List
CREATE TABLE activity_wishlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    activity_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activities(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Activities Wish List History
CREATE TABLE activity_wishlist_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    activity_id INT NOT NULL,
    wishlist_id INT NOT NULL,
    status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activities(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (wishlist_id) REFERENCES activity_wishlist(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Sample data for Package Wishlist
INSERT INTO package_wishlist (user_id, package_id, status_id) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(2, 4, 1),
(2, 5, 1),
(2, 6, 2);

-- Sample data for Package Wishlist History
INSERT INTO package_wishlist_history (user_id, package_id, wishlist_id, status_id) VALUES
(1, 1, 1, 1),
(1, 2, 2, 2),
(2, 4, 4, 1);

-- Sample data for Tour Wishlist
INSERT INTO tour_wishlist (user_id, tour_id, status_id) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 2),
(2, 4, 1);

-- Sample data for Tour Wishlist History
INSERT INTO tour_wishlist_history (user_id, tour_id, wishlist_id, status_id) VALUES
(1, 1, 1, 1),
(2, 3, 3, 2);

-- Sample data for Destination Wishlist
INSERT INTO destination_wishlist (user_id, destination_id, status_id) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 1),
(2, 4, 2);

-- Sample data for Destination Wishlist History
INSERT INTO destination_wishlist_history (user_id, destination_id, wishlist_id, status_id) VALUES
(1, 1, 1, 1),
(2, 4, 4, 2);

-- Sample data for Activity Wishlist
INSERT INTO activity_wishlist (user_id, activity_id, status_id) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 2),
(2, 4, 1);

-- Sample data for Activity Wishlist History
INSERT INTO activity_wishlist_history (user_id, activity_id, wishlist_id, status_id) VALUES
(1, 1, 1, 1),
(2, 3, 3, 2);

