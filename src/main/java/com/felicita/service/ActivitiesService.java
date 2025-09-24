package com.felicita.service;

import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityResponse;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActivitiesService {
    ResponseEntity<CommonResponse<List<ActivityResponse>>> getAllActivities();

    ResponseEntity<CommonResponse<List<ActivityResponse>>> getActiveActivities();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActivityCategories();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActiveActivityCategories();
}
