package com.felicita.service;

import com.felicita.model.dto.*;
import com.felicita.model.request.DestinationDataRequest;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DestinationService {
    ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllDestinations();

    ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllActiveDestinations();

    ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getAllDestinationsCategories();

    ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getActiveDestinationsCategories();

    ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getPopularDestinations();

    ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getNewDestinations();

    ResponseEntity<CommonResponse<List<TrendingDestinationResponseDto>>> getTrendingDestinations();

    ResponseEntity<CommonResponse<List<DestinationsForTourMapDto>>> getDestinationsForTourMap();

    CommonResponse<List<DestinationResponseDto>> getDestinationDetailsByTourId(String tourId);

    CommonResponse<List<DestinationReviewDetailsResponse>> getAllDestinationsReviewDetails();

    CommonResponse<List<DestinationReviewDetailsResponse>> getDestinationReviewDetailsById(String destinationId);

    CommonResponse<DestinationResponseDto> getDestinationDetailsById(String destinationId);

    CommonResponse<List<DestinationHistoryDetailsResponse>> getAllDestinationHistoryDetails();

    CommonResponse<List<DestinationHistoryDetailsResponse>> getDestinationHistoryDetailsById(String destinationId);

    CommonResponse<List<DestinationHistoryImageResponse>> getAllDestinationHistoryImages();

    CommonResponse<List<DestinationHistoryImageResponse>> getDestinationHistoryImagesById(String destinationId);

    CommonResponse<DestinationsWithParamsResponse> getDestinationWithParams(DestinationDataRequest destinationDataRequest);
}
