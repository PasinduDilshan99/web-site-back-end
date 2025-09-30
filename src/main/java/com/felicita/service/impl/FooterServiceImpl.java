package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.FooterOtherDto;
import com.felicita.model.dto.FooterSectionDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FooterResponse;
import com.felicita.model.response.SocialMediaResponse;
import com.felicita.repository.FooterRepository;
import com.felicita.repository.SocialMediaRepository;
import com.felicita.service.FooterService;
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
public class FooterServiceImpl implements FooterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooterServiceImpl.class);

    private final FooterRepository footerRepository;
    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public FooterServiceImpl(FooterRepository footerRepository, SocialMediaRepository socialMediaRepository) {
        this.footerRepository = footerRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<FooterResponse>> getAllFooterData() {
        LOGGER.info("Start fetching all social media data from repository");
        try {
            List<FooterSectionDto> allFooterData = footerRepository.getAllFooterData();
            List<FooterOtherDto> allFooterOthersData = footerRepository.getAllFooterOthersData();
            List<SocialMediaResponse> allSocialMediaData = socialMediaRepository.getAllSocialMediaData();

            FooterResponse footerResponse = new FooterResponse(
                    allFooterData,
                    allSocialMediaData,
                    allFooterOthersData
            );

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            footerResponse,
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
    public ResponseEntity<CommonResponse<FooterResponse>> getActiveFooterData() {
        LOGGER.info("Start fetching all social media data from repository");
        try {
            List<FooterSectionDto> allFooterData = footerRepository.getAllFooterData();
            List<FooterOtherDto> allFooterOthersData = footerRepository.getAllFooterOthersData();
            List<SocialMediaResponse> allSocialMediaData = socialMediaRepository.getAllSocialMediaData();

            List<FooterSectionDto> footerSectionDtoList = allFooterData.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();
            List<FooterOtherDto> footerOtherDtoList = allFooterOthersData.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();
            List<SocialMediaResponse> socialMediaResponses = allSocialMediaData.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            FooterResponse footerResponse = new FooterResponse(
                    footerSectionDtoList,
                    socialMediaResponses,
                    footerOtherDtoList
            );

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            footerResponse,
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
}
