package com.felicita.queries;

public class TourQueries {

    public static final String GET_ALL_TOURS = """
            SELECT
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                tt.name AS tour_type,
                tc.name AS tour_category,
                t.duration_days,
                t.start_date,
                t.end_date,
                ds_start.name AS start_location,
                ds_end.name AS end_location,
                t.max_people,
                t.min_people,
                t.price_per_person,
                cs.name AS tour_status,
                ti.id AS image_id,
                ti.name AS image_name,
                ti.image_url AS image_url,
                ti.description AS image_description,
                cs_img.name AS image_status,
                GROUP_CONCAT(DISTINCT td.destination_id) AS destination_ids, -- destination IDs as CSV
                t.created_at,
                t.created_by,
                t.updated_at,
                t.updated_by,
                t.terminated_at,
                t.terminated_by
            FROM tour t
            INNER JOIN tour_type tt ON t.tour_type_id = tt.id
            INNER JOIN tour_category tc ON t.tour_category_id = tc.id
            INNER JOIN common_status cs ON t.tour_status_id = cs.id
            INNER JOIN destination ds_start ON t.start_location_id = ds_start.id
            INNER JOIN destination ds_end ON t.end_location_id = ds_end.id
            LEFT JOIN tour_image ti ON t.tour_id = ti.tour_id
            LEFT JOIN common_status cs_img ON ti.status_id = cs_img.id
            LEFT JOIN tour_destination td ON t.tour_id = td.tour_id
            GROUP BY t.tour_id, ti.id
            ORDER BY t.tour_id, ti.id
            """;

}
