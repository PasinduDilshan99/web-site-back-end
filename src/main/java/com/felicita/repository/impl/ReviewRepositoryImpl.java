package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.ReviewResponse;
import com.felicita.model.response.TourReviewImageResponse;
import com.felicita.model.response.TourReviewResponse;
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
    public List<TourReviewResponse> getAllTourReviews() {
        String GET_ALL_TOUR_REVIEW = ReviewQueries.GET_ALL_TOUR_REVIEW;

        try {
            LOGGER.info("Executing query to fetch all reviews...");

            // Using LinkedHashMap to preserve order
            Map<Long, TourReviewResponse> reviewMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_TOUR_REVIEW, (rs) -> {
                Long reviewId = rs.getLong("review_id");

                // Check if review already exists in the map
                TourReviewResponse review = reviewMap.get(reviewId);
                if (review == null) {
                    review = new TourReviewResponse();
                    review.setReviewId(reviewId);
                    review.setReviewerName(rs.getString("reviewer_name"));
                    review.setReview(rs.getString("review"));
                    review.setRating(rs.getBigDecimal("rating"));
                    review.setReviewDescription(rs.getString("review_description"));
                    review.setNumberOfParticipate(rs.getInt("number_of_participate"));
                    review.setReviewCreatedAt(rs.getTimestamp("review_created_at") != null
                            ? rs.getTimestamp("review_created_at").toLocalDateTime() : null);

                    review.setScheduleId(rs.getLong("schedule_id"));
                    review.setScheduleName(rs.getString("schedule_name"));
                    review.setAssumeStartDate(rs.getTimestamp("assume_start_date") != null
                            ? rs.getTimestamp("assume_start_date").toLocalDateTime() : null);
                    review.setAssumeEndDate(rs.getTimestamp("assume_end_date") != null
                            ? rs.getTimestamp("assume_end_date").toLocalDateTime() : null);

                    review.setTourId(rs.getLong("tour_id"));
                    review.setTourName(rs.getString("tour_name"));
                    review.setTourDescription(rs.getString("tour_description"));
                    review.setStartLocation(rs.getString("start_location"));
                    review.setEndLocation(rs.getString("end_location"));

                    review.setUserId(rs.getLong("user_id"));
                    review.setUserFullName(rs.getString("user_full_name"));
                    review.setUserEmail(rs.getString("user_email"));

                    review.setReviewStatus(rs.getString("review_status"));

                    review.setImages(new ArrayList<>());
                    reviewMap.put(reviewId, review);
                }

                // Map image if present
                Long imageId = rs.getLong("image_id");
                if (imageId != null && imageId != 0) {
                    TourReviewImageResponse image = new TourReviewImageResponse();
                    image.setImageId(imageId);
                    image.setImageName(rs.getString("image_name"));
                    image.setImageDescription(rs.getString("image_description"));
                    image.setImageUrl(rs.getString("image_url"));
                    review.getImages().add(image);
                }
            });

            // Convert map values to list
            return new ArrayList<>(reviewMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching reviews: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch reviews from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching reviews: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching reviews");
        }
    }


}
