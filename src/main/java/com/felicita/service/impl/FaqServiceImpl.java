package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.FaqItemStatus;
import com.felicita.model.enums.HeroSectionItemStatus;
import com.felicita.model.request.FaqViewCountUpdateRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.HeroSectionResponse;
import com.felicita.model.response.UpdateResponse;
import com.felicita.repository.FaqRepository;
import com.felicita.service.FaqService;
import com.felicita.util.CommonResponseMessages;
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

    @Autowired
    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
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
            }else{
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
}
