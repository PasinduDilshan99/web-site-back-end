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

}
