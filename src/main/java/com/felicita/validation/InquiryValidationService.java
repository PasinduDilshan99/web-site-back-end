package com.felicita.validation;

import com.felicita.model.request.CreateInquiryRequest;

public interface InquiryValidationService {
    void validateCreateInquiryRequest(CreateInquiryRequest createInquiryRequest);
}
