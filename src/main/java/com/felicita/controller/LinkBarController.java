package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.service.LinkBarService;
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
@RequestMapping(path = "/api/v0/link-bar")
public class LinkBarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkBarController.class);

    private final LinkBarService linkBarService;

    @Autowired
    public LinkBarController(LinkBarService linkBarService) {
        this.linkBarService = linkBarService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<LinkBarResponse>>> getAllLinkBarData() {
        LOGGER.info("{} Start execute get all link bar data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<LinkBarResponse>> response = linkBarService.getAllLinkBarData();
        LOGGER.info("{} End execute get all link bar data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<LinkBarResponse>>> getActiveLinkBarData() {
        LOGGER.info("{} Start execute get active link bar data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<LinkBarResponse>> response = linkBarService.getActiveLinkBarData();
        LOGGER.info("{} End execute get active link bar data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
