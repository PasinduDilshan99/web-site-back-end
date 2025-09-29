package com.felicita.controller;

import com.felicita.model.response.*;
import com.felicita.service.DestinationService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinations() {
        LOGGER.info("{} Start execute get all destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponse>>> response = destinationService.getAllDestinations();
        LOGGER.info("{} End execute get all destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-destinations")
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllActiveDestinations() {
        LOGGER.info("{} Start execute get all active destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponse>>> response = destinationService.getAllActiveDestinations();
        LOGGER.info("{} End execute get all active destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-categories")
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllActiveDestinationsCategory() {
        LOGGER.info("{} Start execute get all active destinations category {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> response = destinationService.getAllActiveDestinationsCategory();
        LOGGER.info("{} End execute get all active destinations category {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> getAllDestinationsCategory() {
        LOGGER.info("{} Start execute get all destinations category {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationCategoryResponse>>> response = destinationService.getAllDestinationsCategory();
        LOGGER.info("{} End execute get all destinations category {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllDestinationsByCategoryId(@PathVariable String categoryId) {
        LOGGER.info("{} Start execute get all destinations by category id {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponse>>> response = destinationService.getAllDestinationsByCategoryId(categoryId);
        LOGGER.info("{} End execute get all destinations by category id {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/{destinationId}")
    public ResponseEntity<CommonResponse<DestinationResponse>> getDestinationDetailsById(@PathVariable String destinationId) {
        LOGGER.info("{} Start execute get all destination by id {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<DestinationResponse>> response = destinationService.getDestinationDetailsById(destinationId);
        LOGGER.info("{} End execute get all destination category id {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/popular-destinations")
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllPopularDestinations() {
        LOGGER.info("{} Start execute get all popular destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponse>>> response = destinationService.getAllPopularDestinations();
        LOGGER.info("{} End execute get all popular destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/trending-destinations")
    public ResponseEntity<CommonResponse<List<TrendingDestinationResponse>>> getAllTrendingDestinations() {
        LOGGER.info("{} Start execute get all trending destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TrendingDestinationResponse>>> response = destinationService.getAllTrendingDestinations();
        LOGGER.info("{} End execute get all trending destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/new-destinations")
    public ResponseEntity<CommonResponse<List<DestinationResponse>>> getAllNewDestinations() {
        LOGGER.info("{} Start execute get all new destinations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<DestinationResponse>>> response = destinationService.getAllNewDestinations();
        LOGGER.info("{} End execute get all new destinations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-locations")
    public ResponseEntity<CommonResponse<List<ActiveDestinationLocations>>> getActiveDestinations() {
        LOGGER.info("{} Start execute get all active destinations locations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActiveDestinationLocations>>> response = destinationService.getActiveDestinationsLocations();
        LOGGER.info("{} End execute get all active destinations locations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-locations-categories")
    public ResponseEntity<CommonResponse<List<ActiveDestinationsLocationsCategories>>> getActiveDestinationsCategories() {
        LOGGER.info("{} Start execute get all active destinations locations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActiveDestinationsLocationsCategories>>> response = destinationService.getActiveDestinationsCategories();
        LOGGER.info("{} End execute get all active destinations locations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }


}
