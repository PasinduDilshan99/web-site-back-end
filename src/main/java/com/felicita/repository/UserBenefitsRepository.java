package com.felicita.repository;

import com.felicita.model.response.UserBenefitResponse;

import java.util.List;

public interface UserBenefitsRepository {
    List<UserBenefitResponse> getAllUserBenefits();
}
