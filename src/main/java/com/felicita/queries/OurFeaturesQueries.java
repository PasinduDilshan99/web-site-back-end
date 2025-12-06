package com.felicita.queries;

public class OurFeaturesQueries {

    public static final String GET_OUR_FEATURES_DETAILS = """
            SELECT
                f.feature_id,
                f.name AS feature_name,
                f.icon_url,
                f.color,
                f.hover_color,
                f.description,
                f.status_id,
                s.name AS status_name,
                f.created_at,
                f.created_by,
                f.updated_at,
                f.updated_by,
                f.terminated_at,
                f.terminated_by
            FROM our_features f
            LEFT JOIN common_status s
                ON f.status_id = s.id
            ORDER BY f.feature_id
            """;

}
