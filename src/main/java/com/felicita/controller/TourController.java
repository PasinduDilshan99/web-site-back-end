package com.felicita.controller;

import com.felicita.model.dto.PopularTourResponseDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.request.TourDataRequest;
import com.felicita.model.response.*;
import com.felicita.service.TourService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/tour")
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<TourResponseDto>>> getAllTours() {
        LOGGER.info("{} Start execute get all tours {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TourResponseDto>>> response = tourService.getAllTours();
        LOGGER.info("{} End execute get all tours {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<TourResponseDto>>> getActiveTours() {
        LOGGER.info("{} Start execute get all active tours {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TourResponseDto>>> response = tourService.getActiveTours();
        LOGGER.info("{} End execute get all active tours {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/tours")
    public ResponseEntity<CommonResponse<ToursDetailsWithParamResponse>> getToursToShowWithParam(@RequestBody TourDataRequest tourDataRequest) {
        LOGGER.info("{} Start execute get active tours for params {}", Constant.DOTS, Constant.DOTS);
        LOGGER.info("Request body: {}", tourDataRequest);
        CommonResponse<ToursDetailsWithParamResponse> response = tourService.getToursToShowWithParam(tourDataRequest);
        LOGGER.info("{} End execute get active tours for params {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/popular")
    public ResponseEntity<CommonResponse<List<PopularTourResponseDto>>> getPopularTours() {
        LOGGER.info("{} Start execute get popular tours {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PopularTourResponseDto>>> response = tourService.getPopularTours();
        LOGGER.info("{} End execute get popular tours {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/{tourId}")
    public ResponseEntity<CommonResponse<TourResponseDto>> getTourDetailsById(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get tour details by id  {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<TourResponseDto> response = tourService.getTourDetailsById(tourId);
        LOGGER.info("{} End execute get package details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews")
    public ResponseEntity<CommonResponse<List<TourReviewDetailsResponse>>> getAllTourReviewDetails() {
        LOGGER.info("{} Start execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourReviewDetailsResponse>> response = tourService.getAllTourReviewDetails();
        LOGGER.info("{} End execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews/{tourId}")
    public ResponseEntity<CommonResponse<List<TourReviewDetailsResponse>>> getTourReviewDetailsById(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourReviewDetailsResponse>> response = tourService.getTourReviewDetailsById(tourId);
        LOGGER.info("{} End execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history")
    public ResponseEntity<CommonResponse<List<TourHistoryResponse>>> getAllTourHistoryDetails() {
        LOGGER.info("{} Start execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHistoryResponse>> response = tourService.getAllTourHistoryDetails();
        LOGGER.info("{} End execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history/{tourId}")
    public ResponseEntity<CommonResponse<List<TourHistoryResponse>>> getTourHistoryDetailsById(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHistoryResponse>> response = tourService.getTourHistoryDetailsById(tourId);
        LOGGER.info("{} End execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour-map/{tourId}")
    public ResponseEntity<CommonResponse<List<TourDestinationsForMapResponse>>> getTourDestinationsForMap(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get tour details by id  {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourDestinationsForMapResponse>> response = tourService.getTourDestinationsForMap(tourId);
        LOGGER.info("{} End execute get package details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images")
    public ResponseEntity<CommonResponse<List<TourHistoryImageResponse>>> getAllTourHistoryImages() {
        LOGGER.info("{} Start execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHistoryImageResponse>> response = tourService.getAllTourHistoryImages();
        LOGGER.info("{} End execute get all tour review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images/{tourId}")
    public ResponseEntity<CommonResponse<List<TourHistoryImageResponse>>> getTourHistoryImagesById(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourHistoryImageResponse>> response = tourService.getTourHistoryImagesById(tourId);
        LOGGER.info("{} End execute get all tour review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour-details/{tourId}")
    public ResponseEntity<CommonResponse<List<TourDetailsWithDayToDayResponse>>> getTourDetailsDayByDay(@PathVariable Long tourId) {
        LOGGER.info("{} Start execute get tour details day by day with given id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<TourDetailsWithDayToDayResponse>> response = tourService.getTourDetailsDayByDay(tourId);
        LOGGER.info("{} End execute get all tour details day by day with given id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour-extra-details/{tourId}")
    public ResponseEntity<CommonResponse<TourExtrasResponse>> getTourExtraDetailsDayByDay(@PathVariable Long tourId) {
        LOGGER.info("{} Start execute get tour extra details day by day with given id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<TourExtrasResponse> response = tourService.getTourExtraDetailsDayByDay(tourId);
        LOGGER.info("{} End execute get all tour extra details day by day with given id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour-schedules/{tourId}")
    public ResponseEntity<CommonResponse<TourSchedulesResponse>> getTourSchedules(@PathVariable Long tourId) {
        LOGGER.info("{} Start execute get tour schedules with given id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<TourSchedulesResponse> response = tourService.getTourSchedules(tourId);
        LOGGER.info("{} End execute get all tour schedules with given id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
