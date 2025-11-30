-- Booking Status Table
CREATE TABLE booking_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL, -- Pending, Confirmed, Paid, Cancelled, Completed
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Payment Methods Table
CREATE TABLE payment_methods (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL, -- Credit Card, Bank Transfer, Cash, etc.
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Payment Status Table
CREATE TABLE payment_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL, -- Pending, Processing, Completed, Failed, Refunded
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Cancellation Reasons Table
CREATE TABLE cancellation_reasons (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Refund Status Table
CREATE TABLE refund_status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL, -- Pending, Approved, Processed, Rejected
    description TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Main Booking Table
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_reference VARCHAR(50) UNIQUE NOT NULL, -- Unique booking number
    user_id INT NOT NULL,
    package_schedule_id INT NOT NULL,
    
    -- Booking Details
    total_persons INT NOT NULL,
    total_amount DECIMAL(15,2) NOT NULL,
    discount_amount DECIMAL(15,2) DEFAULT 0.00,
    tax_amount DECIMAL(15,2) DEFAULT 0.00,
    insurance_amount DECIMAL(15,2) DEFAULT 0.00,
    final_amount DECIMAL(15,2) NOT NULL,
    
    -- Booking Dates
    booking_date DATE NOT NULL,
    travel_start_date DATE NOT NULL,
    travel_end_date DATE NOT NULL,
    
    -- Status
    booking_status_id INT NOT NULL,
    
    -- Cancellation & Refund
    cancellation_reason_id INT NULL,
    cancellation_date TIMESTAMP NULL,
    cancellation_notes TEXT,
    refund_amount DECIMAL(15,2) DEFAULT 0.00,
    refund_status_id INT NULL,
    
    -- Special Requirements
    special_requirements TEXT,
    dietary_restrictions TEXT,
    insurance_required BOOLEAN DEFAULT FALSE,
    
    -- Audit Fields
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    terminated_at TIMESTAMP NULL,
    terminated_by INT,
    
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (package_schedule_id) REFERENCES package_schedule(id),
    FOREIGN KEY (booking_status_id) REFERENCES booking_status(id),
    FOREIGN KEY (cancellation_reason_id) REFERENCES cancellation_reasons(id),
    FOREIGN KEY (refund_status_id) REFERENCES refund_status(id)
);

-- Booking Participants Table
CREATE TABLE booking_participants (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Personal Information
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    gender_id INT,
    passport_number VARCHAR(50),
    nationality_country_id INT,
    
    -- Contact Information
    email VARCHAR(150),
    mobile_number VARCHAR(20),
    
    -- Emergency Contact
    emergency_contact_name VARCHAR(200),
    emergency_contact_phone VARCHAR(20),
    emergency_contact_relationship VARCHAR(100),
    
    -- Special Needs
    medical_conditions TEXT,
    allergies TEXT,
    special_assistance_required BOOLEAN DEFAULT FALSE,
    assistance_details TEXT,
    
    -- Room Sharing
    room_sharing_with INT NULL, -- Reference to another participant
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (gender_id) REFERENCES gender(gender_id),
    FOREIGN KEY (nationality_country_id) REFERENCES country(country_id),
    FOREIGN KEY (room_sharing_with) REFERENCES booking_participants(id)
);

-- Booking Activities Table (Selected activities for the booking)
CREATE TABLE booking_activities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    activity_id INT NOT NULL,
    activity_schedule_id INT NOT NULL,
    
    -- Activity Details
    activity_date DATE NOT NULL,
    start_time TIME,
    end_time TIME,
    number_of_participants INT NOT NULL,
    price_per_person DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    
    -- Status
    status INT NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (activity_schedule_id) REFERENCES activities_schedule(id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Booking Price Breakdown Table
CREATE TABLE booking_price_breakdown (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    item_type VARCHAR(50) NOT NULL, -- Package, Activity, Extra, Tax, Discount, Insurance
    item_name VARCHAR(255) NOT NULL,
    item_description TEXT,
    quantity INT DEFAULT 1,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Payments Table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    payment_reference VARCHAR(100) UNIQUE NOT NULL,
    
    -- Payment Details
    amount DECIMAL(15,2) NOT NULL,
    payment_method_id INT NOT NULL,
    payment_status_id INT NOT NULL,
    
    -- Installment Information
    installment_number INT DEFAULT 1,
    total_installments INT DEFAULT 1,
    
    -- Payment Dates
    payment_date TIMESTAMP NULL,
    due_date DATE NOT NULL,
    
    -- Transaction Details
    transaction_id VARCHAR(200),
    gateway_response TEXT,
    gateway_name VARCHAR(100),
    
    -- Audit Fields
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id),
    FOREIGN KEY (payment_status_id) REFERENCES payment_status(id)
);

-- Refunds Table
CREATE TABLE refunds (
    refund_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    payment_id INT NOT NULL,
    
    -- Refund Details
    refund_reference VARCHAR(100) UNIQUE NOT NULL,
    refund_amount DECIMAL(15,2) NOT NULL,
    refund_reason TEXT,
    refund_status_id INT NOT NULL,
    
    -- Processing Details
    processed_date TIMESTAMP NULL,
    processed_by INT,
    bank_account_number VARCHAR(50),
    bank_name VARCHAR(100),
    account_holder_name VARCHAR(255),
    
    -- Audit Fields
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (payment_id) REFERENCES payments(payment_id),
    FOREIGN KEY (refund_status_id) REFERENCES refund_status(id),
    FOREIGN KEY (processed_by) REFERENCES user(user_id)
);

-- Booking Invoices Table
CREATE TABLE booking_invoices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    invoice_number VARCHAR(100) UNIQUE NOT NULL,
    
    -- Invoice Details
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    subtotal DECIMAL(15,2) NOT NULL,
    tax_amount DECIMAL(15,2) DEFAULT 0.00,
    discount_amount DECIMAL(15,2) DEFAULT 0.00,
    total_amount DECIMAL(15,2) NOT NULL,
    amount_paid DECIMAL(15,2) DEFAULT 0.00,
    balance_due DECIMAL(15,2) NOT NULL,
    
    -- Billing Information
    billing_full_name VARCHAR(255) NOT NULL,
    billing_address TEXT NOT NULL,
    billing_email VARCHAR(150) NOT NULL,
    billing_phone VARCHAR(20),
    
    -- Status
    status INT NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Booking Documents Table
CREATE TABLE booking_documents (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Document Details
    document_type VARCHAR(100) NOT NULL, -- Invoice, Receipt, Itinerary, Ticket, Passport, Visa
    document_name VARCHAR(255) NOT NULL,
    document_url VARCHAR(500) NOT NULL,
    file_size INT,
    mime_type VARCHAR(100),
    
    -- Status
    status INT NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (status) REFERENCES common_status(id)
);

-- Booking Notes Table (for internal communication)
CREATE TABLE booking_notes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Note Details
    note_type VARCHAR(50) NOT NULL, -- Internal, Customer, System, Follow-up
    note_text TEXT NOT NULL,
    is_important BOOLEAN DEFAULT FALSE,
    follow_up_date DATE NULL,
    follow_up_completed BOOLEAN DEFAULT FALSE,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Booking Itinerary Table (Detailed day-by-day plan)
CREATE TABLE booking_itinerary (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Itinerary Details
    day_number INT NOT NULL,
    itinerary_date DATE NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIME,
    end_time TIME,
    location VARCHAR(255),
    included_meals VARCHAR(100), -- Breakfast, Lunch, Dinner
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Booking Accommodation Table
CREATE TABLE booking_accommodation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Accommodation Details
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    hotel_name VARCHAR(255) NOT NULL,
    room_type VARCHAR(100),
    room_number VARCHAR(50),
    confirmation_number VARCHAR(100),
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Booking Transportation Table
CREATE TABLE booking_transportation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Transportation Details
    transport_type VARCHAR(100) NOT NULL, -- Flight, Train, Bus, Car
    departure_date DATE NOT NULL,
    departure_time TIME,
    arrival_date DATE,
    arrival_time TIME,
    departure_location VARCHAR(255),
    arrival_location VARCHAR(255),
    carrier_name VARCHAR(255),
    reference_number VARCHAR(100),
    seat_numbers VARCHAR(100),
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Booking Insurance Table
CREATE TABLE booking_insurance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    
    -- Insurance Details
    insurance_provider VARCHAR(255),
    policy_number VARCHAR(100),
    coverage_type VARCHAR(100),
    premium_amount DECIMAL(10,2),
    coverage_details TEXT,
    policy_start_date DATE,
    policy_end_date DATE,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by INT,
    
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);


CREATE INDEX idx_bookings_user_id ON bookings(user_id);
CREATE INDEX idx_bookings_package_schedule_id ON bookings(package_schedule_id);
CREATE INDEX idx_bookings_status ON bookings(booking_status_id);
CREATE INDEX idx_bookings_dates ON bookings(travel_start_date, travel_end_date);
CREATE INDEX idx_bookings_reference ON bookings(booking_reference);

CREATE INDEX idx_booking_participants_booking ON booking_participants(booking_id);
CREATE INDEX idx_booking_activities_booking ON booking_activities(booking_id);
CREATE INDEX idx_payments_booking_id ON payments(booking_id);
CREATE INDEX idx_payments_reference ON payments(payment_reference);
CREATE INDEX idx_invoices_booking_id ON booking_invoices(booking_id);
CREATE INDEX idx_invoices_number ON booking_invoices(invoice_number);
CREATE INDEX idx_documents_booking_id ON booking_documents(booking_id);
CREATE INDEX idx_notes_booking_id ON booking_notes(booking_id);
CREATE INDEX idx_itinerary_booking_id ON booking_itinerary(booking_id);
CREATE INDEX idx_refunds_booking_id ON refunds(booking_id);