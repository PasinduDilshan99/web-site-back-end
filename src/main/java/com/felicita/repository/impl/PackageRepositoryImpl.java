package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.response.*;
import com.felicita.queries.PackageQueries;
import com.felicita.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<PackageResponseDto> getAllPackages() {
        try {
            return jdbcTemplate.query(PackageQueries.GET_ALL_PACKAGES, (ResultSet rs) -> {
                Map<Long, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    Long packageId = rs.getLong("package_id");

                    // Get or create package
                    PackageResponseDto pkg = packageMap.get(packageId);
                    if (pkg == null) {
                        pkg = new PackageResponseDto();
                        pkg.setPackageId(packageId);
                        pkg.setPackageName(rs.getString("package_name"));
                        pkg.setPackageDescription(rs.getString("package_description"));
                        pkg.setTotalPrice(rs.getBigDecimal("total_price"));
                        pkg.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                        pkg.setStartDate(rs.getObject("start_date", LocalDate.class));
                        pkg.setEndDate(rs.getObject("end_date", LocalDate.class));
                        pkg.setColor(rs.getString("color"));
                        pkg.setHoverColor(rs.getString("hover_color"));
                        pkg.setMinPersonCount(rs.getObject("min_person_count", Integer.class));
                        pkg.setMaxPersonCount(rs.getObject("max_person_count", Integer.class));
                        pkg.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                        pkg.setPackageStatus(rs.getString("package_status"));

                        // New fields
                        pkg.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                        pkg.setCreatedBy(rs.getObject("created_by", Integer.class));
                        pkg.setPackageTypeName(rs.getString("package_type_name"));
                        pkg.setPackageTypeDescription(rs.getString("package_type_description"));
                        pkg.setPackageTypeStatus(rs.getString("package_type_status"));

                        // Tour info
                        pkg.setTourId(rs.getInt("tour_id"));
                        pkg.setTourName(rs.getString("tour_name"));
                        pkg.setTourDescription(rs.getString("tour_description"));
                        pkg.setDuration(rs.getObject("duration", Integer.class));
                        pkg.setLatitude(rs.getObject("latitude", Double.class));
                        pkg.setLongitude(rs.getObject("longitude", Double.class));
                        pkg.setStartLocation(rs.getString("start_location"));
                        pkg.setEndLocation(rs.getString("end_location"));
                        pkg.setTourStatus(rs.getString("tour_status"));

                        pkg.setSchedules(new ArrayList<>());
                        pkg.setFeatures(new ArrayList<>());
                        pkg.setImages(new ArrayList<>());
                        packageMap.put(packageId, pkg);
                    }

                    // Schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (scheduleId != 0 && rs.getString("schedule_name") != null) {
                        PackageScheduleResponseDto schedule = new PackageScheduleResponseDto();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                        schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                        schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                        schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                        schedule.setSpecialNote(rs.getString("schedule_special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));

                        if (pkg.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            pkg.getSchedules().add(schedule);
                        }
                    }

                    // Feature
                    int featureId = rs.getInt("feature_id");
                    if (featureId != 0 && rs.getString("feature_name") != null) {
                        PackageFeatureResponseDto feature = new PackageFeatureResponseDto();
                        feature.setFeatureId(featureId);
                        feature.setFeatureName(rs.getString("feature_name"));
                        feature.setFeatureValue(rs.getString("feature_value"));
                        feature.setFeatureDescription(rs.getString("feature_description"));
                        feature.setColor(rs.getString("feature_color"));
                        feature.setSpecialNote(rs.getString("feature_special_note"));

                        if (pkg.getFeatures().stream().noneMatch(f -> f.getFeatureId() == featureId)) {
                            pkg.getFeatures().add(feature);
                        }
                    }

                    // Image
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        PackageImageResponseDto image = new PackageImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        image.setColor(rs.getString("image_color"));

                        if (pkg.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            pkg.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(packageMap.values());
            });
        } catch (DataAccessException ex) {
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }

    @Override
    public PackageResponseDto getPackageDetailsById(String packageId) {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_DETAILS_BY_PACKAGE_ID, new Object[]{packageId}, (ResultSet rs) -> {
                Map<Long, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    Long pkgId = rs.getLong("package_id");

                    // Get or create package object
                    PackageResponseDto pkg = packageMap.get(pkgId);
                    if (pkg == null) {
                        pkg = new PackageResponseDto();
                        pkg.setPackageId(pkgId);
                        pkg.setPackageName(rs.getString("package_name"));
                        pkg.setPackageDescription(rs.getString("package_description"));
                        pkg.setTotalPrice(rs.getBigDecimal("total_price"));
                        pkg.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                        pkg.setStartDate(rs.getObject("start_date", LocalDate.class));
                        pkg.setEndDate(rs.getObject("end_date", LocalDate.class));
                        pkg.setColor(rs.getString("color"));
                        pkg.setHoverColor(rs.getString("hover_color"));
                        pkg.setMinPersonCount(rs.getObject("min_person_count", Integer.class));
                        pkg.setMaxPersonCount(rs.getObject("max_person_count", Integer.class));
                        pkg.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                        pkg.setPackageStatus(rs.getString("package_status"));
                        pkg.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                        pkg.setCreatedBy(rs.getObject("created_by", Integer.class));

                        // Package type info
                        pkg.setPackageTypeName(rs.getString("package_type_name"));
                        pkg.setPackageTypeDescription(rs.getString("package_type_description"));
                        pkg.setPackageTypeStatus(rs.getString("package_type_status"));

                        // Tour info
                        pkg.setTourId(rs.getObject("tour_id", Integer.class));
                        pkg.setTourName(rs.getString("tour_name"));
                        pkg.setTourDescription(rs.getString("tour_description"));
                        pkg.setDuration(rs.getObject("duration", Integer.class));
                        pkg.setLatitude(rs.getObject("latitude", Double.class));
                        pkg.setLongitude(rs.getObject("longitude", Double.class));
                        pkg.setStartLocation(rs.getString("start_location"));
                        pkg.setEndLocation(rs.getString("end_location"));
                        pkg.setTourStatus(rs.getString("tour_status"));

                        // Initialize nested lists
                        pkg.setSchedules(new ArrayList<>());
                        pkg.setFeatures(new ArrayList<>());
                        pkg.setImages(new ArrayList<>());

                        packageMap.put(pkgId, pkg);
                    }

                    // Schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (!rs.wasNull() && rs.getString("schedule_name") != null) {
                        if (pkg.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            PackageScheduleResponseDto schedule = new PackageScheduleResponseDto();
                            schedule.setScheduleId(scheduleId);
                            schedule.setScheduleName(rs.getString("schedule_name"));
                            schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                            schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                            schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                            schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                            schedule.setSpecialNote(rs.getString("schedule_special_note"));
                            schedule.setScheduleDescription(rs.getString("schedule_description"));
                            pkg.getSchedules().add(schedule);
                        }
                    }

                    // Feature
                    int featureId = rs.getInt("feature_id");
                    if (!rs.wasNull() && rs.getString("feature_name") != null) {
                        if (pkg.getFeatures().stream().noneMatch(f -> f.getFeatureId() == featureId)) {
                            PackageFeatureResponseDto feature = new PackageFeatureResponseDto();
                            feature.setFeatureId(featureId);
                            feature.setFeatureName(rs.getString("feature_name"));
                            feature.setFeatureValue(rs.getString("feature_value"));
                            feature.setFeatureDescription(rs.getString("feature_description"));
                            feature.setColor(rs.getString("feature_color"));
                            feature.setSpecialNote(rs.getString("feature_special_note"));
                            pkg.getFeatures().add(feature);
                        }
                    }

                    // Image
                    int imageId = rs.getInt("image_id");
                    if (!rs.wasNull() && rs.getString("image_url") != null) {
                        if (pkg.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            PackageImageResponseDto image = new PackageImageResponseDto();
                            image.setImageId(imageId);
                            image.setImageName(rs.getString("image_name"));
                            image.setImageDescription(rs.getString("image_description"));
                            image.setImageUrl(rs.getString("image_url"));
                            image.setColor(rs.getString("image_color"));
                            pkg.getImages().add(image);
                        }
                    }
                }

                // Return the first (and only) package
                return packageMap.values().stream().findFirst().orElse(null);
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package details");
        }
    }

    @Override
    public List<PackageReviewResponse> getAllPackageReviewDetails() {
        String SQL = PackageQueries.GET_PACKAGE_ALL_REVIEWS_DETAILS;

        return jdbcTemplate.query(SQL, rs -> {
            Map<Long, PackageReviewResponse> reviewMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long reviewId = rs.getLong("review_id");

                // ------------------- PACKAGE REVIEW -------------------
                PackageReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = PackageReviewResponse.builder()
                            .reviewId(reviewId)
                            .packageId(rs.getLong("package_id"))
                            .packageScheduleId(rs.getLong("package_schedule_id"))
                            .name(rs.getString("review_name"))
                            .review(rs.getString("review"))
                            .rating(rs.getDouble("rating"))
                            .description(rs.getString("description"))
                            .status(rs.getString("review_status"))
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .createdBy(rs.getLong("review_created_by"))
                            .createdAt(rs.getTimestamp("review_created_at") != null
                                    ? rs.getTimestamp("review_created_at").toLocalDateTime() : null)
                            .updatedBy(rs.getLong("review_updated_by"))
                            .updatedAt(rs.getTimestamp("review_updated_at") != null
                                    ? rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();

                    reviewMap.put(reviewId, review);
                }

                // ------------------- IMAGES -------------------
                Long imageId = rs.getLong("image_id");
                if (imageId != 0 && review.getImages().stream().noneMatch(i -> i.getId().equals(imageId))) {
                    review.getImages().add(
                            PackageReviewResponse.Image.builder()
                                    .id(imageId)
                                    .name(rs.getString("image_name"))
                                    .description(rs.getString("image_description"))
                                    .imageUrl(rs.getString("image_url"))
                                    .status(rs.getString("image_status"))
                                    .createdBy(rs.getLong("image_created_by"))
                                    .createdAt(rs.getTimestamp("image_created_at") != null
                                            ? rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // ------------------- REVIEW REACTIONS -------------------
                Long reactionId = rs.getLong("review_reaction_id");
                if (reactionId != 0 && review.getReactions().stream().noneMatch(r -> r.getId().equals(reactionId))) {
                    review.getReactions().add(
                            PackageReviewResponse.Reaction.builder()
                                    .id(reactionId)
                                    .packageReviewId(rs.getLong("reaction_review_id"))
                                    .userId(rs.getLong("reaction_user_id"))
                                    .userName(rs.getString("reaction_user_name"))
                                    .reactionType(rs.getString("reaction_type"))
                                    .status(rs.getString("review_reaction_status"))
                                    .createdAt(rs.getTimestamp("reaction_created_at") != null
                                            ? rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // ------------------- COMMENTS -------------------
                Long commentId = rs.getLong("comment_id");
                PackageReviewResponse.Comment comment = null;
                if (commentId != 0) {
                    Optional<PackageReviewResponse.Comment> existingComment = review.getComments()
                            .stream()
                            .filter(c -> c.getId().equals(commentId))
                            .findFirst();

                    if (existingComment.isPresent()) {
                        comment = existingComment.get();
                    } else {
                        comment = PackageReviewResponse.Comment.builder()
                                .id(commentId)
                                .packageReviewId(rs.getLong("comment_review_id"))
                                .userId(rs.getLong("comment_user_id"))
                                .userName(rs.getString("comment_user_name"))
                                .parentCommentId(rs.getLong("parent_comment_id"))
                                .comment(rs.getString("comment"))
                                .status(rs.getString("comment_status"))
                                .createdAt(rs.getTimestamp("comment_created_at") != null
                                        ? rs.getTimestamp("comment_created_at").toLocalDateTime() : null)
                                .createdBy(rs.getLong("comment_created_by"))
                                .reactions(new ArrayList<>())
                                .build();

                        review.getComments().add(comment);
                    }

                    // ------------------- COMMENT REACTIONS -------------------
                    Long commentReactionId = rs.getLong("comment_reaction_id");
                    if (commentReactionId != 0 && comment.getReactions().stream()
                            .noneMatch(cr -> cr.getId().equals(commentReactionId))) {

                        comment.getReactions().add(
                                PackageReviewResponse.CommentReaction.builder()
                                        .id(commentReactionId)
                                        .commentId(rs.getLong("comment_reaction_comment_id"))
                                        .userId(rs.getLong("comment_reaction_user_id"))
                                        .userName(rs.getString("comment_reaction_user_name"))
                                        .reactionType(rs.getString("comment_reaction_type"))
                                        .status(rs.getString("comment_reaction_status"))
                                        .createdBy(rs.getLong("comment_reaction_created_by"))
                                        .createdAt(rs.getTimestamp("comment_reaction_created_at") != null
                                                ? rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                        .build()
                        );
                    }
                }
            }

            return new ArrayList<>(reviewMap.values());
        });
    }


    @Override
    public List<PackageReviewResponse> getPackageReviewDetailsById(String packageId) {
        String SQL = PackageQueries.GET_PACKAGE_REVIEWS_DETAILS_BY_ID;

        return jdbcTemplate.query(SQL, new Object[]{packageId}, rs -> {
            Map<Long, PackageReviewResponse> reviewMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long reviewId = rs.getLong("review_id");

                // ------------------- PACKAGE REVIEW -------------------
                PackageReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = PackageReviewResponse.builder()
                            .reviewId(reviewId)
                            .packageId(rs.getLong("package_id"))
                            .packageScheduleId(rs.getLong("package_schedule_id"))
                            .name(rs.getString("review_name"))
                            .review(rs.getString("review"))
                            .rating(rs.getDouble("rating"))
                            .description(rs.getString("description"))
                            .status(rs.getString("review_status"))
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .createdBy(rs.getLong("review_created_by"))
                            .createdAt(rs.getTimestamp("review_created_at") != null
                                    ? rs.getTimestamp("review_created_at").toLocalDateTime() : null)
                            .updatedBy(rs.getLong("review_updated_by"))
                            .updatedAt(rs.getTimestamp("review_updated_at") != null
                                    ? rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();

                    reviewMap.put(reviewId, review);
                }

                // ------------------- IMAGES -------------------
                Long imageId = rs.getLong("image_id");
                if (imageId != 0 && review.getImages().stream().noneMatch(i -> i.getId().equals(imageId))) {
                    review.getImages().add(
                            PackageReviewResponse.Image.builder()
                                    .id(imageId)
                                    .name(rs.getString("image_name"))
                                    .description(rs.getString("image_description"))
                                    .imageUrl(rs.getString("image_url"))
                                    .status(rs.getString("image_status"))
                                    .createdBy(rs.getLong("image_created_by"))
                                    .createdAt(rs.getTimestamp("image_created_at") != null
                                            ? rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // ------------------- REVIEW REACTIONS -------------------
                Long reactionId = rs.getLong("review_reaction_id");
                if (reactionId != 0 && review.getReactions().stream().noneMatch(r -> r.getId().equals(reactionId))) {
                    review.getReactions().add(
                            PackageReviewResponse.Reaction.builder()
                                    .id(reactionId)
                                    .packageReviewId(rs.getLong("reaction_review_id"))
                                    .userId(rs.getLong("reaction_user_id"))
                                    .userName(rs.getString("reaction_user_name"))
                                    .reactionType(rs.getString("reaction_type"))
                                    .status(rs.getString("review_reaction_status"))
                                    .createdAt(rs.getTimestamp("reaction_created_at") != null
                                            ? rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // ------------------- COMMENTS -------------------
                Long commentId = rs.getLong("comment_id");
                PackageReviewResponse.Comment comment = null;
                if (commentId != 0) {
                    Optional<PackageReviewResponse.Comment> existingComment = review.getComments()
                            .stream()
                            .filter(c -> c.getId().equals(commentId))
                            .findFirst();

                    if (existingComment.isPresent()) {
                        comment = existingComment.get();
                    } else {
                        comment = PackageReviewResponse.Comment.builder()
                                .id(commentId)
                                .packageReviewId(rs.getLong("comment_review_id"))
                                .userId(rs.getLong("comment_user_id"))
                                .userName(rs.getString("comment_user_name"))
                                .parentCommentId(rs.getLong("parent_comment_id"))
                                .comment(rs.getString("comment"))
                                .status(rs.getString("comment_status"))
                                .createdAt(rs.getTimestamp("comment_created_at") != null
                                        ? rs.getTimestamp("comment_created_at").toLocalDateTime() : null)
                                .createdBy(rs.getLong("comment_created_by"))
                                .reactions(new ArrayList<>())
                                .build();

                        review.getComments().add(comment);
                    }

                    // ------------------- COMMENT REACTIONS -------------------
                    Long commentReactionId = rs.getLong("comment_reaction_id");
                    if (commentReactionId != 0 && comment.getReactions().stream()
                            .noneMatch(cr -> cr.getId().equals(commentReactionId))) {

                        comment.getReactions().add(
                                PackageReviewResponse.CommentReaction.builder()
                                        .id(commentReactionId)
                                        .commentId(rs.getLong("comment_reaction_comment_id"))
                                        .userId(rs.getLong("comment_reaction_user_id"))
                                        .userName(rs.getString("comment_reaction_user_name"))
                                        .reactionType(rs.getString("comment_reaction_type"))
                                        .status(rs.getString("comment_reaction_status"))
                                        .createdBy(rs.getLong("comment_reaction_created_by"))
                                        .createdAt(rs.getTimestamp("comment_reaction_created_at") != null
                                                ? rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                        .build()
                        );
                    }
                }
            }

            return new ArrayList<>(reviewMap.values());
        });
    }

    @Override
    public List<PackageHistoryImageResponse> getPackageHistoryImagesById(String packageId) {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_IMAGES_BY_ID, ps -> {
                ps.setString(1, packageId);
            }, (ResultSet rs) -> {
                List<PackageHistoryImageResponse> result = new ArrayList<>();
                while (rs.next()) {
                    PackageHistoryImageResponse.PackageInfo packageInfo = PackageHistoryImageResponse.PackageInfo.builder()
                            .packageId(rs.getInt("package_id"))
                            .packageName(rs.getString("package_name"))
                            .tourId(rs.getInt("tour_id"))
                            .build();

                    PackageHistoryImageResponse.PackageScheduleInfo scheduleInfo = PackageHistoryImageResponse.PackageScheduleInfo.builder()
                            .packageScheduleId(rs.getInt("package_schedule_id"))
                            .packageScheduleName(rs.getString("package_schedule_name"))
                            .build();

                    PackageHistoryImageResponse.UserInfo createdByUser = PackageHistoryImageResponse.UserInfo.builder()
                            .fullName(rs.getString("created_by_user"))
                            .imageUrl(rs.getString("created_by_image"))
                            .build();

                    PackageHistoryImageResponse imageResponse = PackageHistoryImageResponse.builder()
                            .imageId(rs.getInt("image_id"))
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .color(rs.getString("color"))
                            .imageStatusName(rs.getString("image_status_name"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .packageInfo(packageInfo)
                            .packageSchedule(scheduleInfo)
                            .createdByUser(createdByUser)
                            .build();

                    result.add(imageResponse);
                }
                return result;
            });
        } catch (DataAccessException ex) {
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }


    @Override
    public List<PackageHistoryImageResponse> getAllPackageHistoryImages() {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_IMAGES, (ResultSet rs) -> {
                List<PackageHistoryImageResponse> result = new ArrayList<>();
                while (rs.next()) {
                    PackageHistoryImageResponse.PackageInfo packageInfo = PackageHistoryImageResponse.PackageInfo.builder()
                            .packageId(rs.getInt("package_id"))
                            .packageName(rs.getString("package_name"))
                            .tourId(rs.getInt("tour_id"))
                            .build();

                    PackageHistoryImageResponse.PackageScheduleInfo scheduleInfo = PackageHistoryImageResponse.PackageScheduleInfo.builder()
                            .packageScheduleId(rs.getInt("package_schedule_id"))
                            .packageScheduleName(rs.getString("package_schedule_name"))
                            .build();

                    PackageHistoryImageResponse.UserInfo createdByUser = PackageHistoryImageResponse.UserInfo.builder()
                            .fullName(rs.getString("created_by_user"))
                            .imageUrl(rs.getString("created_by_image"))
                            .build();

                    PackageHistoryImageResponse imageResponse = PackageHistoryImageResponse.builder()
                            .imageId(rs.getInt("image_id"))
                            .imageName(rs.getString("image_name"))
                            .imageDescription(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .color(rs.getString("color"))
                            .imageStatusName(rs.getString("image_status_name"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .packageInfo(packageInfo)
                            .packageSchedule(scheduleInfo)
                            .createdByUser(createdByUser)
                            .build();

                    result.add(imageResponse);
                }
                return result;
            });
        } catch (DataAccessException ex) {
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }


    @Override
    public List<PackageHistoryDetailsResponse> getPackageHistoryDetailsById(String packageId) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // Jackson mapper

            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_DETAILS_BY_ID, ps -> {
                ps.setString(1, packageId);
            }, (ResultSet rs) -> {
                List<PackageHistoryDetailsResponse> result = new ArrayList<>();

                while (rs.next()) {
                    // --- Package info ---
                    PackageHistoryDetailsResponse.PackageInfo packageInfo = PackageHistoryDetailsResponse.PackageInfo.builder()
                            .packageId(rs.getInt("package_id"))
                            .packageName(rs.getString("package_name"))
                            .tourId(rs.getInt("tour_id"))
                            .build();

                    // --- Users ---
                    PackageHistoryDetailsResponse.UserInfo createdByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("created_by_user"))
                            .imageUrl(rs.getString("created_by_image"))
                            .build();

                    PackageHistoryDetailsResponse.UserInfo updatedByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("updated_by_user"))
                            .build();

                    PackageHistoryDetailsResponse.UserInfo terminatedByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("terminated_by_user"))
                            .build();

                    // --- Images ---
                    List<PackageHistoryDetailsResponse.ImageInfo> images = new ArrayList<>();
                    String imagesJson = rs.getString("images");
                    if (imagesJson != null && !imagesJson.isEmpty()) {
                        try {
                            images = objectMapper.readValue(
                                    imagesJson,
                                    new TypeReference<List<PackageHistoryDetailsResponse.ImageInfo>>() {}
                            );
                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Failed to parse images JSON for packageHistoryId {}: {}",
                                    rs.getInt("package_history_id"), e.getMessage());
                        }
                    }

                    // --- Build response ---
                    PackageHistoryDetailsResponse response = PackageHistoryDetailsResponse.builder()
                            .packageHistoryId(rs.getInt("package_history_id"))
                            .packageScheduleId(rs.getInt("package_schedule_id"))
                            .packageScheduleName(rs.getString("package_schedule_name"))
                            .assumeStartDate(rs.getDate("assume_start_date") != null ? rs.getDate("assume_start_date").toLocalDate() : null)
                            .assumeEndDate(rs.getDate("assume_end_date") != null ? rs.getDate("assume_end_date").toLocalDate() : null)
                            .durationStart(rs.getInt("duration_start"))
                            .durationEnd(rs.getInt("duration_end"))
                            .packageInfo(packageInfo)
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .rating(rs.getBigDecimal("rating"))
                            .duration(rs.getInt("duration"))
                            .historyDescription(rs.getString("history_description"))
                            .color(rs.getString("color"))
                            .hoverColor(rs.getString("hover_color"))
                            .startDate(rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null)
                            .endDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null)
                            .historyCreatedAt(rs.getTimestamp("history_created_at") != null ? rs.getTimestamp("history_created_at").toLocalDateTime() : null)
                            .createdByUser(createdByUser)
                            .historyUpdatedAt(rs.getTimestamp("history_updated_at") != null ? rs.getTimestamp("history_updated_at").toLocalDateTime() : null)
                            .updatedByUser(updatedByUser)
                            .historyTerminatedAt(rs.getTimestamp("history_terminated_at") != null ? rs.getTimestamp("history_terminated_at").toLocalDateTime() : null)
                            .terminatedByUser(terminatedByUser)
                            .images(images)
                            .build();

                    result.add(response);
                }

                return result;
            });

        } catch (DataAccessException ex) {
            LOGGER.error("DataAccessException: {}", ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            LOGGER.error("Exception: {}", ex.toString());
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }



    @Override
    public List<PackageHistoryDetailsResponse> getAllPackageHistoryDetails() {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_DETAILS, (ResultSet rs) -> {
                List<PackageHistoryDetailsResponse> result = new ArrayList<>();
                ObjectMapper objectMapper = new ObjectMapper();

                while (rs.next()) {
                    // Package info
                    PackageHistoryDetailsResponse.PackageInfo packageInfo = PackageHistoryDetailsResponse.PackageInfo.builder()
                            .packageId(rs.getInt("package_id"))
                            .packageName(rs.getString("package_name"))
                            .tourId(rs.getInt("tour_id"))
                            .build();

                    // Users
                    PackageHistoryDetailsResponse.UserInfo createdByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("created_by_user"))
                            .imageUrl(rs.getString("created_by_image"))
                            .build();

                    PackageHistoryDetailsResponse.UserInfo updatedByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("updated_by_user"))
                            .build();

                    PackageHistoryDetailsResponse.UserInfo terminatedByUser = PackageHistoryDetailsResponse.UserInfo.builder()
                            .fullName(rs.getString("terminated_by_user"))
                            .build();

                    // Images JSON array
                    List<PackageHistoryDetailsResponse.ImageInfo> images = new ArrayList<>();
                    String imagesJson = rs.getString("images");
                    if (imagesJson != null && !imagesJson.isEmpty()) {
                        try {
                            images = objectMapper.readValue(
                                    imagesJson,
                                    new TypeReference<List<PackageHistoryDetailsResponse.ImageInfo>>() {}
                            );
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException("Failed to parse images JSON", e);
                        }
                    }

                    // Build final response
                    PackageHistoryDetailsResponse history = PackageHistoryDetailsResponse.builder()
                            .packageHistoryId(rs.getInt("package_history_id"))
                            .packageScheduleId(rs.getInt("package_schedule_id"))
                            .packageScheduleName(rs.getString("package_schedule_name"))
                            .assumeStartDate(rs.getDate("assume_start_date") != null ? rs.getDate("assume_start_date").toLocalDate() : null)
                            .assumeEndDate(rs.getDate("assume_end_date") != null ? rs.getDate("assume_end_date").toLocalDate() : null)
                            .durationStart(rs.getInt("duration_start"))
                            .durationEnd(rs.getInt("duration_end"))
                            .packageInfo(packageInfo)
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .rating(rs.getBigDecimal("rating"))
                            .duration(rs.getInt("duration"))
                            .historyDescription(rs.getString("history_description"))
                            .color(rs.getString("color"))
                            .hoverColor(rs.getString("hover_color"))
                            .startDate(rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null)
                            .endDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null)
                            .historyCreatedAt(rs.getTimestamp("history_created_at").toLocalDateTime())
                            .createdByUser(createdByUser)
                            .historyUpdatedAt(rs.getTimestamp("history_updated_at") != null ? rs.getTimestamp("history_updated_at").toLocalDateTime() : null)
                            .updatedByUser(updatedByUser)
                            .historyTerminatedAt(rs.getTimestamp("history_terminated_at") != null ? rs.getTimestamp("history_terminated_at").toLocalDateTime() : null)
                            .terminatedByUser(terminatedByUser)
                            .images(images)
                            .build();

                    result.add(history);
                }
                return result;
            });
        } catch (DataAccessException ex) {
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }

    @Override
    public PackageWithParamsResponse getPackagesWithParams(PackageDataRequest req) {

        try {
            int offset = (req.getPageNumber() - 1) * req.getPageSize();

            /* -------------------------------------------------
             * STEP 1: Get paginated package IDs
             * ------------------------------------------------- */
            List<Long> packageIds = jdbcTemplate.queryForList(
                    PackageQueries.GET_PACKAGE_IDS_WITH_FILTERS,
                    Long.class,

                    req.getName(), req.getName(),
                    req.getMinPrice(), req.getMinPrice(),
                    req.getMaxPrice(), req.getMaxPrice(),
                    req.getDuration(), req.getDuration(),
                    req.getPackageType(), req.getPackageType(),
                    req.getLocation(), req.getLocation(), req.getLocation(),
                    req.getMinGroupSize(), req.getMinGroupSize(),
                    req.getMaxGroupSize(), req.getMaxGroupSize(),
                    req.getFromDate(), req.getFromDate(),
                    req.getToDate(), req.getToDate(),

                    req.getPageSize(),
                    offset
            );

            if (packageIds.isEmpty()) {
               return null;
            }

            /* -------------------------------------------------
             * STEP 2: Get total count (for pagination)
             * ------------------------------------------------- */
            Integer totalCount = jdbcTemplate.queryForObject(
                    PackageQueries.COUNT_PACKAGES_WITH_FILTERS,
                    Integer.class,

                    req.getName(), req.getName(),
                    req.getMinPrice(), req.getMinPrice(),
                    req.getMaxPrice(), req.getMaxPrice(),
                    req.getDuration(), req.getDuration(),
                    req.getPackageType(), req.getPackageType(),
                    req.getLocation(), req.getLocation(), req.getLocation(),
                    req.getMinGroupSize(), req.getMinGroupSize(),
                    req.getMaxGroupSize(), req.getMaxGroupSize(),
                    req.getFromDate(), req.getFromDate(),
                    req.getToDate(), req.getToDate()
            );

            /* -------------------------------------------------
             * STEP 3: Fetch full data by IDs
             * ------------------------------------------------- */
            String inSql = String.join(",", Collections.nCopies(packageIds.size(), "?"));

            Map<Long, PackageResponseDto> packageMap = new LinkedHashMap<>();

            jdbcTemplate.query(
                    PackageQueries.GET_PACKAGES_BY_IDS.replace(":packageIds", inSql),
                    packageIds.toArray(),
                    rs -> {

                        Long packageId = rs.getLong("package_id");

                        PackageResponseDto pkg = packageMap.computeIfAbsent(packageId, id -> {
                            PackageResponseDto p = new PackageResponseDto();

                            p.setPackageId(id);
                            try {
                                p.setPackageName(rs.getString("package_name"));
                                p.setPackageDescription(rs.getString("package_description"));
                                p.setTotalPrice(rs.getBigDecimal("total_price"));
                                p.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                                p.setStartDate(rs.getObject("start_date", LocalDate.class));
                                p.setEndDate(rs.getObject("end_date", LocalDate.class));
                                p.setColor(rs.getString("color"));
                                p.setHoverColor(rs.getString("hover_color"));
                                p.setMinPersonCount(rs.getInt("min_person_count"));
                                p.setMaxPersonCount(rs.getInt("max_person_count"));
                                p.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                                p.setPackageStatus(rs.getString("package_status"));

                                p.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                                p.setCreatedBy(rs.getInt("created_by"));

                                p.setPackageTypeName(rs.getString("package_type_name"));
                                p.setPackageTypeDescription(rs.getString("package_type_description"));
                                p.setPackageTypeStatus(rs.getString("package_type_status"));

                                // Tour
                                p.setTourId(rs.getInt("tour_id"));
                                p.setTourName(rs.getString("tour_name"));
                                p.setTourDescription(rs.getString("tour_description"));
                                p.setDuration(rs.getInt("duration"));
                                p.setLatitude(rs.getDouble("latitude"));
                                p.setLongitude(rs.getDouble("longitude"));
                                p.setStartLocation(rs.getString("start_location"));
                                p.setEndLocation(rs.getString("end_location"));
                                p.setTourStatus(rs.getString("tour_status"));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                            p.setSchedules(new ArrayList<>());
                            p.setFeatures(new ArrayList<>());
                            p.setImages(new ArrayList<>());

                            return p;
                        });

                        /* ---------- Schedule ---------- */
                        int scheduleId = rs.getInt("schedule_id");
                        if (scheduleId > 0) {
                            if (pkg.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                                PackageScheduleResponseDto s = new PackageScheduleResponseDto();
                                s.setScheduleId(scheduleId);
                                s.setScheduleName(rs.getString("schedule_name"));
                                s.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                                s.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                                s.setDurationStart(rs.getInt("duration_start"));
                                s.setDurationEnd(rs.getInt("duration_end"));
                                s.setSpecialNote(rs.getString("schedule_special_note"));
                                s.setScheduleDescription(rs.getString("schedule_description"));
                                pkg.getSchedules().add(s);
                            }
                        }

                        /* ---------- Feature ---------- */
                        int featureId = rs.getInt("feature_id");
                        if (featureId > 0) {
                            if (pkg.getFeatures().stream().noneMatch(f -> f.getFeatureId() == featureId)) {
                                PackageFeatureResponseDto f = new PackageFeatureResponseDto();
                                f.setFeatureId(featureId);
                                f.setFeatureName(rs.getString("feature_name"));
                                f.setFeatureValue(rs.getString("feature_value"));
                                f.setFeatureDescription(rs.getString("feature_description"));
                                f.setColor(rs.getString("feature_color"));
                                f.setSpecialNote(rs.getString("feature_special_note"));
                                pkg.getFeatures().add(f);
                            }
                        }

                        /* ---------- Image ---------- */
                        int imageId = rs.getInt("image_id");
                        if (imageId > 0) {
                            if (pkg.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                                PackageImageResponseDto img = new PackageImageResponseDto();
                                img.setImageId(imageId);
                                img.setImageName(rs.getString("image_name"));
                                img.setImageDescription(rs.getString("image_description"));
                                img.setImageUrl(rs.getString("image_url"));
                                img.setColor(rs.getString("image_color"));
                                pkg.getImages().add(img);
                            }
                        }
                    }
            );

            return new PackageWithParamsResponse(totalCount, new ArrayList<>(packageMap.values()));

        } catch (DataAccessException ex) {
            LOGGER.error("DB error", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }

    @Override
    public List<PackageDetailsDto> getDayToPackageDetailsById(Long tourId) {
        String sql = PackageQueries.GET_DAY_TO_PACKAGE_DETAILS_BY_ID;
        return jdbcTemplate.query(
                sql,
                new Object[]{tourId}, // query parameter
                (rs, rowNum) -> {
                    PackageDetailsDto dto = new PackageDetailsDto();
                    dto.setPackageId(rs.getLong("package_id"));
                    dto.setPackageName(rs.getString("name")); // assuming pt.name is for type, adjust if needed
                    dto.setPackageDescription(rs.getString("description"));
                    dto.setTotalPrice(rs.getDouble("total_price"));
                    dto.setPricePerPerson(rs.getDouble("price_per_person"));
                    dto.setDiscount(rs.getDouble("discount_percentage"));
                    dto.setColor(rs.getString("color"));
                    dto.setHoverColor(rs.getString("hover_color"));
                    return dto;
                }
        );
    }

    @Override
    public List<PackageDayByDayDto> getPackagesAccoamdationsByIds(List<Long> packageIds) {
        if (packageIds == null || packageIds.isEmpty()) {
            return List.of();
        }

        String placeholders = packageIds.stream().map(id -> "?").collect(Collectors.joining(", "));
        String sql = String.format(PackageQueries.GET_PACKAGES_ACCOMMODATIONS_BY_IDS, placeholders);

        return jdbcTemplate.query(sql, packageIds.toArray(), (rs, rowNum) -> PackageDayByDayDto.builder()
                .packageId(rs.getLong("package_id"))
                .packageDayAccommodationId(rs.getLong("package_day_accommodation_id"))
                .dayNumber(rs.getInt("day_number"))
                .breakfast(rs.getBoolean("breakfast"))
                .breakfastDescription(rs.getString("breakfast_description"))
                .lunch(rs.getBoolean("lunch"))
                .lunchDescription(rs.getString("lunch_description"))
                .dinner(rs.getBoolean("dinner"))
                .dinnerDescription(rs.getString("dinner_description"))
                .morningTea(rs.getBoolean("morning_tea"))
                .morningTeaDescription(rs.getString("morning_tea_description"))
                .eveningTea(rs.getBoolean("evening_tea"))
                .eveningTeaDescription(rs.getString("evening_tea_description"))
                .snacks(rs.getBoolean("snacks"))
                .snackNote(rs.getString("snack_note"))
                .otherNotes(rs.getString("other_notes"))
                .hotelId(rs.getObject("hotel_id") != null ? rs.getLong("hotel_id") : null)
                .hotelName(rs.getString("hotel_name"))
                .hotelDescription(rs.getString("hotel_description"))
                .hotelWebsite(rs.getString("hotel_website"))
                .hotelCategory(rs.getObject("hotel_category") != null ? rs.getInt("hotel_category") : null)
                .hotelType(rs.getString("hotel_type"))
                .hotelLocation(rs.getString("hotel_location"))
                .hotelLatitude(rs.getObject("hotel_latitude") != null ? rs.getDouble("hotel_latitude") : null)
                .hotelLongitude(rs.getObject("hotel_longitude") != null ? rs.getDouble("hotel_longitude") : null)
                .transportId(rs.getObject("transport_id") != null ? rs.getLong("transport_id") : null)
                .vehicleRegistrationNumber(rs.getString("vehicle_registration_number"))
                .vehicleTypeName(rs.getString("vehicle_type_name"))
                .vehicleModel(rs.getString("vehicle_model"))
                .seatCapacity(rs.getObject("seat_capacity") != null ? rs.getInt("seat_capacity") : null)
                .airCondition(rs.getObject("air_condition") != null ? rs.getBoolean("air_condition") : null)
                .build()
        );
    }

    @Override
    public List<Long> getPackageIdsByTourId(Long tourId) {
        String GET_PACKAGE_IDS_BY_TOUR_ID = PackageQueries.GET_PACKAGE_IDS_BY_TOUR_ID;
        try {
            return jdbcTemplate.query(GET_PACKAGE_IDS_BY_TOUR_ID, new Object[]{tourId}, (ResultSet rs) -> {
                List<Long> packageIds = new ArrayList<>();
                while (rs.next()) {
                    packageIds.add(rs.getLong("package_id"));
                }
                return packageIds;
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package details");
        }
    }

    @Override
    public List<PackageExtrasResponse.PackageExclusion> getPackageExclusions(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_EXCLUSIONS_BY_PACKAGE_ID;
        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (rs, rowNum) ->
                    PackageExtrasResponse.PackageExclusion.builder()
                            .id(rs.getLong("package_exclusion_id"))
                            .description(rs.getString("exclusion_text"))
                            .displayOrder(rs.getInt("display_order"))
                            .status(rs.getString("status_name"))
                            .build()
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package exclusions", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package exclusions");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package exclusions", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package exclusions");
        }
    }

    @Override
    public List<PackageExtrasResponse.PackageInclusion> getPackageInclusions(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_INCLUSIONS_BY_PACKAGE_ID;
        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (rs, rowNum) ->
                    PackageExtrasResponse.PackageInclusion.builder()
                            .id(rs.getLong("package_inclusion_id"))
                            .description(rs.getString("inclusion_text"))
                            .displayOrder(rs.getInt("display_order"))
                            .status(rs.getString("status_name"))
                            .build()
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package inclusions", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package inclusions");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package inclusions", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package inclusions");
        }
    }

    @Override
    public List<PackageExtrasResponse.PackageCondition> getPackageConditions(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_CONDITIONS_BY_PACKAGE_ID;
        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (rs, rowNum) ->
                    PackageExtrasResponse.PackageCondition.builder()
                            .id(rs.getLong("package_condition_id"))
                            .description(rs.getString("condition_text"))
                            .displayOrder(rs.getInt("display_order"))
                            .status(rs.getString("status_name"))
                            .build()
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package conditions", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package conditions");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package conditions", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package conditions");
        }
    }

    @Override
    public List<PackageExtrasResponse.PackageTravelTip> getPackageTravelTips(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_TRAVEL_TIPS_BY_PACKAGE_ID;
        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (rs, rowNum) ->
                    PackageExtrasResponse.PackageTravelTip.builder()
                            .id(rs.getLong("package_travel_tip_id"))
                            .title(rs.getString("tip_title"))
                            .description(rs.getString("tip_description"))
                            .displayOrder(rs.getInt("display_order"))
                            .status(rs.getString("status_name"))
                            .build()
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package travel tips", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package travel tips");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package travel tips", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package travel tips");
        }
    }

    @Override
    public List<Long> getPackageSchedulesIdsByTourId(Long tourId) {
        String GET_PACKAGE_SCHEDULE_IDS_BY_TOUR_ID = PackageQueries.GET_PACKAGE_SCHEDULE_IDS_BY_TOUR_ID;
        try {
            return jdbcTemplate.query(GET_PACKAGE_SCHEDULE_IDS_BY_TOUR_ID, new Object[]{tourId}, (ResultSet rs) -> {
                List<Long> packageScheduleIds = new ArrayList<>();
                while (rs.next()) {
                    packageScheduleIds.add(rs.getLong("id"));
                }
                return packageScheduleIds;
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package schedule details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package schedule details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package schedule details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package schedule details");
        }
    }

    @Override
    public List<PackageScheduleResponse.PackageScheduleDetails> getPackageSchedulesById(Long packageId) {
        String GET_PACKAGE_SCHEDULE_DETAILS_BY_ID = PackageQueries.GET_PACKAGE_SCHEDULE_DETAILS_BY_ID;
        try {
            return jdbcTemplate.query(GET_PACKAGE_SCHEDULE_DETAILS_BY_ID, new Object[]{packageId}, (ResultSet rs) -> {
                List<PackageScheduleResponse.PackageScheduleDetails> packageScheduleDetailsList = new ArrayList<>();
                while (rs.next()) {
                    PackageScheduleResponse.PackageScheduleDetails packageScheduleDetails =
                            PackageScheduleResponse.PackageScheduleDetails.builder()
                                    .packageScheduleId(rs.getLong("id"))
                                    .packageId(rs.getLong("package_id"))
                                    .name(rs.getString("name"))
                                    .assumeStartDate(rs.getDate("assume_start_date") != null ? rs.getDate("assume_start_date").toLocalDate() : null)
                                    .assumeEndDate(rs.getDate("assume_end_date") != null ? rs.getDate("assume_end_date").toLocalDate() : null)
                                    .description(rs.getString("description"))
                                    .specialNote(rs.getString("special_note"))
                                    .status(rs.getString("status"))
                                    .durationStart(rs.getObject("duration_start", Integer.class))
                                    .durationEnd(rs.getObject("duration_end", Integer.class))
                                    .build();

                    packageScheduleDetailsList.add(packageScheduleDetails);
                }
                return packageScheduleDetailsList;
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package schedule details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package schedule details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package schedule details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package schedule details");
        }
    }

    @Override
    public List<PackageScheduleDetailsResponse.PackageScheduleDetails> getPackageSchedulesForId(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_SCHEDULE_DETAILS_BY_PACKAGE_ID;

        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (ResultSet rs) -> {
                List<PackageScheduleDetailsResponse.PackageScheduleDetails> schedules = new ArrayList<>();

                while (rs.next()) {
                    PackageScheduleDetailsResponse.PackageScheduleDetails schedule =
                            PackageScheduleDetailsResponse.PackageScheduleDetails.builder()
                                    .scheduleId(rs.getLong("schedule_id"))
                                    .scheduleName(rs.getString("schedule_name"))
                                    .assumeStartDate(
                                            rs.getDate("assume_start_date") != null
                                                    ? rs.getDate("assume_start_date").toLocalDate()
                                                    : null
                                    )
                                    .assumeEndDate(
                                            rs.getDate("assume_end_date") != null
                                                    ? rs.getDate("assume_end_date").toLocalDate()
                                                    : null
                                    )
                                    .durationStart(rs.getObject("duration_start", Integer.class))
                                    .durationEnd(rs.getObject("duration_end", Integer.class))
                                    .specialNote(rs.getString("special_note"))
                                    .description(rs.getString("description"))
                                    .status(rs.getString("status_name"))
                                    .createdAt(
                                            rs.getTimestamp("created_at") != null
                                                    ? rs.getTimestamp("created_at").toLocalDateTime()
                                                    : null
                                    )
                                    .updatedAt(
                                            rs.getTimestamp("updated_at") != null
                                                    ? rs.getTimestamp("updated_at").toLocalDateTime()
                                                    : null
                                    )
                                    .build();

                    schedules.add(schedule);
                }
                return schedules;
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package schedule details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package schedule details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package schedule details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package schedule details");
        }
    }


    @Override
    public PackageScheduleDetailsResponse.PackageBasicDetails getPackageBasicDetails(Long packageId) {
        String sql = PackageQueries.GET_PACKAGE_BASIC_DETAILS_BY_PACKAGE_ID;

        try {
            return jdbcTemplate.query(sql, new Object[]{packageId}, (ResultSet rs) -> {

                PackageScheduleDetailsResponse.PackageBasicDetails.PackageBasicDetailsBuilder packageBuilder = null;
                List<PackageScheduleDetailsResponse.PackageImageDetails> images = new ArrayList<>();

                while (rs.next()) {

                    // Initialize package details only once
                    if (packageBuilder == null) {
                        packageBuilder = PackageScheduleDetailsResponse.PackageBasicDetails.builder()
                                .packageId(rs.getLong("package_id"))
                                .packageName(rs.getString("name"))
                                .packageDescription(rs.getString("description"))
                                .totalPrice(rs.getObject("total_price", Double.class))
                                .pricePerPerson(rs.getObject("price_per_person", Double.class))
                                .discount(rs.getObject("discount_percentage", Double.class))
                                .color(rs.getString("color"))
                                .hoverColor(rs.getString("hover_color"))
                                .minPersonCount(rs.getObject("min_person_count", Integer.class))
                                .maxPersonCount(rs.getObject("max_person_count", Integer.class))
                                .status(rs.getString("status"));
                    }

                    // Add image if exists
                    Long imageId = rs.getObject("image_id", Long.class);
                    if (imageId != null) {
                        images.add(
                                PackageScheduleDetailsResponse.PackageImageDetails.builder()
                                        .imageId(imageId)
                                        .imageName(rs.getString("image_name"))
                                        .imageDescription(rs.getString("image_description"))
                                        .imageUrl(rs.getString("image_url"))
                                        .build()
                        );
                    }
                }

                if (packageBuilder == null) {
                    return null; // no package found
                }

                return packageBuilder.images(images).build();
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package basic details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package basic details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package basic details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package basic details");
        }
    }



}
