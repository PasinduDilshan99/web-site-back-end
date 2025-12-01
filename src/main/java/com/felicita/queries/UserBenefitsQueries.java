package com.felicita.queries;

public class UserBenefitsQueries {

    public static final String GET_ALL_USER_BENEFITS = """
            SELECT
                ul.user_level_id,
                ul.level AS user_level_name,
                ul.description AS user_level_description,
                cs1.name AS user_level_status,
                ulb.benefit_id,
                ulb.name AS benefit_name,
                ulb.description AS benefit_description,
                ulb.benefit_value,
                ulb.valid_from,
                ulb.valid_to,
                cs2.name AS benefit_status,
                bt.benefit_type_id,
                bt.name AS benefit_type_name,
                bt.description AS benefit_type_description,
                cs3.name AS benefit_type_status
            FROM user_level ul
            LEFT JOIN common_status cs1
                ON ul.status_id = cs1.id
            INNER JOIN user_level_benefit_mapping ulbm
                ON ul.user_level_id = ulbm.user_level_id
            INNER JOIN user_level_benefit ulb
                ON ulbm.benefit_id = ulb.benefit_id
            INNER JOIN benefit_type bt
                ON ulb.benefit_type_id = bt.benefit_type_id
            LEFT JOIN common_status cs2
                ON ulb.status_id = cs2.id
            LEFT JOIN common_status cs3
                ON bt.status_id = cs3.id
            ORDER BY ul.user_level_id, ulb.benefit_id
            """;


    public static final String GET_USER_LEVEL_DETAILS = """
        SELECT 
            u.user_id,
            u.username,
            u.first_name,
            u.last_name,
            u.benefits_points_count,
            
            -- Current Level
            current.user_level_id AS current_level_id,
            current.level AS current_level_name,
            current.points_needed AS current_level_points,
            current.description AS current_level_description,
            
            -- Previous Level
            prev.user_level_id AS previous_level_id,
            prev.level AS previous_level_name,
            prev.points_needed AS previous_level_points,
            prev.description AS previous_level_description,
            
            -- Next Level
            next.user_level_id AS next_level_id,
            next.level AS next_level_name,
            next.points_needed AS next_level_points,
            next.description AS next_level_description,
            
            -- Progress Info
            CASE 
                WHEN next.user_level_id IS NOT NULL THEN 
                    ROUND((u.benefits_points_count - current.points_needed) / 
                          (next.points_needed - current.points_needed) * 100, 2)
                ELSE 100
            END AS progress_percentage,
            
            CASE 
                WHEN next.user_level_id IS NOT NULL THEN 
                    next.points_needed - u.benefits_points_count
                ELSE 0
            END AS points_needed_for_next_level

        FROM user u
        CROSS JOIN LATERAL (
            SELECT * FROM user_level 
            WHERE points_needed = (
                SELECT MAX(points_needed) FROM user_level 
                WHERE points_needed <= u.benefits_points_count 
                AND status_id = (SELECT id FROM common_status WHERE name = 'Active')
            )
            AND status_id = (SELECT id FROM common_status WHERE name = 'Active')
        ) AS current
        LEFT JOIN LATERAL (
            SELECT * FROM user_level 
            WHERE points_needed < current.points_needed
            AND status_id = (SELECT id FROM common_status WHERE name = 'Active')
            ORDER BY points_needed DESC LIMIT 1
        ) AS prev ON TRUE
        LEFT JOIN LATERAL (
            SELECT * FROM user_level 
            WHERE points_needed > current.points_needed
            AND status_id = (SELECT id FROM common_status WHERE name = 'Active')
            ORDER BY points_needed ASC LIMIT 1
        ) AS next ON TRUE
        WHERE u.user_id = ?
    """;

    public static final String GET_BENEFITS_BY_LEVEL_IDS = """
        SELECT 
            ul.user_level_id,
            ul.level AS level_name,
            b.benefit_id,
            b.name AS benefit_name,
            b.description AS benefit_description,
            b.benefit_value,
            bt.name AS benefit_type,
            bt.description AS benefit_type_description,
            b.valid_from,
            b.valid_to,
            cs.name AS benefit_status
        FROM user_level ul
        INNER JOIN user_level_benefit_mapping ulm ON ul.user_level_id = ulm.user_level_id
        INNER JOIN user_level_benefit b ON ulm.benefit_id = b.benefit_id
        INNER JOIN benefit_type bt ON b.benefit_type_id = bt.benefit_type_id
        INNER JOIN common_status cs ON b.status_id = cs.id
        WHERE ul.user_level_id IN (:levelIds)
            AND ul.status_id = (SELECT id FROM common_status WHERE name = 'Active')
            AND ulm.status_id = (SELECT id FROM common_status WHERE name = 'Active')
            AND b.status_id = (SELECT id FROM common_status WHERE name = 'Active')
            AND (b.valid_to IS NULL OR b.valid_to >= CURDATE())
        ORDER BY ul.user_level_id, bt.name, b.benefit_value DESC
    """;


}
