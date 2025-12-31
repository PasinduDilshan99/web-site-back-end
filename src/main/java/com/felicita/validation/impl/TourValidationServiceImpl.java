package com.felicita.validation.impl;

import com.felicita.model.response.TourTerminateRequest;
import com.felicita.validation.CommonValidationService;
import com.felicita.validation.TourValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourValidationServiceImpl implements TourValidationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TourValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public TourValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateTerminateTourRequest(TourTerminateRequest tourTerminateRequest) {

    }
}
