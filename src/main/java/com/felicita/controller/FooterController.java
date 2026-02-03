package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FooterResponse;
import com.felicita.service.FooterService;
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
@RequestMapping(path = "/api/v0/footer")
public class FooterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooterController.class);

    private final FooterService footerService;

    @Autowired
    public FooterController(FooterService footerService) {
        this.footerService = footerService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<FooterResponse>> getAllFooterData(){
        LOGGER.info("{} Start execute get all footer data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<FooterResponse> response = footerService.getAllFooterData();
        LOGGER.info("{} End execute get all footer data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<FooterResponse>> getActiveFooterData(){
        LOGGER.info("{} Start execute get active footer data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<FooterResponse> response = footerService.getActiveFooterData();
        LOGGER.info("{} End execute get active footer data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
