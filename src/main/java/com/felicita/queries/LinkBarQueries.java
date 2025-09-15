package com.felicita.queries;

public class LinkBarQueries {

    public static final String GET_ALL_LINK_BAR_DATA = """
            SELECT
                    lb.name AS name,
                    lb.description AS description,
                    lbt.name AS type_name,
                    cs1.name AS type_status,
                    lb.icon_url AS icon_url,
                    lb.link_url AS link_url,
                    lbs.name AS item_status,
                    cs2.name AS item_status_status,
                    lb.created_at AS created_at,
                    lb.created_by AS created_by,
                    lb.updated_at AS updated_at,
                    lb.updated_by AS updated_by,
                    lb.terminated_at AS terminated_at,
                    lb.terminated_by AS terminated_by
                FROM link_bar lb
                LEFT JOIN link_bar_status lbs
                    ON lb.link_bar_status_id = lbs.id
                LEFT JOIN link_bar_type lbt
                    ON lb.link_bar_type_id = lbt.id
                LEFT JOIN common_status cs1
                    ON lbt.common_status_id = cs1.id
                LEFT JOIN common_status cs2
                    ON lbs.common_status_id = cs2.id
            """;
}
