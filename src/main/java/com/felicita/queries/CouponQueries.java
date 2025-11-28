package com.felicita.queries;

public class CouponQueries {

    public static final String GET_ALL_COUPONS_DETAILS = """
            SELECT
                c.id AS coupon_id,
                c.coupon_code,
                c.name AS coupon_name,
                c.description AS coupon_description,
                ct.name AS coupon_type,
                cs.name AS coupon_status,
                c.discount_type,
                c.discount_value,
                c.minimum_cart_value,
                c.maximum_discount,
                ca.name AS applicable_for,
                c.valid_from,
                c.valid_until,
                c.usage_limit_per_coupon,
                c.usage_limit_per_user,
                c.total_usage_count,
                c.created_at AS coupon_created_at,
                p.package_id,
                p.name AS package_name,
                p.total_price AS package_price,
                ut.user_type_id,
                ut.name AS user_type_name,
                crt.name AS rule_type,
                cr.operator AS rule_operator,
                cr.rule_value AS rule_value
            FROM coupon c
            LEFT JOIN coupon_type ct ON c.coupon_type_id = ct.id
            LEFT JOIN common_status cs ON c.status_id = cs.id
            LEFT JOIN coupon_applicable ca ON c.applicable_id = ca.id
            LEFT JOIN coupon_applicable_package cap ON c.id = cap.coupon_id AND cap.status_id = 1
            LEFT JOIN packages p ON cap.package_id = p.package_id
            LEFT JOIN coupon_applicable_user_type caut ON c.id = caut.coupon_id AND caut.status_id = 1
            LEFT JOIN user_type ut ON caut.user_type_id = ut.user_type_id
            LEFT JOIN coupon_rule cr ON c.id = cr.coupon_id AND cr.status_id = 1
            LEFT JOIN coupon_rule_type crt ON cr.coupon_rule_type_id = crt.id
            ORDER BY c.id, p.package_id, ut.user_type_id, crt.name
            """;

    public static final String GET_ALL_COUPONS_DETAILS_BY_ID = """
            SELECT
                c.id AS coupon_id,
                c.coupon_code,
                c.name AS coupon_name,
                c.description AS coupon_description,
                ct.name AS coupon_type,
                cs.name AS coupon_status,
                c.discount_type,
                c.discount_value,
                c.minimum_cart_value,
                c.maximum_discount,
                ca.name AS applicable_for,
                c.valid_from,
                c.valid_until,
                c.usage_limit_per_coupon,
                c.usage_limit_per_user,
                c.total_usage_count,
                c.created_at AS coupon_created_at,
                p.package_id,
                p.name AS package_name,
                p.total_price AS package_price,
                ut.user_type_id,
                ut.name AS user_type_name,
                crt.name AS rule_type,
                cr.operator AS rule_operator,
                cr.rule_value AS rule_value
            FROM coupon c
            LEFT JOIN coupon_type ct ON c.coupon_type_id = ct.id
            LEFT JOIN common_status cs ON c.status_id = cs.id
            LEFT JOIN coupon_applicable ca ON c.applicable_id = ca.id
            LEFT JOIN coupon_applicable_package cap ON c.id = cap.coupon_id AND cap.status_id = 1
            LEFT JOIN packages p ON cap.package_id = p.package_id
            LEFT JOIN coupon_applicable_user_type caut ON c.id = caut.coupon_id AND caut.status_id = 1
            LEFT JOIN user_type ut ON caut.user_type_id = ut.user_type_id
            LEFT JOIN coupon_rule cr ON c.id = cr.coupon_id AND cr.status_id = 1
            LEFT JOIN coupon_rule_type crt ON cr.coupon_rule_type_id = crt.id
            WHERE c.id = ?
            ORDER BY c.id, p.package_id, ut.user_type_id, crt.name
            """;

    public static final String GET_ALL_COUPONS_DETAILS_BY_USER = """
            SELECT
                cua.id AS allocation_id,
                cua.user_id,
                u.username,
                CONCAT(u.first_name, ' ', COALESCE(u.middle_name, ''), ' ', u.last_name) AS user_full_name,
            	c.id AS coupon_id,
                c.coupon_code,
                c.name AS coupon_name,
                c.description AS coupon_description,
                ct.name AS coupon_type,
            	cs.name AS allocation_status,
                cs.status_category,
                cs.description AS status_description,
            	c.discount_type,
                c.discount_value,
                CASE
                    WHEN c.discount_type = 'percentage' THEN CONCAT(c.discount_value, '%')
                    ELSE CONCAT('$', c.discount_value)
                END AS discount_display,
                c.minimum_cart_value,
                c.maximum_discount,
            	cua.allocated_at,
                cua.expires_at,
                cua.used_at,
                c.valid_from AS coupon_valid_from,
                c.valid_until AS coupon_valid_until,
            	CASE
                    WHEN cua.used_at IS NOT NULL THEN 'USED'
                    WHEN cua.expires_at < NOW() THEN 'EXPIRED'
                    WHEN c.valid_until < NOW() THEN 'COUPON_EXPIRED'
                    WHEN c.valid_from > NOW() THEN 'NOT_YET_VALID'
                    WHEN c.usage_limit_per_coupon IS NOT NULL AND c.total_usage_count >= c.usage_limit_per_coupon THEN 'COUPON_LIMIT_REACHED'
                    WHEN cs.status_category = 'cancelled' THEN 'CANCELLED'
                    ELSE 'ACTIVE'
                END AS effective_status,
                DATEDIFF(cua.expires_at, NOW()) AS days_remaining,
                c.usage_limit_per_user,
                c.usage_limit_per_coupon,
                c.total_usage_count,
                ca.name AS applicable_for,
                GROUP_CONCAT(DISTINCT p.name) AS applicable_packages
            FROM coupon_user_allocation cua
            INNER JOIN user u ON cua.user_id = u.user_id
            INNER JOIN coupon c ON cua.coupon_id = c.id
            INNER JOIN coupon_status cs ON cua.coupon_status_id = cs.id
            LEFT JOIN coupon_type ct ON c.coupon_type_id = ct.id
            LEFT JOIN coupon_applicable ca ON c.applicable_id = ca.id
            LEFT JOIN coupon_applicable_package cap ON c.id = cap.coupon_id AND cap.status_id = 1
            LEFT JOIN packages p ON cap.package_id = p.package_id
            WHERE cua.user_id = ?
            GROUP BY cua.id
            ORDER BY
                CASE
                    WHEN cua.used_at IS NOT NULL THEN 4
                    WHEN cua.expires_at < NOW() THEN 3
                    WHEN cs.status_category = 'cancelled' THEN 2
                    ELSE 1
                END,
                cua.expires_at ASC
            """;

}
