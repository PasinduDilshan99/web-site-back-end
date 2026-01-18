package com.felicita.queries;

public class HeroSectionQueries {

    private HeroSectionQueries() {
    }

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


    public static final String GET_ALL_CONTACT_US_HERO_SECTION_DATA = """
            SELECT
                c.id,
                c.name,
                c.image_url,
                c.title,
                c.subtitle,
                c.description,
                c.primary_button_text,
                c.primary_button_link,
                c.secondary_button_text,
                c.secondary_button_link,
                c.`order`,
                c.created_at,
                c.updated_at,
                s.name AS status_name
            FROM contact_us_hero_section c
            LEFT JOIN common_status s ON c.status = s.id
            WHERE c.terminated_at IS NULL
            ORDER BY c.`order` ASC, c.created_at ASC
            """;

    public static final String GET_ALL_BLOG_HERO_SECTION_DATA = """
            SELECT
                b.id,
                b.name,
                b.image_url,
                b.title,
                b.subtitle,
                b.description,
                b.primary_button_text,
                b.primary_button_link,
                b.secondary_button_text,
                b.secondary_button_link,
                b.`order`,
                b.created_at,
                b.updated_at,
                s.name AS status_name
            FROM blog_hero_section b
            LEFT JOIN common_status s ON b.status = s.id
            WHERE b.terminated_at IS NULL
            ORDER BY b.`order` ASC, b.created_at ASC
            """;

    public static final String GET_ALL_FAQ_HERO_SECTION_DATA = """
            SELECT
                fhs.id,
                fhs.name,
                fhs.image_url,
                fhs.title,
                fhs.subtitle,
                fhs.description,
                fhs.primary_button_text,
                fhs.primary_button_link,
                fhs.secondary_button_text,
                fhs.secondary_button_link,
                cs.name AS status,
                fhs.`order`,
                fhs.created_at,
                fhs.created_by,
                fhs.updated_at,
                fhs.updated_by,
                fhs.terminated_at,
                fhs.terminated_by
            FROM faq_hero_section fhs
            JOIN common_status cs ON fhs.status = cs.id
            ORDER BY fhs.`order` ASC
            """;

    public static final String GET_ALL_TOUR_HERO_SECTION_DATA = """
            SELECT
                ths.id,
                ths.name,
                ths.image_url,
                ths.title,
                ths.subtitle,
                ths.description,
                ths.primary_button_text,
                ths.primary_button_link,
                ths.secondary_button_text,
                ths.secondary_button_link,
                cs.name AS status,
                ths.`order`,
                ths.created_at,
                ths.created_by,
                ths.updated_at,
                ths.updated_by,
                ths.terminated_at,
                ths.terminated_by
            FROM tour_hero_section ths
            JOIN common_status cs ON ths.status = cs.id
            ORDER BY ths.`order` ASC
            """;

    public static final String GET_ALL_DESTINATION_HERO_SECTION_DATA = """
            SELECT
                dhs.id,
                dhs.name,
                dhs.image_url,
                dhs.title,
                dhs.subtitle,
                dhs.description,
                dhs.primary_button_text,
                dhs.primary_button_link,
                dhs.secondary_button_text,
                dhs.secondary_button_link,
                cs.name AS status,
                dhs.`order`,
                dhs.created_at,
                dhs.created_by,
                dhs.updated_at,
                dhs.updated_by,
                dhs.terminated_at,
                dhs.terminated_by
            FROM destination_hero_section dhs
            JOIN common_status cs ON dhs.status = cs.id
            ORDER BY dhs.`order` ASC
            """;

    public static final String GET_ALL_ACTIVITY_HERO_SECTION_DATA = """
            SELECT
                ahs.id,
                ahs.name,
                ahs.image_url,
                ahs.title,
                ahs.subtitle,
                ahs.description,
                ahs.primary_button_text,
                ahs.primary_button_link,
                ahs.secondary_button_text,
                ahs.secondary_button_link,
                cs.name AS status,
                ahs.`order`,
                ahs.created_at,
                ahs.created_by,
                ahs.updated_at,
                ahs.updated_by,
                ahs.terminated_at,
                ahs.terminated_by
            FROM activity_hero_section ahs
            JOIN common_status cs ON ahs.status = cs.id
            ORDER BY ahs.`order` ASC
            """;

    public static final String GET_ALL_PACKAGE_HERO_SECTION_DATA = """
            SELECT
                phs.id,
                phs.name,
                phs.image_url,
                phs.title,
                phs.subtitle,
                phs.description,
                phs.primary_button_text,
                phs.primary_button_link,
                phs.secondary_button_text,
                phs.secondary_button_link,
                cs.name AS status,
                phs.`order`,
                phs.created_at,
                phs.created_by,
                phs.updated_at,
                phs.updated_by,
                phs.terminated_at,
                phs.terminated_by
            FROM package_hero_section phs
            JOIN common_status cs ON phs.status = cs.id
            ORDER BY phs.`order` ASC
            """;

    public static final String GET_ALL_PACKAGE_SCHEDULE_HERO_SECTION_DATA = """
            SELECT
                pi.id,
            	pi.name,
                pi.description,
                pi.image_url,
                pi.color
            FROM package_images pi
            LEFT JOIN packages p
            	ON p.package_id = pi.package_id
            WHERE p.package_id = ?
            """;

    public static final String GET_BOOKED_TOUR_HERO_SECTION_DATA = """
            SELECT
            	ti.id,
            	ti.name,
                ti.description,
                ti.image_url
            FROM bookings b
            LEFT JOIN package_schedule ps
            	ON ps.id = b.package_schedule_id
            LEFT JOIN packages p
            	ON p.package_id =  ps.package_id
            LEFt JOIN tour t
            	ON t.tour_id = p.tour_id
            LEFT JOIN tour_images ti
            	ON ti.tour_id = t.tour_id
            WHERE b.booking_id = ?
            """;

}
