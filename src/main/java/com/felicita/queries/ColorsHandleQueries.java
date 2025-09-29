package com.felicita.queries;

public class ColorsHandleQueries {

    public static final String GET_HOME_PAGE_COLORS = """
            SELECT
                p.id AS page_id,
                p.name AS page_name,
                p.slug AS page_slug,
                pc.id AS page_component_id,
                pc.order_index,
                pc.is_visible,
                c.id AS component_id,
                c.name AS component_name,
                c.description AS component_description,
                ct.id AS theme_id,
                ct.theme_name,
                ct.theme_description,
                ct.primary_color,
                ct.secondary_color,
                ct.background_color,
                ct.gradient_enabled,
                ct.gradient_type,
                ct.gradient_direction,
                ct.gradient_start,
                ct.gradient_end,
                ct.text_primary,
                ct.text_secondary,
                ct.banner_image,
                ct.custom_css
            FROM page p
            INNER JOIN page_component pc ON p.id = pc.page_id
            INNER JOIN component c ON pc.component_id = c.id
            LEFT JOIN component_theme ct ON c.id = ct.component_id
            WHERE p.slug = ?
            ORDER BY pc.order_index, c.id, ct.id
            """;

}
