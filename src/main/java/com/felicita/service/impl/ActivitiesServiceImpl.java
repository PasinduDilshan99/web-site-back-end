package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
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

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesServiceImpl.class);

    private final ActivitiesRepository activitiesRepository;

    @Autowired
    public ActivitiesServiceImpl(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityResponse>>> getAllActivities() {
        LOGGER.info("Start fetching all activities from repository");
        try {
            List<ActivityResponse> activityResponses = activitiesRepository.getAllActivities();

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

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all activities from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityResponse>>> getActiveActivities() {
        LOGGER.info("Start fetching all visible activities from repository");

        try {
            List<ActivityResponse> activityResponses = activitiesRepository.getAllActivities();

            if (activityResponses.isEmpty()) {
                LOGGER.warn("No activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            List<ActivityResponse> activityResponseList = activityResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus().getName()))
                    .toList();

            if (activityResponseList.isEmpty()) {
                LOGGER.warn("No visible activities found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible activities found");
            }

            LOGGER.info("Fetched {} visible activities successfully", activityResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible activities: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible activities: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activities from database");
        } finally {
            LOGGER.info("End fetching all visible activities from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActivityCategories() {
        LOGGER.info("Start fetching activity categories from repository");
        try {
            List<ActivityCategoryResponse> activityCategoryResponses = activitiesRepository.getActivityCategories();

            if (activityCategoryResponses.isEmpty()) {
                LOGGER.warn("No activity categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No activities found");
            }

            LOGGER.info("Fetched {} activity categories successfully", activityCategoryResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityCategoryResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching activity categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching activity categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activity categories from database");
        } finally {
            LOGGER.info("End fetching activity categories from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActiveActivityCategories() {
        LOGGER.info("Start fetching all active activity categories from repository");

        try {
            List<ActivityCategoryResponse> activityCategoryResponses = activitiesRepository.getActivityCategories();

            if (activityCategoryResponses.isEmpty()) {
                LOGGER.warn("No activity category found in database");
                throw new DataNotFoundErrorExceptionHandler("No activity category found");
            }

            List<ActivityCategoryResponse> activityCategoryResponseList = activityCategoryResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (activityCategoryResponseList.isEmpty()) {
                LOGGER.warn("No active activity categories found in database");
                throw new DataNotFoundErrorExceptionHandler("No active activity categories found");
            }

            LOGGER.info("Fetched {} active activity categories successfully", activityCategoryResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            activityCategoryResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active activity categories: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active activity categories: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active activity categories from database");
        } finally {
            LOGGER.info("End fetching all active activity categories from repository");
        }
    }
}
