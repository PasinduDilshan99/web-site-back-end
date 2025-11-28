package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.request.UpdateUserNotificationPermissionRequest;
import com.felicita.model.response.UserNotificationPermissionResponse;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.queries.FaqQueries;
import com.felicita.queries.UserNotificationPermissionQueries;
import com.felicita.queries.UserProfileQueries;
import com.felicita.repository.UserNotificationPermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserNotificationPermissionRepositoryImpl implements UserNotificationPermissionRepository {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserNotificationPermissionRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserNotificationPermissionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserNotificationPermissionResponse getUserNotificationPermissionById(Long userId) {
        String GET_USER_NOTIFICATION_PERMISSION_BY_ID = UserNotificationPermissionQueries.GET_USER_NOTIFICATION_PERMISSION_BY_ID;

        try {
            LOGGER.info("Executing query to fetch user notification permission details...");

            return jdbcTemplate.queryForObject(
                    GET_USER_NOTIFICATION_PERMISSION_BY_ID,
                    new Object[]{userId},
                    (rs, rowNum) -> UserNotificationPermissionResponse.builder()
                            .id(rs.getInt("id"))
                            .userId(rs.getInt("user_id"))
                            .newToursUpdate(rs.getBoolean("new_tours"))
                            .newToursUpdateAt(rs.getObject("new_tours_updated_at", LocalDateTime.class))
                            .newPackagesUpdate(rs.getBoolean("new_packages"))
                            .newPackagesUpdateAt(rs.getObject("new_packages_updated_at", LocalDateTime.class))
                            .newDestinationsUpdate(rs.getBoolean("new_destinations"))
                            .newDestinationsUpdateAt(rs.getObject("new_destinations_updated_at", LocalDateTime.class))
                            .newActivitiesUpdate(rs.getBoolean("new_activities"))
                            .newActivitiesUpdateAt(rs.getObject("new_activities_updated_at", LocalDateTime.class))
                            .discounts(rs.getBoolean("discounts"))
                            .discountsUpdatedAt(rs.getObject("discounts_updated_at", LocalDateTime.class))
                            .freeCoupons(rs.getBoolean("free_coupons"))
                            .freeCouponsUpdatedAt(rs.getObject("free_coupons_updated_at", LocalDateTime.class))
                            .yourTourDetailsUpdates(rs.getBoolean("your_tour_details"))
                            .yourTourDetailsUpdatesAt(rs.getObject("your_tour_details_updated_at", LocalDateTime.class))
                            .tourReminders(rs.getBoolean("tour_reminders"))
                            .tourRemindersUpdatedAt(rs.getObject("tour_reminders_updated_at", LocalDateTime.class))
                            .tourSuggestions(rs.getBoolean("tour_suggestions"))
                            .tourSuggestionsUpdatedAt(rs.getObject("tour_suggestions_updated_at", LocalDateTime.class))
                            .specialNotices(rs.getBoolean("special_notices"))
                            .specialNoticesUpdatedAt(rs.getObject("special_notices_updated_at", LocalDateTime.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user notification permission details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user notification permission from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user notification permission details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user notification permission");
        }
    }

    @Override
    public void updateUserNotificationPermissionDetails(UpdateUserNotificationPermissionRequest request, Long userId) {
        List<String> allowedColumns = List.of(
                "new_tours",
                "new_packages",
                "new_destinations",
                "new_activities",
                "discounts",
                "free_coupons",
                "your_tour_details",
                "tour_reminders",
                "tour_suggestions",
                "special_notices"
        );

        String columnName = request.getName();
        Object value = request.getValue();

        if (!allowedColumns.contains(columnName)) {
            throw new IllegalArgumentException("Invalid column name: " + columnName);
        }

        String timestampColumn = columnName + "_updated_at";

        String sql = "UPDATE user_notification_permission " +
                "SET " + columnName + " = ?, " +
                timestampColumn + " = NOW() " +
                "WHERE user_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, Boolean.valueOf(String.valueOf(value)), userId);

            if (rowsAffected == 0) {
                throw new InsertFailedErrorExceptionHandler("No rows affected when updating user notification");
            }

            LOGGER.info("Updated user notification {} for userId {} successfully.", columnName, userId);

        } catch (InsertFailedErrorExceptionHandler e) {
            LOGGER.error("Failed to update user notification {} for userId {}: {}", columnName, userId, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error while updating user notification {} for userId {}: {}", columnName, userId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Something went wrong while updating user notification");
        }
    }


}
