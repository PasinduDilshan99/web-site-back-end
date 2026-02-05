package com.felicita.controller;

import com.felicita.model.request.BrowsingHistoryRequest;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.BrowserHistoryResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.service.HistoryManagementService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/history-management")
public class HistoryManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementController.class);

    private final HistoryManagementService historyManagementService;

    @Autowired
    public HistoryManagementController(HistoryManagementService historyManagementService) {
        this.historyManagementService = historyManagementService;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<CommonResponse<InsertResponse>> insertHistoryData(@RequestBody InsertHistoryData insertHistoryData){
        LOGGER.info("{} Start execute insert history management request data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = historyManagementService.insertHistoryData(insertHistoryData);
        LOGGER.info("{} End execute insert history management request data {}", Constant.DOTS, Constant.DOTS);
        return ResponseEntity.ok(response);
    }


    @PostMapping(path = "/history-data")
    public ResponseEntity<CommonResponse<BrowserHistoryResponse>> getHistoryData(@RequestBody BrowsingHistoryRequest browsingHistoryRequest){
        LOGGER.info("{} Start execute get history data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<BrowserHistoryResponse> response = historyManagementService.getHistoryData(browsingHistoryRequest);
        LOGGER.info("{} End execute get history data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
