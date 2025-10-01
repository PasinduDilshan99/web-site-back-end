package com.felicita.service;

import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TourService {

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getAllTours();

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getActiveTours();
}
