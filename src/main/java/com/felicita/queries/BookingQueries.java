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

}
