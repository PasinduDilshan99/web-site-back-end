package com.felicita.queries;

public class FooterQueries {

    public static final String GET_ALL_FOOTER_DATA = """
            SELECT
               f.id AS footer_id,
               f.title AS footer_title,
               f.description AS footer_description,
               f.color AS footer_color,
               cs1.name AS footer_status,
               s.id AS sub_item_id,
               s.name AS sub_item_name,
               s.description AS sub_item_description,
               s.icon AS sub_item_icon,
               s.link_url AS sub_item_url,
               cs2.name AS sub_item_status
           FROM footer f
           LEFT JOIN footer_sub_items s ON f.id = s.footer_id
           LEFT JOIN common_status cs1
           ON cs1.id = f.status
           LEFT JOIN common_status cs2
           ON cs2.id = f.status
            """;

    public static final String GET_ALL_OTHERS_FOOTER_DATA = """
            SELECT
                fo.id,
                fo.name,
                fo.description,
                fo.link_url,
                cs.name AS status_name
            FROM footer_others fo
            LEFT JOIN common_status cs
                ON cs.id = fo.status
            """;



}
