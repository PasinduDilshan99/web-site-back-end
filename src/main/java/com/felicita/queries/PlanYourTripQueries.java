package com.felicita.queries;

public class PlanYourTripQueries {

    public static final String GET_ALL_DESTINATIONS_FOR_PLAN_YOUR_TRIP = """
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
                di.image_url,
                nd.id AS nearby_id,
                nd.distance_km,
                nd_d.destination_id AS nearby_destination_id,
                nd_d.name AS nearby_destination_name,
                nd_d.location AS nearby_destination_location,
                nd_d.latitude AS nearby_destination_latitude,
                nd_d.longitude AS nearby_destination_longitude
            FROM destination d
            LEFT JOIN destination_categories dc
                ON d.destination_category = dc.id
            LEFT JOIN common_status cs
                ON d.status = cs.id
            LEFT JOIN activities a
                ON d.destination_id = a.destination_id
            LEFT JOIN destination_images di
                ON d.destination_id = di.destination_id
            LEFT JOIN nearby_destinations nd
                ON d.destination_id = nd.destination_id
            LEFT JOIN destination nd_d
                ON nd.nearby_destination_id = nd_d.destination_id;
            
            """;

    public static final String GET_ALL_DESTINATION_FOR_PLAN_YOUR_TRIP = """
            SELECT
            	d.destination_id,
                d.name,
                d.description,
                dc.category,
                d.latitude,
                d.longitude
            FROM destination d
            LEFT JOIN destination_categories dc
            ON d.destination_category = dc.id
        """;

    public static final String GET_ALL_PLAN_YOUR_TRIP_ACTIVITIES = """
            SELECT
            	d.destination_id,
                a.name,
                a.description,
                a.activities_category,
                a.available_from,
                a.available_to,
                a.duration_hours,
                a.price_local,
                a.price_foreigners,
                a.min_participate,
                a.max_participate
            FROM destination d
            LEFT JOIN activities a
            ON d.destination_id = a.destination_id;
            
        """;

    public static final String GET_ALL_PLAN_YOUR_TRIP_NEAR_DESTINATIONS= """
            SELECT
                destination_id,
                GROUP_CONCAT(nearby_destination_id ORDER BY nearby_destination_id) AS nearby_destinations
            FROM nearby_destinations
            GROUP BY destination_id
            ORDER BY destination_id
        """;



}
