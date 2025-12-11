package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurStoryAndValuesResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.repository.OurStoryRepository;
import com.felicita.service.OurStoryService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OurStoryServiceImpl implements OurStoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurStoryServiceImpl.class);

    private final OurStoryRepository ourStoryRepository;

    @Autowired
    public OurStoryServiceImpl(OurStoryRepository ourStoryRepository) {
        this.ourStoryRepository = ourStoryRepository;
    }

    @Override
    public CommonResponse<OurStoryAndValuesResponse> getOurStoryAndKeyValueDetails() {
        LOGGER.info("Start fetching our story details from repository");

        try {
            List<OurStoryAndValuesResponse.Timeline> timelines = ourStoryRepository.getOurStoryDetails();
            List<OurStoryAndValuesResponse.CoreValue> coreValues = ourStoryRepository.getOurCoreValues();

            OurStoryAndValuesResponse ourStoryAndValuesResponse = new OurStoryAndValuesResponse(
                    timelines,
                    coreValues
            );

            LOGGER.info("Fetched our story details successfully");

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            ourStoryAndValuesResponse,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching our story details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching our story details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch our story details from database");
        } finally {
            LOGGER.info("End fetching our story details from repository");
        }
    }
}
