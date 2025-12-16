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
            LEFT JOIN tour_images img ON t.tour_id = img.tour_id
            """;

    public static final String GET_PAGINATED_TOUR_IDS = """
    SELECT t.tour_id
    FROM tour t
    LEFT JOIN common_status cs ON t.status = cs.id
    LEFT JOIN tour_type tt ON t.tour_type = tt.id
    LEFT JOIN tour_category tc ON t.tour_category = tc.id
    LEFT JOIN seasons s ON t.season = s.id
    WHERE cs.name = 'ACTIVE'
      AND (? IS NULL OR t.name LIKE CONCAT('%', ?, '%'))
      AND (? IS NULL OR t.duration = ?)
      AND (? IS NULL OR (t.start_location LIKE CONCAT('%', ?, '%') 
           OR t.end_location LIKE CONCAT('%', ?, '%')))
      AND (? IS NULL OR tc.name = ?)
      AND (? IS NULL OR s.name = ?)
      AND (? IS NULL OR tt.name = ?)
    LIMIT ? OFFSET ?;
""";


    public static final String GET_TOURS_BY_IDS = """
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
    LEFT JOIN tour_images img ON t.tour_id = img.tour_id
    WHERE t.tour_id IN (%s)  -- will replace with comma-separated IDs
""";



    public static final String GET_TOUR_DETAILS_BY_ID = """
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
                    LEFT JOIN tour_images img ON t.tour_id = img.tour_id
            WHERE t.tour_id=?
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


    public static final String GET_TOUR_REVIEWS_DETAILS = """
            SELECT
                tr.id AS review_id,
                ts.id AS tour_schedule_id,
                t.tour_id AS tour_id,
                t.name AS tour_name,
                tr.name AS review_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                cs_tr.name AS review_status,
                tr.number_of_participate,
                tr.created_by AS review_created_by,
                u_review.username AS review_created_user,
                tr.created_at AS review_created_at,
                tr.updated_by AS review_updated_by,
                tr.updated_at AS review_updated_at,
                tri.id AS image_id,
                tri.name AS image_name,
                tri.description AS image_description,
                tri.image_url AS image_url,
                cs_tri.name AS image_status,
                tri.created_by AS image_created_by,
                tri.created_at AS image_created_at,
                trr.id AS review_reaction_id,
                trr.tour_review_id AS reaction_review_id,
                trr.user_id AS reaction_user_id,
                u_reaction.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_trr.name AS review_reaction_status,
                trr.created_at AS reaction_created_at,
                trc.id AS comment_id,
                trc.tour_review_id AS comment_review_id,
                trc.user_id AS comment_user_id,
                u_comment.username AS comment_user_name,
                trc.parent_comment_id,
                trc.comment,
                cs_trc.name AS comment_status,
                trc.created_at AS comment_created_at,
                trc.created_by AS comment_created_by,
                trcr.id AS comment_reaction_id,
                trcr.comment_id AS comment_reaction_comment_id,
                trcr.user_id AS comment_reaction_user_id,
                u_comment_react.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_trcr.name AS comment_reaction_status,
                trcr.created_by AS comment_reaction_created_by,
                trcr.created_at AS comment_reaction_created_at
            FROM tour_review tr
            LEFT JOIN tour_schedule ts
                ON tr.tour_schedule_id = ts.id
            LEFT JOIN tour t
                ON ts.tour_id = t.tour_id
            LEFT JOIN common_status cs_tr
                ON cs_tr.id = tr.status
            LEFT JOIN tour_review_images tri
                ON tri.tour_review_id = tr.id
            LEFT JOIN common_status cs_tri
                ON cs_tri.id = tri.status
            LEFT JOIN tour_review_reaction trr
                ON trr.tour_review_id = tr.id
            LEFT JOIN reaction_type rt
                ON rt.id = trr.reaction_type_id
            LEFT JOIN user u_reaction
                ON u_reaction.user_id = trr.user_id
            LEFT JOIN common_status cs_trr
                ON cs_trr.id = trr.status
            LEFT JOIN tour_review_comment trc
                ON trc.tour_review_id = tr.id
            LEFT JOIN user u_comment
                ON u_comment.user_id = trc.user_id
            LEFT JOIN common_status cs_trc
                ON cs_trc.id = trc.status
            LEFT JOIN tour_review_comment_reaction trcr
                ON trcr.comment_id = trc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = trcr.reaction_type_id
            LEFT JOIN user u_comment_react
                ON u_comment_react.user_id = trcr.user_id
            LEFT JOIN common_status cs_trcr
                ON cs_trcr.id = trcr.status
            LEFT JOIN user u_review
                ON u_review.user_id = tr.created_by
            ORDER BY
                tr.id,
                trc.id,
                trcr.id
            """;

    public static final String GET_TOUR_REVIEW_DETAILS_BY_ID = """
            SELECT
                tr.id AS review_id,
                ts.id AS tour_schedule_id,
                t.tour_id AS tour_id,
                t.name AS tour_name,
                tr.name AS review_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                cs_tr.name AS review_status,
                tr.number_of_participate,
                tr.created_by AS review_created_by,
                u_review.username AS review_created_user,
                tr.created_at AS review_created_at,
                tr.updated_by AS review_updated_by,
                tr.updated_at AS review_updated_at,
                tri.id AS image_id,
                tri.name AS image_name,
                tri.description AS image_description,
                tri.image_url AS image_url,
                cs_tri.name AS image_status,
                tri.created_by AS image_created_by,
                tri.created_at AS image_created_at,
                trr.id AS review_reaction_id,
                trr.tour_review_id AS reaction_review_id,
                trr.user_id AS reaction_user_id,
                u_reaction.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_trr.name AS review_reaction_status,
                trr.created_at AS reaction_created_at,
                trc.id AS comment_id,
                trc.tour_review_id AS comment_review_id,
                trc.user_id AS comment_user_id,
                u_comment.username AS comment_user_name,
                trc.parent_comment_id,
                trc.comment,
                cs_trc.name AS comment_status,
                trc.created_at AS comment_created_at,
                trc.created_by AS comment_created_by,
                trcr.id AS comment_reaction_id,
                trcr.comment_id AS comment_reaction_comment_id,
                trcr.user_id AS comment_reaction_user_id,
                u_comment_react.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_trcr.name AS comment_reaction_status,
                trcr.created_by AS comment_reaction_created_by,
                trcr.created_at AS comment_reaction_created_at
            FROM tour_review tr
            LEFT JOIN tour_schedule ts
                ON tr.tour_schedule_id = ts.id
            LEFT JOIN tour t
                ON ts.tour_id = t.tour_id
            LEFT JOIN common_status cs_tr
                ON cs_tr.id = tr.status
            LEFT JOIN tour_review_images tri
                ON tri.tour_review_id = tr.id
            LEFT JOIN common_status cs_tri
                ON cs_tri.id = tri.status
            LEFT JOIN tour_review_reaction trr
                ON trr.tour_review_id = tr.id
            LEFT JOIN reaction_type rt
                ON rt.id = trr.reaction_type_id
            LEFT JOIN user u_reaction
                ON u_reaction.user_id = trr.user_id
            LEFT JOIN common_status cs_trr
                ON cs_trr.id = trr.status
            LEFT JOIN tour_review_comment trc
                ON trc.tour_review_id = tr.id
            LEFT JOIN user u_comment
                ON u_comment.user_id = trc.user_id
            LEFT JOIN common_status cs_trc
                ON cs_trc.id = trc.status
            LEFT JOIN tour_review_comment_reaction trcr
                ON trcr.comment_id = trc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = trcr.reaction_type_id
            LEFT JOIN user u_comment_react
                ON u_comment_react.user_id = trcr.user_id
            LEFT JOIN common_status cs_trcr
                ON cs_trcr.id = trcr.status
            LEFT JOIN user u_review
                ON u_review.user_id = tr.created_by
            WHERE t.tour_id = ?
            ORDER BY
                tr.id,
                trc.id,
                trcr.id
            """;


    public static final String GET_TOUR_DESTINATIONS_FOR_MAP = """
            SELECT
                d.destination_id AS id,
                d.name,
                d.description,
                d.latitude AS lat,
                d.longitude AS lng,
                di.id AS image_id,
                di.image_url AS image_url,
                di.name AS image_name,
                di.description AS image_description
            FROM
                tour_destination td
            JOIN
                destination d ON td.destination_id = d.destination_id
            LEFT JOIN
                destination_images di ON d.destination_id = di.destination_id
            WHERE
                td.tour_id = ?
            ORDER BY
                d.destination_id;
            """;


    public static final String GET_ALL_TOUR_HISTORY_DETAILS = """
            SELECT
                th.id AS history_id,
                th.name AS history_name,
                th.description AS history_description,
                th.number_of_participate,
                th.rating,
                th.duration AS history_duration,
                th.start_date,
                th.end_date,
                th.vehicle_number,
                th.driver_id,
                th.guide_id,
                th.color AS history_color,
                th.hover_color,
                th.status AS history_status,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                ts.duration_start,
                ts.duration_end,
                ts.special_note,
                ts.description AS schedule_description,
                ts.status AS schedule_status,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.latitude,
                t.longitude,
                t.start_location,
                t.end_location,
                t.status AS tour_status,
                t.tour_type,
                t.tour_category,
                t.season,
                thimg.id AS image_id,
                thimg.name AS image_name,
                thimg.description AS image_description,
                thimg.image_url,
                thimg.color AS image_color,
                thimg.status AS image_status
            FROM tour_history th
            INNER JOIN tour_schedule ts ON th.tour_schedule_id = ts.id
            INNER JOIN tour t ON ts.tour_id = t.tour_id
            LEFT JOIN tour_history_images thimg ON ts.id = thimg.tour_schedule_id
            ORDER BY th.id, thimg.id;
            """;

    public static final String GET_TOUR_HISTORY_DETAILS_BY_ID = """
            SELECT
                th.id AS history_id,
                th.name AS history_name,
                th.description AS history_description,
                th.number_of_participate,
                th.rating,
                th.duration AS history_duration,
                th.start_date,
                th.end_date,
                th.vehicle_number,
                th.driver_id,
                th.guide_id,
                th.color AS history_color,
                th.hover_color,
                th.status AS history_status,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                ts.duration_start,
                ts.duration_end,
                ts.special_note,
                ts.description AS schedule_description,
                ts.status AS schedule_status,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.duration AS tour_duration,
                t.latitude,
                t.longitude,
                t.start_location,
                t.end_location,
                t.status AS tour_status,
                t.tour_type,
                t.tour_category,
                t.season,
                thimg.id AS image_id,
                thimg.name AS image_name,
                thimg.description AS image_description,
                thimg.image_url,
                thimg.color AS image_color,
                thimg.status AS image_status
            FROM tour_history th
            INNER JOIN tour_schedule ts ON th.tour_schedule_id = ts.id
            INNER JOIN tour t ON ts.tour_id = t.tour_id
            LEFT JOIN tour_history_images thimg ON ts.id = thimg.tour_schedule_id
            WHERE t.tour_id =?
            ORDER BY th.id, thimg.id
            """;

    public static final String GET_ALL_TOUR_HISTORY_IMAGES = """
            SELECT
                thi.id AS image_id,
                thi.name,
                thi.description,
                thi.image_url,
                thi.color,
                cs.name AS status,
                thi.created_at,
                thi.created_by,
                thi.updated_at,
                thi.updated_by,
                thi.terminated_at,
                thi.terminated_by
            FROM tour_history_images thi
            LEFT JOIN common_status cs ON thi.status = cs.id
            ORDER BY thi.created_at DESC
            """;

    public static final String GET_ALL_TOUR_HISTORY_IMAGES_BY_ID = """
            SELECT
                thi.id AS image_id,
                thi.name,
                thi.description,
                thi.image_url,
                thi.color,
                cs.name AS status,
                thi.created_at,
                thi.created_by,
                thi.updated_at,
                thi.updated_by,
                thi.terminated_at,
                thi.terminated_by
            FROM tour_history_images thi
            INNER JOIN tour_schedule ts ON thi.tour_schedule_id = ts.id
            INNER JOIN tour t ON ts.tour_id = t.tour_id
            LEFT JOIN common_status cs ON thi.status = cs.id
            WHERE t.tour_id = ?
            ORDER BY thi.created_at DESC
            """;

}
