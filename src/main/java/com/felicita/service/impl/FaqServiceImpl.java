package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.enums.FaqItemStatus;
import com.felicita.model.enums.HeroSectionItemStatus;
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
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getAllFaqItems() {
        LOGGER.info("Start fetching all faq items from repository");
        try {
            List<FaqResponse> faqResponses = faqRepository.getAllFaqItems();

            if (faqResponses.isEmpty()) {
                LOGGER.warn("No faq items found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq items found");
            }

            LOGGER.info("Fetched {} faq items successfully", faqResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching faq items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq items from database");
        } finally {
            LOGGER.info("End fetching all faq items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<FaqResponse>>> getAllVisibleFaqItems() {
        LOGGER.info("Start fetching all visible faq items from repository");

        try {
            List<FaqResponse> faqResponses = faqRepository.getAllFaqItems();

            if (faqResponses.isEmpty()) {
                LOGGER.warn("No faq items found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq items found");
            }

            List<FaqResponse> faqResponseList = faqResponses.stream()
                    .filter(item -> FaqItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getFaqStatus()))
                    .toList();

            if (faqResponseList.isEmpty()) {
                LOGGER.warn("No visible faq items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible faq items found");
            }

            LOGGER.info("Fetched {} visible faq items successfully", faqResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqResponseList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible faq items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq items from database");
        } finally {
            LOGGER.info("End fetching all visible faq items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<UpdateResponse>> updateFaqViewCount(FaqViewCountUpdateRequest faqViewCountUpdateRequest) {
        LOGGER.info("Start update faq view count from repository");
        try {
            FaqResponse faqResponse = faqRepository.getFaqItemById(faqViewCountUpdateRequest.getFaqId());

            if (faqResponse != null) {
                LOGGER.info("Fetched faq item successfully");
                faqResponse.setFaqViewCount(faqResponse.getFaqViewCount() + 1);
                faqResponse.setFaqLastView(Instant.now().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
                faqRepository.updateFaqViewCount(faqResponse);
            } else {
                LOGGER.warn("No faq item found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq item found");
            }

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new UpdateResponse(
                                    "Success fully update faq count",
                                    faqViewCountUpdateRequest.getFaqId()
                            ),
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while updating faq items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq items from database");
        } finally {
            LOGGER.info("End updating faq items from repository");
        }
    }

    @Override
    public CommonResponse<List<FaqOptionDetailsResponse>> getFaqOptions() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<FaqOptionDetailsResponse> faqOptionDetailsResponses = faqRepository.getFaqOptions();

            if (faqOptionDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqOptionDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertFaqRequest(InsertFaqRequest insertFaqRequest) {
        try {
            faqValidationService.validateInsertFaqRequest(insertFaqRequest);
            faqRepository.insertFaqRequest(insertFaqRequest);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
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
