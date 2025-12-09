package com.felicita.queries;

public class AchievementQueries {

    public static final String GET_ACHIEVEMENT_DETAILS = """
            SELECT
                a.achievement_id,
                a.name AS achievement_name,
                a.color,
                a.hover_color,
                a.description AS achievement_description,
                cs.name AS achievement_status,
                ai.id AS image_id,
                ai.name AS image_name,
                ai.description AS image_description,
                ai.image_url,
                cs_img.name AS image_status
            FROM achievements a
            LEFT JOIN achievements_images ai
                ON a.achievement_id = ai.achievement_id
            LEFT JOIN common_status cs
                ON a.status_id = cs.id
            LEFT JOIN common_status cs_img
                ON ai.status = cs_img.id
            ORDER BY a.achievement_id, ai.id
            """;

}
