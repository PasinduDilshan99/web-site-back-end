package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.ActivityReviewDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PackageReviewResponse;
import com.felicita.repository.ActivitiesRepository;
import com.felicita.service.ActivitiesService;
import com.felicita.util.CommonResponseMessages;
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

    @Autowired
    public ActivitiesServiceImpl(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
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


}
