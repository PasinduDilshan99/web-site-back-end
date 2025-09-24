package com.felicita.queries;

public class PromotionsQueries {

    public static final String GET_ALL_PROMOTIONS = """
            SELECT
                p.id AS promotion_id,
                p.promotion_code,
                p.description AS promotion_description,
                p.start_date,
                p.end_date,
                p.apply_to,
                p.is_public,
                p.priority,
                p.max_usage_limit,
                p.per_user_limit,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.start_date AS tour_start_date,
                t.end_date AS tour_end_date,
                t.price_per_person,
                pt.name AS promotion_type,
                pt.description AS promotion_type_description,
                pr.min_person,
                pr.max_person,
                pr.min_price,
                pr.max_price,
                pr.discount_type,
                pr.discount_value,
                pr.eligible_customer_group,
                pr.payment_method_restriction,
                pr.booking_period_start,
                pr.booking_period_end,
                pr.travel_period_start,
                pr.travel_period_end,
                cs.name AS promotion_status
            FROM promotions p
            LEFT JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN promotion_type pt ON p.promotion_type_id = pt.id
            LEFT JOIN promotions_requirements pr ON p.requirement_id = pr.id
            LEFT JOIN common_status cs ON p.status_id = cs.id
            ORDER BY t.tour_id, p.priority
            """;

}
