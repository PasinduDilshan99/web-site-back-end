package com.felicita.queries;

public class FaqQueries {
    public static final String GET_ALL_FAQ_DATA = """
            SELECT
                f.id AS FAQ_ID,
                f.question AS FAQ_QUESTION,
                f.answer1 AS FAQ_ANSWER1,
                f.answer2 AS FAQ_ANSWER2,
                f.answer3 AS FAQ_ANSWER3,
                f.answer4 AS FAQ_ANSWER4,
                f.answer5 AS FAQ_ANSWER5,
                f.display_answer AS FAQ_DISPLAY_ASNWER,
                fs.name AS FAQ_STATUS,
                cs.name AS FAQ_STATUS_STATUS,
                f.created_at AS FAQ_CREATED_AT,
                f.created_by AS FAQ_CREATED_BY,
                f.updated_at AS FAQ_UPDATED_AT,
                f.updated_by AS FAQ_UPDATED_BY,
                f.terminated_at AS FAQ_TERMINATED_AT,
                f.terminated_by AS FAQ_TERMINATED_BY,
                fvc.count AS FAQ_VIEW_COUNT,
                fvc.last_view AS FAQ_LAST_VIEW
            FROM faq f
            LEFT JOIN faq_status fs
                ON f.faq_status_id = fs.id
            LEFT JOIN common_status cs
                ON fs.common_status_id = cs.id
            LEFT JOIN faq_view_count fvc
                ON f.id = fvc.faq_id
            """;

    public static final String UPDATE_VIEW_COUNT = """
                    UPDATE faq_view_count
                    SET count = ?, last_view = ?
                    WHERE faq_id = ?
            """;

    public static final String GET_FAQ_BY_ID = """
            SELECT
                f.id AS FAQ_ID,
                f.question AS FAQ_QUESTION,
                f.answer1 AS FAQ_ANSWER1,
                f.answer2 AS FAQ_ANSWER2,
                f.answer3 AS FAQ_ANSWER3,
                f.answer4 AS FAQ_ANSWER4,
                f.answer5 AS FAQ_ANSWER5,
                f.display_answer AS FAQ_DISPLAY_ASNWER,
                fs.name AS FAQ_STATUS,
                cs.name AS FAQ_STATUS_STATUS,
                f.created_at AS FAQ_CREATED_AT,
                f.created_by AS FAQ_CREATED_BY,
                f.updated_at AS FAQ_UPDATED_AT,
                f.updated_by AS FAQ_UPDATED_BY,
                f.terminated_at AS FAQ_TERMINATED_AT,
                f.terminated_by AS FAQ_TERMINATED_BY,
                fvc.count AS FAQ_VIEW_COUNT,
                fvc.last_view AS FAQ_LAST_VIEW
            FROM faq f
            LEFT JOIN faq_status fs
                ON f.faq_status_id = fs.id
            LEFT JOIN common_status cs
                ON fs.common_status_id = cs.id
            LEFT JOIN faq_view_count fvc
                ON f.id = fvc.faq_id
            WHERE f.id = ?
            """;

    public static final String GET_FAQ_OPTIONS = """
            SELECT
                fo.id AS option_id,
                fo.option_key,
                fo.option_value,
                fot.name AS option_type,
                fot.description AS option_type_description,
                fo.description AS option_description,
                cs.name AS common_status_name,
                fo.created_at,
                fo.updated_at,
                fo.created_by,
                fo.updated_by
            FROM
                faq_options fo
            INNER JOIN
                faq_option_type fot ON fo.option_type_id = fot.id
            INNER JOIN
                common_status cs ON fo.common_status_id = cs.id
            ORDER BY
                fo.id ASC
            """;

    public static final String INSERT_FAQ_REQUEST = """
    INSERT INTO faq_contact_requests (
        ticket_number,
        name,
        email,
        category,
        subject,
        message,
        request_status_id,
        priority_id,
        ip_address,
        created_by
    ) VALUES (?, ?, ?, ?, ?, ?, 1, 1, ?, ?)
    """;

}
