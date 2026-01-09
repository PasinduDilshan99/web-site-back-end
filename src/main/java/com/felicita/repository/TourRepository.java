package com.felicita.repository;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourDayDestinationActivityIdsDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.request.*;
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

    List<TourExtrasResponse.TourInclusion> getTourInclusions(Long tourId);

    List<TourExtrasResponse.TourExclusion> getTourExclusions(Long tourId);

    List<TourExtrasResponse.TourCondition> getTourConditions(Long tourId);

    List<TourExtrasResponse.TourTravelTip> getTourTravelTips(Long tourId);

    List<TourSchedulesResponse.TourScheduleDetails> getTourSchedulesById(Long tourId);

    TourSchedulesResponse.TourBasicDetails getTourBasicDetails(Long tourId);

    List<TourBasicDetailsResponse> getAllToursBasicDetails();

    List<TourForTerminateResponse> getToursForTerminate();

    void terminateTour(TourTerminateRequest tourTerminateRequest, Long userId);

    Long insertTourDetails(TourInsertRequest tourInsertRequest, Long userId);

    void insertTourDestinations(Long tourId, List<TourDestinationInsertRequest> destinations, Long userId);

    void insertTourImages(Long tourId, List<TourImageInsertRequest> images, Long userId);

    void insertTourInclusions(Long tourId, List<TourInclusionInsertRequest> inclusions, Long userId);

    void insertTourExclusions(Long tourId, List<TourExclusionInsertRequest> exclusions, Long userId);

    void insertTourConditions(Long tourId, List<TourConditionInsertRequest> conditions, Long userId);

    void insertTourTravelTips(Long tourId, List<TourTravelTipInsertRequest> travelTips, Long userId);

    TourDetailsForAddPackageResponse getTourDetailsForAddPackage(Long tourId);

    List<String> getTourInclusionsNamesOnly(Long tourId);

    List<String> getTourExclusionsNamesOnly(Long tourId);

    List<String> getTourConditionsNamesOnly(Long tourId);

    List<TourDetailsForAddPackageResponse.TravelTip> getTourTravelTipsNamesOnly(Long tourId);
}
