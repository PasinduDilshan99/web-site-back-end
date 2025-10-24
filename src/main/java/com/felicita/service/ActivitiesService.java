package com.felicita.service;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.ActivityHistoryDetailsResponse;
import com.felicita.model.response.ActivityHistoryImageResponse;
import com.felicita.model.response.ActivityReviewDetailsResponse;
import com.felicita.model.response.CommonResponse;
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
}
