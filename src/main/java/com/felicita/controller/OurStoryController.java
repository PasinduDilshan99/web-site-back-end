package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurStoryAndValuesResponse;
import com.felicita.service.OurStoryService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v0/our-story")
public class OurStoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurStoryController.class);

    private final OurStoryService ourStoryService;

    @Autowired
    public OurStoryController(OurStoryService ourStoryService) {
        this.ourStoryService = ourStoryService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<OurStoryAndValuesResponse>> getOurStoryAndKeyValueDetails(){
        LOGGER.info("{} Start execute get our story details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<OurStoryAndValuesResponse> response = ourStoryService.getOurStoryAndKeyValueDetails();
        LOGGER.info("{} End execute get our story details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
