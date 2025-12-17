package com.felicita.repository;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourDayDestinationActivityIdsDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface TourRepository {
    List<TourResponseDto> getAllTours();

    List<PopularTourResponseDto> getPopularTours();

    TourResponseDto getTourDetailsById(String tourId);

    List<TourReviewDetailsResponse> getAllTourReviewDetails();

    List<TourReviewDetailsResponse> getTourReviewDetailsById(String tourId);

    List<TourDestinationsForMapResponse> getTourDestinationsForMap(String tourId);

    List<TourHistoryResponse> getAllTourHistoryDetails();

    List<TourHistoryResponse> getTourHistoryDetailsById(String tourId);

    List<TourHistoryImageResponse> getTourHistoryImagesById(String tourId);

    List<TourHistoryImageResponse> getAllTourHistoryImages();

    ToursDetailsWithParamResponse getToursToShowWithParam(TourDataRequest tourDataRequest);

    List<TourDayDestinationActivityIdsDto> getTourDayDestinationActivityIds(Long tourId);

    List<TourDetailsWithDayToDayResponse.DestinationDetailsPerDay> getDestinationsDetailsByIds(List<Long> destinationIdList);

    List<TourDetailsWithDayToDayResponse.ActivityPerDayResponse> getActivityDetailsByIds(List<Long> activityIdList);
}
