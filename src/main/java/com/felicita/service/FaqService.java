package com.felicita.service;

import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.UpdateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FaqService {
    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqItems();

    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllVisibleFaqItems();

    ResponseEntity<CommonResponse<UpdateResponse>> updateFaqViewCount(FaqViewCountUpdateRequest faqViewCountUpdateRequest);
}
