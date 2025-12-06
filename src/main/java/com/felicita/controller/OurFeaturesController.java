package com.felicita.controller;

import com.felicita.model.response.CancelledToursResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.service.OurFeaturesService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/our-features")
public class OurFeaturesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurFeaturesController.class);

    private final OurFeaturesService ourFeaturesService;

    @Autowired
    public OurFeaturesController(OurFeaturesService ourFeaturesService) {
        this.ourFeaturesService = ourFeaturesService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<List<FeatureResponse>>> getOurFeaturesDetails() {
        LOGGER.info("{} Start execute get our features details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<FeatureResponse>> response = ourFeaturesService.getOurFeaturesDetails();
        LOGGER.info("{} End execute get our features details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
