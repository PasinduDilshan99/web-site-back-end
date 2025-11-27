package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.request.InsertHistoryData;
import com.felicita.model.response.BrowserHistoryResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.HistoryManagementRepository;
import com.felicita.service.CommonService;
import com.felicita.service.HistoryManagementService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.HistoryManagementValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class HistoryManagementServiceImpl implements HistoryManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementServiceImpl.class);

    private final HistoryManagementRepository historyManagementRepository;
    private final HistoryManagementValidationService historyManagementValidationService;
    private final CommonService commonService;

    @Autowired
    public HistoryManagementServiceImpl(HistoryManagementRepository historyManagementRepository,
                                        HistoryManagementValidationService historyManagementValidationService,
                                        CommonService commonService) {
        this.historyManagementRepository = historyManagementRepository;
        this.historyManagementValidationService = historyManagementValidationService;
        this.commonService = commonService;
    }

    @Override
    public CommonResponse<InsertResponse> insertHistoryData(InsertHistoryData insertHistoryData) {
        try {
            historyManagementValidationService.validateInsertHistoryData(insertHistoryData);
            Long userId = commonService.getUserIdBySecurityContext();
            historyManagementRepository.insertHistoryData(insertHistoryData, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new InsertResponse("Successfully insert history request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert history request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());

        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<List<BrowserHistoryResponse>> getHistoryData() {
        LOGGER.info("Start fetching browser history data from repository");

        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<BrowserHistoryResponse> browserHistoryResponses =
                    historyManagementRepository.getHistoryData(userId);

            if (browserHistoryResponses.isEmpty()) {
                LOGGER.warn("No browser history data found in database");
                throw new DataNotFoundErrorExceptionHandler("No browser history data found");
            }

            List<BrowserHistoryResponse> browserHistoryResponseList = browserHistoryResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (browserHistoryResponseList.isEmpty()) {
                LOGGER.warn("No active history data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active history data found");
            }

            LOGGER.info("Fetched {} active history data successfully", browserHistoryResponseList.size());

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            browserHistoryResponseList,
                            Instant.now()
                    );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active history data: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active history data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to active history data from database");
        } finally {
            LOGGER.info("End fetching active history data from repository");
        }
    }
}
