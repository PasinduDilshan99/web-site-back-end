package com.felicita.queries;

public class BookingQueries {

    public static final String GET_COMPLETE_BOOKING_DETAILS_BY_ID = """
            SELECT 
                b.booking_id,
                b.booking_reference,
                b.booking_date,
                b.travel_start_date,
                b.travel_end_date,
                b.total_persons,
                b.total_amount,
                b.discount_amount,
                b.tax_amount,
                b.insurance_amount,
                b.final_amount,
                bs.name AS booking_status,
                cr.name AS cancellation_reason,
                b.cancellation_date,
                b.refund_amount,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.start_location,
                t.end_location,
                tt.name AS tour_type,
                tc.name AS tour_category,
                p.name AS package_name,
                p.description AS package_description,
                p.total_price AS package_total_price,
                p.discount_percentage,
                p.price_per_person AS package_price_per_person,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                u.username,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email,
                u.mobile_number1,
                DATEDIFF(b.travel_end_date, b.travel_start_date) + 1 AS actual_duration_days,
                CONCAT('Completed ', DATEDIFF(CURDATE(), b.travel_end_date), ' days ago') AS completion_time
            FROM bookings b
            INNER JOIN booking_status bs ON b.booking_status_id = bs.id
            INNER JOIN package_schedule ps ON b.package_schedule_id = ps.id
            INNER JOIN packages p ON ps.package_id = p.package_id
            INNER JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN cancellation_reasons cr ON b.cancellation_reason_id = cr.id
            INNER JOIN user u ON b.user_id = u.user_id
            WHERE b.user_id = ?
            AND bs.name = 'TOUR_COMPLETED'
            ORDER BY b.travel_end_date DESC
            """;

    public static final String GET_COMPLETE_BOOKING_PARTICIPANTS = """
            SELECT 
                bp.booking_id,
                bp.first_name,
                bp.last_name,
                bp.date_of_birth,
                TIMESTAMPDIFF(YEAR, bp.date_of_birth, CURDATE()) AS age,
                g.name AS gender,
                bp.passport_number,
                c.name AS nationality,
                bp.email,
                bp.mobile_number,
                bp.emergency_contact_name,
                bp.emergency_contact_phone,
                bp.emergency_contact_relationship,
                bp.medical_conditions,
                bp.allergies,
                bp.special_assistance_required,
                bp.assistance_details,
                bp2.first_name AS room_sharing_with_first_name,
                bp2.last_name AS room_sharing_with_last_name
            FROM booking_participants bp
            LEFT JOIN gender g ON bp.gender_id = g.gender_id
            LEFT JOIN country c ON bp.nationality_country_id = c.country_id
            LEFT JOIN booking_participants bp2 ON bp.room_sharing_with = bp2.id
            WHERE bp.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? AND bs.name = 'TOUR_COMPLETED'
            )
            ORDER BY bp.booking_id, bp.id
            """;

    // Similarly define the other queries...
    public static final String GET_COMPLETE_BOOKING_ACTIVITIES = """
            SELECT
                ba.booking_id,
                a.name AS activity_name,
                a.description AS activity_description,
                ac.name AS activity_category,
                ba.activity_date,
                ba.start_time,
                ba.end_time,
                ba.number_of_participants,
                ba.price_per_person,
                ba.total_price,
                d.name AS destination_name,
                a.duration_hours,
                a.price_local,
                a.price_foreigners,
                -- Activity status (completed since tour is completed)
                'COMPLETED' AS activity_status
            FROM booking_activities ba
            INNER JOIN activities a ON ba.activity_id = a.id
            LEFT JOIN activity_category ac ON a.activities_category = ac.id
            LEFT JOIN destination d ON a.destination_id = d.destination_id
            WHERE ba.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? AND bs.name = 'TOUR_COMPLETED'
            )
            ORDER BY ba.booking_id, ba.activity_date, ba.start_time
            """;
    public static final String GET_COMPLETE_BOOKING_PAYMENTS = """
            SELECT
                p.booking_id,
                p.payment_reference,
                p.amount,
                pm.name AS payment_method,
                ps.name AS payment_status,
                p.installment_number,
                p.total_installments,
                p.payment_date,
                p.due_date,
                p.transaction_id,
                bi.invoice_number,
                bi.invoice_date,
                bi.total_amount AS invoice_total,
                bi.amount_paid,
                bi.balance_due
            FROM payments p
            INNER JOIN payment_methods pm ON p.payment_method_id = pm.id
            INNER JOIN payment_status ps ON p.payment_status_id = ps.id
            LEFT JOIN booking_invoices bi ON p.booking_id = bi.booking_id
            WHERE p.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? AND bs.name = 'TOUR_COMPLETED'
            )
            ORDER BY p.booking_id, p.installment_number
            """;
    public static final String GET_COMPLETE_BOOKING_DOCUMENTS = """
            SELECT
                bd.booking_id,
                bd.document_type,
                bd.document_name,
                bd.document_url,
                bd.file_size
            FROM booking_documents bd
            WHERE bd.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? AND bs.name = 'TOUR_COMPLETED'
            )
            ORDER BY bd.booking_id, bd.document_type
            """;


    public static final String GET_UPCOMING_BOOKING_DETAILS_BY_ID = """
            SELECT 
                b.booking_id,
                b.booking_reference,
                b.booking_date,
                b.travel_start_date,
                b.travel_end_date,
                b.total_persons,
                b.total_amount,
                b.discount_amount,
                b.tax_amount,
                b.insurance_amount,
                b.final_amount,
                bs.name AS booking_status,
                cr.name AS cancellation_reason,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.start_location,
                t.end_location,
                tt.name AS tour_type,
                tc.name AS tour_category,
                p.name AS package_name,
                p.description AS package_description,
                p.total_price AS package_total_price,
                p.discount_percentage,
                p.price_per_person AS package_price_per_person,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                u.username,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email,
                u.mobile_number1,
                DATEDIFF(b.travel_start_date, CURDATE()) AS days_until_travel,
                CASE 
                    WHEN DATEDIFF(b.travel_start_date, CURDATE()) <= 7 THEN 'IMMINENT'
                    WHEN DATEDIFF(b.travel_start_date, CURDATE()) <= 30 THEN 'SOON'
                    ELSE 'FUTURE'
                END AS travel_urgency,
                CONCAT('Starts in ', DATEDIFF(b.travel_start_date, CURDATE()), ' days') AS countdown
            FROM bookings b
            INNER JOIN booking_status bs ON b.booking_status_id = bs.id
            INNER JOIN package_schedule ps ON b.package_schedule_id = ps.id
            INNER JOIN packages p ON ps.package_id = p.package_id
            INNER JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN cancellation_reasons cr ON b.cancellation_reason_id = cr.id
            INNER JOIN user u ON b.user_id = u.user_id
            WHERE b.user_id = ?
            AND bs.name IN ('CONFIRMED', 'PAID')
            AND b.travel_start_date > CURDATE()
            ORDER BY b.travel_start_date ASC
            """;

    public static final String GET_UPCOMING_BOOKING_PARTICIPANTS = """
            SELECT 
                bp.booking_id,
                bp.first_name,
                bp.last_name,
                bp.date_of_birth,
                TIMESTAMPDIFF(YEAR, bp.date_of_birth, CURDATE()) AS age,
                g.name AS gender,
                bp.passport_number,
                c.name AS nationality,
                bp.email,
                bp.mobile_number,
                bp.emergency_contact_name,
                bp.emergency_contact_phone,
                bp.emergency_contact_relationship,
                bp.medical_conditions,
                bp.allergies,
                bp.special_assistance_required,
                bp.assistance_details,
                bp2.first_name AS room_sharing_with_first_name,
                bp2.last_name AS room_sharing_with_last_name
            FROM booking_participants bp
            LEFT JOIN gender g ON bp.gender_id = g.gender_id
            LEFT JOIN country c ON bp.nationality_country_id = c.country_id
            LEFT JOIN booking_participants bp2 ON bp.room_sharing_with = bp2.id
            WHERE bp.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('CONFIRMED', 'PAID')
                AND b.travel_start_date > CURDATE()
            )
            ORDER BY bp.booking_id, bp.id
            """;

    public static final String GET_UPCOMING_BOOKING_ACTIVITIES = """
            SELECT 
                ba.booking_id,
                a.name AS activity_name,
                a.description AS activity_description,
                ac.name AS activity_category,
                ba.activity_date,
                ba.start_time,
                ba.end_time,
                ba.number_of_participants,
                ba.price_per_person,
                ba.total_price,
                d.name AS destination_name,
                a.duration_hours,
                a.price_local,
                a.price_foreigners,
                DATEDIFF(ba.activity_date, CURDATE()) AS days_until_activity,
                CASE 
                    WHEN ba.activity_date = b.travel_start_date THEN 'FIRST_DAY'
                    WHEN ba.activity_date = b.travel_end_date THEN 'LAST_DAY'
                    ELSE 'MID_TOUR'
                END AS activity_timing
            FROM booking_activities ba
            INNER JOIN activities a ON ba.activity_id = a.id
            INNER JOIN bookings b ON ba.booking_id = b.booking_id
            LEFT JOIN activity_category ac ON a.activities_category = ac.id
            LEFT JOIN destination d ON a.destination_id = d.destination_id
            WHERE ba.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('CONFIRMED', 'PAID')
                AND b.travel_start_date > CURDATE()
            )
            ORDER BY ba.booking_id, ba.activity_date, ba.start_time
            """;

    public static final String GET_UPCOMING_BOOKING_PAYMENTS = """
            SELECT 
                p.booking_id,
                p.payment_reference,
                p.amount,
                pm.name AS payment_method,
                ps.name AS payment_status,
                p.installment_number,
                p.total_installments,
                p.payment_date,
                p.due_date,
                p.transaction_id,
                bi.invoice_number,
                bi.invoice_date,
                bi.total_amount AS invoice_total,
                bi.amount_paid,
                bi.balance_due,
                CASE 
                    WHEN p.due_date <= CURDATE() AND ps.name != 'COMPLETED' THEN 'OVERDUE'
                    WHEN p.due_date <= CURDATE() + INTERVAL 7 DAY AND ps.name != 'COMPLETED' THEN 'DUE_SOON'
                    ELSE 'UP_TO_DATE'
                END AS payment_urgency
            FROM payments p
            INNER JOIN payment_methods pm ON p.payment_method_id = pm.id
            INNER JOIN payment_status ps ON p.payment_status_id = ps.id
            LEFT JOIN booking_invoices bi ON p.booking_id = bi.booking_id
            WHERE p.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('CONFIRMED', 'PAID')
                AND b.travel_start_date > CURDATE()
            )
            ORDER BY p.booking_id, p.installment_number
            """;

    public static final String GET_UPCOMING_BOOKING_DOCUMENTS = """
            SELECT 
                bd.booking_id,
                bd.document_type,
                bd.document_name,
                bd.document_url,
                bd.file_size,
                CASE 
                    WHEN bd.document_type IN ('ITINERARY', 'TICKET') THEN 'TRAVEL_DOCS'
                    WHEN bd.document_type IN ('INVOICE', 'RECEIPT') THEN 'FINANCIAL_DOCS'
                    ELSE 'OTHER_DOCS'
                END AS document_category
            FROM booking_documents bd
            WHERE bd.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('CONFIRMED', 'PAID')
                AND b.travel_start_date > CURDATE()
            )
            ORDER BY bd.booking_id, bd.document_type
            """;

    public static final String GET_REQUESTED_BOOKING_DETAILS_BY_ID = """
            SELECT 
                b.booking_id,
                b.booking_reference,
                b.booking_date,
                b.travel_start_date,
                b.travel_end_date,
                b.total_persons,
                b.total_amount,
                b.discount_amount,
                b.tax_amount,
                b.insurance_amount,
                b.final_amount,
                bs.name AS booking_status,
                cr.name AS cancellation_reason,
                b.cancellation_date,
                b.refund_amount,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.start_location,
                t.end_location,
                tt.name AS tour_type,
                tc.name AS tour_category,
                p.name AS package_name,
                p.description AS package_description,
                p.total_price AS package_total_price,
                p.discount_percentage,
                p.price_per_person AS package_price_per_person,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                u.username,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email,
                u.mobile_number1,
                -- Request Status Info
                CASE 
                    WHEN bs.name = 'PENDING' THEN 'AWAITING_APPROVAL'
                    WHEN bs.name = 'IN_PROGRESS' THEN 'UNDER_REVIEW'
                    ELSE 'PROCESSING'
                END AS request_status,
                CASE 
                    WHEN bs.name = 'PENDING' THEN 'PENDING_APPROVAL'
                    WHEN bs.name = 'IN_PROGRESS' THEN 'IN_REVIEW'
                    ELSE 'PROCESSING'
                END AS approval_status,
                DATEDIFF(b.travel_start_date, CURDATE()) AS days_until_travel,
                CASE 
                    WHEN DATEDIFF(b.travel_start_date, CURDATE()) <= 7 THEN 'URGENT'
                    WHEN DATEDIFF(b.travel_start_date, CURDATE()) <= 30 THEN 'SOON'
                    ELSE 'FUTURE'
                END AS request_urgency,
                CONCAT('Requested ', DATEDIFF(CURDATE(), b.booking_date), ' days ago') AS request_age
            FROM bookings b
            INNER JOIN booking_status bs ON b.booking_status_id = bs.id
            INNER JOIN package_schedule ps ON b.package_schedule_id = ps.id
            INNER JOIN packages p ON ps.package_id = p.package_id
            INNER JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN cancellation_reasons cr ON b.cancellation_reason_id = cr.id
            INNER JOIN user u ON b.user_id = u.user_id
            WHERE b.user_id = ?
            AND bs.name IN ('PENDING', 'IN_PROGRESS')
            ORDER BY 
                CASE 
                    WHEN bs.name = 'PENDING' THEN 1
                    WHEN bs.name = 'IN_PROGRESS' THEN 2
                    ELSE 3
                END,
                b.booking_date DESC
            """;

    public static final String GET_REQUESTED_BOOKING_PARTICIPANTS = """
            SELECT 
                bp.booking_id,
                bp.first_name,
                bp.last_name,
                bp.date_of_birth,
                TIMESTAMPDIFF(YEAR, bp.date_of_birth, CURDATE()) AS age,
                g.name AS gender,
                bp.passport_number,
                c.name AS nationality,
                bp.email,
                bp.mobile_number,
                bp.emergency_contact_name,
                bp.emergency_contact_phone,
                bp.emergency_contact_relationship,
                bp.medical_conditions,
                bp.allergies,
                bp.special_assistance_required,
                bp.assistance_details,
                bp2.first_name AS room_sharing_with_first_name,
                bp2.last_name AS room_sharing_with_last_name
            FROM booking_participants bp
            LEFT JOIN gender g ON bp.gender_id = g.gender_id
            LEFT JOIN country c ON bp.nationality_country_id = c.country_id
            LEFT JOIN booking_participants bp2 ON bp.room_sharing_with = bp2.id
            WHERE bp.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('PENDING', 'IN_PROGRESS')
            )
            ORDER BY bp.booking_id, bp.id
            """;

    public static final String GET_REQUESTED_BOOKING_ACTIVITIES = """
            SELECT 
                ba.booking_id,
                a.name AS activity_name,
                a.description AS activity_description,
                ac.name AS activity_category,
                ba.activity_date,
                ba.start_time,
                ba.end_time,
                ba.number_of_participants,
                ba.price_per_person,
                ba.total_price,
                d.name AS destination_name,
                a.duration_hours,
                a.price_local,
                a.price_foreigners,
                'REQUESTED' AS activity_status,
                CASE 
                    WHEN a.max_participate IS NOT NULL AND ba.number_of_participants > a.max_participate THEN 'WAITLIST'
                    ELSE 'AVAILABLE'
                END AS availability_status
            FROM booking_activities ba
            INNER JOIN activities a ON ba.activity_id = a.id
            LEFT JOIN activity_category ac ON a.activities_category = ac.id
            LEFT JOIN destination d ON a.destination_id = d.destination_id
            WHERE ba.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('PENDING', 'IN_PROGRESS')
            )
            ORDER BY ba.booking_id, ba.activity_date, ba.start_time
            """;

    public static final String GET_REQUESTED_BOOKING_PAYMENTS = """
            SELECT 
                p.booking_id,
                p.payment_reference,
                p.amount,
                pm.name AS payment_method,
                ps.name AS payment_status,
                p.installment_number,
                p.total_installments,
                p.payment_date,
                p.due_date,
                p.transaction_id,
                bi.invoice_number,
                bi.invoice_date,
                bi.total_amount AS invoice_total,
                bi.amount_paid,
                bi.balance_due,
                CASE 
                    WHEN p.due_date <= CURDATE() AND ps.name != 'COMPLETED' THEN 'HIGH_PRIORITY'
                    WHEN p.due_date <= CURDATE() + INTERVAL 7 DAY AND ps.name != 'COMPLETED' THEN 'MEDIUM_PRIORITY'
                    ELSE 'LOW_PRIORITY'
                END AS payment_priority,
                CASE 
                    WHEN p.amount > 1000 THEN TRUE
                    ELSE FALSE
                END AS deposit_required,
                CASE 
                    WHEN p.amount > 1000 THEN p.amount * 0.2
                    ELSE p.amount
                END AS deposit_amount
            FROM payments p
            INNER JOIN payment_methods pm ON p.payment_method_id = pm.id
            INNER JOIN payment_status ps ON p.payment_status_id = ps.id
            LEFT JOIN booking_invoices bi ON p.booking_id = bi.booking_id
            WHERE p.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('PENDING', 'IN_PROGRESS')
            )
            ORDER BY p.booking_id, p.installment_number
            """;

    public static final String GET_REQUESTED_BOOKING_DOCUMENTS = """
            SELECT 
                bd.booking_id,
                bd.document_type,
                bd.document_name,
                bd.document_url,
                bd.file_size,
                CASE 
                    WHEN bd.document_type IN ('PASSPORT_COPY', 'ID_PROOF') THEN 'VERIFIED'
                    ELSE 'PENDING_VERIFICATION'
                END AS document_status,
                CASE 
                    WHEN bd.document_type IN ('PASSPORT_COPY', 'ID_PROOF', 'MEDICAL_CERTIFICATE') THEN TRUE
                    ELSE FALSE
                END AS required_for_approval
            FROM booking_documents bd
            WHERE bd.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name IN ('PENDING', 'IN_PROGRESS')
            )
            ORDER BY bd.booking_id, bd.document_type
            """;

    public static final String GET_CANCELLED_BOOKING_DETAILS_BY_ID = """
            SELECT 
                b.booking_id,
                b.booking_reference,
                b.booking_date,
                b.travel_start_date,
                b.travel_end_date,
                b.total_persons,
                b.total_amount,
                b.discount_amount,
                b.tax_amount,
                b.insurance_amount,
                b.final_amount,
                bs.name AS booking_status,
                cr.name AS cancellation_reason,
                b.cancellation_date,
                b.refund_amount,
                COALESCE(rs.name, 'PENDING') AS refund_status,
                COALESCE(r.refund_amount, 0.00) AS refunded_amount,
                r.processed_date AS refund_processed_date,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.start_location,
                t.end_location,
                tt.name AS tour_type,
                tc.name AS tour_category,
                p.name AS package_name,
                p.description AS package_description,
                p.total_price AS package_total_price,
                p.discount_percentage,
                p.price_per_person AS package_price_per_person,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                u.username,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email,
                u.mobile_number1,
                -- Cancellation Info
                CASE 
                    WHEN DATEDIFF(b.cancellation_date, b.booking_date) < 7 THEN 'EARLY_CANCELLATION'
                    WHEN DATEDIFF(b.travel_start_date, b.cancellation_date) > 30 THEN 'ADVANCED_CANCELLATION'
                    WHEN DATEDIFF(b.travel_start_date, b.cancellation_date) > 7 THEN 'LAST_MINUTE_CANCELLATION'
                    ELSE 'LATE_CANCELLATION'
                END AS cancellation_stage,
                DATEDIFF(b.travel_start_date, DATE(b.cancellation_date)) AS days_before_travel,
                b.final_amount * 0.1 AS cancellation_fee,
                CASE 
                    WHEN DATEDIFF(b.travel_start_date, DATE(b.cancellation_date)) > 30 THEN 10.00
                    WHEN DATEDIFF(b.travel_start_date, DATE(b.cancellation_date)) > 14 THEN 25.00
                    WHEN DATEDIFF(b.travel_start_date, DATE(b.cancellation_date)) > 7 THEN 50.00
                    ELSE 75.00
                END AS cancellation_penalty_percentage,
                b.cancellation_notes
            FROM bookings b
            INNER JOIN booking_status bs ON b.booking_status_id = bs.id
            INNER JOIN package_schedule ps ON b.package_schedule_id = ps.id
            INNER JOIN packages p ON ps.package_id = p.package_id
            INNER JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN cancellation_reasons cr ON b.cancellation_reason_id = cr.id
            LEFT JOIN refunds r ON b.booking_id = r.booking_id
            LEFT JOIN refund_status rs ON r.refund_status_id = rs.id
            INNER JOIN user u ON b.user_id = u.user_id
            WHERE b.user_id = ?
            AND bs.name = 'CANCELLED'
            ORDER BY b.cancellation_date DESC
            """;

    public static final String GET_CANCELLED_BOOKING_PARTICIPANTS = """
            SELECT 
                bp.booking_id,
                bp.first_name,
                bp.last_name,
                bp.date_of_birth,
                TIMESTAMPDIFF(YEAR, bp.date_of_birth, CURDATE()) AS age,
                g.name AS gender,
                bp.passport_number,
                c.name AS nationality,
                bp.email,
                bp.mobile_number,
                bp.emergency_contact_name,
                bp.emergency_contact_phone,
                bp.emergency_contact_relationship,
                bp.medical_conditions,
                bp.allergies,
                bp.special_assistance_required,
                bp.assistance_details,
                bp2.first_name AS room_sharing_with_first_name,
                bp2.last_name AS room_sharing_with_last_name,
                CASE 
                    WHEN r.refund_id IS NOT NULL THEN TRUE
                    ELSE FALSE
                END AS refund_issued,
                COALESCE(r.refund_amount / (
                    SELECT COUNT(*) 
                    FROM booking_participants bp3 
                    WHERE bp3.booking_id = bp.booking_id
                ), 0.00) AS participant_refund_amount
            FROM booking_participants bp
            LEFT JOIN gender g ON bp.gender_id = g.gender_id
            LEFT JOIN country c ON bp.nationality_country_id = c.country_id
            LEFT JOIN booking_participants bp2 ON bp.room_sharing_with = bp2.id
            LEFT JOIN refunds r ON bp.booking_id = r.booking_id
            WHERE bp.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name = 'CANCELLED'
            )
            ORDER BY bp.booking_id, bp.id
            """;

    public static final String GET_CANCELLED_BOOKING_ACTIVITIES = """
            SELECT 
                ba.booking_id,
                a.name AS activity_name,
                a.description AS activity_description,
                ac.name AS activity_category,
                ba.activity_date,
                ba.start_time,
                ba.end_time,
                ba.number_of_participants,
                ba.price_per_person,  -- FIXED: Changed from price_perperson to price_per_person
                ba.total_price,
                d.name AS destination_name,
                a.duration_hours,
                a.price_local,
                a.price_foreigners,
                'CANCELLED' AS activity_status,
                CASE 
                    WHEN DATEDIFF(DATE(b.cancellation_date), ba.activity_date) > 7 THEN TRUE
                    ELSE FALSE
                END AS activity_refundable,
                CASE 
                    WHEN DATEDIFF(DATE(b.cancellation_date), ba.activity_date) > 7 THEN ba.total_price * 0.8
                    ELSE 0.00
                END AS activity_refund_amount
            FROM booking_activities ba
            INNER JOIN activities a ON ba.activity_id = a.id
            INNER JOIN bookings b ON ba.booking_id = b.booking_id
            LEFT JOIN activity_category ac ON a.activities_category = ac.id
            LEFT JOIN destination d ON a.destination_id = d.destination_id
            WHERE ba.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name = 'CANCELLED'
            )
            ORDER BY ba.booking_id, ba.activity_date, ba.start_time
            """;

    public static final String GET_CANCELLED_BOOKING_PAYMENTS = """
            SELECT 
                p.booking_id,
                p.payment_reference,
                p.amount,
                pm.name AS payment_method,
                ps.name AS payment_status,
                p.installment_number,
                p.total_installments,
                p.payment_date,
                p.due_date,
                p.transaction_id,
                bi.invoice_number,
                bi.invoice_date,
                bi.total_amount AS invoice_total,
                bi.amount_paid,
                bi.balance_due,
            
                -- Refund Information (FIXED: Use consistent naming)
                r.refund_reference,
                r.refund_amount,
                r.processed_date AS refund_date,
                rs.name AS refund_status,  -- FIXED: Changed back to refund_status
            
                -- Payment Priority
                CASE 
                    WHEN p.due_date <= CURDATE() AND ps.name != 'COMPLETED' THEN 'HIGH_PRIORITY'
                    WHEN p.due_date <= CURDATE() + INTERVAL 7 DAY AND ps.name != 'COMPLETED' THEN 'MEDIUM_PRIORITY'
                    ELSE 'LOW_PRIORITY'
                END AS payment_priority,
            
                -- Deposit Info
                CASE 
                    WHEN p.amount > 1000 THEN TRUE
                    ELSE FALSE
                END AS deposit_required,
            
                CASE 
                    WHEN p.amount > 1000 THEN p.amount * 0.2
                    ELSE p.amount
                END AS deposit_amount,
            
                -- Cancellation Refund Info (FIXED: This was duplicate, remove or rename)
                CASE 
                    WHEN r.refund_id IS NOT NULL THEN 'REFUND_PROCESSED'
                    WHEN b.refund_amount > 0 THEN 'REFUND_PENDING'
                    ELSE 'NO_REFUND'
                END AS refund_status_info,
            
                -- Add payment priority fields that were missing in Response
                p.payment_method_id,
                p.payment_status_id,
                r.refund_status_id
            
            FROM payments p
            INNER JOIN payment_methods pm ON p.payment_method_id = pm.id
            INNER JOIN payment_status ps ON p.payment_status_id = ps.id
            LEFT JOIN booking_invoices bi ON p.booking_id = bi.booking_id
            LEFT JOIN refunds r ON p.booking_id = r.booking_id AND p.payment_id = r.payment_id
            LEFT JOIN refund_status rs ON r.refund_status_id = rs.id
            LEFT JOIN bookings b ON p.booking_id = b.booking_id
            WHERE p.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name = 'CANCELLED'
            )
            ORDER BY p.booking_id, p.installment_number
            """;

    public static final String GET_CANCELLED_BOOKING_DOCUMENTS = """
            SELECT 
                bd.booking_id,
                bd.document_type,
                bd.document_name,
                bd.document_url,
                bd.file_size,
                CASE 
                    WHEN bd.document_type IN ('CANCELLATION_FORM', 'REFUND_REQUEST') THEN 'PROCESSED'
                    ELSE 'ARCHIVED'
                END AS document_status,
                CASE 
                    WHEN bd.document_type IN ('CANCELLATION_FORM', 'REFUND_REQUEST', 'CANCELLATION_CONFIRMATION') THEN TRUE
                    ELSE FALSE
                END AS cancellation_related
            FROM booking_documents bd
            WHERE bd.booking_id IN (
                SELECT b.booking_id FROM bookings b
                INNER JOIN booking_status bs ON b.booking_status_id = bs.id
                WHERE b.user_id = ? 
                AND bs.name = 'CANCELLED'
            )
            ORDER BY bd.booking_id, bd.document_type
            """;

    public static final String INSERT_BOOKING_BASIC_DETAILS = """
                INSERT INTO bookings (
                    booking_reference,
                    user_id,
                    package_schedule_id,
                    total_persons,
                    total_amount,
                    discount_amount,
                    tax_amount,
                    insurance_amount,
                    final_amount,
                    booking_date,
                    travel_start_date,
                    travel_end_date,
                    booking_status_id,
                    special_requirements,
                    dietary_restrictions,
                    insurance_required,
                    created_by
                ) VALUES (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                )
            """;

    public static final String INSERT_BOOKING_TRANSPORTATION = """
                INSERT INTO booking_transportation (
                    booking_id,
                    transport_type,
                    departure_date,
                    carrier_name,
                    reference_number,
                    created_at,
                    created_by
                ) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?)
            """;

    public static final String INSERT_BOOKING_PRICE_BREAKDOWN = """
                INSERT INTO booking_price_breakdown (
                    booking_id,
                    item_type,
                    item_name,
                    item_description,
            		quantity,
                    unit_price,
                    total_price,
                    created_at,
                    created_by
                ) VALUES (?, ?, ?, ?, ?, ?,?, CURRENT_TIMESTAMP, ?)
            """;

    public static final String INSERT_BOOKING_PARTICIPANT = """
                INSERT INTO booking_participants (
                    booking_id,
                    first_name,
                    last_name,
                    date_of_birth,
                    gender_id,
                    passport_number,
                    nationality_country_id,
                    email,
                    mobile_number,
                    emergency_contact_name,
                    emergency_contact_phone,
                    emergency_contact_relationship,
                    medical_conditions,
                    allergies,
                    special_assistance_required,
                    assistance_details,
                    room_sharing_with,
                    created_by
                ) VALUES (
                    ?, -- booking_id
                    ?, -- first_name
                    ?, -- last_name
                    ?, -- date_of_birth
                    (SELECT gender_id FROM gender WHERE name = ? LIMIT 1),        -- gender_id from gender table
                    ?, -- passport_number
                    (SELECT country_id FROM country WHERE name = ? LIMIT 1),      -- nationality_country_id from country table
                    ?, -- email
                    ?, -- mobile_number
                    ?, -- emergency_contact_name
                    ?, -- emergency_contact_phone
                    ?, -- emergency_contact_relationship
                    ?, -- medical_conditions
                    ?, -- allergies
                    ?, -- special_assistance_required
                    ?, -- assistance_details
                    ?, -- room_sharing_with
                    ?  -- created_by
                )
            """;


    public static final String INSERT_BOOKING_NOTE = """
                INSERT INTO booking_notes (
                    booking_id,
                    note_type,
                    note_text,
                    created_at,
                    created_by
                ) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?)
            """;

    public static final String INSERT_BOOKING_ITINERARY = """
                INSERT INTO booking_itinerary (
                    booking_id,
                    day_number,
                    itinerary_date,
                    title,
                    description,
                    start_time,
                    end_time,
                    location,
                    included_meals,
                    created_at,
                    created_by
                ) VALUES (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?
                )
            """;

    public static final String INSERT_BOOKING_ACTIVITIES = """
    INSERT INTO booking_activities (
        booking_id,
        activity_id,
        activity_schedule_id,
        activity_date,
        start_time,
        end_time,
        number_of_participants,
        price_per_person,
        total_price,
        status,
        created_at,
        created_by
    ) VALUES (
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?
    )
""";

    public static final String INSERT_BOOKING_INVOICE = """
    INSERT INTO booking_invoices (
        booking_id,
        invoice_number,
        invoice_date,
        due_date,
        subtotal,
        tax_amount,
        discount_amount,
        total_amount,
        amount_paid,
        balance_due,
        billing_full_name,
        billing_address,
        billing_email,
        billing_phone,
        status,
        created_at,
        created_by
    ) VALUES (
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?
    )
""";


    public static final String GET_BOOKING_BASIC_DETAILS_BY_BOOKING_ID = """
            SELECT
            	b.booking_id,
                b.booking_reference,
            	bi.invoice_number,
                bi.invoice_date,
                bi.due_date,
                bi.subtotal,
                bi.tax_amount,
                bi.discount_amount,
            	bi.insurance_amount,
                p.price_per_person AS package_price,
                bi.total_amount,
                bi.amount_paid,
                bi.balance_due,
                bi.billing_full_name,
                bi.billing_address,
                bi.billing_email,
                bi.billing_phone,
                p.name AS package_name,
                ps.id AS package_schedule_id,
                ps.assume_start_date,
                ps.assume_end_date,
                t.name AS tour_name,
                t.description AS tour_description,
                b.final_amount,
                b.booking_date,
                bs.name AS booking_status
            FROM bookings b
            LEFT JOIN booking_invoices bi
            	ON b.booking_id = bi.booking_id
            LEFT JOIN package_schedule ps
            	ON ps.id = b.package_schedule_id
            LEFT JOIN packages p
            	ON p.package_id =  ps.package_id
            LEFt JOIN tour t
            	ON t.tour_id = p.tour_id
            LEFT JOIN booking_status bs
            	ON bs.id = b.booking_status_id
            WHERE b.booking_id = ?
            """;
    public static final String GET_BOOKING_ACTIVITIES_BY_BOOKING_ID = """
            SELECT
            	ba.booking_id,
                ba.activity_id,
                a.name,
                a.description,
                ba.number_of_participants,
                ba.price_per_person,
                ba.total_price
            FROM booking_activities ba
            LEFT JOIN activities a
            	ON a.id = ba.activity_id
            WHERE ba.booking_id = ?
            """;
    public static final String GET_BOOKING_PARTICIPANTS_BY_BOOKING_ID = """
            SELECT
            	bp.booking_id,
                bp.first_name,
                bp.last_name,
                bp.date_of_birth,
                g.name,
                bp.passport_number,
                c.name,
                bp.email,
                bp.mobile_number,
                bp.medical_conditions,
                bp.allergies
            FROM booking_participants bp
            LEFT JOIN gender g
            	ON g.gender_id = bp.gender_id
            LEFT JOIN country c
            	ON c.country_id = bp.nationality_country_id
            WHERE bp.booking_id = ?
            """;
    public static final String GET_BOOKING_FILTER = """
            SELECT
            	t.tour_id AS tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                p.package_id AS package_id,
                p.name AS package_name,
                p.description AS package_description,
                ps.id AS package_schedule_id,
                ps.name AS package_schedule_name,
                ps.description AS package_schedule_description,
                ps.assume_start_date AS package_schedule_start_date,
                ps.assume_end_date AS package_schedule_end_date
            FROM tour t
            LEFT JOIN packages p
            	ON t.tour_id = p.tour_id
            LEFT JOIN package_schedule ps
            	ON ps.package_id = p.package_id
            """;
    public static final String GET_BOOKED_TOURS_BY_USER_ID = """
            SELECT
            	b.booking_id,
                bi.invoice_number,
                b.booking_reference,
                p.name AS package_name,
                ps.name AS package_schedule_name,
                t.name AS tour_name
            FROM bookings b
            LEFT JOIN booking_invoices bi
            	ON b.booking_id = bi.booking_id
            LEFT JOIN package_schedule ps
            	ON ps.id = b.package_schedule_id
            LEFT JOIN packages p
            	ON p.package_id =  ps.package_id
            LEFt JOIN tour t
            	ON t.tour_id = p.tour_id
            WHERE b.user_id = ?
            """;

    public static final String INSERT_BOOKING_AIRPORT_TRANSPORTATION = """
    INSERT INTO booking_transportation (
        booking_id,
        transport_type,
        departure_date,
        departure_time,
        arrival_date,
        arrival_time,
        departure_location,
        arrival_location,
        created_by,
        created_at
    ) VALUES (
        ?,?, ?, ?, ?, ?, ?, ?, ?, NOW()
    )
    """;

}
