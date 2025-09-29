package com.felicita.queries;

public class PackageQueries {

    public static final String GET_ALL_PACKAGES = """
            SELECT
                            p.package_id,
                            p.name AS package_name,
                            p.description AS package_description,
                            p.total_price,
                            p.discount_percentage,
                            p.start_date AS package_start_date,
                            p.end_date AS package_end_date,
                            p.color,
                            p.hover_color,
                            p.min_person_count,
                            p.max_person_count,
                            ps.name AS package_status,
                            pt.id AS package_type_id,
                            pt.name AS package_type_name,
                            pt.description AS package_type_description,
                            pt_status.name AS package_type_status,
                            t.tour_id,
                            t.name AS tour_name,
                            t.description AS tour_description,
                            t.start_date AS tour_start_date,
                            t.end_date AS tour_end_date,
                            t.duration_days,
                            t.max_people AS tour_max_people,
                            t.min_people AS tour_min_people,
                            t.price_per_person,
                            ts.name AS tour_status,
                            pi.id AS image_id,
                            pi.image_url,
                            pi_status.name AS image_status,
                            d.name AS destiantion_name,
                            d.description AS destiantion_description
                        FROM package p
                        INNER JOIN tour t ON p.tour_id = t.tour_id
                        INNER JOIN common_status ps ON p.package_status_id = ps.id
                        INNER JOIN common_status ts ON t.tour_status_id = ts.id
                        LEFT JOIN package_type pt ON pt.id = p.package_type_id
                        LEFT JOIN common_status pt_status ON pt.status_id = pt_status.id
                        LEFT JOIN package_image pi ON pi.package_id = p.package_id
                        LEFT JOIN common_status pi_status ON pi.status_id = pi_status.id
                        LEFT JOIN tour_destination td ON td.tour_id = t.tour_id
                        LEFT JOIN destination d ON td.destination_id = d.id
                        ORDER BY p.package_id, pi.id
            """;

}
