package com.felicita.controller;

import com.felicita.model.request.InsertBrowserHistoryRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.service.BrowserHistoryService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v0/browser-history")
public class BrowserHistoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserHistoryController.class);

    private final BrowserHistoryService browserHistoryService;

    @Autowired
    public BrowserHistoryController(BrowserHistoryService browserHistoryService) {
        this.browserHistoryService = browserHistoryService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<CommonResponse<InsertResponse>> addHistoryData(@RequestBody InsertBrowserHistoryRequest insertBrowserHistoryRequest) {
        LOGGER.info("{} Start execute create history data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = browserHistoryService.addHistoryData(insertBrowserHistoryRequest);
        LOGGER.info("{} End execute create history data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
