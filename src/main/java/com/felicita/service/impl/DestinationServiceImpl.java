package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.DestinationDataRequest;
import com.felicita.model.request.DestinationInsertRequest;
import com.felicita.model.request.DestinationTerminateRequest;
import com.felicita.model.request.DestinationUpdateRequest;
import com.felicita.model.response.*;
import com.felicita.repository.DestinationRepository;
import com.felicita.service.CommonService;
import com.felicita.service.DestinationService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.DestinationValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationServiceImpl.class);

    private final DestinationRepository destinationRepository;
    private final DestinationValidationService destinationValidationService;
    private final CommonService commonService;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository,
                                  DestinationValidationService destinationValidationService,
                                  CommonService commonService) {
        this.destinationRepository = destinationRepository;
        this.destinationValidationService = destinationValidationService;
        this.commonService = commonService;
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
                            d.getPopularCreatedAt().isAfter(LocalDateTime.now().minusMonths(6)))
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

    @Override
    public CommonResponse<List<DestinationResponseDto>> getDestinationDetailsByTourId(String tourId) {
        LOGGER.info("Start fetching all destinations from repository");
        try {
            List<DestinationResponseDto> destinationResponseDtos = destinationRepository.getDestinationDetailsByTourId(tourId);

            if (destinationResponseDtos.isEmpty()) {
                LOGGER.warn("No destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No destinations found");
            }

            LOGGER.info("Fetched {} destinations successfully", destinationResponseDtos.size());
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationResponseDtos,
                            Instant.now()
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
    public CommonResponse<List<DestinationReviewDetailsResponse>> getAllDestinationsReviewDetails() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<DestinationReviewDetailsResponse> destinationReviewDetailsResponses = destinationRepository.getAllDestinationsReviewDetails();

            if (destinationReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            List<DestinationReviewDetailsResponse> destinationReviewDetailsResponseList = destinationReviewDetailsResponses.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getReviewStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active package successfully", destinationReviewDetailsResponseList.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    destinationReviewDetailsResponseList,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active package from database");
        } finally {
            LOGGER.info("End fetching all active package from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationReviewDetailsResponse>> getDestinationReviewDetailsById(String destinationId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<DestinationReviewDetailsResponse> destinationReviewDetailsResponses = destinationRepository.getDestinationReviewDetailsById(destinationId);

            if (destinationReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationReviewDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<DestinationResponseDto> getDestinationDetailsById(String destinationId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            DestinationResponseDto destinationDetailsById = destinationRepository.getDestinationDetailsById(destinationId);

            if (destinationDetailsById == null) {
                LOGGER.warn("No package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationDetailsById,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationHistoryDetailsResponse>> getAllDestinationHistoryDetails() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<DestinationHistoryDetailsResponse> destinationReviewDetailsResponses = destinationRepository.getAllDestinationHistoryDetails();

            if (destinationReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationReviewDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationHistoryDetailsResponse>> getDestinationHistoryDetailsById(String destinationId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<DestinationHistoryDetailsResponse> destinationReviewDetailsResponses = destinationRepository.getDestinationHistoryDetailsById(destinationId);

            if (destinationReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationReviewDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationHistoryImageResponse>> getAllDestinationHistoryImages() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<DestinationHistoryImageResponse> destinationHistoryImageResponses = destinationRepository.getAllDestinationHistoryImages();

            if (destinationHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationHistoryImageResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationHistoryImageResponse>> getDestinationHistoryImagesById(String destinationId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<DestinationHistoryImageResponse> destinationHistoryImageResponses = destinationRepository.getDestinationHistoryImagesById(destinationId);

            if (destinationHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationHistoryImageResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<DestinationsWithParamsResponse> getDestinationWithParams(DestinationDataRequest destinationDataRequest) {
        LOGGER.info("Start fetching all destinations with params from repository");
        try {
            DestinationsWithParamsResponse destinationsWithParamsResponse = destinationRepository.getDestinationWithParams(destinationDataRequest);

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    destinationsWithParamsResponse,
                            Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching destinations with params : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching destinations with params: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destinations with params from database");
        } finally {
            LOGGER.info("End fetching all destinations with params from repository");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertDestination(DestinationInsertRequest destinationInsertRequest) {
        LOGGER.info("Start execute insert destination request.");
        try {
            destinationValidationService.validateDestinationInsertRequest(destinationInsertRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            destinationRepository.insertDestination(destinationInsertRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new InsertResponse("Successfully insert destination request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert destination request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<TerminateResponse> terminateDestination(DestinationTerminateRequest destinationTerminateRequest) {
        LOGGER.info("Start execute terminate destination request.");
        try {
            destinationValidationService.validateTerminateDestinationRequest(destinationTerminateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            destinationRepository.terminateDestination(destinationTerminateRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new TerminateResponse("Successfully terminate destination request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the terminate destination request", vfe.getValidationFailedResponses());
        } catch (TerminateFailedErrorExceptionHandler tfe) {
            throw new TerminateFailedErrorExceptionHandler(tfe.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<List<DestinationForTerminateResponse>> getDestinationsForTerminate() {
        LOGGER.info("Start fetching active destinations from repository");
        try {
            List<DestinationForTerminateResponse> destinationForTerminateResponses =
                    destinationRepository.getDestinationsForTerminate();

            if (destinationForTerminateResponses.isEmpty()) {
                LOGGER.warn("No active destinations found in database");
                throw new DataNotFoundErrorExceptionHandler("No active destinations found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            destinationForTerminateResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active destinations: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active destinations: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active destinations from database");
        } finally {
            LOGGER.info("End fetching active destinations from repository");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updateDestination(DestinationUpdateRequest destinationUpdateRequest) {
        LOGGER.info("Start execute update destination request.");
        try {
            destinationValidationService.validateDestinationUpdateRequest(destinationUpdateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            destinationRepository.updateBasicDestinationDetails(destinationUpdateRequest, userId);
            destinationRepository.removeDestinationImages(destinationUpdateRequest.getRemoveImages(), userId);
            destinationRepository.addNewImagesToDestination(destinationUpdateRequest.getNewImages(), destinationUpdateRequest.getDestinationId(),userId);
            destinationRepository.removeDestinationActivities(destinationUpdateRequest.getRemoveActivities(), userId);
            destinationRepository.addNewActivitiesToDestination(destinationUpdateRequest.getNewActivities(), destinationUpdateRequest.getDestinationId(), userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse("Successfully update destination request",destinationUpdateRequest.getDestinationId()),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert destination request", vfe.getValidationFailedResponses());
        } catch (UpdateFailedErrorExceptionHandler ufe) {
            throw new UpdateFailedErrorExceptionHandler(ufe.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

}
