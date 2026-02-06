package com.felicita.queries;

public class UserProfileQueries {

    public static final String GET_USER_PROFILE_DETAILS = """
            SELECT
                u.user_id,
                u.username,
                u.first_name,
                u.middle_name,
                u.last_name,
                u.nic,
                u.passport_number,
                u.driving_license_number,
                u.email,
                u.mobile_number1,
                u.mobile_number2,
                u.date_of_birth,
                u.image_url,
                u.created_at,
                u.updated_at,
                u.benefits_points_count,
                a.number AS address_number,
                a.address_line1,
                a.address_line2,
                a.city,
                a.district,
                a.province,
                c_country.name AS country_name,
                a.postal_code,
                g.name AS gender,
                r.name AS religion,
                ut.name AS user_type,
                ut.description AS user_type_description,
                us.name AS user_status,
                us.description AS user_status_description,
                w.wallet_number,
                w.amount,
                ws.name AS wallet_status
            FROM user u
            LEFT JOIN address a ON u.address_id = a.address_id
            LEFT JOIN country c_country ON a.country_id = c_country.country_id
            LEFT JOIN gender g ON u.gender_id = g.gender_id
            LEFT JOIN religion r ON u.religion_id = r.religion_id
            LEFT JOIN user_type ut ON u.user_type_id = ut.user_type_id
            LEFT JOIN user_status us ON u.user_status_id = us.user_status_id
            LEFT JOIN wallet w ON u.wallet_id = w.wallet_id
            LEFT JOIN wallet_status ws ON w.wallet_status_id = ws.wallet_status_id
            WHERE u.user_id = ?
            """;

    public static final String GET_USER_PROFILE_SIDEBAR = """
            SELECT
                ups.id,
                ups.parent_id,
                ups.name,
                ups.description,
                p.name AS privilege_name,
                cs.name AS status_name,
                ups.url
            FROM user_profile_sidebar ups
            LEFT JOIN privileges p ON ups.privilege_id = p.id
            LEFT JOIN common_status cs ON ups.status_id = cs.id
            ORDER BY
                COALESCE(ups.parent_id, ups.id),
                ups.id
            """;

    public static final String GET_USER_PROFILE_REVIEWS = """
            SELECT
                tr.id AS review_id,
                tr.name AS reviewer_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                tr.number_of_participate,
                tr.created_at AS review_created_at,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.start_location,
                t.end_location,
                u.user_id,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email AS user_email,
                cs.name AS review_status,
                tri.id AS image_id,
                tri.name AS image_name,
                tri.description AS image_description,
                tri.image_url AS image_url
            FROM tour_review tr
            INNER JOIN tour_schedule ts
                ON tr.tour_schedule_id = ts.id
            INNER JOIN tour t
                ON ts.tour_id = t.tour_id
            LEFT JOIN user u
                ON tr.created_by = u.user_id
            LEFT JOIN tour_review_images tri
                ON tr.id = tri.tour_review_id
            LEFT JOIN common_status cs
                ON tr.status = cs.id
            WHERE tr.status = 1
            AND u.user_id = ?
            ORDER BY tr.created_at DESC, tri.id
            """;

    public static final String GET_USER_PROFILE_PACKAGE_REVIEWS = """
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
            			WHERE pr.created_by = ?
                        ORDER BY pr.id, prc.id, prcr.id
            """;

    public static final String GET_USER_PROFILE_DESTIANTION_REVIEWS = """
            SELECT
                            dr.review_id,
                            dr.destination_id,
                            d.name AS destination_name,
                            dr.user_id AS review_user_id,
                            u1.username AS review_user_name,
                            dr.review_text AS review_text,
                            dr.rating AS review_rating,
                            cs_dr.name AS review_status,
                            dr.created_by AS review_created_by,
                            dr.created_at AS review_created_at,
                            dr.updated_by AS review_updated_by,
                            dr.updated_at AS review_updated_at,
                            dri.image_id,
                            dri.name AS image_name,
                            dri.description AS image_description,
                            dri.image_url AS image_url,
                            cs_dri.name AS image_status,
                            dri.created_by AS image_created_by,
                            dri.created_at AS image_created_at,
                            drr.review_reaction_id,
                            drr.review_id AS reaction_review_id,
                            drr.user_id AS reaction_user_id,
                            u2.username AS reaction_user_name,
                            rt.name AS reaction_type,
                            cs_drr.name AS review_reaction_status,
                            drr.created_at AS reaction_created_at,
                            drc.comment_id,
                            drc.review_id AS comment_review_id,
                            drc.user_id AS comment_user_id,
                            u3.username AS comment_user_name,
                            drc.parent_comment_id,
                            drc.comment_text AS comment_text,
                            cs_drc.name AS comment_status,
                            drc.created_at AS comment_created_at,
                            drc.created_by AS comment_created_by,
                            drcr.comment_reaction_id,
                            drcr.comment_id AS comment_reaction_comment_id,
                            drcr.user_id AS comment_reaction_user_id,
                            u4.username AS comment_reaction_user_name,
                            rt2.name AS comment_reaction_type,
                            cs_drcr.name AS comment_reaction_status,
                            drcr.created_by AS comment_reaction_created_by,
                            drcr.created_at AS comment_reaction_created_at
                        FROM destination_review dr
                        LEFT JOIN destination d
                            ON dr.destination_id = d.destination_id
                        LEFT JOIN common_status cs_dr
                            ON cs_dr.id = dr.status
                        LEFT JOIN destination_review_images dri
                            ON dri.review_id = dr.review_id
                        LEFT JOIN common_status cs_dri
                            ON cs_dri.id = dri.status
                        LEFT JOIN destination_review_reaction drr
                            ON drr.review_id = dr.review_id
                        LEFT JOIN reaction_type rt
                            ON rt.id = drr.reaction_type_id
                        LEFT JOIN user u1
                            ON u1.user_id = dr.user_id
                        LEFT JOIN user u2
                            ON u2.user_id = drr.user_id
                        LEFT JOIN common_status cs_drr
                            ON cs_drr.id = drr.status
                        LEFT JOIN destination_review_comment drc
                            ON drc.review_id = dr.review_id
                        LEFT JOIN user u3
                            ON u3.user_id = drc.user_id
                        LEFT JOIN common_status cs_drc
                            ON cs_drc.id = drc.status
                        LEFT JOIN destination_review_comment_reaction drcr
                            ON drcr.comment_id = drc.comment_id
                        LEFT JOIN reaction_type rt2
                            ON rt2.id = drcr.reaction_type_id
                        LEFT JOIN user u4
                            ON u4.user_id = drcr.user_id
                        LEFT JOIN common_status cs_drcr
                            ON cs_drcr.id = drcr.status
            			WHERE u1.user_id = ?
                        ORDER BY dr.review_id, drc.comment_id, drcr.comment_reaction_id
            """;

    public static final String GET_USER_PROFILE_ACTIVITIES_REVIEWS = """
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
            WHERE ar.created_by = ?
            ORDER BY ar.id, arc.id, arcr.id
            """;

    public static final String GET_USER_PROFILE_TOUR_REVIEWS = """
            SELECT
                tr.id AS review_id,
                tr.name AS reviewer_name,
                tr.review,
                tr.rating,
                tr.description AS review_description,
                tr.number_of_participate,
                tr.created_at AS review_created_at,
                ts.id AS schedule_id,
                ts.name AS schedule_name,
                ts.assume_start_date,
                ts.assume_end_date,
                t.tour_id,
                t.name AS tour_name,
                t.description AS tour_description,
                t.start_location,
                t.end_location,
                u.user_id,
                CONCAT(u.first_name, ' ', u.last_name) AS user_full_name,
                u.email AS user_email,
                cs.name AS review_status,
                tri.id AS image_id,
                tri.name AS image_name,
                tri.description AS image_description,
                tri.image_url AS image_url
            FROM tour_review tr
            INNER JOIN tour_schedule ts
                ON tr.tour_schedule_id = ts.id
            INNER JOIN tour t
                ON ts.tour_id = t.tour_id
            LEFT JOIN user u
                ON tr.created_by = u.user_id
            LEFT JOIN tour_review_images tri
                ON tr.id = tri.tour_review_id
            LEFT JOIN common_status cs
                ON tr.status = cs.id
            WHERE tr.status = 1
            AND u.user_id = ?
            ORDER BY tr.created_at DESC, tri.id
            """;

    public static final String GET_USER_PROFILE_WALLET = """
            SELECT
                u.user_id,
                u.username,
                u.first_name,
                u.last_name,
                w.wallet_id,
                w.wallet_number,
                w.amount,
                ws.wallet_status_id,
                ws.name AS wallet_status_name,
                ws.description AS wallet_status_description,
                w.created_at AS wallet_created_at,
                w.updated_at AS wallet_updated_at
            FROM user u
            LEFT JOIN wallet w ON u.wallet_id = w.wallet_id
            LEFT JOIN wallet_status ws ON w.wallet_status_id = ws.wallet_status_id
            WHERE u.user_id = ?
            """;

    public static final String UPDATE_USER_PROFILE_DETAILS = """
                UPDATE user
                SET
                    first_name = ?,
                    middle_name = ?,
                    last_name = ?,
                    address_id = ?,
                    nic = ?,
                    gender_id = ?,
                    passport_number = ?,
                    driving_license_number = ?,
                    email = ?,
                    email2 = ?,
                    mobile_number1 = ?,
                    mobile_number2 = ?,
                    region_id = ?,
                    religion_id = ?,
                    date_of_birth = ?,
                    image_url = ?,
                    updated_at = CURRENT_TIMESTAMP
                WHERE user_id = ?
            """;


}
