INSERT INTO seasons (name, description, status, created_by) VALUES
-- Temperate Seasons
('Spring', 'Mild weather, flowers bloom, popular for outdoor activities', 1, 1),
('Summer', 'Hot and sunny season, ideal for beach activities, hiking, and festivals', 1, 1),
('Autumn', 'Cooler weather, leaves change color, great for scenic tours', 1, 1),
('Winter', 'Cold season, snow in some regions, perfect for skiing and mountain tours', 1, 1),

-- Tropical Seasons
('Dry Season', 'Sunny and dry, best time for safaris, island travel, and outdoor adventures', 1, 1),
('Wet Season', 'Heavy rains and lush landscapes, waterfalls at peak beauty', 1, 1),
('Monsoon', 'Characterized by seasonal rainfall, cultural festivals, and rich greenery', 1, 1),
('Cool Season', 'Comfortable temperatures in tropical regions, ideal for cultural and city tours', 1, 1),

-- Tourism Seasons
('Peak Season', 'High demand period with best weather, higher prices, and crowded attractions', 1, 1),
('Shoulder Season', 'Transition period between peak and low, fewer crowds, balanced prices', 1, 1),
('Off Season', 'Least busy travel period, lowest prices, may have unfavorable weather', 1, 1);


INSERT INTO activity_category (name, description, status, created_by) VALUES
('Adventure', 'Thrilling outdoor activities like climbing, hiking, and trekking', 1, 1),
('Water Sports', 'Ocean and water-based activities including surfing, diving, and snorkeling', 1, 1),
('Wildlife', 'Animal watching and safari experiences', 1, 1),
('Marine Life', 'Ocean wildlife experiences like whale watching and dolphin tours', 1, 1),
('Sightseeing', 'Visiting landmarks, viewpoints, and scenic locations', 1, 1),
('Hiking', 'Mountain and nature trail walking activities', 1, 1),
('Cultural', 'Temple visits, cultural shows, and heritage experiences', 1, 1),
('Wellness', 'Yoga, meditation, spa, and relaxation activities', 1, 1),
('Photography', 'Specialized photo tours and sessions', 1, 1),
('Food & Dining', 'Culinary experiences, cooking classes, and food tours', 1, 1);


-- 2. Insert Tour Types
INSERT INTO tour_type (name, description, status, created_by) VALUES
('Adventure', 'Thrilling outdoor activities and experiences', 1, 1),
('Cultural', 'Explore heritage sites and local traditions', 1, 1),
('Wildlife', 'Safari and nature exploration tours', 1, 1),
('Beach', 'Coastal and water-based activities', 1, 1),
('Wellness', 'Spa, yoga, and relaxation focused tours', 1, 1);

-- 3. Insert Tour Categories
INSERT INTO tour_category (name, description, status, created_by) VALUES
('Luxury', 'Premium experiences with high-end accommodations', 1, 1),
('Budget', 'Affordable tours for budget travelers', 1, 1),
('Family', 'Family-friendly tours suitable for all ages', 1, 1),
('Solo', 'Perfect for solo travelers and backpackers', 1, 1),
('Group', 'Large group tours with shared experiences', 1, 1);


INSERT INTO package_type (name, description, status, created_by) VALUES
-- Accommodation & Meal Packages
('All-Inclusive', 'Everything included – accommodation, meals, drinks, transport, and activities', 1, 1),
('Full-Board', 'Accommodation with breakfast, lunch, and dinner included', 1, 1),
('Half-Board', 'Accommodation with breakfast and either lunch or dinner included', 1, 1),
('Bed & Breakfast', 'Accommodation with breakfast included only', 1, 1),

-- Duration-Based Packages
('Day Trip', 'Single day excursion packages including activities and meals', 1, 1),
('Weekend Getaway', 'Short stay package ideal for weekends or holidays', 1, 1),
('Extended Stay', 'Longer duration package with discounted rates', 1, 1),

-- Customization & Themes
('Custom', 'Fully customizable package based on traveler preferences', 1, 1),
('Adventure', 'Adventure-focused package including hiking, safaris, and outdoor activities', 1, 1),
('Luxury', 'Premium package with 5-star accommodation, private transport, and exclusive services', 1, 1),
('Budget', 'Affordable travel package with basic amenities for cost-conscious travelers', 1, 1),
('Honeymoon', 'Romantic package designed for couples with special activities and services', 1, 1),
('Family', 'Family-friendly package with kid-friendly activities and accommodations', 1, 1);



INSERT INTO tour 
(name, description, tour_type, tour_category, duration, latitude, longitude, start_location, end_location, season, status, created_by) 
VALUES
-- 1: Adventure + Solo
('Sigiriya Rock Fortress Adventure', 'Climb the ancient rock fortress and explore UNESCO heritage site', 1, 4, 1, 7.9570, 80.7603, 'Colombo', 'Sigiriya', 1, 1, 1),
-- 2: Cultural + Budget
('Cultural Triangle Tour', 'Visit ancient cities: Anuradhapura, Polonnaruwa, Sigiriya', 2, 2, 5, 8.3114, 80.4037, 'Colombo', 'Anuradhapura', 2, 1, 1),
-- 3: Wildlife + Family
('Yala Wildlife Safari', 'Spot leopards, elephants and diverse wildlife in national park', 3, 3, 2, 6.3725, 81.5194, 'Tissamaharama', 'Yala', 2, 1, 1),
-- 4: Beach + Group
('Mirissa Beach Retreat', 'Relax on pristine beaches and enjoy whale watching', 4, 5, 2, 5.9450, 80.4489, 'Galle', 'Mirissa', 1, 1, 1),
-- 5: Adventure + Family
('Ella Hill Country Explorer', 'Scenic train journey through tea plantations and waterfalls', 1, 3, 3, 6.8667, 81.0467, 'Kandy', 'Ella', 4, 1, 1),
-- 6: Wellness + Luxury
('Bentota Spa & Wellness Escape', 'Rejuvenate with spa treatments, yoga, and beachside relaxation', 5, 1, 3, 6.4210, 80.0050, 'Colombo', 'Bentota', 1, 1, 1),
-- 7: Cultural + Family
('Kandy Temple & Cultural Dance Tour', 'Experience the Temple of the Tooth and traditional dance shows', 2, 3, 2, 7.2906, 80.6337, 'Kandy', 'Kandy', 4, 1, 1),
-- 8: Adventure + Group
('Knuckles Mountain Hiking', 'Trek through Knuckles mountain range with scenic landscapes', 1, 5, 2, 7.3898, 80.7840, 'Kandy', 'Knuckles Range', 3, 1, 1),
-- 9: Wildlife + Luxury
('Wilpattu Safari Experience', 'Explore Sri Lanka’s largest national park with luxury camping', 3, 1, 3, 8.4590, 80.1000, 'Puttalam', 'Wilpattu', 2, 1, 1),
-- 10: Beach + Solo
('Arugam Bay Surf Trip', 'World-class surfing destination popular with backpackers', 4, 4, 4, 6.8390, 81.8340, 'Colombo', 'Arugam Bay', 1, 1, 1),
-- 11: Wellness + Group
('Ayurvedic Healing Retreat in Dambulla', 'Traditional Ayurveda treatments and meditation programs', 5, 5, 3, 7.8570, 80.6510, 'Colombo', 'Dambulla', 4, 1, 1),
-- 12: Cultural + Luxury
('Jaffna Heritage & Food Tour', 'Discover Jaffna’s temples, forts, and unique cuisine', 2, 1, 4, 9.6685, 80.0074, 'Colombo', 'Jaffna', 2, 1, 1),
-- 13: Adventure + Budget
('Kitulgala White Water Rafting', 'Thrilling rafting experience on Kelani River', 1, 2, 1, 6.9886, 80.4250, 'Colombo', 'Kitulgala', 1, 1, 1),
-- 14: Beach + Family
('Trincomalee Beach & Snorkeling', 'Family-friendly beach stay with snorkeling at Pigeon Island', 4, 3, 3, 8.5711, 81.2335, 'Colombo', 'Trincomalee', 1, 1, 1),
-- 15: Wildlife + Solo
('Udawalawe Elephant Safari', 'Jeep safari to see large herds of elephants in the wild', 3, 4, 1, 6.4267, 80.8987, 'Colombo', 'Udawalawe', 2, 1, 1);


INSERT INTO packages 
(tour_id, name, description, total_price, discount_percentage, start_date, end_date, color, status, hover_color, min_person_count, max_person_count, price_per_person, created_by) 
VALUES
-- Tour 1: Sigiriya Rock Fortress Adventure
(1, 'Sigiriya Day Package', 'Full day tour with guide and transport', 15000.00, 10.00, '2025-10-01', '2026-03-31', '#FF6B6B', 1, '#FF5252', 2, 8, 7500.00, 1),
(1, 'Sigiriya Sunrise Hike', 'Morning hike with breakfast and cultural guide', 18000.00, 12.00, '2025-11-01', '2026-04-30', '#FFA07A', 1, '#FF8C69', 2, 6, 9000.00, 1),

-- Tour 2: Cultural Triangle Tour
(2, 'Cultural Triangle 5-Day', 'Complete cultural experience with expert guide', 75000.00, 12.00, '2025-10-01', '2026-05-31', '#AA96DA', 1, '#9580D0', 4, 12, 18750.00, 1),
(2, 'Triangle Express 3-Day', 'Quick exploration of Anuradhapura, Polonnaruwa, and Sigiriya', 52000.00, 8.00, '2025-10-01', '2026-03-31', '#9370DB', 1, '#836FFF', 2, 10, 17333.00, 1),

-- Tour 3: Yala Wildlife Safari
(3, 'Yala Safari Experience', 'Two-day safari with luxury camping', 35000.00, 5.00, '2025-11-01', '2026-02-28', '#95E1D3', 1, '#7DD4C5', 2, 10, 17500.00, 1),
(3, 'Yala Premium Jeep Safari', 'Private jeep with naturalist guide', 42000.00, 10.00, '2025-11-01', '2026-05-15', '#20B2AA', 1, '#2E8B57', 2, 6, 21000.00, 1),

-- Tour 4: Mirissa Beach Retreat
(4, 'Mirissa Beach Escape', 'Relaxing beach holiday with water sports', 40000.00, 20.00, '2025-10-15', '2026-03-15', '#F38181', 1, '#F06868', 2, 4, 20000.00, 1),
(4, 'Whale Watching Adventure', 'Half-day boat trip with breakfast included', 18000.00, 15.00, '2025-11-01', '2026-04-30', '#FFB6C1', 1, '#FF69B4', 2, 10, 9000.00, 1),

-- Tour 5: Ella Hill Country Explorer
(5, 'Ella 3-Day Adventure', 'Three days exploring Ella with accommodation', 45000.00, 15.00, '2025-10-01', '2026-04-30', '#4ECDC4', 1, '#3BB8B0', 2, 6, 22500.00, 1),
(5, 'Ella Scenic Train & Tea Tour', '2-day package including train and tea plantation visits', 28000.00, 10.00, '2025-11-01', '2026-05-31', '#48D1CC', 1, '#40E0D0', 2, 6, 14000.00, 1),

-- Tour 6: Bentota Spa & Wellness Escape
(6, 'Bentota Spa Weekend', '2-night stay with massages and yoga sessions', 60000.00, 15.00, '2025-10-01', '2026-06-30', '#FFD700', 1, '#FFC107', 2, 4, 30000.00, 1),
(6, 'Luxury Wellness Retreat', '5-day retreat with spa, meditation, and Ayurveda meals', 125000.00, 20.00, '2025-11-01', '2026-05-31', '#DAA520', 1, '#B8860B', 2, 6, 62500.00, 1),

-- Tour 7: Kandy Temple & Cultural Dance
(7, 'Kandy Culture Half-Day', 'Temple visit and evening dance show', 12000.00, 10.00, '2025-10-01', '2026-06-30', '#BA55D3', 1, '#9932CC', 2, 12, 6000.00, 1),
(7, 'Kandy Full Experience', 'City tour including temple, dance, and botanical gardens', 22000.00, 15.00, '2025-11-01', '2026-04-30', '#8A2BE2', 1, '#6A5ACD', 2, 10, 11000.00, 1),

-- Tour 8: Knuckles Mountain Hiking
(8, 'Knuckles Day Hike', 'One-day trek with guide and meals', 18000.00, 10.00, '2025-10-01', '2026-05-31', '#32CD32', 1, '#228B22', 2, 8, 9000.00, 1),
(8, 'Knuckles Camping Adventure', '2-day trek with overnight camping', 32000.00, 12.00, '2025-11-01', '2026-04-30', '#006400', 1, '#2E8B57', 2, 6, 16000.00, 1),

-- Tour 9: Wilpattu Safari Experience
(9, 'Wilpattu Luxury Safari', '3-day safari with luxury lodge stay', 95000.00, 18.00, '2025-10-15', '2026-05-31', '#8B4513', 1, '#A0522D', 2, 6, 47500.00, 1),
(9, 'Wilpattu Classic Safari', '2-day safari with tented camping', 55000.00, 10.00, '2025-11-01', '2026-04-30', '#D2691E', 1, '#CD853F', 2, 8, 27500.00, 1),

-- Tour 10: Arugam Bay Surf Trip
(10, 'Surf Beginner Package', '3-day surfing lessons with equipment', 38000.00, 10.00, '2025-05-01', '2025-09-30', '#00CED1', 1, '#20B2AA', 2, 6, 19000.00, 1),
(10, 'Surf Pro Adventure', '5-day advanced surfing experience with beach parties', 68000.00, 12.00, '2025-05-01', '2025-09-30', '#1E90FF', 1, '#4169E1', 2, 8, 34000.00, 1),

-- Tour 11: Ayurvedic Healing Retreat
(11, 'Ayurveda Short Retreat', '3-day healing and detox program', 72000.00, 15.00, '2025-11-01', '2026-05-31', '#FF8C00', 1, '#FF7F50', 2, 6, 36000.00, 1),
(11, 'Deep Healing Package', '7-day full Ayurveda program with meals and treatments', 160000.00, 20.00, '2025-11-01', '2026-05-31', '#FF6347', 1, '#FF4500', 2, 10, 80000.00, 1),

-- Tour 12: Jaffna Heritage & Food Tour
(12, 'Jaffna 2-Day Heritage', 'Explore temples, forts, and taste authentic cuisine', 45000.00, 10.00, '2025-10-01', '2026-06-30', '#FF1493', 1, '#DB7093', 2, 10, 22500.00, 1),
(12, 'Jaffna Extended 4-Day', 'Deeper cultural and culinary exploration', 85000.00, 12.00, '2025-10-01', '2026-06-30', '#C71585', 1, '#8B008B', 2, 8, 42500.00, 1),

-- Tour 13: Kitulgala White Water Rafting
(13, 'Rafting Day Package', 'Half-day white water rafting experience', 18000.00, 10.00, '2025-10-01', '2026-06-30', '#40E0D0', 1, '#48D1CC', 2, 10, 9000.00, 1),
(13, 'Rafting + Canyoning Combo', 'Adventure combo with rafting and canyoning', 32000.00, 15.00, '2025-11-01', '2026-05-31', '#4682B4', 1, '#5F9EA0', 2, 8, 16000.00, 1),

-- Tour 14: Trincomalee Beach & Snorkeling
(14, 'Snorkeling Day Trip', 'Pigeon Island snorkeling with guide and boat transfer', 22000.00, 10.00, '2025-05-01', '2025-09-30', '#00FA9A', 1, '#00FF7F', 2, 12, 11000.00, 1),
(14, 'Trinco Beach Stay 3-Day', 'Beach resort with snorkeling and dolphin watching', 60000.00, 15.00, '2025-05-01', '2025-09-30', '#3CB371', 1, '#2E8B57', 2, 8, 30000.00, 1),

-- Tour 15: Udawalawe Elephant Safari
(15, 'Elephant Day Safari', 'Full-day jeep safari in Udawalawe', 20000.00, 8.00, '2025-11-01', '2026-05-31', '#B22222', 1, '#DC143C', 2, 12, 10000.00, 1),
(15, 'Udawalawe Overnight Safari', '2-day experience with camping near park', 42000.00, 12.00, '2025-11-01', '2026-05-31', '#CD5C5C', 1, '#8B0000', 2, 6, 21000.00, 1);


INSERT INTO destination_categories (category, description, status, created_by) VALUES
('Adventure', 'Thrilling activities such as hiking, rafting, and outdoor challenges (e.g., Kitulgala)', 1, 1),
('Wildlife', 'National parks and safaris to experience animals in their natural habitat', 1, 1),
('Cultural & Heritage', 'Cultural festivals, temples, and heritage sites (e.g., Kandy Perahera)', 1, 1),
('Beach', 'Coastal destinations, surfing spots, and beach resorts', 1, 1),
('Hill Country', 'Scenic highlands with tea estates, waterfalls, and train journeys', 1, 1);


INSERT INTO destination (name, description, status, destination_category, location, latitude, longitude, created_by) VALUES
-- Adventure
('Kitulgala', 'Famous for white-water rafting and jungle adventures', 1, 1, 'Sabaragamuwa Province', 6.9886, 80.4250, 1),
('Horton Plains', 'Hiking trails with stunning landscapes and World End view', 1, 1, 'Central Province', 6.8026, 80.7991, 1),
('Knuckles Mountain Range', 'Trekking and camping in scenic mountain ranges', 1, 1, 'Central Province', 7.3898, 80.7840, 1),
('Adams Peak', 'Pilgrimage and trekking adventure with panoramic sunrise', 1, 1, 'Sabaragamuwa Province', 6.8090, 80.4990, 1),
('Sinharaja Forest', 'Rainforest trekking with waterfalls and wildlife spotting', 1, 1, 'Southern Province', 6.4020, 80.4020, 1),

-- Wildlife
('Yala National Park', 'Most visited and second largest national park', 1, 2, 'Southern Province', 6.3725, 81.5194, 1),
('Udawalawe National Park', 'Famous for large herds of elephants and safari experiences', 1, 2, 'Southern Province', 6.4267, 80.8987, 1),
('Wilpattu National Park', 'Largest national park, known for leopards', 1, 2, 'North Western Province', 8.4590, 80.1000, 1),
('Minneriya National Park', 'Famous for elephant gatherings during dry season', 1, 2, 'North Central Province', 8.0030, 80.9360, 1),
('Bundala National Park', 'Coastal wetlands with migratory birds and wildlife', 1, 2, 'Southern Province', 5.9910, 80.5900, 1),

-- Cultural & Heritage
('Sigiriya', 'Ancient rock fortress and UNESCO World Heritage site', 1, 3, 'Central Province', 7.9570, 80.7603, 1),
('Anuradhapura', 'Ancient capital with sacred Buddhist sites', 1, 3, 'North Central Province', 8.3114, 80.4037, 1),
('Polonnaruwa', 'Historical city with ancient ruins and temples', 1, 3, 'North Central Province', 7.9400, 81.0000, 1),
('Kandy', 'Cultural capital with Temple of the Tooth and Perahera festival', 1, 3, 'Central Province', 7.2906, 80.6337, 1),
('Galle Fort', 'UNESCO-listed fort with Dutch architecture and cultural sites', 1, 3, 'Southern Province', 6.0320, 80.2170, 1),

-- Beach
('Mirissa', 'Beautiful beach town famous for whale watching', 1, 4, 'Southern Province', 5.9450, 80.4489, 1),
('Unawatuna', 'Popular beach with water sports and nightlife', 1, 4, 'Southern Province', 5.9445, 80.2580, 1),
('Bentota', 'Resort town with beaches, water sports, and river tours', 1, 4, 'Western Province', 6.4210, 80.0050, 1),
('Trincomalee', 'East coast beach with snorkeling and diving', 1, 4, 'Eastern Province', 8.5711, 81.2335, 1),
('Arugam Bay', 'World-renowned surfing destination', 1, 4, 'Eastern Province', 6.8390, 81.8340, 1),

-- Hill Country
('Ella', 'Picturesque hill country town with stunning views', 1, 5, 'Uva Province', 6.8667, 81.0467, 1),
('Nuwara Eliya', 'Cool climate hill station with tea plantations', 1, 5, 'Central Province', 6.9497, 80.7891, 1),
('Haputale', 'Hill town with tea estates and breathtaking viewpoints', 1, 5, 'Uva Province', 6.7790, 80.9990, 1),
('Nuwara Eliya Hakgala', 'Tea gardens and botanical garden exploration', 1, 5, 'Central Province', 6.9000, 80.7830, 1),
('Dambatenne', 'Scenic tea estate in central highlands', 1, 5, 'Central Province', 6.8450, 80.9430, 1);




INSERT INTO activities 
(destination_id, name, description, activities_category, duration_hours, available_from, available_to, price_local, price_foreigners, min_participate, max_participate, season, status, created_by) 
VALUES

-- Adventure
(1, 'Sigiriya Rock Climb', 'Climb the 1200 steps to the top of Sigiriya Rock Fortress', 'Adventure', 3.5, '06:00:00', '17:00:00', 3000.00, 5000.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(8, 'Knuckles Mountain Trek', 'Full-day trekking adventure with guide in Knuckles Range', 'Adventure', 6.0, '05:30:00', '17:00:00', 4000.00, 6500.00, 2, 10, 'Summer,Winter,Monsoon', 1, 1),
(13, 'Kitulgala White Water Rafting', 'Exciting river rafting experience with safety gear', 'Adventure', 4.0, '08:00:00', '16:00:00', 3500.00, 5500.00, 2, 8, 'Summer,Winter', 1, 1),
(8, 'Knuckles Overnight Camping', 'Two-day trek with guided camping in mountains', 'Adventure', 12.0, '06:00:00', '18:00:00', 8000.00, 12000.00, 2, 6, 'Summer,Winter', 1, 1),

-- Water Sports
(10, 'Surfing Lessons', 'Learn to surf with experienced instructors', 'Water Sports', 2.0, '07:00:00', '17:00:00', 3000.00, 4500.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(4, 'Kayaking in Bentota River', 'Paddle along scenic river with guide', 'Water Sports', 3.0, '08:00:00', '15:00:00', 2500.00, 4000.00, 1, 8, 'Summer,Winter,Spring', 1, 1),
(10, 'Arugam Bay Surf Adventure', 'Advanced 3-day surf trip with equipment', 'Water Sports', 9.0, '06:00:00', '18:00:00', 12000.00, 20000.00, 2, 6, 'Summer', 1, 1),
(4, 'Mirissa Jet Skiing', 'High-speed jet ski fun on the beach', 'Water Sports', 1.5, '09:00:00', '16:00:00', 4000.00, 6000.00, 1, 5, 'Summer,Winter,Spring', 1, 1),

-- Wildlife
(3, 'Yala Safari', 'Half-day jeep safari to spot leopards and elephants', 'Wildlife', 4.0, '05:30:00', '17:00:00', 4000.00, 6000.00, 2, 6, 'Summer,Winter', 1, 1),
(15, 'Udawalawe Elephant Safari', 'Full-day safari to see wild elephants', 'Wildlife', 5.0, '06:00:00', '17:00:00', 3500.00, 5500.00, 2, 8, 'Summer,Winter', 1, 1),
(9, 'Wilpattu Leopard Safari', 'Spot leopards, birds, and wildlife in Wilpattu', 'Wildlife', 6.0, '05:00:00', '18:00:00', 5000.00, 8000.00, 2, 6, 'Winter,Spring', 1, 1),
(14, 'Minneriya Elephant Gathering', 'Witness mass elephant gathering in Minneriya', 'Wildlife', 4.0, '06:00:00', '18:00:00', 3000.00, 5000.00, 2, 12, 'Summer,Winter', 1, 1),

-- Marine Life
(4, 'Whale Watching', 'Boat tour to see blue whales and dolphins', 'Marine Life', 4.0, '06:00:00', '12:00:00', 5000.00, 8000.00, 4, 30, 'Winter,Spring', 1, 1),
(4, 'Mirissa Dolphin Cruise', 'Morning cruise to spot playful dolphins', 'Marine Life', 3.0, '06:00:00', '11:00:00', 4000.00, 6500.00, 2, 25, 'Winter,Spring', 1, 1),
(14, 'Pigeon Island Snorkeling', 'Snorkel among tropical fish and coral reefs', 'Marine Life', 4.0, '08:00:00', '16:00:00', 5000.00, 7500.00, 2, 15, 'Summer,Winter', 1, 1),
(4, 'Bentota Glass-Bottom Boat', 'See marine life without getting wet', 'Marine Life', 2.0, '07:00:00', '16:00:00', 2500.00, 4000.00, 1, 20, 'Summer,Winter', 1, 1),

-- Sightseeing
(2, 'Nine Arch Bridge Visit', 'Walk to the iconic colonial-era railway bridge', 'Sightseeing', 2.0, '06:00:00', '18:00:00', 500.00, 1000.00, 1, 50, 'Summer,Winter,Monsoon,Spring', 1, 1),
(5, 'Galle Fort Tour', 'Explore UNESCO-listed fort and streets', 'Sightseeing', 3.0, '08:00:00', '17:00:00', 1500.00, 2500.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(6, 'Nuwara Eliya City Tour', 'Visit botanical gardens and Gregory Lake', 'Sightseeing', 4.0, '07:00:00', '16:00:00', 2000.00, 3500.00, 2, 15, 'Summer,Winter,Spring', 1, 1),
(12, 'Jaffna Heritage Walk', 'Visit forts, temples, and local streets', 'Sightseeing', 3.0, '08:00:00', '17:00:00', 2500.00, 4000.00, 2, 15, 'Summer,Winter,Spring', 1, 1),

-- Hiking
(2, 'Little Adams Peak Hike', 'Easy hike with panoramic views of Ella', 'Hiking', 2.5, '05:30:00', '18:00:00', 1000.00, 1500.00, 1, 30, 'Summer,Winter,Spring', 1, 1),
(1, 'Pidurangala Rock Hike', 'Alternative rock climb near Sigiriya', 'Hiking', 3.0, '05:30:00', '17:00:00', 1500.00, 2500.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(8, 'Horton Plains Trail', 'Walk scenic trails to World\'s End and Baker\'s Falls', 'Hiking', 5.0, '06:00:00', '17:00:00', 2000.00, 3500.00, 2, 15, 'Summer,Winter', 1, 1),
(5, 'Ella Rock Hike', 'Moderate hike with panoramic hill country views', 'Hiking', 4.0, '06:00:00', '16:00:00', 1500.00, 3000.00, 1, 12, 'Summer,Winter,Spring', 1, 1),

-- Cultural
(7, 'Kandy Temple of Tooth', 'Visit the sacred Buddhist temple in Kandy', 'Cultural', 2.0, '06:00:00', '18:00:00', 1000.00, 2000.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(12, 'Kandy Perahera Experience', 'Watch traditional dance and elephant procession', 'Cultural', 3.0, '18:00:00', '22:00:00', 1500.00, 2500.00, 1, 15, 'Summer,Winter,Spring', 1, 1),
(12, 'Polonnaruwa Ancient City', 'Explore ruins and temples of Polonnaruwa', 'Cultural', 4.0, '06:00:00', '17:00:00', 2000.00, 3500.00, 2, 12, 'Summer,Winter', 1, 1),
(5, 'Dambulla Cave Temples', 'Visit historic Buddhist cave temples', 'Cultural', 3.0, '06:00:00', '17:00:00', 1500.00, 3000.00, 2, 12, 'Summer,Winter,Spring', 1, 1),

-- Wellness
(6, 'Bentota Yoga Session', 'Morning yoga session by the beach', 'Wellness', 1.5, '06:00:00', '08:00:00', 2000.00, 3500.00, 1, 15, 'Summer,Winter,Spring', 1, 1),
(6, 'Ayurvedic Massage', 'Traditional Ayurvedic full-body massage', 'Wellness', 2.0, '09:00:00', '18:00:00', 3500.00, 6000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(11, 'Dambulla Meditation Retreat', 'Guided meditation and relaxation program', 'Wellness', 3.0, '06:00:00', '12:00:00', 4000.00, 7000.00, 2, 12, 'Summer,Winter,Spring', 1, 1),
(6, 'Bentota Spa Package', 'Full spa day with treatments and meals', 'Wellness', 5.0, '08:00:00', '18:00:00', 12000.00, 20000.00, 1, 8, 'Summer,Winter,Spring', 1, 1),

-- Photography
(2, 'Ella Sunrise Photography', 'Capture sunrise over tea plantations', 'Photography', 2.0, '05:30:00', '08:00:00', 1000.00, 2000.00, 1, 5, 'Summer,Winter,Spring', 1, 1),
(14, 'Trincomalee Coastal Photography', 'Photograph pristine beaches and marine life', 'Photography', 3.0, '06:00:00', '11:00:00', 1200.00, 2500.00, 1, 5, 'Summer,Winter', 1, 1),
(4, 'Mirissa Sunset Shoot', 'Capture sunset views over the beach', 'Photography', 1.5, '16:30:00', '18:30:00', 1500.00, 3000.00, 1, 5, 'Winter,Spring', 1, 1),
(6, 'Nuwara Eliya Landscape Photography', 'Photograph tea estates and waterfalls', 'Photography', 2.5, '06:00:00', '11:00:00', 2000.00, 3500.00, 1, 5, 'Summer,Winter', 1, 1),

-- Food & Dining
(12, 'Jaffna Food Tour', 'Taste traditional Jaffna cuisine and sweets', 'Food & Dining', 3.0, '09:00:00', '14:00:00', 2000.00, 4000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(5, 'Kandy Street Food Walk', 'Sample local snacks and drinks', 'Food & Dining', 2.0, '11:00:00', '14:00:00', 1500.00, 3000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(3, 'Yala Safari Picnic', 'Enjoy local cuisine during safari', 'Food & Dining', 2.0, '12:00:00', '14:00:00', 1200.00, 2000.00, 2, 6, 'Summer,Winter', 1, 1),
(6, 'Bentota Seafood Tasting', 'Fresh seafood platter at beachside restaurant', 'Food & Dining', 2.5, '18:00:00', '20:30:00', 2500.00, 4000.00, 1, 8, 'Summer,Winter', 1, 1);



INSERT INTO tour_schedule (name, tour_id, assume_start_date, assume_end_date, duration_start, duration_end, special_note, description, status, created_by) VALUES

-- Sigiriya Rock Fortress Adventure
('Sigiriya October Schedule', 1, '2025-10-01', '2025-10-31', 1, 1, 'Morning climb recommended', 'Daily departures available', 1, 1),
('Sigiriya November Schedule', 1, '2025-11-01', '2025-11-30', 1, 1, 'Bring water and sunscreen', 'Weekday and weekend departures', 1, 1),
('Sigiriya December Schedule', 1, '2025-12-01', '2025-12-31', 1, 1, 'Sunrise tour highly recommended', 'Daily departures', 1, 1),

-- Cultural Triangle Tour
('Cultural Triangle October Schedule', 2, '2025-10-01', '2025-10-31', 5, 5, 'Guided tour preferred', 'Weekend batches available', 1, 1),
('Cultural Triangle November Schedule', 2, '2025-11-01', '2025-11-30', 5, 5, 'Comfortable shoes recommended', 'Daily departures', 1, 1),
('Cultural Triangle December Schedule', 2, '2025-12-01', '2025-12-31', 5, 5, 'Photography allowed', 'Weekday and weekend tours', 1, 1),

-- Yala Wildlife Safari
('Yala Safari October Schedule', 3, '2025-10-01', '2025-10-31', 2, 2, 'Morning and afternoon safaris', 'Advance booking recommended', 1, 1),
('Yala Safari November Schedule', 3, '2025-11-01', '2025-11-30', 2, 2, 'Peak wildlife sightings', 'Daily departures', 1, 1),
('Yala Safari December Schedule', 3, '2025-12-01', '2025-12-31', 2, 2, 'Bring binoculars', 'Morning safaris preferred', 1, 1),

-- Mirissa Beach Retreat
('Mirissa January Schedule', 4, '2026-01-01', '2026-01-31', 2, 3, 'Best whale watching season', 'Daily departures', 1, 1),
('Mirissa February Schedule', 4, '2026-02-01', '2026-02-28', 2, 3, 'Water sports available', 'Morning and afternoon tours', 1, 1),
('Mirissa March Schedule', 4, '2026-03-01', '2026-03-31', 2, 3, 'Sunset cruise included', 'Daily departures', 1, 1),

-- Ella Hill Country Explorer
('Ella October Schedule', 5, '2025-10-01', '2025-10-31', 3, 3, 'Train journey included', 'Weekend batches available', 1, 1),
('Ella November Schedule', 5, '2025-11-01', '2025-11-30', 3, 3, 'Tea plantation visit included', 'Daily departures', 1, 1),
('Ella December Schedule', 5, '2025-12-01', '2025-12-31', 3, 3, 'Scenic viewpoints included', 'Daily departures', 1, 1),

-- Bentota Spa & Wellness Escape
('Bentota October Schedule', 6, '2025-10-01', '2025-10-31', 3, 3, 'Spa treatments included', 'Daily departures', 1, 1),
('Bentota November Schedule', 6, '2025-11-01', '2025-11-30', 3, 3, 'Yoga sessions included', 'Weekend batches', 1, 1),
('Bentota December Schedule', 6, '2025-12-01', '2025-12-31', 3, 3, 'Beach relaxation included', 'Daily departures', 1, 1),

-- Kandy Temple & Cultural Dance Tour
('Kandy October Schedule', 7, '2025-10-01', '2025-10-31', 2, 2, 'Temple visit and dance show', 'Daily departures', 1, 1),
('Kandy November Schedule', 7, '2025-11-01', '2025-11-30', 2, 2, 'Cultural guide included', 'Weekday tours', 1, 1),
('Kandy December Schedule', 7, '2025-12-01', '2025-12-31', 2, 2, 'Evening performance recommended', 'Daily departures', 1, 1),

-- Knuckles Mountain Hiking
('Knuckles October Schedule', 8, '2025-10-01', '2025-10-31', 2, 2, 'Moderate trek', 'Guided tours available', 1, 1),
('Knuckles November Schedule', 8, '2025-11-01', '2025-11-30', 2, 2, 'Scenic viewpoints included', 'Daily departures', 1, 1),
('Knuckles December Schedule', 8, '2025-12-01', '2025-12-31', 2, 2, 'Bring hiking shoes', 'Weekend batches', 1, 1),

-- Wilpattu Safari Experience
('Wilpattu October Schedule', 9, '2025-10-01', '2025-10-31', 3, 3, 'Luxury camping included', 'Advance booking required', 1, 1),
('Wilpattu November Schedule', 9, '2025-11-01', '2025-11-30', 3, 3, 'Morning and evening safaris', 'Daily departures', 1, 1),
('Wilpattu December Schedule', 9, '2025-12-01', '2025-12-31', 3, 3, 'Guided wildlife tours', 'Daily departures', 1, 1),

-- Arugam Bay Surf Trip
('Arugam Bay October Schedule', 10, '2025-10-01', '2025-10-31', 4, 4, 'Surf lessons included', 'Daily departures', 1, 1),
('Arugam Bay November Schedule', 10, '2025-11-01', '2025-11-30', 4, 4, 'Equipment rental included', 'Weekends preferred', 1, 1),
('Arugam Bay December Schedule', 10, '2025-12-01', '2025-12-31', 4, 4, 'Sunset surfing sessions', 'Daily departures', 1, 1),

-- Ayurvedic Healing Retreat in Dambulla
('Dambulla October Schedule', 11, '2025-10-01', '2025-10-31', 3, 3, 'Ayurvedic treatments included', 'Daily departures', 1, 1),
('Dambulla November Schedule', 11, '2025-11-01', '2025-11-30', 3, 3, 'Meditation sessions included', 'Weekends available', 1, 1),
('Dambulla December Schedule', 11, '2025-12-01', '2025-12-31', 3, 3, 'Herbal meals provided', 'Daily departures', 1, 1),

-- Jaffna Heritage & Food Tour
('Jaffna October Schedule', 12, '2025-10-01', '2025-10-31', 4, 4, 'Temple and fort visits included', 'Daily departures', 1, 1),
('Jaffna November Schedule', 12, '2025-11-01', '2025-11-30', 4, 4, 'Local cuisine tasting', 'Weekends preferred', 1, 1),
('Jaffna December Schedule', 12, '2025-12-01', '2025-12-31', 4, 4, 'Guided cultural tours', 'Daily departures', 1, 1),

-- Kitulgala White Water Rafting
('Kitulgala October Schedule', 13, '2025-10-01', '2025-10-31', 1, 1, 'Safety gear provided', 'Daily departures', 1, 1),
('Kitulgala November Schedule', 13, '2025-11-01', '2025-11-30', 1, 1, 'Guided rafting included', 'Weekdays and weekends', 1, 1),
('Kitulgala December Schedule', 13, '2025-12-01', '2025-12-31', 1, 1, 'Peak water flow season', 'Daily departures', 1, 1),

-- Trincomalee Beach & Snorkeling
('Trincomalee October Schedule', 14, '2025-10-01', '2025-10-31', 3, 3, 'Snorkeling gear provided', 'Daily departures', 1, 1),
('Trincomalee November Schedule', 14, '2025-11-01', '2025-11-30', 3, 3, 'Family-friendly tours', 'Weekends available', 1, 1),
('Trincomalee December Schedule', 14, '2025-12-01', '2025-12-31', 3, 3, 'Coral reef visit included', 'Daily departures', 1, 1),

-- Udawalawe Elephant Safari
('Udawalawe October Schedule', 15, '2025-10-01', '2025-10-31', 1, 1, 'Elephant sightings expected', 'Morning safaris', 1, 1),
('Udawalawe November Schedule', 15, '2025-11-01', '2025-11-30', 1, 1, 'Guided safari included', 'Daily departures', 1, 1),
('Udawalawe December Schedule', 15, '2025-12-01', '2025-12-31', 1, 1, 'Afternoon safari recommended', 'Daily departures', 1, 1);



INSERT INTO activities_schedule (name, activity_id, assume_start_date, assume_end_date, duration_hours_start, duration_hours_end, special_note, description, status, created_by) VALUES

-- Adventure Activities
('Sigiriya Morning Climb', 1, '2025-10-01', '2025-12-31', 3.0, 4.0, 'Start early to avoid heat', 'Daily morning slots', 1, 1),
('Sigiriya Evening Climb', 1, '2025-10-01', '2025-12-31', 3.0, 4.0, 'Sunset climb recommended', 'Evening departures available', 1, 1),
('Sigiriya Weekend Climb', 1, '2025-10-01', '2025-12-31', 3.5, 4.5, 'Weekend guided climb', 'Limited slots', 1, 1),

('Knuckles Morning Trek', 2, '2025-10-01', '2025-11-30', 5.0, 6.0, 'Bring water and snacks', 'Daily morning departures', 1, 1),
('Knuckles Sunset Trek', 2, '2025-10-01', '2025-11-30', 5.0, 6.0, 'Sunset scenic points included', 'Evening departures', 1, 1),
('Knuckles Weekend Trek', 2, '2025-10-01', '2025-11-30', 6.0, 7.0, 'Overnight camping option', 'Weekend batches', 1, 1),

('Kitulgala Morning Rafting', 3, '2025-10-01', '2025-12-31', 3.5, 4.0, 'Safety gear provided', 'Daily departures', 1, 1),
('Kitulgala Afternoon Rafting', 3, '2025-10-01', '2025-12-31', 3.5, 4.0, 'Guided rafting included', 'Afternoon sessions', 1, 1),
('Kitulgala Weekend Rafting', 3, '2025-10-01', '2025-12-31', 4.0, 4.5, 'Peak water flow season', 'Weekend slots only', 1, 1),

-- Water Sports
('Surfing Lessons Morning', 4, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Beginner friendly', 'Morning sessions', 1, 1),
('Surfing Lessons Afternoon', 4, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Intermediate level', 'Afternoon sessions', 1, 1),
('Surfing Weekend Camp', 4, '2025-10-01', '2026-03-31', 4.0, 5.0, '3-day surf camp', 'Weekend batches', 1, 1),

('Kayaking Morning', 5, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Calm water sections', 'Morning departures', 1, 1),
('Kayaking Afternoon', 5, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Scenic route', 'Afternoon departures', 1, 1),
('Kayaking Weekend', 5, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Guided tour', 'Weekend batches', 1, 1),

-- Wildlife
('Yala Morning Safari', 6, '2025-10-01', '2026-02-28', 3.5, 4.5, 'Higher chance of leopard sightings', 'Early morning departure', 1, 1),
('Yala Afternoon Safari', 6, '2025-10-01', '2026-02-28', 3.5, 4.5, 'Sunset wildlife tour', 'Afternoon departures', 1, 1),
('Yala Weekend Safari', 6, '2025-10-01', '2026-02-28', 4.0, 5.0, 'Guided safari', 'Weekend batches', 1, 1),

('Udawalawe Morning Safari', 7, '2025-10-01', '2026-02-28', 4.5, 5.0, 'Elephant sightings expected', 'Morning slots', 1, 1),
('Udawalawe Afternoon Safari', 7, '2025-10-01', '2026-02-28', 4.5, 5.0, 'Sunset safari included', 'Afternoon slots', 1, 1),
('Udawalawe Weekend Safari', 7, '2025-10-01', '2026-02-28', 5.0, 6.0, 'Guided safari', 'Weekend departures', 1, 1),

-- Marine Life
('Whale Watching Morning', 8, '2025-11-01', '2026-04-30', 3.5, 4.5, 'Best sighting months', 'Morning departures', 1, 1),
('Whale Watching Afternoon', 8, '2025-11-01', '2026-04-30', 3.5, 4.5, 'Afternoon cruises', 'Limited slots', 1, 1),
('Whale Watching Weekend', 8, '2025-11-01', '2026-04-30', 4.0, 5.0, 'Guided tours', 'Weekend departures', 1, 1),

-- Sightseeing
('Nine Arch Bridge Morning Walk', 9, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Best during train passing', 'Morning sessions', 1, 1),
('Nine Arch Bridge Afternoon Walk', 9, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Scenic photography', 'Afternoon sessions', 1, 1),
('Nine Arch Bridge Weekend Walk', 9, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Guided tours', 'Weekend batches', 1, 1),

-- Hiking
('Little Adams Peak Morning Hike', 10, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Panoramic views', 'Morning slots', 1, 1),
('Little Adams Peak Afternoon Hike', 10, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Sunset view included', 'Afternoon slots', 1, 1),
('Little Adams Peak Weekend Hike', 10, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Guided hike', 'Weekend batches', 1, 1),

-- Cultural
('Kandy Temple Morning Visit', 11, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Temple visit with guide', 'Morning slots', 1, 1),
('Kandy Temple Afternoon Visit', 11, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Cultural show included', 'Afternoon slots', 1, 1),
('Kandy Temple Weekend Visit', 11, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Guided cultural experience', 'Weekend batches', 1, 1),

-- Wellness
('Bentota Yoga Morning', 12, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Sunrise session', 'Morning slots', 1, 1),
('Bentota Yoga Afternoon', 12, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Beach yoga included', 'Afternoon slots', 1, 1),
('Bentota Weekend Yoga Retreat', 12, '2025-10-01', '2026-03-31', 2.0, 3.0, 'Meditation and relaxation', 'Weekend retreats', 1, 1),

-- Photography
('Ella Sunrise Photography Morning', 13, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Capture sunrise views', 'Morning slots', 1, 1),
('Ella Sunrise Photography Afternoon', 13, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Golden hour included', 'Afternoon slots', 1, 1),
('Ella Sunrise Photography Weekend', 13, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Guided photography tour', 'Weekend batches', 1, 1),

-- Food & Dining
('Jaffna Food Tour Morning', 14, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Traditional dishes', 'Morning slots', 1, 1),
('Jaffna Food Tour Afternoon', 14, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Cooking demo included', 'Afternoon slots', 1, 1),
('Jaffna Food Tour Weekend', 14, '2025-10-01', '2026-03-31', 3.5, 4.0, 'Guided tasting', 'Weekend batches', 1, 1);


























