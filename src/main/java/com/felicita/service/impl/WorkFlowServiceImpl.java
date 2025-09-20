package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.enums.WorkFlowDataStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.WorkFlowResponse;
import com.felicita.repository.WorkFlowRepository;
import com.felicita.service.WorkFlowService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowServiceImpl.class);

    private final WorkFlowRepository workFlowRepository;

    @Autowired
    public WorkFlowServiceImpl(WorkFlowRepository workFlowRepository) {
        this.workFlowRepository = workFlowRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllWorkFlowSteps() {
        LOGGER.info("Start fetching all work flow steps from repository");
        try {
            List<WorkFlowResponse> workFlowResponses = workFlowRepository.getAllWorkFlowSteps();

            if (workFlowResponses.isEmpty()) {
                LOGGER.warn("No work flow steps found in database");
                throw new DataNotFoundErrorExceptionHandler("No work flow steps found");
            }

            LOGGER.info("Fetched {} work flow steps successfully", workFlowResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            workFlowResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching work flow steps: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching work flow steps: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch work flow steps from database");
        } finally {
            LOGGER.info("End fetching all work flow steps from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<WorkFlowResponse>>> getAllActiveWorkFlowSteps() {
        LOGGER.info("Start fetching all visible work flow steps from repository");

        try {
            List<WorkFlowResponse> workFlowResponses = workFlowRepository.getAllWorkFlowSteps();

            if (workFlowResponses.isEmpty()) {
                LOGGER.warn("No work flow steps found in database");
                throw new DataNotFoundErrorExceptionHandler("No work flow steps found");
            }

            List<WorkFlowResponse> workFlowResponseList = workFlowResponses.stream()
                    .filter(item -> WorkFlowDataStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (workFlowResponseList.isEmpty()) {
                LOGGER.warn("No visible work flow steps found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible work flow steps found");
            }

            LOGGER.info("Fetched {} visible work flow steps successfully", workFlowResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            workFlowResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible work flow steps: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible work flow steps: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch work flow steps from database");
        } finally {
            LOGGER.info("End fetching all visible work flow steps from repository");
        }
    }
}
