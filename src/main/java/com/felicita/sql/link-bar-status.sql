CREATE TABLE link_bar_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    common_status_id INT NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO link_bar_status (name, common_status_id, description, created_by)
VALUES
('Visible', 1, 'Link bar is visible', 1),
('Hidden', 2, 'Link bar is hidden', 1),
('Archived', 3, 'Link bar is archived', 1);