package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.WorkFlowResponse;
import com.felicita.service.WorkFlowService;
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
@RequestMapping(path = "/v0/api/work-flow")
public class WorkFlowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowController.class);

    private final WorkFlowService workFlowService;

    @Autowired
    public WorkFlowController(WorkFlowService workFlowService) {
        this.workFlowService = workFlowService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllWorkFlowSteps(){
        LOGGER.info("{} Start execute get all work flow data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<WorkFlowResponse>>> response = workFlowService.getAllWorkFlowSteps();
        LOGGER.info("{} End execute get all work flow data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllActiveWorkFlowSteps(){
        LOGGER.info("{} Start execute get all visible work flow data {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<WorkFlowResponse>>> response = workFlowService.getAllActiveWorkFlowSteps();
        LOGGER.info("{} End execute get all visible work flow data {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
