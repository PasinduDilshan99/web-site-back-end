-- =============================================
-- SAMPLE DATA FOR BOOKING SYSTEM
-- =============================================

-- Insert booking statuses
INSERT INTO booking_status (name, description, status, created_by) VALUES
('PENDING', 'Booking is pending confirmation', 1, 1),
('CONFIRMED', 'Booking has been confirmed', 1, 1),
('PAID', 'Booking has been fully paid', 1, 1),
('IN_PROGRESS', 'Tour is currently ongoing', 1, 1),
('COMPLETED', 'Booking has been completed', 1, 1),
('CANCELLED', 'Booking has been cancelled', 1, 1);

-- Insert payment methods
INSERT INTO payment_methods (name, description, status, created_by) VALUES
('CREDIT_CARD', 'Payment via credit card', 1, 1),
('DEBIT_CARD', 'Payment via debit card', 1, 1),
('BANK_TRANSFER', 'Payment via bank transfer', 1, 1),
('CASH', 'Payment in cash', 1, 1),
('DIGITAL_WALLET', 'Payment via digital wallet', 1, 1);

-- Insert payment statuses
INSERT INTO payment_status (name, description, status, created_by) VALUES
('PENDING', 'Payment is pending', 1, 1),
('PROCESSING', 'Payment is being processed', 1, 1),
('COMPLETED', 'Payment has been completed', 1, 1),
('FAILED', 'Payment has failed', 1, 1),
('REFUNDED', 'Payment has been refunded', 1, 1);

-- Insert cancellation reasons
INSERT INTO cancellation_reasons (name, description, status, created_by) VALUES
('CUSTOMER_REQUEST', 'Cancelled by customer request', 1, 1),
('WEATHER_CONDITIONS', 'Cancelled due to bad weather', 1, 1),
('HEALTH_ISSUES', 'Cancelled due to health problems', 1, 1),
('FORCE_MAJEURE', 'Cancelled due to unforeseen circumstances', 1, 1),
('OPERATOR_CANCELLATION', 'Cancelled by tour operator', 1, 1);

-- Insert refund statuses
INSERT INTO refund_status (name, description, status, created_by) VALUES
('PENDING', 'Refund is pending approval', 1, 1),
('APPROVED', 'Refund has been approved', 1, 1),
('PROCESSING', 'Refund is being processed', 1, 1),
('COMPLETED', 'Refund has been completed', 1, 1),
('REJECTED', 'Refund has been rejected', 1, 1);

-- Insert sample bookings
INSERT INTO bookings (
    booking_reference, user_id, package_schedule_id, total_persons, 
    total_amount, discount_amount, tax_amount, insurance_amount, final_amount,
    booking_date, travel_start_date, travel_end_date, booking_status_id,
    special_requirements, dietary_restrictions, insurance_required, created_by
) VALUES
('BK001', 1, 1, 2, 1200.00, 100.00, 60.00, 50.00, 1210.00, 
 '2024-01-15', '2024-03-01', '2024-03-07', 2, 
 'Window seat preferred', 'Vegetarian meals', TRUE, 1),
 
('BK002', 2, 3, 4, 2500.00, 200.00, 125.00, 100.00, 2525.00, 
 '2024-01-20', '2024-04-10', '2024-04-15', 2, 
 'Early check-in requested', 'No seafood', FALSE, 1),
 
('BK003', 3, 5, 1, 800.00, 50.00, 40.00, 30.00, 820.00, 
 '2024-02-01', '2024-05-01', '2024-05-05', 1, 
 'Solo traveler', 'None', TRUE, 1),
 
('BK004', 1, 7, 3, 1800.00, 150.00, 90.00, 75.00, 1815.00, 
 '2024-02-10', '2024-06-01', '2024-06-07', 3, 
 'Family with children', 'Child meals required', FALSE, 1),
 
('BK005', 2, 9, 2, 1500.00, 120.00, 75.00, 60.00, 1515.00, 
 '2024-02-15', '2024-07-01', '2024-07-06', 5, 
 'Honeymoon couple', 'None', TRUE, 1);

-- Insert booking participants
INSERT INTO booking_participants (
    booking_id, first_name, last_name, date_of_birth, gender_id, 
    passport_number, nationality_country_id, email, mobile_number,
    emergency_contact_name, emergency_contact_phone, emergency_contact_relationship,
    medical_conditions, allergies, special_assistance_required, created_by
) VALUES
-- Participants for BK001
(1, 'John', 'Smith', '1985-05-15', 1, 'AB123456', 1, 'john.smith@email.com', '+1234567890',
 'Jane Smith', '+1234567891', 'Spouse', 'None', 'Peanuts', FALSE, 1),
 
(1, 'Jane', 'Smith', '1986-08-20', 2, 'AB123457', 1, 'jane.smith@email.com', '+1234567892',
 'John Smith', '+1234567890', 'Spouse', 'Asthma', 'None', FALSE, 1),

-- Participants for BK002
(2, 'Mike', 'Johnson', '1978-03-10', 1, 'CD123458', 1, 'mike.j@email.com', '+1234567893',
 'Sarah Johnson', '+1234567894', 'Spouse', 'None', 'None', FALSE, 1),
 
(2, 'Sarah', 'Johnson', '1980-07-22', 2, 'CD123459', 1, 'sarah.j@email.com', '+1234567895',
 'Mike Johnson', '+1234567893', 'Spouse', 'None', 'Shellfish', FALSE, 1),
 
(2, 'Emily', 'Johnson', '2010-11-05', 2, 'CD123460', 1, NULL, NULL,
 'Mike Johnson', '+1234567893', 'Father', 'None', 'Dairy', FALSE, 1),
 
(2, 'Tom', 'Johnson', '2012-02-15', 1, 'CD123461', 1, NULL, NULL,
 'Sarah Johnson', '+1234567895', 'Mother', 'None', 'None', FALSE, 1),

-- Participant for BK003
(3, 'David', 'Wilson', '1990-12-30', 1, 'EF123462', 1, 'david.w@email.com', '+1234567896',
 'Robert Wilson', '+1234567897', 'Father', 'Diabetes', 'None', FALSE, 1),

-- Participants for BK004
(4, 'Robert', 'Brown', '1975-09-18', 1, 'GH123463', 1, 'robert.b@email.com', '+1234567898',
 'Lisa Brown', '+1234567899', 'Spouse', 'None', 'None', FALSE, 1),
 
(4, 'Lisa', 'Brown', '1978-04-25', 2, 'GH123464', 1, 'lisa.b@email.com', '+1234567900',
 'Robert Brown', '+1234567898', 'Spouse', 'None', 'Gluten', FALSE, 1),
 
(4, 'Sophia', 'Brown', '2015-06-12', 2, 'GH123465', 1, NULL, NULL,
 'Robert Brown', '+1234567898', 'Father', 'None', 'None', FALSE, 1);

-- Update room sharing relationships
UPDATE booking_participants SET room_sharing_with = 2 WHERE id = 1;
UPDATE booking_participants SET room_sharing_with = 1 WHERE id = 2;
UPDATE booking_participants SET room_sharing_with = 4 WHERE id = 3;
UPDATE booking_participants SET room_sharing_with = 3 WHERE id = 4;
UPDATE booking_participants SET room_sharing_with = 9 WHERE id = 8;
UPDATE booking_participants SET room_sharing_with = 8 WHERE id = 9;

-- Insert booking activities
INSERT INTO booking_activities (
    booking_id, activity_id, activity_schedule_id, activity_date,
    start_time, end_time, number_of_participants, price_per_person, total_price, status, created_by
) VALUES
-- Activities for BK001
(1, 1, 1, '2024-03-02', '09:00:00', '12:00:00', 2, 50.00, 100.00, 1, 1),
(1, 2, 3, '2024-03-03', '14:00:00', '17:00:00', 2, 75.00, 150.00, 1, 1),

-- Activities for BK002
(2, 3, 5, '2024-04-11', '10:00:00', '13:00:00', 4, 40.00, 160.00, 1, 1),
(2, 4, 7, '2024-04-12', '08:00:00', '11:00:00', 4, 60.00, 240.00, 1, 1),

-- Activities for BK003
(3, 5, 9, '2024-05-02', '11:00:00', '14:00:00', 1, 80.00, 80.00, 1, 1),

-- Activities for BK004
(4, 6, 11, '2024-06-02', '09:30:00', '12:30:00', 3, 45.00, 135.00, 1, 1),
(4, 7, 13, '2024-06-03', '15:00:00', '18:00:00', 3, 55.00, 165.00, 1, 1),

-- Activities for BK005
(5, 8, 15, '2024-07-02', '13:00:00', '16:00:00', 2, 70.00, 140.00, 1, 1);

-- Insert booking price breakdown
INSERT INTO booking_price_breakdown (
    booking_id, item_type, item_name, item_description, quantity, unit_price, total_price, created_by
) VALUES
-- BK001 Breakdown
(1, 'PACKAGE', 'Standard Tour Package', '5-day cultural tour', 2, 500.00, 1000.00, 1),
(1, 'ACTIVITY', 'City Walking Tour', 'Guided city tour', 2, 50.00, 100.00, 1),
(1, 'ACTIVITY', 'Mountain Hiking', 'Guided mountain hike', 2, 75.00, 150.00, 1),
(1, 'DISCOUNT', 'Early Bird Discount', '10% early booking discount', 1, -100.00, -100.00, 1),
(1, 'TAX', 'Service Tax', 'Government service tax', 1, 60.00, 60.00, 1),
(1, 'INSURANCE', 'Travel Insurance', 'Comprehensive travel insurance', 2, 25.00, 50.00, 1),

-- BK002 Breakdown
(2, 'PACKAGE', 'Family Tour Package', '4-day family adventure', 4, 550.00, 2200.00, 1),
(2, 'ACTIVITY', 'Wildlife Safari', 'Jeep safari experience', 4, 40.00, 160.00, 1),
(2, 'ACTIVITY', 'Waterfall Visit', 'Guided waterfall tour', 4, 60.00, 240.00, 1),
(2, 'DISCOUNT', 'Family Discount', 'Family package discount', 1, -200.00, -200.00, 1),
(2, 'TAX', 'Service Tax', 'Government service tax', 1, 125.00, 125.00, 1),
(2, 'INSURANCE', 'Travel Insurance', 'Comprehensive travel insurance', 4, 25.00, 100.00, 1);

-- Insert payments
INSERT INTO payments (
    booking_id, payment_reference, amount, payment_method_id, payment_status_id,
    installment_number, total_installments, payment_date, due_date, transaction_id, gateway_name, created_by
) VALUES
-- BK001 Payments
(1, 'PAY001', 605.00, 1, 3, 1, 2, '2024-01-16 10:30:00', '2024-01-20', 'TXN001', 'Stripe', 1),
(1, 'PAY002', 605.00, 1, 1, 2, 2, NULL, '2024-02-15', NULL, 'Stripe', 1),

-- BK002 Payments
(2, 'PAY003', 1262.50, 3, 3, 1, 2, '2024-01-21 14:20:00', '2024-01-25', 'TXN002', 'Bank Transfer', 1),
(2, 'PAY004', 1262.50, 3, 2, 2, 2, NULL, '2024-03-10', NULL, 'Bank Transfer', 1),

-- BK003 Payments
(3, 'PAY005', 820.00, 2, 3, 1, 1, '2024-02-02 09:15:00', '2024-02-05', 'TXN003', 'PayPal', 1),

-- BK004 Payments
(4, 'PAY006', 1815.00, 1, 3, 1, 1, '2024-02-11 16:45:00', '2024-02-15', 'TXN004', 'Stripe', 1),

-- BK005 Payments
(5, 'PAY007', 1515.00, 4, 3, 1, 1, '2024-02-16 11:30:00', '2024-02-20', 'TXN005', 'Cash', 1);

-- Insert booking invoices
INSERT INTO booking_invoices (
    booking_id, invoice_number, invoice_date, due_date, subtotal, tax_amount, 
    discount_amount, total_amount, amount_paid, balance_due,
    billing_full_name, billing_address, billing_email, billing_phone, status, created_by
) VALUES
(1, 'INV001', '2024-01-15', '2024-02-15', 1300.00, 60.00, 100.00, 1210.00, 605.00, 605.00,
 'John Smith', '123 Main St, City, State, 12345', 'john.smith@email.com', '+1234567890', 1, 1),
 
(2, 'INV002', '2024-01-20', '2024-03-10', 2700.00, 125.00, 200.00, 2525.00, 1262.50, 1262.50,
 'Mike Johnson', '456 Oak Ave, City, State, 12346', 'mike.j@email.com', '+1234567893', 1, 1),
 
(3, 'INV003', '2024-02-01', '2024-02-05', 850.00, 40.00, 50.00, 820.00, 820.00, 0.00,
 'David Wilson', '789 Pine Rd, City, State, 12347', 'david.w@email.com', '+1234567896', 1, 1),
 
(4, 'INV004', '2024-02-10', '2024-02-15', 1950.00, 90.00, 150.00, 1815.00, 1815.00, 0.00,
 'Robert Brown', '321 Elm St, City, State, 12348', 'robert.b@email.com', '+1234567898', 1, 1),
 
(5, 'INV005', '2024-02-15', '2024-02-20', 1620.00, 75.00, 120.00, 1515.00, 1515.00, 0.00,
 'Sarah Johnson', '654 Maple Dr, City, State, 12349', 'sarah.j@email.com', '+1234567895', 1, 1);

-- Insert booking documents
INSERT INTO booking_documents (
    booking_id, document_type, document_name, document_url, file_size, mime_type, status, created_by
) VALUES
(1, 'INVOICE', 'Invoice_BK001.pdf', '/documents/invoices/INV001.pdf', 2048, 'application/pdf', 1, 1),
(1, 'ITINERARY', 'Itinerary_BK001.pdf', '/documents/itineraries/IT_BK001.pdf', 3072, 'application/pdf', 1, 1),
(2, 'INVOICE', 'Invoice_BK002.pdf', '/documents/invoices/INV002.pdf', 2048, 'application/pdf', 1, 1),
(3, 'RECEIPT', 'Payment_Receipt_BK003.pdf', '/documents/receipts/RCP_BK003.pdf', 1024, 'application/pdf', 1, 1),
(4, 'TICKET', 'Entrance_Tickets_BK004.pdf', '/documents/tickets/TKT_BK004.pdf', 4096, 'application/pdf', 1, 1);

-- Insert booking notes
INSERT INTO booking_notes (
    booking_id, note_type, note_text, is_important, follow_up_date, follow_up_completed, created_by
) VALUES
(1, 'INTERNAL', 'Customer requested window seats for all flights. Confirmed with airline.', FALSE, NULL, FALSE, 1),
(1, 'CUSTOMER', 'Client called to confirm dietary requirements - vegetarian meals for both passengers.', FALSE, NULL, FALSE, 1),
(2, 'INTERNAL', 'Family with young children. Arrange adjacent rooms.', TRUE, '2024-03-01', FALSE, 1),
(3, 'SYSTEM', 'Automatic payment confirmation received.', FALSE, NULL, FALSE, 1),
(4, 'FOLLOW-UP', 'Send travel documents 2 weeks before departure.', TRUE, '2024-05-15', FALSE, 1);

-- Insert booking itinerary
INSERT INTO booking_itinerary (
    booking_id, day_number, itinerary_date, title, description, start_time, end_time, location, included_meals, created_by
) VALUES
-- BK001 Itinerary
(1, 1, '2024-03-01', 'Arrival and Check-in', 'Arrive at destination and check into hotel', '14:00:00', '16:00:00', 'Hotel Grand', 'Dinner', 1),
(1, 2, '2024-03-02', 'City Exploration', 'Guided city walking tour and local market visit', '09:00:00', '17:00:00', 'City Center', 'Breakfast, Lunch', 1),
(1, 3, '2024-03-03', 'Mountain Adventure', 'Hiking trip to nearby mountains with picnic lunch', '08:00:00', '18:00:00', 'Mountain Range', 'Breakfast, Lunch', 1),

-- BK002 Itinerary
(2, 1, '2024-04-10', 'Welcome and Orientation', 'Arrival and welcome dinner', '16:00:00', '20:00:00', 'Resort Paradise', 'Dinner', 1),
(2, 2, '2024-04-11', 'Wildlife Experience', 'Morning wildlife safari in national park', '06:00:00', '13:00:00', 'National Park', 'Breakfast, Lunch', 1);

-- Insert booking accommodation
INSERT INTO booking_accommodation (
    booking_id, check_in_date, check_out_date, hotel_name, room_type, room_number, confirmation_number, created_by
) VALUES
(1, '2024-03-01', '2024-03-04', 'Hotel Grand', 'Deluxe Double', '201', 'HGRN201', 1),
(2, '2024-04-10', '2024-04-13', 'Resort Paradise', 'Family Suite', '305', 'RPRD305', 1),
(3, '2024-05-01', '2024-05-04', 'City Hotel', 'Single Room', '108', 'CHTL108', 1),
(4, '2024-06-01', '2024-06-05', 'Mountain Lodge', 'Double Room', '215', 'MTLD215', 1);

-- Insert booking transportation
INSERT INTO booking_transportation (
    booking_id, transport_type, departure_date, departure_time, arrival_date, arrival_time,
    departure_location, arrival_location, carrier_name, reference_number, seat_numbers, created_by
) VALUES
(1, 'FLIGHT', '2024-03-01', '08:00:00', '2024-03-01', '11:00:00', 
 'City A Airport', 'City B Airport', 'Sky Airlines', 'SKY123', '14A,14B', 1),
(1, 'FLIGHT', '2024-03-07', '19:00:00', '2024-03-07', '22:00:00',
 'City B Airport', 'City A Airport', 'Sky Airlines', 'SKY124', '14A,14B', 1),
(2, 'TRAIN', '2024-04-10', '06:30:00', '2024-04-10', '09:30:00',
 'Central Station', 'Mountain Station', 'Rail Express', 'RE456', 'B12-B15', 1);

-- Insert booking insurance
INSERT INTO booking_insurance (
    booking_id, insurance_provider, policy_number, coverage_type, premium_amount,
    coverage_details, policy_start_date, policy_end_date, created_by
) VALUES
(1, 'Global Travel Insurance', 'GTI789456', 'Comprehensive', 50.00,
 'Medical coverage, trip cancellation, baggage loss', '2024-03-01', '2024-03-08', 1),
(2, 'Safe Journey Inc', 'SJI123789', 'Family Plan', 100.00,
 'Family medical coverage, emergency evacuation', '2024-04-10', '2024-04-16', 1),
(3, 'Travel Guard Co', 'TGC456123', 'Single Trip', 30.00,
 'Basic medical and trip interruption', '2024-05-01', '2024-05-06', 1);

-- Insert one cancelled booking with refund
INSERT INTO bookings (
    booking_reference, user_id, package_schedule_id, total_persons, 
    total_amount, discount_amount, tax_amount, insurance_amount, final_amount,
    booking_date, travel_start_date, travel_end_date, booking_status_id,
    cancellation_reason_id, cancellation_date, cancellation_notes, refund_amount, refund_status_id,
    special_requirements, insurance_required, created_by
) VALUES
('BK006', 3, 2, 2, 1000.00, 80.00, 50.00, 40.00, 1010.00, 
 '2024-01-25', '2024-03-15', '2024-03-18', 6, 
 1, '2024-02-10 14:30:00', 'Customer changed plans', 800.00, 3, 
 'None', TRUE, 1);

-- Insert refund for cancelled booking
INSERT INTO refunds (
    booking_id, payment_id, refund_reference, refund_amount, refund_reason,
    refund_status_id, processed_date, processed_by, bank_account_number, bank_name, account_holder_name, created_by
) VALUES
(6, 6, 'REF001', 800.00, 'Customer cancellation with 80% refund according to policy',
 3, '2024-02-12 10:00:00', 1, '1234567890', 'City Bank', 'David Wilson', 1);