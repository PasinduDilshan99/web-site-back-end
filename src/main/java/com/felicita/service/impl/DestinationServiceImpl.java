package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.*;
import com.felicita.repository.DestinationRepository;
import com.felicita.service.DestinationService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DestinationServiceImpl implements DestinationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationServiceImpl.class);

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinations() {
        LOGGER.info("Start fetching all destinations from repository");
        try {
            List<DestinationResponse> destinationResponses = destinationRepository.getAllDestinations();

            if (destinationResponses.isEmpty()) {
                LOGGER.warn("No destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} destinations successfully", destinationResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations from database");
        } finally {
            LOGGER.info("End fetching all destinations from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllActiveDestinationsCategory() {
        LOGGER.info("Start fetching all active destinations categories from repository");
        try {
            List<DestinationCategoryResponse> destinationCategoryResponses = destinationRepository.getAllActiveDestinationsCategory();

            if (destinationCategoryResponses.isEmpty()) {
                LOGGER.warn("No active destinations categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} active destinations categories successfully", destinationCategoryResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationCategoryResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active destinations categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active destinations categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active destinations categories from database");
        } finally {
            LOGGER.info("End fetching all active destinations categories from repository");
        }

    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinationsByCategoryId(String categoryId) {
        LOGGER.info("Start fetching all destinations by category id from repository");
        try {
            List<DestinationResponse> destinationResponses = destinationRepository.getAllDestinationsByCategoryId(categoryId);

            if (destinationResponses.isEmpty()) {
                LOGGER.warn("No destinations by category id found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations by category id found");
            }

            LOGGER.info("Fetched {} destinations by category id successfully", destinationResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations by category id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations by category id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations by category id from database");
        } finally {
            LOGGER.info("End fetching all destinations by category id from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<DestinationResponse>> getDestinationDetailsById(String destinationId) {
        LOGGER.info("Start fetching destination by id from repository");
        try {
            DestinationResponse destinationResponse = destinationRepository.getDestinationDetailsById(destinationId);

            if (destinationResponse == null) {
                LOGGER.warn("No destination by id found in database");
                throw new DataNotFoundErrorExceptionHandler("No destination by id found");
            }

            LOGGER.info("Fetched destination by id successfully");
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponse,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destination by id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destination by  id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destination by  id from database");
        } finally {
            LOGGER.info("End fetching all destination by  id from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllDestinationsCategory() {
        LOGGER.info("Start fetching all destinations categories from repository");
        try {
            List<DestinationCategoryResponse> destinationCategoryResponses = destinationRepository.getAllDestinationsCategory();

            if (destinationCategoryResponses.isEmpty()) {
                LOGGER.warn("No destinations categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} destinations categories successfully", destinationCategoryResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationCategoryResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching  destinations categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching  destinations categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch  destinations categories from database");
        } finally {
            LOGGER.info("End fetching all  destinations categories from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllActiveDestinations() {
        LOGGER.info("Start fetching all active destinations from repository");
        try {
            List<DestinationResponse> destinationResponses = destinationRepository.getAllActiveDestinations();

            if (destinationResponses.isEmpty()) {
                LOGGER.warn("No active destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} active destinations successfully", destinationResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active destinations: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active destinations from database");
        } finally {
            LOGGER.info("End fetching all active destinations from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllPopularDestinations() {
        LOGGER.info("Start fetching popular destinations from repository");
        try {
            List<DestinationResponse> destinationResponses = destinationRepository.getAllDestinations();

            List<DestinationResponse> popularDestinations = destinationResponses.stream()
                    .filter(data -> data.getPopularity() > 3)
                    .collect(Collectors.toList());

            if (popularDestinations.isEmpty()) {
                LOGGER.warn("No popular destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No popular destinations found");
            }

            LOGGER.info("Fetched {} popular destinations successfully", popularDestinations.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            popularDestinations,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching popular destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch popular destinations from database");
        } finally {
            LOGGER.info("End fetching popular destinations from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<TrendingDestinationResponse>>> getAllTrendingDestinations() {
        LOGGER.info("Start fetching trending destinations from repository");
        try {
            List<TrendingDestinationResponse> trendingDestinationResponses = destinationRepository.getAllTrendingDestinations();

            if (trendingDestinationResponses.isEmpty()) {
                LOGGER.warn("No trending destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No popular destinations found");
            }

            LOGGER.info("Fetched {} trending destinations successfully", trendingDestinationResponses.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            trendingDestinationResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching trending destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch trending destinations from database");
        } finally {
            LOGGER.info("End fetching trending destinations from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllNewDestinations() {
        LOGGER.info("Start fetching all new destinations from repository");
        try {
            List<DestinationResponse> destinationResponses = destinationRepository.getAllDestinations();

            if (destinationResponses.isEmpty()) {
                LOGGER.warn("No new destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
            List<DestinationResponse> lastMonthDestinations = destinationResponses.stream()
                    .filter(destination -> destination.getCreatedAt() != null)
                    .filter(destination -> destination.getCreatedAt().isAfter(oneMonthAgo))
                    .collect(Collectors.toList());

            if (lastMonthDestinations.isEmpty()) {
                LOGGER.warn("No new destinations found from the last month");
                throw new DataNotFoundErrorExceptionHandler("No destinations found from the last month");
            }

            LOGGER.info("Fetched {} new destinations from the last month successfully", lastMonthDestinations.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            lastMonthDestinations,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching new destinations: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching new destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch new destinations from database");
        } finally {
            LOGGER.info("End fetching all new destinations from repository");
        }
    }

}
