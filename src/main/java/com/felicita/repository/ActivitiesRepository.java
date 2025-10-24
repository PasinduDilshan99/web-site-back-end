package com.felicita.repository;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityHistoryDetailsResponse;
import com.felicita.model.response.ActivityHistoryImageResponse;
import com.felicita.model.response.ActivityReviewDetailsResponse;

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
}
