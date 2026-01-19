package com.felicita.queries;

public class DestinationQueries {

    private DestinationQueries() {
    }

    public static final String GET_ALL_DESTINATIONS = """
            SELECT
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location,
                d.latitude,
                d.longitude,
                dc.category AS category_name,
                dc.description AS category_description,
                cs.name AS status_name,
                a.id AS activity_id,
                a.name AS activity_name,
                a.description AS activity_description,
                a.activities_category,
                a.duration_hours,
                a.available_from,
                a.available_to,
                a.price_local,
                a.price_foreigners,
                a.min_participate,
                a.max_participate,
                a.season,
                di.id AS image_id,
                di.name AS image_name,
                di.description AS image_description,
                di.image_url
            FROM destination d
            LEFT JOIN destination_categories dc ON d.destination_category = dc.id
            LEFT JOIN common_status cs ON d.status = cs.id
            LEFT JOIN activities a ON d.destination_id = a.destination_id
            LEFT JOIN destination_images di ON d.destination_id = di.destination_id
            """;

    public static final String GET_PAGINATED_DESTINATION_IDS = """
                SELECT d.destination_id
                FROM destination d
                LEFT JOIN common_status cs ON d.status = cs.id
                LEFT JOIN activities a ON d.destination_id = a.destination_id
                WHERE (? IS NULL OR d.name LIKE CONCAT('%', ?, '%'))
                  AND (? IS NULL OR a.price_local >= ?)
                  AND (? IS NULL OR a.price_local <= ?)
                  AND (? IS NULL OR a.duration_hours = ?)
                  AND (? IS NULL OR a.activities_category = ?)
                  AND (? IS NULL OR a.season = ?)
                  AND (? IS NULL OR cs.name = ?)
                GROUP BY d.destination_id
                LIMIT ? OFFSET ?
            """;

    public static final String GET_DESTINATIONS_BY_IDS = """
                SELECT
                    d.destination_id,
                    d.name AS destination_name,
                    d.description AS destination_description,
                    d.location,
                    d.latitude,
                    d.longitude,
                    dc.category AS category_name,
                    dc.description AS category_description,
                    cs.name AS status_name,
                    a.id AS activity_id,
                    a.name AS activity_name,
                    a.description AS activity_description,
                    a.activities_category,
                    a.duration_hours,
                    a.available_from,
                    a.available_to,
                    a.price_local,
                    a.price_foreigners,
                    a.min_participate,
                    a.max_participate,
                    a.season,
                    di.id AS image_id,
                    di.name AS image_name,
                    di.description AS image_description,
                    di.image_url
                FROM destination d
                LEFT JOIN destination_categories dc ON d.destination_category = dc.id
                LEFT JOIN common_status cs ON d.status = cs.id
                LEFT JOIN activities a ON d.destination_id = a.destination_id
                LEFT JOIN destination_images di ON d.destination_id = di.destination_id
                WHERE d.destination_id IN (%s)
            """;

    public static final String GET_FILTERED_DESTINATION_COUNT = """
                SELECT COUNT(DISTINCT d.destination_id) 
                FROM destination d
                LEFT JOIN common_status cs ON d.status = cs.id
                LEFT JOIN activities a ON d.destination_id = a.destination_id
                WHERE (? IS NULL OR d.name LIKE CONCAT('%', ?, '%'))
                  AND (? IS NULL OR a.price_local >= ?)
                  AND (? IS NULL OR a.price_local <= ?)
                  AND (? IS NULL OR a.duration_hours = ?)
                  AND (? IS NULL OR a.activities_category = ?)
                  AND (? IS NULL OR a.season = ?)
                  AND (? IS NULL OR cs.name = ?);
            """;

    public static final String GET_ALL_DESTINATIONS_BY_TOUR_ID = """
            SELECT
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location,
                d.latitude,
                d.longitude,
                dc.category AS category_name,
                dc.description AS category_description,
                cs.name AS status_name,
            	a.id AS activity_id,
                a.name AS activity_name,
                a.description AS activity_description,
                a.activities_category,
                a.duration_hours,
                a.available_from,
                a.available_to,
                a.price_local,
                a.price_foreigners,
                a.min_participate,
                a.max_participate,
                a.season,
            	di.id AS image_id,
                di.name AS image_name,
                di.description AS image_description,
                di.image_url
            FROM tour t
            JOIN tour_destination td ON t.tour_id = td.tour_id
            JOIN destination d ON td.destination_id = d.destination_id
            LEFT JOIN destination_categories dc ON d.destination_category = dc.id
            LEFT JOIN common_status cs ON d.status = cs.id
            LEFT JOIN activities a ON d.destination_id = a.destination_id
            LEFT JOIN destination_images di ON d.destination_id = di.destination_id
            WHERE t.tour_id = ?
            """;

    public static final String GET_ALL_DESTINATIONS_CATEGORIES = """
            SELECT
                dc.id AS category_id,
                dc.category,
                dc.description AS category_description,
                cs.name AS category_status,
                dc.created_at,
                dc.updated_at,
                dci.id AS image_id,
                dci.name AS image_name,
                dci.description AS image_description,
                dci.image_url,
                cs_img.name AS image_status,
                dci.created_at AS image_created_at
            FROM destination_categories dc
            JOIN common_status cs
                ON dc.status = cs.id
            LEFT JOIN destination_categories_images dci
                ON dci.destination_categories_id = dc.id
            LEFT JOIN common_status cs_img
                ON dci.status = cs_img.id
            ORDER BY dc.id, dci.id
            """;

    public static final String GET_POPULAR_DESTINATIONS = """
            SELECT
                pd.id AS popular_id,
                pd.rating,
                pd.popularity,
                pd.created_at AS popular_created_at,
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location,
                d.latitude,
                d.longitude,
                cs_dest.name AS destination_status,
                dc.id AS category_id,
                dc.category AS category_name,
                dc.description AS category_description,
                cs_cat.name AS category_status,
                di.id AS image_id,
                di.name AS image_name,
                di.description AS image_description,
                di.image_url,
                cs_img.name AS image_status
            FROM popular_destination pd
            JOIN destination d
                ON pd.destination_id = d.destination_id
            JOIN common_status cs_dest
                ON d.status = cs_dest.id
            LEFT JOIN destination_categories dc
                ON d.destination_category = dc.id
            LEFT JOIN common_status cs_cat
                ON dc.status = cs_cat.id
            LEFT JOIN destination_images di
                ON d.destination_id = di.destination_id
            LEFT JOIN common_status cs_img
                ON di.status = cs_img.id
            ORDER BY pd.popularity DESC, pd.rating DESC;
            """;

    public static final String GET_TRENDING_DESTINATIONS = """
            SELECT
                pd.id AS popular_id,
                pd.rating,
                pd.popularity,
                pd.created_at AS popular_created_at,
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location,
                d.latitude,
                d.longitude,
                cs_dest.name AS destination_status,
                dc.id AS category_id,
                dc.category AS category_name,
                dc.description AS category_description,
                cs_cat.name AS category_status,
                di.id AS image_id,
                di.name AS image_name,
                di.description AS image_description,
                di.image_url,
                cs_img.name AS image_status,
                di.created_at AS image_created_at,
                a.id AS activity_id,
                a.name AS activity_name,
                a.description AS activity_description,
                a.activities_category,
                a.duration_hours,
                a.available_from,
                a.available_to,
                a.price_local,
                a.price_foreigners,
                a.min_participate,
                a.max_participate,
                a.season
            FROM popular_destination pd
            JOIN destination d ON pd.destination_id = d.destination_id
            JOIN common_status cs_dest ON d.status = cs_dest.id
            LEFT JOIN destination_categories dc ON d.destination_category = dc.id
            LEFT JOIN common_status cs_cat ON dc.status = cs_cat.id
            LEFT JOIN destination_images di ON d.destination_id = di.destination_id
            LEFT JOIN common_status cs_img ON di.status = cs_img.id
            LEFT JOIN activities a ON d.destination_id = a.destination_id
            LEFT JOIN common_status cs_act ON a.status = cs_act.id
            WHERE pd.rating > 4.0
            ORDER BY pd.popularity DESC, pd.rating DESC
            """;

    public static final String GET_ALL_DESTINATIONS_CATEGORY = """
                SELECT
                dc.id AS CATEGORY_ID,
                dc.name AS CATEGORY_NAME,
                dc.description AS CATEGORY_DESCRIPTION,
                cs.name AS CATEGORY_STATUS,
                dc.created_at AS CATEGORY_CREATED_AT,
                dc.created_by AS CATEGORY_CREATED_BY,
                dc.updated_at AS CATEGORY_UPDATED_AT,
                dc.updated_by AS CATEGORY_UPDATED_BY,
                dc.terminated_at AS CATEGORY_TERMINATED_AT,
                dc.terminated_by AS CATEGORY_TERMINATED_BY,
                dc.image_url AS CATEGORY_IMAGE_URL
            FROM destination_category dc
            LEFT JOIN common_status cs
                ON dc.common_status_id = cs.id
                WHERE cs.name = 'ACTIVE'
            """;

    public static final String GET_ALL_ACTIVE_DESTINATIONS = """
            SELECT
                d.id AS DESTINATION_ID,
                d.name AS DESTINATION_NAME,
                d.description AS DESTINATION_DESCRIPTION,
                cs2.name AS DESTINATION_STATUS,
                dc.name AS DESTINATION_CATEGORY,
                dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
                dc.image_url AS DESTINATION_CATEGORY_IMAGE_URL,
                cs1.name AS DESTINATION_CATEGORY_STATUS,
                d.location AS DESTINATION_LOCATION,
                d.rating AS DESTINATION_RATING,
                d.popularity AS DESTINATION_POPULARITY,
                d.created_at AS DESTINATION_CREATED_AT,
                d.created_by AS DESTINATION_CREATED_BY,
                d.updated_at AS DESTINATION_UPDATED_AT,
                d.updated_by AS DESTINATION_UPDATED_BY,
                d.terminated_at AS DESTINATION_TERMINATED_AT,
                d.terminated_by AS DESTINATION_TERMINATED_BY,
                di.id AS IMAGE_ID,
                di.name AS IMAGE_NAME,
                di.description AS IMAGE_DESCRIPTION,
                di.image_url AS IMAGE_URL,
                cs3.name AS IMAGE_STATUS
            FROM destination d
            LEFT JOIN destination_category dc
                ON d.destination_category_id = dc.id
            LEFT JOIN destination_images di
                ON d.id = di.destination_id
            LEFT JOIN common_status cs1
                ON dc.common_status_id = cs1.id
            LEFT JOIN common_status cs2
                ON d.common_status_id = cs2.id
            LEFT JOIN common_status cs3
                ON di.common_status_id = cs3.id
                WHERE cs2.name = 'ACTIVE'
            """;

    public static final String GET_ALL_TRENDING_DESTINATIONS = """
            SELECT
                d.id AS DESTINATION_ID,
                d.name AS DESTINATION_NAME,
                d.description AS DESTINATION_DESCRIPTION,
                cs2.name AS DESTINATION_STATUS,
                dc.name AS DESTINATION_CATEGORY,
                dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
                cs1.name AS DESTINATION_CATEGORY_STATUS,
                d.location AS DESTINATION_LOCATION,
                d.rating AS DESTINATION_RATING,
                d.popularity AS DESTINATION_POPULARITY,
                d.created_at AS DESTINATION_CREATED_AT,
                d.created_by AS DESTINATION_CREATED_BY,
                d.updated_at AS DESTINATION_UPDATED_AT,
                d.updated_by AS DESTINATION_UPDATED_BY,
                d.terminated_at AS DESTINATION_TERMINATED_AT,
                d.terminated_by AS DESTINATION_TERMINATED_BY,
                di.id AS IMAGE_ID,
                di.name AS IMAGE_NAME,
                di.description AS IMAGE_DESCRIPTION,
                di.image_url AS IMAGE_URL,
                cs3.name AS IMAGE_STATUS,
                td.id AS TRENDING_ID,
                td.created_at AS TRENDING_CREATED_AT,
                td.created_by AS TRENDING_CREATED_BY,
                td.updated_at AS TRENDING_UPDATED_AT,
                td.updated_by AS TRENDING_UPDATED_BY,
                td.terminated_at AS TRENDING_TERMINATED_AT,
                td.terminated_by AS TRENDING_TERMINATED_BY,
                cs4.name AS TRENDING_STATUS
            FROM trending_destinations td
            INNER JOIN destination d
                ON td.destination_id = d.id
            LEFT JOIN destination_category dc
                ON d.destination_category_id = dc.id
            LEFT JOIN destination_images di
                ON d.id = di.destination_id
            LEFT JOIN common_status cs1
                ON dc.common_status_id = cs1.id
            LEFT JOIN common_status cs2
                ON d.common_status_id = cs2.id
            LEFT JOIN common_status cs3
                ON di.common_status_id = cs3.id
            LEFT JOIN common_status cs4
                ON td.status_id = cs4.id
            """;

    public static final String GET_DESTINATIONS_BY_ID = """
                SELECT
                    d.id AS DESTINATION_ID,
                    d.name AS DESTINATION_NAME,
                    d.description AS DESTINATION_DESCRIPTION,
                    cs2.name AS DESTINATION_STATUS,
                    dc.name AS DESTINATION_CATEGORY,
                    dc.description AS DESTINATION_CATEGORY_DESCRIPTION,
                    dc.image_url AS DESTINATION_CATEGORY_IMAGE_URL,
                    cs1.name AS DESTINATION_CATEGORY_STATUS,
                    d.location AS DESTINATION_LOCATION,
                    d.rating AS DESTINATION_RATING,
                    d.popularity AS DESTINATION_POPULARITY,
                    d.created_at AS DESTINATION_CREATED_AT,
                    d.created_by AS DESTINATION_CREATED_BY,
                    d.updated_at AS DESTINATION_UPDATED_AT,
                    d.updated_by AS DESTINATION_UPDATED_BY,
                    d.terminated_at AS DESTINATION_TERMINATED_AT,
                    d.terminated_by AS DESTINATION_TERMINATED_BY,
                    di.id AS IMAGE_ID,
                    di.name AS IMAGE_NAME,
                    di.description AS IMAGE_DESCRIPTION,
                    di.image_url AS IMAGE_URL,
                    cs3.name AS IMAGE_STATUS
                FROM destination d
                LEFT JOIN destination_category dc
                    ON d.destination_category_id = dc.id
                LEFT JOIN destination_images di
                    ON d.id = di.destination_id
                LEFT JOIN common_status cs1
                    ON dc.common_status_id = cs1.id
                LEFT JOIN common_status cs2
                    ON d.common_status_id = cs2.id
                LEFT JOIN common_status cs3
                    ON di.common_status_id = cs3.id
                WHERE d.id IN (:ids)
            """;

    public static final String GET_ALL_DESTINATIONS_LOCATIONS = """
            SELECT
            	d.id AS DESTINATION_ID,
                d.name AS DESTINATION_NAME,
                d.description AS DESTINATION_DESCRIPTION,
                dc.name AS DESTINATION_CATEGORY,
                d.latitude AS DESTINATION_LATITUDE,
                d.longitude AS DESTINATION_LONGITUDE
            FROM destination d
            LEFT JOIN common_status cs
            ON d.common_status_id = cs.id
            LEFT JOIN destination_category dc
            ON d.destination_category_id = dc.id
            WHERE cs.name = 'ACTIVE'
            """;


    public static final String GET_DESTINATIONS_FOR_TOUR_MAP = """
            SELECT
                d.destination_id AS destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                cs1.name AS destination_status,
                dc.category AS destination_category,
                cs2.name AS destination_category_status,
                d.location AS destination_location,
                d.latitude AS destination_latitude,
                d.longitude AS destination_longitude,
                d.created_at AS destination_created_at,
                d.created_by AS destination_created_by,
                di.id AS destination_image_id,
                di.name AS destination_image_name,
                di.description AS destination_image_description,
                di.image_url AS destination_image_url,
                cs3.name AS destination_image_status,
                dci.id AS destination_category_image_id,
                dci.name AS destination_category_image_name,
                dci.description AS destination_category_image_description,
                dci.image_url AS destination_category_image_url,
                cs4.name AS destination_category_image_status
            FROM destination d
            LEFT JOIN common_status cs1
                ON cs1.id = d.status
            LEFT JOIN destination_categories dc
                ON dc.id = d.destination_category
            LEFT JOIN common_status cs2
                ON cs2.id = dc.status
            LEFT JOIN destination_images di
                ON di.destination_id = d.destination_id
            LEFT JOIN common_status cs3
                ON cs3.id = di.status
            LEFT JOIN destination_categories_images dci
                ON dci.destination_categories_id = dc.id
            LEFT JOIN common_status cs4
                ON cs4.id = dci.status
            WHERE cs1.name = 'ACTIVE'
            """;

    public static final String GET_DESTINATIONS_REVIEW_DETAILS = """
            SELECT
                dr.review_id,
                dr.destination_id,
                d.name AS destination_name,
                dr.user_id AS review_user_id,
                u1.username AS review_user_name,
                dr.review_text AS review_text,
                dr.rating AS review_rating,
                cs_dr.name AS review_status,
                dr.created_by AS review_created_by,
                dr.created_at AS review_created_at,
                dr.updated_by AS review_updated_by,
                dr.updated_at AS review_updated_at,
                dri.image_id,
                dri.name AS image_name,
                dri.description AS image_description,
                dri.image_url AS image_url,
                cs_dri.name AS image_status,
                dri.created_by AS image_created_by,
                dri.created_at AS image_created_at,
                drr.review_reaction_id,
                drr.review_id AS reaction_review_id,
                drr.user_id AS reaction_user_id,
                u2.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_drr.name AS review_reaction_status,
                drr.created_at AS reaction_created_at,
                drc.comment_id,
                drc.review_id AS comment_review_id,
                drc.user_id AS comment_user_id,
                u3.username AS comment_user_name,
                drc.parent_comment_id,
                drc.comment_text AS comment_text,
                cs_drc.name AS comment_status,
                drc.created_at AS comment_created_at,
                drc.created_by AS comment_created_by,
                drcr.comment_reaction_id,
                drcr.comment_id AS comment_reaction_comment_id,
                drcr.user_id AS comment_reaction_user_id,
                u4.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_drcr.name AS comment_reaction_status,
                drcr.created_by AS comment_reaction_created_by,
                drcr.created_at AS comment_reaction_created_at
            FROM destination_review dr
            LEFT JOIN destination d
                ON dr.destination_id = d.destination_id
            LEFT JOIN common_status cs_dr
                ON cs_dr.id = dr.status
            LEFT JOIN destination_review_images dri
                ON dri.review_id = dr.review_id
            LEFT JOIN common_status cs_dri
                ON cs_dri.id = dri.status
            LEFT JOIN destination_review_reaction drr
                ON drr.review_id = dr.review_id
            LEFT JOIN reaction_type rt
                ON rt.id = drr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = dr.user_id
            LEFT JOIN user u2
                ON u2.user_id = drr.user_id
            LEFT JOIN common_status cs_drr
                ON cs_drr.id = drr.status
            LEFT JOIN destination_review_comment drc
                ON drc.review_id = dr.review_id
            LEFT JOIN user u3
                ON u3.user_id = drc.user_id
            LEFT JOIN common_status cs_drc
                ON cs_drc.id = drc.status
            LEFT JOIN destination_review_comment_reaction drcr
                ON drcr.comment_id = drc.comment_id
            LEFT JOIN reaction_type rt2
                ON rt2.id = drcr.reaction_type_id
            LEFT JOIN user u4
                ON u4.user_id = drcr.user_id
            LEFT JOIN common_status cs_drcr
                ON cs_drcr.id = drcr.status
            ORDER BY dr.review_id, drc.comment_id, drcr.comment_reaction_id
            """;

    public static final String GET_DESTINATIONS_REVIEW_DETAILS_BY_ID = """
            SELECT
                dr.review_id,
                dr.destination_id,
                d.name AS destination_name,
                dr.user_id AS review_user_id,
                u1.username AS review_user_name,
                dr.review_text AS review_text,
                dr.rating AS review_rating,
                cs_dr.name AS review_status,
                dr.created_by AS review_created_by,
                dr.created_at AS review_created_at,
                dr.updated_by AS review_updated_by,
                dr.updated_at AS review_updated_at,
                dri.image_id,
                dri.name AS image_name,
                dri.description AS image_description,
                dri.image_url AS image_url,
                cs_dri.name AS image_status,
                dri.created_by AS image_created_by,
                dri.created_at AS image_created_at,
                drr.review_reaction_id,
                drr.review_id AS reaction_review_id,
                drr.user_id AS reaction_user_id,
                u2.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_drr.name AS review_reaction_status,
                drr.created_at AS reaction_created_at,
                drc.comment_id,
                drc.review_id AS comment_review_id,
                drc.user_id AS comment_user_id,
                u3.username AS comment_user_name,
                drc.parent_comment_id,
                drc.comment_text AS comment_text,
                cs_drc.name AS comment_status,
                drc.created_at AS comment_created_at,
                drc.created_by AS comment_created_by,
                drcr.comment_reaction_id,
                drcr.comment_id AS comment_reaction_comment_id,
                drcr.user_id AS comment_reaction_user_id,
                u4.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_drcr.name AS comment_reaction_status,
                drcr.created_by AS comment_reaction_created_by,
                drcr.created_at AS comment_reaction_created_at
            FROM destination_review dr
            LEFT JOIN destination d
                ON dr.destination_id = d.destination_id
            LEFT JOIN common_status cs_dr
                ON cs_dr.id = dr.status
            LEFT JOIN destination_review_images dri
                ON dri.review_id = dr.review_id
            LEFT JOIN common_status cs_dri
                ON cs_dri.id = dri.status
            LEFT JOIN destination_review_reaction drr
                ON drr.review_id = dr.review_id
            LEFT JOIN reaction_type rt
                ON rt.id = drr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = dr.user_id
            LEFT JOIN user u2
                ON u2.user_id = drr.user_id
            LEFT JOIN common_status cs_drr
                ON cs_drr.id = drr.status
            LEFT JOIN destination_review_comment drc
                ON drc.review_id = dr.review_id
            LEFT JOIN user u3
                ON u3.user_id = drc.user_id
            LEFT JOIN common_status cs_drc
                ON cs_drc.id = drc.status
            LEFT JOIN destination_review_comment_reaction drcr
                ON drcr.comment_id = drc.comment_id
            LEFT JOIN reaction_type rt2
                ON rt2.id = drcr.reaction_type_id
            LEFT JOIN user u4
                ON u4.user_id = drcr.user_id
            LEFT JOIN common_status cs_drcr
                ON cs_drcr.id = drcr.status
            WHERE dr.destination_id = ?
            ORDER BY dr.review_id, drc.comment_id, drcr.comment_reaction_id
            """;

    public static final String GET_DESTINATION_DETAILS_BY_ID = """
            SELECT
            	d.destination_id,
            	d.name AS destination_name,
            	d.description AS destination_description,
            	d.location,
            	d.latitude,
            	d.longitude,
            	dc.category AS category_name,
            	dc.description AS category_description,
            	cs.name AS status_name,
            	a.id AS activity_id,
            	a.name AS activity_name,
            	a.description AS activity_description,
            	a.activities_category,
            	a.duration_hours,
            	a.available_from,
            	a.available_to,
            	a.price_local,
            	a.price_foreigners,
            	a.min_participate,
            	a.max_participate,
            	a.season,
            	di.id AS image_id,
            	di.name AS image_name,
            	di.description AS image_description,
            	di.image_url
            FROM destination d
            LEFT JOIN destination_categories dc ON d.destination_category = dc.id
            LEFT JOIN common_status cs ON d.status = cs.id
            LEFT JOIN activities a ON d.destination_id = a.destination_id
            LEFT JOIN destination_images di
               ON d.destination_id = di.destination_id
              AND di.status = (
                  SELECT id FROM common_status WHERE name = 'ACTIVE' LIMIT 1
              )
            WHERE d.destination_id=?
            """;

    public static final String GET_DESTINATION_REVIEW_DETAILS = """
            SELECT
                dh.id AS history_id,
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location AS destination_location,
                d.latitude,
                d.longitude,
                dh.title AS history_title,
                dh.description AS history_description,
                dh.event_date,
                dh.status AS history_status_id,
                cs_history.name AS history_status_name,
                u_created.username AS history_created_by_username,
                u_updated.username AS history_updated_by_username,
                u_terminated.username AS history_terminated_by_username,
                dh.created_at AS history_created_at,
                dh.updated_at AS history_updated_at,
                dh.terminated_at AS history_terminated_at,
                dhi.id AS image_id,
                dhi.name AS image_name,
                dhi.description AS image_description,
                dhi.image_url,
                dhi.status AS image_status_id,
                cs_image.name AS image_status_name,
                ui_created.username AS image_created_by_username,
                ui_updated.username AS image_updated_by_username,
                ui_terminated.username AS image_terminated_by_username,
                dhi.created_at AS image_created_at,
                dhi.updated_at AS image_updated_at,
                dhi.terminated_at AS image_terminated_at
            FROM destination_history dh
            JOIN destination d ON dh.destination_id = d.destination_id
            LEFT JOIN destination_history_images dhi ON dhi.destination_history_id = dh.id
            LEFT JOIN common_status cs_history ON dh.status = cs_history.id
            LEFT JOIN common_status cs_image ON dhi.status = cs_image.id
            LEFT JOIN user u_created ON dh.created_by = u_created.user_id
            LEFT JOIN user u_updated ON dh.updated_by = u_updated.user_id
            LEFT JOIN user u_terminated ON dh.terminated_by = u_terminated.user_id
            LEFT JOIN user ui_created ON dhi.created_by = ui_created.user_id
            LEFT JOIN user ui_updated ON dhi.updated_by = ui_updated.user_id
            LEFT JOIN user ui_terminated ON dhi.terminated_by = ui_terminated.user_id
            ORDER BY d.destination_id, dh.event_date, dhi.id
            """;

    public static final String GET_DESTINATION_REVIEW_DETAILS_BY_ID = """
            SELECT
                dh.id AS history_id,
                d.destination_id,
                d.name AS destination_name,
                d.description AS destination_description,
                d.location AS destination_location,
                d.latitude,
                d.longitude,
                dh.title AS history_title,
                dh.description AS history_description,
                dh.event_date,
                dh.status AS history_status_id,
                cs_history.name AS history_status_name,
                u_created.username AS history_created_by_username,
                u_updated.username AS history_updated_by_username,
                u_terminated.username AS history_terminated_by_username,
                dh.created_at AS history_created_at,
                dh.updated_at AS history_updated_at,
                dh.terminated_at AS history_terminated_at,
                dhi.id AS image_id,
                dhi.name AS image_name,
                dhi.description AS image_description,
                dhi.image_url,
                dhi.status AS image_status_id,
                cs_image.name AS image_status_name,
                ui_created.username AS image_created_by_username,
                ui_updated.username AS image_updated_by_username,
                ui_terminated.username AS image_terminated_by_username,
                dhi.created_at AS image_created_at,
                dhi.updated_at AS image_updated_at,
                dhi.terminated_at AS image_terminated_at
            FROM destination_history dh
            JOIN destination d ON dh.destination_id = d.destination_id
            LEFT JOIN destination_history_images dhi ON dhi.destination_history_id = dh.id
            LEFT JOIN common_status cs_history ON dh.status = cs_history.id
            LEFT JOIN common_status cs_image ON dhi.status = cs_image.id
            LEFT JOIN user u_created ON dh.created_by = u_created.user_id
            LEFT JOIN user u_updated ON dh.updated_by = u_updated.user_id
            LEFT JOIN user u_terminated ON dh.terminated_by = u_terminated.user_id
            LEFT JOIN user ui_created ON dhi.created_by = ui_created.user_id
            LEFT JOIN user ui_updated ON dhi.updated_by = ui_updated.user_id
            LEFT JOIN user ui_terminated ON dhi.terminated_by = ui_terminated.user_id
            WHERE d.destination_id = ?
            ORDER BY d.destination_id, dh.event_date, dhi.id
            """;

    public static final String GET_DESTINATION_HISTORY_IMAGES = """
            SELECT
                dhi.id AS image_id,
                dhi.name AS image_name,
                dhi.description AS image_description,
                dhi.image_url,
                cs_image.name AS image_status_name,
                dhi.created_at AS image_created_at,
                dhi.updated_at AS image_updated_at,
                dhi.terminated_at AS image_terminated_at,
                ui_created.username AS image_created_by_username,
                ui_updated.username AS image_updated_by_username,
                ui_terminated.username AS image_terminated_by_username,
                dh.id AS history_id,
                dh.title AS history_title,
                dh.description AS history_description,
                dh.event_date AS history_event_date,
                cs_history.name AS history_status_name,
                d.destination_id,
                d.name AS destination_name,
                d.location AS destination_location,
                d.latitude,
                d.longitude
            FROM destination_history_images dhi
            JOIN destination_history dh ON dhi.destination_history_id = dh.id
            JOIN destination d ON dh.destination_id = d.destination_id
            LEFT JOIN common_status cs_image ON dhi.status = cs_image.id
            LEFT JOIN common_status cs_history ON dh.status = cs_history.id
            LEFT JOIN user ui_created ON dhi.created_by = ui_created.user_id
            LEFT JOIN user ui_updated ON dhi.updated_by = ui_updated.user_id
            LEFT JOIN user ui_terminated ON dhi.terminated_by = ui_terminated.user_id
            ORDER BY dh.id, dhi.id
            """;

    public static final String GET_DESTINATION_HISTORY_IMAGES_BY_ID = """
            SELECT
                dhi.id AS image_id,
                dhi.name AS image_name,
                dhi.description AS image_description,
                dhi.image_url,
                cs_image.name AS image_status_name,
                dhi.created_at AS image_created_at,
                dhi.updated_at AS image_updated_at,
                dhi.terminated_at AS image_terminated_at,
                ui_created.username AS image_created_by_username,
                ui_updated.username AS image_updated_by_username,
                ui_terminated.username AS image_terminated_by_username,
                dh.id AS history_id,
                dh.title AS history_title,
                dh.description AS history_description,
                dh.event_date AS history_event_date,
                cs_history.name AS history_status_name,
                d.destination_id,
                d.name AS destination_name,
                d.location AS destination_location,
                d.latitude,
                d.longitude
            FROM destination_history_images dhi
            JOIN destination_history dh ON dhi.destination_history_id = dh.id
            JOIN destination d ON dh.destination_id = d.destination_id
            LEFT JOIN common_status cs_image ON dhi.status = cs_image.id
            LEFT JOIN common_status cs_history ON dh.status = cs_history.id
            LEFT JOIN user ui_created ON dhi.created_by = ui_created.user_id
            LEFT JOIN user ui_updated ON dhi.updated_by = ui_updated.user_id
            LEFT JOIN user ui_terminated ON dhi.terminated_by = ui_terminated.user_id
            WHERE d.destination_id = ?
            ORDER BY dh.id, dhi.id
            """;

    public static final String INSERT_DESTINATION_REQUEST = """
            INSERT INTO destination
            (
                name,
                description,
                status,
                destination_category,
                location,
                latitude,
                longitude,
                created_by,
                extra_price,
                extra_price_note
            )
            VALUES
            (
                ?, 
                ?, 
                (SELECT cs.id FROM common_status cs WHERE cs.name = ? LIMIT 1),
                (SELECT dc.id FROM destination_categories dc WHERE dc.category = ? LIMIT 1),
                ?, 
                ?, 
                ?, 
                ?, 
                ?, 
                ?
            )
            """;

    public static final String INSERT_DESTINATION_IMAGES_REQUEST = """
            INSERT INTO destination_images
            (destination_id, name, description, image_url, status, created_by)
            VALUES (?, ?, ?, ?, (SELECT cs.id FROM common_status cs WHERE cs.name = ? LIMIT 1), ?)
            """;

    public static final String DESTINATION_TERMINATE = """
            UPDATE destination
            SET status = (SELECT id FROM common_status WHERE name = ? LIMIT 1),terminated_at = now(), terminated_by = ?
            WHERE destination_id = ?
            """;

    public static final String GET_ACTIVE_DESTINATIONS_FOR_TERMINATE = """
            SELECT
            	d.destination_id,
                d.name
            FROM destination d
            LEFT JOIN common_status cs
            	ON cs.id = d.status
            WHERE cs.name = ?
            """;

    public static final String UPDATE_BASIC_DESTINATION_DETAILS = """
            UPDATE destination
            SET name = ? ,
            	description = ?,
                status = (SELECT id FROM common_status WHERE name = ? LIMIT 1),
                destination_category = (SELECT id FROM destination_categories WHERE category = ? LIMIT 1),
                location = ?,
                latitude = ?,
                longitude = ?,
                updated_by = ?,
                updated_at = now(),
                extra_price = ?,
                extra_price_note =?
            WHERE destination_id = ?
            """;

    public static final String DESTINATION_IMAGES_REMOVE = """
            UPDATE destination_images
            SET status = (SELECT id FROM common_status WHERE name = ? LIMIT 1),
                terminated_at = now(),
                terminated_by = ?
            WHERE id = ?
            """;

    public static final String DESTINATION_ACTIVITIES_REMOVE = """
            UPDATE activities
            SET status = ( SELECT id FROM common_status WHERE name = ? LIMIT 1),
            	terminated_at = now(),
                terminated_by = ?
            WHERE id = ?
            """;

    public static final String INSERT_DESTINATION_ACTIVITY = """
            INSERT INTO activities
            (
                destination_id,
                name,
                description,
                activities_category,
                duration_hours,
                available_from,
                available_to,
                price_local,
                price_foreigners,
                min_participate,
                max_participate,
                season,
                status,
                created_by
            )
            VALUES
            (
                ?, 
                ?, 
                ?, 
                ?, 
                ?, 
                ?, 
                ?,?,?,?,?,?,
                (SELECT cs.id FROM common_status cs WHERE cs.name = ? LIMIT 1),
                ?
            )
            """;

    public static final String INSERT_DESTINATION_ACTIVITY_IMAGE = """
            INSERT INTO activities_images
            (activity_id, name, description, image_url, status, created_by)
            VALUES (?, ?, ?, ?, (SELECT cs.id FROM common_status cs WHERE cs.name = ? LIMIT 1), ?)
            """;

}
