package com.felicita.service;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.request.ActivityDataRequest;
import com.felicita.model.request.ActivityInsertRequest;
import com.felicita.model.request.ActivityTerminateRequest;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActivitiesService {
    ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getAllActivities();

    ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getActiveActivities();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getAllActivityCategories();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getActiveActivityCategories();

    CommonResponse<List<ActivityReviewDetailsResponse>> getAllActivityReviewDetails();

    CommonResponse<List<ActivityReviewDetailsResponse>> getActivityReviewDetailsById(String activityId);

    CommonResponse<ActivityResponseDto> getActivityById(String activityId);

    CommonResponse<List<ActivityHistoryDetailsResponse>> getAllActivityHistoryDetails();

    CommonResponse<List<ActivityHistoryDetailsResponse>> getActivityHistoryDetailsById(String activityId);

    CommonResponse<List<ActivityHistoryImageResponse>> getAllActivityHistoryImages();

    CommonResponse<List<ActivityHistoryImageResponse>> getActivityHistoryImagesById(String activityId);

    CommonResponse<ActivityWithParamsResponse> getActivitiesWithParams(ActivityDataRequest activityDataRequest);

    CommonResponse<List<ActivityForTerminateResponse>> getActivitiesForTerminate();

    CommonResponse<TerminateResponse> terminateActivity(ActivityTerminateRequest activityTerminateRequest);

    CommonResponse<InsertResponse> insertActivity(ActivityInsertRequest activityInsertRequest);
}
