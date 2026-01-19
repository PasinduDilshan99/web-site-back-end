package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WhyChooseUsResponse;
import com.felicita.service.WhyChooseUsService;
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
@RequestMapping(path = "/api/v0/why-choose-us")
public class WhyChooseUsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhyChooseUsController.class);

    private final WhyChooseUsService whyChooseUsService;

    @Autowired
    public WhyChooseUsController(WhyChooseUsService whyChooseUsService) {
        this.whyChooseUsService = whyChooseUsService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllWhyChooseUsData(){
        LOGGER.info("{} Start execute get all why choose us data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<WhyChooseUsResponse>> response = whyChooseUsService.getAllWhyChooseUsData();
        LOGGER.info("{} End execute get all why choose us data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getActiveWhyChooseUsData(){
        LOGGER.info("{} Start execute get active why choose us data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<WhyChooseUsResponse>> response = whyChooseUsService.getActiveWhyChooseUsData();
        LOGGER.info("{} End execute get active why choose us data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
