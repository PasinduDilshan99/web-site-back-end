package com.felicita.validation.impl;

import com.felicita.model.request.InsertBrowserHistoryRequest;
import com.felicita.validation.BrowserHistoryValidationService;
import com.felicita.validation.CommonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowserHistoryValidationServiceImpl implements BrowserHistoryValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserHistoryValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public BrowserHistoryValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateInsertBrowserHistoryRequest(InsertBrowserHistoryRequest insertBrowserHistoryRequest) {

    }
}
