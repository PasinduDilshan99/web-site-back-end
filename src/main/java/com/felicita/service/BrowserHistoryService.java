package com.felicita.service;

import com.felicita.model.request.InsertBrowserHistoryRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;

public interface BrowserHistoryService {
    CommonResponse<InsertResponse> addHistoryData(InsertBrowserHistoryRequest insertBrowserHistoryRequest);
}
