package com.felicita.queries;

public class SocialMediaQueries {

    public static final String GET_ALL_SOCIAL_MEDIA = """
            SELECT
                sm.id,
                sm.name,
                sm.description,
                sm.link,
                sm.icon_url,
                sm.color,
                sm.hover_color,
                cs.name AS status
            FROM social_media sm
            LEFT JOIN common_status cs
            ON cs.id = sm.status;
            """;

    public static final String GET_ALL_SOCIAL_MEDIA_WITH_BEST_FOR = """
            SELECT
                sm.id                       AS social_media_id,
                sm.username                 AS social_media_username,
                sm.name                     AS social_media_name,
                sm.description              AS social_media_description,
                sm.link,
                sm.icon_url,
                sm.color,
                sm.hover_color,
                cs_sm.name                  AS social_media_status,
                bf.id                       AS best_for_id,
                bf.name                     AS best_for_name,
                bf.description              AS best_for_description,
                cs_bf.name                  AS best_for_status,
                sm.created_at,
                sm.created_by,
                sm.updated_at,
                sm.updated_by
            FROM social_media sm
            LEFT JOIN common_status cs_sm
                   ON cs_sm.id = sm.status
            LEFT JOIN social_media_best_for bf
                   ON bf.social_media_id = sm.id
            LEFT JOIN common_status cs_bf
                   ON cs_bf.id = bf.status_id
            WHERE cs_bf.id = 1
            ORDER BY sm.id, bf.id
            """;
}
