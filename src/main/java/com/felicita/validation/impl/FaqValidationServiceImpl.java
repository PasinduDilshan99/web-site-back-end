package com.felicita.validation.impl;

import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.ValidationFailedResponse;
import com.felicita.model.response.ValidationResultResponse;
import com.felicita.validation.CommonValidationService;
import com.felicita.validation.FaqValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqValidationServiceImpl implements FaqValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public FaqValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateInsertFaqRequest(InsertFaqRequest insertFaqRequest) {

        LOGGER.info(insertFaqRequest.toString());
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse name = commonValidationService.validateNotNullOrEmpty("name", insertFaqRequest.getName());
        if (!name.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(name.getField()).value(name.getMessage()).build());
        }
        ValidationResultResponse email = commonValidationService.validateEmailFormat("email", insertFaqRequest.getEmail());
        if (!email.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(email.getField()).value(email.getMessage()).build());
        }
        ValidationResultResponse category = commonValidationService.validateNotNullOrEmpty("category", insertFaqRequest.getCategory());
        if (!category.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(category.getField()).value(category.getMessage()).build());
        }
        ValidationResultResponse subject = commonValidationService.validateNotNullOrEmpty("subject", insertFaqRequest.getSubject());
        if (!subject.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(subject.getField()).value(subject.getMessage()).build());
        }
        ValidationResultResponse message = commonValidationService.validateNotNullOrEmpty("message", insertFaqRequest.getMessage());
        if (!message.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(message.getField()).value(message.getMessage()).build());
        }
        ValidationResultResponse ipAddress = commonValidationService.validateIPAddress("ipAddress", insertFaqRequest.getIpAddress());
        if (!ipAddress.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(ipAddress.getField()).value(ipAddress.getMessage()).build());
        }
        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : insertFaqRequest", validationFailedResponses);
        }

    }
}
