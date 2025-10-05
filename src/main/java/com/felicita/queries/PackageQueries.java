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
            FROM packages p
            LEFT JOIN common_status cs2 ON p.status = cs2.id
            LEFT JOIN tour t ON p.tour_id = t.tour_id
            LEFT JOIN package_schedule ps ON p.package_id = ps.package_id
            LEFT JOIN features f ON p.package_id = f.package_id
            LEFT JOIN package_images pi ON p.package_id = pi.package_id
            LEFT JOIN common_status cs_package ON p.status = cs_package.id
            LEFT JOIN common_status cs_tour ON t.status = cs_tour.id
""";


}
