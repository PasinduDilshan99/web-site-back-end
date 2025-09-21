package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.TourResponse;
import com.felicita.service.PartnerService;
import com.felicita.service.TourService;
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
@RequestMapping(path = "/v0/api/tour")
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<TourResponse>>> getAllTours(){
        LOGGER.info("{} Start execute get all tours {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TourResponse>>> response = tourService.getAllTours();
        LOGGER.info("{} End execute get all tours {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<TourResponse>>> getAllActiveTours(){
        LOGGER.info("{} Start execute get all active tours {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<TourResponse>>> response = tourService.getAllActiveTours();
        LOGGER.info("{} End execute get all active tours {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
