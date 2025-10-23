package com.felicita.queries;

public class PackageQueries {

    public static final String GET_ALL_PACKAGES = """
                SELECT
                    p.package_id,
                    p.name AS package_name,
                    p.description AS package_description,
                    p.total_price,
                    p.discount_percentage,
                    p.start_date,
                    p.end_date,
                    p.color,
                    p.price_per_person,
                    p.hover_color,
                    p.min_person_count,
                    p.max_person_count,
                    p.price_per_person,
                    p.created_at,
                    p.created_by,
            
                    pt.name AS package_type_name,
                    pt.description AS package_type_description,
                    cs3.name AS package_type_status,
            
                    cs2.name AS package_status,
            
                    t.tour_id,
                    t.name AS tour_name,
                    t.description AS tour_description,
                    t.duration,
                    t.latitude,
                    t.longitude,
                    t.start_location,
                    t.end_location,
                    t.status AS tour_status,
            
                    ps.id AS schedule_id,
                    ps.name AS schedule_name,
                    ps.assume_start_date,
                    ps.assume_end_date,
                    ps.duration_start,
                    ps.duration_end,
                    ps.special_note AS schedule_special_note,
                    ps.description AS schedule_description,
            
                    f.id AS feature_id,
                    f.name AS feature_name,
                    f.value AS feature_value,
                    f.description AS feature_description,
                    f.color AS feature_color,
                    f.special_note AS feature_special_note,
            
                    pi.id AS image_id,
                    pi.name AS image_name,
                    pi.description AS image_description,
                    pi.image_url,
                    pi.color AS image_color
            
                FROM
                    packages p
                    LEFT JOIN common_status cs2 ON p.status = cs2.id
                    LEFT JOIN package_type pt ON pt.id = p.package_type_id
                    LEFT JOIN common_status cs3 ON pt.status = cs3.id
                    LEFT JOIN tour t ON p.tour_id = t.tour_id
                    LEFT JOIN package_schedule ps ON p.package_id = ps.package_id
                    LEFT JOIN features f ON p.package_id = f.package_id
                    LEFT JOIN package_images pi ON p.package_id = pi.package_id
                    LEFT JOIN common_status cs_package ON p.status = cs_package.id
                    LEFT JOIN common_status cs_tour ON t.status = cs_tour.id
            """;


    public static final String GET_PACKAGE_DETAILS_BY_PACKAGE_ID = """
                SELECT
                    p.package_id,
                    p.name AS package_name,
                    p.description AS package_description,
                    p.total_price,
                    p.discount_percentage,
                    p.start_date,
                    p.end_date,
                    p.color,
                    p.price_per_person,
                    p.hover_color,
                    p.min_person_count,
                    p.max_person_count,
                    p.price_per_person,
                    p.created_at,
                    p.created_by,
            
                    pt.name AS package_type_name,
                    pt.description AS package_type_description,
                    cs3.name AS package_type_status,
            
                    cs2.name AS package_status,
            
                    t.tour_id,
                    t.name AS tour_name,
                    t.description AS tour_description,
                    t.duration,
                    t.latitude,
                    t.longitude,
                    t.start_location,
                    t.end_location,
                    t.status AS tour_status,
            
                    ps.id AS schedule_id,
                    ps.name AS schedule_name,
                    ps.assume_start_date,
                    ps.assume_end_date,
                    ps.duration_start,
                    ps.duration_end,
                    ps.special_note AS schedule_special_note,
                    ps.description AS schedule_description,
            
                    f.id AS feature_id,
                    f.name AS feature_name,
                    f.value AS feature_value,
                    f.description AS feature_description,
                    f.color AS feature_color,
                    f.special_note AS feature_special_note,
            
                    pi.id AS image_id,
                    pi.name AS image_name,
                    pi.description AS image_description,
                    pi.image_url,
                    pi.color AS image_color
            
                FROM
                    packages p
                    LEFT JOIN common_status cs2 ON p.status = cs2.id
                    LEFT JOIN package_type pt ON pt.id = p.package_type_id
                    LEFT JOIN common_status cs3 ON pt.status = cs3.id
                    LEFT JOIN tour t ON p.tour_id = t.tour_id
                    LEFT JOIN package_schedule ps ON p.package_id = ps.package_id
                    LEFT JOIN features f ON p.package_id = f.package_id
                    LEFT JOIN package_images pi ON p.package_id = pi.package_id
                    LEFT JOIN common_status cs_package ON p.status = cs_package.id
                    LEFT JOIN common_status cs_tour ON t.status = cs_tour.id
                    WHERE p.package_id=?
            """;


    public static final String GET_PACKAGE_ALL_REVIEWS_DETAILS = """
            SELECT
                pr.id AS review_id,
                ps.package_id AS package_id,
                pr.package_schedule_id,
                pr.name AS review_name,
                pr.review,
                pr.rating,
                pr.description,
                cs_pr.name AS review_status,
                pr.number_of_participate,
                pr.created_by AS review_created_by,
                pr.created_at AS review_created_at,
                pr.updated_by AS review_updated_by,
                pr.updated_at AS review_updated_at,
                pri.id AS image_id,
                pri.name AS image_name,
                pri.description AS image_description,
                pri.image_url AS image_url,
                cs_pri.name AS image_status,
                pri.created_by AS image_created_by,
                pri.created_at AS image_created_at,
                prr.id AS review_reaction_id,
                prr.package_review_id AS reaction_review_id,
                prr.user_id AS reaction_user_id,
                u1.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_prr.name AS review_reaction_status,
                prr.created_at AS reaction_created_at,
                prc.id AS comment_id,
                prc.package_review_id AS comment_review_id,
                prc.user_id AS comment_user_id,
                u2.username AS comment_user_name,
                prc.parent_comment_id,
                prc.comment,
                cs_prc.name AS comment_status,
                prc.created_at AS comment_created_at,
                prc.created_by AS comment_created_by,
                prcr.id AS comment_reaction_id,
                prcr.comment_id AS comment_reaction_comment_id,
                prcr.user_id AS comment_reaction_user_id,
                u3.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_prcr.name AS comment_reaction_status,
                prcr.created_by AS comment_reaction_created_by,
                prcr.created_at AS comment_reaction_created_at
            FROM package_review pr
            LEFT JOIN package_schedule ps
                ON pr.package_schedule_id = ps.id
            LEFT JOIN packages p
                ON ps.package_id = p.package_id
            LEFT JOIN common_status cs_pr
                ON cs_pr.id = pr.status
            LEFT JOIN package_review_images pri
                ON pri.package_review_id = pr.id
            LEFT JOIN common_status cs_pri
                ON cs_pri.id = pri.status
            LEFT JOIN package_review_reaction prr
                ON prr.package_review_id = pr.id
            LEFT JOIN reaction_type rt
                ON rt.id = prr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = prr.user_id
            LEFT JOIN common_status cs_prr
                ON cs_prr.id = prr.status
            LEFT JOIN package_review_comment prc
                ON prc.package_review_id = pr.id
            LEFT JOIN user u2
                ON u2.user_id = prc.user_id
            LEFT JOIN common_status cs_prc
                ON cs_prc.id = prc.status
            LEFT JOIN package_review_comment_reaction prcr
                ON prcr.comment_id = prc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = prcr.reaction_type_id
            LEFT JOIN user u3
                ON u3.user_id = prcr.user_id
            LEFT JOIN common_status cs_prcr
                ON cs_prcr.id = prcr.status
            ORDER BY pr.id, prc.id, prcr.id
            """;

    public static final String GET_PACKAGE_REVIEWS_DETAILS_BY_ID = """
            SELECT
                pr.id AS review_id,
                ps.package_id AS package_id,
                pr.package_schedule_id,
                pr.name AS review_name,
                pr.review,
                pr.rating,
                pr.description,
                cs_pr.name AS review_status,
                pr.number_of_participate,
                pr.created_by AS review_created_by,
                pr.created_at AS review_created_at,
                pr.updated_by AS review_updated_by,
                pr.updated_at AS review_updated_at,
                pri.id AS image_id,
                pri.name AS image_name,
                pri.description AS image_description,
                pri.image_url AS image_url,
                cs_pri.name AS image_status,
                pri.created_by AS image_created_by,
                pri.created_at AS image_created_at,
                prr.id AS review_reaction_id,
                prr.package_review_id AS reaction_review_id,
                prr.user_id AS reaction_user_id,
                u1.username AS reaction_user_name,
                rt.name AS reaction_type,
                cs_prr.name AS review_reaction_status,
                prr.created_at AS reaction_created_at,
                prc.id AS comment_id,
                prc.package_review_id AS comment_review_id,
                prc.user_id AS comment_user_id,
                u2.username AS comment_user_name,
                prc.parent_comment_id,
                prc.comment,
                cs_prc.name AS comment_status,
                prc.created_at AS comment_created_at,
                prc.created_by AS comment_created_by,
                prcr.id AS comment_reaction_id,
                prcr.comment_id AS comment_reaction_comment_id,
                prcr.user_id AS comment_reaction_user_id,
                u3.username AS comment_reaction_user_name,
                rt2.name AS comment_reaction_type,
                cs_prcr.name AS comment_reaction_status,
                prcr.created_by AS comment_reaction_created_by,
                prcr.created_at AS comment_reaction_created_at
            FROM package_review pr
            LEFT JOIN package_schedule ps
                ON pr.package_schedule_id = ps.id
            LEFT JOIN packages p
                ON ps.package_id = p.package_id
            LEFT JOIN common_status cs_pr
                ON cs_pr.id = pr.status
            LEFT JOIN package_review_images pri
                ON pri.package_review_id = pr.id
            LEFT JOIN common_status cs_pri
                ON cs_pri.id = pri.status
            LEFT JOIN package_review_reaction prr
                ON prr.package_review_id = pr.id
            LEFT JOIN reaction_type rt
                ON rt.id = prr.reaction_type_id
            LEFT JOIN user u1
                ON u1.user_id = prr.user_id
            LEFT JOIN common_status cs_prr
                ON cs_prr.id = prr.status
            LEFT JOIN package_review_comment prc
                ON prc.package_review_id = pr.id
            LEFT JOIN user u2
                ON u2.user_id = prc.user_id
            LEFT JOIN common_status cs_prc
                ON cs_prc.id = prc.status
            LEFT JOIN package_review_comment_reaction prcr
                ON prcr.comment_id = prc.id
            LEFT JOIN reaction_type rt2
                ON rt2.id = prcr.reaction_type_id
            LEFT JOIN user u3
                ON u3.user_id = prcr.user_id
            LEFT JOIN common_status cs_prcr
                ON cs_prcr.id = prcr.status
            WHERE p.package_id = ?
            ORDER BY pr.id, prc.id, prcr.id
            """;

    public static final String GET_PACKAGE_HISTORY_DETAILS = """
            SELECT
                ph.id AS package_history_id,
                ph.package_schedule_id,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                ps.duration_start,
                ps.duration_end,
                p.package_id,
                p.name AS package_name,
                p.tour_id,
                ph.number_of_participate,
                ph.rating,
                ph.duration,
                ph.description AS history_description,
                ph.color,
                ph.hover_color,
                ph.start_date,
                ph.end_date,
                ph.created_at AS history_created_at,
                CONCAT(uc.first_name, ' ', IFNULL(uc.middle_name, ''), ' ', IFNULL(uc.last_name, '')) AS created_by_user,
                uc.image_url AS created_by_image,
                ph.updated_at AS history_updated_at,
                CONCAT(uu.first_name, ' ', IFNULL(uu.middle_name, ''), ' ', IFNULL(uu.last_name, '')) AS updated_by_user,
                ph.terminated_at AS history_terminated_at,
                CONCAT(ut.first_name, ' ', IFNULL(ut.middle_name, ''), ' ', IFNULL(ut.last_name, '')) AS terminated_by_user,
                COALESCE(
                    JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'image_id', thi.id,
                            'name', thi.name,
                            'description', thi.description,
                            'image_url', thi.image_url,
                            'color', thi.color
                        )
                    ), JSON_ARRAY()
                ) AS images
            FROM package_history ph
            JOIN package_schedule ps ON ph.package_schedule_id = ps.id
            JOIN packages p ON ps.package_id = p.package_id
            LEFT JOIN tour_schedule ts ON ts.tour_id = p.tour_id
            LEFT JOIN tour_history_images thi ON thi.tour_schedule_id = ts.id
            LEFT JOIN user uc ON ph.created_by = uc.user_id
            LEFT JOIN user uu ON ph.updated_by = uu.user_id
            LEFT JOIN user ut ON ph.terminated_by = ut.user_id
            GROUP BY ph.id
            ORDER BY ph.created_at DESC
            """;

    public static final String GET_PACKAGE_HISTORY_DETAILS_BY_ID = """
            SELECT
                ph.id AS package_history_id,
                ph.package_schedule_id,
                ps.name AS package_schedule_name,
                ps.assume_start_date,
                ps.assume_end_date,
                ps.duration_start,
                ps.duration_end,
                p.package_id,
                p.name AS package_name,
                p.tour_id,
                ph.number_of_participate,
                ph.rating,
                ph.duration,
                ph.description AS history_description,
                ph.color,
                ph.hover_color,
                ph.start_date,
                ph.end_date,
                ph.created_at AS history_created_at,
                CONCAT(uc.first_name, ' ', IFNULL(uc.middle_name, ''), ' ', IFNULL(uc.last_name, '')) AS created_by_user,
                uc.image_url AS created_by_image,
                ph.updated_at AS history_updated_at,
                CONCAT(uu.first_name, ' ', IFNULL(uu.middle_name, ''), ' ', IFNULL(uu.last_name, '')) AS updated_by_user,
                ph.terminated_at AS history_terminated_at,
                CONCAT(ut.first_name, ' ', IFNULL(ut.middle_name, ''), ' ', IFNULL(ut.last_name, '')) AS terminated_by_user,
                COALESCE(
                    JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'image_id', thi.id,
                            'name', thi.name,
                            'description', thi.description,
                            'image_url', thi.image_url,
                            'color', thi.color
                        )
                    ), JSON_ARRAY()
                ) AS images
            FROM package_history ph
            JOIN package_schedule ps ON ph.package_schedule_id = ps.id
            JOIN packages p ON ps.package_id = p.package_id
            LEFT JOIN tour_schedule ts ON ts.tour_id = p.tour_id
            LEFT JOIN tour_history_images thi ON thi.tour_schedule_id = ts.id
            LEFT JOIN user uc ON ph.created_by = uc.user_id
            LEFT JOIN user uu ON ph.updated_by = uu.user_id
            LEFT JOIN user ut ON ph.terminated_by = ut.user_id
            GROUP BY ph.id
            HAVING p.package_id = ?
            ORDER BY ph.created_at DESC
            """;

    public static final String GET_PACKAGE_HISTORY_IMAGES = """
            SELECT
                thi.id AS image_id,
                thi.name AS image_name,
                thi.description AS image_description,
                thi.image_url,
                thi.color,
                cs.name AS image_status_name,
                ps.id AS package_schedule_id,
                ps.name AS package_schedule_name,
                p.package_id,
                p.name AS package_name,
                p.tour_id,
                thi.created_at AS created_at,
                CONCAT(uc.first_name, ' ', IFNULL(uc.middle_name, ''), ' ', IFNULL(uc.last_name, '')) AS created_by_user,
                uc.image_url AS created_by_image
            FROM tour_history_images thi
            JOIN tour_schedule ts ON thi.tour_schedule_id = ts.id
            JOIN packages p ON ts.tour_id = p.tour_id
            JOIN package_schedule ps ON ps.package_id = p.package_id
            LEFT JOIN common_status cs ON thi.status = cs.id
            LEFT JOIN user uc ON thi.created_by = uc.user_id
            ORDER BY thi.created_at DESC
            """;

    public static final String GET_PACKAGE_HISTORY_IMAGES_BY_ID = """
            SELECT
                thi.id AS image_id,
                thi.name AS image_name,
                thi.description AS image_description,
                thi.image_url,
                thi.color,
                cs.name AS image_status_name,
                ps.id AS package_schedule_id,
                ps.name AS package_schedule_name,
                p.package_id,
                p.name AS package_name,
                p.tour_id,
                thi.created_at AS created_at,
                CONCAT(uc.first_name, ' ', IFNULL(uc.middle_name, ''), ' ', IFNULL(uc.last_name, '')) AS created_by_user,
                uc.image_url AS created_by_image
            FROM tour_history_images thi
            JOIN tour_schedule ts ON thi.tour_schedule_id = ts.id
            JOIN packages p ON ts.tour_id = p.tour_id
            JOIN package_schedule ps ON ps.package_id = p.package_id
            LEFT JOIN common_status cs ON thi.status = cs.id
            LEFT JOIN user uc ON thi.created_by = uc.user_id
            WHERE p.package_id = ?
            ORDER BY thi.created_at DESC
            """;

}
