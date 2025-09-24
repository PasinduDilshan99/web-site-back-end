package com.felicita.queries;

public class ReviewQueries {

    public static final String GET_ALL_REVIEW = """
            SELECT
                 r.review_id,
                 r.rating,
                 r.comment AS review_description,
                 r.created_at AS review_created_at,
                 r.updated_at AS review_updated_at,
                 cs.name AS review_status,
                 u.user_id,
                 u.username,
                 u.first_name,
                 u.last_name,
                 u.image_url AS user_avatar,
                 t.tour_id,
                 t.name AS tour_name,
                 p.package_id,
                 p.name AS package_name,
                 ri.id AS image_id,
                 ri.image_url,
                 rr.id AS reaction_id,
                 rt.name AS reaction_type,
                 ru.user_id AS reacted_by_user_id,
                 ru.username AS reacted_by_username,
                 rc.id AS comment_id,
                 rc.comment_text,
                 rc.parent_id AS comment_parent_id,
                 cu.user_id AS comment_user_id,
                 cu.username AS comment_user_name,
                 rc.created_at AS comment_created_at
             FROM review r
             LEFT JOIN common_status cs ON r.status_id = cs.id
             LEFT JOIN user u ON r.user_id = u.user_id
             LEFT JOIN tour t ON r.tour_id = t.tour_id
             LEFT JOIN package p ON r.package_id = p.package_id
             LEFT JOIN review_image ri ON ri.review_id = r.review_id AND ri.status_id = 1
             LEFT JOIN review_reaction rr ON rr.review_id = r.review_id AND rr.status_id = 1
             LEFT JOIN reaction_type rt ON rr.reaction_type_id = rt.id
             LEFT JOIN user ru ON rr.user_id = ru.user_id
             LEFT JOIN review_comment rc ON rc.review_id = r.review_id AND rc.status_id = 1
             LEFT JOIN user cu ON rc.user_id = cu.user_id
             ORDER BY r.review_id, ri.id, rr.id, rc.id
            """;


}
