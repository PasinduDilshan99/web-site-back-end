package com.felicita.validation.impl;

import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.ValidationFailedResponse;
import com.felicita.model.response.ValidationResultResponse;
import com.felicita.validation.CommonValidationService;
import com.felicita.validation.HistoryManagementValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryManagementValidationServiceImpl implements HistoryManagementValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public HistoryManagementValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }


    @Override
    public void validateInsertHistoryData(InsertHistoryData insertHistoryData) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse type = commonValidationService.validateNotNullOrEmpty("type", insertHistoryData.getType());
        if (!type.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(type.getField()).value(type.getMessage()).build());
        }
        ValidationResultResponse dataId = commonValidationService.validateOnlyNumbers("data id", insertHistoryData.getDataId().toString());
        if (!dataId.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(dataId.getField()).value(dataId.getMessage()).build());
        }

        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : insertHistoryData", validationFailedResponses);
        }
    }
}
