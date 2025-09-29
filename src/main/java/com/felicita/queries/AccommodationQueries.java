package com.felicita.queries;

public class AccommodationQueries {
    public static final String GET_ALL_ACCOMMODATION_DATA = """
            SELECT
            	ac.id AS ACCOMMADATION_ID,
                ac.title AS ACCOMMADATION_TITLE,
                ac.sub_title AS ACCOMMADATION_SUB_TITLE,
                ac.description AS ACCOMMADATION_DESCRIPTION,
                ac.icon_url AS ACCOMMADATION_ICON_URL,
                ac.image_url AS ACCOMMADATION_IMAGE_URL,
                ac.color AS ACCOMMADATION_COLOR,
                ac.hover_color AS ACCOMMADATION_HOVER_COLOR,
                ac.link_url AS ACCOMMADATION_LINK_URL,
                acs.name AS ACCOMMADATION_STATUS,
                cs.name AS ACCOMMADATION_STATUS_STATUS,
            	ac.created_at AS ACCOMMADATION_CREATED_AT,
                ac.created_by AS ACCOMMADATION_CREATED_BY,
                ac.updated_at AS ACCOMMADATION_UPDATED_AT,
                ac.updated_by AS ACCOMMADATION_UPDATED_BY,
                ac.terminated_at AS ACCOMMADATION_TERMINATED_AT,
                ac.terminated_by AS ACCOMMADATION_TERMINATED_BY
            FROM accommodation ac
            LEFT JOIN accommodation_status acs
            ON ac.status_id = acs.id
            LEFT JOIN common_status cs
            ON acs.common_status_id = cs.id
            """;

}
