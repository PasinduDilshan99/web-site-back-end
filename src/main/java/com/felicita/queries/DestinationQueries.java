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

    public static final String GET_ALL_ACTIVE_DESTINATIONS_CATEGORY = """
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
            """;


    public static final String GET_ALL_DESTINATIONS_BY_CATEGORY_ID = """
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
            WHERE dc.id = ?
            """;

    public static final String GET_DESTINATION_BY_ID = """
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
            WHERE d.id = ?
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
            """ ;
    public static final String GET_ALL_DESTINATIONS_LOCATIONS_CATEGORIES = """
            SELECT
            	dc.name AS ID,
                dc.description NAME,
                dc.color AS COLOR
            FROM destination_category dc
            LEFT JOIN common_status cs
            ON dc.common_status_id = cs.id
            WHERE cs.name= 'active'
            """;
}
