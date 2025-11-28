package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.*;
import com.felicita.queries.*;
import com.felicita.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.*;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProfileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserProfileDetailsResponse getUserProfileDetails(UserProfileDetailsRequest req) {
        String GET_USER_PROFILE_DETAILS = UserProfileQueries.GET_USER_PROFILE_DETAILS;
        try {
            LOGGER.info("Executing query to fetch user profile details...");
            return jdbcTemplate.queryForObject(
                    GET_USER_PROFILE_DETAILS,
                    new Object[]{req.getUserId()},
                    (rs, rowNum) -> {
                        UserProfileDetailsResponse response = new UserProfileDetailsResponse();
                        response.setUserId(rs.getInt("user_id"));
                        response.setUsername(rs.getString("username"));
                        response.setFirstName(rs.getString("first_name"));
                        response.setMiddleName(rs.getString("middle_name"));
                        response.setLastName(rs.getString("last_name"));
                        response.setNic(rs.getString("nic"));
                        response.setPassportNumber(rs.getString("passport_number"));
                        response.setDrivingLicenseNumber(rs.getString("driving_license_number"));
                        response.setEmail(rs.getString("email"));
                        response.setMobileNumber1(rs.getString("mobile_number1"));
                        response.setMobileNumber2(rs.getString("mobile_number2"));
                        response.setDateOfBirth(rs.getString("date_of_birth"));
                        response.setImageUrl(rs.getString("image_url"));
                        response.setCreatedAt(rs.getString("created_at"));
                        response.setUpdatedAt(rs.getString("updated_at"));
                        response.setBenefitsPointsCount(rs.getInt("benefits_points_count"));
                        response.setAddressNumber(rs.getString("address_number"));
                        response.setAddressLine1(rs.getString("address_line1"));
                        response.setAddressLine2(rs.getString("address_line2"));
                        response.setCity(rs.getString("city"));
                        response.setDistrict(rs.getString("district"));
                        response.setProvince(rs.getString("province"));
                        response.setCountryName(rs.getString("country_name"));
                        response.setPostalCode(rs.getString("postal_code"));
                        response.setGender(rs.getString("gender"));
                        response.setReligion(rs.getString("religion"));
                        response.setUserType(rs.getString("user_type"));
                        response.setUserTypeDescription(rs.getString("user_type_description"));
                        response.setUserStatus(rs.getString("user_status"));
                        response.setUserStatusDescription(rs.getString("user_status_description"));
                        response.setWalletNumber(rs.getString("wallet_number"));
                        response.setWalletAmount(rs.getDouble("amount"));
                        response.setWalletStatus(rs.getString("wallet_status"));
                        return response;
                    }
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user profile details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user profile from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user profile details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user profile");
        }
    }

    @Override
    public List<UserProfileSidebarResponse> getUserProfileSideBar() {
        String GET_USER_PROFILE_SIDEBAR = UserProfileQueries.GET_USER_PROFILE_SIDEBAR;
        try {
            List<UserProfileSidebarResponse> flatList = jdbcTemplate.query(GET_USER_PROFILE_SIDEBAR, (rs, rowNum) -> {
                UserProfileSidebarResponse resp = new UserProfileSidebarResponse();
                resp.setId(rs.getInt("id"));
                resp.setParentId(rs.getObject("parent_id") != null ? rs.getInt("parent_id") : null);
                resp.setName(rs.getString("name"));
                resp.setDescription(rs.getString("description"));
                resp.setPrivilegeName(rs.getString("privilege_name"));
                resp.setStatus(rs.getString("status_name"));
                resp.setUrl(rs.getString("url"));
                return resp;
            });
            return buildSidebarTree(flatList);
        } catch (Exception ex) {
            LOGGER.error("Error fetching sidebar: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch sidebar data");
        }
    }

    @Override
    public List<UserProfileReviewResponse> getUserProfileReviews(Long userId) {
        String GET_USER_PROFILE_REVIEWS = UserProfileQueries.GET_USER_PROFILE_REVIEWS;

        try {
            LOGGER.info("Executing query to fetch all reviews...");
            Map<Long, UserProfileReviewResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_USER_PROFILE_REVIEWS, new Object[]{userId},(rs) -> {
                Long reviewId = rs.getLong("review_id");

                UserProfileReviewResponse review = reviewMap.get(reviewId);

                if (review == null) {
                    review = new UserProfileReviewResponse();
                    review.setReviewId(reviewId);
                    review.setRating(rs.getInt("rating"));
                    review.setReviewDescription(rs.getString("review_description"));
                    review.setReviewStatus(rs.getString("review_status"));
                    review.setReviewCreatedAt(rs.getTimestamp("review_created_at") != null
                            ? rs.getTimestamp("review_created_at").toLocalDateTime() : null);

                    UserProfileReviewResponse.UserInfo reviewer = UserProfileReviewResponse.UserInfo.builder()
                            .userId(rs.getLong("user_id"))
                            .username(rs.getString("reviewer_name"))
                            .firstName(null)            // not in SELECT
                            .lastName(null)             // not in SELECT
                            .avatarUrl(null)            // not in SELECT
                            .build();
                    review.setReviewer(reviewer);

                    UserProfileReviewResponse.TourInfo tourInfo = UserProfileReviewResponse.TourInfo.builder()
                            .tourId(rs.getLong("tour_id"))
                            .name(rs.getString("tour_name"))
                            .build();
                    review.setTour(tourInfo);

                    UserProfileReviewResponse.PackageInfo packageInfo =
                            UserProfileReviewResponse.PackageInfo.builder()
                                    .packageId(rs.getLong("schedule_id"))
                                    .name(rs.getString("schedule_name"))
                                    .build();
                    review.setPackageInfo(packageInfo);

                    review.setImages(new ArrayList<>());
                    review.setReactions(new ArrayList<>());
                    review.setComments(new ArrayList<>());

                    reviewMap.put(reviewId, review);
                }

                Long imageId = rs.getLong("image_id");
                if (!rs.wasNull()) {
                    UserProfileReviewResponse.ReviewImage img =
                            UserProfileReviewResponse.ReviewImage.builder()
                                    .id(imageId)
                                    .imageUrl(rs.getString("image_url"))
                                    .build();

                    review.getImages().add(img);
                }
            });

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching reviews: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching reviews: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching reviews");
        }
    }

    @Override
    public List<UserProfilePackageReviewResponse> getUserProfilePackageReviews(Long userId) {
        String GET_USER_PROFILE_PACKAGE_REVIEWS = UserProfileQueries.GET_USER_PROFILE_PACKAGE_REVIEWS;

        return jdbcTemplate.query(GET_USER_PROFILE_PACKAGE_REVIEWS,new Object[]{userId}, (ResultSet rs) -> {
            Map<Long, UserProfilePackageReviewResponse> reviewMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long reviewId = rs.getLong("review_id");

                // --------------------------------------------------
                // MAIN REVIEW OBJECT (If Not Already Created)
                // --------------------------------------------------
                UserProfilePackageReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = UserProfilePackageReviewResponse.builder()
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

                // --------------------------------------------------
                // IMAGES
                // --------------------------------------------------
                Long imageId = rs.getLong("image_id");
                if (!rs.wasNull() && review.getImages().stream().noneMatch(i -> i.getId().equals(imageId))) {

                    review.getImages().add(
                            UserProfilePackageReviewResponse.Image.builder()
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

                // --------------------------------------------------
                // REVIEW REACTIONS
                // --------------------------------------------------
                Long reactionId = rs.getLong("review_reaction_id");
                if (!rs.wasNull() &&
                        review.getReactions().stream().noneMatch(r -> r.getId().equals(reactionId))) {

                    review.getReactions().add(
                            UserProfilePackageReviewResponse.Reaction.builder()
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

                // --------------------------------------------------
                // COMMENTS
                // --------------------------------------------------
                Long commentId = rs.getLong("comment_id");

                UserProfilePackageReviewResponse.Comment comment = null;

                if (!rs.wasNull()) {
                    // Check if already added
                    Optional<UserProfilePackageReviewResponse.Comment> existingComment =
                            review.getComments().stream()
                                    .filter(c -> c.getId().equals(commentId))
                                    .findFirst();

                    if (existingComment.isPresent()) {
                        comment = existingComment.get();
                    } else {
                        comment = UserProfilePackageReviewResponse.Comment.builder()
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

                    // --------------------------------------------------
                    // COMMENT REACTIONS
                    // --------------------------------------------------
                    Long commentReactionId = rs.getLong("comment_reaction_id");
                    if (!rs.wasNull() &&
                            comment.getReactions().stream().noneMatch(cr -> cr.getId().equals(commentReactionId))) {

                        comment.getReactions().add(
                                UserProfilePackageReviewResponse.CommentReaction.builder()
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
    public List<UserProfileDestinationReviewResponse> getUserProfileDestiantionsReviews(Long userId) {
        String GET_USER_PROFILE_DESTIANTION_REVIEWS = UserProfileQueries.GET_USER_PROFILE_DESTIANTION_REVIEWS;

        try {
            Map<Integer, UserProfileDestinationReviewResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_USER_PROFILE_DESTIANTION_REVIEWS, new Object[]{userId}, rs -> {

                Integer reviewId = rs.getInt("review_id");

                // --------------------------------------------------------
                // CREATE REVIEW (if new)
                // --------------------------------------------------------
                UserProfileDestinationReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = UserProfileDestinationReviewResponse.builder()
                            .reviewId(reviewId)
                            .destinationId(rs.getInt("destination_id"))
                            .destinationName(rs.getString("destination_name"))
                            .reviewUserId(rs.getInt("review_user_id"))
                            .reviewUserName(rs.getString("review_user_name"))
                            .reviewText(rs.getString("review_text"))
                            .reviewRating(rs.getBigDecimal("review_rating"))
                            .reviewStatus(rs.getString("review_status"))
                            .reviewCreatedBy(rs.getInt("review_created_by"))
                            .reviewCreatedAt(rs.getTimestamp("review_created_at") != null ?
                                    rs.getTimestamp("review_created_at").toLocalDateTime() : null)
                            .reviewUpdatedBy(rs.getInt("review_updated_by"))
                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at") != null ?
                                    rs.getTimestamp("review_updated_at").toLocalDateTime() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();

                    reviewMap.put(reviewId, review);
                }

                // --------------------------------------------------------
                // IMAGES
                // --------------------------------------------------------
                Integer imageId = rs.getObject("image_id", Integer.class);
                if (imageId != null &&
                        review.getImages().stream().noneMatch(i -> i.getImageId().equals(imageId))) {

                    review.getImages().add(
                            UserProfileDestinationReviewResponse.Image.builder()
                                    .imageId(imageId)
                                    .imageName(rs.getString("image_name"))
                                    .imageDescription(rs.getString("image_description"))
                                    .imageUrl(rs.getString("image_url"))
                                    .imageStatus(rs.getString("image_status"))
                                    .imageCreatedBy(rs.getInt("image_created_by"))
                                    .imageCreatedAt(rs.getTimestamp("image_created_at") != null ?
                                            rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // --------------------------------------------------------
                // REVIEW REACTIONS
                // --------------------------------------------------------
                Integer reactionId = rs.getObject("review_reaction_id", Integer.class);
                if (reactionId != null &&
                        review.getReactions().stream().noneMatch(r -> r.getReviewReactionId().equals(reactionId))) {

                    review.getReactions().add(
                            UserProfileDestinationReviewResponse.Reaction.builder()
                                    .reviewReactionId(reactionId)
                                    .reactionReviewId(rs.getInt("reaction_review_id"))
                                    .reactionUserId(rs.getInt("reaction_user_id"))
                                    .reactionUserName(rs.getString("reaction_user_name"))
                                    .reactionType(rs.getString("reaction_type"))
                                    .reviewReactionStatus(rs.getString("review_reaction_status"))
                                    .reactionCreatedAt(rs.getTimestamp("reaction_created_at") != null ?
                                            rs.getTimestamp("reaction_created_at").toLocalDateTime() : null)
                                    .build()
                    );
                }

                // --------------------------------------------------------
                // COMMENTS
                // --------------------------------------------------------
                Integer commentId = rs.getObject("comment_id", Integer.class);
                UserProfileDestinationReviewResponse.Comment comment = null;

                if (commentId != null) {
                    // find existing comment
                    comment = review.getComments().stream()
                            .filter(c -> c.getCommentId().equals(commentId))
                            .findFirst()
                            .orElse(null);

                    if (comment == null) {
                        comment = UserProfileDestinationReviewResponse.Comment.builder()
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

                // --------------------------------------------------------
                // COMMENT REACTIONS
                // --------------------------------------------------------
                Integer commentReactionId = rs.getObject("comment_reaction_id", Integer.class);

                if (commentReactionId != null && comment != null &&
                        comment.getCommentReactions().stream()
                                .noneMatch(cr -> cr.getCommentReactionId().equals(commentReactionId))) {

                    comment.getCommentReactions().add(
                            UserProfileDestinationReviewResponse.Comment.CommentReaction.builder()
                                    .commentReactionId(commentReactionId)
                                    .commentReactionCommentId(rs.getInt("comment_reaction_comment_id"))
                                    .commentReactionUserId(rs.getInt("comment_reaction_user_id"))
                                    .commentReactionUserName(rs.getString("comment_reaction_user_name"))
                                    .commentReactionType(rs.getString("comment_reaction_type"))
                                    .commentReactionStatus(rs.getString("comment_reaction_status"))
                                    .commentReactionCreatedBy(rs.getInt("comment_reaction_created_by"))
                                    .commentReactionCreatedAt(rs.getTimestamp("comment_reaction_created_at") != null ?
                                            rs.getTimestamp("comment_reaction_created_at").toLocalDateTime() : null)
                                    .build()
                    );
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
    public List<UserProfileActivitesReviewsResponse> getUserProfileActivitesReviews(Long userId) {
        String GET_USER_PROFILE_ACTIVITIES_REVIEWS = UserProfileQueries.GET_USER_PROFILE_ACTIVITIES_REVIEWS;

        try {
            LOGGER.info("Executing query to fetch all activity review details...");

            // Map to store reviews by reviewId for aggregating nested lists
            Map<Long, UserProfileActivitesReviewsResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_USER_PROFILE_ACTIVITIES_REVIEWS, new Object[]{userId},rs -> {
                Long reviewId = rs.getLong("review_id");
                UserProfileActivitesReviewsResponse review = reviewMap.get(reviewId);

                if (review == null) {
                    review = UserProfileActivitesReviewsResponse.builder()
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
                    review.getImages().add(UserProfileActivitesReviewsResponse.ReviewImage.builder()
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
                    review.getReactions().add(UserProfileActivitesReviewsResponse.ReviewReaction.builder()
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
                UserProfileActivitesReviewsResponse.Comment comment = null;
                if (commentId != null) {
                    comment = review.getComments().stream()
                            .filter(c -> c.getCommentId().equals(commentId))
                            .findFirst()
                            .orElse(null);
                    if (comment == null) {
                        comment = UserProfileActivitesReviewsResponse.Comment.builder()
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
                        comment.getCommentReactions().add(UserProfileActivitesReviewsResponse.Comment.CommentReaction.builder()
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
    public List<UserProfileTourReviewResponse> getUserProfileTourReviews(Long userId) {
        String sql = UserProfileQueries.GET_USER_PROFILE_TOUR_REVIEWS;

        try {
            LOGGER.info("Executing query to fetch all reviews...");

            Map<Long, UserProfileTourReviewResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(sql, new Object[]{userId}, rs -> {
                Long reviewId = rs.getLong("review_id");

                UserProfileTourReviewResponse review = reviewMap.get(reviewId);

                if (review == null) {
                    review = UserProfileTourReviewResponse.builder()
                            .reviewId(reviewId)
                            .reviewerName(rs.getString("reviewer_name"))
                            .review(rs.getString("review"))
                            .rating(rs.getBigDecimal("rating"))
                            .reviewDescription(rs.getString("review_description"))
                            .numberOfParticipate(rs.getInt("number_of_participate"))
                            .reviewCreatedAt(
                                    rs.getTimestamp("review_created_at") != null
                                            ? rs.getTimestamp("review_created_at").toLocalDateTime()
                                            : null
                            )
                            .scheduleId(rs.getLong("schedule_id"))
                            .scheduleName(rs.getString("schedule_name"))
                            .assumeStartDate(
                                    rs.getTimestamp("assume_start_date") != null
                                            ? rs.getTimestamp("assume_start_date").toLocalDateTime()
                                            : null
                            )
                            .assumeEndDate(
                                    rs.getTimestamp("assume_end_date") != null
                                            ? rs.getTimestamp("assume_end_date").toLocalDateTime()
                                            : null
                            )
                            .tourId(rs.getLong("tour_id"))
                            .tourName(rs.getString("tour_name"))
                            .tourDescription(rs.getString("tour_description"))
                            .startLocation(rs.getString("start_location"))
                            .endLocation(rs.getString("end_location"))
                            .userId(rs.getLong("user_id"))
                            .userFullName(rs.getString("user_full_name"))
                            .userEmail(rs.getString("user_email"))
                            .reviewStatus(rs.getString("review_status"))
                            .images(new ArrayList<>())
                            .build();

                    reviewMap.put(reviewId, review);
                }

                // Process images safely
                Long imageId = rs.getObject("image_id", Long.class);
                if (imageId != null &&
                        review.getImages().stream().noneMatch(img -> img.getImageId().equals(imageId))) {

                    TourReviewImageResponse img = new TourReviewImageResponse();
                    img.setImageId(imageId);
                    img.setImageName(rs.getString("image_name"));
                    img.setImageDescription(rs.getString("image_description"));
                    img.setImageUrl(rs.getString("image_url"));

                    review.getImages().add(img);
                }
            });

            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching reviews: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching reviews: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching reviews");
        }
    }

    @Override
    public UserProfileWalletResponse getUserProfileWalletDetails(Long userId) {
        String GET_USER_PROFILE_WALLET = UserProfileQueries.GET_USER_PROFILE_WALLET;
        try {
            LOGGER.info("Executing query to fetch user profile wallet details...");

            return jdbcTemplate.queryForObject(
                    GET_USER_PROFILE_WALLET,
                    new Object[]{userId},
                    (rs, rowNum) -> UserProfileWalletResponse.builder()
                            .userId(rs.getLong("user_id"))
                            .username(rs.getString("username"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .walletId(rs.getLong("wallet_id") != 0 ? rs.getLong("wallet_id") : null)
                            .walletNumber(rs.getString("wallet_number"))
                            .amount(rs.getBigDecimal("amount") != null ? rs.getBigDecimal("amount").doubleValue() : 0.0)
                            .walletStatusId(rs.getLong("wallet_status_id") != 0 ? rs.getLong("wallet_status_id") : null)
                            .walletStatusName(rs.getString("wallet_status_name"))
                            .walletStatusDescription(rs.getString("wallet_status_description"))
                            .walletCreatedAt(rs.getTimestamp("wallet_created_at") != null
                                    ? rs.getTimestamp("wallet_created_at").toLocalDateTime().toString() : null)
                            .walletUpdatedAt(rs.getTimestamp("wallet_updated_at") != null
                                    ? rs.getTimestamp("wallet_updated_at").toLocalDateTime().toString() : null)
                            .build()
            );

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user profile wallet details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user profile wallet from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user profile wallet details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user profile wallet");
        }
    }



    private List<UserProfileSidebarResponse> buildSidebarTree(List<UserProfileSidebarResponse> flatList) {
        Map<Integer, UserProfileSidebarResponse> map = new HashMap<>();
        flatList.forEach(item -> map.put(item.getId(), item));
        List<UserProfileSidebarResponse> rootItems = new ArrayList<>();
        for (UserProfileSidebarResponse item : flatList) {
            if (item.getParentId() == null) {
                rootItems.add(item);
            } else {
                UserProfileSidebarResponse parent = map.get(item.getParentId());
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(item);
            }
        }
        return rootItems;
    }

}
