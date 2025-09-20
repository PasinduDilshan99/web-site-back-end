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

}
