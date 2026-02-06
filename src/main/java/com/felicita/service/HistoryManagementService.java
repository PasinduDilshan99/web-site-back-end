package com.felicita.service;

import com.felicita.model.request.BrowsingHistoryRequest;
import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.BrowserHistoryResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;

import java.util.List;

public interface HistoryManagementService {
    CommonResponse<InsertResponse> insertHistoryData(InsertHistoryData insertHistoryData);

    CommonResponse<BrowserHistoryResponse> getHistoryData(BrowsingHistoryRequest browsingHistoryRequest);
}
