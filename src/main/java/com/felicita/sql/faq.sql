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

 
 
 
 
 
 
 
 
 
 
 
 

