package com.felicita.service;

import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FaqService {
    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqItems();

    ResponseEntity<CommonResponse<List<FaqResponse>>> getAllVisibleFaqItems();

    ResponseEntity<CommonResponse<UpdateResponse>> updateFaqViewCount(FaqViewCountUpdateRequest faqViewCountUpdateRequest);

    CommonResponse<List<FaqOptionDetailsResponse>> getFaqOptions();

    CommonResponse<InsertResponse> insertFaqRequest(InsertFaqRequest insertFaqRequest);
}
