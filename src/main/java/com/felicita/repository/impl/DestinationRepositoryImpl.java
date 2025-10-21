package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.*;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.DestinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DestinationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DestinationResponseDto> getAllDestinations() {
        String GET_ALL_DESTINATIONS = DestinationQueries.GET_ALL_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            return jdbcTemplate.query(GET_ALL_DESTINATIONS, rs -> {
                Map<Integer, DestinationResponseDto> destinationMap = new HashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("destination_id");

                    // Check if destination already exists
                    DestinationResponseDto destination = destinationMap.get(destinationId);
                    if (destination == null) {
                        destination = new DestinationResponseDto();
                        destination.setDestinationId(destinationId);
                        destination.setDestinationName(rs.getString("destination_name"));
                        destination.setDestinationDescription(rs.getString("destination_description"));
                        destination.setLocation(rs.getString("location"));
                        destination.setLatitude(rs.getObject("latitude", Double.class));
                        destination.setLongitude(rs.getObject("longitude", Double.class));

                        destination.setCategoryName(rs.getString("category_name"));
                        destination.setCategoryDescription(rs.getString("category_description"));
                        destination.setStatusName(rs.getString("status_name"));

                        destination.setActivities(new ArrayList<>());
                        destination.setImages(new ArrayList<>());

                        destinationMap.put(destinationId, destination);
                    }

                    // Add activity if exists
                    int activityId = rs.getInt("activity_id");
                    if (activityId != 0 && rs.getString("activity_name") != null) {
                        DestinationActivityResponseDto activity = new DestinationActivityResponseDto();
                        activity.setActivityId(activityId);
                        activity.setActivityName(rs.getString("activity_name"));
                        activity.setActivityDescription(rs.getString("activity_description"));
                        activity.setActivitiesCategory(rs.getString("activities_category"));
                        activity.setDurationHours(rs.getObject("duration_hours", Double.class));
                        activity.setAvailableFrom(rs.getString("available_from"));
                        activity.setAvailableTo(rs.getString("available_to"));
                        activity.setPriceLocal(rs.getObject("price_local", Double.class));
                        activity.setPriceForeigners(rs.getObject("price_foreigners", Double.class));
                        activity.setMinParticipate(rs.getObject("min_participate", Integer.class));
                        activity.setMaxParticipate(rs.getObject("max_participate", Integer.class));
                        activity.setSeason(rs.getString("season"));

                        // Avoid duplicates
                        if (destination.getActivities().stream().noneMatch(a -> a.getActivityId() == activityId)) {
                            destination.getActivities().add(activity);
                        }
                    }

                    // Add image if exists
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        DestionationImageResponseDto image = new DestionationImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));

                        // Avoid duplicates
                        if (destination.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            destination.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<DestinationCategoryResponseDto> getAllDestinationsCategories() {
        String GET_ALL_DESTINATIONS_CATEGORIES = DestinationQueries.GET_ALL_DESTINATIONS_CATEGORIES;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            return jdbcTemplate.query(GET_ALL_DESTINATIONS_CATEGORIES, rs -> {
                Map<Integer, DestinationCategoryResponseDto> categoryMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int categoryId = rs.getInt("category_id");

                    // If category not seen before, create a new DTO
                    DestinationCategoryResponseDto category = categoryMap.computeIfAbsent(categoryId, id ->
                            {
                                try {
                                    return new DestinationCategoryResponseDto(
                                            id,
                                            rs.getString("category"),
                                            rs.getString("category_description"),
                                            rs.getString("category_status"),
                                            rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                                            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                                            new ArrayList<>()
                                    );
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

                    int imageId = rs.getInt("image_id");
                    if (imageId > 0) {
                        DestinationsCategoryImageResponseDto image = new DestinationsCategoryImageResponseDto(
                                imageId,
                                rs.getString("image_name"),
                                rs.getString("image_description"),
                                rs.getString("image_url"),
                                rs.getString("image_status"),
                                rs.getTimestamp("image_created_at") != null ? rs.getTimestamp("image_created_at").toLocalDateTime() : null
                        );
                        category.getImages().add(image);
                    }
                }

                return new ArrayList<>(categoryMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<PopularDestinationResponseDto> getPopularDestinations() {
        String GET_POPULAR_DESTINATIONS = DestinationQueries.GET_POPULAR_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all popular destinations...");

            return jdbcTemplate.query(GET_POPULAR_DESTINATIONS, rs -> {
                Map<Integer, PopularDestinationResponseDto> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int popularId = rs.getInt("popular_id");

                    // If we haven't added this destination yet → create DTO
                    PopularDestinationResponseDto popularDestination = destinationMap.computeIfAbsent(popularId, id ->
                            {
                                try {
                                    return new PopularDestinationResponseDto(
                                            rs.getInt("popular_id"),
                                            rs.getDouble("rating"),
                                            rs.getInt("popularity"),
                                            rs.getTimestamp("popular_created_at") != null ? rs.getTimestamp("popular_created_at").toLocalDateTime() : null,

                                            rs.getInt("destination_id"),
                                            rs.getString("destination_name"),
                                            rs.getString("destination_description"),
                                            rs.getString("location"),
                                            rs.getObject("latitude") != null ? rs.getDouble("latitude") : null,
                                            rs.getObject("longitude") != null ? rs.getDouble("longitude") : null,
                                            rs.getString("destination_status"),

                                            rs.getInt("category_id"),
                                            rs.getString("category_name"),
                                            rs.getString("category_description"),
                                            rs.getString("category_status"),

                                            new ArrayList<>() // empty images list initially
                                    );
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

                    // Handle image (if exists)
                    int imageId = rs.getInt("image_id");
                    if (imageId > 0) {
                        DestinationImageResponseDto image = new DestinationImageResponseDto(
                                imageId,
                                rs.getString("image_name"),
                                rs.getString("image_description"),
                                rs.getString("image_url"),
                                rs.getString("image_status"),
                                rs.getTimestamp("popular_created_at") != null ? rs.getTimestamp("popular_created_at").toLocalDateTime() : null
                        );
                        popularDestination.getImages().add(image);
                    }
                }

                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching popular destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch popular destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching popular destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching popular destinations");
        }
    }

    @Override
    public List<TrendingDestinationResponseDto> getTrendingDestinations() {
        String GET_TRENDING_DESTINATIONS = DestinationQueries.GET_TRENDING_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all trending destinations...");

            return jdbcTemplate.query(GET_TRENDING_DESTINATIONS, rs -> {
                Map<Integer, TrendingDestinationResponseDto> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int popularId = rs.getInt("popular_id");

                    // Create or get the destination DTO
                    TrendingDestinationResponseDto trendingDestination = destinationMap.computeIfAbsent(popularId, id ->
                            {
                                try {
                                    return new TrendingDestinationResponseDto(
                                            rs.getInt("popular_id"),
                                            rs.getDouble("rating"),
                                            rs.getInt("popularity"),
                                            rs.getTimestamp("popular_created_at") != null ? rs.getTimestamp("popular_created_at").toLocalDateTime() : null,

                                            rs.getInt("destination_id"),
                                            rs.getString("destination_name"),
                                            rs.getString("destination_description"),
                                            rs.getString("location"),
                                            rs.getObject("latitude") != null ? rs.getDouble("latitude") : null,
                                            rs.getObject("longitude") != null ? rs.getDouble("longitude") : null,
                                            rs.getString("destination_status"),

                                            rs.getInt("category_id"),
                                            rs.getString("category_name"),
                                            rs.getString("category_description"),
                                            rs.getString("category_status"),

                                            new ArrayList<>(), // images list
                                            new ArrayList<>()  // activities list
                                    );
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

                    // Handle image
                    int imageId = rs.getInt("image_id");
                    if (!rs.wasNull() && imageId > 0) {
                        DestinationImageResponseDto image = new DestinationImageResponseDto(
                                imageId,
                                rs.getString("image_name"),
                                rs.getString("image_description"),
                                rs.getString("image_url"),
                                rs.getString("image_status"),
                                rs.getTimestamp("image_created_at") != null ? rs.getTimestamp("image_created_at").toLocalDateTime() : null
                        );
                        // Prevent duplicate images
                        if (trendingDestination.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            trendingDestination.getImages().add(image);
                        }
                    }

                    // Handle activity
                    int activityId = rs.getInt("activity_id");
                    if (!rs.wasNull() && activityId > 0) {
                        DestinationActivityResponseDto activity = new DestinationActivityResponseDto(
                                activityId,
                                rs.getString("activity_name"),
                                rs.getString("activity_description"),
                                rs.getString("activities_category"),
                                rs.getObject("duration_hours") != null ? rs.getDouble("duration_hours") : null,
                                rs.getString("available_from"),
                                rs.getString("available_to"),
                                rs.getObject("price_local") != null ? rs.getDouble("price_local") : null,
                                rs.getObject("price_foreigners") != null ? rs.getDouble("price_foreigners") : null,
                                rs.getObject("min_participate") != null ? rs.getInt("min_participate") : null,
                                rs.getObject("max_participate") != null ? rs.getInt("max_participate") : null,
                                rs.getString("season")
                        );
                        // Prevent duplicate activities
                        if (trendingDestination.getActivities().stream().noneMatch(a -> a.getActivityId() == activityId)) {
                            trendingDestination.getActivities().add(activity);
                        }
                    }
                }

                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching trending destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch trending destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching trending destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching trending destinations");
        }
    }

    @Override
    public List<DestinationsForTourMapDto> getDestinationsForTourMap() {
        String GET_DESTINATIONS_FOR_TOUR_MAP = DestinationQueries.GET_DESTINATIONS_FOR_TOUR_MAP;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            // Use LinkedHashMap to preserve insertion order
            Map<Long, DestinationsForTourMapDto> destinationMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_DESTINATIONS_FOR_TOUR_MAP, rs -> {
                Long destinationId = rs.getLong("destination_id");

                // Check if destination already exists in map
                DestinationsForTourMapDto dto = destinationMap.get(destinationId);
                if (dto == null) {
                    dto = new DestinationsForTourMapDto();
                    dto.setDestinationId(destinationId);
                    dto.setDestinationName(rs.getString("destination_name"));
                    dto.setDestinationDescription(rs.getString("destination_description"));
                    dto.setDestinationStatus(rs.getString("destination_status"));
                    dto.setDestinationCategory(rs.getString("destination_category"));
                    dto.setDestinationCategoryStatus(rs.getString("destination_category_status"));
                    dto.setDestinationLocation(rs.getString("destination_location"));
                    dto.setDestinationLatitude(rs.getObject("destination_latitude") != null ? rs.getDouble("destination_latitude") : null);
                    dto.setDestinationLongitude(rs.getObject("destination_longitude") != null ? rs.getDouble("destination_longitude") : null);
                    dto.setDestinationCreatedAt(rs.getTimestamp("destination_created_at") != null
                            ? rs.getTimestamp("destination_created_at").toLocalDateTime()
                            : null);
                    dto.setDestinationCreatedBy(rs.getObject("destination_created_by") != null ? rs.getLong("destination_created_by") : null);

                    // Initialize empty lists for images
                    dto.setDestinationImagesForTourMapDtos(new ArrayList<>());
                    dto.setDestinationCategoryImageForTourMapDtos(new ArrayList<>());

                    // Add to map
                    destinationMap.put(destinationId, dto);
                }

                // --- Destination Image Mapping ---
                Long destinationImageId = rs.getObject("destination_image_id") != null ? rs.getLong("destination_image_id") : null;
                if (destinationImageId != null) {
                    DestinationImagesForTourMapDto imageDto = new DestinationImagesForTourMapDto();
                    imageDto.setId(destinationImageId);
                    imageDto.setName(rs.getString("destination_image_name"));
                    imageDto.setDescription(rs.getString("destination_image_description"));
                    imageDto.setImageUrl(rs.getString("destination_image_url"));
                    imageDto.setStatus(rs.getString("destination_image_status"));

                    // Add to list only if not already present
                    if (!dto.getDestinationImagesForTourMapDtos().stream()
                            .anyMatch(img -> img.getId().equals(destinationImageId))) {
                        dto.getDestinationImagesForTourMapDtos().add(imageDto);
                    }
                }

                // --- Destination Category Image Mapping ---
                Long destinationCategoryImageId = rs.getObject("destination_category_image_id") != null
                        ? rs.getLong("destination_category_image_id")
                        : null;
                if (destinationCategoryImageId != null) {
                    DestinationCategoryImageForTourMapDto categoryImageDto = new DestinationCategoryImageForTourMapDto();
                    categoryImageDto.setId(destinationCategoryImageId);
                    categoryImageDto.setName(rs.getString("destination_category_image_name"));
                    categoryImageDto.setDescription(rs.getString("destination_category_image_description"));
                    categoryImageDto.setImageUrl(rs.getString("destination_category_image_url"));
                    categoryImageDto.setStatus(rs.getString("destination_category_image_status"));

                    if (!dto.getDestinationCategoryImageForTourMapDtos().stream()
                            .anyMatch(img -> img.getId().equals(destinationCategoryImageId))) {
                        dto.getDestinationCategoryImageForTourMapDtos().add(categoryImageDto);
                    }
                }
            });

            // Convert map values to list
            return new ArrayList<>(destinationMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<DestinationResponseDto> getDestinationDetailsByTourId(String tourId) {
        String GET_ALL_DESTINATIONS = DestinationQueries.GET_ALL_DESTINATIONS_BY_TOUR_ID;
        try {
            LOGGER.info("Executing query to fetch all destinations for tourId: {}", tourId);

            return jdbcTemplate.query(GET_ALL_DESTINATIONS, new Object[]{tourId}, rs -> {
                Map<Integer, DestinationResponseDto> destinationMap = new HashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("destination_id");

                    // Fetch or create destination
                    DestinationResponseDto destination = destinationMap.computeIfAbsent(destinationId, id -> {
                        DestinationResponseDto dto = new DestinationResponseDto();
                        dto.setDestinationId(id);
                        try {
                            dto.setDestinationName(rs.getString("destination_name"));
                            dto.setDestinationDescription(rs.getString("destination_description"));
                            dto.setLocation(rs.getString("location"));
                            dto.setLatitude(rs.getObject("latitude", Double.class));
                            dto.setLongitude(rs.getObject("longitude", Double.class));
                            dto.setCategoryName(rs.getString("category_name"));
                            dto.setCategoryDescription(rs.getString("category_description"));
                            dto.setStatusName(rs.getString("status_name"));
                            dto.setActivities(new ArrayList<>());
                            dto.setImages(new ArrayList<>());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        return dto;
                    });

                    // Add activity if exists
                    int activityId = rs.getInt("activity_id");
                    if (activityId != 0 && rs.getString("activity_name") != null) {
                        boolean activityExists = destination.getActivities()
                                .stream()
                                .anyMatch(a -> a.getActivityId() == activityId);
                        if (!activityExists) {
                            DestinationActivityResponseDto activity = new DestinationActivityResponseDto();
                            activity.setActivityId(activityId);
                            activity.setActivityName(rs.getString("activity_name"));
                            activity.setActivityDescription(rs.getString("activity_description"));
                            activity.setActivitiesCategory(rs.getString("activities_category"));
                            activity.setDurationHours(rs.getObject("duration_hours", Double.class));
                            activity.setAvailableFrom(rs.getString("available_from"));
                            activity.setAvailableTo(rs.getString("available_to"));
                            activity.setPriceLocal(rs.getObject("price_local", Double.class));
                            activity.setPriceForeigners(rs.getObject("price_foreigners", Double.class));
                            activity.setMinParticipate(rs.getObject("min_participate", Integer.class));
                            activity.setMaxParticipate(rs.getObject("max_participate", Integer.class));
                            activity.setSeason(rs.getString("season"));

                            destination.getActivities().add(activity);
                        }
                    }

                    // Add image if exists
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        boolean imageExists = destination.getImages()
                                .stream()
                                .anyMatch(i -> i.getImageId() == imageId);
                        if (!imageExists) {
                            DestionationImageResponseDto image = new DestionationImageResponseDto();
                            image.setImageId(imageId);
                            image.setImageName(rs.getString("image_name"));
                            image.setImageDescription(rs.getString("image_description"));
                            image.setImageUrl(rs.getString("image_url"));

                            destination.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public List<DestinationReviewDetailsResponse> getDestinationReviewDetailsById(String destinationId) {
        String GET_DESTINATIONS_REVIEW_DETAILS_BY_ID = DestinationQueries.GET_DESTINATIONS_REVIEW_DETAILS_BY_ID;

        try {
            // Map to hold reviews by reviewId to aggregate images, reactions, comments
            Map<Integer, DestinationReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_DESTINATIONS_REVIEW_DETAILS_BY_ID, new Object[]{destinationId}, rs -> {
                Integer reviewId = rs.getInt("review_id");

                // Fetch or create review object
                DestinationReviewDetailsResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = DestinationReviewDetailsResponse.builder()
                            .reviewId(reviewId)
                            .destinationId(rs.getInt("destination_id"))
                            .destinationName(rs.getString("destination_name"))
                            .reviewUserId(rs.getInt("review_user_id"))
                            .reviewUserName(rs.getString("review_user_name"))
                            .reviewText(rs.getString("review_text"))
                            .reviewRating(rs.getBigDecimal("review_rating"))
                            .reviewStatus(rs.getString("review_status"))
                            .reviewCreatedBy(rs.getInt("review_created_by"))
                            .reviewCreatedAt(rs.getTimestamp("review_created_at").toLocalDateTime())
                            .reviewUpdatedBy(rs.getInt("review_updated_by"))
                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ?
                                    rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();
                    reviewMap.put(reviewId, review);
                }

                // Add image if exists
                Integer imageId = rs.getObject("image_id", Integer.class);
                if (imageId != null && review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {
                    DestinationReviewDetailsResponse.Image image = DestinationReviewDetailsResponse.Image.builder()
                            .imageId(imageId)
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .imageStatus(rs.getString("image_status"))
                            .imageCreatedBy(rs.getInt("image_created_by"))
                            .imageCreatedAt(rs.getTimestamp("image_created_at") != null ?
                                    rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                            .build();
                    review.getImages().add(image);
                }

                // Add reaction if exists
                Integer reactionId = rs.getObject("review_reaction_id", Integer.class);
                if (reactionId != null && review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reactionId))) {
                    DestinationReviewDetailsResponse.Reaction reaction = DestinationReviewDetailsResponse.Reaction.builder()
                            .reviewReactionId(reactionId)
                            .reactionReviewId(rs.getInt("reaction_review_id"))
                            .reactionUserId(rs.getInt("reaction_user_id"))
                            .reactionUserName(rs.getString("reaction_user_name"))
                            .reactionType(rs.getString("reaction_type"))
                            .reviewReactionStatus(rs.getString("review_reaction_status"))
                            .reactionCreatedAt(rs.getTimestamp("reaction_created_at") != null ?
                                    rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                            .build();
                    review.getReactions().add(reaction);
                }

                // Add comment if exists
                Integer commentId = rs.getObject("comment_id", Integer.class);
                DestinationReviewDetailsResponse.Comment comment = null;
                if (commentId != null) {
                    comment = review.getComments().stream()
                            .filter(c -> c.getCommentId().equals(commentId))
                            .findFirst()
                            .orElse(null);

                    if (comment == null) {
                        comment = DestinationReviewDetailsResponse.Comment.builder()
                                .commentId(commentId)
                                .commentReviewId(rs.getInt("comment_review_id"))
                                .commentUserId(rs.getInt("comment_user_id"))
                                .commentUserName(rs.getString("comment_user_name"))
                                .parentCommentId(rs.getObject("parent_comment_id", Integer.class))
                                .commentText(rs.getString("comment_text"))
                                .commentStatus(rs.getString("comment_status"))
                                .commentCreatedAt(rs.getTimestamp("comment_created_at") != null ?
                                        rs.getTimestamp("comment_created_at").toLocalDateTime() : null)
                                .commentCreatedBy(rs.getInt("comment_created_by"))
                                .commentReactions(new ArrayList<>())
                                .build();
                        review.getComments().add(comment);
                    }
                }

                // Add comment reaction if exists
                Integer commentReactionId = rs.getObject("comment_reaction_id", Integer.class);
                if (commentReactionId != null && comment != null &&
                        comment.getCommentReactions().stream().noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {
                    DestinationReviewDetailsResponse.Comment.CommentReaction commentReaction =
                            DestinationReviewDetailsResponse.Comment.CommentReaction.builder()
                                    .commentReactionId(commentReactionId)
                                    .commentReactionCommentId(rs.getInt("comment_reaction_comment_id"))
                                    .commentReactionUserId(rs.getInt("comment_reaction_user_id"))
                                    .commentReactionUserName(rs.getString("comment_reaction_user_name"))
                                    .commentReactionType(rs.getString("comment_reaction_type"))
                                    .commentReactionStatus(rs.getString("comment_reaction_status"))
                                    .commentReactionCreatedBy(rs.getInt("comment_reaction_created_by"))
                                    .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at") != null ?
                                            rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                    .build();
                    comment.getCommentReactions().add(commentReaction);
                }
            });

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }


    @Override
    public List<DestinationReviewDetailsResponse> getAllDestinationsReviewDetails() {
        String GET_DESTINATIONS_REVIEW_DETAILS = DestinationQueries.GET_DESTINATIONS_REVIEW_DETAILS;

        try {
            // Map to hold reviews by reviewId to aggregate images, reactions, comments
            Map<Integer, DestinationReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_DESTINATIONS_REVIEW_DETAILS, rs -> {
                Integer reviewId = rs.getInt("review_id");

                // Fetch or create review object
                DestinationReviewDetailsResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = DestinationReviewDetailsResponse.builder()
                            .reviewId(reviewId)
                            .destinationId(rs.getInt("destination_id"))
                            .destinationName(rs.getString("destination_name"))
                            .reviewUserId(rs.getInt("review_user_id"))
                            .reviewUserName(rs.getString("review_user_name"))
                            .reviewText(rs.getString("review_text"))
                            .reviewRating(rs.getBigDecimal("review_rating"))
                            .reviewStatus(rs.getString("review_status"))
                            .reviewCreatedBy(rs.getInt("review_created_by"))
                            .reviewCreatedAt(rs.getTimestamp("review_created_at").toLocalDateTime())
                            .reviewUpdatedBy(rs.getInt("review_updated_by"))
                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ?
                                    rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();
                    reviewMap.put(reviewId, review);
                }

                // Add image if exists
                Integer imageId = rs.getObject("image_id", Integer.class);
                if (imageId != null && review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {
                    DestinationReviewDetailsResponse.Image image = DestinationReviewDetailsResponse.Image.builder()
                            .imageId(imageId)
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .imageStatus(rs.getString("image_status"))
                            .imageCreatedBy(rs.getInt("image_created_by"))
                            .imageCreatedAt(rs.getTimestamp("image_created_at") != null ?
                                    rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                            .build();
                    review.getImages().add(image);
                }

                // Add reaction if exists
                Integer reactionId = rs.getObject("review_reaction_id", Integer.class);
                if (reactionId != null && review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reactionId))) {
                    DestinationReviewDetailsResponse.Reaction reaction = DestinationReviewDetailsResponse.Reaction.builder()
                            .reviewReactionId(reactionId)
                            .reactionReviewId(rs.getInt("reaction_review_id"))
                            .reactionUserId(rs.getInt("reaction_user_id"))
                            .reactionUserName(rs.getString("reaction_user_name"))
                            .reactionType(rs.getString("reaction_type"))
                            .reviewReactionStatus(rs.getString("review_reaction_status"))
                            .reactionCreatedAt(rs.getTimestamp("reaction_created_at") != null ?
                                    rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                            .build();
                    review.getReactions().add(reaction);
                }

                // Add comment if exists
                Integer commentId = rs.getObject("comment_id", Integer.class);
                DestinationReviewDetailsResponse.Comment comment = null;
                if (commentId != null) {
                    comment = review.getComments().stream()
                            .filter(c -> c.getCommentId().equals(commentId))
                            .findFirst()
                            .orElse(null);

                    if (comment == null) {
                        comment = DestinationReviewDetailsResponse.Comment.builder()
                                .commentId(commentId)
                                .commentReviewId(rs.getInt("comment_review_id"))
                                .commentUserId(rs.getInt("comment_user_id"))
                                .commentUserName(rs.getString("comment_user_name"))
                                .parentCommentId(rs.getObject("parent_comment_id", Integer.class))
                                .commentText(rs.getString("comment_text"))
                                .commentStatus(rs.getString("comment_status"))
                                .commentCreatedAt(rs.getTimestamp("comment_created_at") != null ?
                                        rs.getTimestamp("comment_created_at").toLocalDateTime() : null)
                                .commentCreatedBy(rs.getInt("comment_created_by"))
                                .commentReactions(new ArrayList<>())
                                .build();
                        review.getComments().add(comment);
                    }
                }

                // Add comment reaction if exists
                Integer commentReactionId = rs.getObject("comment_reaction_id", Integer.class);
                if (commentReactionId != null && comment != null &&
                        comment.getCommentReactions().stream().noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {
                    DestinationReviewDetailsResponse.Comment.CommentReaction commentReaction =
                            DestinationReviewDetailsResponse.Comment.CommentReaction.builder()
                                    .commentReactionId(commentReactionId)
                                    .commentReactionCommentId(rs.getInt("comment_reaction_comment_id"))
                                    .commentReactionUserId(rs.getInt("comment_reaction_user_id"))
                                    .commentReactionUserName(rs.getString("comment_reaction_user_name"))
                                    .commentReactionType(rs.getString("comment_reaction_type"))
                                    .commentReactionStatus(rs.getString("comment_reaction_status"))
                                    .commentReactionCreatedBy(rs.getInt("comment_reaction_created_by"))
                                    .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at") != null ?
                                            rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                    .build();
                    comment.getCommentReactions().add(commentReaction);
                }
            });

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }

    @Override
    public DestinationResponseDto getDestinationDetailsById(String destinationId) {
        String GET_DESTINATION_DETAILS_BY_ID = DestinationQueries.GET_DESTINATION_DETAILS_BY_ID;

        try {
            LOGGER.info("Executing query to fetch destination details...");

            return jdbcTemplate.query(GET_DESTINATION_DETAILS_BY_ID, new Object[]{destinationId}, rs -> {
                DestinationResponseDto destination = null;
                Map<Integer, DestinationActivityResponseDto> activityMap = new LinkedHashMap<>();
                Map<Integer, DestionationImageResponseDto> imageMap = new LinkedHashMap<>();

                while (rs.next()) {
                    if (destination == null) {
                        destination = new DestinationResponseDto();
                        destination.setDestinationId(rs.getInt("destination_id"));
                        destination.setDestinationName(rs.getString("destination_name"));
                        destination.setDestinationDescription(rs.getString("destination_description"));
                        destination.setLocation(rs.getString("location"));
                        destination.setLatitude(rs.getDouble("latitude"));
                        destination.setLongitude(rs.getDouble("longitude"));
                        destination.setCategoryName(rs.getString("category_name"));
                        destination.setCategoryDescription(rs.getString("category_description"));
                        destination.setStatusName(rs.getString("status_name"));
                    }

                    // Map activity
                    int activityId = rs.getInt("activity_id");
                    if (activityId != 0 && !activityMap.containsKey(activityId)) {
                        DestinationActivityResponseDto activity = new DestinationActivityResponseDto();
                        activity.setActivityId(activityId);
                        activity.setActivityName(rs.getString("activity_name"));
                        activity.setActivityDescription(rs.getString("activity_description"));
                        activity.setActivitiesCategory(rs.getString("activities_category"));
                        activity.setDurationHours(rs.getDouble("duration_hours"));
                        activity.setAvailableFrom(rs.getString("available_from"));
                        activity.setAvailableTo(rs.getString("available_to"));
                        activity.setPriceLocal(rs.getDouble("price_local"));
                        activity.setPriceForeigners(rs.getDouble("price_foreigners"));
                        activity.setMinParticipate(rs.getInt("min_participate"));
                        activity.setMaxParticipate(rs.getInt("max_participate"));
                        activity.setSeason(rs.getString("season"));
                        activityMap.put(activityId, activity);
                    }

                    // Map image
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && !imageMap.containsKey(imageId)) {
                        DestionationImageResponseDto image = new DestionationImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        imageMap.put(imageId, image);
                    }
                }

                if (destination != null) {
                    destination.setActivities(new ArrayList<>(activityMap.values()));
                    destination.setImages(new ArrayList<>(imageMap.values()));
                }

                return destination;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }



}
