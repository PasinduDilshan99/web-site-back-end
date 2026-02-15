package com.felicita.validation;

import com.felicita.model.request.BookingRequest;
import com.felicita.model.request.TourBookingInquiryRequest;

public interface BookingValidationService {
    void validateBookingRequest(BookingRequest bookingRequest);

    void validateTourBookingInquiryRequest(TourBookingInquiryRequest tourBookingInquiryRequest);
}
