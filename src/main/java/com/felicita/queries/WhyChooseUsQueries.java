package com.felicita.queries;

public class WhyChooseUsQueries {
    public static final String GET_ALL_WHY_CHOOSE_US_DATA = """
            SELECT
            	wcs.id AS CARD_ID,
                wcs.name AS CARD_NAME,
                wcs.title AS CARD_TITLE,
                wcs.sub_title AS CARD_SUB_TITLE,
                wcs.description AS CARD_DESCRIPTION,
                wcs.image_url AS CARD_IMAGE_URL,
                wcs.icon_url AS CARD_ICON_URL,
                wcs.clicked_url AS CARD_CLICKED_URL,
                wcus.name as CARD_STATUS,
                cs.name AS CARD_STATUS_STATUS,
                wcs.color AS CARD_COLOR,
                wcs.order AS CARD_ORDER,
            	wcs.created_at AS CARD_CREATED_AT,
                wcs.created_by AS CARD_CREATED_BY,
                wcs.updated_at AS CARD_UPDATED_AT,
                wcs.updated_by AS CARD_UPDATED_BY,
                wcs.terminated_at AS CARD_TERMINATED_AT,
                wcs.terminated_by AS CARD_TERMINATED_BY
            FROM why_choose_us wcs
            LEFT JOIN why_choose_us_status wcus
            ON wcs.why_choose_us_status_id = wcus.id
            LEFT JOIN common_status cs
            ON wcus.common_status_id = cs.id
    """;
}
