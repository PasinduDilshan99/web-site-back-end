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

INSERT INTO nav_bar_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'Nav bar item is visible', 1, 1),   -- common_status_id 1 = ACTIVE
('HIDDEN', 'Nav bar item is hidden', 2, 1),     -- common_status_id 2 = INACTIVE
('REMOVED', 'Nav bar item removed', 3, 1);      -- common_status_id 3 = TERMINATED