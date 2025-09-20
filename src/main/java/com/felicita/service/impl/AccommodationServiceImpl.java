package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.AccommodationDataStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.AccommodationResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.AccommodationRepository;
import com.felicita.service.AccommodationService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationServiceImpl.class);

    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }


    @Override
    public ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllAccommodations() {
        LOGGER.info("Start fetching all accommodation data from repository");
        try {
            List<AccommodationResponse> accommodationResponses = accommodationRepository.getAllAccommodations();

            if (accommodationResponses.isEmpty()) {
                LOGGER.warn("No accommodation data found in database");
                throw new DataNotFoundErrorExceptionHandler("No accommodation data found");
            }

            LOGGER.info("Fetched {} accommodation data successfully", accommodationResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            accommodationResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching accommodation data: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching accommodation data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch accommodation data from database");
        } finally {
            LOGGER.info("End fetching all accommodation data from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<AccommodationResponse>>> getAllActiveAccommodations() {
        LOGGER.info("Start fetching all visible accommodation data from repository");

        try {
            List<AccommodationResponse> accommodationResponses = accommodationRepository.getAllAccommodations();

            if (accommodationResponses.isEmpty()) {
                LOGGER.warn("No accommodation data found in database");
                throw new DataNotFoundErrorExceptionHandler("No accommodation data found");
            }

            List<AccommodationResponse> accommodationResponseList = accommodationResponses.stream()
                    .filter(item -> AccommodationDataStatus.AVAILABLE.toString().equalsIgnoreCase(item.getAccommodationStatus()))
                    .toList();

            if (accommodationResponseList.isEmpty()) {
                LOGGER.warn("No visible accommodation data found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible accommodation data found");
            }

            LOGGER.info("Fetched {} visible accommodation data successfully", accommodationResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            accommodationResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible accommodation data: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible accommodation data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch accommodation data from database");
        } finally {
            LOGGER.info("End fetching all visible accommodation data from repository");
        }
    }
}
