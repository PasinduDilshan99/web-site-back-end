package com.felicita.service.impl;

import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.request.InsertBrowserHistoryRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.repository.BrowserHistoryRepository;
import com.felicita.service.BrowserHistoryService;
import com.felicita.service.CommonService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.BrowserHistoryValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BrowserHistoryServiceImpl implements BrowserHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserHistoryServiceImpl.class);

    private final BrowserHistoryRepository browserHistoryRepository;
    private final BrowserHistoryValidationService browserHistoryValidationService;
    private final CommonService commonService;

    @Autowired
    public BrowserHistoryServiceImpl(BrowserHistoryRepository browserHistoryRepository, BrowserHistoryValidationService browserHistoryValidationService, CommonService commonService) {
        this.browserHistoryRepository = browserHistoryRepository;
        this.browserHistoryValidationService = browserHistoryValidationService;
        this.commonService = commonService;
    }

    @Override
    public CommonResponse<InsertResponse> addHistoryData(InsertBrowserHistoryRequest insertBrowserHistoryRequest) {
        try {
            browserHistoryValidationService.validateInsertBrowserHistoryRequest(insertBrowserHistoryRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            browserHistoryRepository.addHistoryData(insertBrowserHistoryRequest, userId);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                    CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                    new InsertResponse("Successfully insert browser history request"),
                    Instant.now());

        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert browser history request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }
}
