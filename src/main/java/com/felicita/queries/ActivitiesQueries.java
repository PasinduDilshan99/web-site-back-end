package com.felicita.queries;

public class ActivitiesQueries {

    public static final String GET_ALL_ACTIVITIES = """
                        SELECT
                            a.id,
                            a.destination_id,
                            a.name,
                            a.description,
                            a.activities_category,
                            a.duration_hours,
                            a.available_from,
                            a.available_to,
                            a.price_local,
                            a.price_foreigners,
                            a.min_participate,
                            a.max_participate,
                            a.season,
                            MAX(cs.name) AS status_name,
                            a.created_at,
                            a.updated_at,
                            MAX(ac.name) AS category_name,
                            MAX(ac.description) AS category_description,
                            CASE
                                WHEN COUNT(asch.id) > 0 THEN
                                    JSON_ARRAYAGG(
                                        JSON_OBJECT(
                                            'id', asch.id,
                                            'name', asch.name,
                                            'assume_start_date', asch.assume_start_date,
                                            'assume_end_date', asch.assume_end_date,
                                            'duration_hours_start', asch.duration_hours_start,
                                            'duration_hours_end', asch.duration_hours_end,
                                            'special_note', asch.special_note,
                                            'description', asch.description,
                                            'status', asch.status
                                        )
                                    )
                                ELSE JSON_ARRAY()
                            END AS schedules,
                            (SELECT COALESCE(JSON_ARRAYAGG(
                                JSON_OBJECT(
                                    'id', ar.id,
                                    'name', ar.name,
                                    'value', ar.value,
                                    'description', ar.description,
                                    'color', ar.color,
                                    'status', ar.status
                                )
                            ), JSON_ARRAY())
                            FROM activities_requirement ar
                            WHERE ar.activity_id = a.id AND ar.terminated_at IS NULL
                            ) AS requirements,
                            (SELECT COALESCE(JSON_ARRAYAGG(
                                JSON_OBJECT(
                                    'id', ai.id,
                                    'name', ai.name,
                                    'description', ai.description,
                                    'image_url', ai.image_url,
                                    'status', ai.status
                                )
                            ), JSON_ARRAY())
                            FROM activities_images ai
                            WHERE ai.activity_id = a.id AND ai.terminated_at IS NULL
                            ) AS images
                        FROM activities a
                        LEFT JOIN common_status cs ON a.status = cs.id
                        LEFT JOIN activity_category ac ON a.activities_category = ac.name
                        LEFT JOIN activities_schedule asch ON asch.activity_id = a.id
                            AND asch.terminated_at IS NULL
                        WHERE a.terminated_at IS NULL
                        GROUP BY a.id, a.destination_id, a.name, a.description, a.activities_category,
                                 a.duration_hours, a.available_from, a.available_to, a.price_local,
                                 a.price_foreigners, a.min_participate, a.max_participate, a.season,
                                 a.status, a.created_at, a.updated_at
                        LIMIT 1000
            """;

    public static final String GET_ACTIVITY_CATEGORIES = """
            SELECT
                ac.id,
                ac.name,
                ac.description,
                ac.image_url,
                ac.color,
                ac.hover_color,
                cs.name AS status_name,
                ac.created_at,
                ac.created_by,
                ac.updated_at,
                ac.updated_by
            FROM activities_category ac
            JOIN common_status cs ON ac.common_status_id = cs.id
            """;


}
