package com.felicita.service;

import com.felicita.model.response.AchievementResponse;
import com.felicita.model.response.CommonResponse;

import java.util.List;

public interface AchievementService {
    CommonResponse<List<AchievementResponse>> getAchievementDetails();
}
