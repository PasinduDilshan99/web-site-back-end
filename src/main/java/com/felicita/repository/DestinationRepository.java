package com.felicita.repository;

import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.model.response.DestinationResponse;

import java.util.List;

public interface DestinationRepository {
    List<DestinationResponse> getAllDestinations();

    List<DestinationCategoryResponse> getAllActiveDestinationsCategory();

    List<DestinationResponse> getAllDestinationsByCategoryId(String categoryId);

    DestinationResponse getDestinationDetailsById(String destinationId);

    List<DestinationCategoryResponse> getAllDestinationsCategory();

    List<DestinationResponse> getAllActiveDestinations();
}
