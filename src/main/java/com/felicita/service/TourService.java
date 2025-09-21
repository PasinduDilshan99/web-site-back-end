package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.TourResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TourService {
    ResponseEntity<CommonResponse<List<TourResponse>>> getAllTours();

    ResponseEntity<CommonResponse<List<TourResponse>>> getAllActiveTours();
}
