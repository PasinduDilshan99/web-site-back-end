package com.felicita.controller;

import com.felicita.model.dto.*;
import com.felicita.model.response.*;
import com.felicita.service.DestinationService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/destination")
public class DestinationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationController.class);

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllDestinations() {
        LOGGER.info("{} Start execute get all destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponseDto>>> response = destinationService.getAllDestinations();
        LOGGER.info("{} End execute get all destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-destinations")
    public ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getAllActiveDestinations() {
        LOGGER.info("{} Start execute get all active destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponseDto>>> response = destinationService.getAllActiveDestinations();
        LOGGER.info("{} End execute get all active destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/all-categories")
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getAllDestinationsCategories() {
        LOGGER.info("{} Start execute get all destinations categories {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> response = destinationService.getAllDestinationsCategories();
        LOGGER.info("{} End execute get all destinations categories {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-categories")
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> getActiveDestinationsCategories() {
        LOGGER.info("{} Start execute get active destinations categories{}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationCategoryResponseDto>>> response = destinationService.getActiveDestinationsCategories();
        LOGGER.info("{} End execute get active destinations categories{}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/popular-destinations")
    public ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getPopularDestinations() {
        LOGGER.info("{} Start execute get popular destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> response = destinationService.getPopularDestinations();
        LOGGER.info("{} End execute get popular destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/new-destinations")
    public ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> getNewDestinations() {
        LOGGER.info("{} Start execute get new destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PopularDestinationResponseDto>>> response = destinationService.getNewDestinations();
        LOGGER.info("{} End execute get new destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/trending-destinations")
    public ResponseEntity<CommonResponse<List<TrendingDestinationResponseDto>>> getTrendingDestinations() {
        LOGGER.info("{} Start execute get trending destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TrendingDestinationResponseDto>>> response = destinationService.getTrendingDestinations();
        LOGGER.info("{} End execute get trending destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/tour-map")
    public ResponseEntity<CommonResponse<List<DestinationsForTourMapDto>>> getDestinationsForTourMap() {
        LOGGER.info("{} Start execute get destinations for tour map {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationsForTourMapDto>>> response = destinationService.getDestinationsForTourMap();
        LOGGER.info("{} End execute get destinations for tour map {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/tour-id/{tourId}")
    public ResponseEntity<CommonResponse<List<DestinationResponseDto>>> getDestinationDetailsByTourId(@PathVariable String tourId) {
        LOGGER.info("{} Start execute get destinations details by id  {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationResponseDto>> response = destinationService.getDestinationDetailsByTourId(tourId);
        LOGGER.info("{} End execute get destinations details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{destinationId}")
    public ResponseEntity<CommonResponse<DestinationResponseDto>> getDestinationDetailsById(@PathVariable String destinationId) {
        LOGGER.info("{} Start execute get destination details by id  {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<DestinationResponseDto> response = destinationService.getDestinationDetailsById(destinationId);
        LOGGER.info("{} End execute get destination details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews")
    public ResponseEntity<CommonResponse<List<DestinationReviewDetailsResponse>>> getAllDestinationsReviewDetails() {
        LOGGER.info("{} Start execute get all destination review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationReviewDetailsResponse>> response = destinationService.getAllDestinationsReviewDetails();
        LOGGER.info("{} End execute get all destination review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews/{destinationId}")
    public ResponseEntity<CommonResponse<List<DestinationReviewDetailsResponse>>> getDestinationReviewDetailsById(@PathVariable String destinationId) {
        LOGGER.info("{} Start execute get all destination review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationReviewDetailsResponse>> response = destinationService.getDestinationReviewDetailsById(destinationId);
        LOGGER.info("{} End execute get all destination review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history")
    public ResponseEntity<CommonResponse<List<DestinationHistoryDetailsResponse>>> getAllDestinationHistoryDetails() {
        LOGGER.info("{} Start execute get all destinations review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHistoryDetailsResponse>> response = destinationService.getAllDestinationHistoryDetails();
        LOGGER.info("{} End execute get all destinations review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history/{destinationId}")
    public ResponseEntity<CommonResponse<List<DestinationHistoryDetailsResponse>>> getDestinationHistoryDetailsById(@PathVariable String destinationId) {
        LOGGER.info("{} Start execute get all destinations review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHistoryDetailsResponse>> response = destinationService.getDestinationHistoryDetailsById(destinationId);
        LOGGER.info("{} End execute get all destinations review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images")
    public ResponseEntity<CommonResponse<List<DestinationHistoryImageResponse>>> getAllDestinationHistoryImages() {
        LOGGER.info("{} Start execute get all destination review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHistoryImageResponse>> response = destinationService.getAllDestinationHistoryImages();
        LOGGER.info("{} End execute get all destination review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images/{destinationId}")
    public ResponseEntity<CommonResponse<List<DestinationHistoryImageResponse>>> getDestinationHistoryImagesById(@PathVariable String destinationId) {
        LOGGER.info("{} Start execute get all destination review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<DestinationHistoryImageResponse>> response = destinationService.getDestinationHistoryImagesById(destinationId);
        LOGGER.info("{} End execute get all destination review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
