package com.felicita.queries;

public class HeroSectionQueries {
    public static final String GET_ALL_HERO_SECTION_DATA = """
            SELECT
            	hs.id AS IMAGE_ID,
                hs.name AS IMAGE_NAME,
                hs.image_url AS IMAGE_URL,
                hs.title AS IMAGE_TITLE,
                hs.subtitle AS IMAGE_SUB_TITLE,
                hs.description AS ImAGE_DESCRIPTION,
                hs.primary_button_text AS IMAGE_PRIMARY_BUTTON_TEXT,
                hs.primary_button_link AS IMAGE_PRIMARY_BUTTON_LINK,
                hs.secondary_button_text AS IMAGE_SECONDRY_BUTTON_TEXT,
                hs.secondary_button_link AS IMAGE_SECONDRY_BUTTON_LINK,
                hss.name AS IMAGE_STATUS,
                cs.name AS IMAGE_STATUS_STATUS,
                hs.order AS IMAGE_ORDER,
                hs.created_at AS IMAGE_CREATED_AT,
                hs.created_by AS IMAGE_CREATED_BY,
                hs.updated_at AS IMAGE_UPDATED_AT,
                hs.updated_by AS IMAGE_UPDATED_BY,
                hs.terminated_at AS IMAGE_TERMINATED_AT,
                hs.terminated_by AS IMAGE_TERMINATED_BY
            FROM hero_section hs
            LEFT JOIN hero_section_status hss
            ON hs.hero_section_status_id = hss.id
            lEFT JOIN common_status cs
            ON hss.common_status_id = cs.id
            """;

    public static final String GET_ALL_ABOUT_US_HERO_SECTION_DATA = """
            SELECT
               a.id,
               a.name,
               a.image_url,
               a.title,
               a.subtitle,
               a.description,
               a.primary_button_text,
               a.primary_button_link,
               a.secondary_button_text,
               a.secondary_button_link,
               a.`order`,
               a.created_at,
               a.updated_at,
               s.name AS status_name
           FROM about_us_hero_section a
           LEFT JOIN common_status s ON a.status = s.id
           WHERE a.terminated_at IS NULL
           ORDER BY a.`order` ASC, a.created_at ASC
            """;
}
