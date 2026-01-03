package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.TerminateFailedErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.ActivityDataRequest;
import com.felicita.model.request.ActivityTerminateRequest;
import com.felicita.model.response.*;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.ActivitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ActivitiesRepositoryImpl implements ActivitiesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;  // ADD THIS

    @Autowired
    public ActivitiesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = new ObjectMapper();  // ADD THIS
    }

    @Override
    public List<ActivityResponseDto> getAllActivities() {
        String GET_ALL_ACTIVITIES = ActivitiesQueries.GET_ALL_ACTIVITIES;

        return jdbcTemplate.query(GET_ALL_ACTIVITIES, new ActivityRowMapper());
    }

    @Override
    public List<ActivityCategoryResponseDto> getAllActivityCategories() {
        String GET_ALL_ACTIVITY_CATEGORIES = ActivitiesQueries.GET_ALL_ACTIVITY_CATEGORIES;

        try {
            LOGGER.info("Executing query to fetch all activity categories...");

            // Use a LinkedHashMap to maintain insertion order and group images under categories
            Map<Integer, ActivityCategoryResponseDto> categoryMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_ACTIVITY_CATEGORIES, (rs) -> {
                int categoryId = rs.getInt("category_id");

                ActivityCategoryResponseDto category = categoryMap.get(categoryId);
                if (category == null) {
                    category = new ActivityCategoryResponseDto();
                    category.setCategoryId(categoryId);
                    category.setCategoryName(rs.getString("category_name"));
                    category.setCategoryDescription(rs.getString("category_description"));
                    category.setCategoryStatus(rs.getString("category_status"));
                    category.setCreatedAt(rs.getTimestamp("category_created_at"));
                    category.setCreatedBy(rs.getObject("category_created_by", Integer.class));
                    category.setUpdatedAt(rs.getTimestamp("category_updated_at"));
                    category.setUpdatedBy(rs.getObject("category_updated_by", Integer.class));
                    category.setTerminatedAt(rs.getTimestamp("category_terminated_at"));
                    category.setTerminatedBy(rs.getObject("category_terminated_by", Integer.class));
                    category.setImages(new ArrayList<>());

                    categoryMap.put(categoryId, category);
                }

                int imageId = rs.getInt("image_id");
                if (!rs.wasNull()) {
                    ActivityCategoryImageResponseDto image = new ActivityCategoryImageResponseDto();
                    image.setImageId(imageId);
                    image.setImageName(rs.getString("image_name"));
                    image.setImageDescription(rs.getString("image_description"));
                    image.setImageUrl(rs.getString("image_url"));
                    image.setImageStatus(rs.getString("image_status"));
                    image.setCreatedAt(rs.getTimestamp("image_created_at"));
                    image.setCreatedBy(rs.getObject("image_created_by", Integer.class));
                    image.setUpdatedAt(rs.getTimestamp("image_updated_at"));
                    image.setUpdatedBy(rs.getObject("image_updated_by", Integer.class));
                    image.setTerminatedAt(rs.getTimestamp("image_terminated_at"));
                    image.setTerminatedBy(rs.getObject("image_terminated_by", Integer.class));

                    category.getImages().add(image);
                }
            });

            return new ArrayList<>(categoryMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity categories");
        }
    }

    @Override
    public List<ActivityReviewDetailsResponse> getActivityReviewDetailsById(String activityId) {
        String GET_ACTIVITY_REVIEW_DETAILS_BY_ID = ActivitiesQueries.GET_ACTIVITY_REVIEW_DETAILS_BY_ID;

        try {
            LOGGER.info("Executing query to fetch activity review details for activityId={}", activityId);

            // Map to aggregate nested data
            Map<Long, ActivityReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(
                    GET_ACTIVITY_REVIEW_DETAILS_BY_ID,
                    new Object[]{activityId},
                    rs -> {
                        Long reviewId = rs.getLong("review_id");
                        ActivityReviewDetailsResponse review = reviewMap.get(reviewId);

                        if (review == null) {
                            review = ActivityReviewDetailsResponse.builder()
                                    .reviewId(reviewId)
                                    .activityScheduleId(rs.getLong("activity_schedule_id"))
                                    .activityId(rs.getLong("activity_id"))
                                    .activityName(rs.getString("activity_name"))
                                    .reviewName(rs.getString("review_name"))
                                    .review(rs.getString("review"))
                                    .rating(rs.getBigDecimal("rating"))
                                    .description(rs.getString("description"))
                                    .reviewStatus(rs.getString("review_status"))
                                    .numberOfParticipate(rs.getInt("number_of_participate"))
                                    .reviewCreatedBy(rs.getLong("review_created_by"))
                                    .reviewCreatedAt(rs.getTimestamp("review_created_at").toLocalDateTime())
                                    .reviewUpdatedBy(rs.getObject("review_updated_by") != null ? rs.getLong("review_updated_by") : null)
                                    .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ? rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                                    .images(new ArrayList<>())
                                    .reactions(new ArrayList<>())
                                    .comments(new ArrayList<>())
                                    .build();
                            reviewMap.put(reviewId, review);
                        }

                        // Images
                        Long imageId = rs.getObject("image_id", Long.class);
                        if (imageId != null && review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {
                            review.getImages().add(ActivityReviewDetailsResponse.ReviewImage.builder()
                                    .imageId(imageId)
                                    .imageName(rs.getString("image_name"))
                                    .imageDescription(rs.getString("image_description"))
                                    .imageUrl(rs.getString("image_url"))
                                    .imageStatus(rs.getString("image_status"))
                                    .imageCreatedBy(rs.getLong("image_created_by"))
                                    .imageCreatedAt(rs.getTimestamp("image_created_at").toLocalDateTime())
                                    .build());
                        }

                        // Review Reactions
                        Long reviewReactionId = rs.getObject("review_reaction_id", Long.class);
                        if (reviewReactionId != null && review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reviewReactionId))) {
                            review.getReactions().add(ActivityReviewDetailsResponse.ReviewReaction.builder()
                                    .reviewReactionId(reviewReactionId)
                                    .reactionReviewId(rs.getLong("reaction_review_id"))
                                    .userId(rs.getLong("reaction_user_id"))
                                    .userName(rs.getString("reaction_user_name"))
                                    .reactionType(rs.getString("reaction_type"))
                                    .reviewReactionStatus(rs.getString("review_reaction_status"))
                                    .reactionCreatedAt(rs.getTimestamp("reaction_created_at").toLocalDateTime())
                                    .build());
                        }

                        // Comments
                        Long commentId = rs.getObject("comment_id", Long.class);
                        ActivityReviewDetailsResponse.Comment comment = null;
                        if (commentId != null) {
                            comment = review.getComments().stream()
                                    .filter(c -> c.getCommentId().equals(commentId))
                                    .findFirst()
                                    .orElse(null);
                            if (comment == null) {
                                comment = ActivityReviewDetailsResponse.Comment.builder()
                                        .commentId(commentId)
                                        .commentReviewId(rs.getLong("comment_review_id"))
                                        .userId(rs.getLong("comment_user_id"))
                                        .userName(rs.getString("comment_user_name"))
                                        .parentCommentId(rs.getObject("parent_comment_id") != null ? rs.getLong("parent_comment_id") : null)
                                        .comment(rs.getString("comment"))
                                        .commentStatus(rs.getString("comment_status"))
                                        .commentCreatedAt(rs.getTimestamp("comment_created_at").toLocalDateTime())
                                        .commentCreatedBy(rs.getLong("comment_created_by"))
                                        .commentReactions(new ArrayList<>())
                                        .build();
                                review.getComments().add(comment);
                            }
                        }

                        // Comment Reactions
                        if (comment != null) {
                            Long commentReactionId = rs.getObject("comment_reaction_id", Long.class);
                            if (commentReactionId != null && comment.getCommentReactions().stream().noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {
                                comment.getCommentReactions().add(ActivityReviewDetailsResponse.Comment.CommentReaction.builder()
                                        .commentReactionId(commentReactionId)
                                        .commentReactionCommentId(rs.getLong("comment_reaction_comment_id"))
                                        .userId(rs.getLong("comment_reaction_user_id"))
                                        .userName(rs.getString("comment_reaction_user_name"))
                                        .commentReactionType(rs.getString("comment_reaction_type"))
                                        .commentReactionStatus(rs.getString("comment_reaction_status"))
                                        .commentReactionCreatedBy(rs.getLong("comment_reaction_created_by"))
                                        .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at").toLocalDateTime())
                                        .build());
                            }
                        }
                    }
            );

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity reviews for activityId={}: {}", activityId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity reviews for activityId={}: {}", activityId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity reviews");
        }
    }


    @Override
    public List<ActivityReviewDetailsResponse> getAllActivityReviewDetails() {
        String GET_ACTIVITY_REVIEW_DETAILS = ActivitiesQueries.GET_ACTIVITY_REVIEW_DETAILS;

        try {
            LOGGER.info("Executing query to fetch all activity review details...");

            // Map to store reviews by reviewId for aggregating nested lists
            Map<Long, ActivityReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ACTIVITY_REVIEW_DETAILS, rs -> {
                Long reviewId = rs.getLong("review_id");
                ActivityReviewDetailsResponse review = reviewMap.get(reviewId);

                if (review == null) {
                    review = ActivityReviewDetailsResponse.builder()
                            .reviewId(reviewId)
                            .activityScheduleId(rs.getLong("activity_schedule_id"))
                            .activityId(rs.getLong("activity_id"))
                            .activityName(rs.getString("activity_name"))
                            .reviewName(rs.getString("review_name"))
                            .review(rs.getString("review"))
                            .rating(rs.getBigDecimal("rating"))
                            .description(rs.getString("description"))
                            .reviewStatus(rs.getString("review_status"))
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .reviewCreatedBy(rs.getLong("review_created_by"))
                            .reviewCreatedAt(rs.getTimestamp("review_created_at").toLocalDateTime())
                            .reviewUpdatedBy(rs.getObject("review_updated_by") != null ? rs.getLong("review_updated_by") : null)
                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ? rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();
                    reviewMap.put(reviewId, review);
                }

                // Process Review Image
                Long imageId = rs.getObject("image_id", Long.class);
                if (imageId != null && review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {
                    review.getImages().add(ActivityReviewDetailsResponse.ReviewImage.builder()
                            .imageId(imageId)
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .imageStatus(rs.getString("image_status"))
                            .imageCreatedBy(rs.getLong("image_created_by"))
                            .imageCreatedAt(rs.getTimestamp("image_created_at").toLocalDateTime())
                            .build());
                }

                // Process Review Reaction
                Long reviewReactionId = rs.getObject("review_reaction_id", Long.class);
                if (reviewReactionId != null && review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reviewReactionId))) {
                    review.getReactions().add(ActivityReviewDetailsResponse.ReviewReaction.builder()
                            .reviewReactionId(reviewReactionId)
                            .reactionReviewId(rs.getLong("reaction_review_id"))
                            .userId(rs.getLong("reaction_user_id"))
                            .userName(rs.getString("reaction_user_name"))
                            .reactionType(rs.getString("reaction_type"))
                            .reviewReactionStatus(rs.getString("review_reaction_status"))
                            .reactionCreatedAt(rs.getTimestamp("reaction_created_at").toLocalDateTime())
                            .build());
                }

                // Process Comment
                Long commentId = rs.getObject("comment_id", Long.class);
                ActivityReviewDetailsResponse.Comment comment = null;
                if (commentId != null) {
                    comment = review.getComments().stream()
                            .filter(c -> c.getCommentId().equals(commentId))
                            .findFirst()
                            .orElse(null);
                    if (comment == null) {
                        comment = ActivityReviewDetailsResponse.Comment.builder()
                                .commentId(commentId)
                                .commentReviewId(rs.getLong("comment_review_id"))
                                .userId(rs.getLong("comment_user_id"))
                                .userName(rs.getString("comment_user_name"))
                                .parentCommentId(rs.getObject("parent_comment_id") != null ? rs.getLong("parent_comment_id") : null)
                                .comment(rs.getString("comment"))
                                .commentStatus(rs.getString("comment_status"))
                                .commentCreatedAt(rs.getTimestamp("comment_created_at").toLocalDateTime())
                                .commentCreatedBy(rs.getLong("comment_created_by"))
                                .commentReactions(new ArrayList<>())
                                .build();
                        review.getComments().add(comment);
                    }
                }

                // Process Comment Reaction
                if (comment != null) {
                    Long commentReactionId = rs.getObject("comment_reaction_id", Long.class);
                    if (commentReactionId != null && comment.getCommentReactions().stream().noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {
                        comment.getCommentReactions().add(ActivityReviewDetailsResponse.Comment.CommentReaction.builder()
                                .commentReactionId(commentReactionId)
                                .commentReactionCommentId(rs.getLong("comment_reaction_comment_id"))
                                .userId(rs.getLong("comment_reaction_user_id"))
                                .userName(rs.getString("comment_reaction_user_name"))
                                .commentReactionType(rs.getString("comment_reaction_type"))
                                .commentReactionStatus(rs.getString("comment_reaction_status"))
                                .commentReactionCreatedBy(rs.getLong("comment_reaction_created_by"))
                                .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at").toLocalDateTime())
                                .build());
                    }
                }
            });

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity reviews: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity reviews: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity reviews");
        }
    }

    @Override
    public ActivityResponseDto getActivityById(String activityId) {
        String GET_ACTIVITY_DETAILS_BY_ID = ActivitiesQueries.GET_ACTIVITY_DETAILS_BY_ID;

        try {
            return jdbcTemplate.queryForObject(GET_ACTIVITY_DETAILS_BY_ID, new Object[]{activityId}, (rs, rowNum) -> {
                ActivityResponseDto activity = new ActivityResponseDto();
                activity.setId(rs.getInt("id"));
                activity.setDestinationId(rs.getInt("destination_id"));
                activity.setName(rs.getString("name"));
                activity.setDescription(rs.getString("description"));
                activity.setActivitiesCategory(rs.getString("activities_category"));
                activity.setDurationHours(rs.getBigDecimal("duration_hours"));
                activity.setAvailableFrom(rs.getTime("available_from"));
                activity.setAvailableTo(rs.getTime("available_to"));
                activity.setPriceLocal(rs.getBigDecimal("price_local"));
                activity.setPriceForeigners(rs.getBigDecimal("price_foreigners"));
                activity.setMinParticipate(rs.getInt("min_participate"));
                activity.setMaxParticipate(rs.getInt("max_participate"));
                activity.setSeason(rs.getString("season"));
                activity.setStatus(rs.getString("status_name"));
                activity.setCreatedAt(rs.getTimestamp("created_at"));
                activity.setUpdatedAt(rs.getTimestamp("updated_at"));
                activity.setCategoryName(rs.getString("category_name"));
                activity.setCategoryDescription(rs.getString("category_description"));

                ObjectMapper mapper = new ObjectMapper();

                // Deserialize JSON arrays into DTO lists
                String schedulesJson = rs.getString("schedules");
                try {
                    activity.setSchedules(schedulesJson != null ?
                            mapper.readValue(schedulesJson, new TypeReference<List<ActivityScheduleDto>>() {}) : List.of());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                String requirementsJson = rs.getString("requirements");
                try {
                    activity.setRequirements(requirementsJson != null ?
                            mapper.readValue(requirementsJson, new TypeReference<List<ActivityRequirementDto>>() {}) : List.of());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                String imagesJson = rs.getString("images");
                try {
                    activity.setImages(imagesJson != null ?
                            mapper.readValue(imagesJson, new TypeReference<List<ActivityImageDto>>() {}) : List.of());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                return activity;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity details");
        }
    }

    @Override
    public List<ActivityHistoryDetailsResponse> getActivityHistoryDetailsById(String activityId) {
        String GET_ACTIVITY_HISTORY_DETAILS_BY_ID = ActivitiesQueries.GET_ACTIVITY_HISTORY_DETAILS_BY_ID;

        try {
            LOGGER.info("Fetching activity history details for activityId: {}", activityId);

            Map<Long, ActivityHistoryDetailsResponse> historyMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ACTIVITY_HISTORY_DETAILS_BY_ID, new Object[]{activityId}, rs -> {
                Long historyId = rs.getLong("history_id");
                ActivityHistoryDetailsResponse response = historyMap.get(historyId);

                if (response == null) {
                    response = ActivityHistoryDetailsResponse.builder()
                            .historyId(historyId)
                            .activity(ActivityHistoryDetailsResponse.ActivityInfo.builder()
                                    .activityId(rs.getLong("activity_id"))
                                    .activityName(rs.getString("activity_name"))
                                    .activityDescription(rs.getString("activity_description"))
                                    .activityCategory(rs.getString("activity_category"))
                                    .durationHours(rs.getInt("duration_hours"))
                                    .availableFrom(getLocalDateTime(rs, "available_from"))
                                    .availableTo(getLocalDateTime(rs, "available_to"))
                                    .priceLocal(rs.getDouble("price_local"))
                                    .priceForeigners(rs.getDouble("price_foreigners"))
                                    .minParticipate(rs.getInt("min_participate"))
                                    .maxParticipate(rs.getInt("max_participate"))
                                    .season(rs.getString("season"))
                                    .destination(ActivityHistoryDetailsResponse.DestinationInfo.builder()
                                            .destinationId(rs.getString("destination_id"))
                                            .destinationName(rs.getString("destination_name"))
                                            .destinationDescription(rs.getString("destination_description"))
                                            .destinationLocation(rs.getString("destination_location"))
                                            .latitude(getDouble(rs, "latitude"))
                                            .longitude(getDouble(rs, "longitude"))
                                            .build())
                                    .build())
                            .schedule(ActivityHistoryDetailsResponse.ScheduleInfo.builder()
                                    .scheduleId(rs.getLong("schedule_id"))
                                    .scheduleName(rs.getString("schedule_name"))
                                    .scheduleDescription(rs.getString("schedule_description"))
                                    .assumeStartDate(getLocalDateTime(rs, "assume_start_date"))
                                    .assumeEndDate(getLocalDateTime(rs, "assume_end_date"))
                                    .durationHoursStart(rs.getInt("duration_hours_start"))
                                    .durationHoursEnd(rs.getInt("duration_hours_end"))
                                    .specialNote(rs.getString("schedule_special_note"))
                                    .build())
                            .history(ActivityHistoryDetailsResponse.HistoryInfo.builder()
                                    .historyName(rs.getString("history_name"))
                                    .historyDescription(rs.getString("history_description"))
                                    .numberOfParticipate(rs.getInt("number_of_participate"))
                                    .activityStart(getLocalDateTime(rs, "activity_start"))
                                    .activityEnd(getLocalDateTime(rs, "activity_end"))
                                    .rating(rs.getDouble("rating"))
                                    .specialNote(rs.getString("history_special_note"))
                                    .statusName(rs.getString("history_status_name"))
                                    .createdByUsername(rs.getString("history_created_by_username"))
                                    .updatedByUsername(rs.getString("history_updated_by_username"))
                                    .terminatedByUsername(rs.getString("history_terminated_by_username"))
                                    .createdAt(getLocalDateTime(rs, "history_created_at"))
                                    .updatedAt(getLocalDateTime(rs, "history_updated_at"))
                                    .terminatedAt(getLocalDateTime(rs, "history_terminated_at"))
                                    .build())
                            .images(new ArrayList<>())
                            .build();

                    historyMap.put(historyId, response);
                }

                // 🖼️ Add image if exists
                Long imageId = rs.getLong("image_id");
                if (imageId != 0) {
                    ActivityHistoryDetailsResponse.ImageInfo image = ActivityHistoryDetailsResponse.ImageInfo.builder()
                            .imageId(imageId)
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .statusName(rs.getString("image_status_name"))
                            .createdByUsername(rs.getString("image_created_by_username"))
                            .updatedByUsername(rs.getString("image_updated_by_username"))
                            .terminatedByUsername(rs.getString("image_terminated_by_username"))
                            .createdAt(getLocalDateTime(rs, "image_created_at"))
                            .updatedAt(getLocalDateTime(rs, "image_updated_at"))
                            .terminatedAt(getLocalDateTime(rs, "image_terminated_at"))
                            .build();

                    response.getImages().add(image);
                }
            });

            return new ArrayList<>(historyMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity details");
        }
    }


    @Override
    public List<ActivityHistoryDetailsResponse> getAllActivityHistoryDetails() {
        String GET_ACTIVITY_HISTORY_DETAILS = ActivitiesQueries.GET_ACTIVITY_HISTORY_DETAILS;

        try {
            LOGGER.info("Executing query to fetch all activity history details...");

            Map<Long, ActivityHistoryDetailsResponse> historyMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ACTIVITY_HISTORY_DETAILS, rs -> {
                Long historyId = rs.getLong("history_id");
                ActivityHistoryDetailsResponse response = historyMap.get(historyId);

                if (response == null) {
                    // Build base response object
                    response = ActivityHistoryDetailsResponse.builder()
                            .historyId(historyId)
                            .activity(ActivityHistoryDetailsResponse.ActivityInfo.builder()
                                    .activityId(rs.getLong("activity_id"))
                                    .activityName(rs.getString("activity_name"))
                                    .activityDescription(rs.getString("activity_description"))
                                    .activityCategory(rs.getString("activity_category"))
                                    .durationHours(rs.getInt("duration_hours"))
                                    .availableFrom(getLocalDateTime(rs, "available_from"))
                                    .availableTo(getLocalDateTime(rs, "available_to"))
                                    .priceLocal(rs.getDouble("price_local"))
                                    .priceForeigners(rs.getDouble("price_foreigners"))
                                    .minParticipate(rs.getInt("min_participate"))
                                    .maxParticipate(rs.getInt("max_participate"))
                                    .season(rs.getString("season"))
                                    .destination(ActivityHistoryDetailsResponse.DestinationInfo.builder()
                                            .destinationId(rs.getString("destination_id"))
                                            .destinationName(rs.getString("destination_name"))
                                            .destinationDescription(rs.getString("destination_description"))
                                            .destinationLocation(rs.getString("destination_location"))
                                            .latitude(getDouble(rs, "latitude"))
                                            .longitude(getDouble(rs, "longitude"))
                                            .build())
                                    .build())
                            .schedule(ActivityHistoryDetailsResponse.ScheduleInfo.builder()
                                    .scheduleId(rs.getLong("schedule_id"))
                                    .scheduleName(rs.getString("schedule_name"))
                                    .scheduleDescription(rs.getString("schedule_description"))
                                    .assumeStartDate(getLocalDateTime(rs, "assume_start_date"))
                                    .assumeEndDate(getLocalDateTime(rs, "assume_end_date"))
                                    .durationHoursStart(rs.getInt("duration_hours_start"))
                                    .durationHoursEnd(rs.getInt("duration_hours_end"))
                                    .specialNote(rs.getString("schedule_special_note"))
                                    .build())
                            .history(ActivityHistoryDetailsResponse.HistoryInfo.builder()
                                    .historyName(rs.getString("history_name"))
                                    .historyDescription(rs.getString("history_description"))
                                    .numberOfParticipate(rs.getInt("number_of_participate"))
                                    .activityStart(getLocalDateTime(rs, "activity_start"))
                                    .activityEnd(getLocalDateTime(rs, "activity_end"))
                                    .rating(rs.getDouble("rating"))
                                    .specialNote(rs.getString("history_special_note"))
                                    .statusName(rs.getString("history_status_name"))
                                    .createdByUsername(rs.getString("history_created_by_username"))
                                    .updatedByUsername(rs.getString("history_updated_by_username"))
                                    .terminatedByUsername(rs.getString("history_terminated_by_username"))
                                    .createdAt(getLocalDateTime(rs, "history_created_at"))
                                    .updatedAt(getLocalDateTime(rs, "history_updated_at"))
                                    .terminatedAt(getLocalDateTime(rs, "history_terminated_at"))
                                    .build())
                            .images(new ArrayList<>())
                            .build();

                    historyMap.put(historyId, response);
                }

                // Add image if exists
                Long imageId = rs.getLong("image_id");
                if (imageId != 0) {
                    ActivityHistoryDetailsResponse.ImageInfo image = ActivityHistoryDetailsResponse.ImageInfo.builder()
                            .imageId(imageId)
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .statusName(rs.getString("image_status_name"))
                            .createdByUsername(rs.getString("image_created_by_username"))
                            .updatedByUsername(rs.getString("image_updated_by_username"))
                            .terminatedByUsername(rs.getString("image_terminated_by_username"))
                            .createdAt(getLocalDateTime(rs, "image_created_at"))
                            .updatedAt(getLocalDateTime(rs, "image_updated_at"))
                            .terminatedAt(getLocalDateTime(rs, "image_terminated_at"))
                            .build();

                    response.getImages().add(image);
                }
            });

            return new ArrayList<>(historyMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity history details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity history details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity history details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity history details");
        }
    }


    @Override
    public List<ActivityHistoryImageResponse> getAllActivityHistoryImages() {
        String GET_ACTIVITY_HISTORY_IMAGES = ActivitiesQueries.GET_ACTIVITY_HISTORY_IMAGES;

        try {
            LOGGER.info("Executing query to fetch all activity history images...");

            List<ActivityHistoryImageResponse> result = new ArrayList<>();

            jdbcTemplate.query(GET_ACTIVITY_HISTORY_IMAGES, rs -> {

                ActivityHistoryImageResponse.Activity activity = ActivityHistoryImageResponse.Activity.builder()
                        .activityId(rs.getLong("activity_id"))
                        .activityName(rs.getString("activity_name"))
                        .activityDescription(rs.getString("activity_description"))
                        .activityCategory(rs.getString("activity_category"))
                        .durationHours(rs.getInt("duration_hours"))
                        .priceLocal(rs.getDouble("price_local"))
                        .priceForeigners(rs.getDouble("price_foreigners"))
                        .minParticipate(rs.getInt("min_participate"))
                        .maxParticipate(rs.getInt("max_participate"))
                        .build();

                ActivityHistoryImageResponse.Schedule schedule = ActivityHistoryImageResponse.Schedule.builder()
                        .scheduleId(rs.getLong("schedule_id"))
                        .scheduleName(rs.getString("schedule_name"))
                        .scheduleDescription(rs.getString("schedule_description"))
                        .assumeStartDate(getLocalDateTime(rs, "assume_start_date"))
                        .assumeEndDate(getLocalDateTime(rs, "assume_end_date"))
                        .durationHoursStart(rs.getInt("duration_hours_start"))
                        .durationHoursEnd(rs.getInt("duration_hours_end"))
                        .scheduleSpecialNote(rs.getString("schedule_special_note"))
                        .build();

                ActivityHistoryImageResponse.History history = ActivityHistoryImageResponse.History.builder()
                        .historyId(rs.getLong("history_id"))
                        .historyName(rs.getString("history_name"))
                        .historyDescription(rs.getString("history_description"))
                        .numberOfParticipate(rs.getInt("number_of_participate"))
                        .activityStart(getLocalDateTime(rs, "activity_start"))
                        .activityEnd(getLocalDateTime(rs, "activity_end"))
                        .rating(rs.getDouble("rating"))
                        .historySpecialNote(rs.getString("history_special_note"))
                        .historyStatusName(rs.getString("history_status_name"))
                        .build();

                ActivityHistoryImageResponse image = ActivityHistoryImageResponse.builder()
                        .imageId(rs.getLong("image_id"))
                        .imageName(rs.getString("image_name"))
                        .imageDescription(rs.getString("image_description"))
                        .imageUrl(rs.getString("image_url"))
                        .imageStatusName(rs.getString("image_status_name"))
                        .imageCreatedByUsername(rs.getString("image_created_by_username"))
                        .imageUpdatedByUsername(rs.getString("image_updated_by_username"))
                        .imageTerminatedByUsername(rs.getString("image_terminated_by_username"))
                        .imageCreatedAt(getLocalDateTime(rs, "image_created_at"))
                        .imageUpdatedAt(getLocalDateTime(rs, "image_updated_at"))
                        .imageTerminatedAt(getLocalDateTime(rs, "image_terminated_at"))
                        .activity(activity)
                        .schedule(schedule)
                        .history(history)
                        .build();

                result.add(image);
            });

            return result;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity history images: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity history images from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity history images: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity history images");
        }
    }

    @Override
    public List<ActivityHistoryImageResponse> getActivityHistoryImagesById(String activityId) {
        String GET_ACTIVITY_HISTORY_IMAGES_BY_ID = ActivitiesQueries.GET_ACTIVITY_HISTORY_IMAGES_BY_ID;

        try {
            LOGGER.info("Fetching activity history images for activityId: {}", activityId);

            List<ActivityHistoryImageResponse> result = new ArrayList<>();

            jdbcTemplate.query(GET_ACTIVITY_HISTORY_IMAGES_BY_ID, new Object[]{activityId}, rs -> {

                ActivityHistoryImageResponse.Activity activity = ActivityHistoryImageResponse.Activity.builder()
                        .activityId(rs.getLong("activity_id"))
                        .activityName(rs.getString("activity_name"))
                        .activityDescription(rs.getString("activity_description"))
                        .activityCategory(rs.getString("activity_category"))
                        .durationHours(rs.getInt("duration_hours"))
                        .priceLocal(rs.getDouble("price_local"))
                        .priceForeigners(rs.getDouble("price_foreigners"))
                        .minParticipate(rs.getInt("min_participate"))
                        .maxParticipate(rs.getInt("max_participate"))
                        .build();

                ActivityHistoryImageResponse.Schedule schedule = ActivityHistoryImageResponse.Schedule.builder()
                        .scheduleId(rs.getLong("schedule_id"))
                        .scheduleName(rs.getString("schedule_name"))
                        .scheduleDescription(rs.getString("schedule_description"))
                        .assumeStartDate(getLocalDateTime(rs, "assume_start_date"))
                        .assumeEndDate(getLocalDateTime(rs, "assume_end_date"))
                        .durationHoursStart(rs.getInt("duration_hours_start"))
                        .durationHoursEnd(rs.getInt("duration_hours_end"))
                        .scheduleSpecialNote(rs.getString("schedule_special_note"))
                        .build();

                ActivityHistoryImageResponse.History history = ActivityHistoryImageResponse.History.builder()
                        .historyId(rs.getLong("history_id"))
                        .historyName(rs.getString("history_name"))
                        .historyDescription(rs.getString("history_description"))
                        .numberOfParticipate(rs.getInt("number_of_participate"))
                        .activityStart(getLocalDateTime(rs, "activity_start"))
                        .activityEnd(getLocalDateTime(rs, "activity_end"))
                        .rating(rs.getDouble("rating"))
                        .historySpecialNote(rs.getString("history_special_note"))
                        .historyStatusName(rs.getString("history_status_name"))
                        .build();

                ActivityHistoryImageResponse image = ActivityHistoryImageResponse.builder()
                        .imageId(rs.getLong("image_id"))
                        .imageName(rs.getString("image_name"))
                        .imageDescription(rs.getString("image_description"))
                        .imageUrl(rs.getString("image_url"))
                        .imageStatusName(rs.getString("image_status_name"))
                        .imageCreatedByUsername(rs.getString("image_created_by_username"))
                        .imageUpdatedByUsername(rs.getString("image_updated_by_username"))
                        .imageTerminatedByUsername(rs.getString("image_terminated_by_username"))
                        .imageCreatedAt(getLocalDateTime(rs, "image_created_at"))
                        .imageUpdatedAt(getLocalDateTime(rs, "image_updated_at"))
                        .imageTerminatedAt(getLocalDateTime(rs, "image_terminated_at"))
                        .activity(activity)
                        .schedule(schedule)
                        .history(history)
                        .build();

                result.add(image);
            });

            return result;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity history images: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity history images from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity history images: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity history images");
        }
    }

    @Override
    public ActivityWithParamsResponse getActivitiesWithParams(ActivityDataRequest activityDataRequest) {

        try {
            LOGGER.info("Executing query to fetch all activity categories...");
            int offset = (activityDataRequest.getPageNumber() - 1) * activityDataRequest.getPageSize();
            List<Long> activitiesIds = jdbcTemplate.query(
                    ActivitiesQueries.GET_ACTIVITY_IDS_WITH_FILTERS,
                    new Object[]{
                            activityDataRequest.getName(), activityDataRequest.getName(),
                            activityDataRequest.getMinPrice(), activityDataRequest.getMinPrice(),
                            activityDataRequest.getMaxPrice(), activityDataRequest.getMaxPrice(),
                            activityDataRequest.getDuration(), activityDataRequest.getDuration(),
                            activityDataRequest.getActivityCategory(), activityDataRequest.getActivityCategory(),
                            activityDataRequest.getSeason(), activityDataRequest.getSeason(),
                            activityDataRequest.getStatus(), activityDataRequest.getStatus(),
                            activityDataRequest.getPageSize(), offset
                    },
                    (rs, rowNum) -> rs.getLong("id")
            );

            Integer totalCount = jdbcTemplate.queryForObject(
                    ActivitiesQueries.GET_ACTIVITY_COUNT_WITH_FILTERS,
                    new Object[]{
                            activityDataRequest.getName(), activityDataRequest.getName(),
                            activityDataRequest.getMinPrice(), activityDataRequest.getMinPrice(),
                            activityDataRequest.getMaxPrice(), activityDataRequest.getMaxPrice(),
                            activityDataRequest.getDuration(), activityDataRequest.getDuration(),
                            activityDataRequest.getActivityCategory(), activityDataRequest.getActivityCategory(),
                            activityDataRequest.getSeason(), activityDataRequest.getSeason(),
                            activityDataRequest.getStatus(), activityDataRequest.getStatus()
                    },
                    Integer.class
            );



            if (activitiesIds.isEmpty()) {
                return null;
            }

            LOGGER.info(activitiesIds.toString());

            String inSql = String.join(",", Collections.nCopies(activitiesIds.size(), "?"));
            String sql = String.format(ActivitiesQueries.GET_ACTIVITIES_BY_IDS, inSql);

            List<ActivityResponseDto> result = jdbcTemplate.query(
                    sql,
                    activitiesIds.toArray(),
                    new ActivityRowMapper()
            );

            return new ActivityWithParamsResponse(totalCount,result);

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity categories");
        }
    }

    @Override
    public List<ActivityForTerminateResponse> getActivitiesForTerminate() {
        String GET_ACTIVE_ACTIVITIES_FOR_TERMINATE = ActivitiesQueries.GET_ACTIVE_ACTIVITIES_FOR_TERMINATE;

        try {
            return jdbcTemplate.query(
                    GET_ACTIVE_ACTIVITIES_FOR_TERMINATE,
                    new Object[]{CommonStatus.ACTIVE.toString()}, // parameter for cs.name = ?
                    (rs, rowNum) -> ActivityForTerminateResponse.builder()
                            .activityId(rs.getLong("id"))
                            .activityName(rs.getString("name"))
                            .build()
            );
        } catch (DataAccessException e) {
            LOGGER.error("Failed to fetch activities for terminate: ", e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities");
        }
    }

    @Override
    public void terminateActivity(ActivityTerminateRequest activityTerminateRequest, Long userId) {
        String ACTIVITY_TERMINATE = ActivitiesQueries.ACTIVITY_TERMINATE;
        try {
            jdbcTemplate.update(ACTIVITY_TERMINATE, new Object[]{CommonStatus.TERMINATED.toString(), userId, activityTerminateRequest.getActivityId()});
        } catch (DataAccessException tfe) {
            LOGGER.error(tfe.toString());
            throw new TerminateFailedErrorExceptionHandler(tfe.getMessage());

        } catch (Exception e) {
            LOGGER.error("Failed to terminate activity : ", e);
            throw new InternalServerErrorExceptionHandler("Failed to terminate activity");
        }
    }


    private LocalDateTime getLocalDateTime(ResultSet rs, String column) {
        try {
            Timestamp ts = rs.getTimestamp(column);
            return ts != null ? ts.toLocalDateTime() : null;
        } catch (SQLException e) {
            return null;
        }
    }

    private Double getDouble(ResultSet rs, String column) {
        try {
            double value = rs.getDouble(column);
            return rs.wasNull() ? null : value;
        } catch (SQLException e) {
            return null;
        }
    }


    private class ActivityRowMapper implements RowMapper<ActivityResponseDto> {
        @Override
        public ActivityResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActivityResponseDto activity = new ActivityResponseDto();

            // Map basic fields
            activity.setId(rs.getInt("id"));
            activity.setDestinationId(rs.getInt("destination_id"));
            activity.setName(rs.getString("name"));
            activity.setDescription(rs.getString("description"));
            activity.setActivitiesCategory(rs.getString("activities_category"));
            activity.setDurationHours(rs.getBigDecimal("duration_hours"));
            activity.setAvailableFrom(rs.getTime("available_from"));
            activity.setAvailableTo(rs.getTime("available_to"));
            activity.setPriceLocal(rs.getBigDecimal("price_local"));
            activity.setPriceForeigners(rs.getBigDecimal("price_foreigners"));
            activity.setMinParticipate(rs.getInt("min_participate"));
            activity.setMaxParticipate(rs.getInt("max_participate"));
            activity.setSeason(rs.getString("season"));
            activity.setStatus(rs.getString("status_name"));
            activity.setCreatedAt(rs.getTimestamp("created_at"));
            activity.setUpdatedAt(rs.getTimestamp("updated_at"));

            // Map category details
            activity.setCategoryName(rs.getString("category_name"));
            activity.setCategoryDescription(rs.getString("category_description"));

            // Parse JSON arrays
            try {
                // Parse schedules
                String schedulesJson = rs.getString("schedules");
                if (schedulesJson != null && !schedulesJson.equals("[]")) {
                    List<ActivityScheduleDto> schedules = objectMapper.readValue(
                            schedulesJson,
                            new TypeReference<List<ActivityScheduleDto>>() {
                            }
                    );
                    activity.setSchedules(schedules);
                } else {
                    activity.setSchedules(List.of());
                }

                // Parse requirements
                String requirementsJson = rs.getString("requirements");
                if (requirementsJson != null && !requirementsJson.equals("[]")) {
                    List<ActivityRequirementDto> requirements = objectMapper.readValue(
                            requirementsJson,
                            new TypeReference<List<ActivityRequirementDto>>() {
                            }
                    );
                    activity.setRequirements(requirements);
                } else {
                    activity.setRequirements(List.of());
                }

                // Parse images
                String imagesJson = rs.getString("images");
                if (imagesJson != null && !imagesJson.equals("[]")) {
                    List<ActivityImageDto> images = objectMapper.readValue(
                            imagesJson,
                            new TypeReference<List<ActivityImageDto>>() {
                            }
                    );
                    activity.setImages(images);
                } else {
                    activity.setImages(List.of());
                }

            } catch (Exception e) {
                LOGGER.error("Error parsing JSON data for activity id: {}", activity.getId(), e);
                throw new SQLException("Error parsing JSON data", e);
            }

            return activity;
        }
    }
}