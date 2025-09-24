package com.felicita.repository;

import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.PopularTourResponse;

import java.util.List;

public interface TourRepository {
    List<TourResponseDto> getAllTours();

    List<PopularTourResponse> getPopularTours();
}
