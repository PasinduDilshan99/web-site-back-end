package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.response.*;
import com.felicita.repository.TourRepository;
import com.felicita.service.DestinationService;
import com.felicita.service.TourService;
import com.felicita.util.CommonResponseMessages;
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

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, DestinationService destinationService) {
        this.tourRepository = tourRepository;
        this.destinationService = destinationService;
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

            List<TourDetailsWithDayToDayResponse.Accommodations> accommodations =
                    tourRepository.getAccomadationsListByTourId(tourId);

// Step 1: Map accommodations by day for fast lookup
            Map<Integer, TourDetailsWithDayToDayResponse.Accommodations> accMap = accommodations.stream()
                    .collect(Collectors.toMap(TourDetailsWithDayToDayResponse.Accommodations::getDay, Function.identity()));

// Step 2: Attach accommodation to each day
            for (TourDetailsWithDayToDayResponse dayResponse : response) {
                TourDetailsWithDayToDayResponse.Accommodations acc = accMap.get(dayResponse.getDayNumber());
                dayResponse.setAccommodations(acc);  // set acc (can be null if no data)
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

}
