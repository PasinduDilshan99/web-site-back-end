package com.felicita.repository;

import com.felicita.model.response.WorkFlowResponse;

import java.util.List;

public interface WorkFlowRepository {
    List<WorkFlowResponse> getAllWorkFlowSteps();
}
