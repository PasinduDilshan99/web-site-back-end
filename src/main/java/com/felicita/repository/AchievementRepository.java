package com.felicita.repository;

import com.felicita.model.response.AchievementResponse;

import java.util.List;

public interface AchievementRepository {
    List<AchievementResponse> getAchievementDetails();
}
