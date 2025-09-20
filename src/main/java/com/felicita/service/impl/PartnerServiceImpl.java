package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.FaqItemStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.PartnerRepository;
import com.felicita.service.PartnerService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllPartners() {
        LOGGER.info("Start fetching all partners from repository");
        try {
            List<PartnerResponse> partnerResponses = partnerRepository.getAllPartners();

            if (partnerResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            LOGGER.info("Fetched {} partners successfully", partnerResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            partnerResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all partners from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllVisiblePartners() {
        LOGGER.info("Start fetching all visible partners from repository");

        try {
            List<PartnerResponse> partnerResponses = partnerRepository.getAllPartners();

            if (partnerResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<PartnerResponse> partnerResponseList = partnerResponses.stream()
                    .filter(item -> PartnerStatus.ACTIVE.toString().equalsIgnoreCase(item.getPartnerStatus()))
                    .toList();

            if (partnerResponseList.isEmpty()) {
                LOGGER.warn("No visible partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible partners found");
            }

            LOGGER.info("Fetched {} visible partners successfully", partnerResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            partnerResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible partners: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible partners: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all visible partners from repository");
        }
    }
}
