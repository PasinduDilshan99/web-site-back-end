package com.felicita.repository;

import com.felicita.model.response.UserLevelWithBenefitsResponse;
import com.felicita.model.response.UserLevelsResponse;

import java.util.List;

public interface UserLevelRepository {
    List<UserLevelWithBenefitsResponse> getAllUserLevelWithBenefits();

    List<UserLevelsResponse> getAllUserLevel();
}
