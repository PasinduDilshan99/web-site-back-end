package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.OurServiceStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurServiceResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.OurServicesRepository;
import com.felicita.service.OurServicesService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OurServicesServiceImpl implements OurServicesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurServicesServiceImpl.class);

    private final OurServicesRepository ourServicesRepository;

    @Autowired
    public OurServicesServiceImpl(OurServicesRepository ourServicesRepository) {
        this.ourServicesRepository = ourServicesRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllOurServices() {
        LOGGER.info("Start fetching all our services from repository");
        try {
            List<OurServiceResponse> ourServiceResponses = ourServicesRepository.getAllOurServices();

            if (ourServiceResponses.isEmpty()) {
                LOGGER.warn("No our services found in database");
                throw new DataNotFoundErrorExceptionHandler("No our services found");
            }

            LOGGER.info("Fetched {} our services successfully", ourServiceResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            ourServiceResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching our services: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching our services: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch our services from database");
        } finally {
            LOGGER.info("End fetching all our services from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllVisibleOurServices() {
        LOGGER.info("Start fetching all visible our services from repository");

        try {
            List<OurServiceResponse> ourServiceResponses = ourServicesRepository.getAllOurServices();

            if (ourServiceResponses.isEmpty()) {
                LOGGER.warn("No our services found in database");
                throw new DataNotFoundErrorExceptionHandler("No our services found");
            }

            List<OurServiceResponse> ourServiceResponseList = ourServiceResponses.stream()
                    .filter(item -> OurServiceStatus.ACTIVE.toString().equalsIgnoreCase(item.getServiceStatus()))
                    .toList();

            if (ourServiceResponseList.isEmpty()) {
                LOGGER.warn("No visible our services found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible our services found");
            }

            LOGGER.info("Fetched {} visible our services successfully", ourServiceResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            ourServiceResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible our services: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible our services: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch our services from database");
        } finally {
            LOGGER.info("End fetching all visible our services from repository");
        }
    }
}
