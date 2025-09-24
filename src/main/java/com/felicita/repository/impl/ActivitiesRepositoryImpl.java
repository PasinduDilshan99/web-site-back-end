package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.ActivitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ActivitiesRepositoryImpl implements ActivitiesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActivitiesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ActivityResponse> getAllActivities() {
        String GET_ALL_ACTIVITIES = ActivitiesQueries.GET_ALL_ACTIVITIES;

        try {
            LOGGER.info("Executing query to fetch all activities...");

            List<ActivityResponse> results = jdbcTemplate.query(GET_ALL_ACTIVITIES, rs -> {
                Map<Long, ActivityResponse> activityMap = new LinkedHashMap<>();
                Map<Long, ActivityResponse.ActivityHistoryResponse> historyMap = new HashMap<>();

                while (rs.next()) {
                    Long activityId = rs.getLong("activity_id");
                    ActivityResponse activity = activityMap.get(activityId);

                    if (activity == null) {
                        // Build main ActivityResponse
                        activity = ActivityResponse.builder()
                                .id(activityId)
                                .name(rs.getString("activity_name"))
                                .description(rs.getString("activity_description"))
                                .durationHours(rs.getInt("duration_hours"))
                                .price(rs.getBigDecimal("activity_price"))
                                .maxParticipants(rs.getObject("max_participants", Integer.class))
                                .minParticipants(rs.getObject("min_participants", Integer.class))
                                .category(ActivityResponse.ActivityCategory.builder()
                                        .id(rs.getLong("category_id"))
                                        .name(rs.getString("category_name"))
                                        .description(rs.getString("category_description"))
                                        .imageUrl(rs.getString("category_image"))
                                        .build())
                                .destination(ActivityResponse.Destination.builder()
                                        .id(rs.getLong("destination_id"))
                                        .name(rs.getString("destination_name"))
                                        .description(rs.getString("destination_description"))
                                        .location(rs.getString("destination_location"))
                                        .rating(rs.getBigDecimal("destination_rating"))
                                        .build())
                                .status(ActivityResponse.CommonStatus.builder()
                                        .id(rs.getLong("status_id"))
                                        .name(rs.getString("status_name"))
                                        .description(rs.getString("status_description"))
                                        .build())
                                .requirements(new ArrayList<>())
                                .historyList(new ArrayList<>())
                                .build();

                        activityMap.put(activityId, activity);
                    }

                    // Requirements (may be null)
                    String reqType = rs.getString("requirement_type");
                    if (reqType != null) {
                        ActivityResponse.ActivityRequirement requirement =
                                ActivityResponse.ActivityRequirement.builder()
                                        .type(reqType)
                                        .value(rs.getString("requirement_value"))
                                        .description(rs.getString("requirement_description"))
                                        .build();
                        if (!activity.getRequirements().contains(requirement)) {
                            activity.getRequirements().add(requirement);
                        }
                    }

                    // History (may be null)
                    Long historyId = rs.getObject("history_id", Long.class);
                    if (historyId != null) {
                        ActivityResponse.ActivityHistoryResponse history = historyMap.get(historyId);

                        if (history == null) {
                            history = ActivityResponse.ActivityHistoryResponse.builder()
                                    .id(historyId)
                                    .name(rs.getString("history_name"))
                                    .startDate(rs.getDate("start_date"))
                                    .endDate(rs.getDate("end_date"))
                                    .price(rs.getBigDecimal("history_price"))
                                    .participantsCount(rs.getObject("participants_count", Integer.class))
                                    .guide(rs.getString("guide"))
                                    .images(new ArrayList<>())
                                    .build();

                            activity.getHistoryList().add(history);
                            historyMap.put(historyId, history);
                        }

                        // History images (may be null)
                        Long imageId = rs.getObject("history_image_id", Long.class);
                        if (imageId != null) {
                            ActivityResponse.ActivityHistoryImage image =
                                    ActivityResponse.ActivityHistoryImage.builder()
                                            .id(imageId)
                                            .name(rs.getString("history_image_name"))
                                            .imageUrl(rs.getString("history_image_url"))
                                            .imageOwner(rs.getString("history_image_owner"))
                                            .build();

                            if (!history.getImages().contains(image)) {
                                history.getImages().add(image);
                            }
                        }
                    }
                }

                return new ArrayList<>(activityMap.values());
            });

            LOGGER.info("Successfully fetched {} activities.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activities: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activities from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activities: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activities");
        }
    }

    @Override
    public List<ActivityCategoryResponse> getActivityCategories() {
        String GET_ACTIVITY_CATEGORIES = ActivitiesQueries.GET_ACTIVITY_CATEGORIES;

        try {
            LOGGER.info("Executing query to fetch all activity categories...");

            List<ActivityCategoryResponse> results = jdbcTemplate.query(GET_ACTIVITY_CATEGORIES, (rs, rowNum) -> {
                ActivityCategoryResponse response = new ActivityCategoryResponse();
                response.setId(rs.getInt("id"));
                response.setName(rs.getString("name"));
                response.setDescription(rs.getString("description"));
                response.setImageUrl(rs.getString("image_url"));
                response.setColor(rs.getString("color"));
                response.setHoverColor(rs.getString("hover_color"));
                response.setStatusName(rs.getString("status_name"));
                response.setCreatedAt(rs.getString("created_at"));
                response.setCreatedBy(rs.getObject("created_by") != null ? rs.getInt("created_by") : null);
                response.setUpdatedAt(rs.getString("updated_at"));
                response.setUpdatedBy(rs.getObject("updated_by") != null ? rs.getInt("updated_by") : null);
                return response;
            });

            LOGGER.info("Successfully fetched {} activity categories.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity categories");
        }
    }


}
