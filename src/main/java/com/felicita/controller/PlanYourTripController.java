package com.felicita.controller;

import com.felicita.model.dto.PlanYourTripDestinationsDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PlanYourTripDestinationResponse;
import com.felicita.service.PlanYourTripService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/plan-your-trip")
public class PlanYourTripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanYourTripController.class);

    private final PlanYourTripService planYourTripService;

    @Autowired
    public PlanYourTripController(PlanYourTripService planYourTripService) {
        this.planYourTripService = planYourTripService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getAllDestinationsForPlanYouTrip(){
        LOGGER.info("{} Start execute get all destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> response = planYourTripService.getAllDestinationsForPlanYouTrip();
        LOGGER.info("{} End execute get all destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getActiveDestinationsForPlanYouTrip(){
        LOGGER.info("{} Start execute get all active destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> response = planYourTripService.getActiveDestinationsForPlanYouTrip();
        LOGGER.info("{} End execute get all active destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/tours")
    public ResponseEntity<CommonResponse<PlanYourTripDestinationResponse>> getDestinationsForPlanYourTrip(){
        LOGGER.info("{} Start execute get destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<PlanYourTripDestinationResponse>> response = planYourTripService.getDestinationsForPlanYourTrip();
        LOGGER.info("{} End execute get destinations for plan your trip {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
