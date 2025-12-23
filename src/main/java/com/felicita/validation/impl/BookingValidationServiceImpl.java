package com.felicita.validation.impl;

import com.felicita.model.request.BookingRequest;
import com.felicita.service.CommonService;
import com.felicita.validation.BookingValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingValidationServiceImpl implements BookingValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingValidationServiceImpl.class);

    private final CommonService commonService;

    @Autowired
    public BookingValidationServiceImpl(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public void validateBookingRequest(BookingRequest bookingRequest) {

    }
}
