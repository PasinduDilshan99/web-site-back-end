package com.felicita.repository;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.request.*;
import com.felicita.model.response.*;

import java.util.List;

public interface ActivitiesRepository {
    List<ActivityResponseDto> getAllActivities();

    List<ActivityCategoryResponseDto> getAllActivityCategories();

    List<ActivityReviewDetailsResponse> getActivityReviewDetailsById(String activityId);

    List<ActivityReviewDetailsResponse> getAllActivityReviewDetails();

    ActivityResponseDto getActivityById(String activityId);

    List<ActivityHistoryDetailsResponse> getActivityHistoryDetailsById(String activityId);

    List<ActivityHistoryDetailsResponse> getAllActivityHistoryDetails();

    List<ActivityHistoryImageResponse> getAllActivityHistoryImages();

    List<ActivityHistoryImageResponse> getActivityHistoryImagesById(String activityId);

    ActivityWithParamsResponse getActivitiesWithParams(ActivityDataRequest activityDataRequest);

    List<ActivityForTerminateResponse> getActivitiesForTerminate();

    void terminateActivity(ActivityTerminateRequest activityTerminateRequest, Long userId);

    Long insertActivityDetails(ActivityInsertRequest activityInsertRequest, Long userId);

    void insertActivityImages(Long activityId, List<ActivityImageInsertRequest> images, Long userId);

    void insertActivityRequirements(Long activityId, List<ActivityRequirementInsertRequest> requirements, Long userId);
}
