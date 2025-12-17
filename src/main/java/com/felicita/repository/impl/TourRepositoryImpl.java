package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.response.*;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.TourQueries;
import com.felicita.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.felicita.queries.TourQueries.GET_PAGINATED_TOUR_IDS;
import static com.felicita.queries.TourQueries.GET_TOURS_BY_IDS;

@Repository
public class TourRepositoryImpl implements TourRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TourRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<TourResponseDto> getAllTours() {
        String GET_ALL_TOURS = TourQueries.GET_ALL_TOURS;

        try {
            return jdbcTemplate.query(GET_ALL_TOURS, (ResultSet rs) -> {
                Map<Integer, TourResponseDto> tourMap = new HashMap<>();

                while (rs.next()) {
                    int tourId = rs.getInt("tour_id");

                    // If we haven't seen this tour before, create it
                    TourResponseDto tour = tourMap.get(tourId);
                    if (tour == null) {
                        tour = new TourResponseDto();
                        tour.setTourId(tourId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setDuration(rs.getObject("duration", Integer.class));
                        tour.setLatitude(rs.getObject("latitude", Double.class));
                        tour.setLongitude(rs.getObject("longitude", Double.class));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));

                        tour.setTourTypeName(rs.getString("tour_type_name"));
                        tour.setTourTypeDescription(rs.getString("tour_type_description"));
                        tour.setTourCategoryName(rs.getString("tour_category_name"));
                        tour.setTourCategoryDescription(rs.getString("tour_category_description"));
                        tour.setSeasonName(rs.getString("season_name"));
                        tour.setSeasonDescription(rs.getString("season_description"));
                        tour.setStatusName(rs.getString("status_name"));

                        tour.setSchedules(new ArrayList<>());
                        tour.setImages(new ArrayList<>());

                        tourMap.put(tourId, tour);
                    }

                    // Handle schedules
                    int scheduleId = rs.getInt("schedule_id");
                    if (scheduleId != 0 && rs.getString("schedule_name") != null) {
                        TourScheduleResponseDto schedule = new TourScheduleResponseDto();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                        schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                        schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                        schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                        schedule.setSpecialNote(rs.getString("special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));

                        // Avoid duplicates
                        if (tour.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            tour.getSchedules().add(schedule);
                        }
                    }

                    // Handle images
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        TourImageResponseDto image = new TourImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));

                        // Avoid duplicates
                        if (tour.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            tour.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(tourMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

    @Override
    public List<PopularTourResponseDto> getPopularTours() {
        String GET_POPULAR_TOURS = TourQueries.GET_POPULAR_TOURS;

        try {
            return jdbcTemplate.query(GET_POPULAR_TOURS, rs -> {
                // Map to hold tours by tourId
                Map<Integer, PopularTourResponseDto> tourMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int tourId = rs.getInt("tour_id");

                    // Create or get existing tour DTO
                    PopularTourResponseDto tour = tourMap.computeIfAbsent(tourId, id -> {
                        try {
                            return new PopularTourResponseDto(
                                    tourId,
                                    rs.getString("tour_name"),
                                    rs.getString("tour_description"),
                                    rs.getObject("tour_duration") != null ? rs.getInt("tour_duration") : null,
                                    rs.getObject("latitude") != null ? rs.getDouble("latitude") : null,
                                    rs.getObject("longitude") != null ? rs.getDouble("longitude") : null,
                                    rs.getString("start_location"),
                                    rs.getString("end_location"),
                                    rs.getString("tour_type"),
                                    rs.getString("tour_category"),
                                    rs.getString("season"),
                                    rs.getString("tour_status"),
                                    new ArrayList<>(), // images list
                                    new ArrayList<>()  // schedules list
                            );
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    // Handle images (avoid duplicates)
                    String imageUrl = rs.getString("tour_image");
                    String imageName = rs.getString("tour_name"); // careful: this was reused in SQL as ti.name AS tour_name
                    if (imageUrl != null && imageName != null) {
                        boolean imageExists = tour.getImages().stream()
                                .anyMatch(img -> img.getImageUrl().equals(imageUrl));
                        if (!imageExists) {
                            PopularTourImagesDto image = new PopularTourImagesDto(imageName, imageUrl);
                            tour.getImages().add(image);
                        }
                    }

                    // Handle schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (!rs.wasNull()) {
                        popularTourScheduleResponseDto schedule = tour.getSchedules().stream()
                                .filter(s -> s.getScheduleId() == scheduleId)
                                .findFirst()
                                .orElseGet(() -> {
                                    popularTourScheduleResponseDto s;
                                    try {
                                        s = new popularTourScheduleResponseDto(
                                                scheduleId,
                                                rs.getString("schedule_name"),
                                                rs.getObject("assume_start_date") != null ? rs.getDate("assume_start_date").toLocalDate() : null,
                                                rs.getObject("assume_end_date") != null ? rs.getDate("assume_end_date").toLocalDate() : null,
                                                rs.getObject("duration_start") != null ? rs.getInt("duration_start") : null,
                                                rs.getObject("duration_end") != null ? rs.getInt("duration_end") : null,
                                                rs.getString("special_note"),
                                                rs.getString("schedule_description"),
                                                rs.getString("schedule_status"),
                                                new ArrayList<>(), // destinations
                                                new ArrayList<>()  // reviews
                                        );
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    tour.getSchedules().add(s);
                                    return s;
                                });

                        // Handle destination
                        int destinationId = rs.getInt("destination_id");
                        if (!rs.wasNull() && destinationId > 0) {
                            if (schedule.getDestinations().stream().noneMatch(d -> d.getDestinationId() == destinationId)) {
                                TourDestinationResponseDto destination = new TourDestinationResponseDto(
                                        destinationId,
                                        rs.getString("destination_name"),
                                        rs.getString("destination_description"),
                                        rs.getString("destination_location"),
                                        rs.getString("destination_status")
                                );
                                schedule.getDestinations().add(destination);
                            }
                        }

                        // Handle review
                        int reviewId = rs.getInt("review_id");
                        if (!rs.wasNull() && reviewId > 0) {
                            if (schedule.getReviews().stream().noneMatch(r -> r.getReviewId() == reviewId)) {
                                TourReviewResponseDto review = new TourReviewResponseDto(
                                        reviewId,
                                        rs.getString("reviewer_name"),
                                        rs.getString("review"),
                                        rs.getObject("rating") != null ? rs.getDouble("rating") : null,
                                        rs.getString("review_description"),
                                        rs.getObject("number_of_participate") != null ? rs.getInt("number_of_participate") : null,
                                        rs.getString("review_status"),
                                        rs.getTimestamp("review_created_at") != null ? rs.getTimestamp("review_created_at").toLocalDateTime() : null
                                );
                                schedule.getReviews().add(review);
                            }
                        }
                    }
                }

                return new ArrayList<>(tourMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

    @Override
    public TourResponseDto getTourDetailsById(String tourId) {
        String GET_TOUR_DETAILS_BY_ID = TourQueries.GET_TOUR_DETAILS_BY_ID;

        try {
            return jdbcTemplate.query(GET_TOUR_DETAILS_BY_ID, new Object[]{tourId}, (ResultSet rs) -> {
                Map<Integer, TourResponseDto> tourMap = new HashMap<>();

                while (rs.next()) {
                    int tId = rs.getInt("tour_id");

                    // Create or get existing tour
                    TourResponseDto tour = tourMap.get(tId);
                    if (tour == null) {
                        tour = new TourResponseDto();
                        tour.setTourId(tId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setDuration(rs.getObject("duration", Integer.class));
                        tour.setLatitude(rs.getObject("latitude", Double.class));
                        tour.setLongitude(rs.getObject("longitude", Double.class));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));

                        // Join data
                        tour.setTourTypeName(rs.getString("tour_type_name"));
                        tour.setTourTypeDescription(rs.getString("tour_type_description"));
                        tour.setTourCategoryName(rs.getString("tour_category_name"));
                        tour.setTourCategoryDescription(rs.getString("tour_category_description"));
                        tour.setSeasonName(rs.getString("season_name"));
                        tour.setSeasonDescription(rs.getString("season_description"));
                        tour.setStatusName(rs.getString("status_name"));

                        // Initialize collections
                        tour.setSchedules(new ArrayList<>());
                        tour.setImages(new ArrayList<>());

                        tourMap.put(tId, tour);
                    }

                    // Handle schedules
                    int scheduleId = rs.getInt("schedule_id");
                    if (!rs.wasNull() && rs.getString("schedule_name") != null) {
                        if (tour.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            TourScheduleResponseDto schedule = new TourScheduleResponseDto();
                            schedule.setScheduleId(scheduleId);
                            schedule.setScheduleName(rs.getString("schedule_name"));
                            schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                            schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                            schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                            schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                            schedule.setSpecialNote(rs.getString("special_note"));
                            schedule.setScheduleDescription(rs.getString("schedule_description"));
                            tour.getSchedules().add(schedule);
                        }
                    }

                    // Handle images
                    int imageId = rs.getInt("image_id");
                    if (!rs.wasNull() && rs.getString("image_url") != null) {
                        if (tour.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            TourImageResponseDto image = new TourImageResponseDto();
                            image.setImageId(imageId);
                            image.setImageName(rs.getString("image_name"));
                            image.setImageDescription(rs.getString("image_description"));
                            image.setImageUrl(rs.getString("image_url"));
                            tour.getImages().add(image);
                        }
                    }
                }

                // Return single tour (or null if not found)
                return tourMap.values().stream().findFirst().orElse(null);
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tour details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tour details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tour details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching tour details");
        }
    }

    @Override
    public List<TourReviewDetailsResponse> getAllTourReviewDetails() {
        String GET_TOUR_REVIEWS_DETAILS = TourQueries.GET_TOUR_REVIEWS_DETAILS;

        try {
            List<TourReviewDetailsResponse> reviews = jdbcTemplate.query(GET_TOUR_REVIEWS_DETAILS, (ResultSet rs) -> {

                Map<Integer, TourReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int reviewId = rs.getInt("review_id");

                    // 🔹 Get or create main review
                    TourReviewDetailsResponse review = reviewMap.computeIfAbsent(reviewId, id ->
                            {
                                try {
                                    return TourReviewDetailsResponse.builder()
                                            .reviewId(rs.getInt("review_id"))
                                            .tourScheduleId(rs.getInt("tour_schedule_id"))
                                            .tourId(rs.getInt("tour_id"))
                                            .tourName(rs.getString("tour_name"))
                                            .reviewName(rs.getString("review_name"))
                                            .review(rs.getString("review"))
                                            .rating(rs.getBigDecimal("rating"))
                                            .reviewDescription(rs.getString("review_description"))
                                            .reviewStatus(rs.getString("review_status"))
                                            .numberOfParticipate(rs.getInt("number_of_participate"))
                                            .reviewCreatedBy(rs.getInt("review_created_by"))
                                            .reviewCreatedUser(rs.getString("review_created_user"))
                                            .reviewCreatedAt(getLocalDateTime(rs, "review_created_at"))
                                            .reviewUpdatedBy(rs.getObject("review_updated_by") != null ? rs.getInt("review_updated_by") : null)
                                            .reviewUpdatedAt(getLocalDateTime(rs, "review_updated_at"))
                                            .images(new ArrayList<>())
                                            .reactions(new ArrayList<>())
                                            .comments(new ArrayList<>())
                                            .build();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

                    // 🔹 Add image if exists
                    if (rs.getObject("image_id") != null) {
                        boolean imageExists = review.getImages().stream()
                                .anyMatch(i -> {
                                    try {
                                        return i.getImageId().equals(rs.getInt("image_id"));
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                        if (!imageExists) {
                            review.getImages().add(
                                    TourReviewDetailsResponse.Image.builder()
                                            .imageId(rs.getInt("image_id"))
                                            .imageName(rs.getString("image_name"))
                                            .imageDescription(rs.getString("image_description"))
                                            .imageUrl(rs.getString("image_url"))
                                            .imageStatus(rs.getString("image_status"))
                                            .imageCreatedBy(rs.getInt("image_created_by"))
                                            .imageCreatedAt(getLocalDateTime(rs, "image_created_at"))
                                            .build()
                            );
                        }
                    }

                    // 🔹 Add reaction if exists
                    if (rs.getObject("review_reaction_id") != null) {
                        boolean reactionExists = review.getReactions().stream()
                                .anyMatch(r -> {
                                    try {
                                        return r.getReviewReactionId().equals(rs.getInt("review_reaction_id"));
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                        if (!reactionExists) {
                            review.getReactions().add(
                                    TourReviewDetailsResponse.Reaction.builder()
                                            .reviewReactionId(rs.getInt("review_reaction_id"))
                                            .reactionReviewId(rs.getInt("reaction_review_id"))
                                            .reactionUserId(rs.getInt("reaction_user_id"))
                                            .reactionUserName(rs.getString("reaction_user_name"))
                                            .reactionType(rs.getString("reaction_type"))
                                            .reviewReactionStatus(rs.getString("review_reaction_status"))
                                            .reactionCreatedAt(getLocalDateTime(rs, "reaction_created_at"))
                                            .build()
                            );
                        }
                    }

                    // 🔹 Add comment if exists
                    if (rs.getObject("comment_id") != null) {
                        TourReviewDetailsResponse.Comment comment = review.getComments().stream()
                                .filter(c -> {
                                    try {
                                        return c.getCommentId().equals(rs.getInt("comment_id"));
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .findFirst()
                                .orElseGet(() -> {
                                    TourReviewDetailsResponse.Comment newComment = null;
                                    try {
                                        newComment = TourReviewDetailsResponse.Comment.builder()
                                                .commentId(rs.getInt("comment_id"))
                                                .commentReviewId(rs.getInt("comment_review_id"))
                                                .commentUserId(rs.getInt("comment_user_id"))
                                                .commentUserName(rs.getString("comment_user_name"))
                                                .parentCommentId((Integer) rs.getObject("parent_comment_id"))
                                                .comment(rs.getString("comment"))
                                                .commentStatus(rs.getString("comment_status"))
                                                .commentCreatedAt(getLocalDateTime(rs, "comment_created_at"))
                                                .commentCreatedBy(rs.getInt("comment_created_by"))
                                                .commentReactions(new ArrayList<>())
                                                .build();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    review.getComments().add(newComment);
                                    return newComment;
                                });

                        // 🔹 Add comment reaction if exists
                        if (rs.getObject("comment_reaction_id") != null) {
                            boolean commentReactionExists = comment.getCommentReactions().stream()
                                    .anyMatch(cr -> {
                                        try {
                                            return cr.getCommentReactionId().equals(rs.getInt("comment_reaction_id"));
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                            if (!commentReactionExists) {
                                comment.getCommentReactions().add(
                                        TourReviewDetailsResponse.CommentReaction.builder()
                                                .commentReactionId(rs.getInt("comment_reaction_id"))
                                                .commentReactionCommentId(rs.getInt("comment_reaction_comment_id"))
                                                .commentReactionUserId(rs.getInt("comment_reaction_user_id"))
                                                .commentReactionUserName(rs.getString("comment_reaction_user_name"))
                                                .commentReactionType(rs.getString("comment_reaction_type"))
                                                .commentReactionStatus(rs.getString("comment_reaction_status"))
                                                .commentReactionCreatedBy(rs.getInt("comment_reaction_created_by"))
                                                .commentReactionCreatedAt(getLocalDateTime(rs, "comment_reaction_created_at"))
                                                .build()
                                );
                            }
                        }
                    }
                }

                return new ArrayList<>(reviewMap.values());
            });

            return reviews;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tour details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tour details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tour details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching tour details");
        }
    }

    /**
     * Utility method to safely get LocalDateTime from ResultSet.
     */
    private LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) {
        try {
            Timestamp timestamp = rs.getTimestamp(columnLabel);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public List<TourReviewDetailsResponse> getTourReviewDetailsById(String tourId) {
        String GET_TOUR_REVIEW_DETAILS_BY_ID = TourQueries.GET_TOUR_REVIEW_DETAILS_BY_ID;

        try {
            return jdbcTemplate.query(con -> {
                var ps = con.prepareStatement(GET_TOUR_REVIEW_DETAILS_BY_ID);
                ps.setString(1, tourId);
                return ps;
            }, rs -> {
                Map<Integer, TourReviewDetailsResponse> reviewMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int reviewId = rs.getInt("review_id");

                    // --- 1️⃣ Create or Get Main Review ---
                    TourReviewDetailsResponse review = reviewMap.computeIfAbsent(reviewId, id ->
                            {
                                try {
                                    return TourReviewDetailsResponse.builder()
                                            .reviewId(rs.getInt("review_id"))
                                            .tourScheduleId(rs.getInt("tour_schedule_id"))
                                            .tourId(rs.getInt("tour_id"))
                                            .tourName(rs.getString("tour_name"))
                                            .reviewName(rs.getString("review_name"))
                                            .review(rs.getString("review"))
                                            .rating(rs.getBigDecimal("rating"))
                                            .reviewDescription(rs.getString("review_description"))
                                            .reviewStatus(rs.getString("review_status"))
                                            .numberOfParticipate(rs.getInt("number_of_participate"))
                                            .reviewCreatedBy(rs.getInt("review_created_by"))
                                            .reviewCreatedUser(rs.getString("review_created_user"))
                                            .reviewCreatedAt(rs.getTimestamp("review_created_at") != null ?
                                                    rs.getTimestamp("review_created_at").toLocalDateTime() : null)
                                            .reviewUpdatedBy(rs.getInt("review_updated_by"))
                                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ?
                                                    rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                                            .images(new ArrayList<>())
                                            .reactions(new ArrayList<>())
                                            .comments(new ArrayList<>())
                                            .build();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

                    // --- 2️⃣ Add Image ---
                    int imageId = rs.getInt("image_id");
                    if (imageId > 0 && review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {
                        review.getImages().add(TourReviewDetailsResponse.Image.builder()
                                .imageId(imageId)
                                .imageName(rs.getString("image_name"))
                                .imageDescription(rs.getString("image_description"))
                                .imageUrl(rs.getString("image_url"))
                                .imageStatus(rs.getString("image_status"))
                                .imageCreatedBy(rs.getInt("image_created_by"))
                                .imageCreatedAt(rs.getTimestamp("image_created_at") != null ?
                                        rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                                .build());
                    }

                    // --- 3️⃣ Add Reaction ---
                    int reactionId = rs.getInt("review_reaction_id");
                    if (reactionId > 0 && review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reactionId))) {
                        review.getReactions().add(TourReviewDetailsResponse.Reaction.builder()
                                .reviewReactionId(reactionId)
                                .reactionReviewId(rs.getInt("reaction_review_id"))
                                .reactionUserId(rs.getInt("reaction_user_id"))
                                .reactionUserName(rs.getString("reaction_user_name"))
                                .reactionType(rs.getString("reaction_type"))
                                .reviewReactionStatus(rs.getString("review_reaction_status"))
                                .reactionCreatedAt(rs.getTimestamp("reaction_created_at") != null ?
                                        rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                                .build());
                    }

                    // --- 4️⃣ Add Comment & Comment Reactions ---
                    int commentId = rs.getInt("comment_id");
                    if (commentId > 0) {
                        // find existing comment
                        TourReviewDetailsResponse.Comment comment = review.getComments()
                                .stream()
                                .filter(c -> c.getCommentId().equals(commentId))
                                .findFirst()
                                .orElseGet(() -> {
                                    TourReviewDetailsResponse.Comment newComment = null;
                                    try {
                                        newComment = TourReviewDetailsResponse.Comment.builder()
                                                .commentId(commentId)
                                                .commentReviewId(rs.getInt("comment_review_id"))
                                                .commentUserId(rs.getInt("comment_user_id"))
                                                .commentUserName(rs.getString("comment_user_name"))
                                                .parentCommentId(rs.getInt("parent_comment_id"))
                                                .comment(rs.getString("comment"))
                                                .commentStatus(rs.getString("comment_status"))
                                                .commentCreatedAt(rs.getTimestamp("comment_created_at") != null ?
                                                        rs.getTimestamp("comment_created_at").toLocalDateTime() : null)
                                                .commentCreatedBy(rs.getInt("comment_created_by"))
                                                .commentReactions(new ArrayList<>())
                                                .build();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    review.getComments().add(newComment);
                                    return newComment;
                                });

                        // Add comment reaction
                        int commentReactionId = rs.getInt("comment_reaction_id");
                        if (commentReactionId > 0 && comment.getCommentReactions().stream().noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {
                            comment.getCommentReactions().add(TourReviewDetailsResponse.CommentReaction.builder()
                                    .commentReactionId(commentReactionId)
                                    .commentReactionCommentId(rs.getInt("comment_reaction_comment_id"))
                                    .commentReactionUserId(rs.getInt("comment_reaction_user_id"))
                                    .commentReactionUserName(rs.getString("comment_reaction_user_name"))
                                    .commentReactionType(rs.getString("comment_reaction_type"))
                                    .commentReactionStatus(rs.getString("comment_reaction_status"))
                                    .commentReactionCreatedBy(rs.getInt("comment_reaction_created_by"))
                                    .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at") != null ?
                                            rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                    .build());
                        }
                    }
                }

                return new ArrayList<>(reviewMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tour reviews for ID {}", tourId, ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tour reviews");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tour reviews for ID {}", tourId, ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching tour reviews");
        }
    }

    @Override
    public List<TourDestinationsForMapResponse> getTourDestinationsForMap(String tourId) {
        String GET_TOUR_DESTINATIONS_FOR_MAP = TourQueries.GET_TOUR_DESTINATIONS_FOR_MAP;

        try {
            return jdbcTemplate.query(connection -> {
                var ps = connection.prepareStatement(GET_TOUR_DESTINATIONS_FOR_MAP);
                ps.setString(1, tourId);
                return ps;
            }, (ResultSet rs) -> {

                List<TourDestinationsForMapResponse> destinations = new ArrayList<>();
                Map<Integer, TourDestinationsForMapResponse> destinationMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("id");

                    // Get or create the destination entry
                    TourDestinationsForMapResponse destination = destinationMap.computeIfAbsent(destinationId, id ->
                            TourDestinationsForMapResponse.builder()
                                    .id(id)
                                    .name(getSafeString(rs, "name"))
                                    .description(getSafeString(rs, "description"))
                                    .lat(getSafeDouble(rs, "lat"))
                                    .lng(getSafeDouble(rs, "lng"))
                                    .images(new ArrayList<>())
                                    .build()
                    );

                    // Add image if available
                    int imageId = rs.getInt("image_id");
                    if (imageId > 0) {
                        destination.getImages().add(
                                TourDestinationsForMapResponse.Image.builder()
                                        .id(imageId)
                                        .url(getSafeString(rs, "image_url"))
                                        .name(getSafeString(rs, "image_name"))
                                        .description(getSafeString(rs, "image_description"))
                                        .build()
                        );
                    }
                }

                destinations.addAll(destinationMap.values());
                return destinations;
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tour destinations");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tour destinations");
        }
    }

    @Override
    public List<TourHistoryResponse> getAllTourHistoryDetails() {
        String GET_ALL_TOUR_HISTORY_DETAILS = TourQueries.GET_ALL_TOUR_HISTORY_DETAILS;

        try {
            return jdbcTemplate.query(GET_ALL_TOUR_HISTORY_DETAILS, rs -> {
                Map<Long, TourHistoryResponse> historyMap = new LinkedHashMap<>();

                while (rs.next()) {
                    Long historyId = rs.getLong("history_id");
                    Long scheduleId = rs.getLong("schedule_id");
                    Long tourId = rs.getLong("tour_id");
                    Long imageId = rs.getLong("image_id");

                    // Get or create TourHistoryResponse
                    TourHistoryResponse history = historyMap.get(historyId);
                    if (history == null) {
                        history = TourHistoryResponse.builder()
                                .historyId(historyId)
                                .historyName(rs.getString("history_name"))
                                .historyDescription(rs.getString("history_description"))
                                .numberOfParticipate(rs.getInt("number_of_participate"))
                                .rating(rs.getBigDecimal("rating"))
                                .historyDuration(rs.getInt("history_duration"))
                                .startDate(rs.getDate("start_date"))
                                .endDate(rs.getDate("end_date"))
                                .vehicleNumber(rs.getString("vehicle_number"))
                                .driverId(rs.getInt("driver_id"))
                                .guideId(rs.getInt("guide_id"))
                                .historyColor(rs.getString("history_color"))
                                .hoverColor(rs.getString("hover_color"))
                                .historyStatus(rs.getInt("history_status"))
                                .build();

                        // Set TourSchedule
                        TourHistoryResponse.TourSchedule schedule = new TourHistoryResponse.TourSchedule();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getDate("assume_start_date"));
                        schedule.setAssumeEndDate(rs.getDate("assume_end_date"));
                        schedule.setDurationStart(rs.getInt("duration_start"));
                        schedule.setDurationEnd(rs.getInt("duration_end"));
                        schedule.setSpecialNote(rs.getString("special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));
                        schedule.setScheduleStatus(rs.getInt("schedule_status"));

                        // Set Tour
                        TourHistoryResponse.Tour tour = new TourHistoryResponse.Tour();
                        tour.setTourId(tourId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setTourDuration(rs.getInt("tour_duration"));
                        tour.setLatitude(rs.getBigDecimal("latitude"));
                        tour.setLongitude(rs.getBigDecimal("longitude"));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));
                        tour.setTourStatus(rs.getInt("tour_status"));
                        tour.setTourType(rs.getInt("tour_type"));
                        tour.setTourCategory(rs.getInt("tour_category"));
                        tour.setSeason(rs.getInt("season"));

                        schedule.setTour(tour);
                        schedule.setImages(new ArrayList<>());

                        history.setTourSchedule(schedule);
                        historyMap.put(historyId, history);
                    }

                    // Add image if exists
                    if (imageId != 0) {
                        TourHistoryResponse.TourHistoryImage image = new TourHistoryResponse.TourHistoryImage();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        image.setImageColor(rs.getString("image_color"));
                        image.setImageStatus(rs.getInt("image_status"));

                        history.getTourSchedule().getImages().add(image);
                    }
                }

                return new ArrayList<>(historyMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }


    @Override
    public List<TourHistoryResponse> getTourHistoryDetailsById(String tourId) {
        String GET_TOUR_HISTORY_DETAILS_BY_ID = TourQueries.GET_TOUR_HISTORY_DETAILS_BY_ID;

        try {
            return jdbcTemplate.query(GET_TOUR_HISTORY_DETAILS_BY_ID, ps -> ps.setString(1, tourId), rs -> {
                Map<Long, TourHistoryResponse> historyMap = new LinkedHashMap<>();

                while (rs.next()) {
                    Long historyId = rs.getLong("history_id");
                    Long scheduleId = rs.getLong("schedule_id");
                    Long tId = rs.getLong("tour_id");
                    Long imageId = rs.getLong("image_id");

                    // Get or create TourHistoryResponse
                    TourHistoryResponse history = historyMap.get(historyId);
                    if (history == null) {
                        history = TourHistoryResponse.builder()
                                .historyId(historyId)
                                .historyName(rs.getString("history_name"))
                                .historyDescription(rs.getString("history_description"))
                                .numberOfParticipate(rs.getInt("number_of_participate"))
                                .rating(rs.getBigDecimal("rating"))
                                .historyDuration(rs.getInt("history_duration"))
                                .startDate(rs.getDate("start_date"))
                                .endDate(rs.getDate("end_date"))
                                .vehicleNumber(rs.getString("vehicle_number"))
                                .driverId(rs.getInt("driver_id"))
                                .guideId(rs.getInt("guide_id"))
                                .historyColor(rs.getString("history_color"))
                                .hoverColor(rs.getString("hover_color"))
                                .historyStatus(rs.getInt("history_status"))
                                .build();

                        // Set TourSchedule
                        TourHistoryResponse.TourSchedule schedule = new TourHistoryResponse.TourSchedule();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getDate("assume_start_date"));
                        schedule.setAssumeEndDate(rs.getDate("assume_end_date"));
                        schedule.setDurationStart(rs.getInt("duration_start"));
                        schedule.setDurationEnd(rs.getInt("duration_end"));
                        schedule.setSpecialNote(rs.getString("special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));
                        schedule.setScheduleStatus(rs.getInt("schedule_status"));

                        // Set Tour
                        TourHistoryResponse.Tour tour = new TourHistoryResponse.Tour();
                        tour.setTourId(tId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setTourDuration(rs.getInt("tour_duration"));
                        tour.setLatitude(rs.getBigDecimal("latitude"));
                        tour.setLongitude(rs.getBigDecimal("longitude"));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));
                        tour.setTourStatus(rs.getInt("tour_status"));
                        tour.setTourType(rs.getInt("tour_type"));
                        tour.setTourCategory(rs.getInt("tour_category"));
                        tour.setSeason(rs.getInt("season"));

                        schedule.setTour(tour);
                        schedule.setImages(new ArrayList<>());

                        history.setTourSchedule(schedule);
                        historyMap.put(historyId, history);
                    }

                    // Add image if exists
                    if (imageId != 0) {
                        TourHistoryResponse.TourHistoryImage image = new TourHistoryResponse.TourHistoryImage();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        image.setImageColor(rs.getString("image_color"));
                        image.setImageStatus(rs.getInt("image_status"));

                        history.getTourSchedule().getImages().add(image);
                    }
                }

                return new ArrayList<>(historyMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tour history for tourId: " + tourId);
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tour history for tourId: " + tourId);
        }
    }

    @Override
    public List<TourHistoryImageResponse> getTourHistoryImagesById(String tourId) {
        String GET_ALL_TOUR_HISTORY_IMAGES_BY_ID = TourQueries.GET_ALL_TOUR_HISTORY_IMAGES_BY_ID;

        try {
            return jdbcTemplate.query(GET_ALL_TOUR_HISTORY_IMAGES_BY_ID, ps -> ps.setString(1, tourId), rs -> {
                List<TourHistoryImageResponse> images = new ArrayList<>();
                while (rs.next()) {
                    TourHistoryImageResponse image = TourHistoryImageResponse.builder()
                            .imageId(rs.getInt("image_id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .imageUrl(rs.getString("image_url"))
                            .color(rs.getString("color"))
                            .status(rs.getString("status"))
                            .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                            .createdBy(rs.getObject("created_by") != null ? rs.getInt("created_by") : null)
                            .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                            .updatedBy(rs.getObject("updated_by") != null ? rs.getInt("updated_by") : null)
                            .terminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                            .terminatedBy(rs.getObject("terminated_by") != null ? rs.getInt("terminated_by") : null)
                            .build();

                    images.add(image);
                }
                return images;
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler(
                    "Database error while fetching tour history for tourId: ");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching tour history for tourId: ");
        }
    }


    @Override
    public List<TourHistoryImageResponse> getAllTourHistoryImages() {
        String GET_ALL_TOUR_HISTORY_IMAGES = TourQueries.GET_ALL_TOUR_HISTORY_IMAGES;

        try {
            return jdbcTemplate.query(GET_ALL_TOUR_HISTORY_IMAGES, (rs) -> {
                List<TourHistoryImageResponse> images = new ArrayList<>();
                while (rs.next()) {
                    TourHistoryImageResponse image = TourHistoryImageResponse.builder()
                            .imageId(rs.getInt("image_id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .imageUrl(rs.getString("image_url"))
                            .color(rs.getString("color"))
                            .status(rs.getString("status"))
                            .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                            .createdBy(rs.getObject("created_by") != null ? rs.getInt("created_by") : null)
                            .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                            .updatedBy(rs.getObject("updated_by") != null ? rs.getInt("updated_by") : null)
                            .terminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                            .terminatedBy(rs.getObject("terminated_by") != null ? rs.getInt("terminated_by") : null)
                            .build();

                    images.add(image);
                }
                return images;
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

    @Override
    public ToursDetailsWithParamResponse getToursToShowWithParam(TourDataRequest tourDataRequest) {
        try {
            int offset = (tourDataRequest.getPageNumber() - 1) * tourDataRequest.getPageSize();
            List<Integer> tourIds = jdbcTemplate.queryForList(GET_PAGINATED_TOUR_IDS,
                    new Object[]{
                            tourDataRequest.getName(), tourDataRequest.getName(),
                            tourDataRequest.getDuration(), tourDataRequest.getDuration(),
                            tourDataRequest.getLocation(), tourDataRequest.getLocation(), tourDataRequest.getLocation(),
                            tourDataRequest.getTourCategory(), tourDataRequest.getTourCategory(),
                            tourDataRequest.getSeason(), tourDataRequest.getSeason(),
                            tourDataRequest.getTourType(), tourDataRequest.getTourType(),
                            tourDataRequest.getPageSize(), offset
                    }, Integer.class);

            List<Integer> totalTourIds = jdbcTemplate.queryForList(GET_PAGINATED_TOUR_IDS,
                    new Object[]{
                            tourDataRequest.getName(), tourDataRequest.getName(),
                            tourDataRequest.getDuration(), tourDataRequest.getDuration(),
                            tourDataRequest.getLocation(), tourDataRequest.getLocation(), tourDataRequest.getLocation(),
                            tourDataRequest.getTourCategory(), tourDataRequest.getTourCategory(),
                            tourDataRequest.getSeason(), tourDataRequest.getSeason(),
                            tourDataRequest.getTourType(), tourDataRequest.getTourType(),
                            1000000000, 1
                    }, Integer.class);

            if (tourIds.isEmpty()) {
                return null;
            }
            String inSql = String.join(",", Collections.nCopies(tourIds.size(), "?"));
            String fullQuery = String.format(GET_TOURS_BY_IDS, inSql);

            ArrayList<TourResponseDto> query = jdbcTemplate.query(fullQuery, tourIds.toArray(), (ResultSet rs) -> {
                Map<Integer, TourResponseDto> tourMap = new HashMap<>();

                while (rs.next()) {
                    int tourId = rs.getInt("tour_id");
                    TourResponseDto tour = tourMap.get(tourId);
                    if (tour == null) {
                        tour = new TourResponseDto();
                        tour.setTourId(tourId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setDuration(rs.getObject("duration", Integer.class));
                        tour.setLatitude(rs.getObject("latitude", Double.class));
                        tour.setLongitude(rs.getObject("longitude", Double.class));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));
                        tour.setTourTypeName(rs.getString("tour_type_name"));
                        tour.setTourTypeDescription(rs.getString("tour_type_description"));
                        tour.setTourCategoryName(rs.getString("tour_category_name"));
                        tour.setTourCategoryDescription(rs.getString("tour_category_description"));
                        tour.setSeasonName(rs.getString("season_name"));
                        tour.setSeasonDescription(rs.getString("season_description"));
                        tour.setStatusName(rs.getString("status_name"));
                        tour.setSchedules(new ArrayList<>());
                        tour.setImages(new ArrayList<>());
                        tourMap.put(tourId, tour);
                    }

                    // Add schedules
                    int scheduleId = rs.getInt("schedule_id");
                    if (scheduleId != 0 && rs.getString("schedule_name") != null) {
                        TourScheduleResponseDto schedule = new TourScheduleResponseDto();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                        schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                        schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                        schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                        schedule.setSpecialNote(rs.getString("special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));
                        if (tour.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            tour.getSchedules().add(schedule);
                        }
                    }

                    // Add images
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        TourImageResponseDto image = new TourImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        if (tour.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            tour.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(tourMap.values());
            });
            return new ToursDetailsWithParamResponse(totalTourIds.size(), query);

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

    @Override
    public List<TourDayDestinationActivityIdsDto> getTourDayDestinationActivityIds(Long tourId) {

        try {
            return jdbcTemplate.query(
                    TourQueries.GET_ALL_TOUR_DAY_DESTINATION_ACTIVITY_IDS,
                    new Object[]{tourId},
                    (rs, rowNum) -> {

                        List<Integer> activityIds = Arrays.stream(
                                        rs.getString("activity_ids").split(","))
                                .map(Integer::valueOf)
                                .toList();

                        return TourDayDestinationActivityIdsDto.builder()
                                .day(rs.getInt("day"))
                                .destinationId(rs.getInt("destination_id"))
                                .activityIds(activityIds)
                                .build();
                    }
            );

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler(
                    "Database error while fetching tour day-to-day details for tourId: " + tourId);
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error while fetching tour day-to-day details for tourId: " + tourId);
        }
    }

    @Override
    public List<TourDetailsWithDayToDayResponse.DestinationDetailsPerDay> getDestinationsDetailsByIds(List<Long> destinationIdList) {
        String GET_DESTINATIONS_DETAILS_WITH_FOR_DAY_IDS = TourQueries.GET_DESTINATIONS_DETAILS_WITH_FOR_DAY_IDS;
        try {
            LOGGER.info("Executing query to fetch all destinations...");
            if (destinationIdList == null || destinationIdList.isEmpty()) {
                return List.of();
            }

            String placeholders = destinationIdList.stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(","));

            String sql = TourQueries.GET_DESTINATIONS_DETAILS_WITH_FOR_DAY_IDS
                    .replace(":destinationIds", placeholders);

            return jdbcTemplate.query(
                    sql,
                    destinationIdList.toArray(),
                    rs -> {
                        Map<Long, TourDetailsWithDayToDayResponse.DestinationDetailsPerDay> destinationMap = new HashMap<>();

                        while (rs.next()) {
                            Long destinationId = rs.getLong("destination_id");

                            // Check if destination already exists
                            TourDetailsWithDayToDayResponse.DestinationDetailsPerDay destination = destinationMap.get(destinationId);
                            if (destination == null) {
                                destination = new TourDetailsWithDayToDayResponse.DestinationDetailsPerDay();
                                destination.setDestinationId(destinationId);
                                destination.setDestinationName(rs.getString("destination_name"));
                                destination.setDestinationDescription(rs.getString("destination_description"));
                                destination.setLocation(rs.getString("location"));
                                destination.setLatitude(rs.getObject("latitude", Double.class));
                                destination.setLongitude(rs.getObject("longitude", Double.class));
                                destination.setCreatedBy(rs.getString("created_by"));
                                destination.setCreaterImageUrl(rs.getString("creater_image"));
                                destination.setUpdatedBy(rs.getString("updated_by"));
                                destination.setUpdaterImageUrl(rs.getString("updater_image"));
                                destination.setCreatedAt((rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null));
                                destination.setUpdatedAt((rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null));

                                destination.setCategory(rs.getString("category_name"));
                                destination.setCategoryDescription(rs.getString("category_description"));

                                destination.setImages(new ArrayList<>());

                                destinationMap.put(destinationId, destination);
                            }

                            // Add image if exists
                            int imageId = rs.getInt("image_id");
                            if (imageId != 0 && rs.getString("image_url") != null) {
                                TourDetailsWithDayToDayResponse.DestinationImagePerDay image = new TourDetailsWithDayToDayResponse.DestinationImagePerDay();
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
    public List<TourDetailsWithDayToDayResponse.ActivityPerDayResponse> getActivityDetailsByIds(
            List<Long> activityIdList
    ) {
        if (activityIdList == null || activityIdList.isEmpty()) {
            return List.of();
        }

        // ---- Build dynamic placeholders for IN clause ----
        String placeholders = activityIdList.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));

        // ---- Append IN clause and LIMIT at the end ----
        String sql = TourQueries.GET_ACTIVITIES_DETAILS_BASE
                + " AND a.id IN (" + placeholders + ")"
                + " LIMIT 1000";  // LIMIT goes last

        ObjectMapper objectMapper = new ObjectMapper();

        return jdbcTemplate.query(sql, activityIdList.toArray(), (rs, rowNum) -> {
            List<TourDetailsWithDayToDayResponse.ActivityRequirementPerDay> requirements;
            try {
                requirements = objectMapper.readValue(
                        rs.getString("requirements"),
                        new TypeReference<>() {}
                );
            } catch (JsonProcessingException e) {
                requirements = List.of();
            }

            List<TourDetailsWithDayToDayResponse.ActivityImagePerDay> images;
            try {
                images = objectMapper.readValue(
                        rs.getString("images"),
                        new TypeReference<>() {}
                );
            } catch (JsonProcessingException e) {
                images = List.of();
            }

            return TourDetailsWithDayToDayResponse.ActivityPerDayResponse.builder()
                    .id(rs.getLong("id"))
                    .destinationId(rs.getLong("destination_id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .activitiesCategory(rs.getString("activities_category"))
                    .durationHours(rs.getBigDecimal("duration_hours"))
                    .availableFrom(rs.getTime("available_from"))
                    .availableTo(rs.getTime("available_to"))
                    .priceLocal(rs.getBigDecimal("price_local"))
                    .priceForeigners(rs.getBigDecimal("price_foreigners"))
                    .minParticipate(rs.getInt("min_participate"))
                    .maxParticipate(rs.getInt("max_participate"))
                    .season(rs.getString("season"))
                    .status(rs.getString("status_name"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .updatedAt(rs.getTimestamp("updated_at"))
                    .categoryName(rs.getString("category_name"))
                    .categoryDescription(rs.getString("category_description"))
                    .requirements(requirements)
                    .images(images)
                    .build();
        });
    }





    // ✅ Helper methods (avoid null pointer issues)
    private String getSafeString(ResultSet rs, String column) {
        try {
            return rs.getString(column);
        } catch (Exception e) {
            return null;
        }
    }

    private Double getSafeDouble(ResultSet rs, String column) {
        try {
            double value = rs.getDouble(column);
            return rs.wasNull() ? null : value;
        } catch (Exception e) {
            return null;
        }
    }


}
