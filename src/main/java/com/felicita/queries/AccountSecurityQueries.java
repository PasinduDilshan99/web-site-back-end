package com.felicita.queries;

public class AccountSecurityQueries {

    public static final String GET_ACCOUNT_SECURITY_DETAILS_BY_ID = """
            	SELECT
            		u.username,
            		u.email,
            		u.mobile_number1,
            		u.mobile_number2,
            		ev.created_at AS email_created_at,
            		ev.updated_at AS email_updated_at,
            		vs_email.name AS email_status_name,
            		vs_email.description AS email_status_description,
            		mv.created_at AS mobile_created_at,
            		mv.updated_at AS mobile_updated_at,
            		vs_mobile.name AS mobile_status_name,
            		vs_mobile.description AS mobile_status_description
            	FROM user u
            	LEFT JOIN email_verified ev
            		ON u.user_id = ev.user_id
            	LEFT JOIN verified_status vs_email
            		ON ev.status_id = vs_email.verified_status_id
            	LEFT JOIN mobile_verified mv
            		ON u.user_id = mv.user_id
            	LEFT JOIN verified_status vs_mobile
            		ON mv.status_id = vs_mobile.verified_status_id
            	WHERE u.user_id = ?
            """;

    public static final String GET_REAL_MOBILE_VERIFICATION_CODE = """
                SELECT
                		mv.send_code,
                		vs.name
                	from mobile_verified mv
                    left join verified_status vs ON vs.verified_status_id = mv.status_id
                    WHERE mv.user_id=? AND mv.which_mobile =?
            """;

    public static final String GET_REAL_EMAIL_VERIFICATION_CODE = """
                SELECT
                		ev.send_code,
                		vs.name
                	from email_verified ev
                    left join verified_status vs ON vs.verified_status_id = ev.status_id
                    WHERE ev.user_id=? AND ev.which_email = ?
            """;

    public static final String UPDATE_MOBILE_VERIFICATION = """
            UPDATE mobile_verified
            SET status_id = (
                    SELECT verified_status_id
                    FROM verified_status
                    WHERE name = ?
                ),
                updated_at = CURRENT_TIMESTAMP
            WHERE user_id = ? AND which_mobile = ? 
            """;

    public static final String UPDATE_EMAIL_VERIFICATION = """
            UPDATE email_verified
            SET status_id = (
                    SELECT verified_status_id
                    FROM verified_status
                    WHERE name = ?
                ),
                updated_at = CURRENT_TIMESTAMP
            WHERE user_id = ? AND which_email = ?
            """;

    public static final String GET_VERIFICATION_COMPARE_OTP_PRIMARY = """
    SELECT
        u.mobile_number1 AS MOBILE_NUMBER,
        vs.name AS CURRENT_STATUS
    FROM mobile_verified mv
    LEFT JOIN verified_status vs ON vs.verified_status_id = mv.status_id
    LEFT JOIN user u ON u.user_id = mv.user_id
    WHERE u.user_id = ? AND mv.which_mobile = 'primary'
""";

    public static final String GET_VERIFICATION_COMPARE_OTP_SECONDARY = """
    SELECT
        u.mobile_number2 AS MOBILE_NUMBER,
        vs.name AS CURRENT_STATUS
    FROM mobile_verified mv
    LEFT JOIN verified_status vs ON vs.verified_status_id = mv.status_id
    LEFT JOIN user u ON u.user_id = mv.user_id
    WHERE u.user_id = ? AND mv.which_mobile = 'secondary'
""";
    public static final String GET_EMAIL_VERIFICATION_COMPARE_OTP_PRIMARY = """
    SELECT
        u.email AS EMAIL,
        vs.name AS CURRENT_STATUS
    FROM email_verified ev
    LEFT JOIN verified_status vs ON vs.verified_status_id = ev.status_id
    LEFT JOIN user u ON u.user_id = ev.user_id
    WHERE u.user_id = ? AND ev.which_email = 'primary'
""";

    public static final String GET_EMAIL_VERIFICATION_COMPARE_OTP_SECONDARY = """
    SELECT
        u.email2 AS EMAIL,
        vs.name AS CURRENT_STATUS
    FROM email_verified ev
    LEFT JOIN verified_status vs ON vs.verified_status_id = ev.status_id
    LEFT JOIN user u ON u.user_id = ev.user_id
    WHERE u.user_id = ? AND ev.which_email = 'secondary'
""";


    public static final String UPDATE_MOBILE_NUMBER_RANDOM_OTP = """
            UPDATE mobile_verified
            SET status_id = (
            		SELECT verified_status_id
            		FROM verified_status
            		WHERE name = 'Pending'
            	),
            	updated_at = CURRENT_TIMESTAMP,
                send_code = ?
            WHERE user_id = ? AND which_mobile = ?
            """;

    public static final String UPDATE_EMAIL_RANDOM_OTP = """
            UPDATE email_verified
            SET status_id = (
            		SELECT verified_status_id
            		FROM verified_status
            		WHERE name = 'Pending'
            	),
            	updated_at = CURRENT_TIMESTAMP,
                send_code = ?
            WHERE user_id = ? AND which_email = ?
            """;

}
