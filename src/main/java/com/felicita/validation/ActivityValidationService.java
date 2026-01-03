package com.felicita.validation;

import com.felicita.model.request.ActivityTerminateRequest;

public interface ActivityValidationService {
    void validateTerminateActivityRequest(ActivityTerminateRequest activityTerminateRequest);
}
