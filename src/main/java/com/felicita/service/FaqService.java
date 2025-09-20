package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FaqResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FaqService {
    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqItems();

    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllVisibleFaqItems();
}
