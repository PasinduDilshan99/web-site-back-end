package com.felicita.service;

import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.*;
import java.util.List;

public interface FaqService {

    CommonResponse<List<FaqResponse>> getAllFaqData();

    CommonResponse<List<FaqResponse>> getActiveFaqData();

    CommonResponse<UpdateResponse> updateFaqViewCount(FaqViewCountUpdateRequest faqViewCountUpdateRequest);

    CommonResponse<List<FaqOptionDetailsResponse>> getFaqOptions();

    CommonResponse<InsertResponse> insertFaqRequest(InsertFaqRequest insertFaqRequest);

}
