package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.PackageHistoryDetailsResponse;
import com.felicita.model.response.PackageHistoryImageResponse;
import com.felicita.model.response.PackageReviewResponse;
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
                Map<Integer, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    int packageId = rs.getInt("package_id");

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
                Map<Integer, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    int pkgId = rs.getInt("package_id");

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
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_DETAILS_BY_ID, ps -> {
                ps.setString(1, packageId);
            }, (ResultSet rs) -> {
                List<PackageHistoryDetailsResponse> result = new ArrayList<>();
                ObjectMapper objectMapper = new ObjectMapper(); // Jackson mapper

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

                    // Images JSON
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
                            images = new ArrayList<>();
                        }
                    }


                    // Build final response
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
                            .historyCreatedAt(rs.getTimestamp("history_created_at").toLocalDateTime())
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
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }


    @Override
    public List<PackageHistoryDetailsResponse> getAllPackageHistoryDetails() {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_HISTORY_DETAILS, (ResultSet rs) -> {
                List<PackageHistoryDetailsResponse> result = new ArrayList<>();
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

                    // Images (JSON array from MySQL)
                    List<PackageHistoryDetailsResponse.ImageInfo> images = new ArrayList<>();
                    String imagesJson = rs.getString("images");
                    if (imagesJson != null && !imagesJson.isEmpty()) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            images = objectMapper.readValue(
                                    imagesJson,
                                    new TypeReference<List<PackageHistoryDetailsResponse.ImageInfo>>() {}
                            );
                        } catch (JsonProcessingException e) {
                            LOGGER.warn("Failed to parse images JSON for packageHistoryId {}: {}",
                                    rs.getInt("package_history_id"), e.getMessage());
                            images = new ArrayList<>();
                        }
                    }


                    // Build final response
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
                            .historyCreatedAt(rs.getTimestamp("history_created_at").toLocalDateTime())
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
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }



}
