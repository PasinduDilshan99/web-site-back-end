package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.DestinationCategoryResponse;
import com.felicita.model.response.DestinationResponse;
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
}
