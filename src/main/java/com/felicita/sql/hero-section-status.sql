CREATE TABLE hero_section_status (
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
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

INSERT INTO hero_section_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'Slider is visible on homepage', 1, 1),
('HIDDEN', 'Slider is not visible', 2, 1);