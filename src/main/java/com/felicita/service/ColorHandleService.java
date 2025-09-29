package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PageColorsResponse;
import org.springframework.http.ResponseEntity;

public interface ColorHandleService {
    ResponseEntity<CommonResponse<PageColorsResponse>> getHomePageColors(String pageName);
}
