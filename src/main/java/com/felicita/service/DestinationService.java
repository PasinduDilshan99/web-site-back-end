package com.felicita.service;

import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DestinationService {
    ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinations();

    ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllActiveDestinationsCategory();

    ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinationsByCategoryId(String categoryId);

    ResponseEntity<CommonResponse<DestinationResponse>> getDestinationDetailsById(String destinationId);

    ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllDestinationsCategory();

    ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllActiveDestinations();

    ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllPopularDestinations();

    ResponseEntity<CommonResponse<List<TrendingDestinationResponse>>> getAllTrendingDestinations();

    ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllNewDestinations();

    List<DestinationResponse> getDestinationByIds(List<Integer> destinationIds);

    ResponseEntity<CommonResponse<List<ActiveDestinationLocations>>> getActiveDestinationsLocations();

    ResponseEntity<CommonResponse<List<ActiveDestinationsLocationsCategories>>> getActiveDestinationsCategories();
}
