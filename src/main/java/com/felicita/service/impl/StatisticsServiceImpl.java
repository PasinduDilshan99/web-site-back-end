package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.AboutUsHeroSectionResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.StatisticsResponse;
import com.felicita.repository.StatisticsRepository;
import com.felicita.service.StatisticsService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public CommonResponse<List<StatisticsResponse>> getAboutUsStatisticsDetails() {
        LOGGER.info("Start fetching all about us statistics data from repository");

        try {
            List<StatisticsResponse> statisticsResponses = statisticsRepository.getAboutUsStatisticsDetails();

            if (statisticsResponses.isEmpty()) {
                LOGGER.warn("No about us statistics data found in database");
                throw new DataNotFoundErrorExceptionHandler("No about us statistics data found");
            }

            List<StatisticsResponse> statisticsResponseList = statisticsResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (statisticsResponseList.isEmpty()) {
                LOGGER.warn("No active about us statistics data found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible statistics data found");
            }

            LOGGER.info("Fetched {} active about us statistics data successfully", statisticsResponseList.size());

            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            statisticsResponseList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active about us statistics data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch about us statistics data from database");
        } finally {
            LOGGER.info("End fetching all about us statistics data from repository");
        }
    }
}
