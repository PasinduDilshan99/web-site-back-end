package com.felicita.queries;

public class OurServicesQueries {
    public static final String GET_ALL_OUR_SERVICES_DATA = """
            SELECT
            	os.id AS SERVICE_ID,
                os.title AS SERVICE_TITLE,
                os.sub_title AS SERVICE_SUB_TITLE,
                os.description AS SERVICE_DESCRIPTION,
                os.image_url AS SERVICE_IMAGE_URL,
                os.color AS SERVICE_COLOR,
                oss.name AS SERVICE_STATUS,
                cs.name AS SERVICE_STATUS_STATUS,
            	cs.created_at AS SERVICE_CREATED_AT,
                cs.created_by AS SERVICE_CREATED_BY,
                cs.updated_at AS SERVICE_UPDATED_AT,
                cs.updated_by AS SERVICE_UPDATED_BY,
                cs.terminated_at AS SERVICE_TERMINATED_AT,
                cs.terminated_by AS SERVICE_TERMINATED_BY
            FROM our_services os
            LEFT JOIN our_service_status oss
            ON os.our_service_status_id = oss.id
            LEFT JOIN common_status cs
            ON oss.common_status_id = cs.id
            """;
}
