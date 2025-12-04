package com.felicita.queries;

public class StatisticsQueries {

    public static final String GET_ABOUT_US_STATISTICS_DETAILS =
            """
                    SELECT
                        s.id,
                        s.name,
                        s.image_url,
                        s.title,
                        s.description,
                        s.color,
                        s.hover_color,
                        s.value,
                        cs.name AS status,
                        s.created_at,
                        u1.username AS created_by_name,
                        s.updated_at,
                        u2.username AS updated_by_name,
                        s.terminated_at,
                        u3.username AS terminated_by_name
                    FROM statistics s
                    LEFT JOIN common_status cs ON s.status = cs.id
                    LEFT JOIN user u1 ON s.created_by = u1.user_id
                    LEFT JOIN user u2 ON s.updated_by = u2.user_id
                    LEFT JOIN user u3 ON s.terminated_by = u3.user_id
                    ORDER BY s.created_at DESC     
                    """
            ;

}
