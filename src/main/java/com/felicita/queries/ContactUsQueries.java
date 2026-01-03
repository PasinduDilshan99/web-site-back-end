package com.felicita.queries;

public class ContactUsQueries {

    public static final String GET_ALL_ACTIVE_CONTACT_METHODS = """
            SELECT
                cm.id,
                cm.title,
                cm.value,
                cm.description,
                cm.link,
                cm.action,
                cm.icon
            FROM contact_methods cm
            JOIN common_status cs ON cm.status = cs.id
            WHERE cs.name = 'ACTIVE'
            ORDER BY cm.id ASC
            """;

}
