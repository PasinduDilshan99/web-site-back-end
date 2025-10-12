package com.felicita.queries;

public class TourQueries {

    public static final String GET_ALL_TOURS = """
            SELECT
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration,
                t.latitude,
                t.longitude,
                t.start_location,
                t.end_location,
                tt.name AS tour_type_name,
                tt.description AS tour_type_description,
                tc.name AS tour_category_name,
                tc.description AS tour_category_description,
                s.name AS season_name,
                s.description AS season_description,
                cs.name AS status_name,
                sch.id AS schedule_id,
                sch.name AS schedule_name,
                sch.assume_start_date,
                sch.assume_end_date,
                sch.duration_start,
                sch.duration_end,
                sch.special_note,
                sch.description AS schedule_description,
                img.id AS image_id,
                img.name AS image_name,
                img.description AS image_description,
                img.image_url
            FROM tour t
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN seasons s ON t.season = s.id
            LEFT JOIN common_status cs ON t.status = cs.id
            LEFT JOIN tour_schedule sch ON t.tour_id = sch.tour_id
            LEFT JOIN tour_images img ON t.tour_id = img.tour_id;
            """;


    public static final String GET_POPULAR_TOURS = """
            SELECT
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.latitude,
                t.longitude,
                t.start_location,
                t.end_location,
                tt.name AS tour_type,
                tc.name AS tour_category,
                s.name AS season,
                cs_t.name AS tour_status,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                ts.duration_start,
                ts.duration_end,
                ts.special_note,
                ts.description AS schedule_description,
                cs_ts.name AS schedule_status,
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location AS destination_location,
                cs_dest.name AS destination_status,
                tr.id AS review_id,
                tr.name AS reviewer_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                tr.number_of_participate,
                cs_tr.name AS review_status,
                tr.created_at AS review_created_at,
                ti.image_url AS tour_image,
                ti.name AS tour_name
            FROM tour t
            LEFT JOIN tour_type tt ON t.tour_type = tt.id
            LEFT JOIN tour_category tc ON t.tour_category = tc.id
            LEFT JOIN seasons s ON t.season = s.id
            LEFT JOIN common_status cs_t ON t.status = cs_t.id
            LEFT JOIN tour_schedule ts ON t.tour_id = ts.tour_id
            LEFT JOIN common_status cs_ts ON ts.status = cs_ts.id
            LEFT JOIN tour_destination td ON t.tour_id = td.tour_id
            LEFT JOIN destination d ON td.destination_id = d.destination_id
            LEFT JOIN common_status cs_dest ON d.status = cs_dest.id
            LEFT JOIN tour_review tr ON ts.id = tr.tour_schedule_id AND tr.rating > 4.0
            LEFT JOIN common_status cs_tr ON tr.status = cs_tr.id
            LEFT JOIN tour_images ti ON t.tour_id = ti.tour_id
            WHERE tr.id IS NOT NULL
            ORDER BY tr.rating DESC, t.tour_id
            """;


}
