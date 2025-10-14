package com.felicita.queries;

public class DestinationQueries {

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
            """;

}
