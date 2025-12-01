package com.felicita.repository;

import com.felicita.model.response.UserBenefitResponse;
import com.felicita.model.response.UserLevelDetailsResponse;

import java.util.List;

public interface UserBenefitsRepository {
    List<UserBenefitResponse> getAllUserBenefits();

    UserLevelDetailsResponse getUserBenifitsDetailsForUserId(Long userId);
}
