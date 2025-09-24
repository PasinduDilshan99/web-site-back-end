-- Our Services Table
CREATE TABLE our_service_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    common_status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_common_status_service FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert into our_service_status
INSERT INTO our_service_status (name, description, common_status_id, created_by)
VALUES 
('ACTIVE', 'Service is available', 1,1),
('INACTIVE', 'Service is not available', 2,1),
('TERMINATED', 'Service discontinued', 1,1);

CREATE TABLE our_services (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    sub_title VARCHAR(255),
    description TEXT,
    image_url VARCHAR(500),
    color VARCHAR(50),
    our_service_status_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    CONSTRAINT fk_our_service_status FOREIGN KEY (our_service_status_id) REFERENCES our_service_status(id)
);

-- Our Service Status Table


-- Insert into our_services
INSERT INTO our_services (title, sub_title, description, image_url, color, our_service_status_id, created_by)
VALUES
('Web Development', 'Modern & Scalable', 'We build scalable and modern web applications tailored to your business needs.', '/images/our-services-images/service-1.png', '#0073D2', 1, 1),
('Mobile Apps', 'iOS & Android', 'Our team develops user-friendly mobile applications for both iOS and Android platforms.', '/images/our-services-images/service-2.png', '#16A34A', 1, 1),
('Cloud Solutions', 'Secure & Reliable', 'We provide secure and reliable cloud hosting, migration, and management services.', '/images/our-services-images/service-3.png', '#F59E0B', 1, 1),
('UI/UX Design', 'Beautiful & Functional', 'Our designers craft intuitive and engaging UI/UX designs for web and mobile.', '/images/our-services-images/service-4.png', '#DC2626', 2, 1);


SELECT 
	os.id AS SERVICE_ID,
    os.title AS SERVICE_TITLE,
    os.sub_title AS SERVICE_SUB_TITLE,
    os.description AS SERVICE_DESCRIPTION,
    os.image_url AS SERVICE_IMAGE_URL,
    os.color AS SERVICE_COLOR,
    oss.name AS SERVICE_STATUS,
    cs.name AS SERVICE_STATUS_STATUS,
	cs.created_at AS SERVICE_CREATED_AT,
    cs.created_by AS SERVICE_CREATED_BY,
    cs.updated_at AS SERVICE_UPDATED_AT,
    cs.updated_by AS SERVICE_UPDATED_BY,
    cs.terminated_at AS SERVICE_TERMINATED_AT,
    cs.terminated_by AS SERVICE_TERMINATED_BY
FROM our_services os
LEFT JOIN our_service_status oss
ON os.our_service_status_id = oss.id
LEFT JOIN common_status cs
ON oss.common_status_id = cs.id;

