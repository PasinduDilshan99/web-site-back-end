package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
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
    public ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllDestinations() {
        LOGGER.info("Start fetching all destinations from repository");
        try {
            List<DestinationResponseDto> destinationResponseDtos = destinationRepository.getAllDestinations();

            if (destinationResponseDtos.isEmpty()) {
                LOGGER.warn("No destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} destinations successfully", destinationResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponseDtos,
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
    public ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllActiveDestinations() {
        LOGGER.info("Start fetching all active destinations from repository");
        try {
            List<DestinationResponseDto> destinationResponseDtos = destinationRepository.getAllDestinations();

            if (destinationResponseDtos.isEmpty()) {
                LOGGER.warn("No active destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            List<DestinationResponseDto> destinationResponseDtoList = destinationResponseDtos.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getStatusName()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active destinations successfully", destinationResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponseDtoList,
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
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getAllDestinationsCategories() {
        LOGGER.info("Start fetching all destinations categories from repository");
        try {
            List<DestinationCategoryResponseDto> destinationCategoryResponseDtos = destinationRepository.getAllDestinationsCategories();

            if (destinationCategoryResponseDtos.isEmpty()) {
                LOGGER.warn("No destinations categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations categories found");
            }

            LOGGER.info("Fetched {} destinations categories successfully", destinationCategoryResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationCategoryResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations categories from database");
        } finally {
            LOGGER.info("End fetching all destinations categories from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getActiveDestinationsCategories() {
        LOGGER.info("Start fetching all destinations categories from repository");
        try {
            List<DestinationCategoryResponseDto> destinationCategoryResponseDtos = destinationRepository.getAllDestinationsCategories();

            if (destinationCategoryResponseDtos.isEmpty()) {
                LOGGER.warn("No destinations categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations categories found");
            }

            List<DestinationCategoryResponseDto> destinationCategoryResponseDtoList = destinationCategoryResponseDtos.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getCategoryStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} destinations categories successfully", destinationCategoryResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationCategoryResponseDtoList,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations categories from database");
        } finally {
            LOGGER.info("End fetching all destinations categories from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getPopularDestinations() {
        LOGGER.info("Start fetching all popular destinations from repository");
        try {
            List<PopularDestinationResponseDto> popularDestinationResponseDtos = destinationRepository.getPopularDestinations();

            if (popularDestinationResponseDtos.isEmpty()) {
                LOGGER.warn("No popular  destinations  found in database");
                throw new DataNotFoundErrorExceptionHandler("No popular destinations found");
            }

            LOGGER.info("Fetched {} popular destinations  successfully", popularDestinationResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            popularDestinationResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch popular destinations  from database");
        } finally {
            LOGGER.info("End fetching all popular destinations  from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getNewDestinations() {
        LOGGER.info("Start fetching all popular destinations from repository");
        try {
            List<PopularDestinationResponseDto> popularDestinationResponseDtos =
                    destinationRepository.getPopularDestinations();

            List<PopularDestinationResponseDto> lastMonthDestinations = popularDestinationResponseDtos.stream()
                    .filter(d -> d.getPopularCreatedAt() != null &&
                            d.getPopularCreatedAt().isAfter(LocalDateTime.now().minusMonths(1)))
                    .collect(Collectors.toList());


            if (lastMonthDestinations.isEmpty()) {
                LOGGER.warn("No popular  destinations  found in database");
                throw new DataNotFoundErrorExceptionHandler("No popular destinations found");
            }

            LOGGER.info("Fetched {} popular destinations  successfully", lastMonthDestinations.size());
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
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch popular destinations  from database");
        } finally {
            LOGGER.info("End fetching all popular destinations  from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<TrendingDestinationResponseDto>>> getTrendingDestinations() {
        LOGGER.info("Start fetching all popular destinations from repository");
        try {
            List<TrendingDestinationResponseDto> trendingDestinationResponseDtos = destinationRepository.getTrendingDestinations();

            if (trendingDestinationResponseDtos.isEmpty()) {
                LOGGER.warn("No popular  destinations  found in database");
                throw new DataNotFoundErrorExceptionHandler("No popular destinations found");
            }

            LOGGER.info("Fetched {} popular destinations  successfully", trendingDestinationResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            trendingDestinationResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching popular destinations : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch popular destinations  from database");
        } finally {
            LOGGER.info("End fetching all popular destinations  from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<DestinationsForTourMapDto>>> getDestinationsForTourMap() {
        LOGGER.info("Start fetching get destinations for tour map from repository");
        try {
            List<DestinationsForTourMapDto> destinationsForTourMapDtos = destinationRepository.getDestinationsForTourMap();

            if (destinationsForTourMapDtos.isEmpty()) {
                LOGGER.warn("No destinations for tour map  found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations for tour map found");
            }

            LOGGER.info("Fetched {} destinations for tour map  successfully", destinationsForTourMapDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationsForTourMapDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations for tour map : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations for tour map : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations for tour map  from database");
        } finally {
            LOGGER.info("End fetching all destinations for tour map  from repository");
        }
    }

}
