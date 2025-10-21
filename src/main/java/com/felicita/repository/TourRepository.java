package com.felicita.repository;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.TourReviewDetailsResponse;

import java.util.List;

public interface TourRepository {
    List<TourResponseDto> getAllTours();

    List<PopularTourResponseDto> getPopularTours();

    TourResponseDto getTourDetailsById(String tourId);

    List<TourReviewDetailsResponse> getAllTourReviewDetails();

    List<TourReviewDetailsResponse> getTourReviewDetailsById(String tourId);
}
