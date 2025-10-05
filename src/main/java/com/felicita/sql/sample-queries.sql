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
    MAX(cs.name) AS status_name,  -- Changed: Use MAX() and give it an alias
    a.created_at,
    a.updated_at,
    
    -- Activity Category Details (using MAX or ANY_VALUE to make them aggregated)
    MAX(ac.name) AS category_name,
    MAX(ac.description) AS category_description,
    
    -- Aggregated Schedules as JSON (handle NULL case)
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
    
    -- Aggregated Requirements as JSON
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
    
    -- Aggregated Images as JSON
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
LEFT JOIN common_status cs ON a.status = cs.id  -- FIXED: Changed from a.id to cs.id
LEFT JOIN activity_category ac ON a.activities_category = ac.name
LEFT JOIN activities_schedule asch ON asch.activity_id = a.id 
    AND asch.terminated_at IS NULL
WHERE a.terminated_at IS NULL
GROUP BY a.id, a.destination_id, a.name, a.description, a.activities_category, 
         a.duration_hours, a.available_from, a.available_to, a.price_local, 
         a.price_foreigners, a.min_participate, a.max_participate, a.season, 
         a.status, a.created_at, a.updated_at
LIMIT 0, 1000;



SELECT 
    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location,
    d.latitude,
    d.longitude,
    dc.category AS category_name,
    dc.description AS category_description,
    cs.name AS status_name,
    a.id AS activity_id,
    a.name AS activity_name,
    a.description AS activity_description,
    a.activities_category,
    a.duration_hours,
    a.available_from,
    a.available_to,
    a.price_local,
    a.price_foreigners,
    a.min_participate,
    a.max_participate,
    a.season,
    di.id AS image_id,
    di.name AS image_name,
    di.description AS image_description,
    di.image_url
FROM destination d
LEFT JOIN destination_categories dc ON d.destination_category = dc.id
LEFT JOIN common_status cs ON d.status = cs.id
LEFT JOIN activities a ON d.destination_id = a.destination_id
LEFT JOIN destination_images di ON d.destination_id = di.destination_id;



SELECT 
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    t.duration,
    t.latitude,
    t.longitude,
    t.start_location,
    t.end_location,
    tt.name AS tour_type_name,
    tt.description AS tour_type_description,
    tc.name AS tour_category_name,
    tc.description AS tour_category_description,
    s.name AS season_name,
    s.description AS season_description,
    cs.name AS status_name,
    sch.id AS schedule_id,
    sch.name AS schedule_name,
    sch.assume_start_date,
    sch.assume_end_date,
    sch.duration_start,
    sch.duration_end,
    sch.special_note,
    sch.description AS schedule_description,
    img.id AS image_id,
    img.name AS image_name,
    img.description AS image_description,
    img.image_url
FROM tour t
LEFT JOIN tour_type tt ON t.tour_type = tt.id
LEFT JOIN tour_category tc ON t.tour_category = tc.id
LEFT JOIN seasons s ON t.season = s.id
LEFT JOIN common_status cs ON t.status = cs.id
LEFT JOIN tour_schedule sch ON t.tour_id = sch.tour_id
LEFT JOIN tour_images img ON t.tour_id = img.tour_id;




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
    p.status AS package_status,
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
LEFT JOIN tour t ON p.tour_id = t.tour_id
LEFT JOIN package_schedule ps ON p.package_id = ps.package_id
LEFT JOIN features f ON p.package_id = f.package_id
LEFT JOIN package_images pi ON p.package_id = pi.package_id
LEFT JOIN common_status cs_package ON p.status = cs_package.id
LEFT JOIN common_status cs_tour ON t.status = cs_tour.id;














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
                cs.name AS package_status,
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
            LEFT JOIN common_status cs_tour ON t.status = cs_tour.id;


use travelagencydb;

SELECT 
    dc.id AS category_id,
    dc.category,
    dc.description AS category_description,
    cs.name AS category_status,
    dc.created_at,
    dc.updated_at,
    dci.id AS image_id,
    dci.name AS image_name,
    dci.description AS image_description,
    dci.image_url,
    cs_img.name AS image_status,
    dci.created_at AS image_created_at
FROM destination_categories dc
JOIN common_status cs 
    ON dc.status = cs.id
LEFT JOIN destination_categories_images dci 
    ON dci.destination_categories_id = dc.id
LEFT JOIN common_status cs_img 
    ON dci.status = cs_img.id
ORDER BY dc.id, dci.id;




SELECT 
    pd.id AS popular_id,
    pd.rating,
    pd.popularity,
    pd.created_at AS popular_created_at,

    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location,
    d.latitude,
    d.longitude,
    cs_dest.name AS destination_status,

    dc.id AS category_id,
    dc.category AS category_name,
    dc.description AS category_description,
    cs_cat.name AS category_status,

    di.id AS image_id,
    di.name AS image_name,
    di.description AS image_description,
    di.image_url,
    cs_img.name AS image_status

FROM popular_destination pd
JOIN destination d 
    ON pd.destination_id = d.destination_id
JOIN common_status cs_dest 
    ON d.status = cs_dest.id
LEFT JOIN destination_categories dc 
    ON d.destination_category = dc.id
LEFT JOIN common_status cs_cat 
    ON dc.status = cs_cat.id
LEFT JOIN destination_images di 
    ON d.destination_id = di.destination_id
LEFT JOIN common_status cs_img 
    ON di.status = cs_img.id

ORDER BY pd.popularity DESC, pd.rating DESC;


SELECT 
    pd.id AS popular_id,
    pd.rating,
    pd.popularity,
    pd.created_at AS popular_created_at,

    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location,
    d.latitude,
    d.longitude,
    cs_dest.name AS destination_status,

    dc.id AS category_id,
    dc.category AS category_name,
    dc.description AS category_description,
    cs_cat.name AS category_status,

    di.id AS image_id,
    di.name AS image_name,
    di.description AS image_description,
    di.image_url,
    cs_img.name AS image_status,
    di.created_at AS image_created_at,

    a.id AS activity_id,
    a.name AS activity_name,
    a.description AS activity_description,
    a.activities_category,
    a.duration_hours,
    a.available_from,
    a.available_to,
    a.price_local,
    a.price_foreigners,
    a.min_participate,
    a.max_participate,
    a.season,
    cs_act.name AS activity_status,
    a.created_at AS activity_created_at

FROM popular_destination pd
JOIN destination d ON pd.destination_id = d.destination_id
JOIN common_status cs_dest ON d.status = cs_dest.id
LEFT JOIN destination_categories dc ON d.destination_category = dc.id
LEFT JOIN common_status cs_cat ON dc.status = cs_cat.id
LEFT JOIN destination_images di ON d.destination_id = di.destination_id
LEFT JOIN common_status cs_img ON di.status = cs_img.id
LEFT JOIN activities a ON d.destination_id = a.destination_id
LEFT JOIN common_status cs_act ON a.status = cs_act.id

WHERE pd.rating > 4.0

ORDER BY pd.popularity DESC, pd.rating DESC;



SELECT
    t.tour_id,
    t.name AS tour_name,
    t.description AS tour_description,
    t.duration AS tour_duration,
    t.latitude,
    t.longitude,
    t.start_location,
    t.end_location,
    tt.name AS tour_type,
    tc.name AS tour_category,
    s.name AS season,
    cs_t.name AS tour_status,
    ts.id AS schedule_id,
    ts.name AS schedule_name,
    ts.assume_start_date,
    ts.assume_end_date,
    ts.duration_start,
    ts.duration_end,
    ts.special_note,
    ts.description AS schedule_description,
    cs_ts.name AS schedule_status,
    d.destination_id,
    d.name AS destination_name,
    d.description AS destination_description,
    d.location AS destination_location,
    cs_dest.name AS destination_status,
    tr.id AS review_id,
    tr.name AS reviewer_name,
    tr.review,
    tr.rating,
    tr.description AS review_description,
    tr.number_of_participate,
    cs_tr.name AS review_status,
    tr.created_at AS review_created_at
FROM tour t
LEFT JOIN tour_type tt ON t.tour_type = tt.id
LEFT JOIN tour_category tc ON t.tour_category = tc.id
LEFT JOIN seasons s ON t.season = s.id
LEFT JOIN common_status cs_t ON t.status = cs_t.id
LEFT JOIN tour_schedule ts ON t.tour_id = ts.tour_id
LEFT JOIN common_status cs_ts ON ts.status = cs_ts.id
LEFT JOIN tour_destination td ON t.tour_id = td.tour_id
LEFT JOIN destination d ON td.destination_id = d.destination_id
LEFT JOIN common_status cs_dest ON d.status = cs_dest.id
LEFT JOIN tour_review tr ON ts.id = tr.tour_schedule_id AND tr.rating > 4.0
LEFT JOIN common_status cs_tr ON tr.status = cs_tr.id
WHERE tr.id IS NOT NULL  -- ensures only tours with reviews > 4.0
ORDER BY tr.rating DESC, t.tour_id;


SELECT 
    ac.id AS category_id,
    ac.name AS category_name,
    ac.description AS category_description,
    ac.status AS category_status,
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
ORDER BY ac.id, aci.id;



