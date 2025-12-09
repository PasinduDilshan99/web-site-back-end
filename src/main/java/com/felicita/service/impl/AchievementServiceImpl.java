package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.AchievementResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.repository.AchievementRepository;
import com.felicita.service.AchievementService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AchievementServiceImpl.class);

    private final AchievementRepository achievementRepository;

    @Autowired
    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public CommonResponse<List<AchievementResponse>> getAchievementDetails() {
        LOGGER.info("Start fetching achievements details from repository");

        try {
            List<AchievementResponse> AchievementResponse = achievementRepository.getAchievementDetails();
            if (AchievementResponse.isEmpty()) {
                LOGGER.warn("No achievements details found in database");
                throw new DataNotFoundErrorExceptionHandler("No achievements details found");
            }

            LOGGER.info("Fetched {} achievements details successfully", AchievementResponse.size());
            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            AchievementResponse,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching achievements details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching achievements details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch achievements details from database");
        } finally {
            LOGGER.info("End fetching all achievements details from repository");
        }
    }

}
