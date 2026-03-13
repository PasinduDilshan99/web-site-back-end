package com.felicita.queries;

public class SeasonQueries {

    public static final String GET_SEASON_DETAILS_BY_SEASON_ID = """
            SELECT
                s.id,
                s.name,
                s.standard_name,
                s.local_name,
                s.start_month,
                s.end_month,
                s.monsoon_type,
                s.weather_summary,
                s.temperature_min,
                s.temperature_max,
                s.rainfall_pattern,
                s.is_peak,
                s.display_order,
                s.description,
                s.status,
                s.created_at,
                s.created_by,
                s.updated_at,
                s.updated_by,
            
                si.id AS image_id,
                si.name AS image_name,
                si.description AS image_description,
                si.image_url,
                si.status AS image_status,
                si.created_at AS image_created_at,
                si.created_by AS image_created_by,
                si.updated_at AS image_updated_at,
                si.updated_by AS image_updated_by
            
            FROM seasons s
            LEFT JOIN seasons_images si ON s.id = si.season_id
            WHERE s.id = ?
            AND s.terminated_at IS NULL
            AND (si.terminated_at IS NULL OR si.id IS NULL)
            """;

    public static final String GET_ALL_SEASONS_BASIC = """
            SELECT
                s.id,
                s.name,
                s.standard_name,
                s.local_name,
                s.start_month,
                s.end_month,
                s.is_peak,
                s.display_order,
            
                si.id AS image_id,
                si.name AS image_name,
                si.image_url
            
            FROM seasons s
            LEFT JOIN seasons_images si 
                ON s.id = si.season_id
                AND si.terminated_at IS NULL
                AND si.status = 1
            
            WHERE s.terminated_at IS NULL
            AND s.status = 1
            
            ORDER BY s.display_order ASC
            """;

}