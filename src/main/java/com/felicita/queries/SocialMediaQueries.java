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

}
