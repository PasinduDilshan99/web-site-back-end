package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.UserLevelWithBenefitsResponse;
import com.felicita.model.response.UserLevelsResponse;
import com.felicita.repository.UserLevelRepository;
import com.felicita.service.UserLevelService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserLevelServiceImpl implements UserLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLevelServiceImpl.class);

    private final UserLevelRepository userLevelRepository;

    @Autowired
    public UserLevelServiceImpl(UserLevelRepository userLevelRepository) {
        this.userLevelRepository = userLevelRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserLevelWithBenefits() {
        LOGGER.info("Start fetching all user levels from repository");
        try {
            List<UserLevelWithBenefitsResponse> userLevelWithBenefitsResponses = userLevelRepository.getAllUserLevelWithBenefits();

            if (userLevelWithBenefitsResponses.isEmpty()) {
                LOGGER.warn("No user levels found in database");
                throw new DataNotFoundErrorExceptionHandler("No user levels found");
            }

            LOGGER.info("Fetched {} user levels successfully", userLevelWithBenefitsResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userLevelWithBenefitsResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user levels: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching user levels: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user levels from database");
        } finally {
            LOGGER.info("End fetching all user levels from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllUserLevel() {
        LOGGER.info("Start fetching all user levels from repository");
        try {
            List<UserLevelsResponse> userLevelsResponses = userLevelRepository.getAllUserLevel();

            if (userLevelsResponses.isEmpty()) {
                LOGGER.warn("No user levels found in database");
                throw new DataNotFoundErrorExceptionHandler("No user levels found");
            }

            LOGGER.info("Fetched {} user levels successfully", userLevelsResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userLevelsResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user levels: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching user levels: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user levels from database");
        } finally {
            LOGGER.info("End fetching all user levels from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllActiveUserLevel() {
        LOGGER.info("Start fetching all active user levels from repository");

        try {
            List<UserLevelsResponse> userLevelsResponses = userLevelRepository.getAllUserLevel();

            if (userLevelsResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<UserLevelsResponse> userLevelsResponseList = userLevelsResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (userLevelsResponseList.isEmpty()) {
                LOGGER.warn("No active user levels found in database");
                throw new DataNotFoundErrorExceptionHandler("No active user levels found");
            }

            LOGGER.info("Fetched {} active user levels successfully", userLevelsResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userLevelsResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active user levels: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active user levels: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all active user levels from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserActiveLevelWithBenefits() {
        LOGGER.info("Start fetching all active user levels from repository");

        try {
            List<UserLevelWithBenefitsResponse> userLevelWithBenefitsResponses = userLevelRepository.getAllUserLevelWithBenefits();

            if (userLevelWithBenefitsResponses.isEmpty()) {
                LOGGER.warn("No user levels found in database");
                throw new DataNotFoundErrorExceptionHandler("No user levels found");
            }

            List<UserLevelWithBenefitsResponse> userLevelWithBenefitsResponseList = userLevelWithBenefitsResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getUserLevelStatus()))
                    .toList();

            if (userLevelWithBenefitsResponseList.isEmpty()) {
                LOGGER.warn("No active user levels found in database");
                throw new DataNotFoundErrorExceptionHandler("No active user levels found");
            }

            LOGGER.info("Fetched {} active user levels successfully", userLevelWithBenefitsResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userLevelWithBenefitsResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active user levels: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active user levels: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all active user levels from repository");
        }
    }
}
