package com.felicita.repository;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityReviewDetailsResponse;

import java.util.List;

public interface ActivitiesRepository {
    List<ActivityResponseDto> getAllActivities();

    List<ActivityCategoryResponseDto> getAllActivityCategories();

    List<ActivityReviewDetailsResponse> getActivityReviewDetailsById(String activityId);

    List<ActivityReviewDetailsResponse> getAllActivityReviewDetails();

    ActivityResponseDto getActivityById(String activityId);
}
