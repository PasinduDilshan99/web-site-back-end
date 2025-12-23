package com.felicita.validation;

import com.felicita.model.request.BookingRequest;

public interface BookingValidationService {
    void validateBookingRequest(BookingRequest bookingRequest);
}
