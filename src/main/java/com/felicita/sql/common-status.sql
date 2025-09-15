CREATE TABLE common_status (
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

INSERT INTO common_status (name, description, created_by)
VALUES
('ACTIVE', 'Currently active', 1),
('INACTIVE', 'Not active', 1),
('TERMINATED', 'Terminated or deleted', 1);

SELECT * FROM common_status;
