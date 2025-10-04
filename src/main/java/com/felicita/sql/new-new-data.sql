INSERT INTO seasons (name, description, status, created_by) VALUES
('Spring', 'Mild weather, flowers bloom, popular for outdoor activities', 1, 1),
('Summer', 'Hot and sunny season, ideal for beach activities, hiking, and festivals', 1, 1),
('Autumn', 'Cooler weather, leaves change color, great for scenic tours', 1, 1),
('Winter', 'Cold season, snow in some regions, perfect for skiing and mountain tours', 1, 1),
('Dry Season', 'Sunny and dry, best time for safaris, island travel, and outdoor adventures', 1, 1),
('Wet Season', 'Heavy rains and lush landscapes, waterfalls at peak beauty', 1, 1),
('Monsoon', 'Characterized by seasonal rainfall, cultural festivals, and rich greenery', 1, 1),
('Cool Season', 'Comfortable temperatures in tropical regions, ideal for cultural and city tours', 1, 1),
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


INSERT INTO tour_type (name, description, status, created_by) VALUES
('Adventure', 'Thrilling outdoor activities and experiences', 1, 1),
('Cultural', 'Explore heritage sites and local traditions', 1, 1),
('Wildlife', 'Safari and nature exploration tours', 1, 1),
('Beach', 'Coastal and water-based activities', 1, 1),
('Wellness', 'Spa, yoga, and relaxation focused tours', 1, 1);


INSERT INTO tour_category (name, description, status, created_by) VALUES
('Luxury', 'Premium experiences with high-end accommodations', 1, 1),
('Budget', 'Affordable tours for budget travelers', 1, 1),
('Family', 'Family-friendly tours suitable for all ages', 1, 1),
('Solo', 'Perfect for solo travelers and backpackers', 1, 1),
('Group', 'Large group tours with shared experiences', 1, 1);


INSERT INTO package_type (name, description, status, created_by) VALUES
('All-Inclusive', 'Everything included – accommodation, meals, drinks, transport, and activities', 1, 1),
('Full-Board', 'Accommodation with breakfast, lunch, and dinner included', 1, 1),
('Half-Board', 'Accommodation with breakfast and either lunch or dinner included', 1, 1),
('Bed & Breakfast', 'Accommodation with breakfast included only', 1, 1),
('Day Trip', 'Single day excursion packages including activities and meals', 1, 1),
('Weekend Getaway', 'Short stay package ideal for weekends or holidays', 1, 1),
('Extended Stay', 'Longer duration package with discounted rates', 1, 1),
('Custom', 'Fully customizable package based on traveler preferences', 1, 1),
('Adventure', 'Adventure-focused package including hiking, safaris, and outdoor activities', 1, 1),
('Luxury', 'Premium package with 5-star accommodation, private transport, and exclusive services', 1, 1),
('Budget', 'Affordable travel package with basic amenities for cost-conscious travelers', 1, 1),
('Honeymoon', 'Romantic package designed for couples with special activities and services', 1, 1),
('Family', 'Family-friendly package with kid-friendly activities and accommodations', 1, 1);



INSERT INTO tour 
(name, description, tour_type, tour_category, duration, latitude, longitude, start_location, end_location, season, status, created_by) 
VALUES
('Sigiriya Rock Fortress Adventure', 'Climb the ancient rock fortress and explore UNESCO heritage site', 1, 4, 1, 7.9570, 80.7603, 'Colombo', 'Sigiriya', 1, 1, 1),
('Cultural Triangle Tour', 'Visit ancient cities: Anuradhapura, Polonnaruwa, Sigiriya', 2, 2, 5, 8.3114, 80.4037, 'Colombo', 'Anuradhapura', 2, 1, 1),
('Yala Wildlife Safari', 'Spot leopards, elephants and diverse wildlife in national park', 3, 3, 2, 6.3725, 81.5194, 'Tissamaharama', 'Yala', 2, 1, 1),
('Mirissa Beach Retreat', 'Relax on pristine beaches and enjoy whale watching', 4, 5, 2, 5.9450, 80.4489, 'Galle', 'Mirissa', 1, 1, 1),
('Ella Hill Country Explorer', 'Scenic train journey through tea plantations and waterfalls', 1, 3, 3, 6.8667, 81.0467, 'Kandy', 'Ella', 4, 1, 1),
('Bentota Spa & Wellness Escape', 'Rejuvenate with spa treatments, yoga, and beachside relaxation', 5, 1, 3, 6.4210, 80.0050, 'Colombo', 'Bentota', 1, 1, 1),
('Kandy Temple & Cultural Dance Tour', 'Experience the Temple of the Tooth and traditional dance shows', 2, 3, 2, 7.2906, 80.6337, 'Kandy', 'Kandy', 4, 1, 1),
('Knuckles Mountain Hiking', 'Trek through Knuckles mountain range with scenic landscapes', 1, 5, 2, 7.3898, 80.7840, 'Kandy', 'Knuckles Range', 3, 1, 1),
('Wilpattu Safari Experience', 'Explore Sri Lanka’s largest national park with luxury camping', 3, 1, 3, 8.4590, 80.1000, 'Puttalam', 'Wilpattu', 2, 1, 1),
('Arugam Bay Surf Trip', 'World-class surfing destination popular with backpackers', 4, 4, 4, 6.8390, 81.8340, 'Colombo', 'Arugam Bay', 1, 1, 1),
('Ayurvedic Healing Retreat in Dambulla', 'Traditional Ayurveda treatments and meditation programs', 5, 5, 3, 7.8570, 80.6510, 'Colombo', 'Dambulla', 4, 1, 1),
('Jaffna Heritage & Food Tour', 'Discover Jaffna’s temples, forts, and unique cuisine', 2, 1, 4, 9.6685, 80.0074, 'Colombo', 'Jaffna', 2, 1, 1),
('Kitulgala White Water Rafting', 'Thrilling rafting experience on Kelani River', 1, 2, 1, 6.9886, 80.4250, 'Colombo', 'Kitulgala', 1, 1, 1),
('Trincomalee Beach & Snorkeling', 'Family-friendly beach stay with snorkeling at Pigeon Island', 4, 3, 3, 8.5711, 81.2335, 'Colombo', 'Trincomalee', 1, 1, 1),
('Udawalawe Elephant Safari', 'Jeep safari to see large herds of elephants in the wild', 3, 4, 1, 6.4267, 80.8987, 'Colombo', 'Udawalawe', 2, 1, 1);


INSERT INTO packages 
(tour_id, name, description, total_price, discount_percentage, start_date, end_date, color, status, hover_color, min_person_count, max_person_count, price_per_person, created_by) 
VALUES
(1, 'Sigiriya Day Package', 'Full day tour with guide and transport', 15000.00, 10.00, '2025-10-01', '2026-03-31', '#FF6B6B', 1, '#FF5252', 2, 8, 7500.00, 1),
(1, 'Sigiriya Sunrise Hike', 'Morning hike with breakfast and cultural guide', 18000.00, 12.00, '2025-11-01', '2026-04-30', '#FFA07A', 1, '#FF8C69', 2, 6, 9000.00, 1),
(2, 'Cultural Triangle 5-Day', 'Complete cultural experience with expert guide', 75000.00, 12.00, '2025-10-01', '2026-05-31', '#AA96DA', 1, '#9580D0', 4, 12, 18750.00, 1),
(2, 'Triangle Express 3-Day', 'Quick exploration of Anuradhapura, Polonnaruwa, and Sigiriya', 52000.00, 8.00, '2025-10-01', '2026-03-31', '#9370DB', 1, '#836FFF', 2, 10, 17333.00, 1),
(3, 'Yala Safari Experience', 'Two-day safari with luxury camping', 35000.00, 5.00, '2025-11-01', '2026-02-28', '#95E1D3', 1, '#7DD4C5', 2, 10, 17500.00, 1),
(3, 'Yala Premium Jeep Safari', 'Private jeep with naturalist guide', 42000.00, 10.00, '2025-11-01', '2026-05-15', '#20B2AA', 1, '#2E8B57', 2, 6, 21000.00, 1),
(4, 'Mirissa Beach Escape', 'Relaxing beach holiday with water sports', 40000.00, 20.00, '2025-10-15', '2026-03-15', '#F38181', 1, '#F06868', 2, 4, 20000.00, 1),
(4, 'Whale Watching Adventure', 'Half-day boat trip with breakfast included', 18000.00, 15.00, '2025-11-01', '2026-04-30', '#FFB6C1', 1, '#FF69B4', 2, 10, 9000.00, 1),
(5, 'Ella 3-Day Adventure', 'Three days exploring Ella with accommodation', 45000.00, 15.00, '2025-10-01', '2026-04-30', '#4ECDC4', 1, '#3BB8B0', 2, 6, 22500.00, 1),
(5, 'Ella Scenic Train & Tea Tour', '2-day package including train and tea plantation visits', 28000.00, 10.00, '2025-11-01', '2026-05-31', '#48D1CC', 1, '#40E0D0', 2, 6, 14000.00, 1),
(6, 'Bentota Spa Weekend', '2-night stay with massages and yoga sessions', 60000.00, 15.00, '2025-10-01', '2026-06-30', '#FFD700', 1, '#FFC107', 2, 4, 30000.00, 1),
(6, 'Luxury Wellness Retreat', '5-day retreat with spa, meditation, and Ayurveda meals', 125000.00, 20.00, '2025-11-01', '2026-05-31', '#DAA520', 1, '#B8860B', 2, 6, 62500.00, 1),
(7, 'Kandy Culture Half-Day', 'Temple visit and evening dance show', 12000.00, 10.00, '2025-10-01', '2026-06-30', '#BA55D3', 1, '#9932CC', 2, 12, 6000.00, 1),
(7, 'Kandy Full Experience', 'City tour including temple, dance, and botanical gardens', 22000.00, 15.00, '2025-11-01', '2026-04-30', '#8A2BE2', 1, '#6A5ACD', 2, 10, 11000.00, 1),
(8, 'Knuckles Day Hike', 'One-day trek with guide and meals', 18000.00, 10.00, '2025-10-01', '2026-05-31', '#32CD32', 1, '#228B22', 2, 8, 9000.00, 1),
(8, 'Knuckles Camping Adventure', '2-day trek with overnight camping', 32000.00, 12.00, '2025-11-01', '2026-04-30', '#006400', 1, '#2E8B57', 2, 6, 16000.00, 1),
(9, 'Wilpattu Luxury Safari', '3-day safari with luxury lodge stay', 95000.00, 18.00, '2025-10-15', '2026-05-31', '#8B4513', 1, '#A0522D', 2, 6, 47500.00, 1),
(9, 'Wilpattu Classic Safari', '2-day safari with tented camping', 55000.00, 10.00, '2025-11-01', '2026-04-30', '#D2691E', 1, '#CD853F', 2, 8, 27500.00, 1),
(10, 'Surf Beginner Package', '3-day surfing lessons with equipment', 38000.00, 10.00, '2025-05-01', '2025-09-30', '#00CED1', 1, '#20B2AA', 2, 6, 19000.00, 1),
(10, 'Surf Pro Adventure', '5-day advanced surfing experience with beach parties', 68000.00, 12.00, '2025-05-01', '2025-09-30', '#1E90FF', 1, '#4169E1', 2, 8, 34000.00, 1),
(11, 'Ayurveda Short Retreat', '3-day healing and detox program', 72000.00, 15.00, '2025-11-01', '2026-05-31', '#FF8C00', 1, '#FF7F50', 2, 6, 36000.00, 1),
(11, 'Deep Healing Package', '7-day full Ayurveda program with meals and treatments', 160000.00, 20.00, '2025-11-01', '2026-05-31', '#FF6347', 1, '#FF4500', 2, 10, 80000.00, 1),
(12, 'Jaffna 2-Day Heritage', 'Explore temples, forts, and taste authentic cuisine', 45000.00, 10.00, '2025-10-01', '2026-06-30', '#FF1493', 1, '#DB7093', 2, 10, 22500.00, 1),
(12, 'Jaffna Extended 4-Day', 'Deeper cultural and culinary exploration', 85000.00, 12.00, '2025-10-01', '2026-06-30', '#C71585', 1, '#8B008B', 2, 8, 42500.00, 1),
(13, 'Rafting Day Package', 'Half-day white water rafting experience', 18000.00, 10.00, '2025-10-01', '2026-06-30', '#40E0D0', 1, '#48D1CC', 2, 10, 9000.00, 1),
(13, 'Rafting + Canyoning Combo', 'Adventure combo with rafting and canyoning', 32000.00, 15.00, '2025-11-01', '2026-05-31', '#4682B4', 1, '#5F9EA0', 2, 8, 16000.00, 1),
(14, 'Snorkeling Day Trip', 'Pigeon Island snorkeling with guide and boat transfer', 22000.00, 10.00, '2025-05-01', '2025-09-30', '#00FA9A', 1, '#00FF7F', 2, 12, 11000.00, 1),
(14, 'Trinco Beach Stay 3-Day', 'Beach resort with snorkeling and dolphin watching', 60000.00, 15.00, '2025-05-01', '2025-09-30', '#3CB371', 1, '#2E8B57', 2, 8, 30000.00, 1),
(15, 'Elephant Day Safari', 'Full-day jeep safari in Udawalawe', 20000.00, 8.00, '2025-11-01', '2026-05-31', '#B22222', 1, '#DC143C', 2, 12, 10000.00, 1),
(15, 'Udawalawe Overnight Safari', '2-day experience with camping near park', 42000.00, 12.00, '2025-11-01', '2026-05-31', '#CD5C5C', 1, '#8B0000', 2, 6, 21000.00, 1);


INSERT INTO destination_categories (category, description, status, created_by) VALUES
('Adventure', 'Thrilling activities such as hiking, rafting, and outdoor challenges', 1, 1),
('Wildlife', 'National parks and safaris to experience animals in their natural habitat', 1, 1),
('Cultural & Heritage', 'Cultural festivals, temples, and heritage sites', 1, 1),
('Beach', 'Coastal destinations, surfing spots, and beach resorts', 1, 1),
('Hill Country', 'Scenic highlands with tea estates, waterfalls, and train journeys', 1, 1);


INSERT INTO destination (name, description, status, destination_category, location, latitude, longitude, created_by) VALUES
('Kitulgala', 'Famous for white-water rafting and jungle adventures', 1, 1, 'Sabaragamuwa Province', 6.9886, 80.4250, 1),
('Horton Plains', 'Hiking trails with stunning landscapes and World End view', 1, 1, 'Central Province', 6.8026, 80.7991, 1),
('Knuckles Mountain Range', 'Trekking and camping in scenic mountain ranges', 1, 1, 'Central Province', 7.3898, 80.7840, 1),
('Adams Peak', 'Pilgrimage and trekking adventure with panoramic sunrise', 1, 1, 'Sabaragamuwa Province', 6.8090, 80.4990, 1),
('Sinharaja Forest', 'Rainforest trekking with waterfalls and wildlife spotting', 1, 1, 'Southern Province', 6.4020, 80.4020, 1),
('Yala National Park', 'Most visited and second largest national park', 1, 2, 'Southern Province', 6.3725, 81.5194, 1),
('Udawalawe National Park', 'Famous for large herds of elephants and safari experiences', 1, 2, 'Southern Province', 6.4267, 80.8987, 1),
('Wilpattu National Park', 'Largest national park, known for leopards', 1, 2, 'North Western Province', 8.4590, 80.1000, 1),
('Minneriya National Park', 'Famous for elephant gatherings during dry season', 1, 2, 'North Central Province', 8.0030, 80.9360, 1),
('Bundala National Park', 'Coastal wetlands with migratory birds and wildlife', 1, 2, 'Southern Province', 5.9910, 80.5900, 1),
('Sigiriya', 'Ancient rock fortress and UNESCO World Heritage site', 1, 3, 'Central Province', 7.9570, 80.7603, 1),
('Anuradhapura', 'Ancient capital with sacred Buddhist sites', 1, 3, 'North Central Province', 8.3114, 80.4037, 1),
('Polonnaruwa', 'Historical city with ancient ruins and temples', 1, 3, 'North Central Province', 7.9400, 81.0000, 1),
('Kandy', 'Cultural capital with Temple of the Tooth and Perahera festival', 1, 3, 'Central Province', 7.2906, 80.6337, 1),
('Galle Fort', 'UNESCO-listed fort with Dutch architecture and cultural sites', 1, 3, 'Southern Province', 6.0320, 80.2170, 1),
('Mirissa', 'Beautiful beach town famous for whale watching', 1, 4, 'Southern Province', 5.9450, 80.4489, 1),
('Unawatuna', 'Popular beach with water sports and nightlife', 1, 4, 'Southern Province', 5.9445, 80.2580, 1),
('Bentota', 'Resort town with beaches, water sports, and river tours', 1, 4, 'Western Province', 6.4210, 80.0050, 1),
('Trincomalee', 'East coast beach with snorkeling and diving', 1, 4, 'Eastern Province', 8.5711, 81.2335, 1),
('Arugam Bay', 'World-renowned surfing destination', 1, 4, 'Eastern Province', 6.8390, 81.8340, 1),
('Ella', 'Picturesque hill country town with stunning views', 1, 5, 'Uva Province', 6.8667, 81.0467, 1),
('Nuwara Eliya', 'Cool climate hill station with tea plantations', 1, 5, 'Central Province', 6.9497, 80.7891, 1),
('Haputale', 'Hill town with tea estates and breathtaking viewpoints', 1, 5, 'Uva Province', 6.7790, 80.9990, 1),
('Nuwara Eliya Hakgala', 'Tea gardens and botanical garden exploration', 1, 5, 'Central Province', 6.9000, 80.7830, 1),
('Dambatenne', 'Scenic tea estate in central highlands', 1, 5, 'Central Province', 6.8450, 80.9430, 1);




INSERT INTO activities 
(destination_id, name, description, activities_category, duration_hours, available_from, available_to, price_local, price_foreigners, min_participate, max_participate, season, status, created_by) 
VALUES
(1, 'Sigiriya Rock Climb', 'Climb the 1200 steps to the top of Sigiriya Rock Fortress', 'Adventure', 3.5, '06:00:00', '17:00:00', 3000.00, 5000.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(8, 'Knuckles Mountain Trek', 'Full-day trekking adventure with guide in Knuckles Range', 'Adventure', 6.0, '05:30:00', '17:00:00', 4000.00, 6500.00, 2, 10, 'Summer,Winter,Monsoon', 1, 1),
(13, 'Kitulgala White Water Rafting', 'Exciting river rafting experience with safety gear', 'Adventure', 4.0, '08:00:00', '16:00:00', 3500.00, 5500.00, 2, 8, 'Summer,Winter', 1, 1),
(8, 'Knuckles Overnight Camping', 'Two-day trek with guided camping in mountains', 'Adventure', 12.0, '06:00:00', '18:00:00', 8000.00, 12000.00, 2, 6, 'Summer,Winter', 1, 1),
(10, 'Surfing Lessons', 'Learn to surf with experienced instructors', 'Water Sports', 2.0, '07:00:00', '17:00:00', 3000.00, 4500.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(4, 'Kayaking in Bentota River', 'Paddle along scenic river with guide', 'Water Sports', 3.0, '08:00:00', '15:00:00', 2500.00, 4000.00, 1, 8, 'Summer,Winter,Spring', 1, 1),
(10, 'Arugam Bay Surf Adventure', 'Advanced 3-day surf trip with equipment', 'Water Sports', 9.0, '06:00:00', '18:00:00', 12000.00, 20000.00, 2, 6, 'Summer', 1, 1),
(4, 'Mirissa Jet Skiing', 'High-speed jet ski fun on the beach', 'Water Sports', 1.5, '09:00:00', '16:00:00', 4000.00, 6000.00, 1, 5, 'Summer,Winter,Spring', 1, 1),
(3, 'Yala Safari', 'Half-day jeep safari to spot leopards and elephants', 'Wildlife', 4.0, '05:30:00', '17:00:00', 4000.00, 6000.00, 2, 6, 'Summer,Winter', 1, 1),
(15, 'Udawalawe Elephant Safari', 'Full-day safari to see wild elephants', 'Wildlife', 5.0, '06:00:00', '17:00:00', 3500.00, 5500.00, 2, 8, 'Summer,Winter', 1, 1),
(9, 'Wilpattu Leopard Safari', 'Spot leopards, birds, and wildlife in Wilpattu', 'Wildlife', 6.0, '05:00:00', '18:00:00', 5000.00, 8000.00, 2, 6, 'Winter,Spring', 1, 1),
(14, 'Minneriya Elephant Gathering', 'Witness mass elephant gathering in Minneriya', 'Wildlife', 4.0, '06:00:00', '18:00:00', 3000.00, 5000.00, 2, 12, 'Summer,Winter', 1, 1),
(4, 'Whale Watching', 'Boat tour to see blue whales and dolphins', 'Marine Life', 4.0, '06:00:00', '12:00:00', 5000.00, 8000.00, 4, 30, 'Winter,Spring', 1, 1),
(4, 'Mirissa Dolphin Cruise', 'Morning cruise to spot playful dolphins', 'Marine Life', 3.0, '06:00:00', '11:00:00', 4000.00, 6500.00, 2, 25, 'Winter,Spring', 1, 1),
(14, 'Pigeon Island Snorkeling', 'Snorkel among tropical fish and coral reefs', 'Marine Life', 4.0, '08:00:00', '16:00:00', 5000.00, 7500.00, 2, 15, 'Summer,Winter', 1, 1),
(4, 'Bentota Glass-Bottom Boat', 'See marine life without getting wet', 'Marine Life', 2.0, '07:00:00', '16:00:00', 2500.00, 4000.00, 1, 20, 'Summer,Winter', 1, 1),
(2, 'Nine Arch Bridge Visit', 'Walk to the iconic colonial-era railway bridge', 'Sightseeing', 2.0, '06:00:00', '18:00:00', 500.00, 1000.00, 1, 50, 'Summer,Winter,Monsoon,Spring', 1, 1),
(5, 'Galle Fort Tour', 'Explore UNESCO-listed fort and streets', 'Sightseeing', 3.0, '08:00:00', '17:00:00', 1500.00, 2500.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(6, 'Nuwara Eliya City Tour', 'Visit botanical gardens and Gregory Lake', 'Sightseeing', 4.0, '07:00:00', '16:00:00', 2000.00, 3500.00, 2, 15, 'Summer,Winter,Spring', 1, 1),
(12, 'Jaffna Heritage Walk', 'Visit forts, temples, and local streets', 'Sightseeing', 3.0, '08:00:00', '17:00:00', 2500.00, 4000.00, 2, 15, 'Summer,Winter,Spring', 1, 1),
(2, 'Little Adams Peak Hike', 'Easy hike with panoramic views of Ella', 'Hiking', 2.5, '05:30:00', '18:00:00', 1000.00, 1500.00, 1, 30, 'Summer,Winter,Spring', 1, 1),
(1, 'Pidurangala Rock Hike', 'Alternative rock climb near Sigiriya', 'Hiking', 3.0, '05:30:00', '17:00:00', 1500.00, 2500.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(8, 'Horton Plains Trail', 'Walk scenic trails to World\'s End and Baker\'s Falls', 'Hiking', 5.0, '06:00:00', '17:00:00', 2000.00, 3500.00, 2, 15, 'Summer,Winter', 1, 1),
(5, 'Ella Rock Hike', 'Moderate hike with panoramic hill country views', 'Hiking', 4.0, '06:00:00', '16:00:00', 1500.00, 3000.00, 1, 12, 'Summer,Winter,Spring', 1, 1),
(7, 'Kandy Temple of Tooth', 'Visit the sacred Buddhist temple in Kandy', 'Cultural', 2.0, '06:00:00', '18:00:00', 1000.00, 2000.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(12, 'Kandy Perahera Experience', 'Watch traditional dance and elephant procession', 'Cultural', 3.0, '18:00:00', '22:00:00', 1500.00, 2500.00, 1, 15, 'Summer,Winter,Spring', 1, 1),
(12, 'Polonnaruwa Ancient City', 'Explore ruins and temples of Polonnaruwa', 'Cultural', 4.0, '06:00:00', '17:00:00', 2000.00, 3500.00, 2, 12, 'Summer,Winter', 1, 1),
(5, 'Dambulla Cave Temples', 'Visit historic Buddhist cave temples', 'Cultural', 3.0, '06:00:00', '17:00:00', 1500.00, 3000.00, 2, 12, 'Summer,Winter,Spring', 1, 1),
(6, 'Bentota Yoga Session', 'Morning yoga session by the beach', 'Wellness', 1.5, '06:00:00', '08:00:00', 2000.00, 3500.00, 1, 15, 'Summer,Winter,Spring', 1, 1),
(6, 'Ayurvedic Massage', 'Traditional Ayurvedic full-body massage', 'Wellness', 2.0, '09:00:00', '18:00:00', 3500.00, 6000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(11, 'Dambulla Meditation Retreat', 'Guided meditation and relaxation program', 'Wellness', 3.0, '06:00:00', '12:00:00', 4000.00, 7000.00, 2, 12, 'Summer,Winter,Spring', 1, 1),
(6, 'Bentota Spa Package', 'Full spa day with treatments and meals', 'Wellness', 5.0, '08:00:00', '18:00:00', 12000.00, 20000.00, 1, 8, 'Summer,Winter,Spring', 1, 1),
(2, 'Ella Sunrise Photography', 'Capture sunrise over tea plantations', 'Photography', 2.0, '05:30:00', '08:00:00', 1000.00, 2000.00, 1, 5, 'Summer,Winter,Spring', 1, 1),
(14, 'Trincomalee Coastal Photography', 'Photograph pristine beaches and marine life', 'Photography', 3.0, '06:00:00', '11:00:00', 1200.00, 2500.00, 1, 5, 'Summer,Winter', 1, 1),
(4, 'Mirissa Sunset Shoot', 'Capture sunset views over the beach', 'Photography', 1.5, '16:30:00', '18:30:00', 1500.00, 3000.00, 1, 5, 'Winter,Spring', 1, 1),
(6, 'Nuwara Eliya Landscape Photography', 'Photograph tea estates and waterfalls', 'Photography', 2.5, '06:00:00', '11:00:00', 2000.00, 3500.00, 1, 5, 'Summer,Winter', 1, 1),
(12, 'Jaffna Food Tour', 'Taste traditional Jaffna cuisine and sweets', 'Food & Dining', 3.0, '09:00:00', '14:00:00', 2000.00, 4000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(5, 'Kandy Street Food Walk', 'Sample local snacks and drinks', 'Food & Dining', 2.0, '11:00:00', '14:00:00', 1500.00, 3000.00, 1, 10, 'Summer,Winter,Spring', 1, 1),
(3, 'Yala Safari Picnic', 'Enjoy local cuisine during safari', 'Food & Dining', 2.0, '12:00:00', '14:00:00', 1200.00, 2000.00, 2, 6, 'Summer,Winter', 1, 1),
(6, 'Bentota Seafood Tasting', 'Fresh seafood platter at beachside restaurant', 'Food & Dining', 2.5, '18:00:00', '20:30:00', 2500.00, 4000.00, 1, 8, 'Summer,Winter', 1, 1);



INSERT INTO tour_schedule (name, tour_id, assume_start_date, assume_end_date, duration_start, duration_end, special_note, description, status, created_by) VALUES
('Sigiriya October Schedule', 1, '2025-10-01', '2025-10-31', 1, 1, 'Morning climb recommended', 'Daily departures available', 1, 1),
('Sigiriya November Schedule', 1, '2025-11-01', '2025-11-30', 1, 1, 'Bring water and sunscreen', 'Weekday and weekend departures', 1, 1),
('Sigiriya December Schedule', 1, '2025-12-01', '2025-12-31', 1, 1, 'Sunrise tour highly recommended', 'Daily departures', 1, 1),
('Cultural Triangle October Schedule', 2, '2025-10-01', '2025-10-31', 5, 5, 'Guided tour preferred', 'Weekend batches available', 1, 1),
('Cultural Triangle November Schedule', 2, '2025-11-01', '2025-11-30', 5, 5, 'Comfortable shoes recommended', 'Daily departures', 1, 1),
('Cultural Triangle December Schedule', 2, '2025-12-01', '2025-12-31', 5, 5, 'Photography allowed', 'Weekday and weekend tours', 1, 1),
('Yala Safari October Schedule', 3, '2025-10-01', '2025-10-31', 2, 2, 'Morning and afternoon safaris', 'Advance booking recommended', 1, 1),
('Yala Safari November Schedule', 3, '2025-11-01', '2025-11-30', 2, 2, 'Peak wildlife sightings', 'Daily departures', 1, 1),
('Yala Safari December Schedule', 3, '2025-12-01', '2025-12-31', 2, 2, 'Bring binoculars', 'Morning safaris preferred', 1, 1),
('Mirissa January Schedule', 4, '2026-01-01', '2026-01-31', 2, 3, 'Best whale watching season', 'Daily departures', 1, 1),
('Mirissa February Schedule', 4, '2026-02-01', '2026-02-28', 2, 3, 'Water sports available', 'Morning and afternoon tours', 1, 1),
('Mirissa March Schedule', 4, '2026-03-01', '2026-03-31', 2, 3, 'Sunset cruise included', 'Daily departures', 1, 1),
('Ella October Schedule', 5, '2025-10-01', '2025-10-31', 3, 3, 'Train journey included', 'Weekend batches available', 1, 1),
('Ella November Schedule', 5, '2025-11-01', '2025-11-30', 3, 3, 'Tea plantation visit included', 'Daily departures', 1, 1),
('Ella December Schedule', 5, '2025-12-01', '2025-12-31', 3, 3, 'Scenic viewpoints included', 'Daily departures', 1, 1),
('Bentota October Schedule', 6, '2025-10-01', '2025-10-31', 3, 3, 'Spa treatments included', 'Daily departures', 1, 1),
('Bentota November Schedule', 6, '2025-11-01', '2025-11-30', 3, 3, 'Yoga sessions included', 'Weekend batches', 1, 1),
('Bentota December Schedule', 6, '2025-12-01', '2025-12-31', 3, 3, 'Beach relaxation included', 'Daily departures', 1, 1),
('Kandy October Schedule', 7, '2025-10-01', '2025-10-31', 2, 2, 'Temple visit and dance show', 'Daily departures', 1, 1),
('Kandy November Schedule', 7, '2025-11-01', '2025-11-30', 2, 2, 'Cultural guide included', 'Weekday tours', 1, 1),
('Kandy December Schedule', 7, '2025-12-01', '2025-12-31', 2, 2, 'Evening performance recommended', 'Daily departures', 1, 1),
('Knuckles October Schedule', 8, '2025-10-01', '2025-10-31', 2, 2, 'Moderate trek', 'Guided tours available', 1, 1),
('Knuckles November Schedule', 8, '2025-11-01', '2025-11-30', 2, 2, 'Scenic viewpoints included', 'Daily departures', 1, 1),
('Knuckles December Schedule', 8, '2025-12-01', '2025-12-31', 2, 2, 'Bring hiking shoes', 'Weekend batches', 1, 1),
('Wilpattu October Schedule', 9, '2025-10-01', '2025-10-31', 3, 3, 'Luxury camping included', 'Advance booking required', 1, 1),
('Wilpattu November Schedule', 9, '2025-11-01', '2025-11-30', 3, 3, 'Morning and evening safaris', 'Daily departures', 1, 1),
('Wilpattu December Schedule', 9, '2025-12-01', '2025-12-31', 3, 3, 'Guided wildlife tours', 'Daily departures', 1, 1),
('Arugam Bay October Schedule', 10, '2025-10-01', '2025-10-31', 4, 4, 'Surf lessons included', 'Daily departures', 1, 1),
('Arugam Bay November Schedule', 10, '2025-11-01', '2025-11-30', 4, 4, 'Equipment rental included', 'Weekends preferred', 1, 1),
('Arugam Bay December Schedule', 10, '2025-12-01', '2025-12-31', 4, 4, 'Sunset surfing sessions', 'Daily departures', 1, 1),
('Dambulla October Schedule', 11, '2025-10-01', '2025-10-31', 3, 3, 'Ayurvedic treatments included', 'Daily departures', 1, 1),
('Dambulla November Schedule', 11, '2025-11-01', '2025-11-30', 3, 3, 'Meditation sessions included', 'Weekends available', 1, 1),
('Dambulla December Schedule', 11, '2025-12-01', '2025-12-31', 3, 3, 'Herbal meals provided', 'Daily departures', 1, 1),
('Jaffna October Schedule', 12, '2025-10-01', '2025-10-31', 4, 4, 'Temple and fort visits included', 'Daily departures', 1, 1),
('Jaffna November Schedule', 12, '2025-11-01', '2025-11-30', 4, 4, 'Local cuisine tasting', 'Weekends preferred', 1, 1),
('Jaffna December Schedule', 12, '2025-12-01', '2025-12-31', 4, 4, 'Guided cultural tours', 'Daily departures', 1, 1),
('Kitulgala October Schedule', 13, '2025-10-01', '2025-10-31', 1, 1, 'Safety gear provided', 'Daily departures', 1, 1),
('Kitulgala November Schedule', 13, '2025-11-01', '2025-11-30', 1, 1, 'Guided rafting included', 'Weekdays and weekends', 1, 1),
('Kitulgala December Schedule', 13, '2025-12-01', '2025-12-31', 1, 1, 'Peak water flow season', 'Daily departures', 1, 1),
('Trincomalee October Schedule', 14, '2025-10-01', '2025-10-31', 3, 3, 'Snorkeling gear provided', 'Daily departures', 1, 1),
('Trincomalee November Schedule', 14, '2025-11-01', '2025-11-30', 3, 3, 'Family-friendly tours', 'Weekends available', 1, 1),
('Trincomalee December Schedule', 14, '2025-12-01', '2025-12-31', 3, 3, 'Coral reef visit included', 'Daily departures', 1, 1),
('Udawalawe October Schedule', 15, '2025-10-01', '2025-10-31', 1, 1, 'Elephant sightings expected', 'Morning safaris', 1, 1),
('Udawalawe November Schedule', 15, '2025-11-01', '2025-11-30', 1, 1, 'Guided safari included', 'Daily departures', 1, 1),
('Udawalawe December Schedule', 15, '2025-12-01', '2025-12-31', 1, 1, 'Afternoon safari recommended', 'Daily departures', 1, 1);



INSERT INTO activities_schedule (name, activity_id, assume_start_date, assume_end_date, duration_hours_start, duration_hours_end, special_note, description, status, created_by) VALUES
('Sigiriya Morning Climb', 1, '2025-10-01', '2025-12-31', 3.0, 4.0, 'Start early to avoid heat', 'Daily morning slots', 1, 1),
('Sigiriya Evening Climb', 1, '2025-10-01', '2025-12-31', 3.0, 4.0, 'Sunset climb recommended', 'Evening departures available', 1, 1),
('Sigiriya Weekend Climb', 1, '2025-10-01', '2025-12-31', 3.5, 4.5, 'Weekend guided climb', 'Limited slots', 1, 1),
('Knuckles Morning Trek', 2, '2025-10-01', '2025-11-30', 5.0, 6.0, 'Bring water and snacks', 'Daily morning departures', 1, 1),
('Knuckles Sunset Trek', 2, '2025-10-01', '2025-11-30', 5.0, 6.0, 'Sunset scenic points included', 'Evening departures', 1, 1),
('Knuckles Weekend Trek', 2, '2025-10-01', '2025-11-30', 6.0, 7.0, 'Overnight camping option', 'Weekend batches', 1, 1),
('Kitulgala Morning Rafting', 3, '2025-10-01', '2025-12-31', 3.5, 4.0, 'Safety gear provided', 'Daily departures', 1, 1),
('Kitulgala Afternoon Rafting', 3, '2025-10-01', '2025-12-31', 3.5, 4.0, 'Guided rafting included', 'Afternoon sessions', 1, 1),
('Kitulgala Weekend Rafting', 3, '2025-10-01', '2025-12-31', 4.0, 4.5, 'Peak water flow season', 'Weekend slots only', 1, 1),
('Surfing Lessons Morning', 4, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Beginner friendly', 'Morning sessions', 1, 1),
('Surfing Lessons Afternoon', 4, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Intermediate level', 'Afternoon sessions', 1, 1),
('Surfing Weekend Camp', 4, '2025-10-01', '2026-03-31', 4.0, 5.0, '3-day surf camp', 'Weekend batches', 1, 1),
('Kayaking Morning', 5, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Calm water sections', 'Morning departures', 1, 1),
('Kayaking Afternoon', 5, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Scenic route', 'Afternoon departures', 1, 1),
('Kayaking Weekend', 5, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Guided tour', 'Weekend batches', 1, 1),
('Yala Morning Safari', 6, '2025-10-01', '2026-02-28', 3.5, 4.5, 'Higher chance of leopard sightings', 'Early morning departure', 1, 1),
('Yala Afternoon Safari', 6, '2025-10-01', '2026-02-28', 3.5, 4.5, 'Sunset wildlife tour', 'Afternoon departures', 1, 1),
('Yala Weekend Safari', 6, '2025-10-01', '2026-02-28', 4.0, 5.0, 'Guided safari', 'Weekend batches', 1, 1),
('Udawalawe Morning Safari', 7, '2025-10-01', '2026-02-28', 4.5, 5.0, 'Elephant sightings expected', 'Morning slots', 1, 1),
('Udawalawe Afternoon Safari', 7, '2025-10-01', '2026-02-28', 4.5, 5.0, 'Sunset safari included', 'Afternoon slots', 1, 1),
('Udawalawe Weekend Safari', 7, '2025-10-01', '2026-02-28', 5.0, 6.0, 'Guided safari', 'Weekend departures', 1, 1),
('Whale Watching Morning', 8, '2025-11-01', '2026-04-30', 3.5, 4.5, 'Best sighting months', 'Morning departures', 1, 1),
('Whale Watching Afternoon', 8, '2025-11-01', '2026-04-30', 3.5, 4.5, 'Afternoon cruises', 'Limited slots', 1, 1),
('Whale Watching Weekend', 8, '2025-11-01', '2026-04-30', 4.0, 5.0, 'Guided tours', 'Weekend departures', 1, 1),
('Nine Arch Bridge Morning Walk', 9, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Best during train passing', 'Morning sessions', 1, 1),
('Nine Arch Bridge Afternoon Walk', 9, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Scenic photography', 'Afternoon sessions', 1, 1),
('Nine Arch Bridge Weekend Walk', 9, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Guided tours', 'Weekend batches', 1, 1),
('Little Adams Peak Morning Hike', 10, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Panoramic views', 'Morning slots', 1, 1),
('Little Adams Peak Afternoon Hike', 10, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Sunset view included', 'Afternoon slots', 1, 1),
('Little Adams Peak Weekend Hike', 10, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Guided hike', 'Weekend batches', 1, 1),
('Kandy Temple Morning Visit', 11, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Temple visit with guide', 'Morning slots', 1, 1),
('Kandy Temple Afternoon Visit', 11, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Cultural show included', 'Afternoon slots', 1, 1),
('Kandy Temple Weekend Visit', 11, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Guided cultural experience', 'Weekend batches', 1, 1),
('Bentota Yoga Morning', 12, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Sunrise session', 'Morning slots', 1, 1),
('Bentota Yoga Afternoon', 12, '2025-10-01', '2026-03-31', 1.5, 2.0, 'Beach yoga included', 'Afternoon slots', 1, 1),
('Bentota Weekend Yoga Retreat', 12, '2025-10-01', '2026-03-31', 2.0, 3.0, 'Meditation and relaxation', 'Weekend retreats', 1, 1),
('Ella Sunrise Photography Morning', 13, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Capture sunrise views', 'Morning slots', 1, 1),
('Ella Sunrise Photography Afternoon', 13, '2025-10-01', '2026-03-31', 2.0, 2.5, 'Golden hour included', 'Afternoon slots', 1, 1),
('Ella Sunrise Photography Weekend', 13, '2025-10-01', '2026-03-31', 2.5, 3.0, 'Guided photography tour', 'Weekend batches', 1, 1),
('Jaffna Food Tour Morning', 14, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Traditional dishes', 'Morning slots', 1, 1),
('Jaffna Food Tour Afternoon', 14, '2025-10-01', '2026-03-31', 3.0, 3.5, 'Cooking demo included', 'Afternoon slots', 1, 1),
('Jaffna Food Tour Weekend', 14, '2025-10-01', '2026-03-31', 3.5, 4.0, 'Guided tasting', 'Weekend batches', 1, 1);




INSERT INTO package_schedule 
(name, package_id, assume_start_date, assume_end_date, duration_start, duration_end, special_note, description, status, created_by, updated_by)
VALUES
('Sigiriya Day - Morning Departure', 1, '2025-10-05', '2025-10-05', 1, 1, 'Morning batch with sunrise view', 'Morning start with guide and breakfast stop.', 1, 1, 1),
('Sigiriya Day - Evening Departure', 1, '2025-10-05', '2025-10-05', 1, 1, 'Evening batch with sunset view', 'Evening start, includes sunset point visit.', 1, 1, 1),
('Sigiriya Sunrise - Early Trek', 2, '2025-11-05', '2025-11-05', 1, 1, 'Early morning group', 'Depart at 5 AM for sunrise hike.', 1, 1, 1),
('Sigiriya Sunrise - Late Trek', 2, '2025-11-06', '2025-11-06', 1, 1, 'Late start for relaxed visitors', 'Depart at 8 AM for guided hike.', 1, 1, 1),
('Cultural Triangle - Morning Start', 3, '2025-10-10', '2025-10-15', 5, 5, 'Standard group', 'Complete 5-day cultural experience.', 1, 1, 1),
('Cultural Triangle - Afternoon Start', 3, '2025-10-15', '2025-10-20', 5, 5, 'Second group batch', 'Same route with alternate timing.', 1, 1, 1),
('Triangle Express - Early Batch', 4, '2025-10-12', '2025-10-14', 3, 3, 'Morning travel group', 'Fast-paced cultural highlights.', 1, 1, 1),
('Triangle Express - Late Batch', 4, '2025-10-16', '2025-10-18', 3, 3, 'Afternoon start', 'Flexible schedule for late arrivals.', 1, 1, 1),
('Yala Safari - Day 1 Start', 5, '2025-11-10', '2025-11-11', 2, 2, 'Group 1', 'Includes one night luxury camp.', 1, 1, 1),
('Yala Safari - Day 2 Start', 5, '2025-11-12', '2025-11-13', 2, 2, 'Group 2', 'Second group departure.', 1, 1, 1),
('Yala Premium - Group A', 6, '2025-11-15', '2025-11-16', 2, 2, 'Private jeep package', 'Includes naturalist guide.', 1, 1, 1),
('Yala Premium - Group B', 6, '2025-11-17', '2025-11-18', 2, 2, 'Private jeep - alternate batch', 'Luxury vehicle upgrade.', 1, 1, 1),
('Mirissa Escape - Weekend 1', 7, '2025-10-20', '2025-10-22', 3, 3, 'First weekend group', 'Water sports & relaxation.', 1, 1, 1),
('Mirissa Escape - Weekend 2', 7, '2025-10-27', '2025-10-29', 3, 3, 'Second weekend batch', 'Similar itinerary.', 1, 1, 1),
('Whale Watching - Early Morning', 8, '2025-11-05', '2025-11-05', 1, 1, 'Morning sea trip', 'Best for whale spotting.', 1, 1, 1),
('Whale Watching - Midday Trip', 8, '2025-11-06', '2025-11-06', 1, 1, 'Midday calm sea ride', 'Ideal for photography.', 1, 1, 1),
('Ella 3-Day - Group A', 9, '2025-10-12', '2025-10-14', 3, 3, 'First batch', 'Explore Ella waterfalls.', 1, 1, 1),
('Ella 3-Day - Group B', 9, '2025-10-15', '2025-10-17', 3, 3, 'Second batch', 'Train and hiking combo.', 1, 1, 1),
('Ella Train Tour - Weekday Batch', 10, '2025-11-05', '2025-11-06', 2, 2, 'Weekday group', 'Tea estates & scenic ride.', 1, 1, 1),
('Ella Train Tour - Weekend Batch', 10, '2025-11-08', '2025-11-09', 2, 2, 'Weekend group', 'Same route with flexible timing.', 1, 1, 1),
('Bentota Spa - Short Stay', 11, '2025-10-10', '2025-10-12', 3, 3, 'Weekend spa retreat', 'Includes massage & yoga.', 1, 1, 1),
('Bentota Spa - Extended', 11, '2025-10-13', '2025-10-15', 3, 3, 'Extended session', 'Includes Ayurveda meals.', 1, 1, 1),
('Luxury Wellness - Week 1', 12, '2025-11-05', '2025-11-09', 5, 5, 'First group', 'Full 5-day rejuvenation.', 1, 1, 1),
('Luxury Wellness - Week 2', 12, '2025-11-12', '2025-11-16', 5, 5, 'Second group', 'Alternative schedule.', 1, 1, 1),
('Kandy Half-Day - Morning', 13, '2025-10-01', '2025-10-01', 1, 1, 'Morning temple tour', 'Includes dance show.', 1, 1, 1),
('Kandy Half-Day - Evening', 13, '2025-10-01', '2025-10-01', 1, 1, 'Evening dance show', 'Includes cultural performance.', 1, 1, 1),
('Kandy Full - Day 1 Start', 14, '2025-11-10', '2025-11-10', 1, 1, 'Full experience group 1', 'Temple & gardens tour.', 1, 1, 1),
('Kandy Full - Day 2 Start', 14, '2025-11-12', '2025-11-12', 1, 1, 'Second batch', 'Evening show included.', 1, 1, 1),
('Knuckles Day - Hike 1', 15, '2025-10-05', '2025-10-05', 1, 1, 'Morning trek', 'Includes lunch.', 1, 1, 1),
('Knuckles Day - Hike 2', 15, '2025-10-07', '2025-10-07', 1, 1, 'Afternoon trek', 'Includes dinner.', 1, 1, 1);




INSERT INTO features 
(package_id, name, value, description, status, color, special_note, hover_color, created_by, updated_by)
VALUES
(1, 'Hotel Type', '3-Star', 'Overnight stay in 3-star hotel near Sigiriya Rock.', 1, '#F4B400', 'Comfort stay with breakfast included.', '#FFD700', 1, 1),
(1, 'Meal Plan', 'Breakfast Included', 'Morning buffet with local and continental options.', 1, '#34A853', 'Includes vegetarian meals.', '#66BB6A', 1, 1),
(2, 'Transport Type', 'AC Van', 'Round trip from Colombo in air-conditioned van.', 1, '#4285F4', 'Includes bottled water and WiFi.', '#5C6BC0', 1, 1),
(3, 'Hotel Category', '4-Star', 'Stay in top-rated hotels in Dambulla and Kandy.', 1, '#E67E22', 'Heritage-style rooms with modern comfort.', '#FFB74D', 1, 1),
(3, 'Guide Service', 'Included', 'Professional English-speaking cultural guide.', 1, '#9C27B0', 'Available throughout the journey.', '#BA68C8', 1, 1),
(4, 'Meals', 'Full Board', 'Breakfast, lunch, and dinner covered.', 1, '#4CAF50', 'Includes traditional rice & curry meals.', '#81C784', 1, 1),
(5, 'Safari Vehicle', '4x4 Jeep', 'Private safari jeep with driver and tracker.', 1, '#795548', 'Sunset safari available upon request.', '#A1887F', 1, 1),
(5, 'Lodging', 'Luxury Camp', 'Stay in a fully equipped tented camp with AC.', 1, '#009688', 'Evening campfire and BBQ.', '#4DB6AC', 1, 1),
(6, 'Hotel View', 'Sea View Rooms', 'Accommodation facing the Indian Ocean.', 1, '#03A9F4', 'Includes balcony and sunset views.', '#4FC3F7', 1, 1),
(7, 'Boat Type', 'Double Deck', 'Spacious boat with safety jackets and snacks.', 1, '#2196F3', 'Licensed marine tour guide onboard.', '#64B5F6', 1, 1),
(8, 'Hotel Type', 'Boutique Hotel', 'Small mountain-view hotel with Wi-Fi.', 1, '#9C27B0', 'Eco-friendly and cozy environment.', '#CE93D8', 1, 1),
(9, 'Train Class', 'Observation Deck', 'Reserved seats in scenic observation car.', 1, '#607D8B', 'Panoramic windows for photography.', '#90A4AE', 1, 1),
(10, 'Spa Access', 'Included', 'Full-body massage and aromatherapy session.', 1, '#E91E63', 'Herbal oils used for treatments.', '#F48FB1', 1, 1),
(11, 'Resort Rating', '5-Star', 'Luxury Ayurveda resort with pool and sauna.', 1, '#FF9800', 'All-inclusive with spa treatments.', '#FFB74D', 1, 1),
(12, 'Transport', 'Private Car', 'Pickup and drop from hotel in AC car.', 1, '#2196F3', 'Driver with local knowledge.', '#64B5F6', 1, 1),
(13, 'Temple Visit', 'Included', 'Guided entry to Temple of the Tooth.', 1, '#8BC34A', 'Includes entry ticket.', '#AED581', 1, 1),
(14, 'Equipment', 'Provided', 'Trekking poles, snacks, and first-aid kit.', 1, '#795548', 'Raincoats provided if needed.', '#A1887F', 1, 1),
(15, 'Accommodation', 'Jungle Lodge', 'Stay inside park perimeter with safety facilities.', 1, '#388E3C', 'Campfire and night safari optional.', '#66BB6A', 1, 1);



INSERT INTO activities_requirement (activity_id, name, value, description, status, color, created_by) VALUES
(1, 'Fitness Level', 'Moderate', 'Able to climb 1200 steps', 1, '#FFC107', 1),
(1, 'Age Limit', '8+', 'Children under 8 not recommended', 1, '#FF5722', 1),
(3, 'Fitness Level', 'Easy', 'Suitable for most fitness levels', 1, '#4CAF50', 1),
(4, 'Group Size', 'Min 2', 'At least 2 people required per jeep', 1, '#2196F3', 1),
(5, 'Swimming Ability', 'Not Required', 'Life jackets provided', 1, '#00BCD4', 1);


-- 15. Insert Tour History
INSERT INTO tour_history (tour_schedule_id, package_id, number_of_participate, rating, duration, name, description, status, color, hover_color, start_date, end_date, vehicle_number, driver_id, guide_id, created_by) VALUES
(1, 1, 6, 4.8, 1, 'Sigiriya Tour - October Group', 'Successful tour with positive feedback', 1, '#FF6B6B', '#FF5252', '2025-10-15', '2025-10-15', 'CAR-1234,CAR-5678', 1, 1, 1),
(2, 2, 4, 4.9, 3, 'Ella Adventure - November Batch', 'Excellent weather and great experience', 1, '#4ECDC4', '#3BB8B0', '2025-11-08', '2025-11-10', 'VAN-9876', 2, 2, 1);

-- 16. Insert Activities History
INSERT INTO activities_history (activity_schedule_id, name, description, status, number_of_participate, activity_start, activity_end, rating, special_note, created_by) VALUES
(1, 'Sigiriya Climb - Group A', 'Morning climb with great visibility', 1, 8, '2025-10-15 06:30:00', '2025-10-15 10:00:00', 4.7, 'Clear skies, excellent photos', 1),
(3, 'Yala Safari - Morning', 'Spotted 2 leopards and elephant herd', 1, 6, '2025-12-05 05:30:00', '2025-12-05 09:30:00', 5.0, 'Rare double leopard sighting', 1);

-- 17. Insert Package History
INSERT INTO package_history (package_schedule_id, number_of_participate, rating, duration, name, description, status, color, hover_color, start_date, end_date, created_by) VALUES
(1, 6, 4.8, 1, 'Sigiriya Package October', 'Completed successfully', 1, '#FF6B6B', '#FF5252', '2025-10-15', '2025-10-15', 1),
(2, 4, 4.9, 3, 'Ella Package November', 'Outstanding experience', 1, '#4ECDC4', '#3BB8B0', '2025-11-08', '2025-11-10', 1);



-- 18. Insert Reviews - Tour
INSERT INTO tour_review (tour_schedule_id, name, review, rating, description, status, number_of_participate, created_by) VALUES
(1, 'Amazing Sigiriya Experience', 'The climb was challenging but absolutely worth it. Our guide was knowledgeable and the views were breathtaking!', 4.8, 'Highly recommended for history buffs', 1, 6, 1),
(2, 'Best Ella Trip Ever', 'Three perfect days in paradise. The train ride, hikes, and accommodation were all excellent.', 4.9, 'Perfect for nature lovers', 1, 4, 1);

-- 19. Insert Reviews - Activities
INSERT INTO activities_review (activity_schedule_id, name, review, rating, description, status, number_of_participate, created_by) VALUES
(1, 'Incredible Rock Fortress', 'Early morning climb was the best decision. Fewer crowds and amazing sunrise views from the top.', 4.7, 'Must-do activity in Sri Lanka', 1, 8, 1),
(3, 'Unforgettable Safari', 'We saw leopards, elephants, and so many birds. Our driver/tracker was experienced and knew all the best spots.', 5.0, 'Best wildlife experience', 1, 6, 1);

-- 20. Insert Reviews - Package
INSERT INTO package_review (package_schedule_id, name, review, rating, description, status, number_of_participate, created_by) VALUES
(1, 'Great Value Package', 'Everything was well organized. Transport was comfortable and guide was fantastic.', 4.8, 'Excellent day trip option', 1, 6, 1),
(2, 'Wonderful Ella Package', 'The hotel was beautiful, food was great, and activities were well planned. Could not ask for more!', 4.9, 'Highly recommended 3-day trip', 1, 4, 1);



INSERT INTO seasons_images 
(season_id, name, description, image_url, status, created_by, updated_by)
VALUES
(1, 'Blooming Flowers', 'Vibrant flowers in full bloom across gardens', '/images/season-images/spring-blooming-flower.jpg', 1, 1, 1),
(1, 'Green Meadows', 'Fresh green landscapes under clear skies', '/images/season-images/green-meadows.jpg', 1, 1, 1),
(1, 'Outdoor Activities', 'Families enjoying outdoor picnics and walks', '/images/season-images/outdoor-activities.jpg', 1, 1, 1),
(2, 'Sunny Beach', 'Tourists relaxing at the beach under the summer sun', '/images/season-images/sunny-beach.jpg', 1, 1, 1),
(2, 'Mountain Hike', 'Adventure seekers hiking in sunny mountains', '/images/season-images/mountain-hike.jpg', 1, 1, 1),
(2, 'Summer Festival', 'Local summer festivals with lights and music', '/images/season-images/summer-festival.jpg', 1, 1, 1),
(3, 'Falling Leaves', 'Golden leaves covering forest trails', '/images/season-images/falling-leaves.jpg', 1, 1, 1),
(3, 'Scenic Drive', 'Cars driving through colorful countryside roads', '/images/season-images/scenic-drive.jpg', 1, 1, 1),
(3, 'Harvest Time', 'Farmers harvesting crops during autumn season', '/images/season-images/harvest-time.jpg', 1, 1, 1),
(4, 'Snow Mountains', 'Snow-covered peaks and ski resorts', '/images/season-images/snow-mountains.jpg', 1, 1, 1),
(4, 'Winter Cabin', 'Cozy wooden cabin with smoke rising from chimney', '/images/season-images/winter-cabin.jpg', 1, 1, 1),
(4, 'Holiday Spirit', 'Christmas lights and snowy town streets', '/images/season-images/holiday-spirit.jpg', 1, 1, 1),
(5, 'Safari Adventure', 'Tourists on jeep safari under sunny skies', '/images/season-images/safari-adventure.jpg', 1, 1, 1),
(5, 'Island Paradise', 'Crystal clear beaches and palm trees', '/images/season-images/island-paradise.jpg', 1, 1, 1),
(5, 'Outdoor Trekking', 'Hikers exploring dry landscapes', '/images/season-images/outdoor-trekking.jpg', 1, 1, 1),
(6, 'Rainforest Path', 'Misty jungle path during rainfall', '/images/season-images/rainforest-path.jpg', 1, 1, 1),
(6, 'Waterfalls', 'Waterfalls flowing at peak volume', '/images/season-images/waterfalls.jpg', 1, 1, 1),
(6, 'Rainy Village', 'Traditional houses under tropical rain', '/images/season-images/rainy-village.jpg', 1, 1, 1),
(7, 'Rain Clouds', 'Dark clouds forming before heavy rain', '/images/season-images/rain-clouds.jpg', 1, 1, 1),
(7, 'Cultural Festival', 'Locals celebrating monsoon harvest festival', '/images/season-images/cultural-festival.jpg', 1, 1, 1),
(7, 'Lush Fields', 'Rice paddies glowing in monsoon greenery', '/images/season-images/lush-fields.jpg', 1, 1, 1),
(8, 'City Tour', 'Tourists exploring heritage cities in pleasant weather', '/images/season-images/city-tour.jpg', 1, 1, 1),
(8, 'Countryside Walk', 'Villagers enjoying cool breeze on farmland', '/images/season-images/countryside-walk.jpg', 1, 1, 1),
(8, 'Morning Mist', 'Light mist and blue skies over hills', '/images/season-images/morning-mist.jpg', 1, 1, 1),
(9, 'Crowded Beach', 'Tourists flocking to famous beach destinations', '/images/season-images/crowded-beach.jpg', 1, 1, 1),
(9, 'Resort Stay', 'Luxury resorts fully booked during holidays', '/images/season-images/resort-stay.jpg', 1, 1, 1),
(9, 'Festival Night', 'Lively festival nights with fireworks', '/images/season-images/festival-night.jpg', 1, 1, 1),
(10, 'Quiet Getaway', 'Peaceful resort during transition months', '/images/season-images/quiet-getaway.jpg', 1, 1, 1),
(10, 'Balanced Weather', 'Comfortable mix of sunshine and breeze', '/images/season-images/balanced-weather.jpg', 1, 1, 1),
(10, 'cultural-tour.jpg', 'Travelers exploring temples without crowds', '/images/season-images/shoulder3.jpg', 1, 1, 1),
(11, 'Empty Beach', 'Calm and deserted coastal area during off season', '/images/season-images/empty-beach.jpg', 1, 1, 1),
(11, 'Discount Travel', 'Hotels offering off-season promotions', '/images/season-images/discount-travel.jpg', 1, 1, 1),
(11, 'Rainy Roads', 'Occasional rain and quiet towns', '/images/season-images/rainy-roads.jpg', 1, 1, 1);



INSERT INTO tour_images 
(tour_id, name, description, image_url, status, created_by, updated_by)
VALUES
(1, 'Sigiriya Rock View', 'Panoramic view of Sigiriya Rock Fortress', '/images/tour-images/sigiriya-rock-view.jpg', 1, 1, 1),
(1, 'Lion’s Gate Steps', 'Climbers ascending through the Lion’s Gate', '/images/tour-images/lions-gate-steps.webp', 1, 1, 1),
(1, 'Ancient Frescoes', 'Beautiful wall paintings inside the fortress', '/images/tour-images/ancient-frescoes.webp', 1, 1, 1),
(1, 'Sunset at Sigiriya', 'Golden hour view of the Sigiriya Rock', '/images/tour-images/sunset-at-sigiriya.jpg', 1, 1, 1),
(2, 'Anuradhapura Stupa', 'Ancient Buddhist stupa surrounded by pilgrims', '/images/tour-images/anuradhapura-stupa.jpeg', 1, 1, 1),
(2, 'Polonnaruwa Ruins', 'Old royal palace ruins from the Polonnaruwa kingdom', '/images/tour-images/polonnaruwa-ruins.jpg', 1, 1, 1),
(2, 'Sigiriya Gardens', 'Geometric water gardens of Sigiriya', '/images/tour-images/sigiriya-gardens.jpg', 1, 1, 1),
(2, 'Mihintale Hills', 'Spiritual sunrise view from Mihintale', '/images/tour-images/mihintale-hills.jpg', 1, 1, 1),
(3, 'Leopard in Yala', 'Leopard resting on a tree branch', '/images/tour-images/leopard-in-yala.jpg', 1, 1, 1),
(3, 'Elephants at Waterhole', 'Elephants drinking at a safari waterhole', '/images/tour-images/elephants-at-waterhole.jpg', 1, 1, 1),
(3, 'Safari Jeep', 'Tourists exploring Yala by jeep', '/images/tour-images/safari-jeep.jpg', 1, 1, 1),
(3, 'Sunset Safari', 'Evening view of Yala National Park', '/images/tour-images/sunset-safari.png', 1, 1, 1),
(4, 'Mirissa Beach', 'Golden beach with turquoise waves', '/images/tour-images/mirissa-beach.jpg', 1, 1, 1),
(4, 'Whale Watching', 'Blue whale emerging from the ocean', '/images/tour-images/whale-watching.jpg', 1, 1, 1),
(4, 'Coconut Hill', 'Iconic hilltop viewpoint in Mirissa', '/images/tour-images/coconut-hill.jpg', 1, 1, 1),
(4, 'Beach Sunset', 'Romantic sunset along Mirissa shoreline', '/images/tour-images/beach-sunset.jpeg', 1, 1, 1),
(5, 'Nine Arches Bridge', 'Train passing over the famous bridge', '/images/tour-images/nine-arches-bridge.jpg', 1, 1, 1),
(5, 'Little Adam’s Peak', 'Hikers reaching the summit at dawn', '/images/tour-images/little-adam-peak.jpg', 1, 1, 1),
(5, 'Tea Plantations', 'Tea estates with workers plucking leaves', '/images/tour-images/tea-plantations.jpg', 1, 1, 1),
(5, 'Ella Gap View', 'Scenic valley view from Ella Rock', '/images/tour-images/ella-gap-view.jpg', 1, 1, 1),
(6, 'Beach Resort', 'Luxury spa by the ocean', '/images/tour-images/beach-resort.webp', 1, 1, 1),
(6, 'Yoga Retreat', 'Morning yoga session by the beach', '/images/tour-images/yoga-retreat.jpg', 1, 1, 1),
(6, 'Ayurvedic Massage', 'Therapist performing full-body oil massage', '/images/tour-images/ayurvedic-massage.jpg', 1, 1, 1),
(6, 'Tropical Gardens', 'Tranquil resort gardens with palm trees', '/images/tour-images/tropical-gardens.webp', 1, 1, 1),
(7, 'Temple of the Tooth', 'Exterior of the sacred temple', '/images/tour-images/temple-of-the-tooth.jpg', 1, 1, 1),
(7, 'Cultural Dance Show', 'Traditional Kandyan dancers performing', '/images/tour-images/cultural-dance-show.jpg', 1, 1, 1),
(7, 'Royal Botanic Gardens', 'Beautiful blooms in Peradeniya Gardens', '/images/tour-images/royal-botanic-gardens.jpeg', 1, 1, 1),
(7, 'Kandy Lake', 'Serene view of the lake at sunset', '/images/tour-images/kandy-lake.jpg', 1, 1, 1),
(8, 'Mountain Trail', 'Hikers walking through misty forests', '/images/tour-images/mountain-trail.jpg', 1, 1, 1),
(8, 'Waterfall Spot', 'Hidden waterfall deep in the Knuckles range', '/images/tour-images/waterfall-spot.webp', 1, 1, 1),
(8, 'Campfire Night', 'Campers resting under the stars', '/images/tour-images/campfire-night.jpg', 1, 1, 1),
(8, 'Panoramic View', 'Breathtaking aerial view of Knuckles mountains', '/images/tour-images/panoramic-view.jpg', 1, 1, 1),
(9, 'Wilpattu Lakes', 'Scenic lakes where animals gather', '/images/tour-images/wilpattu-lakes.jpg', 1, 1, 1),
(9, 'Camping Lodges', 'Luxury camping under the stars', '/images/tour-images/camping-lodges.jpg', 1, 1, 1),
(9, 'Wildlife Spotting', 'Tourists spotting animals with binoculars', '/images/tour-images/wildlife-spotting.jpg', 1, 1, 1),
(9, 'Park Entrance', 'Main gate of Wilpattu National Park', '/images/tour-images/park-entrance.jpg', 1, 1, 1),
(10, 'Surf Waves', 'Surfer riding perfect waves at Arugam Bay', '/images/tour-images/surf-waves.jpg', 1, 1, 1),
(10, 'Beach Bars', 'Evening beach bars with music and bonfires', '/images/tour-images/beach-bars.jpg', 1, 1, 1),
(10, 'Surf Boards', 'Colorful surfboards lined up on the sand', '/images/tour-images/surf-boards.jpg', 1, 1, 1),
(10, 'Palm Trees', 'Shady palms swaying along the coast', '/images/tour-images/palm-trees.webp', 1, 1, 1),
(11, 'Ayurveda Center', 'Guests receiving herbal treatments', '/images/tour-images/ayurveda-center.jpg', 1, 1, 1),
(11, 'Meditation Hall', 'Peaceful meditation in nature', '/images/tour-images/meditation-hall.webp', 1, 1, 1),
(11, 'Herbal Garden', 'Organic herbs used for healing', '/images/tour-images/herbal-garden.webp', 1, 1, 1),
(11, 'Relaxation Zone', 'Open-air spa surrounded by greenery', '/images/tour-images/relaxation-zone.webp', 1, 1, 1),
(12, 'Nallur Temple', 'Vibrant temple during festival', '/images/tour-images/nallur-temple.jpg', 1, 1, 1),
(12, 'Jaffna Fort', 'Historic Dutch fort by the sea', '/images/tour-images/jaffna-fort.jpg', 1, 1, 1),
(12, 'Local Cuisine', 'Traditional Jaffna crab curry', '/images/tour-images/local-cuisine.jpg', 1, 1, 1),
(12, 'Market Visit', 'Vendors selling local produce', '/images/tour-images/market-visit.jpg', 1, 1, 1),
(13, 'Rafting Rapids', 'Adventure team paddling through rapids', '/images/tour-images/rafting-rapids.jpg', 1, 1, 1),
(13, 'River Base Camp', 'Rafting base camp beside Kelani River', '/images/tour-images/river-base-camp.jpg', 1, 1, 1),
(13, 'Canyoning Jump', 'Tourists jumping into canyon pools', '/images/tour-images/canyoning-jump.jpg', 1, 1, 1),
(13, 'Rainforest Trail', 'Pathway through tropical forest', '/images/tour-images/rainforest-trail.jpg', 1, 1, 1),
(14, 'Pigeon Island', 'Snorkelers exploring coral reefs', '/images/tour-images/pigeon-island.jpg', 1, 1, 1),
(14, 'Beach Resort', 'Resort overlooking turquoise ocean', '/images/tour-images/beach-resort.jpg', 1, 1, 1),
(14, 'Dolphin Watching', 'Dolphins swimming near boats', '/images/tour-images/dolphin-watching.webp', 1, 1, 1),
(14, 'White Sands', 'Calm and scenic Trincomalee beaches', '/images/tour-images/white-sands.webp', 1, 1, 1),
(15, 'Elephant Herd', 'Group of elephants crossing the savanna', '/images/tour-images/elephant-herd.jpg', 1, 1, 1),
(15, 'Safari Jeep Ride', 'Tourists viewing elephants from jeep', '/images/tour-images/safari-jeep-ride.jpg', 1, 1, 1),
(15, 'Bird Watching', 'Exotic birds near Udawalawe reservoir', '/images/tour-images/bird-watching.webp', 1, 1, 1),
(15, 'Morning Mist', 'Sunrise over the Udawalawe landscape', '/images/tour-images/morning-mist.jpg', 1, 1, 1);




INSERT INTO package_images (package_id, name, description, status, image_url, color, created_by) VALUES
(1, 'Sigiriya Rock View', 'Panoramic view of Sigiriya Rock Fortress', 1, '/images/packages/sigiriya-rock-view.jpg', '#FF6B6B', 1),
(1, 'Ancient Steps', 'Tourists climbing the ancient rock steps', 1, '/images/packages/ancient-steps.jpg', '#FF5252', 1),
(2, 'Sunrise at Sigiriya', 'Golden sunrise view from Sigiriya summit', 1, '/images/packages/sunrise-at-sigiriya.jpg', '#FFA07A', 1),
(2, 'Cultural Guide Tour', 'Guide explaining Sigiriya frescoes', 1, '/images/packages/cultural-guide-tour.jpg', '#FF8C69', 1),
(3, 'Anuradhapura Stupa', 'Ancient stupa from the cultural triangle', 1, '/images/packages/anuradhapura-stupa.jpg', '#AA96DA', 1),
(3, 'Polonnaruwa Ruins', 'Historic stone ruins surrounded by greenery', 1, '/images/packages/polonnaruwa-ruins.jpg', '#9580D0', 1),
(4, 'Triangle Express Bus', 'Tour bus ready for quick 3-day route', 1, '/images/packages/triangle-express-bus.jpg', '#9370DB', 1),
(4, 'Sigiriya Stopover', 'Travelers taking photos at Sigiriya', 1, '/images/packages/sigiriya-stopover.jpg', '#836FFF', 1),
(5, 'Leopard Spotting', 'Jeep group spotting a leopard at Yala', 1, '/images/packages/leopard-spotting.jpg', '#95E1D3', 1),
(5, 'Luxury Camp', 'Luxury tent setup near safari site', 1, '/images/packages/luxury-camp.jpg', '#7DD4C5', 1),
(6, 'Private Jeep Safari', 'Exclusive jeep ride with guide', 1, '/images/packages/private-jeep-safari.png', '#20B2AA', 1),
(6, 'Elephants at Dusk', 'Elephants walking in golden hour light', 1, '/images/packages/elephants-at-dusk.jpg', '#2E8B57', 1),
(7, 'Mirissa Beach', 'Tourists enjoying sunbathing on Mirissa beach', 1, '/images/packages/mirissa-beach.jpg', '#F38181', 1),
(7, 'Water Sports', 'Jet skiing and parasailing activities', 1, '/images/packages/water-sports.jpg', '#F06868', 1),
(8, 'Whale Watching Boat', 'Boat heading to deep sea whale watching', 1, '/images/packages/whale-watching-boat.webp', '#FFB6C1', 1),
(8, 'Blue Whale', 'Rare sight of blue whale surfacing', 1, '/images/packages/blue-whale.jpg', '#FF69B4', 1),
(9, 'Nine Arches Bridge', 'Famous railway bridge in Ella', 1, '/images/packages/nine-arches-bridge.webp', '#4ECDC4', 1),
(9, 'Tea Plantations', 'Tea workers in lush green plantations', 1, '/images/packages/tea-plantations.jpg', '#3BB8B0', 1),
(10, 'Train Journey', 'Scenic train ride from Kandy to Ella', 1, '/images/packages/train-journey.jpeg', '#48D1CC', 1),
(10, 'Tea Tasting', 'Tourists tasting fresh Ceylon tea', 1, '/images/packages/tea-tasting.jpg', '#40E0D0', 1),
(11, 'Spa Treatment', 'Relaxing full-body spa session', 1, '/images/packages/spa-treatment.jpg', '#FFD700', 1),
(11, 'Yoga by the Beach', 'Morning yoga session on the beach', 1, '/images/packages/yoga-by-the-beach.jpg', '#FFC107', 1),
(12, 'Ayurveda Garden', 'Herbal therapy garden and Ayurveda center', 1, '/images/packages/ayurveda-garden.webp', '#DAA520', 1),
(12, 'Luxury Resort', 'Beachfront wellness resort', 1, '/images/packages/luxury-resort.jpg', '#B8860B', 1),
(13, 'Temple of the Tooth', 'Historic Kandy temple exterior view', 1, '/images/packages/temple-of-the-tooth.jpg', '#BA55D3', 1),
(13, 'Cultural Dance', 'Traditional Kandyan dance performance', 1, '/images/packages/cultural-dance.jpg', '#9932CC', 1),
(14, 'Botanical Garden', 'Royal botanical gardens Peradeniya', 1, '/images/packages/botanical-garden.jpg', '#8A2BE2', 1),
(14, 'Cultural City Tour', 'Visitors exploring Kandy streets', 1, '/images/packages/cultural-city-tour.jpg', '#6A5ACD', 1),
(15, 'Knuckles Range View', 'Mountain landscape with morning mist', 1, '/images/packages/knuckles-range-view.jpg', '#32CD32', 1),
(15, 'Group Trekking', 'Group hiking through forest trails', 1, '/images/packages/group-trekking.jpg', '#228B22', 1),
(16, 'Campfire Night', 'Campers enjoying night fire in mountains', 1, '/images/packages/campfire-night.jpg', '#006400', 1),
(16, 'Tents in Valley', 'Camping tents under starry sky', 1, '/images/packages/tents-in-valley.jpg', '#2E8B57', 1),
(17, 'Safari Jeep', 'Jeep heading through Wilpattu trails', 1, '/images/packages/safari-jeep.webp', '#8B4513', 1),
(17, 'Luxury Lodge', 'High-end safari lodge interior', 1, '/images/packages/luxury-lodge.jpg', '#A0522D', 1),
(18, 'Camping Site', 'Tented camp setup near Wilpattu lake', 1, '/images/packages/camping-site.webp', '#D2691E', 1),
(18, 'Wildlife Spot', 'Leopard walking through bush', 1, '/images/packages/wildlife-spot.webp', '#CD853F', 1),
(19, 'Surf Action', 'Surfer catching a wave at Arugam Bay', 1, '/images/packages/surf-action.jpg', '#00CED1', 1),
(19, 'Beach Chill', 'Backpackers relaxing by the surf boards', 1, '/images/packages/beach-chill.jpg', '#20B2AA', 1),
(20, 'Pro Surf Competition', 'Professional surfers performing tricks', 1, '/images/packages/pro-surf-competition.webp', '#1E90FF', 1),
(20, 'Sunset Surf', 'Surfboards against orange sunset', 1, '/images/packages/sunset-surf.jpg', '#4169E1', 1),
(21, 'Ayurveda Treatment', 'Traditional oil therapy', 1, '/images/packages/ayurveda-treatment.jpg', '#FF8C00', 1),
(21, 'Meditation Hall', 'Guests meditating in calm environment', 1, '/images/packages/meditation-hall.jpg', '#FF7F50', 1),
(22, 'Detox Program', 'Guests enjoying herbal detox drinks', 1, '/images/packages/detox-program.jpg', '#FF6347', 1),
(22, 'Resort Stay', 'Healing resort room interior', 1, '/images/packages/resort-stay.jpeg', '#FF4500', 1),
(23, 'Jaffna Fort', 'Historic Dutch fort of Jaffna', 1, '/images/packages/jaffna-fort.jpg', '#FF1493', 1),
(23, 'Temple Visit', 'Colorful Hindu temple exterior', 1, '/images/packages/temple-visit.jpg', '#DB7093', 1),
(24, 'Jaffna Cuisine', 'Traditional northern Sri Lankan dishes', 1, '/images/packages/jaffna-cuisine.jpg', '#C71585', 1),
(24, 'Cultural Streets', 'Busy Jaffna market streets', 1, '/images/packages/cultural-streets.jpg', '#8B008B', 1),
(25, 'Rafting Rapids', 'Adventure seekers paddling through rapids', 1, '/images/packages/rafting-rapids.jpg', '#40E0D0', 1),
(25, 'River Group', 'Team photo after rafting', 1, '/images/packages/river-group.jpg', '#48D1CC', 1),
(26, 'Canyoning Adventure', 'Tourists descending waterfalls', 1, '/images/packages/canyoning-adventure.jpg', '#4682B4', 1),
(26, 'Rope Jump', 'Thrill-seeker jumping into river canyon', 1, '/images/packages/rope-jump.webp', '#5F9EA0', 1),
(27, 'Pigeon Island', 'Snorkelers exploring coral reef', 1, '/images/packages/pigeon-island.webp', '#00FA9A', 1),
(27, 'Boat Ride', 'Tourists boarding snorkeling boat', 1, '/images/packages/boat-ride.jpg', '#00FF7F', 1),
(28, 'Dolphin Watching', 'Dolphins jumping near boat', 1, '/images/packages/dolphin-watching.jpg', '#3CB371', 1),
(28, 'Beach Resort', 'Trincomalee beach resort sunrise', 1, '/images/packages/beach-resort.jpg', '#2E8B57', 1),
(29, 'Elephant Herd', 'Group of elephants near water hole', 1, '/images/packages/elephant-herd.jpg', '#B22222', 1),
(29, 'Jeep Trail', 'Tourists riding through national park', 1, '/images/packages/jeep-trail.webp', '#DC143C', 1),
(30, 'Overnight Camp', 'Camp setup near Udawalawe park', 1, '/images/packages/overnight-camp.jpg', '#CD5C5C', 1),
(30, 'Morning Safari', 'Early morning jeep safari', 1, '/images/packages/morning-safari.jpg', '#8B0000', 1);



INSERT INTO destination_images (destination_id, name, description, image_url, status, created_by)
VALUES
(1, 'Kitulgala Rapids', 'White-water rafting on the Kelani River', '/images/destinations/kitulgala-rapids.jpg', 1, 1),
(1, 'Jungle Trek', 'Adventure trail through lush rainforest', '/images/destinations/jungle-trek.jpg', 1, 1),
(1, 'River Edge Camp', 'Camping spot beside Kelani River', '/images/destinations/river-edge-camp.jpg', 1, 1),
(2, 'World’s End View', 'Dramatic drop at Horton Plains National Park', '/images/destinations/worlds-end-View.jpg', 1, 1),
(2, 'Bakers Falls', 'Beautiful waterfall inside the park', '/images/destinations/bakers-falls.jpg', 1, 1),
(2, 'Grasslands', 'Vast plains with scenic hiking trails', '/images/destinations/grasslands.jpg', 1, 1),
(3, 'Knuckles Peaks', 'View of the Knuckles Mountain Range', '/images/destinations/knuckles-peaks.jpeg', 1, 1),
(3, 'Camping at Knuckles', 'Night camping with misty views', '/images/destinations/camping-at-knuckles.jpg', 1, 1),
(3, 'River Crossing', 'Adventure trekking near Knuckles rivers', '/images/destinations/river-crossing.webp', 1, 1),
(4, 'Adam’s Peak Sunrise', 'Spectacular sunrise view at the summit', '/images/destinations/adams-peak-sunrise.jpg', 1, 1),
(4, 'Sacred Steps', 'Pilgrims ascending Adam’s Peak', '/images/destinations/sacred-steps.jpg', 1, 1),
(4, 'Temple at Summit', 'Shrine at the peak with religious significance', '/images/destinations/temple-at-summit.jpg', 1, 1),
(5, 'Sinharaja Waterfall', 'Waterfall inside Sinharaja Forest Reserve', '/images/destinations/sinharaja-waterfall.jpg', 1, 1),
(5, 'Rainforest Trail', 'Dense forest trail with rich biodiversity', '/images/destinations/rainforest-trail.jpg', 1, 1),
(5, 'Tropical Flora', 'Rare plants and trees in Sinharaja', '/images/destinations/tropical-flora.jpg', 1, 1),
(6, 'Yala Safari', 'Leopard sighting during safari', '/images/destinations/yala-safari.jpg', 1, 1),
(6, 'Yala Landscape', 'Dry zone forest and lake view', '/images/destinations/yala-landscape.webp', 1, 1),
(6, 'Wildlife Close-up', 'Elephants and birds in Yala', '/images/destinations/wildlife-close-up.jpg', 1, 1),
(7, 'Udawalawe Elephants', 'Elephant herd near the lake', '/images/destinations/udawalawe-elephants.jpg', 1, 1),
(7, 'Safari Jeep Ride', 'Tourists on jeep exploring wildlife', '/images/destinations/safari-jeep-ride.jpg', 1, 1),
(7, 'Reservoir View', 'Panoramic Udawalawe reservoir', '/images/destinations/reservoir-view.png', 1, 1),
(8, 'Wilpattu Leopard', 'Leopard resting near waterhole', '/images/destinations/wilpattu-leopard.webp', 1, 1),
(8, 'Forest Tracks', 'Jeep track through Wilpattu forest', '/images/destinations/forest-tracks.jpg', 1, 1),
(8, 'Birdlife', 'Migratory birds near Villu lakes', '/images/destinations/birdlife.webp', 1, 1),
(9, 'Minneriya Elephants', 'The Great Elephant Gathering', '/images/destinations/minneriya-elephants.jpg', 1, 1),
(9, 'Reservoir Sunset', 'Sunset reflection at Minneriya lake', '/images/destinations/reservoir-sunset.webp', 1, 1),
(9, 'Wildlife Spotting', 'Tour jeep exploring the park', '/images/destinations/wildlife-spotting.jpg', 1, 1),
(10, 'Bundala Birds', 'Migratory flamingos in wetlands', '/images/destinations/bundala-birds.jpg', 1, 1),
(10, 'Coastal Lagoon', 'Scenic lagoon and dunes', '/images/destinations/coastal-lagoon.jpg', 1, 1),
(10, 'Salt Pans', 'Traditional salt fields near Bundala', '/images/destinations/salt-pans.jpg', 1, 1),
(11, 'Sigiriya Rock', 'Iconic rock fortress view', '/images/destinations/sigiriya-rock.jpg', 1, 1),
(11, 'Mirror Wall', 'Ancient frescoes and paintings', '/images/destinations/mirror-wall.jpg', 1, 1),
(11, 'Lion’s Paw Entrance', 'Stairway to the summit', '/images/destinations/lions-paw-entrance.jpg', 1, 1),
(12, 'Anuradhapura Stupa', 'Sacred Ruwanwelisaya Dagoba', '/images/destinations/anuradhapura-stupa.jpg', 1, 1),
(12, 'Sacred Bo Tree', 'Oldest documented tree in the world', '/images/destinations/sacred-bo-tree.jpg', 1, 1),
(12, 'Ancient Ruins', 'Stone carvings and historic sites', '/images/destinations/ancient-ruins.webp', 1, 1),
(13, 'Polonnaruwa Vatadage', 'Circular relic house of Polonnaruwa', '/images/destinations/polonnaruwa-vatadage.jpg', 1, 1),
(13, 'Gal Vihara', 'Famous rock-cut Buddha statues', '/images/destinations/gal-vihara.webp', 1, 1),
(13, 'Ancient City Walls', 'Ruins of the ancient kingdom', '/images/destinations/ancient-city-walls.jpg', 1, 1),
(14, 'Temple of the Tooth', 'Sacred temple in Kandy city', '/images/destinations/temple-of-the-tooth.webp', 1, 1),
(14, 'Kandy Lake', 'Beautiful lake at the city center', '/images/destinations/kandy-lake.jpg', 1, 1),
(14, 'Cultural Dance', 'Traditional Kandyan dance performance', '/images/destinations/cultural-dance.webp', 1, 1),
(15, 'Galle Fort Walls', 'Dutch fort overlooking the ocean', '/images/destinations/galle-fort-walls.jpg', 1, 1),
(15, 'Lighthouse View', 'Iconic Galle lighthouse', '/images/destinations/lighthouse-view.jpg', 1, 1),
(15, 'Old Streets', 'Colonial architecture and cafes', '/images/destinations/old-streets.webp', 1, 1),
(16, 'Mirissa Beach', 'Golden sands and calm waves', '/images/destinations/mirissa-beach.webp', 1, 1),
(16, 'Whale Watching', 'Blue whale spotting trip', '/images/destinations/whale-watching.jpg', 1, 1),
(16, 'Coconut Hill', 'Scenic hill viewpoint at Mirissa', '/images/destinations/coconut-hill.jpg', 1, 1),
(17, 'Unawatuna Bay', 'Turquoise waters and palm trees', '/images/destinations/unawatuna-bay.webp', 1, 1),
(17, 'Beachfront Cafes', 'Popular nightlife along the shore', '/images/destinations/beachfront-cafes.jpg', 1, 1),
(17, 'Coral Reef', 'Snorkeling at Unawatuna reef', '/images/destinations/coral-reef.webp', 1, 1),
(18, 'Bentota Beach', 'Resort-lined sandy coast', '/images/destinations/bentota-beach.jpg', 1, 1),
(18, 'River Boat Ride', 'Bentota River mangrove tour', '/images/destinations/river-boat-ride.jpg', 1, 1),
(18, 'Water Sports', 'Jet ski and banana boat rides', '/images/destinations/water-sports.webp', 1, 1),
(19, 'Trincomalee Bay', 'Scenic coastal harbor view', '/images/destinations/trincomalee-bay.jpg', 1, 1),
(19, 'Pigeon Island', 'Snorkeling at coral reef', '/images/destinations/pigeon-island.jpg', 1, 1),
(19, 'Koneswaram Temple', 'Clifftop Hindu temple view', '/images/destinations/koneswaram-temple.jpg', 1, 1),
(20, 'Arugam Bay Surfing', 'Surfers catching morning waves', '/images/destinations/arugam-bay-surfing.jpg', 1, 1),
(20, 'Beach Sunrise', 'Golden sunrise on the east coast', '/images/destinations/beach-sunrise.jpg', 1, 1),
(20, 'Local Chill Spots', 'Cafes and palm huts by the beach', '/images/destinations/local-chill-spots.jpg', 1, 1),
(21, 'Ella Rock View', 'Breathtaking panoramic views', '/images/destinations/ella-rock-view.jpg', 1, 1),
(21, 'Nine Arches Bridge', 'Train crossing the iconic bridge', '/images/destinations/nine-arches-bridge.jpg', 1, 1),
(21, 'Little Adam’s Peak', 'Popular short hike near Ella town', '/images/destinations/little-adams-peak.webp', 1, 1),
(22, 'Gregory Lake', 'Boating and lake views at Nuwara Eliya', '/images/destinations/gregory-lake.jpg', 1, 1),
(22, 'Tea Plantations', 'Scenic green tea fields', '/images/destinations/tea-plantations.jpg', 1, 1),
(22, 'Colonial Buildings', 'British-era architecture in town', '/images/destinations/colonial-buildings.jpg', 1, 1),
(23, 'Lipton’s Seat', 'Famous viewpoint at Haputale', '/images/destinations/liptons-seat.jpg', 1, 1),
(23, 'Tea Factory Visit', 'Tour through tea production', '/images/destinations/tea-factory-visit.jpg', 1, 1),
(23, 'Cloud Forest Trail', 'Misty forest walk in Haputale hills', '/images/destinations/cloud-forest-trail.jpg', 1, 1),
(24, 'Hakgala Gardens', 'Botanical garden with diverse flora', '/images/destinations/hakgala-gardens.jpg', 1, 1),
(24, 'Tea Hills', 'Rolling hills near Hakgala area', '/images/destinations/tea-hills.jpeg', 1, 1),
(24, 'Cool Mist Views', 'Foggy mornings in Nuwara Eliya hills', '/images/destinations/cool-mist-views.jpg', 1, 1),
(25, 'Dambatenne Estate', 'Historic tea estate with scenic views', '/images/destinations/dambatenne-estate.jpg', 1, 1),
(25, 'Tea Workers', 'Local workers harvesting tea leaves', '/images/destinations/tea-workers.jpg', 1, 1),
(25, 'Mountain Roads', 'Curvy roads through tea country', '/images/destinations/mountain-roads.jpg', 1, 1);


INSERT INTO destination_categories_images 
(destination_categories_id, name, description, image_url, status, created_by)
VALUES
(1, 'White Water Rafting', 'Adventurers rafting on the Kelani River in Kitulgala', '/images/destination-categories/white-water-rafting.jpg', 1, 1),
(1, 'Mountain Hiking', 'Trekkers on scenic trails in Knuckles Range', '/images/destination-categories/mountain-hiking.webp', 1, 1),
(1, 'Rock Climbing', 'Climber scaling a rock face at Ella Gap', '/images/destination-categories/rock-climbing.jpg', 1, 1),
(1, 'Forest Camping', 'Overnight tent camp surrounded by rainforest', '/images/destination-categories/forest-camping.jpg', 1, 1),
(1, 'Canyoning Adventure', 'Exploring waterfalls and natural rock pools', '/images/destination-categories/canyoning-adventure.webp', 1, 1),
(2, 'Yala Leopard', 'Leopard resting on a tree branch in Yala National Park', '/images/destination-categories/yala-leopard.jpg', 1, 1),
(2, 'Elephant Herd', 'Elephants grazing in Udawalawe Park', '/images/destination-categories/elephant-herd.jpg', 1, 1),
(2, 'Bird Watching', 'Flamingos and pelicans at Bundala wetlands', '/images/destination-categories/bird-watching.jpg', 1, 1),
(2, 'Safari Jeep', 'Tourists enjoying a jeep safari through the wild', '/images/destination-categories/safari-jeep.webp', 1, 1),
(2, 'Water Buffalo Scene', 'Peaceful moment at Wilpattu’s natural lakes', '/images/destination-categories/water-buffalo-scene.jpg', 1, 1),
(3, 'Temple of the Tooth', 'Kandy’s sacred temple illuminated at night', '/images/destination-categories/temple-of-the-tooth.jpg', 1, 1),
(3, 'Ancient Ruins', 'Historic ruins at Polonnaruwa', '/images/destination-categories/ancient-ruins.webp', 1, 1),
(3, 'Sigiriya Frescoes', 'Famous wall paintings of ancient maidens', '/images/destination-categories/sigiriya-frescoes.jpg', 1, 1),
(3, 'Kandy Perahera', 'Colorful traditional parade with elephants and dancers', '/images/destination-categories/kandy-perahera.jpg', 1, 1),
(3, 'Galle Fort Streets', 'Colonial architecture and cobblestone alleys', '/images/destination-categories/galle-fort-streets.jpg', 1, 1),
(4, 'Tropical Beach', 'Golden sand beach with palm trees', '/images/destination-categories/tropical-beach.jpg', 1, 1),
(4, 'Surfing Waves', 'Surfers catching waves at Arugam Bay', '/images/destination-categories/surfing-waves.jpg', 1, 1),
(4, 'Sunset View', 'Beautiful sunset at Mirissa Beach', '/images/destination-categories/sunset-view.jpg', 1, 1),
(4, 'Snorkeling Reef', 'Colorful coral reef near Trincomalee', '/images/destination-categories/snorkeling-reef.jpg', 1, 1),
(4, 'Beach Resort', 'Luxury seaside resort with coconut trees', '/images/destination-categories/beach-resort.jpg', 1, 1),
(5, 'Tea Plantations', 'Lush green tea fields in Nuwara Eliya', '/images/destination-categories/tea-plantations.jpg', 1, 1),
(5, 'Train through Hills', 'Scenic blue train crossing Nine Arches Bridge', '/images/destination-categories/train-through-hills.jpg', 1, 1),
(5, 'Waterfalls', 'Ravana Falls cascading down rocky cliffs', '/images/destination-categories/waterfalls.jpg', 1, 1),
(5, 'Mountain Viewpoint', 'Panoramic view of Ella Gap valley', '/images/destination-categories/mountain-viewpoint.webp', 1, 1),
(5, 'Cool Mist Morning', 'Foggy sunrise over Haputale hills', '/images/destination-categories/cool-mist-morning.jpg', 1, 1);


INSERT INTO activity_category_images 
(activity_category_id, name, description, image_url, status, created_by)
VALUES
-- Category 1: Adventure
(1, 'Rock Climbing', 'Adventurers scaling cliffs in Ella', '/images/activity_categories/adventure_1.jpg', 1, 1),
(1, 'Zip Lining', 'Thrilling zipline ride across lush valleys', '/images/activity_categories/adventure_2.jpg', 1, 1),
(1, 'Caving', 'Exploring mysterious caves and underground paths', '/images/activity_categories/adventure_3.jpg', 1, 1),
(1, 'Trekking Trail', 'Group trekking through dense forest terrain', '/images/activity_categories/adventure_4.jpg', 1, 1),

-- Category 2: Water Sports
(2, 'Surfing', 'Surfers riding the waves at Arugam Bay', '/images/activity_categories/watersports_1.jpg', 1, 1),
(2, 'Scuba Diving', 'Exploring coral reefs and marine life underwater', '/images/activity_categories/watersports_2.jpg', 1, 1),
(2, 'Jet Skiing', 'Tourists enjoying high-speed jet skiing', '/images/activity_categories/watersports_3.jpg', 1, 1),
(2, 'Snorkeling', 'Colorful reef fish viewed from surface snorkel', '/images/activity_categories/watersports_4.jpg', 1, 1),

-- Category 3: Wildlife
(3, 'Safari Jeep', 'Jeep driving through Yala National Park', '/images/activity_categories/wildlife_1.jpg', 1, 1),
(3, 'Bird Watching', 'Spotting exotic birds in Bundala Sanctuary', '/images/activity_categories/wildlife_2.jpg', 1, 1),
(3, 'Elephants in Wild', 'Herd of elephants grazing in Udawalawe', '/images/activity_categories/wildlife_3.jpg', 1, 1),
(3, 'Leopard Sighting', 'Rare leopard encounter at Wilpattu Park', '/images/activity_categories/wildlife_4.jpg', 1, 1),

-- Category 4: Marine Life
(4, 'Whale Watching', 'Whales breaching off Mirissa coast', '/images/activity_categories/marinelife_1.jpg', 1, 1),
(4, 'Dolphin Tour', 'Dolphins swimming alongside boats', '/images/activity_categories/marinelife_2.jpg', 1, 1),
(4, 'Sea Turtle Experience', 'Visitors observing sea turtles nesting', '/images/activity_categories/marinelife_3.jpg', 1, 1),
(4, 'Underwater World', 'Colorful marine species in coral reefs', '/images/activity_categories/marinelife_4.jpg', 1, 1),

-- Category 5: Sightseeing
(5, 'Scenic Viewpoint', 'Tourists enjoying panoramic mountain views', '/images/activity_categories/sightseeing_1.jpg', 1, 1),
(5, 'City Tour', 'Exploring historic streets and monuments', '/images/activity_categories/sightseeing_2.jpg', 1, 1),
(5, 'Sunset Point', 'Beautiful sunset over coastal cliffs', '/images/activity_categories/sightseeing_3.jpg', 1, 1),
(5, 'Waterfall Visit', 'Tourists visiting a majestic waterfall', '/images/activity_categories/sightseeing_4.jpg', 1, 1),

-- Category 6: Hiking
(6, 'Mountain Trail', 'Hikers climbing misty mountain paths', '/images/activity_categories/hiking_1.jpg', 1, 1),
(6, 'Forest Path', 'Exploring dense forest hiking trails', '/images/activity_categories/hiking_2.jpg', 1, 1),
(6, 'Peak Summit', 'Trekkers reaching the top viewpoint', '/images/activity_categories/hiking_3.jpg', 1, 1),
(6, 'River Crossing', 'Adventure hikers crossing shallow streams', '/images/activity_categories/hiking_4.jpg', 1, 1),

-- Category 7: Cultural
(7, 'Temple Visit', 'Visitors exploring sacred temples', '/images/activity_categories/cultural_1.jpg', 1, 1),
(7, 'Traditional Dance', 'Cultural dancers performing in Kandy', '/images/activity_categories/cultural_2.jpg', 1, 1),
(7, 'Ancient Ruins', 'Tourists discovering heritage ruins', '/images/activity_categories/cultural_3.jpg', 1, 1),
(7, 'Cultural Festival', 'Vibrant street festival with costumes and music', '/images/activity_categories/cultural_4.jpg', 1, 1),

-- Category 8: Wellness
(8, 'Yoga Retreat', 'Morning yoga session in a peaceful environment', '/images/activity_categories/wellness_1.jpg', 1, 1),
(8, 'Meditation', 'Meditation practice near a waterfall', '/images/activity_categories/wellness_2.jpg', 1, 1),
(8, 'Spa Therapy', 'Relaxing herbal spa treatments', '/images/activity_categories/wellness_3.jpg', 1, 1),
(8, 'Ayurvedic Massage', 'Traditional ayurvedic healing experience', '/images/activity_categories/wellness_4.jpg', 1, 1),

-- Category 9: Photography
(9, 'Wildlife Photography', 'Photographer capturing animals in the wild', '/images/activity_categories/photography_1.jpg', 1, 1),
(9, 'Landscape Shot', 'Capturing breathtaking sunrise over hills', '/images/activity_categories/photography_2.jpg', 1, 1),
(9, 'Cultural Portraits', 'Portrait photography during cultural events', '/images/activity_categories/photography_3.jpg', 1, 1),
(9, 'Night Photography', 'Stars and night sky captured in long exposure', '/images/activity_categories/photography_4.jpg', 1, 1),

-- Category 10: Food & Dining
(10, 'Local Cuisine', 'Authentic Sri Lankan rice and curry served on banana leaf', '/images/activity_categories/food_1.jpg', 1, 1),
(10, 'Cooking Class', 'Tourists learning traditional cooking methods', '/images/activity_categories/food_2.jpg', 1, 1),
(10, 'Street Food', 'Colorful local food stalls in Colombo', '/images/activity_categories/food_3.jpg', 1, 1),
(10, 'Fine Dining', 'Elegant restaurant setup with local dishes', '/images/activity_categories/food_4.jpg', 1, 1);





INSERT INTO activities_images 
(activity_id, name, description, image_url, status, created_by)
VALUES
-- Activity 1: Sigiriya Rock Climb
(1, 'Sigiriya Rock Summit', 'View from the top of Sigiriya Rock', '/images/activities/sigiriya_rockclimb_1.jpg', 1, 1),
(1, 'Sigiriya Rock Path', 'Steps and climbing path to the summit', '/images/activities/sigiriya_rockclimb_2.jpg', 1, 1),

-- Activity 2: Knuckles Mountain Trek
(2, 'Knuckles Trail View', 'Scenic trail in Knuckles Range', '/images/activities/knuckles_trek_1.jpg', 1, 1),
(2, 'Knuckles Forest', 'Dense forest along trekking path', '/images/activities/knuckles_trek_2.jpg', 1, 1),

-- Activity 3: Kitulgala White Water Rafting
(3, 'Rafting Rapids', 'Adventure on Kitulgala river rapids', '/images/activities/kitulgala_rafting_1.jpg', 1, 1),
(3, 'Rafting Team', 'Group navigating the rapids', '/images/activities/kitulgala_rafting_2.jpg', 1, 1),

-- Activity 4: Knuckles Overnight Camping
(4, 'Knuckles Camp Setup', 'Tent and campfire at Knuckles mountains', '/images/activities/knuckles_camping_1.jpg', 1, 1),
(4, 'Starry Night', 'Night sky view during camping', '/images/activities/knuckles_camping_2.jpg', 1, 1),

-- Activity 5: Surfing Lessons
(5, 'Surf Lessons Beach', 'Beginner surfing session', '/images/activities/surfing_lessons_1.jpg', 1, 1),
(5, 'Surf Instructor', 'Instructor guiding students', '/images/activities/surfing_lessons_2.jpg', 1, 1),

-- Activity 6: Kayaking in Bentota River
(6, 'Kayak on River', 'Tourist paddling along Bentota River', '/images/activities/kayaking_bentota_1.jpg', 1, 1),
(6, 'River View', 'Scenic river and surrounding greenery', '/images/activities/kayaking_bentota_2.jpg', 1, 1),

-- Activity 7: Arugam Bay Surf Adventure
(7, 'Arugam Bay Waves', 'Surfers catching waves at Arugam Bay', '/images/activities/arugam_surf_1.jpg', 1, 1),
(7, 'Surf Team', 'Group of surfers enjoying the ocean', '/images/activities/arugam_surf_2.jpg', 1, 1),

-- Activity 8: Mirissa Jet Skiing
(8, 'Jet Ski Splash', 'High-speed jet ski ride on Mirissa beach', '/images/activities/mirissa_jetski_1.jpg', 1, 1),
(8, 'Coastal View', 'Rider speeding along the coastline', '/images/activities/mirissa_jetski_2.jpg', 1, 1),

-- Activity 9: Yala Safari
(9, 'Yala Leopard Spotting', 'Leopard seen in Yala National Park', '/images/activities/yala_safari_1.jpg', 1, 1),
(9, 'Elephant Herd', 'Elephants crossing the trail during safari', '/images/activities/yala_safari_2.jpg', 1, 1),

-- Activity 10: Udawalawe Elephant Safari
(10, 'Elephant Closeup', 'Close encounter with wild elephants', '/images/activities/udawalawe_safari_1.jpg', 1, 1),
(10, 'Safari Jeep', 'Jeep moving through Udawalawe National Park', '/images/activities/udawalawe_safari_2.jpg', 1, 1),

-- Activity 11: Wilpattu Leopard Safari
(11, 'Wilpattu Leopard', 'Leopard resting in Wilpattu National Park', '/images/activities/wilpattu_safari_1.jpg', 1, 1),
(11, 'Bird Watching', 'Birds in the Wilpattu park scenery', '/images/activities/wilpattu_safari_2.jpg', 1, 1),

-- Activity 12: Minneriya Elephant Gathering
(12, 'Elephant Gathering', 'Mass elephant gathering in Minneriya', '/images/activities/minneriya_elephants_1.jpg', 1, 1),
(12, 'Sunset View', 'Elephants silhouetted against sunset', '/images/activities/minneriya_elephants_2.jpg', 1, 1),

-- Activity 13: Whale Watching
(13, 'Blue Whale Spotting', 'Whale surfacing in ocean', '/images/activities/whale_watching_1.jpg', 1, 1),
(13, 'Whale Watching Boat', 'Tourists on boat spotting whales', '/images/activities/whale_watching_2.jpg', 1, 1),

-- Activity 14: Mirissa Dolphin Cruise
(14, 'Playful Dolphins', 'Dolphins jumping near the boat', '/images/activities/mirissa_dolphins_1.jpg', 1, 1),
(14, 'Dolphin Tour', 'Cruise enjoying marine wildlife', '/images/activities/mirissa_dolphins_2.jpg', 1, 1),

-- Activity 15: Pigeon Island Snorkeling
(15, 'Coral Reefs', 'Snorkeling among vibrant coral reefs', '/images/activities/pigeon_snorkeling_1.jpg', 1, 1),
(15, 'Tropical Fish', 'Colorful fish seen while snorkeling', '/images/activities/pigeon_snorkeling_2.jpg', 1, 1),

-- Activity 16: Bentota Glass-Bottom Boat
(16, 'Glass-Bottom View', 'View marine life through glass-bottom boat', '/images/activities/bentota_glassboat_1.jpg', 1, 1),
(16, 'Marine Species', 'Fish and coral visible under boat', '/images/activities/bentota_glassboat_2.jpg', 1, 1),

-- Activity 17: Nine Arch Bridge Visit
(17, 'Nine Arch Bridge', 'View of iconic Nine Arch Bridge', '/images/activities/nine_arch_1.jpg', 1, 1),
(17, 'Train Crossing', 'Train crossing Nine Arch Bridge', '/images/activities/nine_arch_2.jpg', 1, 1),

-- Activity 18: Galle Fort Tour
(18, 'Galle Fort Walls', 'UNESCO fort walls and streets', '/images/activities/galle_fort_1.jpg', 1, 1),
(18, 'Historic Streets', 'Walking tour inside Galle Fort', '/images/activities/galle_fort_2.jpg', 1, 1),

-- Activity 19: Nuwara Eliya City Tour
(19, 'Gregory Lake', 'Tourist enjoying boat ride at Gregory Lake', '/images/activities/nuwara_city_1.jpg', 1, 1),
(19, 'Botanical Gardens', 'Colorful flowers at botanical gardens', '/images/activities/nuwara_city_2.jpg', 1, 1),

-- Activity 20: Jaffna Heritage Walk
(20, 'Jaffna Temple', 'Historic temple in Jaffna city', '/images/activities/jaffna_walk_1.jpg', 1, 1),
(20, 'Street Exploration', 'Walking along local streets of Jaffna', '/images/activities/jaffna_walk_2.jpg', 1, 1),

-- Activity 21: Little Adams Peak Hike
(21, 'Little Adams Peak', 'View from top of Little Adams Peak', '/images/activities/little_adams_1.jpg', 1, 1),
(21, 'Hiking Path', 'Trail leading up Little Adams Peak', '/images/activities/little_adams_2.jpg', 1, 1),

-- Activity 22: Pidurangala Rock Hike
(22, 'Pidurangala Summit', 'View from Pidurangala Rock', '/images/activities/pidurangala_1.jpg', 1, 1),
(22, 'Climb Path', 'Pathway up Pidurangala Rock', '/images/activities/pidurangala_2.jpg', 1, 1),

-- Activity 23: Horton Plains Trail
(23, 'Worlds End View', 'Panoramic view from Horton Plains', '/images/activities/horton_trail_1.jpg', 1, 1),
(23, 'Baker\'s Falls', 'Waterfall along Horton Plains trail', '/images/activities/horton_trail_2.jpg', 1, 1),

-- Activity 24: Ella Rock Hike
(24, 'Ella Rock Summit', 'Scenic view from Ella Rock', '/images/activities/ella_rock_1.jpg', 1, 1),
(24, 'Tea Plantation Trail', 'Trail passing through tea plantations', '/images/activities/ella_rock_2.jpg', 1, 1),

-- Activity 25: Kandy Temple of Tooth
(25, 'Temple of Tooth Exterior', 'Main entrance and exterior of temple', '/images/activities/kandy_temple_1.jpg', 1, 1),
(25, 'Temple Interior', 'Inner sanctum and sacred relics', '/images/activities/kandy_temple_2.jpg', 1, 1),

-- Activity 26: Kandy Perahera Experience
(26, 'Elephant Procession', 'Decorated elephants during Perahera festival', '/images/activities/kandy_perahera_1.jpg', 1, 1),
(26, 'Cultural Dance', 'Traditional dancers performing', '/images/activities/kandy_perahera_2.jpg', 1, 1),

-- Activity 27: Polonnaruwa Ancient City
(27, 'Ancient Ruins', 'Historical ruins in Polonnaruwa', '/images/activities/polonnaruwa_1.jpg', 1, 1),
(27, 'Temple Site', 'Buddhist temple ruins', '/images/activities/polonnaruwa_2.jpg', 1, 1),

-- Activity 28: Dambulla Cave Temples
(28, 'Cave Temple Interior', 'Buddhist statues inside cave', '/images/activities/dambulla_1.jpg', 1, 1),
(28, 'Temple Exterior', 'Entrance to Dambulla Cave Temples', '/images/activities/dambulla_2.jpg', 1, 1),

-- Activity 29: Bentota Yoga Session
(29, 'Morning Yoga', 'Yoga session at sunrise on beach', '/images/activities/bentota_yoga_1.jpg', 1, 1),
(29, 'Yoga Pose', 'Participants performing yoga poses', '/images/activities/bentota_yoga_2.jpg', 1, 1),

-- Activity 30: Ayurvedic Massage
(30, 'Massage Room', 'Traditional Ayurvedic massage setup', '/images/activities/ayurvedic_massage_1.jpg', 1, 1),
(30, 'Massage Therapy', 'Therapist performing massage', '/images/activities/ayurvedic_massage_2.jpg', 1, 1),

-- Activity 31: Dambulla Meditation Retreat
(31, 'Meditation Hall', 'Indoor meditation session', '/images/activities/dambulla_meditation_1.jpg', 1, 1),
(31, 'Relaxation Garden', 'Meditation in serene garden', '/images/activities/dambulla_meditation_2.jpg', 1, 1),

-- Activity 32: Bentota Spa Package
(32, 'Spa Interior', 'Luxurious spa treatment room', '/images/activities/bentota_spa_1.jpg', 1, 1),
(32, 'Massage & Therapy', 'Spa therapist with client', '/images/activities/bentota_spa_2.jpg', 1, 1),

-- Activity 33: Ella Sunrise Photography
(33, 'Sunrise View', 'Sun rising over Ella landscape', '/images/activities/ella_sunrise_1.jpg', 1, 1),
(33, 'Photography Spot', 'Tourist taking photos at viewpoint', '/images/activities/ella_sunrise_2.jpg', 1, 1),

-- Activity 34: Trincomalee Coastal Photography
(34, 'Trincomalee Beach', 'Photographing pristine coastline', '/images/activities/trinco_coast_1.jpg', 1, 1),
(34, 'Marine Life Photography', 'Capturing ocean wildlife', '/images/activities/trinco_coast_2.jpg', 1, 1),

-- Activity 35: Mirissa Sunset Shoot
(35, 'Sunset Beach', 'Sunset over Mirissa beach', '/images/activities/mirissa_sunset_1.jpg', 1, 1),
(35, 'Golden Hour', 'Photographer capturing golden hour', '/images/activities/mirissa_sunset_2.jpg', 1, 1),

-- Activity 36: Nuwara Eliya Landscape Photography
(36, 'Tea Estate View', 'Photographing tea plantations', '/images/activities/nuwara_landscape_1.jpg', 1, 1),
(36, 'Waterfall Scene', 'Capturing waterfall scenery', '/images/activities/nuwara_landscape_2.jpg', 1, 1),

-- Activity 37: Jaffna Food Tour
(37, 'Street Food Stall', 'Local cuisine tasting in Jaffna', '/images/activities/jaffna_food_1.jpg', 1, 1),
(37, 'Food Sampling', 'Tasting traditional dishes', '/images/activities/jaffna_food_2.jpg', 1, 1),

-- Activity 38: Kandy Street Food Walk
(38, 'Local Snacks', 'Sampling street food in Kandy', '/images/activities/kandy_food_1.jpg', 1, 1),
(38, 'Food Vendor', 'Vendor preparing local delicacies', '/images/activities/kandy_food_2.jpg', 1, 1),

-- Activity 39: Yala Safari Picnic
(39, 'Safari Picnic', 'Picnic setup during Yala safari', '/images/activities/yala_picnic_1.jpg', 1, 1),
(39, 'Wildlife Dining', 'Enjoying picnic with wildlife view', '/images/activities/yala_picnic_2.jpg', 1, 1),

-- Activity 40: Bentota Seafood Tasting
(40, 'Seafood Platter', 'Fresh seafood served at beachside', '/images/activities/bentota_seafood_1.jpg', 1, 1),
(40, 'Dining View', 'Table setup with ocean view', '/images/activities/bentota_seafood_2.jpg', 1, 1),

-- Activity 41: Bentota Yoga Session
(41, 'Bentota Yoga Morning', 'Morning yoga by the beach', '/images/activities/bentota_yoga_1.jpg', 1, 1),
(41, 'Bentota Yoga Group', 'Group yoga session on the sand', '/images/activities/bentota_yoga_2.jpg', 1, 1),

-- Activity 42: Ayurvedic Massage
(42, 'Ayurvedic Massage Treatment', 'Traditional full body massage', '/images/activities/ayurvedic_massage_1.jpg', 1, 1),
(42, 'Massage Room Setup', 'Ambience of Ayurvedic spa room', '/images/activities/ayurvedic_massage_2.jpg', 1, 1),

-- Activity 43: Dambulla Meditation Retreat
(43, 'Dambulla Meditation Session', 'Guided meditation indoors', '/images/activities/dambulla_meditation_1.jpg', 1, 1),
(43, 'Outdoor Meditation Area', 'Meditation in nature', '/images/activities/dambulla_meditation_2.jpg', 1, 1),

-- Activity 44: Bentota Spa Package
(44, 'Spa Relaxation', 'Full spa day with treatments', '/images/activities/bentota_spa_1.jpg', 1, 1),
(44, 'Spa Therapy Room', 'Luxury spa ambience', '/images/activities/bentota_spa_2.jpg', 1, 1),

-- Activity 45: Ella Sunrise Photography
(45, 'Ella Sunrise Photo', 'Capturing sunrise over tea plantations', '/images/activities/ella_sunrise_1.jpg', 1, 1),
(45, 'Photographers at Work', 'Group photography session', '/images/activities/ella_sunrise_2.jpg', 1, 1),

-- Activity 46: Trincomalee Coastal Photography
(46, 'Trinco Coast Photography', 'Shooting pristine beaches', '/images/activities/trinco_coast_1.jpg', 1, 1),
(46, 'Marine Life Photography', 'Photographing coral reefs', '/images/activities/trinco_coast_2.jpg', 1, 1),

-- Activity 47: Mirissa Sunset Shoot
(47, 'Mirissa Sunset', 'Capturing sunset over the beach', '/images/activities/mirissa_sunset_1.jpg', 1, 1),
(47, 'Sunset Silhouettes', 'Photography of silhouettes at sunset', '/images/activities/mirissa_sunset_2.jpg', 1, 1),

-- Activity 48: Nuwara Eliya Landscape Photography
(48, 'Nuwara Eliya Tea Fields', 'Capturing tea estates and hills', '/images/activities/nuwara_eliya_1.jpg', 1, 1),
(48, 'Waterfall Photography', 'Scenic waterfalls in Nuwara Eliya', '/images/activities/nuwara_eliya_2.jpg', 1, 1);



INSERT INTO activity_category_images
(activity_category_id, name, description, image_url, status, created_by)
VALUES
-- Adventure
(1, 'Mountain Climbing', 'Climbers scaling rocky peaks', '/images/activity_categories/adventure_1.jpg', 1, 1),
(1, 'Forest Trekking', 'Hikers walking through lush forests', '/images/activity_categories/adventure_2.jpg', 1, 1),
(1, 'Adventure Camp', 'Camping and outdoor adventure setup', '/images/activity_categories/adventure_3.jpg', 1, 1),

-- Water Sports
(2, 'Surfing Fun', 'People surfing on ocean waves', '/images/activity_categories/watersports_1.jpg', 1, 1),
(2, 'Kayaking', 'Tourists kayaking along rivers', '/images/activity_categories/watersports_2.jpg', 1, 1),
(2, 'Scuba Diving', 'Exploring underwater marine life', '/images/activity_categories/watersports_3.jpg', 1, 1),

-- Wildlife
(3, 'Safari Jeep', 'Wildlife safari with jeep tour', '/images/activity_categories/wildlife_1.jpg', 1, 1),
(3, 'Elephant Herd', 'Elephants roaming freely in national parks', '/images/activity_categories/wildlife_2.jpg', 1, 1),
(3, 'Bird Watching', 'Spotting exotic birds in their habitat', '/images/activity_categories/wildlife_3.jpg', 1, 1),

-- Marine Life
(4, 'Whale Watching', 'Tourists spotting whales at sea', '/images/activity_categories/marine_1.jpg', 1, 1),
(4, 'Dolphin Cruise', 'Boat cruise to observe playful dolphins', '/images/activity_categories/marine_2.jpg', 1, 1),
(4, 'Snorkeling Reef', 'Snorkeling among colorful coral reefs', '/images/activity_categories/marine_3.jpg', 1, 1),

-- Sightseeing
(5, 'Historic Landmark', 'Tourists visiting famous landmarks', '/images/activity_categories/sightseeing_1.jpg', 1, 1),
(5, 'Scenic Viewpoint', 'Overlooking breathtaking landscapes', '/images/activity_categories/sightseeing_2.jpg', 1, 1),
(5, 'City Tour', 'Exploring city streets and heritage sites', '/images/activity_categories/sightseeing_3.jpg', 1, 1),

-- Hiking
(6, 'Mountain Trail', 'Hikers on a trail through mountains', '/images/activity_categories/hiking_1.jpg', 1, 1),
(6, 'Nature Walk', 'Walking along scenic nature trails', '/images/activity_categories/hiking_2.jpg', 1, 1),
(6, 'Sunrise Hike', 'Early morning hike with sunrise view', '/images/activity_categories/hiking_3.jpg', 1, 1),

-- Cultural
(7, 'Temple Visit', 'Tourists visiting ancient temples', '/images/activity_categories/cultural_1.jpg', 1, 1),
(7, 'Cultural Dance', 'Traditional dance performance', '/images/activity_categories/cultural_2.jpg', 1, 1),
(7, 'Heritage Site', 'Exploring historical and cultural sites', '/images/activity_categories/cultural_3.jpg', 1, 1),

-- Wellness
(8, 'Yoga Session', 'Morning yoga session in nature', '/images/activity_categories/wellness_1.jpg', 1, 1),
(8, 'Meditation Retreat', 'Relaxation and meditation practice', '/images/activity_categories/wellness_2.jpg', 1, 1),
(8, 'Spa Therapy', 'Wellness spa treatment session', '/images/activity_categories/wellness_3.jpg', 1, 1),

-- Photography
(9, 'Landscape Photography', 'Capturing scenic landscapes', '/images/activity_categories/photography_1.jpg', 1, 1),
(9, 'Wildlife Photography', 'Photographing animals in the wild', '/images/activity_categories/photography_2.jpg', 1, 1),
(9, 'Sunset Photography', 'Photographing colorful sunsets', '/images/activity_categories/photography_3.jpg', 1, 1),

-- Food & Dining
(10, 'Cooking Class', 'Learning to cook local dishes', '/images/activity_categories/food_1.jpg', 1, 1),
(10, 'Street Food', 'Tasting popular local street food', '/images/activity_categories/food_2.jpg', 1, 1),
(10, 'Gourmet Dining', 'Enjoying fine dining experiences', '/images/activity_categories/food_3.jpg', 1, 1);






INSERT INTO tour_history_images (tour_schedule_id, name, description, status, image_url, color, created_by) VALUES
(1, 'Sigiriya Group Oct 15', 'Group photo at Sigiriya fortress base', 1, '/images/tour_history/sigiriya_oct15_group.jpg', '#FF6B6B', 1),
(1, 'Sigiriya Summit View', 'View captured during October tour', 1, '/images/tour_history/sigiriya_oct15_summit.jpg', '#FF6B6B', 1),
(2, 'Ella Train Ride', 'Scenic train journey November batch', 1, '/images/tour_history/ella_nov_train.jpg', '#4ECDC4', 1),
(2, 'Ella Nine Arch Bridge', 'Group at Nine Arch Bridge', 1, '/images/tour_history/ella_nov_bridge.jpg', '#4ECDC4', 1),
(2, 'Ella Accommodation', 'Beautiful hotel with mountain view', 1, '/images/tour_history/ella_nov_hotel.jpg', '#4ECDC4', 1);

-- 25c. Insert Sample Images - Activities History
INSERT INTO activities_history_images (activities_history_id, name, description, image_url, status, created_by) VALUES
(1, 'Sunrise at Sigiriya', 'Beautiful sunrise during morning climb', '/images/activity_history/sigiriya_sunrise.jpg', 1, 1),
(1, 'Group at Summit', 'Participants at the top', '/images/activity_history/sigiriya_group_top.jpg', 1, 1),
(1, 'Ancient Frescoes', 'Famous Sigiriya frescoes', '/images/activity_history/sigiriya_frescoes.jpg', 1, 1),
(2, 'Leopard Sighting', 'One of two leopards spotted', '/images/activity_history/yala_leopard1.jpg', 1, 1),
(2, 'Second Leopard', 'Rare second leopard on same safari', '/images/activity_history/yala_leopard2.jpg', 1, 1),
(2, 'Elephant Herd', 'Large elephant herd near waterhole', '/images/activity_history/yala_elephants.jpg', 1, 1);

-- 25d. Insert Sample Images - Package History
INSERT INTO package_history_images (package_schedule_id, name, description, image_url, status, created_by) VALUES
(1, 'Sigiriya Package Memories 1', 'Participants enjoying the tour', '/images/package_history/sigiriya_pkg_mem1.jpg', 1, 1),
(1, 'Sigiriya Package Memories 2', 'Lunch break with traditional food', '/images/package_history/sigiriya_pkg_mem2.jpg', 1, 1),
(2, 'Ella Package Day 1', 'Arrival and hotel check-in', '/images/package_history/ella_pkg_day1.jpg', 1, 1),
(2, 'Ella Package Day 2', 'Hiking and exploring', '/images/package_history/ella_pkg_day2.jpg', 1, 1),
(2, 'Ella Package Day 3', 'Final day breakfast view', '/images/package_history/ella_pkg_day3.jpg', 1, 1),
(3, 'Yala Safari Package', 'Morning safari departure', '/images/package_history/yala_pkg_safari.jpg', 1, 1),
(3, 'Yala Camping Experience', 'Luxury camping setup', '/images/package_history/yala_pkg_camp.jpg', 1, 1);

-- 26. Insert Sample Images - Reviews
INSERT INTO tour_review_images (tour_review_id, name, description, image_url, status, created_by) VALUES
(1, 'Guest Photo - Sigiriya Top', 'Photo taken by guest at summit', '/images/reviews/guest_sigiriya.jpg', 1, 1);

INSERT INTO activities_review_images (activities_review_id, name, description, image_url, status, created_by) VALUES
(1, 'Guest Sunrise Photo', 'Beautiful sunrise captured by guest', '/images/reviews/guest_sunrise.jpg', 1, 1);

INSERT INTO package_review_images (package_review_id, name, description, image_url, status, created_by) VALUES
(1, 'Happy Guests', 'Group photo with guide', '/images/reviews/happy_guests.jpg', 1, 1);

INSERT INTO popular_destination (destination_id, rating, popularity, created_by)
VALUES
(1, 4.8, 95, 1), -- Sigiriya
(2, 4.7, 90, 1), -- Ella
(3, 4.6, 85, 1), -- Yala National Park
(4, 4.5, 80, 1), -- Mirissa
(5, 4.4, 70, 1), -- Anuradhapura
(6, 4.3, 65, 1); -- Nuwara Eliya





-- 1: Sigiriya Rock Fortress Adventure (Adventure + Solo)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(1, 11), -- Sigiriya
(1, 12); -- Anuradhapura (nearby, often on the route)

-- 2: Cultural Triangle Tour (Cultural + Budget)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(2, 12), -- Anuradhapura
(2, 13), -- Polonnaruwa
(2, 11), -- Sigiriya
(2, 4);  -- Dambulla (cave temples, often on the way)

-- 3: Yala Wildlife Safari (Wildlife + Family)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(3, 6), -- Yala National Park
(3, 5); -- Bundala National Park (on the way south coast safari route)

-- 4: Mirissa Beach Retreat (Beach + Group)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(4, 14), -- Mirissa
(4, 15), -- Unawatuna (nearby)
(4, 16); -- Galle Fort (cultural stop nearby)

-- 5: Ella Hill Country Explorer (Adventure + Family)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(5, 16), -- Ella
(5, 17), -- Nuwara Eliya
(5, 18), -- Haputale
(5, 20), -- Dambatenne
(5, 3);  -- Knuckles Mountain Range (optional detour for trekking)

-- 6: Bentota Spa & Wellness Escape (Wellness + Luxury)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(6, 15), -- Bentota
(6, 19), -- Nuwara Eliya Hakgala (for spa and tea garden experience)
(6, 14); -- Mirissa (beach relaxation optional stop)

-- 7: Kandy Temple & Cultural Dance Tour (Cultural + Family)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(7, 14), -- Kandy
(7, 12), -- Anuradhapura (if route extends north)
(7, 11); -- Sigiriya (optional heritage stop)

-- 8: Knuckles Mountain Hiking (Adventure + Group)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(8, 3), -- Knuckles Mountain Range
(8, 2); -- Horton Plains (nearby trekking spot)

-- 9: Wilpattu Safari Experience (Wildlife + Luxury)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(9, 8), -- Wilpattu National Park
(9, 13); -- Polonnaruwa (on the route north-east)

-- 10: Arugam Bay Surf Trip (Beach + Solo)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(10, 19), -- Arugam Bay
(10, 18); -- Trincomalee (optional east coast stop)

-- 11: Ayurvedic Healing Retreat in Dambulla (Wellness + Group)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(11, 4), -- Dambulla
(11, 11); -- Sigiriya (heritage site nearby)

-- 12: Jaffna Heritage & Food Tour (Cultural + Luxury)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(12, 13), -- Jaffna (main destination)
(12, 12); -- Anuradhapura (on the route south)

-- 13: Kitulgala White Water Rafting (Adventure + Budget)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(13, 1), -- Kitulgala
(13, 5); -- Sinharaja Forest (optional jungle stop nearby)

-- 14: Trincomalee Beach & Snorkeling (Beach + Family)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(14, 18), -- Trincomalee
(14, 19), -- Arugam Bay
(14, 10); -- Nilaveli Beach / Pigeon Island (near Trincomalee)

-- 15: Udawalawe Elephant Safari (Wildlife + Solo)
INSERT INTO tour_destination (tour_id, destination_id) VALUES 
(15, 7), -- Udawalawe National Park
(15, 5), -- Bundala National Park (on the way to southern safari circuit)
(15, 6); -- Yala National Park (optional if extended safari)















