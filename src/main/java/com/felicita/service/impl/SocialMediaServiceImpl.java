package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.model.response.SocialMediaWithBestForRespone;
import com.felicita.repository.SocialMediaRepository;
import com.felicita.service.SocialMediaService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaServiceImpl.class);

    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public SocialMediaServiceImpl(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }


    @Override
    public ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getAllSocialMediaData() {
        LOGGER.info("Start fetching all social media data from repository");
        try {
            List<SocialMediaResponse> socialMediaResponses = socialMediaRepository.getAllSocialMediaData();

            if (socialMediaResponses.isEmpty()) {
                LOGGER.warn("No social media data found in database");
                throw new DataNotFoundErrorExceptionHandler("No social media data found");
            }

            LOGGER.info("Fetched {} social media data successfully", socialMediaResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            socialMediaResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching social media data: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching social media data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch social media data from database");
        } finally {
            LOGGER.info("End fetching all social media data from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<SocialMediaResponse>>> getActiveSocialMediaData() {
        LOGGER.info("Start fetching all active social media data from repository");

        try {
            List<SocialMediaResponse> socialMediaResponses = socialMediaRepository.getAllSocialMediaData();

            if (socialMediaResponses.isEmpty()) {
                LOGGER.warn("No social media data found in database");
                throw new DataNotFoundErrorExceptionHandler("No social media data found");
            }

            List<SocialMediaResponse> socialMediaResponseList = socialMediaResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (socialMediaResponseList.isEmpty()) {
                LOGGER.warn("No active social media data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active social media data found");
            }

            LOGGER.info("Fetched {} active social media data successfully", socialMediaResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            socialMediaResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active social media data: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active social media data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch social media data from database");
        } finally {
            LOGGER.info("End fetching all active social media data from repository");
        }
    }

    @Override
    public CommonResponse<List<SocialMediaWithBestForRespone>> getSocialMediaWithBestForData() {
        LOGGER.info("Start fetching all active social media data with best for from repository");

        try {
            List<SocialMediaWithBestForRespone> socialMediaWithBestForData = socialMediaRepository.getSocialMediaWithBestForData();

            if (socialMediaWithBestForData.isEmpty()) {
                LOGGER.warn("No social media data with best for found in database");
                throw new DataNotFoundErrorExceptionHandler("No social media data with best for found");
            }

            List<SocialMediaWithBestForRespone> socialMediaWithBestForResponeList = socialMediaWithBestForData.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getSocialMediaStatus()))
                    .toList();

            if (socialMediaWithBestForResponeList.isEmpty()) {
                LOGGER.warn("No active social media data with best for found in database");
                throw new DataNotFoundErrorExceptionHandler("No active social media data with best for found");
            }

            LOGGER.info("Fetched {} active social media data with best for successfully", socialMediaWithBestForResponeList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            socialMediaWithBestForResponeList,
                            Instant.now()
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active social media data with best for: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active social media data with best for: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch social media data with best for from database");
        } finally {
            LOGGER.info("End fetching all active social media data with best for from repository");
        }
    }
}
