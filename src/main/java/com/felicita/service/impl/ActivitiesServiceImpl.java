package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.ActivityDataRequest;
import com.felicita.model.request.ActivityInsertRequest;
import com.felicita.model.request.ActivityTerminateRequest;
import com.felicita.model.response.*;
import com.felicita.repository.ActivitiesRepository;
import com.felicita.service.ActivitiesService;
import com.felicita.service.CommonService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.ActivityValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesServiceImpl.class);

    private final ActivitiesRepository activitiesRepository;
    private final ActivityValidationService activityValidationService;
    private final CommonService commonService;

    @Autowired
    public ActivitiesServiceImpl(ActivitiesRepository activitiesRepository,
                                 ActivityValidationService activityValidationService,
                                 CommonService commonService) {
        this.activitiesRepository = activitiesRepository;
        this.activityValidationService = activityValidationService;
        this.commonService = commonService;
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getAllActivities() {
        LOGGER.info("Start fetching all activities from repository");
        try {
            List<ActivityResponseDto> activityResponses = activitiesRepository.getAllActivities();

            if (activityResponses.isEmpty()) {
                LOGGER.warn("No activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            LOGGER.info("Fetched {} activities successfully", activityResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all activities from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getActiveActivities() {
        LOGGER.info("Start fetching active activities from repository");
        try {
            List<ActivityResponseDto> activityResponses = activitiesRepository.getAllActivities();

            if (activityResponses.isEmpty()) {
                LOGGER.warn("No activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            List<ActivityResponseDto> activityResponseDtoList = activityResponses.stream()
                    .filter(t -> CommonStatus.ACTIVE.name().equalsIgnoreCase(t.getStatus()))
                    .toList();
            LOGGER.info("Fetched {} activities successfully", activityResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityResponseDtoList,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all activities from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getAllActivityCategories() {
        LOGGER.info("Start fetching all activities from repository");
        try {
            List<ActivityCategoryResponseDto> activityCategoryResponseDtos = activitiesRepository.getAllActivityCategories();

            if (activityCategoryResponseDtos.isEmpty()) {
                LOGGER.warn("No activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            LOGGER.info("Fetched {} activities successfully", activityCategoryResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityCategoryResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all activities from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getActiveActivityCategories() {
        LOGGER.info("Start fetching active activities from repository");
        try {
            List<ActivityCategoryResponseDto> activityCategoryResponseDtos = activitiesRepository.getAllActivityCategories();

            if (activityCategoryResponseDtos.isEmpty()) {
                LOGGER.warn("No activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            List<ActivityCategoryResponseDto> activityCategoryResponseDtoList = activityCategoryResponseDtos.stream()
                    .filter(t -> CommonStatus.ACTIVE.name().equalsIgnoreCase(t.getCategoryStatus()))
                    .toList();
            LOGGER.info("Fetched {} activities successfully", activityCategoryResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityCategoryResponseDtoList,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all activities from repository");
        }
    }

    @Override
    public CommonResponse<List<ActivityReviewDetailsResponse>> getAllActivityReviewDetails() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<ActivityReviewDetailsResponse> activityReviewDetailsResponses = activitiesRepository.getAllActivityReviewDetails();

            if (activityReviewDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            List<ActivityReviewDetailsResponse> activityReviewDetailsResponseList = activityReviewDetailsResponses.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getReviewStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active package successfully", activityReviewDetailsResponseList.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    activityReviewDetailsResponseList,
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
    public CommonResponse<List<ActivityReviewDetailsResponse>> getActivityReviewDetailsById(String activityId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<ActivityReviewDetailsResponse> activityReviewDetailsResponseList = activitiesRepository.getActivityReviewDetailsById(activityId);

            if (activityReviewDetailsResponseList.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityReviewDetailsResponseList,
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
    public CommonResponse<ActivityResponseDto> getActivityById(String activityId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            ActivityResponseDto activityResponseDto = activitiesRepository.getActivityById(activityId);

            if (activityResponseDto == null) {
                LOGGER.warn("No package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityResponseDto,
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
    public CommonResponse<List<ActivityHistoryDetailsResponse>> getAllActivityHistoryDetails() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<ActivityHistoryDetailsResponse> activityHistoryDetailsResponses = activitiesRepository.getAllActivityHistoryDetails();

            if (activityHistoryDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityHistoryDetailsResponses,
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
    public CommonResponse<List<ActivityHistoryDetailsResponse>> getActivityHistoryDetailsById(String activityId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<ActivityHistoryDetailsResponse> activityHistoryDetailsResponses = activitiesRepository.getActivityHistoryDetailsById(activityId);

            if (activityHistoryDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityHistoryDetailsResponses,
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
    public CommonResponse<List<ActivityHistoryImageResponse>> getAllActivityHistoryImages() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<ActivityHistoryImageResponse> activityHistoryImageResponses = activitiesRepository.getAllActivityHistoryImages();

            if (activityHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityHistoryImageResponses,
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
    public CommonResponse<List<ActivityHistoryImageResponse>> getActivityHistoryImagesById(String activityId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<ActivityHistoryImageResponse> activityHistoryImageResponses = activitiesRepository.getActivityHistoryImagesById(activityId);

            if (activityHistoryImageResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityHistoryImageResponses,
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
    public CommonResponse<ActivityWithParamsResponse> getActivitiesWithParams(ActivityDataRequest activityDataRequest) {
        LOGGER.info("Start fetching all activities with params from repository");
        try {
            ActivityWithParamsResponse activityWithParamsResponse = activitiesRepository.getActivitiesWithParams(activityDataRequest);

            if (activityWithParamsResponse == null) {
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
                    activityWithParamsResponse,
                            Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities with params : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities with params : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities with params from database");
        } finally {
            LOGGER.info("End fetching all activities with params from repository");
        }
    }

    @Override
    public CommonResponse<List<ActivityForTerminateResponse>> getActivitiesForTerminate() {
        LOGGER.info("Start fetching active activities from repository");
        try {
            List<ActivityForTerminateResponse> activityForTerminateResponses =
                    activitiesRepository.getActivitiesForTerminate();

            if (activityForTerminateResponses.isEmpty()) {
                LOGGER.warn("No active activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No active activities found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityForTerminateResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active activities from database");
        } finally {
            LOGGER.info("End fetching active activities from repository");
        }
    }

    @Override
    public CommonResponse<TerminateResponse> terminateActivity(ActivityTerminateRequest activityTerminateRequest) {
        LOGGER.info("Start execute terminate destination request.");
        try {
            activityValidationService.validateTerminateActivityRequest(activityTerminateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            activitiesRepository.terminateActivity(activityTerminateRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new TerminateResponse("Successfully terminate activity request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the terminate activity request", vfe.getValidationFailedResponses());
        } catch (TerminateFailedErrorExceptionHandler tfe) {
            throw new TerminateFailedErrorExceptionHandler(tfe.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertActivity(ActivityInsertRequest activityInsertRequest) {
        try {
            activityValidationService.validateActivityInsertRequest(activityInsertRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            Long activityId = activitiesRepository.insertActivityDetails(activityInsertRequest, userId);
            activitiesRepository.insertActivityImages(activityId,activityInsertRequest.getImages(), userId);
            activitiesRepository.insertActivityRequirements(activityId,activityInsertRequest.getRequirements(), userId);

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new InsertResponse("Successfully insert activity request"),
                            Instant.now());

        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert activity request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

//    @Override
//    public CommonResponse<List<ActivityBasicDetailsResponse>> getActivitiesByDestinationId(String destinationId) {
//        LOGGER.info("Start fetching activities by destination id from repository");
//        try {
//            List<ActivityBasicDetailsResponse> activityBasicDetailsResponses =
//                    activitiesRepository.getActivityById(activityId);
//
//            if (activityBasicDetailsResponses.isEmpty()) {
//                LOGGER.warn("No activities found for id in database");
//                throw new DataNotFoundErrorExceptionHandler("No activities found");
//            }
//
//            return
//                    new CommonResponse<>(
//                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
//                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
//                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
//                            activityBasicDetailsResponses,
//                            Instant.now()
//                    )
//                    ;
//
//        } catch (DataNotFoundErrorExceptionHandler e) {
//            LOGGER.error("Error occurred while fetching activities by destination id: {}", e.getMessage(), e);
//            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
//        } catch (Exception e) {
//            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
//            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
//        } finally {
//            LOGGER.info("End fetching all package from repository");
//        }
//    }


}
