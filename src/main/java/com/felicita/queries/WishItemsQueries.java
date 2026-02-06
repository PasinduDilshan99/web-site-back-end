package com.felicita.queries;

public class WishItemsQueries {

    public static final String GET_ALL_WISHLIST_ITEMS = """
            WITH user_param AS (
                SELECT ? AS user_id
            )
            SELECT 'package' AS wishlist_type, pw.package_id AS item_id, pw.status_id
            FROM package_wishlist pw
            JOIN user_param u ON pw.user_id = u.user_id
            UNION ALL
            SELECT 'tour' AS wishlist_type, tw.tour_id AS item_id, tw.status_id
            FROM tour_wishlist tw
            JOIN user_param u ON tw.user_id = u.user_id
            UNION ALL
            SELECT 'destination' AS wishlist_type, dw.destination_id AS item_id, dw.status_id
            FROM destination_wishlist dw
            JOIN user_param u ON dw.user_id = u.user_id
            UNION ALL
            SELECT 'activity' AS wishlist_type, aw.activity_id AS item_id, aw.status_id
            FROM activity_wishlist aw
            JOIN user_param u ON aw.user_id = u.user_id
            """;

    public static final String GET_PACKAGE_WISH_LIST_DETAILS = """
                SELECT
                    p.package_id,
                    p.name AS package_name,
                    p.description AS package_description,
                    p.start_date AS package_start_date,
                    p.end_date AS package_end_date,
                    pi.image_url AS package_image,
                    p.total_price AS package_price,
                    p.color AS package_color,
                    CONCAT('/packages/', p.package_id) AS package_url,
                    t.name AS tour_name,
                    p.discount_percentage AS discount,
                    cs.name AS status,
                    pw.created_at
                FROM packages p
                LEFT JOIN package_images pi
                    ON p.package_id = pi.package_id AND pi.status = 1
                LEFT JOIN tour t
                    ON p.tour_id = t.tour_id
                LEFT JOIN package_wishlist pw
                    ON pw.package_id = p.package_id
                LEFT JOIN common_status cs
                    ON pw.status_id = cs.id
                WHERE p.package_id IN (:ids)
                ORDER BY p.package_id, pi.id
            """;


    public static final String GET_TOUR_WISH_LIST_DETAILS = """
                SELECT
                    t.tour_id,
                    t.name AS tour_name,
                    t.description AS tour_description,
                    t.start_location AS tour_start_location,
                    t.end_location AS tour_end_location,
                    ti.image_url AS tour_image,
                    s.name AS season,
                    CONCAT('/tours/', t.tour_id) AS tour_url,
                    cs.name AS status,
                    tw.created_at
                FROM tour t
                LEFT JOIN tour_images ti
                    ON t.tour_id = ti.tour_id AND ti.status = 1
                LEFT JOIN seasons s
                    ON t.season = s.id
                LEFT JOIN tour_wishlist tw
                    ON tw.tour_id = t.tour_id
                LEFT JOIN common_status cs
                    ON tw.status_id = cs.id
                WHERE t.tour_id IN (:ids)
                ORDER BY t.tour_id, ti.id
            """;


    public static final String GET_DESTINATION_WISH_LIST_DETAILS = """
                SELECT
                    d.destination_id,
                    d.name AS destination_name,
                    d.description AS destination_description,
                    d.location AS destination_location,
                    dc.category AS destination_category,
                    di.image_url AS destination_image,
                    CONCAT('/destinations/', d.destination_id) AS destination_url,
                    cs.name AS status,
                    dw.created_at
                FROM destination d
                LEFT JOIN destination_images di
                    ON d.destination_id = di.destination_id AND di.status = 1
                LEFT JOIN destination_categories dc
                    ON d.destination_category = dc.id
                LEFT JOIN destination_wishlist dw
                    ON dw.destination_id = d.destination_id
                LEFT JOIN common_status cs
                    ON dw.status_id = cs.id
                WHERE d.destination_id IN (:ids)
                ORDER BY d.destination_id, di.id
            """;


    public static final String GET_ACTIVITIES_WISH_LIST_DETAILS = """
                SELECT
                    a.id AS activity_id,
                    a.name AS activity_name,
                    a.description AS activity_description,
                    ac.name AS activities_category,
                    a.season,
                    ai.image_url AS activity_image,
                    CONCAT('/activities/', a.id) AS activity_url,
                    a.duration_hours AS activity_duration,
                    cs.name AS status,
                    aw.created_at
                FROM activities a
                LEFT JOIN activities_images ai
                    ON a.id = ai.activity_id AND ai.status = 1
                LEFT JOIN activity_category ac
                    ON a.activities_category = ac.name
                LEFT JOIN activity_wishlist aw
                    ON aw.activity_id = a.id
                LEFT JOIN common_status cs
                    ON aw.status_id = cs.id
                WHERE a.id IN (:ids)
                ORDER BY a.id, ai.id
            """;


    public static final String INSERT_ACTIVITY_WISH_DATA = """
                INSERT INTO activity_wishlist (user_id, activity_id, status_id, created_at)
                VALUES (?, ?, 1, CURRENT_TIMESTAMP)
            """;

    public static final String INSERT_ACTIVITY_WISHLIST_HISTORY = """
                INSERT INTO activity_wishlist_history
                (user_id, activity_id, wishlist_id, status_id, created_at)
                VALUES (
                    ?, ?, ?,
                    (
                        SELECT cs.id
                        FROM common_status cs
                        WHERE cs.name = ?
                        LIMIT 1
                    ),
                    CURRENT_TIMESTAMP
                )
            """;


    public static final String UPDATE_ACTIVITY_WISHLIST_STATUS = """
                UPDATE activity_wishlist
                SET
                    status_id = (
                        SELECT cs.id
                        FROM common_status cs
                        WHERE cs.name = ?
                        LIMIT 1
                    ),
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                  AND user_id = ?
            """;

    public static final String GET_EXISTING_ACTIVITY_WISHLIST_DATA = """
                SELECT
                    aw.id AS wishListId,
                    aw.activity_id AS activityId,
                    aw.user_id AS userId,
                    cs.name AS status,
                    aw.created_at AS createdAt,
                    aw.updated_at AS updatedAt
                FROM activity_wishlist aw
                JOIN common_status cs ON cs.id = aw.status_id
                WHERE aw.user_id = ?
                  AND aw.activity_id = ?
                LIMIT 1
            """;

    // Destination Wishlist Queries

    public static final String INSERT_DESTINATION_WISH_DATA = """
        INSERT INTO destination_wishlist (user_id, destination_id, status_id, created_at)
        VALUES (?, ?, 1, CURRENT_TIMESTAMP)
    """;

    public static final String INSERT_DESTINATION_WISHLIST_HISTORY = """
        INSERT INTO destination_wishlist_history
        (user_id, destination_id, wishlist_id, status_id, created_at)
        VALUES (
            ?, ?, ?,
            (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            CURRENT_TIMESTAMP
        )
    """;

    public static final String UPDATE_DESTINATION_WISHLIST_STATUS = """
        UPDATE destination_wishlist
        SET
            status_id = (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
          AND user_id = ?
    """;

    public static final String GET_EXISTING_DESTINATION_WISHLIST_DATA = """
        SELECT
            dw.id AS wishListId,
            dw.destination_id AS destinationId,
            dw.user_id AS userId,
            cs.name AS status,
            dw.created_at AS createdAt,
            dw.updated_at AS updatedAt
        FROM destination_wishlist dw
        JOIN common_status cs ON cs.id = dw.status_id
        WHERE dw.user_id = ?
          AND dw.destination_id = ?
        LIMIT 1
    """;

    // Package Wishlist Queries

    public static final String INSERT_PACKAGE_WISH_DATA = """
        INSERT INTO package_wishlist (user_id, package_id, status_id, created_at)
        VALUES (?, ?, 1, CURRENT_TIMESTAMP)
    """;

    public static final String INSERT_PACKAGE_WISHLIST_HISTORY = """
        INSERT INTO package_wishlist_history
        (user_id, package_id, wishlist_id, status_id, created_at)
        VALUES (
            ?, ?, ?,
            (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            CURRENT_TIMESTAMP
        )
    """;

    public static final String UPDATE_PACKAGE_WISHLIST_STATUS = """
        UPDATE package_wishlist
        SET
            status_id = (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
          AND user_id = ?
    """;

    public static final String GET_EXISTING_PACKAGE_WISHLIST_DATA = """
        SELECT
            pw.id AS wishListId,
            pw.package_id AS packageId,
            pw.user_id AS userId,
            cs.name AS status,
            pw.created_at AS createdAt,
            pw.updated_at AS updatedAt
        FROM package_wishlist pw
        JOIN common_status cs ON cs.id = pw.status_id
        WHERE pw.user_id = ?
          AND pw.package_id = ?
        LIMIT 1
    """;


    // Tour Wishlist Queries

    public static final String INSERT_TOUR_WISH_DATA = """
        INSERT INTO tour_wishlist (user_id, tour_id, status_id, created_at)
        VALUES (?, ?, 1, CURRENT_TIMESTAMP)
    """;

    public static final String INSERT_TOUR_WISHLIST_HISTORY = """
        INSERT INTO tour_wishlist_history
        (user_id, tour_id, wishlist_id, status_id, created_at)
        VALUES (
            ?, ?, ?,
            (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            CURRENT_TIMESTAMP
        )
    """;

    public static final String UPDATE_TOUR_WISHLIST_STATUS = """
        UPDATE tour_wishlist
        SET
            status_id = (
                SELECT cs.id
                FROM common_status cs
                WHERE cs.name = ?
                LIMIT 1
            ),
            updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
          AND user_id = ?
    """;

    public static final String GET_EXISTING_TOUR_WISHLIST_DATA = """
        SELECT
            tw.id AS wishListId,
            tw.tour_id AS tourId,
            tw.user_id AS userId,
            cs.name AS status,
            tw.created_at AS createdAt,
            tw.updated_at AS updatedAt
        FROM tour_wishlist tw
        JOIN common_status cs ON cs.id = tw.status_id
        WHERE tw.user_id = ?
          AND tw.tour_id = ?
        LIMIT 1
    """;



}
