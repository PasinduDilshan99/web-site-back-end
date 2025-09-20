package com.felicita.controller;

import com.felicita.model.response.AccommodationResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.AccommodationService;
import com.felicita.service.PartnerService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/accommodation")
public class AccommodationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationController.class);

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllAccommodations(){
        LOGGER.info("{} Start execute get all accommodations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<AccommodationResponse>>> response = accommodationService.getAllAccommodations();
        LOGGER.info("{} End execute get all accommodations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/available")
    public ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllActiveAccommodations(){
        LOGGER.info("{} Start execute get all visible accommodations {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<AccommodationResponse>>> response = accommodationService.getAllActiveAccommodations();
        LOGGER.info("{} End execute get all visible accommodations {}", Constant.DOTS, Constant.DOTS);
        return response;
    }
}
