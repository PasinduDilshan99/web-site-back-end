package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FooterResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FooterService {
    ResponseEntity<CommonResponse<FooterResponse>> getAllFooterData();

    ResponseEntity<CommonResponse<FooterResponse>> getActiveFooterData();
}
