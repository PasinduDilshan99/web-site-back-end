package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserLevelWithBenefitsResponse;
import com.felicita.model.response.UserLevelsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserLevelService {
    ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserLevelWithBenefits();

    ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllUserLevel();

    ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllActiveUserLevel();

    ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserActiveLevelWithBenefits();
}
