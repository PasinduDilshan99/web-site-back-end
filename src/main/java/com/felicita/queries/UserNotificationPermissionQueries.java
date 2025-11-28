package com.felicita.queries;

public class UserNotificationPermissionQueries {

    public static final String GET_USER_NOTIFICATION_PERMISSION_BY_ID = """
            SELECT
                id,
                user_id,
                new_tours,
                new_tours_updated_at,
                new_packages,
                new_packages_updated_at,
                new_destinations,
                new_destinations_updated_at,
                new_activities,
                new_activities_updated_at,
                discounts,
                discounts_updated_at,
                free_coupons,
                free_coupons_updated_at,
                your_tour_details,
                your_tour_details_updated_at,
                tour_reminders,
                tour_reminders_updated_at,
                tour_suggestions,
                tour_suggestions_updated_at,
                special_notices,
                special_notices_updated_at,
                created_at,
                updated_at
            FROM user_notification_permission
            WHERE user_id = ?
            """;

    public static final String UPDATE_USER_NOTIFICATION_PERMISSION_DETAILS = """
            
            """;

}
