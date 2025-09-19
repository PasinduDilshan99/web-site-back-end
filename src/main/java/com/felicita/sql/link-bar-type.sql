CREATE TABLE link_bar_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT
);

INSERT INTO link_bar_type (name, description, created_by)
VALUES
('Header', 'Top navigation bar', 1),
('Sidebar', 'Side menu bar', 1),
('Footer', 'Bottom navigation bar', 1);