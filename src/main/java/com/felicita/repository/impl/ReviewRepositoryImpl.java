package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.ReviewResponse;
import com.felicita.queries.ReviewQueries;
import com.felicita.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        String GET_ALL_REVIEW = ReviewQueries.GET_ALL_REVIEW;
        try {
            LOGGER.info("Executing query to fetch all reviews...");

            Map<Long, ReviewResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_REVIEW, (rs) -> {
                Long reviewId = rs.getLong("review_id");

                // Fetch or create ReviewResponse
                ReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = ReviewResponse.builder()
                            .reviewId(reviewId)
                            .rating(rs.getInt("rating"))
                            .reviewDescription(rs.getString("review_description"))
                            .reviewStatus(rs.getString("review_status"))
                            .reviewCreatedAt(rs.getTimestamp("review_created_at").toLocalDateTime())
                            .reviewUpdatedAt(rs.getTimestamp("review_updated_at").toLocalDateTime())
                            .reviewer(ReviewResponse.UserInfo.builder()
                                    .userId(rs.getLong("user_id"))
                                    .username(rs.getString("username"))
                                    .firstName(rs.getString("first_name"))
                                    .lastName(rs.getString("last_name"))
                                    .avatarUrl(rs.getString("user_avatar"))
                                    .build())
                            .tour(rs.getObject("tour_id") != null ? ReviewResponse.TourInfo.builder()
                                    .tourId(rs.getLong("tour_id"))
                                    .name(rs.getString("tour_name"))
                                    .build() : null)
                            .packageInfo(rs.getObject("package_id") != null ? ReviewResponse.PackageInfo.builder()
                                    .packageId(rs.getLong("package_id"))
                                    .name(rs.getString("package_name"))
                                    .build() : null)
                            .images(new ArrayList<>())
                            .reactions(new ArrayList<>())
                            .comments(new ArrayList<>())
                            .build();

                    reviewMap.put(reviewId, review);
                }

                // Add image if exists
                Long imageId = rs.getObject("image_id", Long.class);
                if (imageId != null && review.getImages().stream().noneMatch(img -> img.getId().equals(imageId))) {
                    review.getImages().add(ReviewResponse.ReviewImage.builder()
                            .id(imageId)
                            .imageUrl(rs.getString("image_url"))
                            .build());
                }

                // Add reaction if exists
                Long reactionId = rs.getObject("reaction_id", Long.class);
                if (reactionId != null && review.getReactions().stream().noneMatch(r -> r.getId().equals(reactionId))) {
                    review.getReactions().add(ReviewResponse.ReviewReaction.builder()
                            .id(reactionId)
                            .reactionType(rs.getString("reaction_type"))
                            .reactedByUserId(rs.getLong("reacted_by_user_id"))
                            .reactedByUsername(rs.getString("reacted_by_username"))
                            .build());
                }

                // Add comment if exists
                Long commentId = rs.getObject("comment_id", Long.class);
                if (commentId != null && review.getComments().stream().noneMatch(c -> c.getId().equals(commentId))) {
                    ReviewResponse.UserInfo commentUser = ReviewResponse.UserInfo.builder()
                            .userId(rs.getLong("comment_user_id"))
                            .username(rs.getString("comment_user_name"))
                            .build();

                    review.getComments().add(ReviewResponse.ReviewComment.builder()
                            .id(commentId)
                            .commentText(rs.getString("comment_text"))
                            .parentId(rs.getObject("comment_parent_id") != null ? rs.getLong("comment_parent_id") : null)
                            .commentedBy(commentUser)
                            .createdAt(rs.getTimestamp("comment_created_at").toLocalDateTime())
                            .build());
                }
            });

            List<ReviewResponse> results = new ArrayList<>(reviewMap.values());
            LOGGER.info("Successfully fetched {} reviews.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching reviews: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching reviews: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching reviews");
        }
    }

}
