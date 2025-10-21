package com.felicita.service;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.TourReviewDetailsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TourService {

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getAllTours();

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getActiveTours();

    ResponseEntity<CommonResponse<List<PopularTourResponseDto>>> getPopularTours();

    CommonResponse<TourResponseDto> getTourDetailsById(String tourId);

    CommonResponse<List<TourReviewDetailsResponse>> getAllTourReviewDetails();

    CommonResponse<List<TourReviewDetailsResponse>> getTourReviewDetailsById(String tourId);
}
