use travelagencydb;

-- 1. Insert Seasons
INSERT INTO seasons (name, description, status, created_by) VALUES
('Summer', 'Hot and sunny season, ideal for beach activities', 1, 1),
('Winter', 'Cool season, perfect for hill country tours', 1, 1),
('Monsoon', 'Rainy season, great for waterfalls and lush greenery', 1, 1),
('Spring', 'Pleasant weather, blooming season', 1, 1);

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

-- 4. Insert Package Types
INSERT INTO package_type (name, description, status, created_by) VALUES
('All-Inclusive', 'Everything included - meals, transport, activities', 1, 1),
('Half-Board', 'Accommodation and some meals included', 1, 1),
('Custom', 'Customizable package based on preferences', 1, 1),
('Day Trip', 'Single day excursion packages', 1, 1);

-- 5. Insert Tours
INSERT INTO tour (name, description, tour_type, tour_category, duration, latitude, longitude, start_location, end_location, season, status, created_by) VALUES
('Sigiriya Rock Fortress Adventure', 'Climb the ancient rock fortress and explore UNESCO heritage site', 1, 3, 1, 7.9570, 80.7603, 'Colombo', 'Sigiriya', 1, 1, 1),
('Ella Hill Country Explorer', 'Scenic train journey through tea plantations and waterfalls', 1, 1, 3, 6.8667, 81.0467, 'Kandy', 'Ella', 4, 1, 1),
('Yala Wildlife Safari', 'Spot leopards, elephants and diverse wildlife in national park', 3, 3, 2, 6.3725, 81.5194, 'Tissamaharama', 'Yala', 2, 1, 1),
('Mirissa Beach Retreat', 'Relax on pristine beaches and enjoy whale watching', 4, 1, 2, 5.9450, 80.4489, 'Galle', 'Mirissa', 1, 1, 1),
('Cultural Triangle Tour', 'Visit ancient cities: Anuradhapura, Polonnaruwa, Sigiriya', 2, 2, 5, 8.3114, 80.4037, 'Colombo', 'Anuradhapura', 2, 1, 1);

-- 6. Insert Packages
INSERT INTO packages (tour_id, name, description, total_price, discount_percentage, start_date, end_date, color, status, hover_color, min_person_count, max_person_count, price_per_person, created_by) VALUES
(1, 'Sigiriya Day Package', 'Full day tour with guide and transport', 15000.00, 10.00, '2025-10-01', '2026-03-31', '#FF6B6B', 1, '#FF5252', 2, 8, 7500.00, 1),
(2, 'Ella 3-Day Adventure', 'Three days exploring Ella with accommodation', 45000.00, 15.00, '2025-10-01', '2026-04-30', '#4ECDC4', 1, '#3BB8B0', 2, 6, 22500.00, 1),
(3, 'Yala Safari Experience', 'Two-day safari with luxury camping', 35000.00, 5.00, '2025-11-01', '2026-02-28', '#95E1D3', 1, '#7DD4C5', 2, 10, 17500.00, 1),
(4, 'Mirissa Beach Escape', 'Relaxing beach holiday with water sports', 40000.00, 20.00, '2025-10-15', '2026-03-15', '#F38181', 1, '#F06868', 2, 4, 20000.00, 1),
(5, 'Cultural Triangle 5-Day', 'Complete cultural experience with expert guide', 75000.00, 12.00, '2025-10-01', '2026-05-31', '#AA96DA', 1, '#9580D0', 4, 12, 18750.00, 1);

-- 7. Insert Destination Categories
INSERT INTO destination_categories (category, description, status, created_by) VALUES
('Historical Sites', 'Ancient ruins and archaeological sites', 1, 1),
('Natural Wonders', 'Mountains, waterfalls, and natural formations', 1, 1),
('Beaches', 'Coastal destinations and beach resorts', 1, 1),
('National Parks', 'Wildlife reserves and protected areas', 1, 1),
('Cities', 'Urban destinations and towns', 1, 1);

-- 8. Insert Destinations
INSERT INTO destination (name, description, status, destination_category, location, latitude, longitude, created_by) VALUES
('Sigiriya', 'Ancient rock fortress and UNESCO World Heritage site', 1, 1, 'Central Province', 7.9570, 80.7603, 1),
('Ella', 'Picturesque hill country town with stunning views', 1, 2, 'Uva Province', 6.8667, 81.0467, 1),
('Yala National Park', 'Most visited and second largest national park', 1, 4, 'Southern Province', 6.3725, 81.5194, 1),
('Mirissa', 'Beautiful beach town famous for whale watching', 1, 3, 'Southern Province', 5.9450, 80.4489, 1),
('Anuradhapura', 'Ancient capital with sacred Buddhist sites', 1, 1, 'North Central Province', 8.3114, 80.4037, 1),
('Nuwara Eliya', 'Cool climate hill station with tea plantations', 1, 5, 'Central Province', 6.9497, 80.7891, 1);

-- 9. Insert Activities
INSERT INTO activities (destination_id, name, description, activities_category, duration_hours, available_from, available_to, price_local, price_foreigners, min_participate, max_participate, season, status, created_by) VALUES
(1, 'Sigiriya Rock Climb', 'Climb the 1200 steps to the top of the rock fortress', 'Adventure', 3.5, '06:00:00', '17:00:00', 3000.00, 5000.00, 1, 20, 'Summer,Winter,Spring', 1, 1),
(2, 'Nine Arch Bridge Visit', 'Walk to the iconic colonial-era railway bridge', 'Sightseeing', 2.0, '06:00:00', '18:00:00', 500.00, 1000.00, 1, 50, 'Summer,Winter,Monsoon,Spring', 1, 1),
(2, 'Little Adams Peak Hike', 'Easy hike with panoramic views of Ella', 'Hiking', 2.5, '05:30:00', '18:00:00', 1000.00, 1500.00, 1, 30, 'Summer,Winter,Spring', 1, 1),
(3, 'Yala Safari', 'Half-day jeep safari to spot leopards and elephants', 'Wildlife', 4.0, '05:30:00', '17:00:00', 4000.00, 6000.00, 2, 6, 'Summer,Winter', 1, 1),
(4, 'Whale Watching', 'Boat tour to see blue whales and dolphins', 'Marine Life', 4.0, '06:00:00', '12:00:00', 5000.00, 8000.00, 4, 30, 'Winter,Spring', 1, 1),
(4, 'Surfing Lessons', 'Learn to surf with experienced instructors', 'Water Sports', 2.0, '07:00:00', '17:00:00', 3000.00, 4500.00, 1, 10, 'Summer,Winter,Spring', 1, 1);

-- 10. Insert Tour Schedule
INSERT INTO tour_schedule (name, tour_id, assume_start_date, assume_end_date, duration_start, duration_end, special_note, description, status, created_by) VALUES
('Sigiriya October Schedule', 1, '2025-10-01', '2025-10-31', 1, 1, 'Morning starts recommended', 'Daily departures available', 1, 1),
('Ella November Schedule', 2, '2025-11-01', '2025-11-30', 3, 3, 'Train tickets should be booked in advance', 'Weekend batches available', 1, 1),
('Yala December Schedule', 3, '2025-12-01', '2025-12-31', 2, 2, 'Peak season - advance booking required', 'Morning and afternoon safaris', 1, 1),
('Mirissa January Schedule', 4, '2026-01-01', '2026-01-31', 2, 3, 'Best whale watching season', 'Daily departures', 1, 1);

-- 11. Insert Activities Schedule
INSERT INTO activities_schedule (name, activity_id, assume_start_date, assume_end_date, duration_hours_start, duration_hours_end, special_note, description, status, created_by) VALUES
('Sigiriya Morning Climb', 1, '2025-10-01', '2026-03-31', 3.0, 4.0, 'Start early to avoid heat', 'Daily morning slots', 1, 1),
('Ella Bridge Walk Daily', 2, '2025-10-01', '2026-12-31', 1.5, 2.5, 'Best during train passing times', 'All day availability', 1, 1),
('Yala Morning Safari', 4, '2025-11-01', '2026-02-28', 3.5, 4.5, 'Higher chance of leopard sightings', 'Early morning departure', 1, 1),
('Whale Watch Season', 5, '2025-11-01', '2026-04-30', 3.5, 4.5, 'Best sighting months', 'Morning departures only', 1, 1);

-- 12. Insert Package Schedule
INSERT INTO package_schedule (name, package_id, assume_start_date, assume_end_date, duration_start, duration_end, special_note, description, status, created_by) VALUES
('Sigiriya Q4 2025', 1, '2025-10-01', '2025-12-31', 1, 1, 'Peak tourist season', 'Daily availability', 1, 1),
('Ella Winter Season', 2, '2025-11-01', '2026-02-28', 3, 3, 'Cool weather ideal for hiking', 'Weekend batches preferred', 1, 1),
('Yala Wildlife Season', 3, '2025-12-01', '2026-02-28', 2, 2, 'Best wildlife spotting period', 'Limited slots', 1, 1);

-- 13. Insert Features
INSERT INTO features (package_id, name, value, description, status, color, special_note, hover_color, created_by) VALUES
(1, 'Transport', 'Included', 'Air-conditioned vehicle with driver', 1, '#4CAF50', 'Pickup from hotel', '#45a049', 1),
(1, 'Guide', 'Included', 'Professional English-speaking guide', 1, '#2196F3', 'Expert local knowledge', '#1976D2', 1),
(2, 'Accommodation', '2 Nights', 'Boutique hotel in Ella town', 1, '#FF9800', 'Mountain view rooms', '#F57C00', 1),
(2, 'Meals', 'Breakfast', 'Daily breakfast included', 1, '#E91E63', 'Local and continental options', '#C2185B', 1),
(3, 'Safari Vehicle', 'Private Jeep', '4x4 safari jeep with tracker', 1, '#795548', 'Exclusive use', '#5D4037', 1);

-- 14. Insert Activities Requirements
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

-- 21. Insert Sample Images - Seasons
INSERT INTO seasons_images (season_id, name, description, image_url, status, created_by) VALUES
(1, 'Summer Beach Scene', 'Beautiful sunny beach during summer', '/images/seasons/summer_beach.jpg', 1, 1),
(2, 'Winter Hills', 'Misty mountains in winter season', '/images/seasons/winter_hills.jpg', 1, 1);

-- 22. Insert Sample Images - Tours
INSERT INTO tour_images (tour_id, name, description, image_url, status, created_by) VALUES
(1, 'Sigiriya Rock View', 'Majestic view of Sigiriya rock fortress', '/images/tours/sigiriya_main.jpg', 1, 1),
(2, 'Ella Train Journey', 'Scenic train passing through tea plantations', '/images/tours/ella_train.jpg', 1, 1),
(3, 'Yala Leopard', 'Leopard spotted during safari', '/images/tours/yala_leopard.jpg', 1, 1);

-- 23. Insert Sample Images - Packages
INSERT INTO package_images (package_id, name, description, status, image_url, color, created_by) VALUES
(1, 'Sigiriya Package Hero', 'Main promotional image', 1, '/images/packages/sigiriya_pkg.jpg', '#FF6B6B', 1),
(2, 'Ella Package Gallery', 'Scenic Ella landscape', 1, '/images/packages/ella_pkg.jpg', '#4ECDC4', 1);

-- 24. Insert Sample Images - Destinations
INSERT INTO destination_images (destination_id, name, description, image_url, status, created_by) VALUES
(1, 'Sigiriya Summit', 'View from the top of Sigiriya', '/images/destinations/sigiriya_top.jpg', 1, 1),
(2, 'Ella Rock Vista', 'Panoramic view from Ella Rock', '/images/destinations/ella_vista.jpg', 1, 1),
(4, 'Mirissa Sunset', 'Beautiful sunset at Mirissa beach', '/images/destinations/mirissa_sunset.jpg', 1, 1);

-- Destination Categories Images Table
INSERT INTO destination_categories_images (destination_categories_id, name, description, image_url, status, created_by) VALUES
(1, 'Historical Sites Banner', 'Ancient ruins and temples', '/images/dest_categories/historical_sites.jpg', 1, 1),
(2, 'Natural Wonders Hero', 'Waterfalls and mountains', '/images/dest_categories/natural_wonders.jpg', 1, 1),
(3, 'Beach Paradise', 'Golden sandy beaches', '/images/dest_categories/beaches.jpg', 1, 1),
(4, 'Wildlife Gallery', 'Animals in natural habitat', '/images/dest_categories/national_parks.jpg', 1, 1),
(5, 'Urban Scenes', 'City life and culture', '/images/dest_categories/cities.jpg', 1, 1);

-- 25. Insert Sample Images - Activities
INSERT INTO activities_images (activity_id, name, description, image_url, status, created_by) VALUES
(1, 'Rock Climbing Action', 'Tourists climbing Sigiriya steps', '/images/activities/sigiriya_climb.jpg', 1, 1),
(4, 'Safari Jeep', 'Safari vehicle in Yala', '/images/activities/yala_jeep.jpg', 1, 1),
(5, 'Whale Breach', 'Blue whale breaching near boat', '/images/activities/whale_watching.jpg', 1, 1);

-- 25b. Insert Sample Images - Tour History
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
