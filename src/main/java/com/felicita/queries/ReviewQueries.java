package com.felicita.queries;

public class ReviewQueries {

    public static final String GET_ALL_TOUR_REVIEW = """
            SELECT
                tr.id AS review_id,
                tr.name AS reviewer_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                tr.number_of_participate,
                tr.created_at AS review_created_at,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.start_location,
                t.end_location,
                u.user_id,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email AS user_email,
                cs.name AS review_status,
                tri.id AS image_id,
                tri.name AS image_name,
                tri.description AS image_description,
                tri.image_url AS image_url
            FROM tour_review tr
            INNER JOIN tour_schedule ts
                ON tr.tour_schedule_id = ts.id
            INNER JOIN tour t
                ON ts.tour_id = t.tour_id
            LEFT JOIN user u
                ON tr.created_by = u.user_id
            LEFT JOIN tour_review_images tri
                ON tr.id = tri.tour_review_id
            LEFT JOIN common_status cs
                ON tr.status = cs.id
            WHERE tr.status = 1
            ORDER BY tr.created_at DESC, tri.id
            """;


}
