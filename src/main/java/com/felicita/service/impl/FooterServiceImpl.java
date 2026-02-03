package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
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
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonResponse<FooterResponse> getAllFooterData() {
        LOGGER.info("Start fetching all footer data from repository");
        try {
            List<FooterSectionDto> allFooterData = footerRepository.getAllFooterData();
            List<FooterOtherDto> allFooterOthersData = footerRepository.getAllFooterOthersData();
            List<SocialMediaResponse> allSocialMediaData = socialMediaRepository.getAllSocialMediaData();

            FooterResponse footerResponse = new FooterResponse(
                    allFooterData,
                    allSocialMediaData,
                    allFooterOthersData
            );

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            footerResponse,
                            Instant.now());

        }catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching footer data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch footer data from database");
        } finally {
            LOGGER.info("End fetching all footer data from repository");
        }
    }

    @Override
    public CommonResponse<FooterResponse> getActiveFooterData() {
        LOGGER.info("Start fetching active footer data from repository");
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
                    footerOtherDtoList);

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            footerResponse,
                            Instant.now());

        }catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active footer data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active footer data from database");
        } finally {
            LOGGER.info("End fetching active footer data from repository");
        }
    }

}
