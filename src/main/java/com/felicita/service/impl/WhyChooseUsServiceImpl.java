package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.HeroSectionItemStatus;
import com.felicita.model.enums.WhyChooseUsItemStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.HeroSectionResponse;
import com.felicita.model.response.WhyChooseUsResponse;
import com.felicita.repository.WhyChooseUsRepository;
import com.felicita.service.WhyChooseUsService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WhyChooseUsServiceImpl implements WhyChooseUsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhyChooseUsServiceImpl.class);

    private final WhyChooseUsRepository whyChooseUsRepository;

    @Autowired
    public WhyChooseUsServiceImpl(WhyChooseUsRepository whyChooseUsRepository) {
        this.whyChooseUsRepository = whyChooseUsRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllWhyChooseUsItems() {
        LOGGER.info("Start fetching all why choose us items from repository");
        try {
            List<WhyChooseUsResponse> whyChooseUsResponses = whyChooseUsRepository.getAllWhyChooseUsItems();

            if (whyChooseUsResponses.isEmpty()) {
                LOGGER.warn("No why choose us items found in database");
                throw new DataNotFoundErrorExceptionHandler("No why choose us items found");
            }

            LOGGER.info("Fetched {} why choose us items successfully", whyChooseUsResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            whyChooseUsResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching why choose us items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch why choose us items from database");
        } finally {
            LOGGER.info("End fetching all why choose us items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<WhyChooseUsResponse>>> getAllVisibleWhyChooseUsItems() {
        LOGGER.info("Start fetching all visible why choose us items from repository");

        try {
            List<WhyChooseUsResponse> whyChooseUsResponses = whyChooseUsRepository.getAllWhyChooseUsItems();

            if (whyChooseUsResponses.isEmpty()) {
                LOGGER.warn("No why choose us items found in database");
                throw new DataNotFoundErrorExceptionHandler("No why choose us items found");
            }

            List<WhyChooseUsResponse> whyChooseUsResponsesList = whyChooseUsResponses.stream()
                    .filter(item -> WhyChooseUsItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getCardStatus()))
                    .toList();

            if (whyChooseUsResponses.isEmpty()) {
                LOGGER.warn("No visible why choose us items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible why choose us items found");
            }

            LOGGER.info("Fetched {} visible why choose us items successfully", whyChooseUsResponses.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            whyChooseUsResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible why choose us items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch why choose us items from database");
        } finally {
            LOGGER.info("End fetching all visible why choose us items from repository");
        }
    }
}
