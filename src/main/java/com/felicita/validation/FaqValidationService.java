package com.felicita.validation;

import com.felicita.model.request.InsertFaqRequest;

public interface FaqValidationService {
    void validateInsertFaqRequest(InsertFaqRequest insertFaqRequest);
}
