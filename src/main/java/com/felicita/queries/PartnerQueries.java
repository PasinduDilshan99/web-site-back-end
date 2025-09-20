package com.felicita.queries;

public class PartnerQueries {

    public static final String GET_ALL_PARTNERS = """
                        SELECT
                        	p.id AS PARTNER_ID,
                            p.name AS PARTNER_NAME,
                            p.company_name AS PARTNER_COMPANY_NAME,
                            p.company_description AS PARTNER_COMPANY_DESCRIPTION,
                            p.company_logo AS PARTNER_LOGO_URL,
                            p.company_website_url AS PARTNER_WEBSITE_URL,
                            p.agreement AS PARTNER_AGREEMENT,
                            ps.name AS PARTNER_STATUS,
                            cs.name AS PARTNER_STATUS_STATUS,
                            p.from_date AS PARTNER_FROM_DATE,
                            p.to_date AS PARTNER_TO_DATE,
                        	p.created_at AS PARTNER_CREATED_AT,
                            p.created_by AS PARTNER_CREATED_BY,
                            p.updated_at AS PARTNER_UPDATED_AT,
                            p.updated_by AS PARTNER_UPDATED_BY,
                            p.terminated_at AS PARTNER_TERMINATED_AT,
                            p.terminated_by AS PARTNER_TERMINATED_BY
                        FROM partners p
                        LEFT JOIN partner_status ps
                        ON p.partner_status_id = ps.id
                        LEFT JOIN common_status cs
                        ON ps.common_status_id = cs.id
            """;

}
