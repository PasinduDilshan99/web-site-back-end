package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.HeroSectionItemStatus;
import com.felicita.model.enums.NavBarItemStatus;
import com.felicita.model.response.*;
import com.felicita.repository.HeroSectionRepository;
import com.felicita.service.HeroSectionService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class HeroSectionServiceImpl implements HeroSectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroSectionServiceImpl.class);

    private final HeroSectionRepository heroSectionRepository;

    @Autowired
    public HeroSectionServiceImpl(HeroSectionRepository heroSectionRepository) {
        this.heroSectionRepository = heroSectionRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllHeroSectionItems() {
        LOGGER.info("Start fetching all hero section items from repository");
        try {
            List<HeroSectionResponse> heroSectionResponses = heroSectionRepository.getAllHeroSectionItems();

            if (heroSectionResponses.isEmpty()) {
                LOGGER.warn("No hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No hero section items found");
            }

            LOGGER.info("Fetched {} hero section items successfully", heroSectionResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            heroSectionResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching hero section items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch hero section items from database");
        } finally {
            LOGGER.info("End fetching all hero section items from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllVisibleHeroSectionItems() {
        LOGGER.info("Start fetching all visible hero section items from repository");

        try {
            List<HeroSectionResponse> heroSectionResponses = heroSectionRepository.getAllHeroSectionItems();

            if (heroSectionResponses.isEmpty()) {
                LOGGER.warn("No hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No hero section items found");
            }

            List<HeroSectionResponse> heroSectionResponsesList = heroSectionResponses.stream()
                    .filter(item -> HeroSectionItemStatus.VISIBLE.toString().equalsIgnoreCase(item.getImageStatus()))
                    .toList();

            if (heroSectionResponsesList.isEmpty()) {
                LOGGER.warn("No visible hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible hero section items found");
            }

            LOGGER.info("Fetched {} visible hero section items successfully", heroSectionResponsesList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            heroSectionResponsesList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible hero section items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch hero section items from database");
        } finally {
            LOGGER.info("End fetching all visible hero section items from repository");
        }
    }

    @Override
    public CommonResponse<List<AboutUsHeroSectionResponse>> getAboutUsHeroSectionDetails() {
        LOGGER.info("Start fetching all about us hero section items from repository");

        try {
            List<AboutUsHeroSectionResponse> aboutUsHeroSectionResponses = heroSectionRepository.getAboutUsHeroSectionDetails();

            if (aboutUsHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No about us hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No about us hero section items found");
            }

            List<AboutUsHeroSectionResponse> heroSectionResponsesList = aboutUsHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (heroSectionResponsesList.isEmpty()) {
                LOGGER.warn("No active about us hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible hero section items found");
            }

            LOGGER.info("Fetched {} active about us hero section items successfully", heroSectionResponsesList.size());

            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            aboutUsHeroSectionResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active about us hero section items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch about us hero section items from database");
        } finally {
            LOGGER.info("End fetching all about us hero section items from repository");
        }
    }

    @Override
    public CommonResponse<List<ContactUsHeroSectionResponse>> getContactUsHeroSectionDetails() {
        LOGGER.info("Start fetching all contact us hero section items from repository");

        try {
            List<ContactUsHeroSectionResponse> contactUsHeroSectionResponses = heroSectionRepository.getContactUsHeroSectionDetails();

            if (contactUsHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No contact us hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No contact us hero section items found");
            }

            List<ContactUsHeroSectionResponse> contactUsHeroSectionResponseList = contactUsHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (contactUsHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active contact us hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible hero section items found");
            }

            LOGGER.info("Fetched {} active contact us hero section items successfully", contactUsHeroSectionResponseList.size());

            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            contactUsHeroSectionResponses,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active contact us hero section items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch contact us hero section items from database");
        } finally {
            LOGGER.info("End fetching all contact us hero section items from repository");
        }
    }

    @Override
    public CommonResponse<List<BlogHeroSectionResponse>> getBlogHeroSectionDetails() {
        LOGGER.info("Start fetching all blog hero section items from repository");

        try {
            List<BlogHeroSectionResponse> blogHeroSectionResponses = heroSectionRepository.getBlogHeroSectionDetails();

            if (blogHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No blog hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No blog hero section items found");
            }

            List<BlogHeroSectionResponse> blogHeroSectionResponseList = blogHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (blogHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active blog hero section items found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible hero section items found");
            }

            LOGGER.info("Fetched {} active blog hero section items successfully", blogHeroSectionResponseList.size());

            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            blogHeroSectionResponseList,
                            Instant.now()
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active blog hero section items: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch blog hero section items from database");
        } finally {
            LOGGER.info("End fetching all blog hero section items from repository");
        }
    }
}
