package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityReviewDetailsResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.ActivitiesQueries;
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