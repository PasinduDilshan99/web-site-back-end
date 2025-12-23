package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.VehicleBasicDetailsDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.VehicleDetailResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.repository.VehicleRepository;
import com.felicita.service.VehicleService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public CommonResponse<List<VehicleResponse>> getActiveVehicles() {
        LOGGER.info("Start fetching all visible partners from repository");

        try {
            List<VehicleResponse> vehicleResponses = vehicleRepository.getActiveVehicles();

            if (vehicleResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<VehicleResponse> partnerResponseList = vehicleResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (partnerResponseList.isEmpty()) {
                LOGGER.warn("No visible partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible partners found");
            }

            LOGGER.info("Fetched {} visible partners successfully", partnerResponseList.size());

            return (
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

    @Override
    public CommonResponse<List<VehicleDetailResponse>> getVehicleDetailsById(String vehicleId) {
        LOGGER.info("Start fetching all visible partners from repository");

        try {
            List<VehicleDetailResponse> vehicleDetailResponses = vehicleRepository.getVehicleDetailsById(vehicleId);

            if (vehicleDetailResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<VehicleDetailResponse> vehicleDetailResponseList = vehicleDetailResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (vehicleDetailResponseList.isEmpty()) {
                LOGGER.warn("No visible partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible partners found");
            }

            LOGGER.info("Fetched {} visible partners successfully", vehicleDetailResponseList.size());

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            vehicleDetailResponseList,
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
    public VehicleBasicDetailsDto getVehicleBasicDetailsById(Long vehicleId) {
        LOGGER.info("Start fetching all visible partners from repository");

        try {
            VehicleBasicDetailsDto vehicleBasicDetailsDto = vehicleRepository.getVehicleBasicDetailsById(vehicleId);
            return vehicleBasicDetailsDto;

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
