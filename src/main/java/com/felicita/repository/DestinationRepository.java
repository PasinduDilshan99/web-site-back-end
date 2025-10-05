package com.felicita.repository;

import com.felicita.model.dto.*;
import com.felicita.model.response.*;

import java.util.List;

public interface DestinationRepository {
    List<DestinationResponseDto> getAllDestinations();

    List<DestinationCategoryResponseDto> getAllDestinationsCategories();

    List<PopularDestinationResponseDto> getPopularDestinations();

    List<TrendingDestinationResponseDto> getTrendingDestinations();

    List<DestinationsForTourMapDto> getDestinationsForTourMap();
}
