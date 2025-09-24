package com.felicita.queries;

public class ActivitiesQueries {

    public static final String GET_ALL_ACTIVITIES = """
            SELECT
                a.id AS activity_id,
                a.name AS activity_name,
                a.description AS activity_description,
                a.duration_hours,
                a.price AS activity_price,
                a.max_participants,
                a.min_participants,
                ac.id AS category_id,
                ac.name AS category_name,
                ac.description AS category_description,
                ac.image_url AS category_image,
                d.id AS destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location AS destination_location,
                d.rating AS destination_rating,
                cs.id AS status_id,
                cs.name AS status_name,
                cs.description AS status_description,
                ar.requirement_type,
                ar.requirement_value,
                ar.description AS requirement_description,
                ah.id AS history_id,
                ah.name AS history_name,
                ah.start_date,
                ah.end_date,
                ah.price AS history_price,
                ah.participants_count,
                ah.guide,
                ahi.id AS history_image_id,
                ahi.name AS history_image_name,
                ahi.image_url AS history_image_url,
                ahi.image_owner AS history_image_owner
            FROM activities a
            INNER JOIN activities_category ac ON a.activities_category_id = ac.id
            INNER JOIN destination d ON a.destination_id = d.id
            INNER JOIN common_status cs ON a.common_status_id = cs.id
            LEFT JOIN activities_requirements ar ON a.id = ar.activities_id
            LEFT JOIN activities_history ah ON a.id = ah.activities_id
            LEFT JOIN activities_history_images ahi ON ah.id = ahi.activities_history_id
            ORDER BY a.id, ah.start_date
            """;

    public static final String GET_ACTIVITY_CATEGORIES = """
            SELECT
                ac.id,
                ac.name,
                ac.description,
                ac.image_url,
                ac.color,
                ac.hover_color,
                cs.name AS status_name,
                ac.created_at,
                ac.created_by,
                ac.updated_at,
                ac.updated_by
            FROM activities_category ac
            JOIN common_status cs ON ac.common_status_id = cs.id
            """;


}
