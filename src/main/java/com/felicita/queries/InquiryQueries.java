package com.felicita.queries;

public class InquiryQueries {

    public static final String CREATE_INQUIRY = """
            INSERT INTO inquiries (
                customer_id,
                customer_name,
                country,
                email,
                phone_number,
                preferred_contact_method,
                preferred_destination,
                arrival_date,
                departure_date,
                adults_count,
                kids_count,
                special_requirements,
                inquiry_status_id,
                created_by
            ) VALUES (
                ?,  -- customer_id (userId)
                ?,  -- customer_name
                ?,  -- country
                ?,  -- email
                ?,  -- phone_number
                ?,  -- preferred_contact_method
                ?,  -- preferred_destination
                ?,  -- arrival_date
                ?,  -- departure_date
                ?,  -- adults_count
                ?,  -- kids_count
                ?,  -- special_requirements
                ?,  -- inquiry_status_id
                ?   -- created_by
            )
            """;

}
