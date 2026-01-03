CREATE TABLE about_us_hero_section (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    title VARCHAR(100),
    subtitle VARCHAR(100),
    description TEXT,
    primary_button_text VARCHAR(50),
    primary_button_link VARCHAR(255),
    secondary_button_text VARCHAR(50),
    secondary_button_link VARCHAR(255),
    status INT NOT NULL,
    `order` INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);


INSERT INTO about_us_hero_section (
    name, 
    image_url, 
    title, 
    subtitle, 
    description, 
    primary_button_text, 
    primary_button_link, 
    secondary_button_text, 
    secondary_button_link, 
    status, 
    `order`, 
    created_by, 
    updated_by
) VALUES 
(
    'About Us Hero 1',
    'https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80',
    'About Us',
    'Who We Are?',
    'We are a Sri Lanka Tourism Development Authority & Sri Lanka Civil Aviation Authority approved travel agent based in Colombo, Sri Lanka. Providing exceptional travel experiences since 2010.',
    'Explore Sri Lanka',
    '/destinations',
    'Contact Us',
    '/contact',
    1, -- Active status
    1,
    1, -- Admin user ID
    1
),
(
    'About Us Hero 2',
    'https://images.unsplash.com/photo-1563492065599-3520f775eeed?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80',
    'Our Mission',
    'Creating Unforgettable Journeys',
    'To be Sri Lanka\'s most trusted travel partner, delivering authentic experiences with professional service, local expertise, and official certifications that guarantee quality and reliability.',
    'Our Services',
    '/services',
    'View Packages',
    '/packages',
    1,
    2,
    1,
    1
),
(
    'About Us Hero 3',
    'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80',
    'Certified Excellence',
    'Officially Approved Travel Experts',
    'Our dual approval from Sri Lanka Tourism Development Authority and Civil Aviation Authority ensures you receive professional, reliable, and compliant travel services for your peace of mind.',
    'View Certifications',
    '/certifications',
    'Book Consultation',
    '/consultation',
    1,
    3,
    1,
    1
),
(
    'About Us Hero 4',
    'https://images.unsplash.com/photo-1544551763-46a013bb70d5?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80',
    'Local Expertise',
    'Colombo Based, Globally Connected',
    'Based in the heart of Colombo, we combine deep local knowledge with international standards to create seamless travel experiences across Sri Lanka and beyond.',
    'Meet Our Team',
    '/team',
    'Local Experiences',
    '/local-tours',
    1,
    4,
    1,
    1
),
(
    'About Us Hero 5',
    'https://images.unsplash.com/photo-1559128010-7c1ad6e1b6a5?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80',
    'Trusted Partner',
    '15+ Years of Excellence',
    'With over 15 years in the travel industry, we have built a reputation for reliability, transparency, and exceptional customer service in Sri Lanka\'s tourism sector.',
    'Client Testimonials',
    '/testimonials',
    'Our Story',
    '/history',
    1,
    5,
    1,
    1
);


-- Join with common_status table to get status name
SELECT 
    a.id,
    a.name,
    a.image_url,
    a.title,
    a.subtitle,
    a.description,
    a.primary_button_text,
    a.primary_button_link,
    a.secondary_button_text,
    a.secondary_button_link,
    a.`order`,
    a.created_at,
    a.updated_at,
    s.name
FROM about_us_hero_section a
LEFT JOIN common_status s ON a.status = s.id
WHERE a.terminated_at IS NULL 
ORDER BY a.`order` ASC, a.created_at ASC;
