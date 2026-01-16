package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.request.TourInsertRequest;
import com.felicita.model.request.TourUpdateRequest;
import com.felicita.model.response.*;
import com.felicita.repository.TourRepository;
import com.felicita.service.CommonService;
import com.felicita.service.DestinationService;
import com.felicita.service.TourService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.TourValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourRepository tourRepository;
    private final DestinationService destinationService;
    private final TourValidationService tourValidationService;
    private final CommonService commonService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository,
                           DestinationService destinationService,
                           TourValidationService tourValidationService,
                           CommonService commonService) {
        this.tourRepository = tourRepository;
        this.destinationService = destinationService;
        this.tourValidationService = tourValidationService;
        this.commonService = commonService;
    }


    @Override
    public ResponseEntity<CommonResponse<List<TourResponseDto>>> getAllTours() {
        LOGGER.info("Start fetching all tours from repository");
        try {
            List<TourResponseDto> tourResponseDtos = tourRepository.getAllTours();

            if (tourResponseDtos.isEmpty()) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            LOGGER.info("Fetched {} tours successfully", tourResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours from database");
        } finally {
            LOGGER.info("End fetching all tours from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<TourResponseDto>>> getActiveTours() {
        LOGGER.info("Start fetching all active tours from repository");
        try {
            List<TourResponseDto> tourResponseDtos = tourRepository.getAllTours();

            if (tourResponseDtos.isEmpty()) {
                LOGGER.warn("No active tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            List<TourResponseDto> tourResponseDtoList = tourResponseDtos.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getStatusName()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active tours successfully", tourResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourResponseDtoList,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active tours from database");
        } finally {
            LOGGER.info("End fetching all active tours from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PopularTourResponseDto>>> getPopularTours() {
        LOGGER.info("Start fetching all active tours from repository");
        try {
            List<PopularTourResponseDto> popularTours = tourRepository.getPopularTours();

            if (popularTours.isEmpty()) {
                LOGGER.warn("No active tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            LOGGER.info("Fetched {} active tours successfully", popularTours.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            popularTours,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active tours from database");
        } finally {
            LOGGER.info("End fetching all active tours from repository");
        }
    }

    @Override
    public CommonResponse<TourResponseDto> getTourDetailsById(String tourId) {
        LOGGER.info("Start fetching all tours from repository");
        try {
            TourResponseDto tourResponseDto = tourRepository.getTourDetailsById(tourId);

            if (tourResponseDto == null) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourResponseDto,
                            Instant.now()
                    );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours from database");
        } finally {
            LOGGER.info("End fetching all tours from repository");
        }
    }

    @Override
    public CommonResponse<List<TourReviewDetailsResponse>> getAllTourReviewDetails() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<TourReviewDetailsResponse> tourReviewDetailsResponses = tourRepository.getAllTourReviewDetails();

            if (tourReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            List<TourReviewDetailsResponse> tourReviewDetailsResponseList = tourReviewDetailsResponses.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getReviewStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active package successfully", tourReviewDetailsResponseList.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourReviewDetailsResponseList,
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
    public CommonResponse<List<TourReviewDetailsResponse>> getTourReviewDetailsById(String tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<TourReviewDetailsResponse> tourReviewDetailsResponses = tourRepository.getTourReviewDetailsById(tourId);

            if (tourReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourReviewDetailsResponses,
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
    public CommonResponse<List<TourDestinationsForMapResponse>> getTourDestinationsForMap(String tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<TourDestinationsForMapResponse> tourDestinationsForMapResponses = tourRepository.getTourDestinationsForMap(tourId);

            if (tourDestinationsForMapResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourDestinationsForMapResponses,
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
    public CommonResponse<List<TourHistoryResponse>> getAllTourHistoryDetails() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<TourHistoryResponse> tourHistoryResponses = tourRepository.getAllTourHistoryDetails();

            if (tourHistoryResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourHistoryResponses,
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
    public CommonResponse<List<TourHistoryResponse>> getTourHistoryDetailsById(String tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<TourHistoryResponse> tourHistoryResponses = tourRepository.getTourHistoryDetailsById(tourId);

            if (tourHistoryResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourHistoryResponses,
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
    public CommonResponse<List<TourHistoryImageResponse>> getAllTourHistoryImages() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<TourHistoryImageResponse> tourHistoryImageResponses = tourRepository.getAllTourHistoryImages();

            if (tourHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourHistoryImageResponses,
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
    public CommonResponse<List<TourHistoryImageResponse>> getTourHistoryImagesById(String tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<TourHistoryImageResponse> tourHistoryImageResponses = tourRepository.getTourHistoryImagesById(tourId);

            if (tourHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourHistoryImageResponses,
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
    public CommonResponse<ToursDetailsWithParamResponse> getToursToShowWithParam(TourDataRequest tourDataRequest) {
        LOGGER.info("Start fetching tours for params from repository");
        try {
            ToursDetailsWithParamResponse toursDetailsWithParamResponse = tourRepository.getToursToShowWithParam(tourDataRequest);

            if (toursDetailsWithParamResponse == null) {
                LOGGER.warn("No tours for param found in database");
                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                        null,
                        Instant.now()
                );
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    toursDetailsWithParamResponse,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours for param: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours for param: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours for param from database");
        } finally {
            LOGGER.info("End fetching all tours for param from repository");
        }
    }

    @Override
    public CommonResponse<List<TourDetailsWithDayToDayResponse>> getTourDetailsDayByDay(Long tourId) {
        LOGGER.info("Start fetching all tours from repository");
        try {
            List<TourDayDestinationActivityIdsDto> tourDayDestinationActivityIdsDtos = tourRepository.getTourDayDestinationActivityIds(tourId);

            List<TourDetailsIdDayByDayReponse> tourDetailsIdDayByDayResponses = tourDayDestinationActivityIdsDtos.stream()
                    .collect(Collectors.groupingBy(TourDayDestinationActivityIdsDto::getDay)) // group by day
                    .entrySet().stream()
                    .map(entry -> {
                        int day = entry.getKey();
                        List<TourDetailsIdDayByDayReponse.DestinationDetails> destinationDetails = entry.getValue().stream()
                                .map(dto -> TourDetailsIdDayByDayReponse.DestinationDetails.builder()
                                        .destinationId(Long.valueOf(dto.getDestinationId()))
                                        .activityIds(dto.getActivityIds().stream()
                                                .map(Long::valueOf)
                                                .toList())
                                        .build())
                                .toList();

                        return TourDetailsIdDayByDayReponse.builder()
                                .day(day)
                                .destinationDetails(destinationDetails)
                                .build();
                    })
                    .sorted(Comparator.comparingInt(TourDetailsIdDayByDayReponse::getDay)) // optional: sort by day
                    .toList();


            List<Long> destinationIdList = tourDayDestinationActivityIdsDtos.stream()
                    .map(TourDayDestinationActivityIdsDto::getDestinationId)
                    .filter(Objects::nonNull)
                    .map(Long::valueOf)
                    .distinct()
                    .toList();

            List<Long> activityIdList = tourDayDestinationActivityIdsDtos.stream()
                    .flatMap(dto -> dto.getActivityIds().stream())
                    .filter(Objects::nonNull)
                    .map(Long::valueOf)
                    .distinct()
                    .toList();


            List<TourDetailsWithDayToDayResponse.DestinationDetailsPerDay> destionationsDetailsList =
                    tourRepository.getDestinationsDetailsByIds(destinationIdList);
            List<TourDetailsWithDayToDayResponse.ActivityPerDayResponse> activityDetailsList =
                    tourRepository.getActivityDetailsByIds(activityIdList);

            List<TourDetailsWithDayToDayResponse> response = new ArrayList<>();

            for (TourDetailsIdDayByDayReponse dayDetail : tourDetailsIdDayByDayResponses) {
                TourDetailsWithDayToDayResponse dayResponse = new TourDetailsWithDayToDayResponse();
                dayResponse.setDayNumber(dayDetail.getDay());

                List<TourDetailsWithDayToDayResponse.DestinationPerDayResponse> destinationPerDayResponses = new ArrayList<>();

                for (TourDetailsIdDayByDayReponse.DestinationDetails dest : dayDetail.getDestinationDetails()) {
                    TourDetailsWithDayToDayResponse.DestinationPerDayResponse destResponse = new TourDetailsWithDayToDayResponse.DestinationPerDayResponse();

                    // Find destination details
                    TourDetailsWithDayToDayResponse.DestinationDetailsPerDay destinationDetails = destionationsDetailsList.stream()
                            .filter(d -> d.getDestinationId().equals(dest.getDestinationId()))
                            .findFirst()
                            .orElse(null);

                    destResponse.setDestination(destinationDetails);

                    // Find activities for this destination
                    List<TourDetailsWithDayToDayResponse.ActivityPerDayResponse> activitiesForDestination = activityDetailsList.stream()
                            .filter(a -> dest.getActivityIds().contains(a.getId()))
                            .toList();

                    destResponse.setActivities(activitiesForDestination);

                    destinationPerDayResponses.add(destResponse);
                }

                dayResponse.setDestinations(destinationPerDayResponses);
                response.add(dayResponse);
            }


            if (tourDayDestinationActivityIdsDtos.isEmpty()) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            LOGGER.info("Fetched {} tours successfully", tourDayDestinationActivityIdsDtos);
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    response,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours from database");
        } finally {
            LOGGER.info("End fetching all tours from repository");
        }
    }

    @Override
    public CommonResponse<TourExtrasResponse> getTourExtraDetailsDayByDay(Long tourId) {
        LOGGER.info("Start fetching tour extra details by tour id from repository");
        try {
            List<TourExtrasResponse.TourInclusion> inclusions = tourRepository.getTourInclusions(tourId);
            List<TourExtrasResponse.TourExclusion> exclusions = tourRepository.getTourExclusions(tourId);
            List<TourExtrasResponse.TourCondition> conditions = tourRepository.getTourConditions(tourId);
            List<TourExtrasResponse.TourTravelTip> travelTips = tourRepository.getTourTravelTips(tourId);

            TourExtrasResponse tourExtrasResponse = new TourExtrasResponse(
                    inclusions,
                    exclusions,
                    conditions,
                    travelTips
            );

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourExtrasResponse,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tour extra details by tour id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tour extra details by tour id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour extra details by tour id from database");
        } finally {
            LOGGER.info("End fetching all tour extra details by tour id from repository");
        }
    }

    @Override
    public CommonResponse<TourSchedulesResponse> getTourSchedules(Long tourId) {
        LOGGER.info("Start fetching tour schedules from the repository");
        try {
            List<TourSchedulesResponse.TourScheduleDetails> scheduleDetails =
                    tourRepository.getTourSchedulesById(tourId);
            TourSchedulesResponse.TourBasicDetails tourBasicDetails =
                    tourRepository.getTourBasicDetails(tourId);


            TourSchedulesResponse tourSchedulesResponse = new TourSchedulesResponse(
                    tourBasicDetails,
                    scheduleDetails
            );

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourSchedulesResponse,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tour schedules by tour id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tour schedules by tour id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour schedules by tour id from database");
        } finally {
            LOGGER.info("End fetching all tour schedules by tour id from repository");
        }
    }

    @Override
    public CommonResponse<List<TourBasicDetailsResponse>> getAllToursBasicDetails() {
        LOGGER.info("Start fetching all tours basic details from repository");
        try {
            List<TourBasicDetailsResponse> tourBasicDetailsResponses = tourRepository.getAllToursBasicDetails();

            if (tourBasicDetailsResponses.isEmpty()) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            LOGGER.info("Fetched {} tours basic details successfully", tourBasicDetailsResponses.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourBasicDetailsResponses,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours basic details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours basic details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours basic details from database");
        } finally {
            LOGGER.info("End fetching all tours basic details from repository");
        }
    }

    @Override
    public CommonResponse<List<TourForTerminateResponse>> getToursForTerminate() {
        LOGGER.info("Start fetching active tours from repository");
        try {
            List<TourForTerminateResponse> tourForTerminateResponses =
                    tourRepository.getToursForTerminate();

            if (tourForTerminateResponses.isEmpty()) {
                LOGGER.warn("No active tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No active tours found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourForTerminateResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active tours from database");
        } finally {
            LOGGER.info("End fetching active tours from repository");
        }
    }

    @Override
    public CommonResponse<TerminateResponse> terminateTour(TourTerminateRequest tourTerminateRequest) {
        LOGGER.info("Start execute terminate tour request.");
        try {
            tourValidationService.validateTerminateTourRequest(tourTerminateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            tourRepository.terminateTour(tourTerminateRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new TerminateResponse("Successfully terminate tour request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the terminate tour request", vfe.getValidationFailedResponses());
        } catch (TerminateFailedErrorExceptionHandler tfe) {
            throw new TerminateFailedErrorExceptionHandler(tfe.getMessage());
        } catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertTour(TourInsertRequest tourInsertRequest) {
        LOGGER.info("Start execute insert tour request.");
        try {
            tourValidationService.validateTourInsertRequest(tourInsertRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            Long tourId = tourRepository.insertTourDetails(tourInsertRequest, userId);
            tourRepository.insertTourDestinations(tourId, tourInsertRequest.getDestinations(), userId);
            tourRepository.insertTourImages(tourId, tourInsertRequest.getImages(), userId);
            tourRepository.insertTourInclusions(tourId, tourInsertRequest.getInclusions(), userId);
            tourRepository.insertTourExclusions(tourId, tourInsertRequest.getExclusions(), userId);
            tourRepository.insertTourConditions(tourId, tourInsertRequest.getConditions(), userId);
            tourRepository.insertTourTravelTips(tourId, tourInsertRequest.getTravelTips(), userId);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    new InsertResponse("Successfully insert tour request"),
                    Instant.now());
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert tour request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<List<TourIdAndTourNameResponse>> getTourIdsAndTourNames() {
        CommonResponse<List<TourForTerminateResponse>> toursForTerminate = getToursForTerminate();
        List<TourIdAndTourNameResponse> tourIdAndTourNameResponses = new ArrayList<>();
        for (TourForTerminateResponse tourForTerminateResponse : toursForTerminate.getData()) {
            tourIdAndTourNameResponses.add(
                    new TourIdAndTourNameResponse(
                            tourForTerminateResponse.getTourId(),
                            tourForTerminateResponse.getTourName()
                    )
            );
        }
        return new CommonResponse<>(
                CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                tourIdAndTourNameResponses,
                Instant.now());
    }

    @Override
    public CommonResponse<TourDetailsForAddPackageResponse> getTourDetailsForAddPackage(Long tourId) {
        LOGGER.info("Start fetching tour details from the repository");
        try {
            TourDetailsForAddPackageResponse tourDetailsForAddPackageResponses =
                    tourRepository.getTourDetailsForAddPackage(tourId);
            List<String> tourInclusions = tourRepository.getTourInclusionsNamesOnly(tourId);
            List<String> tourExclusions = tourRepository.getTourExclusionsNamesOnly(tourId);
            List<String> tourConditions = tourRepository.getTourConditionsNamesOnly(tourId);
            List<TourDetailsForAddPackageResponse.TravelTip> tourTravelTips = tourRepository.getTourTravelTipsNamesOnly(tourId);

            tourDetailsForAddPackageResponses.setInclusions(tourInclusions);
            tourDetailsForAddPackageResponses.setExclusions(tourExclusions);
            tourDetailsForAddPackageResponses.setConditions(tourConditions);
            tourDetailsForAddPackageResponses.setTravelTips(tourTravelTips);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourDetailsForAddPackageResponses,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tour details by tour id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tour details by tour id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour details by tour id from database");
        } finally {
            LOGGER.info("End fetching all tour details by tour id from repository");
        }
    }

    @Override
    public CommonResponse<TourAllDetailsResponse> getTourAllDetailsById(Long tourId) {
        LOGGER.info("Start fetching all tours from repository");
        try {
            TourAllDetailsResponse tourAllDetailsResponse = new TourAllDetailsResponse();
            TourResponseDto tourResponseDto = tourRepository.getTourDetailsById(String.valueOf(tourId));

            TourAssignUserDto tourAssignUserDto = tourRepository.getTourAssignUserDetailsByTourId(tourId);
            tourAllDetailsResponse.setTourId(tourResponseDto.getTourId());
            tourAllDetailsResponse.setTourName(tourResponseDto.getTourName());
            tourAllDetailsResponse.setTourDescription(tourResponseDto.getTourDescription());
            tourAllDetailsResponse.setDuration(tourResponseDto.getDuration());
            tourAllDetailsResponse.setLongitude(tourResponseDto.getLongitude());
            tourAllDetailsResponse.setLatitude(tourResponseDto.getLatitude());
            tourAllDetailsResponse.setStartLocation(tourResponseDto.getStartLocation());
            tourAllDetailsResponse.setEndLocation(tourResponseDto.getEndLocation());
            tourAllDetailsResponse.setTourTypeName(tourResponseDto.getTourTypeName());
            tourAllDetailsResponse.setTourCategoryName(tourResponseDto.getTourCategoryName());
            tourAllDetailsResponse.setSeasonName(tourResponseDto.getSeasonName());
            tourAllDetailsResponse.setTourTypeDescription(tourResponseDto.getTourTypeDescription());
            tourAllDetailsResponse.setTourCategoryDescription(tourResponseDto.getTourCategoryDescription());
            tourAllDetailsResponse.setSeasonDescription(tourResponseDto.getSeasonDescription());
            tourAllDetailsResponse.setStatusName(tourResponseDto.getStatusName());
            tourAllDetailsResponse.setSchedules(tourResponseDto.getSchedules());
            tourAllDetailsResponse.setImages(tourResponseDto.getImages());
            tourAllDetailsResponse.setAssignTo(tourAssignUserDto.getAssignTo());
            tourAllDetailsResponse.setAssignToName(tourAssignUserDto.getAssignToName());
            tourAllDetailsResponse.setAssignMessage(tourAssignUserDto.getAssignMessage());

            List<TourExtrasResponse.TourExclusion> tourExclusions = tourRepository.getTourExclusions(tourId);
            List<TourExtrasResponse.TourCondition> tourConditions = tourRepository.getTourConditions(tourId);
            List<TourExtrasResponse.TourInclusion> tourInclusions = tourRepository.getTourInclusions(tourId);
            List<TourExtrasResponse.TourTravelTip> tourTravelTips = tourRepository.getTourTravelTips(tourId);

            tourAllDetailsResponse.setExclusions(tourExclusions);
            tourAllDetailsResponse.setConditions(tourConditions);
            tourAllDetailsResponse.setInclusions(tourInclusions);
            tourAllDetailsResponse.setTravelTips(tourTravelTips);

            List<TourDetailsWithDayToDayResponse> tourDetailsDayByDay = getTourDetailsDayByDay(tourId).getData();
            List<TourAllDetailsResponse.DayToDayResponse> dayToDayResponses = new ArrayList<>();

            for (TourDetailsWithDayToDayResponse daySource : tourDetailsDayByDay) {

                TourAllDetailsResponse.DayToDayResponse dayTarget =
                        new TourAllDetailsResponse.DayToDayResponse();
                dayTarget.setDayNumber(daySource.getDayNumber());

                List<TourAllDetailsResponse.DestinationPerDayResponse> destinationTargets = new ArrayList<>();

                for (TourDetailsWithDayToDayResponse.DestinationPerDayResponse destSource : daySource.getDestinations()) {

                    TourAllDetailsResponse.DestinationPerDayResponse destTarget =
                            new TourAllDetailsResponse.DestinationPerDayResponse();

                    // ---- Destination details
                    TourAllDetailsResponse.DestinationDetailsPerDay destDetails =
                            new TourAllDetailsResponse.DestinationDetailsPerDay();

                    var srcDest = destSource.getDestination();

                    destDetails.setDestinationId(srcDest.getDestinationId());
                    destDetails.setDestinationName(srcDest.getDestinationName());
                    destDetails.setDestinationDescription(srcDest.getDestinationDescription());
                    destDetails.setCategory(srcDest.getCategory());
                    destDetails.setLocation(srcDest.getLocation());
                    destDetails.setLatitude(srcDest.getLatitude());
                    destDetails.setLongitude(srcDest.getLongitude());

                    // ---- Destination images
                    List<TourAllDetailsResponse.DestinationImagePerDay> destImages = new ArrayList<>();
                    if (srcDest.getImages() != null) {
                        for (var img : srcDest.getImages()) {
                            destImages.add(
                                    TourAllDetailsResponse.DestinationImagePerDay.builder()
                                            .imageId(img.getImageId())
                                            .imageName(img.getImageName())
                                            .imageDescription(img.getImageDescription())
                                            .imageUrl(img.getImageUrl())
                                            .imageStatus(img.getImageStatus())
                                            .build()
                            );
                        }
                    }
                    destDetails.setImages(destImages);
                    destTarget.setDestination(destDetails);

                    // ---- Activities
                    List<TourAllDetailsResponse.ActivityPerDay> activityTargets = new ArrayList<>();

                    for (var actSource : destSource.getActivities()) {

                        TourAllDetailsResponse.ActivityPerDay actTarget =
                                new TourAllDetailsResponse.ActivityPerDay();

                        actTarget.setActivityId(actSource.getId());
                        actTarget.setDestinationId(actSource.getDestinationId());
                        actTarget.setActivityName(actSource.getName());
                        actTarget.setActivityDescription(actSource.getDescription());
                        actTarget.setActivitiesCategory(actSource.getActivitiesCategory());
                        actTarget.setDurationHours(actSource.getDurationHours());
                        actTarget.setAvailableFrom(actSource.getAvailableFrom());
                        actTarget.setAvailableTo(actSource.getAvailableTo());
                        actTarget.setMinParticipate(actSource.getMinParticipate());
                        actTarget.setMaxParticipate(actSource.getMaxParticipate());
                        actTarget.setSeason(actSource.getSeason());
                        actTarget.setCategoryName(actSource.getCategoryName());

                        // ---- Activity images
                        List<TourAllDetailsResponse.ActivityImagePerDay> actImages = new ArrayList<>();
                        if (actSource.getImages() != null) {
                            for (var img : actSource.getImages()) {
                                actImages.add(
                                        TourAllDetailsResponse.ActivityImagePerDay.builder()
                                                .imageId(img.getId())
                                                .imageName(img.getName())
                                                .imageDescription(img.getDescription())
                                                .imageUrl(img.getImageUrl())
                                                .build()
                                );
                            }
                        }
                        actTarget.setImages(actImages);

                        activityTargets.add(actTarget);
                    }

                    destTarget.setActivities(activityTargets);
                    destinationTargets.add(destTarget);
                }

                dayTarget.setDestinations(destinationTargets);
//                LOGGER.info("Day target: {}", dayTarget);
                dayToDayResponses.add(dayTarget);
            }

//            LOGGER.info(dayToDayResponses.toString());
            tourAllDetailsResponse.setDayToDayResponses(dayToDayResponses);

            if (tourResponseDto == null) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourAllDetailsResponse,
                            Instant.now()
                    );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tours from database");
        } finally {
            LOGGER.info("End fetching all tours from repository");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updateTour(TourUpdateRequest tourUpdateRequest) {
        LOGGER.info("Start execute update tour request.");
        try {
            tourValidationService.validateTourUpdateRequest(tourUpdateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            tourRepository.updateTourBasicDetails(tourUpdateRequest.getTourId(), tourUpdateRequest.getTourBasicDetails(), userId);

            tourRepository.insertTourDestinations(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddDestinations(), userId);
            tourRepository.removeTourDestinations(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveDestinations(), userId);
            tourRepository.updateTourDestinations(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateDestinations(), userId);

            tourRepository.insertTourImages(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddImages(), userId);
            tourRepository.removeTourImages(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveImages(), userId);
            tourRepository.updateTourImages(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateImages(), userId);

            tourRepository.insertTourInclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddInclusions(), userId);
            tourRepository.removeTourInclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveInclusions(), userId);
            tourRepository.updateTourInclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateInclusions(), userId);

            tourRepository.insertTourExclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddExclusions(), userId);
            tourRepository.removeTourExclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveExclusions(), userId);
            tourRepository.updateTourExclusions(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateExclusions(), userId);

            tourRepository.insertTourConditions(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddConditions(), userId);
            tourRepository.removeTourConditions(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveConditions(), userId);
            tourRepository.updateTourConditions(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateConditions(), userId);

            tourRepository.insertTourTravelTips(tourUpdateRequest.getTourId(), tourUpdateRequest.getAddTravelTips(), userId);
            tourRepository.removeTourTravelTips(tourUpdateRequest.getTourId(), tourUpdateRequest.getRemoveTravelTips(), userId);
            tourRepository.updateTourTravelTips(tourUpdateRequest.getTourId(), tourUpdateRequest.getUpdateTravelTips(), userId);


            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    new UpdateResponse("Successfully update tour request", tourUpdateRequest.getTourId()),
                    Instant.now());
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update tour request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

}
