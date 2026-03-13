package com.felicita.validation;

import com.felicita.model.request.InsertBrowserHistoryRequest;

public interface BrowserHistoryValidationService {
    void validateInsertBrowserHistoryRequest(InsertBrowserHistoryRequest insertBrowserHistoryRequest);
}
