-- ============================================================
-- COMPLETE SAMPLE DATA FOR ALL TABLES
-- ============================================================

-- 1. currency (already inserted)
INSERT INTO currency (currency_code, currency_name, symbol, exchange_rate, status_id, created_by) VALUES
('USD', 'US Dollar', '$', 1.0000, 1, 1),
('EUR', 'Euro', '€', 0.9200, 1, 1),
('GBP', 'British Pound', '£', 0.7900, 1, 1),
('JPY', 'Japanese Yen', '¥', 148.5000, 1, 1),
('AUD', 'Australian Dollar', 'A$', 1.5200, 1, 1);

-- 2. service_provider_type (already inserted)
INSERT INTO service_provider_type (name, description, status_id, created_by) VALUES
('Hotel', 'Accommodation provider with rooms', 1, 1),
('Resort', 'Luxury accommodation with amenities', 1, 1),
('Villa', 'Private vacation rental', 1, 1),
('Restaurant', 'Food and beverage service', 1, 1),
('Hostel', 'Budget accommodation', 1, 1);

-- 3. service_provider_operating_hours_status (already inserted)
INSERT INTO service_provider_operating_hours_status (name, description, status_id, created_by) VALUES
('OPEN', 'Fully operational', 1, 1),
('CLOSED', 'Not operating', 1, 1),
('HOLIDAY', 'Closed for holiday', 1, 1),
('SEASONAL', 'Seasonal operation', 1, 1),
('RENOVATION', 'Closed for renovation', 1, 1);

-- 4. meal_type (already inserted)
INSERT INTO meal_type (name, description, status_id, created_by) VALUES
('Breakfast', 'Morning meal', 1, 1),
('Lunch', 'Midday meal', 1, 1),
('Dinner', 'Evening meal', 1, 1),
('Brunch', 'Late morning meal', 1, 1),
('Snacks', 'Light meals and snacks', 1, 1);

-- 5. service_provider_room_type (already inserted)
INSERT INTO service_provider_room_type (name, description, status_id, created_by) VALUES
('Standard', 'Basic room with essential amenities', 1, 1),
('Deluxe', 'Upgraded room with additional features', 1, 1),
('Suite', 'Spacious room with separate living area', 1, 1),
('Family', 'Room suitable for families', 1, 1),
('Executive', 'Business-class room', 1, 1);

-- 6. amenity_type (already inserted)
INSERT INTO amenity_type (name, description, category, icon_class, status_id, created_by) VALUES
('WiFi', 'Wireless Internet', 'Technology', 'wifi', 1, 1),
('Pool', 'Swimming Pool', 'Recreation', 'pool', 1, 1),
('Gym', 'Fitness Center', 'Recreation', 'gym', 1, 1),
('Spa', 'Spa Services', 'Wellness', 'spa', 1, 1),
('Parking', 'Car Parking', 'Transport', 'parking', 1, 1);

-- 7. service_provider_review_rating_category (already inserted)
INSERT INTO service_provider_review_rating_category (name, description, status_id, created_by) VALUES
('Cleanliness', 'Cleanliness of facilities', 1, 1),
('Service', 'Quality of service', 1, 1),
('Location', 'Convenience of location', 1, 1),
('Value', 'Value for money', 1, 1),
('Amenities', 'Quality of amenities', 1, 1);

-- 8. service_provider_social_verification_status (already inserted)
INSERT INTO service_provider_social_verification_status (name, description, status_id, created_by) VALUES
('VERIFIED', 'Officially verified', 1, 1),
('UNVERIFIED', 'Not verified', 1, 1),
('PENDING', 'Verification pending', 1, 1),
('REJECTED', 'Verification rejected', 1, 1),
('SUSPENDED', 'Temporarily suspended', 1, 1);

-- 9. service_provider (main hotels - already inserted)
INSERT INTO service_provider (user_id, service_provider_type_id, name, description, address, contact_number, email, website_url, check_in_time, check_out_time, star_rating, currency_id, cancellation_policy, total_rooms, wifi_available, pet_friendly, approval_status_id, status_id, created_by) VALUES
(1, 1, 'Grand Plaza Hotel', 'Luxury 5-star hotel in city center', '123 Main Street, Cityville', '+1-555-0101', 'info@grandplaza.com', 'https://grandplaza.com', '14:00:00', '12:00:00', 5, 1, 'Free cancellation 24 hours before check-in', 150, TRUE, FALSE, 1, 1, 1),
(2, 2, 'Sunset Beach Resort', 'Beachfront resort with spa', '456 Beach Road, Coastal City', '+1-555-0102', 'reservations@sunsetbeach.com', 'https://sunsetbeachresort.com', '15:00:00', '11:00:00', 4, 1, 'Free cancellation 48 hours before check-in', 200, TRUE, TRUE, 1, 1, 1),
(3, 1, 'Mountain View Inn', 'Cozy hotel with mountain views', '789 Mountain Road, Hilltown', '+1-555-0103', 'bookings@mountainview.com', 'https://mountainviewinn.com', '14:00:00', '12:00:00', 3, 1, 'Free cancellation 72 hours before check-in', 80, TRUE, FALSE, 1, 1, 1),
(1, 3, 'Royal Villa Estate', 'Private luxury villas', '321 Villa Lane, Uptown', '+1-555-0104', 'info@royalvilla.com', 'https://royalvilla.com', '16:00:00', '10:00:00', 5, 1, 'Strict cancellation policy', 25, TRUE, TRUE, 1, 1, 1),
(2, 1, 'City Central Hotel', 'Modern hotel near business district', '654 Business Ave, Downtown', '+1-555-0105', 'reservations@citycentral.com', 'https://citycentralhotel.com', '14:00:00', '12:00:00', 4, 1, 'Free cancellation 24 hours before check-in', 120, TRUE, FALSE, 1, 1, 1);

-- 10. service_provider_approval
INSERT INTO service_provider_approval (service_provider_id, approved_by, approval_status_id, approval_comment, approved_at, created_by) VALUES
(1, 1, 1, 'All documents verified and approved', NOW(), 1),
(2, 2, 1, 'Property inspection completed successfully', NOW(), 1),
(3, 1, 1, 'Business registration verified', NOW(), 1),
(4, 2, 1, 'Luxury certification confirmed', NOW(), 1),
(5, 1, 1, 'Safety standards met', NOW(), 1);

-- 11. service_provider_destination
INSERT INTO service_provider_destination (service_provider_id, destination_id, status_id, created_by) VALUES
(1, 1, 1, 1),
(1, 2, 1, 1),
(2, 3, 1, 1),
(3, 4, 1, 1),
(4, 5, 1, 1),
(5, 6, 1, 1),
(2, 7, 1, 1),
(3, 8, 1, 1),
(4, 9, 1, 1),
(5, 10, 1, 1);

-- 12. service_provider_location
INSERT INTO service_provider_location (service_provider_id, latitude, longitude, google_place_id, timezone, created_by) VALUES
(1, 40.712776, -74.005974, 'ChIJHQ6aMnBTwokRc-T-3CrcvOE', 'America/New_York', 1),
(2, 34.052235, -118.243683, 'ChIJE9on3F3HwoAR9AhGJW_fL-I', 'America/Los_Angeles', 1),
(3, 39.739235, -104.990250, 'ChIJzxcfI6qAa4cR1jaKJ_j0jhE', 'America/Denver', 1),
(4, 41.878113, -87.629799, 'ChIJ7cv00DwsDogRAMDACa2m4K8', 'America/Chicago', 1),
(5, 25.761681, -80.191788, 'ChIJEcHIDqKw2YgRZU-t3XHylv8', 'America/New_York', 1);

-- 13. service_provider_operating_hours
INSERT INTO service_provider_operating_hours (service_provider_id, day_of_week, opens_at, closes_at, is_24_hours, operating_status_id, special_note, created_by) VALUES
(1, 1, '06:00:00', '22:00:00', FALSE, 1, 'Breakfast served until 11:00', 1),
(1, 2, '06:00:00', '22:00:00', FALSE, 1, NULL, 1),
(1, 3, '06:00:00', '22:00:00', FALSE, 1, NULL, 1),
(1, 4, '06:00:00', '22:00:00', FALSE, 1, NULL, 1),
(1, 5, '06:00:00', '23:00:00', FALSE, 1, 'Extended hours on Friday', 1),
(2, 1, '00:00:00', '23:59:59', TRUE, 1, '24/7 reception', 1),
(3, 1, '07:00:00', '21:00:00', FALSE, 1, NULL, 1),
(4, 1, '00:00:00', '23:59:59', TRUE, 1, 'Private villa - 24/7 access', 1),
(5, 1, '06:30:00', '22:30:00', FALSE, 1, 'Business center opens at 06:30', 1);

-- 14. service_provider_meal
INSERT INTO service_provider_meal (service_provider_id, meal_type_id, description, local_price, foreign_price, currency_id, discount_percentage, serves_people, cuisine_type, dietary_tags, available, created_by) VALUES
(1, 1, 'Continental Breakfast Buffet', 25.00, 25.00, 1, 0.00, 1, 'International', '["vegetarian", "gluten-free options"]', TRUE, 1),
(1, 2, 'Business Lunch Special', 35.00, 35.00, 1, 10.00, 1, 'American', '[]', TRUE, 1),
(1, 3, 'Gourmet Dinner Experience', 85.00, 85.00, 1, 0.00, 1, 'French', '["vegetarian", "vegan options"]', TRUE, 1),
(2, 1, 'Beachside Breakfast', 22.00, 22.00, 1, 0.00, 1, 'Tropical', '["vegetarian"]', TRUE, 1),
(2, 3, 'Seafood Dinner Platter', 65.00, 65.00, 1, 15.00, 2, 'Seafood', '["pescatarian"]', TRUE, 1),
(3, 1, 'Mountain Breakfast', 18.00, 18.00, 1, 0.00, 1, 'American', '["vegetarian"]', TRUE, 1),
(4, 3, 'Private Villa Dinner', 120.00, 120.00, 1, 0.00, 2, 'Mediterranean', '["vegetarian", "vegan options"]', TRUE, 1),
(5, 1, 'Executive Breakfast', 28.00, 28.00, 1, 0.00, 1, 'International', '["vegetarian", "gluten-free"]', TRUE, 1);

-- 15. service_provider_room
INSERT INTO service_provider_room (service_provider_id, room_type_id, room_number, description, capacity, room_size, bed_type, number_of_beds, local_price_per_night, foreign_price_per_night, currency_id, has_air_conditioning, has_tv, internet_access, status_id, created_by) VALUES
(1, 2, '201', 'Deluxe King Room with city view', 2, 35.00, 'King', 1, 199.00, 199.00, 1, TRUE, TRUE, TRUE, 1, 1),
(1, 3, '301', 'Executive Suite with living area', 3, 55.00, 'King', 1, 350.00, 350.00, 1, TRUE, TRUE, TRUE, 1, 1),
(2, 1, '101', 'Standard Beach View Room', 2, 28.00, 'Queen', 1, 150.00, 150.00, 1, TRUE, TRUE, TRUE, 1, 1),
(2, 4, '201', 'Family Room with bunk beds', 4, 45.00, 'Queen + Bunk', 2, 220.00, 220.00, 1, TRUE, TRUE, TRUE, 1, 1),
(3, 1, '102', 'Cozy Mountain View Room', 2, 25.00, 'Double', 1, 89.00, 89.00, 1, TRUE, TRUE, TRUE, 1, 1),
(4, 3, 'Villa-1', 'Luxury Private Villa with Pool', 6, 120.00, 'King + Queen', 3, 650.00, 650.00, 1, TRUE, TRUE, TRUE, 1, 1),
(5, 5, '501', 'Executive Business Room', 2, 32.00, 'King', 1, 180.00, 180.00, 1, TRUE, TRUE, TRUE, 1, 1),
(1, 1, '101', 'Standard Room', 2, 25.00, 'Queen', 1, 129.00, 129.00, 1, TRUE, TRUE, TRUE, 1, 1);

-- 16. service_provider_package
INSERT INTO service_provider_package (service_provider_id, name, description, local_price, foreign_price, currency_id, discount_percentage, duration_days, min_persons, max_persons, package_code, status_id, created_by) VALUES
(1, 'Weekend Getaway', '2-night stay with breakfast and spa credit', 450.00, 450.00, 1, 10.00, 2, 2, 2, 'GP-WKND-001', 1, 1),
(1, 'Business Traveler', '3-night stay with executive amenities', 600.00, 600.00, 1, 5.00, 3, 1, 1, 'GP-BUS-001', 1, 1),
(2, 'Beach Escape', '4-night all-inclusive beach package', 1200.00, 1200.00, 1, 15.00, 4, 2, 4, 'SB-ESC-001', 1, 1),
(3, 'Mountain Retreat', '3-night nature package with hiking', 350.00, 350.00, 1, 0.00, 3, 2, 2, 'MV-RET-001', 1, 1),
(4, 'Luxury Villa Experience', '7-night private villa with chef', 4200.00, 4200.00, 1, 0.00, 7, 4, 6, 'RV-LUX-001', 1, 1),
(5, 'City Break', '2-night downtown experience', 320.00, 320.00, 1, 8.00, 2, 2, 2, 'CC-CITY-001', 1, 1);

-- 17. service_provider_amenity
INSERT INTO service_provider_amenity (service_provider_id, amenity_type_id, name, description, local_additional_charge, foreign_additional_charge, currency_id, is_available, created_by) VALUES
(1, 1, 'Premium WiFi', 'High-speed internet access', 0.00, 0.00, 1, TRUE, 1),
(1, 2, 'Infinity Pool', 'Rooftop infinity pool', 0.00, 0.00, 1, TRUE, 1),
(1, 3, 'Fitness Center', '24/7 gym access', 0.00, 0.00, 1, TRUE, 1),
(1, 4, 'Luxury Spa', 'Full-service spa treatments', 0.00, 0.00, 1, TRUE, 1),
(2, 2, 'Beach Pool', 'Ocean-view swimming pool', 0.00, 0.00, 1, TRUE, 1),
(2, 4, 'Beachside Spa', 'Tropical spa treatments', 0.00, 0.00, 1, TRUE, 1),
(3, 5, 'Mountain Parking', 'Secure parking facility', 15.00, 15.00, 1, TRUE, 1),
(4, 2, 'Private Villa Pool', 'Individual pool for each villa', 0.00, 0.00, 1, TRUE, 1),
(5, 1, 'Business WiFi', 'Dedicated business internet', 0.00, 0.00, 1, TRUE, 1),
(5, 3, 'Executive Gym', 'Business-class fitness center', 0.00, 0.00, 1, TRUE, 1);

-- 18. service_provider_facility
INSERT INTO service_provider_facility (service_provider_id, facility_name, description, is_available, special_note, created_by) VALUES
(1, 'Conference Center', '1000-person capacity conference facility', TRUE, 'Advanced booking required', 1),
(1, 'Business Center', '24/7 business services', TRUE, 'Printing and copying available', 1),
(2, 'Water Sports Center', 'Beach equipment and water sports', TRUE, 'Seasonal operation', 1),
(2, 'Kids Club', 'Children entertainment facility', TRUE, 'Ages 4-12', 1),
(3, 'Hiking Guide Service', 'Professional mountain guides', TRUE, 'Reservation recommended', 1),
(4, 'Private Chef Service', 'In-villa dining experience', TRUE, '24-hour notice required', 1),
(4, 'Butler Service', 'Personal butler for each villa', TRUE, 'Included in villa rate', 1),
(5, 'Meeting Rooms', 'Executive meeting spaces', TRUE, 'Various sizes available', 1),
(5, 'Concierge Service', 'Personalized city guidance', TRUE, 'Multilingual staff', 1);

-- 19. service_provider_seasonal_pricing
INSERT INTO service_provider_seasonal_pricing (service_provider_id, name, start_date, end_date, local_multiplier, foreign_multiplier, description, status_id, created_by) VALUES
(1, 'Summer Peak', '2024-06-01', '2024-08-31', 1.25, 1.25, 'High season summer rates', 1, 1),
(1, 'Winter Holiday', '2024-12-20', '2025-01-05', 1.35, 1.35, 'Christmas and New Year premium', 1, 1),
(2, 'Beach Season', '2024-05-15', '2024-09-15', 1.30, 1.30, 'Peak beach weather pricing', 1, 1),
(3, 'Fall Foliage', '2024-09-20', '2024-10-31', 1.20, 1.20, 'Autumn leaf viewing season', 1, 1),
(4, 'Festive Season', '2024-12-01', '2025-01-10', 1.40, 1.40, 'Luxury holiday experience', 1, 1),
(5, 'Business Conference', '2024-03-01', '2024-05-31', 1.15, 1.15, 'Spring business travel season', 1, 1);

-- 20. service_provider_contact_person
INSERT INTO service_provider_contact_person (service_provider_id, full_name, designation, email, phone, mobile, is_primary, status_id, created_by) VALUES
(1, 'Sarah Johnson', 'General Manager', 's.johnson@grandplaza.com', '+1-555-0101', '+1-555-0106', TRUE, 1, 1),
(1, 'Michael Chen', 'Front Office Manager', 'm.chen@grandplaza.com', '+1-555-0101', '+1-555-0107', FALSE, 1, 1),
(2, 'Emma Rodriguez', 'Resort Director', 'e.rodriguez@sunsetbeach.com', '+1-555-0102', '+1-555-0108', TRUE, 1, 1),
(3, 'David Wilson', 'Inn Keeper', 'd.wilson@mountainview.com', '+1-555-0103', '+1-555-0109', TRUE, 1, 1),
(4, 'Sophia Martinez', 'Villa Manager', 's.martinez@royalvilla.com', '+1-555-0104', '+1-555-0110', TRUE, 1, 1),
(5, 'James Brown', 'Hotel Manager', 'j.brown@citycentral.com', '+1-555-0105', '+1-555-0111', TRUE, 1, 1);

-- 21. service_provider_bank
INSERT INTO service_provider_bank (service_provider_id, bank_name, account_holder_name, account_number, branch_name, swift_code, currency_id, is_primary, status_id, created_by) VALUES
(1, 'City National Bank', 'Grand Plaza Hotel LLC', '9876543210', 'Main Branch', 'CTBKIUS33', 1, TRUE, 1, 1),
(1, 'Global Trust Bank', 'Grand Plaza Hotel LLC', '1234567890', 'Downtown', 'GTBKUS44', 2, FALSE, 1, 1),
(2, 'Oceanic Bank', 'Sunset Beach Resort Inc', '5556667777', 'Beach Branch', 'OCEAUS55', 1, TRUE, 1, 1),
(3, 'Mountain Federal', 'Mountain View Inn', '3334445555', 'Hilltown', 'MTNFUS66', 1, TRUE, 1, 1),
(4, 'Royal International', 'Royal Villa Estate', '7778889999', 'Uptown', 'ROYLUS77', 1, TRUE, 1, 1),
(5, 'Metro Commercial', 'City Central Hotel', '1112223333', 'Business District', 'METRUS88', 1, TRUE, 1, 1);

-- 22. service_provider_tax_configuration
INSERT INTO service_provider_tax_configuration (service_provider_id, tax_name, tax_percentage, tax_number, is_active, status_id, created_by) VALUES
(1, 'City Tax', 8.50, 'TX-001-GPH', TRUE, 1, 1),
(1, 'State Tax', 6.00, 'TX-002-GPH', TRUE, 1, 1),
(2, 'Tourism Tax', 5.00, 'TX-001-SBR', TRUE, 1, 1),
(2, 'Resort Fee', 12.00, 'RF-001-SBR', TRUE, 1, 1),
(3, 'County Tax', 7.25, 'TX-001-MVI', TRUE, 1, 1),
(4, 'Luxury Tax', 10.00, 'TX-001-RVE', TRUE, 1, 1),
(5, 'Business Tax', 8.75, 'TX-001-CCH', TRUE, 1, 1);

-- 23. service_provider_commission_settings
INSERT INTO service_provider_commission_settings (service_provider_id, commission_percentage, applies_to_rooms, applies_to_meals, applies_to_packages, minimum_commission_amount, status_id, created_by) VALUES
(1, 15.00, TRUE, TRUE, TRUE, 25.00, 1, 1),
(2, 12.00, TRUE, TRUE, TRUE, 20.00, 1, 1),
(3, 18.00, TRUE, FALSE, TRUE, 15.00, 1, 1),
(4, 10.00, TRUE, TRUE, TRUE, 50.00, 1, 1),
(5, 16.00, TRUE, TRUE, TRUE, 22.00, 1, 1);

-- 24. service_provider_document
INSERT INTO service_provider_document (service_provider_id, document_type, document_name, document_url, issue_date, expiry_date, is_verified, verified_by, status_id, created_by) VALUES
(1, 'Business License', 'Hotel Operating License', 'https://docs.example.com/gp-license.pdf', '2023-01-15', '2024-12-31', TRUE, 1, 1, 1),
(1, 'Health Certificate', 'Food Safety Certificate', 'https://docs.example.com/gp-health.pdf', '2023-03-20', '2024-03-20', TRUE, 1, 1, 1),
(2, 'Resort License', 'Beach Resort Permit', 'https://docs.example.com/sb-license.pdf', '2023-02-10', '2025-02-10', TRUE, 2, 1, 1),
(3, 'Inn License', 'Mountain Inn Certification', 'https://docs.example.com/mv-license.pdf', '2023-01-30', '2024-12-31', TRUE, 1, 1, 1),
(4, 'Villa Permit', 'Luxury Villa Authorization', 'https://docs.example.com/rv-permit.pdf', '2023-04-01', '2026-04-01', TRUE, 2, 1, 1),
(5, 'Hotel Registration', 'City Hotel Registration', 'https://docs.example.com/cc-registration.pdf', '2023-01-10', '2024-12-31', TRUE, 1, 1, 1);

-- 25. service_provider_booking_restrictions
INSERT INTO service_provider_booking_restrictions (service_provider_id, restriction_type, min_stay_nights, max_stay_nights, start_date, end_date, description, status_id, created_by) VALUES
(1, 'MIN_STAY', 2, NULL, '2024-12-20', '2025-01-05', 'Minimum 2 nights during holidays', 1, 1),
(2, 'MIN_STAY', 3, 14, '2024-06-01', '2024-08-31', 'Summer season restrictions', 1, 1),
(3, 'MAX_STAY', 1, 30, NULL, NULL, 'Maximum 30 nights stay', 1, 1),
(4, 'MIN_STAY', 5, 21, NULL, NULL, 'Villa minimum 5 nights stay', 1, 1),
(5, 'MIN_STAY', 1, 7, '2024-09-01', '2024-11-30', 'Fall season restrictions', 1, 1);

-- 26. service_provider_statistics
INSERT INTO service_provider_statistics (service_provider_id, total_bookings, total_revenue, average_rating, total_reviews, occupancy_rate) VALUES
(1, 1250, 450000.00, 4.5, 890, 78.5),
(2, 980, 320000.00, 4.3, 650, 82.2),
(3, 450, 95000.00, 4.1, 320, 65.8),
(4, 280, 420000.00, 4.8, 190, 58.3),
(5, 890, 280000.00, 4.4, 540, 75.6);

-- 27. service_provider_review
INSERT INTO service_provider_review (service_provider_id, user_id, rating, title, comment, is_approved, status_id, created_by) VALUES
(1, 1, 5, 'Excellent Stay!', 'Amazing service and beautiful rooms. Will definitely return!', TRUE, 1, 1),
(1, 2, 4, 'Great Location', 'Perfect location and friendly staff. Room was comfortable.', TRUE, 1, 1),
(2, 3, 5, 'Paradise Found', 'Absolutely stunning beach and wonderful amenities.', TRUE, 1, 1),
(2, 1, 4, 'Beautiful Resort', 'Great food and service. Pool area was fantastic.', TRUE, 1, 1),
(3, 2, 4, 'Cozy Retreat', 'Perfect mountain getaway. Very peaceful and relaxing.', TRUE, 1, 1),
(4, 3, 5, 'Ultimate Luxury', 'The villa exceeded all expectations. Pure luxury!', TRUE, 1, 1),
(5, 1, 4, 'Business Friendly', 'Perfect for business travel. Great amenities and location.', TRUE, 1, 1);

-- 28. service_provider_social
INSERT INTO service_provider_social (service_provider_id, platform, profile_url, verification_status_id, status_id, created_by) VALUES
(1, 'Facebook', 'https://facebook.com/grandplazahotel', 1, 1, 1),
(1, 'Instagram', 'https://instagram.com/grandplazahotel', 1, 1, 1),
(2, 'Facebook', 'https://facebook.com/sunsetbeachresort', 1, 1, 1),
(2, 'Twitter', 'https://twitter.com/sunsetbeach', 2, 1, 1),
(3, 'Instagram', 'https://instagram.com/mountainviewinn', 1, 1, 1),
(4, 'Facebook', 'https://facebook.com/royalvillaestate', 1, 1, 1),
(5, 'LinkedIn', 'https://linkedin.com/company/citycentralhotel', 1, 1, 1);

-- 29. accepted_payment_methods
INSERT INTO accepted_payment_methods (service_provider_id, method_type, method_details, is_available, status_id, created_by) VALUES
(1, 'CREDIT_CARD', '{"cards": ["Visa", "MasterCard", "American Express"]}', TRUE, 1, 1),
(1, 'DIGITAL_WALLET', '{"wallets": ["PayPal", "Apple Pay", "Google Pay"]}', TRUE, 1, 1),
(2, 'CREDIT_CARD', '{"cards": ["Visa", "MasterCard", "Discover"]}', TRUE, 1, 1),
(2, 'BANK_TRANSFER', '{"instructions": "Wire transfer details provided upon booking"}', TRUE, 1, 1),
(3, 'CREDIT_CARD', '{"cards": ["Visa", "MasterCard"]}', TRUE, 1, 1),
(4, 'CREDIT_CARD', '{"cards": ["Visa", "MasterCard", "American Express", "UnionPay"]}', TRUE, 1, 1),
(5, 'CREDIT_CARD', '{"cards": ["Visa", "MasterCard", "American Express"]}', TRUE, 1, 1),
(5, 'CORPORATE_ACCOUNT', '{"requirements": "Business registration required"}', TRUE, 1, 1);

-- 30. service_provider_image
INSERT INTO service_provider_image (service_provider_id, image_url, image_name, image_description, caption, status_id, created_by) VALUES
(1, 'https://images.example.com/gp-exterior.jpg', 'Grand Plaza Exterior', 'Main hotel building exterior', 'Luxury City Hotel', 1, 1),
(1, 'https://images.example.com/gp-lobby.jpg', 'Grand Plaza Lobby', 'Elegant hotel lobby', 'Grand Entrance', 1, 1),
(2, 'https://images.example.com/sb-beach.jpg', 'Sunset Beach', 'Private beach area', 'Paradise Beach', 1, 1),
(2, 'https://images.example.com/sb-pool.jpg', 'Resort Pool', 'Infinity pool with ocean view', 'Ocean View Pool', 1, 1),
(3, 'https://images.example.com/mv-mountain.jpg', 'Mountain View', 'Scenic mountain landscape', 'Mountain Retreat', 1, 1),
(4, 'https://images.example.com/rv-villa.jpg', 'Private Villa', 'Luxury villa exterior', 'Exclusive Villa', 1, 1),
(5, 'https://images.example.com/cc-city.jpg', 'City View', 'Downtown cityscape', 'City Center Location', 1, 1);

-- ============================================================
-- TABLES THAT REFERENCE OTHER DEPENDENT TABLES
-- ============================================================

-- 31. service_provider_meal_image
INSERT INTO service_provider_meal_image (meal_id, image_url, image_name, image_description, caption, status_id, created_by) VALUES
(1, 'https://images.example.com/breakfast-buffet.jpg', 'Breakfast Buffet', 'Continental breakfast spread', 'Morning Delights', 1, 1),
(2, 'https://images.example.com/business-lunch.jpg', 'Business Lunch', 'Executive lunch special', 'Power Lunch', 1, 1),
(3, 'https://images.example.com/gourmet-dinner.jpg', 'Gourmet Dinner', 'Fine dining experience', 'Culinary Excellence', 1, 1),
(4, 'https://images.example.com/beach-breakfast.jpg', 'Beach Breakfast', 'Tropical morning meal', 'Beachside Dining', 1, 1),
(5, 'https://images.example.com/seafood-dinner.jpg', 'Seafood Platter', 'Fresh seafood selection', 'Ocean Fresh', 1, 1);

-- 32. service_provider_room_feature
INSERT INTO service_provider_room_feature (room_id, feature_name, feature_value, description, status_id, created_by) VALUES
(1, 'View Type', 'City View', 'Panoramic city skyline', 1, 1),
(1, 'Floor Level', '20', 'High floor with better view', 1, 1),
(2, 'View Type', 'Executive View', 'Premium city panorama', 1, 1),
(2, 'Room Service', '24-hour', 'Round-the-clock service', 1, 1),
(3, 'View Type', 'Beach View', 'Direct ocean view', 1, 1),
(4, 'Bed Configuration', 'Queen + Bunk Beds', 'Family-friendly setup', 1, 1),
(5, 'View Type', 'Mountain View', 'Scenic mountain landscape', 1, 1);

-- 33. service_provider_room_amenity
INSERT INTO service_provider_room_amenity (room_id, amenity_type_id, is_available, additional_notes, status_id, created_by) VALUES
(1, 1, TRUE, 'High-speed internet', 1, 1),
(1, 2, FALSE, 'No pool access from this room', 1, 1),
(2, 1, TRUE, 'Premium business internet', 1, 1),
(2, 3, TRUE, 'Private gym access', 1, 1),
(3, 1, TRUE, 'Standard WiFi', 1, 1),
(4, 1, TRUE, 'Family-friendly internet', 1, 1),
(5, 1, TRUE, 'Mountain view WiFi', 1, 1),
(6, 1, TRUE, 'Villa premium internet', 1, 1),
(7, 1, TRUE, 'Business-grade internet', 1, 1);

-- 34. service_provider_room_availability
INSERT INTO service_provider_room_availability (room_id, date, available_rooms, booked_rooms, local_price_for_date, foreign_price_for_date, created_by) VALUES
(1, '2024-12-25', 5, 0, 250.00, 250.00, 1),
(1, '2024-12-26', 5, 0, 250.00, 250.00, 1),
(2, '2024-12-25', 2, 0, 450.00, 450.00, 1),
(3, '2024-12-25', 15, 0, 180.00, 180.00, 1),
(4, '2024-12-25', 8, 0, 270.00, 270.00, 1),
(5, '2024-12-25', 12, 0, 100.00, 100.00, 1),
(6, '2024-12-25', 3, 0, 750.00, 750.00, 1),
(7, '2024-12-25', 10, 0, 200.00, 200.00, 1);

-- 35. service_provider_room_image
INSERT INTO service_provider_room_image (room_id, image_url, image_name, image_description, caption, status_id, created_by) VALUES
(1, 'https://images.example.com/room-201-bedroom.jpg', 'Deluxe Room Bedroom', 'Spacious bedroom with king bed', 'Luxury Sleeping', 1, 1),
(1, 'https://images.example.com/room-201-bathroom.jpg', 'Deluxe Room Bathroom', 'Modern bathroom amenities', 'Spa-like Bathroom', 1, 1),
(2, 'https://images.example.com/suite-living.jpg', 'Executive Suite Living', 'Separate living area', 'Executive Living', 1, 1),
(3, 'https://images.example.com/beach-room.jpg', 'Beach View Room', 'Room with ocean view', 'Ocean Breeze', 1, 1),
(4, 'https://images.example.com/family-room.jpg', 'Family Room Interior', 'Spacious family accommodation', 'Family Comfort', 1, 1);

-- 36. service_provider_package_feature
INSERT INTO service_provider_package_feature (service_provider_package_id, feature_name, feature_value, description, status_id, created_by) VALUES
(1, 'Inclusions', 'Breakfast + Spa Credit', 'Daily breakfast and $100 spa credit', 1, 1),
(1, 'Duration', '2 Nights', 'Weekend stay package', 1, 1),
(2, 'Business Amenities', 'Executive Lounge', 'Access to executive lounge', 1, 1),
(3, 'All-Inclusive', 'Meals + Activities', 'All meals and selected activities included', 1, 1),
(4, 'Nature Activities', 'Guided Hiking', 'Daily guided mountain hikes', 1, 1),
(5, 'Private Services', 'Personal Chef', 'Dedicated chef service', 1, 1);

-- 37. service_provider_package_inclusion
INSERT INTO service_provider_package_inclusion (service_provider_package_id, inclusion_name, description, status_id, created_by) VALUES
(1, 'Accommodation', '2 nights in deluxe room', 1, 1),
(1, 'Breakfast', 'Daily continental breakfast', 1, 1),
(1, 'Spa Credit', '$100 towards spa services', 1, 1),
(2, 'Executive Room', '3 nights in executive suite', 1, 1),
(2, 'Airport Transfer', 'Round-trip airport transportation', 1, 1),
(3, 'All Meals', 'Breakfast, lunch, and dinner', 1, 1),
(3, 'Water Sports', 'Unlimited non-motorized water sports', 1, 1),
(4, 'Mountain Accommodation', '3 nights in mountain view room', 1, 1),
(4, 'Hiking Tours', 'Guided nature walks', 1, 1);

-- 38. service_provider_package_image
INSERT INTO service_provider_package_image (service_provider_package_id, image_url, image_name, image_description, caption, status_id, created_by) VALUES
(1, 'https://images.example.com/weekend-package.jpg', 'Weekend Getaway', 'Relaxing weekend experience', 'Perfect Escape', 1, 1),
(2, 'https://images.example.com/business-package.jpg', 'Business Travel', 'Executive business amenities', 'Productive Stay', 1, 1),
(3, 'https://images.example.com/beach-package.jpg', 'Beach Escape', 'Tropical beach vacation', 'Island Paradise', 1, 1),
(4, 'https://images.example.com/mountain-package.jpg', 'Mountain Retreat', 'Nature mountain getaway', 'Alpine Adventure', 1, 1),
(5, 'https://images.example.com/villa-package.jpg', 'Villa Experience', 'Luxury private villa stay', 'Ultimate Privacy', 1, 1);

-- 39. service_provider_facility_image
INSERT INTO service_provider_facility_image (facility_id, image_url, image_name, image_description, caption, status_id, created_by) VALUES
(1, 'https://images.example.com/conference-center.jpg', 'Conference Center', 'Large event space', 'Professional Events', 1, 1),
(2, 'https://images.example.com/business-center.jpg', 'Business Center', 'Modern work facilities', 'Work Smart', 1, 1),
(3, 'https://images.example.com/watersports.jpg', 'Water Sports', 'Beach activity center', 'Ocean Fun', 1, 1),
(4, 'https://images.example.com/kids-club.jpg', 'Kids Club', 'Children play area', 'Family Fun', 1, 1),
(5, 'https://images.example.com/hiking-guides.jpg', 'Hiking Guides', 'Mountain adventure team', 'Explore Nature', 1, 1);

-- 40. service_provider_review_rating_detail
INSERT INTO service_provider_review_rating_detail (review_id, category_id, rating, created_by) VALUES
(1, 1, 5, 1),
(1, 2, 5, 1),
(1, 3, 4, 1),
(1, 4, 5, 1),
(1, 5, 5, 1),
(2, 1, 4, 1),
(2, 2, 5, 1),
(2, 3, 5, 1),
(2, 4, 4, 1),
(2, 5, 4, 1),
(3, 1, 5, 1),
(3, 2, 5, 1),
(3, 3, 5, 1),
(3, 4, 4, 1),
(3, 5, 5, 1);