package com.felicita.repository;

import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityResponse;

import java.util.List;

public interface ActivitiesRepository {
    List<ActivityResponse> getAllActivities();

    List<ActivityCategoryResponse> getActivityCategories();
}
