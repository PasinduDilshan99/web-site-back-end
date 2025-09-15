package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.LinkBarItemStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import com.felicita.repository.LinkBarRepository;
import com.felicita.service.LinkBarService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LinkBarServiceImpl implements LinkBarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkBarServiceImpl.class);

    private final LinkBarRepository linkBarRepository;

    @Autowired
    public LinkBarServiceImpl(LinkBarRepository linkBarRepository) {
        this.linkBarRepository = linkBarRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<LinkBarResponse>>> getAllLinkBarItems() {
        LOGGER.info("Start fetching all LinkBar items from repository");
        try {
            List<LinkBarResponse> linkBarResponses = linkBarRepository.getAllLinkBarItems();

            if (linkBarResponses.isEmpty()) {
                LOGGER.warn("No LinkBar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No LinkBar items found");
            }

            LOGGER.info("Fetched {} LinkBar items successfully", linkBarResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            linkBarResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching LinkBar items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch LinkBar items from database");
        } finally {
            LOGGER.info("End fetching all LinkBar items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<LinkBarResponse>>> getAllVisibleLinkBarItems() {
        LOGGER.info("Start fetching all visible LinkBar items from repository");

        try {
            List<LinkBarResponse> linkBarResponses = linkBarRepository.getAllLinkBarItems();

            if (linkBarResponses.isEmpty()) {
                LOGGER.warn("No LinkBar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No LinkBar items found");
            }

            List<LinkBarResponse> visibleList = linkBarResponses.stream()
                    .filter(item -> LinkBarItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getItemStatus()))
                    .toList();

            if (visibleList.isEmpty()) {
                LOGGER.warn("No visible LinkBar items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible LinkBar items found");
            }

            LOGGER.info("Fetched {} visible LinkBar items successfully", visibleList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            visibleList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible LinkBar items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch LinkBar items from database");
        } finally {
            LOGGER.info("End fetching all visible LinkBar items from repository");
        }
    }


}
