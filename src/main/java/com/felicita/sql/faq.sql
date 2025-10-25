use travelagencydb;

CREATE TABLE faq_status (
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

-- Sample statuses
INSERT INTO faq_status (name, description, common_status_id, created_by)
VALUES
('VISIBLE', 'FAQ is visible on frontend', 1, 1),
('HIDDEN', 'FAQ is hidden from frontend', 2, 1);


CREATE TABLE faq (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question VARCHAR(255) NOT NULL,
    answer1 TEXT,
    answer2 TEXT,
    answer3 TEXT,
    answer4 TEXT,
    answer5 TEXT,
    display_answer VARCHAR(50), -- e.g., "answer1", "answer2", or custom label
    faq_status_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (faq_status_id) REFERENCES faq_status(id)
);


CREATE TABLE faq_view_count (
    id INT PRIMARY KEY AUTO_INCREMENT,
    faq_id INT NOT NULL,
    count INT DEFAULT 0,
    last_view TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (faq_id) REFERENCES faq(id)
);

INSERT INTO faq (question, answer1, answer2, answer3, answer4, answer5, display_answer, faq_status_id, created_by)
VALUES
('Do I need a visa to visit Sri Lanka?', 
 'Most tourists need an Electronic Travel Authorization (ETA).', 
 'You can apply online through the official immigration website.', 
 'ETA approval usually arrives within 24 hours.', 
 'Some countries are visa-exempt for short visits.', 
 'On-arrival visa facilities are limited, so apply online first.', 
 'answer1', 1, 1),

('How long does it take to get an ETA visa?', 
 'In most cases, approval takes a few hours.', 
 'During busy periods, it may take up to 2 days.', 
 'Emergency processing is available at the airport.', 
 'We recommend applying at least 3 days before your trip.', 
 'The online system is available 24/7 worldwide.', 
 'answer1', 1, 1),

('What is the best time to visit Sri Lanka?', 
 'December to April is best for the south and west coasts.', 
 'May to September is best for the east coast.', 
 'Hill country is cooler year-round, great for hiking.', 
 'Cultural festivals like Kandy Perahera are in July/August.', 
 'Wildlife safaris are best in the dry season (June–September).', 
 'answer1', 1, 1),

('Which currency is used in Sri Lanka?', 
 'The Sri Lankan Rupee (LKR) is the official currency.', 
 'Foreign currency can be exchanged at banks and hotels.', 
 'ATMs are widely available in cities and tourist towns.', 
 'USD, EUR, and GBP are the most accepted foreign currencies.', 
 'Credit cards can be used but cash is preferred in rural areas.', 
 'answer1', 1, 1),

('Can I use credit cards in Sri Lanka?', 
 'Yes, most hotels and restaurants accept cards.', 
 'Visa and Mastercard are the most widely accepted.', 
 'American Express and Diners Club are less common.', 
 'Always carry cash for small shops and rural areas.', 
 'Some ATMs allow direct cash withdrawals from foreign cards.', 
 'answer1', 1, 1),

('Is Sri Lanka safe for foreign tourists?', 
 'Sri Lanka is generally considered very safe.', 
 'Petty theft can occur in crowded areas.', 
 'Locals are friendly and welcoming to tourists.', 
 'Avoid traveling alone late at night in isolated areas.', 
 'Tourist police are available in major destinations.', 
 'answer1', 1, 1),

('Do I need vaccinations before traveling to Sri Lanka?', 
 'No compulsory vaccines are required.', 
 'Recommended: Hepatitis A and Typhoid.', 
 'Some travelers consider Yellow Fever if coming from endemic countries.', 
 'Tetanus and Rabies shots are advisable for long stays.', 
 'Consult your doctor before travel.', 
 'answer1', 1, 1),

('What language is spoken in Sri Lanka?', 
 'Sinhala is the most widely spoken language.', 
 'Tamil is also an official language.', 
 'English is widely used in tourism and business.', 
 'Most hotels and guides speak English fluently.', 
 'Signboards in tourist areas are often bilingual.', 
 'answer1', 1, 1),

('Can I drink tap water in Sri Lanka?', 
 'It is not safe to drink tap water directly.', 
 'Always drink bottled or boiled water.', 
 'Hotels often provide filtered water.', 
 'Ice in tourist hotels is usually safe.', 
 'Avoid drinking from public taps.', 
 'answer1', 1, 1),

('What are the must-visit places in Sri Lanka?', 
 'Sigiriya Rock Fortress – UNESCO World Heritage Site.', 
 'Kandy – Temple of the Tooth.', 
 'Galle Fort – historic colonial town.', 
 'Ella – scenic train rides and tea plantations.', 
 'Yala National Park – famous for leopards.', 
 'answer1', 1, 1),

('What is the local transportation like in Sri Lanka?', 
 'Tuk-tuks are cheap and popular for short distances.', 
 'Trains offer scenic rides, especially Kandy to Ella.', 
 'Buses are affordable but crowded.', 
 'Private car rentals with drivers are convenient.', 
 'Domestic flights are available between major cities.', 
 'answer1', 1, 1),

('Do I need travel insurance for Sri Lanka?', 
 'Yes, travel insurance is strongly recommended.', 
 'It should cover medical emergencies.', 
 'Policies should include theft and lost luggage.', 
 'Adventure sports coverage is useful for safaris or surfing.', 
 'COVID-19 coverage may be required at entry.', 
 'answer1', 1, 1),

('Is there free Wi-Fi in Sri Lanka?', 
 'Most hotels and cafes offer free Wi-Fi.', 
 'Speeds may vary outside major cities.', 
 'Tourist SIM cards include mobile data.', 
 '4G coverage is available in most towns.', 
 'Some buses and trains now offer free Wi-Fi.', 
 'answer1', 1, 1),

('Which mobile SIM card is best for tourists in Sri Lanka?', 
 'Dialog is the most popular with wide coverage.', 
 'Mobitel is also widely used and reliable.', 
 'Both offer tourist SIM packages at the airport.', 
 'Etisalat and Hutch offer budget options.', 
 'SIM registration requires your passport.', 
 'answer1', 1, 1),

('What kind of food can I expect in Sri Lanka?', 
 'Traditional rice and curry with vegetables and meat.', 
 'Seafood dishes are very popular on the coasts.', 
 'Street food like kottu roti is a must-try.', 
 'Hoppers and string hoppers are common breakfast items.', 
 'Plenty of vegetarian and vegan options available.', 
 'answer1', 1, 1),

('Is tipping expected in Sri Lanka?', 
 'Tipping is not mandatory but appreciated.', 
 'A 10% tip is common in restaurants.', 
 'Some hotels include a service charge already.', 
 'Tour drivers usually receive daily tips.', 
 'Small tips for porters and tuk-tuk drivers are welcome.', 
 'answer1', 1, 1),

('What are the cultural etiquette rules in Sri Lanka?', 
 'Dress modestly when visiting temples.', 
 'Remove shoes and hats before entering temples.', 
 'Never pose with your back to a Buddha statue.', 
 'Public displays of affection are discouraged.', 
 'Use your right hand for giving and receiving items.', 
 'answer1', 1, 1),

('Can I rent a car and drive in Sri Lanka?', 
 'Yes, but you need an International Driving Permit.', 
 'Traffic can be chaotic for first-time visitors.', 
 'Most tourists prefer hiring a driver.', 
 'Cars drive on the left side of the road.', 
 'Rental cars are available in major cities.', 
 'answer1', 1, 1),

('What is the emergency contact number in Sri Lanka?', 
 'Police – 119', 
 'Ambulance/Medical emergencies – 110', 
 'Fire department – 111', 
 'Tourist Police hotline – 1912', 
 'Emergency assistance is also available via your hotel.', 
 'answer1', 1, 1),

('Are there national parks to see wildlife in Sri Lanka?', 
 'Yes, Yala is famous for leopards.', 
 'Udawalawe is known for elephants.', 
 'Minneriya has the largest elephant gathering.', 
 'Wilpattu is Sri Lanka’s oldest park.', 
 'Bundala is a paradise for birdwatchers.', 
 'answer1', 1, 1);
 
 -- FAQ Option Type Table
CREATE TABLE faq_option_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert FAQ option types
INSERT INTO faq_option_type (name, description, created_by) VALUES
('string', 'Text string value', 1),
('number', 'Numeric value', 1),
('boolean', 'True/False value', 1),
('json', 'JSON formatted data', 1),
('array', 'Array of values', 1);


-- FAQ Options Table
CREATE TABLE faq_options (
    id INT PRIMARY KEY AUTO_INCREMENT,
    option_key VARCHAR(100) NOT NULL UNIQUE,
    option_value TEXT,
    option_type_id INT NOT NULL,
    description VARCHAR(255),
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (option_type_id) REFERENCES faq_option_type(id),
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert default FAQ options
INSERT INTO faq_options (option_key, option_value, option_type_id, description, created_by) VALUES
('display_limit_mobile', '5', (SELECT id FROM faq_option_type WHERE name = 'number'), 'Number of FAQs to show on mobile devices', 1),
('display_limit_tablet', '6', (SELECT id FROM faq_option_type WHERE name = 'number'), 'Number of FAQs to show on tablet devices', 1),
('display_limit_desktop', '7', (SELECT id FROM faq_option_type WHERE name = 'number'), 'Number of FAQs to show on desktop devices', 1),
('contact_form_categories', '["general","technical","billing","feature","bug"]', (SELECT id FROM faq_option_type WHERE name = 'json'), 'Available categories for contact form', 1),
('auto_reply_enabled', 'true', (SELECT id FROM faq_option_type WHERE name = 'boolean'), 'Whether to send auto-reply emails', 1),
('auto_reply_subject', 'We have received your message', (SELECT id FROM faq_option_type WHERE name = 'string'), 'Subject for auto-reply emails', 1),
('auto_reply_message', 'Thank you for contacting us. We will get back to you within 24 hours.', (SELECT id FROM faq_option_type WHERE name = 'string'), 'Message for auto-reply emails', 1),
('support_email', 'support@yourcompany.com', (SELECT id FROM faq_option_type WHERE name = 'string'), 'Default support email address', 1),
('response_time_hours', '24', (SELECT id FROM faq_option_type WHERE name = 'number'), 'Expected response time in hours', 1),
('enable_view_tracking', 'true', (SELECT id FROM faq_option_type WHERE name = 'boolean'), 'Enable FAQ view count tracking', 1);

-- Contact Request Status Table
CREATE TABLE faq_contact_request_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    color VARCHAR(7) DEFAULT '#6B7280', -- Hex color for UI
    sort_order INT DEFAULT 0,
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert contact request statuses
INSERT INTO faq_contact_request_status (name, description, color, sort_order, created_by) VALUES
('new', 'New request received', '#EF4444', 1, 1),
('in_progress', 'Request is being handled', '#F59E0B', 2, 1),
('awaiting_reply', 'Waiting for customer reply', '#8B5CF6', 3, 1),
('resolved', 'Issue has been resolved', '#10B981', 4, 1),
('closed', 'Request has been closed', '#6B7280', 5, 1);

-- Contact Priority Table
CREATE TABLE faq_contact_priority (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    color VARCHAR(7) DEFAULT '#6B7280', -- Hex color for UI
    sort_order INT DEFAULT 0,
    response_time_hours INT, -- Expected response time in hours
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert contact priorities
INSERT INTO faq_contact_priority (name, description, color, sort_order, response_time_hours, created_by) VALUES
('low', 'Low priority', '#6B7280', 1, 72, 1),
('medium', 'Medium priority', '#10B981', 2, 24, 1),
('high', 'High priority', '#F59E0B', 3, 4, 1),
('urgent', 'Urgent priority', '#EF4444', 4, 1, 1);

-- Contact Reply Type Table
CREATE TABLE faq_contact_reply_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    icon VARCHAR(50), -- Icon name for UI
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (common_status_id) REFERENCES common_status(id)
);

-- Insert contact reply types
INSERT INTO faq_contact_reply_type (name, description, icon, created_by) VALUES
('customer', 'Reply from customer', 'user', 1),
('support', 'Reply from support team', 'support_agent', 1),
('system', 'System generated message', 'auto_message', 1);

-- Contact Requests Table
CREATE TABLE faq_contact_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_number VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    request_status_id INT NOT NULL,
    priority_id INT NOT NULL,
    assigned_to INT,
    first_response_at TIMESTAMP NULL,
    resolved_at TIMESTAMP NULL,
    closed_at TIMESTAMP NULL,
	auto_reply_sent BOOLEAN DEFAULT FALSE,
    auto_reply_sent_at TIMESTAMP NULL,
    reply_count INT DEFAULT 0,
    last_reply_at TIMESTAMP NULL,
    ip_address VARCHAR(45),
    user_agent TEXT,
    referrer_url VARCHAR(500),
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (request_status_id) REFERENCES faq_contact_request_status(id),
    FOREIGN KEY (priority_id) REFERENCES faq_contact_priority(id),
    FOREIGN KEY (common_status_id) REFERENCES common_status(id),
    FOREIGN KEY (assigned_to) REFERENCES user(user_id)
);

-- Contact Request Replies Table
CREATE TABLE faq_contact_request_replies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    faq_contact_request_id INT NOT NULL,
    reply_type_id INT NOT NULL,
    message TEXT NOT NULL,
    internal_notes TEXT,
    replied_by INT,
    replied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    common_status_id INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP,
    terminated_by INT,
    FOREIGN KEY (faq_contact_request_id) REFERENCES faq_contact_requests(id) ON DELETE CASCADE,
    FOREIGN KEY (reply_type_id) REFERENCES faq_contact_reply_type(id),
    FOREIGN KEY (common_status_id) REFERENCES common_status(id),
    FOREIGN KEY (replied_by) REFERENCES user(user_id)
);

 
SELECT 
	f.id AS FAQ_ID,
    f.question AS FAQ_QUESTION,
    f.answer1 AS FAQ_ANSWER1,
    f.answer2 AS FAQ_ANSWER2,
    f.answer3 AS FAQ_ANSWER3,
    f.answer4 AS FAQ_ANSWER4,
    f.answer5 AS FAQ_ANSWER5,
    f.display_answer AS FAQ_DISPLAY_ASNWER,
    fs.name AS FAQ_STATUS,
    cs.name AS FAQ_STATUS_STATUS,
	f.created_at AS FAQ_CREATED_AT,
    f.created_by AS FAQ_CREATED_BY,
    f.updated_at AS FAQ_UPDATED_AT,
    f.updated_by AS FAQ_UPDATED_BY,
    f.terminated_at AS FAQ_TERMINATED_AT,
    f.terminated_by AS FAQ_TERMINATED_BY,
    fvc.count AS FAQ_VIEW_COUNT,
    fvc.last_view AS FAQ_LAST_VIEW
FROM faq f
LEFT JOIN faq_status fs
ON f.faq_status_id = fs.id
LEFT JOIN common_status cs
ON fs.common_status_id = cs.id
LEFT JOIN faq_view_count fvc
ON f.id = fvc.faq_id; 
 
UPDATE faq_view_count
SET count = 1, last_view = NOW()
WHERE faq_id = 1;

 
 
 
 
 
 
 
 
 
 
 
 

