package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserBenefitResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserBenefitsService {
    ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllUserBenefits();

    ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllActiveUserBenefits();
}
