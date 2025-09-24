package com.felicita.queries;

public class UserLevelQueries {

    public static final String GET_ALL_USER_LEVELS_WITH_BENEFITS = """
            SELECT
                ul.user_level_id,
                ul.level AS user_level_name,
                ul.description AS user_level_description,
                cs1.name AS user_level_status,
                ulb.benefit_id,
                ulb.name AS benefit_name,
                ulb.description AS benefit_description,
                ulb.benefit_value,
                bt.benefit_type_id AS benefit_type_id,
                bt.name AS benefit_type,
                bt.description AS benefit_type_description,
                cs3.name AS benefit_type_status,
                ulb.valid_from,
                ulb.valid_to,
                cs2.name AS benefit_status
            FROM user_level ul
            LEFT JOIN common_status cs1
                ON ul.status_id = cs1.id
            INNER JOIN user_level_benefit_mapping ulbm
                ON ul.user_level_id = ulbm.user_level_id
            INNER JOIN user_level_benefit ulb
                ON ulbm.benefit_id = ulb.benefit_id
            INNER JOIN benefit_type bt
                ON ulb.benefit_type_id = bt.benefit_type_id
                AND bt.status_id = 1
            LEFT JOIN common_status cs2
                ON ulb.status_id = cs2.id
            LEFT JOIN common_status cs3
                ON bt.status_id = cs3.id
            ORDER BY ul.user_level_id, ulb.benefit_id
            """;

    public static final String GET_ALL_USER_LEVELS = """
            SELECT
                 ul.user_level_id AS user_level_id,
                 ul.level AS user_level_name,
                 ul.description AS user_level_description,
                 cs.name AS status_name,
                 ul.created_at,
                 ul.created_by,
                 u1.username AS created_by_user,
                 ul.updated_at,
                 ul.updated_by,
                 u2.username AS updated_by_user,
                 ul.terminated_at,
                 ul.terminated_by,
                 u3.username AS terminated_by_user
             FROM user_level ul
             LEFT JOIN common_status cs
                 ON ul.status_id = cs.id
             LEFT JOIN user u1
                 ON ul.created_by = u1.user_id
             LEFT JOIN user u2
                 ON ul.updated_by = u2.user_id
             LEFT JOIN user u3
                 ON ul.terminated_by = u3.user_id
            """;

}
