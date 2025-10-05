package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.ReviewResponse;
import com.felicita.model.response.TourReviewResponse;
import com.felicita.repository.ReviewRepository;
import com.felicita.service.ReviewService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<TourReviewResponse>>> getAllTourReviews() {
        LOGGER.info("Start fetching all reviews from repository");
        try {
            List<TourReviewResponse> tourReviewResponses = reviewRepository.getAllTourReviews();

            if (tourReviewResponses.isEmpty()) {
                LOGGER.warn("No reviews found in database");
                throw new DataNotFoundErrorExceptionHandler("No reviews found");
            }

            LOGGER.info("Fetched {} reviews successfully", tourReviewResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourReviewResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching reviews: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch reviews from database");
        } finally {
            LOGGER.info("End fetching all reviews from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<TourReviewResponse>>> getActiveTourReviews() {
        LOGGER.info("Start fetching all visible reviews from repository");

        try {
            List<TourReviewResponse> tourReviewResponses = reviewRepository.getAllTourReviews();

            if (tourReviewResponses.isEmpty()) {
                LOGGER.warn("No reviews found in database");
                throw new DataNotFoundErrorExceptionHandler("No reviews found");
            }

            List<TourReviewResponse> tourReviewResponseList = tourReviewResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getReviewStatus()))
                    .toList();

            if (tourReviewResponseList.isEmpty()) {
                LOGGER.warn("No visible reviews found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible reviews found");
            }

            LOGGER.info("Fetched {} visible reviews successfully", tourReviewResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourReviewResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible reviews: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch reviews from database");
        } finally {
            LOGGER.info("End fetching all visible reviews from repository");
        }
    }
}
