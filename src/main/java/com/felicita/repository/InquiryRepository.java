package com.felicita.repository;

import com.felicita.model.request.CreateInquiryRequest;

public interface InquiryRepository {
    void createInquiry(CreateInquiryRequest createInquiryRequest, Long userId);
}
