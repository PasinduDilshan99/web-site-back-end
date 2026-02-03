package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.enums.FaqItemStatus;
import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.request.InsertFaqRequest;
import com.felicita.model.response.*;
import com.felicita.repository.FaqRepository;
import com.felicita.service.FaqService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.FaqValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqServiceImpl.class);

    private final FaqRepository faqRepository;
    private final FaqValidationService faqValidationService;

    @Autowired
    public FaqServiceImpl(FaqRepository faqRepository,
                          FaqValidationService faqValidationService) {
        this.faqRepository = faqRepository;
        this.faqValidationService = faqValidationService;
    }

    @Override
    public CommonResponse<List<FaqResponse>> getAllFaqData() {
        LOGGER.info("Start fetching all faq data from repository");
        try {
            List<FaqResponse> faqResponses = faqRepository.getAllFaqData();

            if (faqResponses.isEmpty()) {
                LOGGER.warn("No faq data found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq data found");
            }

            LOGGER.info("Fetched {} faq data successfully", faqResponses.size());
            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqResponses,
                            Instant.now());

        }catch (DataAccessErrorExceptionHandler | DataNotFoundErrorExceptionHandler e){
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching faq data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq data from database");
        } finally {
            LOGGER.info("End fetching all faq data from repository");
        }
    }

    @Override
    public CommonResponse<List<FaqResponse>> getActiveFaqData() {
        LOGGER.info("Start fetching active faq data from repository");

        try {
            List<FaqResponse> faqResponses = getAllFaqData().getData();

            List<FaqResponse> faqResponseList = faqResponses.stream()
                    .filter(item -> FaqItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getFaqStatus()))
                    .toList();

            if (faqResponseList.isEmpty()) {
                LOGGER.warn("No active faq data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active faq data found");
            }

            LOGGER.info("Fetched {} active faq data successfully", faqResponseList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqResponseList,
                            Instant.now());

        }catch (DataAccessErrorExceptionHandler | DataNotFoundErrorExceptionHandler e){
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active faq data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active faq data from database");
        } finally {
            LOGGER.info("End fetching active faq data from repository");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updateFaqViewCount(FaqViewCountUpdateRequest faqViewCountUpdateRequest) {
        LOGGER.info("Start update faq view count from repository");
        try {
            FaqResponse faqResponse = faqRepository.getFaqItemById(faqViewCountUpdateRequest.getFaqId());

            if (faqResponse != null) {
                LOGGER.info("Fetched faq data successfully");
                faqResponse.setFaqViewCount(faqResponse.getFaqViewCount() + 1);
                faqResponse.setFaqLastView(Instant.now().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
                faqRepository.updateFaqViewCount(faqResponse);
            } else {
                LOGGER.warn("No faq data found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq data found");
            }

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_UPDATE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_UPDATE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                            new UpdateResponse(
                                    "Success fully update faq count",
                                    faqViewCountUpdateRequest.getFaqId()
                            ),
                            Instant.now());

        } catch (Exception e) {
            LOGGER.error("Error occurred while updating faq data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq data from database");
        } finally {
            LOGGER.info("End updating faq data from repository");
        }
    }

    @Override
    public CommonResponse<List<FaqOptionDetailsResponse>> getFaqOptions() {
        LOGGER.info("Start fetching faq options from repository");
        try {
            List<FaqOptionDetailsResponse> faqOptionDetailsResponses = faqRepository.getFaqOptions();

            if (faqOptionDetailsResponses.isEmpty()) {
                LOGGER.warn("No faq options found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq options found");
            }

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqOptionDetailsResponses,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching faq options: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq options from database");
        } finally {
            LOGGER.info("End fetching faq options from repository");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertFaqRequest(InsertFaqRequest insertFaqRequest) {
        try {
            faqValidationService.validateInsertFaqRequest(insertFaqRequest);
            faqRepository.insertFaqRequest(insertFaqRequest);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                            CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                            new InsertResponse("Successfully insert faq request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert faq request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

}
