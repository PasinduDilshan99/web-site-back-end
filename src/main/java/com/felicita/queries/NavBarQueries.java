package com.felicita.queries;

public class NavBarQueries {

    public static final String GET_ALL_NAV_BAR_DATA = """
    SELECT
        nb.name AS NAME,
        nb.description AS DESCRIPTION,
        nbs.name AS STATUS,
        cs.name AS NAV_BAR_STATUS_STATUS,
        nb.link_url AS LINK_URL,
        nb.created_at AS CREATED_AT,
        nb.created_by AS CREATED_BY,
        nb.updated_at AS UPDATED_AT,
        nb.updated_by AS UPDATED_BY,
        nb.terminated_at AS TERMINATED_AT,
        nb.terminated_by AS TERMINATED_BY
    FROM nav_bar nb
    LEFT JOIN nav_bar_status nbs
        ON nb.status_id = nbs.id
    LEFT JOIN common_status cs
        ON nbs.common_status_id = cs.id
    """;

}
