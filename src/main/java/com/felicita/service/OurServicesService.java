package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OurServicesService {
    ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllOurServices();

    ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllVisibleOurServices();
}
