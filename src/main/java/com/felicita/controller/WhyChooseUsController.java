package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.model.response.WhyChooseUsResponse;
import com.felicita.service.WhyChooseUsService;
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
@RequestMapping(path = "/v0/api/why-choose-us")
public class WhyChooseUsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhyChooseUsController.class);

    private final WhyChooseUsService whyChooseUsService;

    @Autowired
    public WhyChooseUsController(WhyChooseUsService whyChooseUsService) {
        this.whyChooseUsService = whyChooseUsService;
    }


    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllWhyChooseUsItems(){
        LOGGER.info("{} Start execute get all why choose us data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> response = whyChooseUsService.getAllWhyChooseUsItems();
        LOGGER.info("{} End execute get all why choose us data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllVisibleWhyChooseUsItems(){
        LOGGER.info("{} Start execute get all visible why choose us data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> response = whyChooseUsService.getAllVisibleWhyChooseUsItems();
        LOGGER.info("{} End execute get all visible why choose us data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
