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


}
