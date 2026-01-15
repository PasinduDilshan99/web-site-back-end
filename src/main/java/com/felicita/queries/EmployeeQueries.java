package com.felicita.queries;

public class EmployeeQueries {

    public static final String GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS = """
            SELECT
                e.id as employee_id,
                e.employee_code,
                CONCAT(u.first_name, ' ', COALESCE(u.middle_name, ''), ' ', u.last_name) as full_name,
                u.email,
                u.image_url,
                u.mobile_number1 as phone,
                u.date_of_birth,
                et.type_name as employee_type,
                ed.department_name,
                edg.designation_name,
                e.hire_date,
                e.work_location,
                e.salary,
                smp.platform_name,
                esm.username,
                esm.profile_url,
                esm.is_primary,
                esm.is_public,
                esm.verified,
                esm.follower_count
            FROM employees e
            JOIN user u ON e.user_id = u.user_id
            JOIN employee_types et ON e.employee_type_id = et.id
            JOIN employee_departments ed ON e.department_id = ed.id
            JOIN employee_designations edg ON e.designation_id = edg.id
            LEFT JOIN employee_social_media esm ON e.id = esm.employee_id
            LEFT JOIN social_media_platforms smp ON esm.platform_id = smp.id
            WHERE e.status_id = 1
            ORDER BY RAND()
            """;

    public static final String GET_EMPLOYEES_GUIDE_DETAILS = """ 
            SELECT e.id as employee_id, e.employee_code, CONCAT(u.first_name, ' ', COALESCE(u.middle_name, ''), ' ', u.last_name) as full_name, u.email, u.image_url, u.mobile_number1 as phone, u.date_of_birth, et.type_name as employee_type, ed.department_name, edg.designation_name, e.hire_date, e.work_location, e.salary, smp.platform_name, esm.username, esm.profile_url, esm.is_primary, esm.is_public, esm.verified, esm.follower_count, egs.specialization_type, egs.regions, egs.languages, egs.certifications, egs.experience_years, egs.rating, egs.is_available FROM employees e JOIN user u ON e.user_id = u.user_id JOIN employee_types et ON e.employee_type_id = et.id JOIN employee_departments ed ON e.department_id = ed.id JOIN employee_designations edg ON e.designation_id = edg.id LEFT JOIN employee_social_media esm ON e.id = esm.employee_id LEFT JOIN social_media_platforms smp ON esm.platform_id = smp.id LEFT JOIN employee_guide_specializations egs ON egs.employee_id = e.id WHERE et.type_name = 'guide' AND e.status_id = 1 """;

    public static final String GET_EMPLOYEE_ASSIGNED_TO_TOUR_ID = """
            SELECT
            	u.first_name,
                u.last_name,
                u.image_url,
                u.email,
                u.mobile_number1,
                ed.designation_name,
                t.assign_message
             FROM employees e
             LEFT JOIN user u
            	ON u.user_id = e.user_id
             LEFT JOIN employee_designations ed
            	ON ed.id = e.designation_id
             LEFT JOIN tour t
            	ON t.assign_to = e.id
            WHERE t.tour_id = ?
            """;
    public static final String GET_OTHER_RELATED_TOURS_BY_TOUR_ID = """
            SELECT t.tour_id, t.name
            FROM tour t
            JOIN tour ref ON ref.tour_id = ?
            WHERE t.assign_to = ref.assign_to
            """ ;
    public static final String GET_EMPLOYEE_IDS_FOR_ASSIGN_TOUR = """
            SELECT e.id
            FROM employees e
            LEFT JOIN employee_departments ed
            	ON ed.id = e.department_id
            WHERE ed.department_name IN ('Sales & Marketing','Executive Management')
            """ ;
    public static final String GET_EMPLOYEE_DETAILS_FOR_ASSIGN_TOUR = """
            SELECT
            	e.id,
                u.first_name,
                u.last_name,
                u.image_url,
                u.email,
                u.mobile_number1,
                ed.designation_name,
                JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'tour_id', t.tour_id,
                        'name', t.name
                    )
                ) AS tours
            FROM employees e
            LEFT JOIN user u
                ON u.user_id = e.user_id
            LEFT JOIN employee_designations ed
                ON ed.id = e.designation_id
            LEFT JOIN tour t
                ON t.assign_to = e.id
            LEFT JOIN employee_departments ede
            	ON ede.id = e.department_id
            WHERE ede.department_name IN ('Sales & Marketing','Executive Management')
            GROUP BY e.id,u.first_name, u.last_name, u.image_url, u.email, u.mobile_number1, ed.designation_name 
            """;
}
