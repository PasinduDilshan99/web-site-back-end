package com.felicita.queries;

public class HistoryManagementQueries {

    public static final String INSERT_HISTORY_DATA = """
            INSERT INTO browser_history (type, data_id, user_id, status_id)
            VALUES (?, ?, ?, 1)
            """;

    public static final String GET_HISTORY_DATA = """
            SELECT
                bh.id,
                bh.type,
                bh.data_id,
                bh.user_id,
                bh.created_at,
                cs.name AS status_name
            FROM
                browser_history bh
            LEFT JOIN
                common_status cs ON bh.status_id = cs.id
            WHERE
                bh.user_id = ?
            ORDER BY
                bh.created_at DESC
            LIMIT 20
            """;

}
