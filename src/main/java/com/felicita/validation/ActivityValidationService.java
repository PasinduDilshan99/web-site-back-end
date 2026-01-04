package com.felicita.validation;

import com.felicita.model.request.ActivityInsertRequest;
import com.felicita.model.request.ActivityTerminateRequest;

public interface ActivityValidationService {
    void validateTerminateActivityRequest(ActivityTerminateRequest activityTerminateRequest);

    void validateActivityInsertRequest(ActivityInsertRequest activityInsertRequest);
}
