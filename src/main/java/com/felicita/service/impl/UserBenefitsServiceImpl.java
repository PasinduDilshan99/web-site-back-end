package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserBenefitResponse;
import com.felicita.model.response.UserLevelDetailsResponse;
import com.felicita.model.response.UserLevelsResponse;
import com.felicita.repository.UserBenefitsRepository;
import com.felicita.service.CommonService;
import com.felicita.service.UserBenefitsService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserBenefitsServiceImpl implements UserBenefitsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBenefitsServiceImpl.class);

    private final UserBenefitsRepository userBenefitsRepository;
    private final CommonService commonService;

    @Autowired
    public UserBenefitsServiceImpl(UserBenefitsRepository userBenefitsRepository,
                                   CommonService commonService) {
        this.userBenefitsRepository = userBenefitsRepository;
        this.commonService =commonService;
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllUserBenefits() {
        LOGGER.info("Start fetching all user benefits from repository");
        try {
            List<UserBenefitResponse> userBenefitResponses = userBenefitsRepository.getAllUserBenefits();

            if (userBenefitResponses.isEmpty()) {
                LOGGER.warn("No user benefits found in database");
                throw new DataNotFoundErrorExceptionHandler("No user benefits found");
            }

            LOGGER.info("Fetched {} user benefits successfully", userBenefitResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userBenefitResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user benefits: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching user benefits: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user benefits from database");
        } finally {
            LOGGER.info("End fetching all user benefits from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllActiveUserBenefits() {
        LOGGER.info("Start fetching all active user benefits from repository");

        try {
            List<UserBenefitResponse> userBenefitResponses = userBenefitsRepository.getAllUserBenefits();

            if (userBenefitResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            List<UserBenefitResponse> userBenefitResponseList = userBenefitResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getBenefitStatus()))
                    .toList();

            if (userBenefitResponseList.isEmpty()) {
                LOGGER.warn("No active user benefits found in database");
                throw new DataNotFoundErrorExceptionHandler("No active user benefits found");
            }

            LOGGER.info("Fetched {} active user benefits successfully", userBenefitResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userBenefitResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active user benefits: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active user benefits: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all active user benefits from repository");
        }
    }

    @Override
    public CommonResponse<UserLevelDetailsResponse> getUserBenifitsDetailsForUserId() {
        LOGGER.info("Start fetching user level benefits details for current user");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            LOGGER.info("Fetching details for user ID: {}", userId);

            UserLevelDetailsResponse userLevelDetailsResponse = userBenefitsRepository.getUserBenifitsDetailsForUserId(userId);

            if (userLevelDetailsResponse == null) {
                LOGGER.warn("No user level details found for user ID: {}", userId);
                throw new DataNotFoundErrorExceptionHandler("No user level details found");
            }

            LOGGER.info("Successfully fetched user level details for user ID: {}", userId);
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    userLevelDetailsResponse,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user level details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user level details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user level details from database");
        } finally {
            LOGGER.info("End fetching user level benefits details for current user");
        }
    }
}
