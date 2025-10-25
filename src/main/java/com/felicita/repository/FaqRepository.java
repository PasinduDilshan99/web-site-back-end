package com.felicita.repository;

import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.FaqOptionDetailsResponse;
import com.felicita.model.response.FaqResponse;

import java.util.List;

public interface FaqRepository {
    List<FaqResponse> getAllFaqItems();

    void updateFaqViewCount(FaqResponse faqResponse);

    FaqResponse getFaqItemById(long faqId);

    List<FaqOptionDetailsResponse> getFaqOptions();

    void insertFaqRequest(InsertFaqRequest request);
}
