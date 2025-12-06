package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CancelledToursResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.repository.OurFeaturesRepository;
import com.felicita.service.OurFeaturesService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OurFeaturesServiceImpl implements OurFeaturesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurFeaturesServiceImpl.class);

    private final OurFeaturesRepository ourFeaturesRepository;

    @Autowired
    public OurFeaturesServiceImpl(OurFeaturesRepository ourFeaturesRepository) {
        this.ourFeaturesRepository = ourFeaturesRepository;
    }

    @Override
    public CommonResponse<List<FeatureResponse>> getOurFeaturesDetails() {
        LOGGER.info("Start fetching our features details from repository");
        try {
            List<FeatureResponse> featureResponses = ourFeaturesRepository.getOurFeaturesDetails();

            if (featureResponses.isEmpty()) {
                LOGGER.warn("No our features details found in database");
                throw new DataNotFoundErrorExceptionHandler("No our features details found");
            }

            LOGGER.info("Fetched {} our features details successfully", featureResponses.size());
            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            featureResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching our features details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching our features details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch our features details from database");
        } finally {
            LOGGER.info("End fetching all our features details from repository");
        }
    }
}
