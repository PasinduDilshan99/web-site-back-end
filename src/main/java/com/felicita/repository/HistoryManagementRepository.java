package com.felicita.repository;

import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.BrowserHistoryResponse;

import java.util.List;

public interface HistoryManagementRepository {
    void insertHistoryData(InsertHistoryData insertHistoryData,Long userId);

    List<BrowserHistoryResponse> getHistoryData(Long userId);
}
