package com.felicita.validation;

import com.felicita.model.request.DestinationInsertRequest;
import com.felicita.model.request.DestinationTerminateRequest;
import com.felicita.model.request.DestinationUpdateRequest;

public interface DestinationValidationService {
    void validateDestinationInsertRequest(DestinationInsertRequest destinationInsertRequest);

    void validateTerminateDestinationRequest(DestinationTerminateRequest destinationTerminateRequest);

    void validateDestinationUpdateRequest(DestinationUpdateRequest destinationUpdateRequest);
}
