package com.felicita.service;

import com.felicita.model.response.AccommodationResponse;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccommodationService {
    ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllAccommodations();

    ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllActiveAccommodations();
}
