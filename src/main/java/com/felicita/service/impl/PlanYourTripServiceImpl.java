package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.PlanYourTripActivitiesDto;
import com.felicita.model.dto.PlanYourTripDestinationDto;
import com.felicita.model.dto.PlanYourTripDestinationsDto;
import com.felicita.model.dto.PlanYourTripNearDestinationsDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PlanYourTripDestinationResponse;
import com.felicita.repository.PlanYourTripRepository;
import com.felicita.service.PlanYourTripService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
public class PlanYourTripServiceImpl implements PlanYourTripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanYourTripServiceImpl.class);

    private final PlanYourTripRepository planYourTripRepository;

    @Autowired
    public PlanYourTripServiceImpl(PlanYourTripRepository planYourTripRepository) {
        this.planYourTripRepository = planYourTripRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getAllDestinationsForPlanYouTrip() {
        LOGGER.info("Start fetching all partners from repository");
        try {
            List<PlanYourTripDestinationsDto> planYourTripDestinationsDtos = planYourTripRepository.getAllDestinationsForPlanYouTrip();

            if (planYourTripDestinationsDtos.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            LOGGER.info("Fetched {} partners successfully", planYourTripDestinationsDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            planYourTripDestinationsDtos,
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
    public ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getActiveDestinationsForPlanYouTrip() {
        LOGGER.info("Start fetching all visible partners from repository");

        try {
            List<PlanYourTripDestinationsDto> planYourTripDestinationsDtos = planYourTripRepository.getAllDestinationsForPlanYouTrip();

            if (planYourTripDestinationsDtos.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<PlanYourTripDestinationsDto> planYourTripDestinationsDtoList = planYourTripDestinationsDtos.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (planYourTripDestinationsDtoList.isEmpty()) {
                LOGGER.warn("No visible partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible partners found");
            }

            LOGGER.info("Fetched {} visible partners successfully", planYourTripDestinationsDtoList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            planYourTripDestinationsDtoList,
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

    @Override
    public ResponseEntity<CommonResponse<PlanYourTripDestinationResponse>> getDestinationsForPlanYourTrip() {
        LOGGER.info("Start fetching all partners from repository");
        try {
            List<PlanYourTripDestinationDto> planYourTripDestinationDtos = planYourTripRepository.getAllPlanYourTripDestination();
            List<PlanYourTripActivitiesDto> planYourTripActivitiesDtos = planYourTripRepository.getAllPlanYourTripActivitiesDtos();
            List<PlanYourTripNearDestinationsDto> planYourTripNearDestinationsDtos = planYourTripRepository.getAllPlanYourTripNearDestinationsDtos();

            PlanYourTripDestinationResponse planYourTripDestinationsDtos = new PlanYourTripDestinationResponse(
                    planYourTripDestinationDtos,
                    planYourTripActivitiesDtos,
                    planYourTripNearDestinationsDtos
            );


            LOGGER.info("Fetched partners successfully");
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            planYourTripDestinationsDtos,
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
}
