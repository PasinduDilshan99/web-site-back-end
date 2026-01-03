package com.felicita.queries;

public class OurStoryQueries {

    public static final String GET_ALL_OUR_STORY_DETAILS = """
            SELECT
              story_id,
              year_label,
              title,
              description,
              icon_name,
              color,
              order_no,
              status_id,
              created_at,
              created_by,
              updated_at,
              updated_by
          FROM our_story_timeline
          WHERE status_id = 1
          ORDER BY order_no ASC
            """;

    public static final String GET_ALL_OUR_KEY_VALUES = """
            SELECT
                value_id,
                title,
                description,
                icon_name,
                color,
                order_no,
                status_id,
                created_at,
                created_by,
                updated_at,
                updated_by
            FROM our_core_values
            WHERE status_id = 1
            ORDER BY order_no ASC
            """;

}
