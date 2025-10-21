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

    public static final String GET_ALL_ACTIVITY_CATEGORIES = """
            SELECT
                ac.id AS category_id,
                ac.name AS category_name,
                ac.description AS category_description,
                ac.created_at AS category_created_at,
                ac.created_by AS category_created_by,
                ac.updated_at AS category_updated_at,
                ac.updated_by AS category_updated_by,
                ac.terminated_at AS category_terminated_at,
                ac.terminated_by AS category_terminated_by,
                cs1.name AS category_status,
                aci.id AS image_id,
                aci.name AS image_name,
                aci.description AS image_description,
                aci.image_url,
                cs2.name AS image_status,
                aci.created_at AS image_created_at,
                aci.created_by AS image_created_by,
                aci.updated_at AS image_updated_at,
                aci.updated_by AS image_updated_by,
                aci.terminated_at AS image_terminated_at,
                aci.terminated_by AS image_terminated_by
            FROM activity_category ac
            LEFT JOIN activity_category_images aci
                ON ac.id = aci.activity_category_id
            LEFT JOIN common_status cs1
            	ON cs1.id = ac.status
            LEFT JOIN common_status cs2
            	ON cs2.id = aci.status
            ORDER BY ac.id, aci.id
            """;


    public static final String GET_ACTIVITY_REVIEW_DETAILS = """
            SELECT
                ar.id AS review_id,
                ar.activity_schedule_id,
                a.id AS activity_id,
                a.name AS activity_name,
                ar.name AS review_name,
                ar.review,
                ar.rating,
                ar.description,
                cs_ar.name AS review_status,
                ar.number_of_participate,
                ar.created_by AS review_created_by,
                ar.created_at AS review_created_at,
                ar.updated_by AS review_updated_by,
                ar.updated_at AS review_updated_at,
                ari.id AS image_id,
                ari.name AS image_name,
                ari.description AS image_description,
                ari.image_url AS image_url,
                cs_ari.name AS image_status,
                ari.created_by AS image_created_by,
                ari.created_at AS image_created_at,
                arr.id AS review_reaction_id,
                arr.activity_review_id AS reaction_review_id,
                arr.user_id AS reaction_user_id,
                u1.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_arr.name AS review_reaction_status,
                arr.created_at AS reaction_created_at,
                arc.id AS comment_id,
                arc.activity_review_id AS comment_review_id,
                arc.user_id AS comment_user_id,
                u2.username AS comment_user_name,
                arc.parent_comment_id,
                arc.comment,
                cs_arc.name AS comment_status,
                arc.created_at AS comment_created_at,
                arc.created_by AS comment_created_by,
                arcr.id AS comment_reaction_id,
                arcr.comment_id AS comment_reaction_comment_id,
                arcr.user_id AS comment_reaction_user_id,
                u3.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_arcr.name AS comment_reaction_status,
                arcr.created_by AS comment_reaction_created_by,
                arcr.created_at AS comment_reaction_created_at
            FROM activities_review ar
            LEFT JOIN activities_schedule ars
                ON ars.id = ar.activity_schedule_id
            LEFT JOIN activities a
                ON a.id = ars.activity_id
            LEFT JOIN common_status cs_ar
                ON cs_ar.id = ar.status
            LEFT JOIN activities_review_images ari
                ON ari.activities_review_id = ar.id
            LEFT JOIN common_status cs_ari
                ON cs_ari.id = ari.status
            LEFT JOIN activity_review_reaction arr
                ON arr.activity_review_id = ar.id
            LEFT JOIN reaction_type rt
                ON rt.id = arr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = arr.user_id
            LEFT JOIN common_status cs_arr
                ON cs_arr.id = arr.status
            LEFT JOIN activity_review_comment arc
                ON arc.activity_review_id = ar.id
            LEFT JOIN user u2
                ON u2.user_id = arc.user_id
            LEFT JOIN common_status cs_arc
                ON cs_arc.id = arc.status
            LEFT JOIN activity_review_comment_reaction arcr
                ON arcr.comment_id = arc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = arcr.reaction_type_id
            LEFT JOIN user u3
                ON u3.user_id = arcr.user_id
            LEFT JOIN common_status cs_arcr
                ON cs_arcr.id = arcr.status
            ORDER BY ar.id, arc.id, arcr.id
            """;

    public static final String GET_ACTIVITY_REVIEW_DETAILS_BY_ID = """
            SELECT
                ar.id AS review_id,
                ar.activity_schedule_id,
                a.id AS activity_id,
                a.name AS activity_name,
                ar.name AS review_name,
                ar.review,
                ar.rating,
                ar.description,
                cs_ar.name AS review_status,
                ar.number_of_participate,
                ar.created_by AS review_created_by,
                ar.created_at AS review_created_at,
                ar.updated_by AS review_updated_by,
                ar.updated_at AS review_updated_at,
                ari.id AS image_id,
                ari.name AS image_name,
                ari.description AS image_description,
                ari.image_url AS image_url,
                cs_ari.name AS image_status,
                ari.created_by AS image_created_by,
                ari.created_at AS image_created_at,
                arr.id AS review_reaction_id,
                arr.activity_review_id AS reaction_review_id,
                arr.user_id AS reaction_user_id,
                u1.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_arr.name AS review_reaction_status,
                arr.created_at AS reaction_created_at,
                arc.id AS comment_id,
                arc.activity_review_id AS comment_review_id,
                arc.user_id AS comment_user_id,
                u2.username AS comment_user_name,
                arc.parent_comment_id,
                arc.comment,
                cs_arc.name AS comment_status,
                arc.created_at AS comment_created_at,
                arc.created_by AS comment_created_by,
                arcr.id AS comment_reaction_id,
                arcr.comment_id AS comment_reaction_comment_id,
                arcr.user_id AS comment_reaction_user_id,
                u3.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_arcr.name AS comment_reaction_status,
                arcr.created_by AS comment_reaction_created_by,
                arcr.created_at AS comment_reaction_created_at
            FROM activities_review ar
            LEFT JOIN activities_schedule ars
                ON ars.id = ar.activity_schedule_id
            LEFT JOIN activities a
                ON a.id = ars.activity_id
            LEFT JOIN common_status cs_ar
                ON cs_ar.id = ar.status
            LEFT JOIN activities_review_images ari
                ON ari.activities_review_id = ar.id
            LEFT JOIN common_status cs_ari
                ON cs_ari.id = ari.status
            LEFT JOIN activity_review_reaction arr
                ON arr.activity_review_id = ar.id
            LEFT JOIN reaction_type rt
                ON rt.id = arr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = arr.user_id
            LEFT JOIN common_status cs_arr
                ON cs_arr.id = arr.status
            LEFT JOIN activity_review_comment arc
                ON arc.activity_review_id = ar.id
            LEFT JOIN user u2
                ON u2.user_id = arc.user_id
            LEFT JOIN common_status cs_arc
                ON cs_arc.id = arc.status
            LEFT JOIN activity_review_comment_reaction arcr
                ON arcr.comment_id = arc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = arcr.reaction_type_id
            LEFT JOIN user u3
                ON u3.user_id = arcr.user_id
            LEFT JOIN common_status cs_arcr
                ON cs_arcr.id = arcr.status
            WHERE a.id = ?
            ORDER BY ar.id, arc.id, arcr.id
            """;

    public static final String GET_ACTIVITY_DETAILS_BY_ID = """
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
            WHERE a.terminated_at IS NULL AND a.id = ?
            GROUP BY a.id, a.destination_id, a.name, a.description, a.activities_category,
            		 a.duration_hours, a.available_from, a.available_to, a.price_local,
            		 a.price_foreigners, a.min_participate, a.max_participate, a.season,
            		 a.status, a.created_at, a.updated_at
            """;

}
