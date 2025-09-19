package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WhyChooseUsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WhyChooseUsService {
    ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllWhyChooseUsItems();

    ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllVisibleWhyChooseUsItems();
}
