package com.felicita.controller;

import com.felicita.model.dto.DestinationResponseDto;
import com.felicita.model.dto.TourResponseDto;
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

}
