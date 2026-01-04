package com.felicita.validation.impl;

import com.felicita.model.request.ActivityInsertRequest;
import com.felicita.model.request.ActivityTerminateRequest;
import com.felicita.validation.ActivityValidationService;
import com.felicita.validation.CommonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityValidationServiceImpl implements ActivityValidationService {

    private static final Logger LOGGEr = LoggerFactory.getLogger(ActivityValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public ActivityValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateTerminateActivityRequest(ActivityTerminateRequest activityTerminateRequest) {

    }

    @Override
    public void validateActivityInsertRequest(ActivityInsertRequest activityInsertRequest) {

    }
}
