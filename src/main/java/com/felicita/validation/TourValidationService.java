package com.felicita.validation;

import com.felicita.model.request.TourInsertRequest;
import com.felicita.model.response.TourTerminateRequest;

public interface TourValidationService {
    void validateTerminateTourRequest(TourTerminateRequest tourTerminateRequest);

    void validateTourInsertRequest(TourInsertRequest tourInsertRequest);
}
