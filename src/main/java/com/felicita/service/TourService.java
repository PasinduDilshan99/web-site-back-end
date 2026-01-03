package com.felicita.service;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TourService {

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getAllTours();

    ResponseEntity<CommonResponse<List<TourResponseDto>>> getActiveTours();

    ResponseEntity<CommonResponse<List<PopularTourResponseDto>>> getPopularTours();

    CommonResponse<TourResponseDto> getTourDetailsById(String tourId);

    CommonResponse<List<TourReviewDetailsResponse>> getAllTourReviewDetails();

    CommonResponse<List<TourReviewDetailsResponse>> getTourReviewDetailsById(String tourId);

    CommonResponse<List<TourDestinationsForMapResponse>> getTourDestinationsForMap(String tourId);

    CommonResponse<List<TourHistoryResponse>> getAllTourHistoryDetails();

    CommonResponse<List<TourHistoryResponse>> getTourHistoryDetailsById(String tourId);

    CommonResponse<List<TourHistoryImageResponse>> getAllTourHistoryImages();

    CommonResponse<List<TourHistoryImageResponse>> getTourHistoryImagesById(String tourId);

    CommonResponse<ToursDetailsWithParamResponse> getToursToShowWithParam(TourDataRequest tourDataRequest);

    CommonResponse<List<TourDetailsWithDayToDayResponse>> getTourDetailsDayByDay(Long tourId);

    CommonResponse<TourExtrasResponse> getTourExtraDetailsDayByDay(Long tourId);

    CommonResponse<TourSchedulesResponse> getTourSchedules(Long tourId);

    CommonResponse<List<TourBasicDetailsResponse>> getAllToursBasicDetails();

    CommonResponse<List<TourForTerminateResponse>> getToursForTerminate();

    CommonResponse<TerminateResponse> terminateTour(TourTerminateRequest tourTerminateRequest);
}
