package com.felicita.repository;

import com.felicita.model.response.*;

import java.util.List;

public interface DestinationRepository {
    List<DestinationResponse> getAllDestinations();

    List<DestinationCategoryResponse> getAllActiveDestinationsCategory();

    List<DestinationResponse> getAllDestinationsByCategoryId(String categoryId);

    DestinationResponse getDestinationDetailsById(String destinationId);

    List<DestinationCategoryResponse> getAllDestinationsCategory();

    List<DestinationResponse> getAllActiveDestinations();

    List<TrendingDestinationResponse> getAllTrendingDestinations();

    List<DestinationResponse> getDestinationByIds(List<Integer> destinationIds);

    List<ActiveDestinationLocations> getActiveDestinationsLocations();

    List<ActiveDestinationsLocationsCategories> getActiveDestinationsCategories();
}
