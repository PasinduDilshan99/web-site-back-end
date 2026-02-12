package com.felicita.queries;

public class BrowserHistoryQueries {

    public static final String INSERT_BROWSER_HISTORY_REQUEST = """
                INSERT INTO browser_history (type, data_id, user_id, status_id)
                VALUES (?, ?, ?, (SELECT id FROM common_status WHERE name = ? LIMIT 1))
            """;

}
