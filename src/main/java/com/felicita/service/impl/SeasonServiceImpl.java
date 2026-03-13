package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.SeasonBasicResponse;
import com.felicita.model.response.SeasonDetailsResponse;
import com.felicita.repository.SeasonRepository;
import com.felicita.service.SeasonService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonServiceImpl.class);

    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public CommonResponse<List<SeasonDetailsResponse>> getSeasonDetailsBySeasonId(String seasonId) {
        LOGGER.info("Start fetching season details by season id : {} from repository", seasonId);
        try {
            List<SeasonDetailsResponse> seasonDetailsResponses = seasonRepository.getSeasonDetailsBySeasonId(seasonId);

            if (seasonDetailsResponses == null) {
                LOGGER.warn("No season details by season id : {} in database", seasonId);
                throw new DataNotFoundErrorExceptionHandler("No season details by season id : " + seasonId);
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    seasonDetailsResponses,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching season details by season id : {} , {}", seasonId ,e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch season details by season id : " + seasonId);
        } finally {
            LOGGER.info("End fetching season details by season id : {} from repository", seasonId);
        }
    }

    @Override
    public CommonResponse<List<SeasonBasicResponse>> getActiveSeasonDetails() {
        LOGGER.info("Start fetching season details from repository");
        try {
            List<SeasonBasicResponse> seasonBasicResponses = seasonRepository.getActiveSeasonDetails();

            if (seasonBasicResponses == null) {
                LOGGER.warn("No season details in database");
                throw new DataNotFoundErrorExceptionHandler("No season details");
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    seasonBasicResponses,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching season details {}" ,e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch season details " );
        } finally {
            LOGGER.info("End fetching season details from repository");
        }
    }
}
