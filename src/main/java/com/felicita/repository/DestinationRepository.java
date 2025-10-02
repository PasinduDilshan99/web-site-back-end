package com.felicita.repository;

import com.felicita.model.dto.DestinationCategoryResponseDto;
import com.felicita.model.dto.DestinationResponseDto;
import com.felicita.model.dto.PopularDestinationResponseDto;
import com.felicita.model.dto.TrendingDestinationResponseDto;
import com.felicita.model.response.*;

import java.util.List;

public interface DestinationRepository {
    List<DestinationResponseDto> getAllDestinations();

    List<DestinationCategoryResponseDto> getAllDestinationsCategories();

    List<PopularDestinationResponseDto> getPopularDestinations();

    List<TrendingDestinationResponseDto> getTrendingDestinations();
}
