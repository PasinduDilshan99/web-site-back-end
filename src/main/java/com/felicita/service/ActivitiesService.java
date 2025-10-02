package com.felicita.service;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActivitiesService {
    ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getAllActivities();

    ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getActiveActivities();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getAllActivityCategories();

    ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getActiveActivityCategories();
}
