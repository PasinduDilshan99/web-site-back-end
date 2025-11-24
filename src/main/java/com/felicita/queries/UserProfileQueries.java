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
                cs.name AS status_name
            FROM user_profile_sidebar ups
            LEFT JOIN privileges p ON ups.privilege_id = p.id
            LEFT JOIN common_status cs ON ups.status_id = cs.id
            ORDER BY
                COALESCE(ups.parent_id, ups.id),
                ups.id
            """;
}
