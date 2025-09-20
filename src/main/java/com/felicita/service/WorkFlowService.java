package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WorkFlowResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkFlowService {
    ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllWorkFlowSteps();

    ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllActiveWorkFlowSteps();
}
