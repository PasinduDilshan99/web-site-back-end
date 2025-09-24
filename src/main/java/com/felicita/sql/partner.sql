use travelagencydb;

CREATE TABLE partner_status (
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

-- Sample data
INSERT INTO partner_status (name, description, common_status_id, created_by)
VALUES
('ACTIVE', 'Partner is active', 1, 1),
('INACTIVE', 'Partner is inactive', 2, 1),
('TERMINATED', 'Partner is terminated', 3, 1);

CREATE TABLE partners (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    company_name VARCHAR(150) NOT NULL,
    company_description TEXT,
    company_logo VARCHAR(255),
    company_website_url VARCHAR(255),
    agreement TEXT,
    partner_status_id INT NOT NULL,
    from_date DATE,
    to_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (partner_status_id) REFERENCES partner_status(id)
);

INSERT INTO partners (
    name, company_name, company_description, company_logo, company_website_url, agreement, partner_status_id, from_date, to_date, created_by
)
VALUES
('partner-1', 'Tech Solutions Ltd', 'Leading technology solutions provider.', '/images/partners-images/partner-1.png', 'https://techsolutions.com', 'Standard Agreement', 1, '2025-01-01', '2025-12-31', 1),
('partner-2', 'Green Energy Corp', 'Renewable energy solutions for a sustainable future.', '/images/partners-images/partner-2.png', 'https://greenenergy.com', 'Standard Agreement', 1, '2025-03-01', '2026-02-28', 1),
('partner-3', 'HealthPlus Inc', 'Innovative healthcare services and products.', '/images/partners-images/partner-3.png', 'https://healthplus.com', 'Standard Agreement', 1, '2025-05-01', '2026-04-30', 1);




SELECT 
	p.id AS PARTNER_ID,
    p.name AS PARTNER_NAME,
    p.company_name AS PARTNER_COMPANY_NAME,
    p.company_description AS PARTNER_COMPANY_DESCRIPTION,
    p.company_logo AS PARTNER_LOGO_URL,
    p.company_website_url AS PARTNER_WEBSITE_URL,
    p.agreement AS PARTNER_AGREEMENT,
    ps.name AS PARTNER_STATUS,
    cs.name AS PARTNER_STATUS_STATUS,
    p.from_date AS PARTNER_FROM_DATE,
    p.to_date AS PARTNER_TO_DATE,
	p.created_at AS PARTNER_CREATED_AT,
    p.created_by AS PARTNER_CREATED_BY,
    p.updated_at AS PARTNER_UPDATED_AT,
    p.updated_by AS PARTNER_UPDATED_BY,
    p.terminated_at AS PARTNER_TERMINATED_AT,
    p.terminated_by AS PARTNER_TERMINATED_BY
FROM partners p
LEFT JOIN partner_status ps
ON p.partner_status_id = ps.id
LEFT JOIN common_status cs
ON ps.common_status_id = cs.id;
























