package com.felicita.repository;

import com.felicita.model.request.InsertBrowserHistoryRequest;

public interface BrowserHistoryRepository {
    void addHistoryData(InsertBrowserHistoryRequest insertBrowserHistoryRequest, Long userId);
}
