package com.felicita.service;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PromotionTourResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromotionsService {
    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPromotions();

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllActivePromotions();
}
