package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.HeroSectionItemStatus;
import com.felicita.model.response.*;
import com.felicita.repository.HeroSectionRepository;
import com.felicita.service.HeroSectionService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonResponse<List<HeroSectionResponse>> getAllHomeHeroSectionData() {
        LOGGER.info("Start fetching home hero section all data from repository");
        try {
            List<HeroSectionResponse> heroSectionResponses = heroSectionRepository.getAllHomeHeroSectionData();

            if (heroSectionResponses.isEmpty()) {
                LOGGER.warn("No home hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No home hero section data found");
            }

            LOGGER.info("Fetched {} home hero section all data successfully", heroSectionResponses.size());
            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    heroSectionResponses,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching home hero section all data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch home hero section all data from database");
        } finally {
            LOGGER.info("End fetching home hero section all data from repository");
        }
    }

    @Override
    public CommonResponse<List<HeroSectionResponse>> getHomeHeroSectionDetails() {
        LOGGER.info("Start fetching home hero section data from repository");

        try {
            List<HeroSectionResponse> heroSectionResponses = getAllHomeHeroSectionData().getData();

            List<HeroSectionResponse> heroSectionResponsesList = heroSectionResponses.stream()
                    .filter(item -> HeroSectionItemStatus.ACTIVE.toString().equalsIgnoreCase(item.getImageStatus()))
                    .toList();

            if (heroSectionResponsesList.isEmpty()) {
                LOGGER.warn("No active home hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active home hero section data found in database");
            }

            LOGGER.info("Fetched {} active home hero section data successfully", heroSectionResponsesList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    heroSectionResponsesList,
                    Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active home hero data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active home hero data from database");
        } finally {
            LOGGER.info("End fetching active home hero data from repository");
        }
    }

    @Override
    public CommonResponse<List<AboutUsHeroSectionResponse>> getAboutUsHeroSectionDetails() {
        LOGGER.info("Start fetching about us hero section data from repository");

        try {
            List<AboutUsHeroSectionResponse> aboutUsHeroSectionResponses = heroSectionRepository.getAboutUsHeroSectionDetails();

            if (aboutUsHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No about us hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No about us hero section data found");
            }

            List<AboutUsHeroSectionResponse> heroSectionResponsesList = aboutUsHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (heroSectionResponsesList.isEmpty()) {
                LOGGER.warn("No active about us hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active about us hero section data found");
            }

            LOGGER.info("Fetched {} active about us hero section data successfully", heroSectionResponsesList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            aboutUsHeroSectionResponses,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active about us hero section data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch about us hero section data from database");
        } finally {
            LOGGER.info("End fetching about us hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<ContactUsHeroSectionResponse>> getContactUsHeroSectionDetails() {
        LOGGER.info("Start fetching contact us hero section data from repository");

        try {
            List<ContactUsHeroSectionResponse> contactUsHeroSectionResponses = heroSectionRepository.getContactUsHeroSectionDetails();

            if (contactUsHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No contact us hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No contact us hero section data found");
            }

            List<ContactUsHeroSectionResponse> contactUsHeroSectionResponseList = contactUsHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (contactUsHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active contact us hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active contact us hero section data found");
            }

            LOGGER.info("Fetched {} active contact us hero section data successfully", contactUsHeroSectionResponseList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            contactUsHeroSectionResponses,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active contact us hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch contact us hero section data from database");
        } finally {
            LOGGER.info("End fetching contact us hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<BlogHeroSectionResponse>> getBlogHeroSectionDetails() {
        LOGGER.info("Start fetching blog hero section data from repository");

        try {
            List<BlogHeroSectionResponse> blogHeroSectionResponses = heroSectionRepository.getBlogHeroSectionDetails();

            if (blogHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No blog hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No blog hero section data found");
            }

            List<BlogHeroSectionResponse> blogHeroSectionResponseList = blogHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatusName()))
                    .toList();

            if (blogHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active blog hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active blog hero section data found");
            }

            LOGGER.info("Fetched {} active blog hero section data successfully", blogHeroSectionResponseList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            blogHeroSectionResponseList,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active blog hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch blog hero section data from database");
        } finally {
            LOGGER.info("End fetching blog hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<FaqHeroSectionResponse>> getFAQHeroSectionDetails() {
        LOGGER.info("Start fetching faq hero section data from repository");

        try {
            List<FaqHeroSectionResponse> faqHeroSectionResponses = heroSectionRepository.getFAQHeroSectionDetails();

            if (faqHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No faq hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No faq hero section data found");
            }

            List<FaqHeroSectionResponse> faqHeroSectionResponseList = faqHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (faqHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active faq hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active faq hero section data found");
            }

            LOGGER.info("Fetched {} active faq hero section data successfully", faqHeroSectionResponseList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            faqHeroSectionResponseList,
                            Instant.now());

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active faq hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch faq hero section data from database");
        } finally {
            LOGGER.info("End fetching faq hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<TourHeroSectionResponse>> getTourHeroSectionDetails() {
        LOGGER.info("Start fetching tour hero section data from repository");

        try {
            List<TourHeroSectionResponse> tourHeroSectionResponses = heroSectionRepository.getTourHeroSectionDetails();

            if (tourHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No tour hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No tour hero section data found");
            }

            List<TourHeroSectionResponse> tourHeroSectionResponseList = tourHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (tourHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active tour hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active tour hero section data found");
            }

            LOGGER.info("Fetched {} active tour hero section data successfully", tourHeroSectionResponseList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    tourHeroSectionResponseList,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active tour hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour hero section data from database");
        } finally {
            LOGGER.info("End fetching tour hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageHeroSectionResponse>> getPackageHeroSectionDetails() {
        LOGGER.info("Start fetching package hero section data from repository");

        try {
            List<PackageHeroSectionResponse> packageHeroSectionResponses = heroSectionRepository.getPackageHeroSectionDetails();

            if (packageHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No package hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No package hero section data found");
            }

            List<PackageHeroSectionResponse> packageHeroSectionResponseList = packageHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (packageHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active package hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active package hero section data found");
            }

            LOGGER.info("Fetched {} active package hero section data successfully", packageHeroSectionResponseList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    packageHeroSectionResponseList,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package hero section data from database");
        } finally {
            LOGGER.info("End fetching package hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<DestinationHeroSectionResponse>> getDestinationHeroSectionDetails() {
        LOGGER.info("Start fetching destination hero section data from repository");

        try {
            List<DestinationHeroSectionResponse> destinationHeroSectionResponses = heroSectionRepository.getDestinationHeroSectionDetails();

            if (destinationHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No destination hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No destination hero section data found");
            }

            List<DestinationHeroSectionResponse> destinationHeroSectionResponseList = destinationHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (destinationHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active destination hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active destination hero section data found");
            }

            LOGGER.info("Fetched {} active destination hero section data successfully", destinationHeroSectionResponseList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    destinationHeroSectionResponseList,
                    Instant.now()
            );

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active destination hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch destination hero section data from database");
        } finally {
            LOGGER.info("End fetching destination hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<ActivityHeroSectionResponse>> getActivityHeroSectionDetails() {
        LOGGER.info("Start fetching activity hero section data from repository");

        try {
            List<ActivityHeroSectionResponse> activityHeroSectionResponses = heroSectionRepository.getActivityHeroSectionDetails();

            if (activityHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No activity hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No activity hero section data found");
            }

            List<ActivityHeroSectionResponse> activityHeroSectionResponseList = activityHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (activityHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active activity hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No active activity hero section data found");
            }

            LOGGER.info("Fetched {} active activity hero section data successfully", activityHeroSectionResponseList.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    activityHeroSectionResponseList,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active activity hero section data : {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activity hero section data from database");
        } finally {
            LOGGER.info("End fetching activity hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageScheduleHeroSectionResponse>> getPackageScheduleHeroSectionDetails(Long packageScheduleId) {
        LOGGER.info("Start fetching package schedule hero section data from repository");

        try {
            List<PackageScheduleHeroSectionResponse> packageScheduleHeroSectionResponses =
                    heroSectionRepository.getPackageScheduleHeroSectionDetails(packageScheduleId);

            if (packageScheduleHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No package schedule hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No package schedule hero section data found");
            }

            LOGGER.info("Fetched {} active package schedule hero section data successfully", packageScheduleHeroSectionResponses.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    packageScheduleHeroSectionResponses,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package schedule hero section data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package schedule hero section data from database");
        } finally {
            LOGGER.info("End fetching package schedule hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<BookedTourHeroSectionResponse>> getBookedTourHeroSectionDetails(Long bookingId) {
        LOGGER.info("Start fetching booked tour hero section data from repository");

        try {
            List<BookedTourHeroSectionResponse> bookedTourHeroSectionResponses =
                    heroSectionRepository.getBookedTourHeroSectionDetails(bookingId);

            if (bookedTourHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No booked tour hero section data found in database");
                throw new DataNotFoundErrorExceptionHandler("No booked tour hero section data found");
            }

            LOGGER.info("Fetched {} active booked tour hero section data successfully", bookedTourHeroSectionResponses.size());

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    bookedTourHeroSectionResponses,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active booked tour hero section data: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch booked tour hero section data from database");
        } finally {
            LOGGER.info("End fetching booked tour hero section data from repository");
        }
    }

    @Override
    public CommonResponse<List<ActivityDetailsHeroSectionResponse>> getActivityHeroSectionDetailsByActivityId(Long activityId) {
        LOGGER.info("Start fetching activity hero section data by activity id : {} from repository", activityId);

        try {
            List<ActivityDetailsHeroSectionResponse> activityDetailsHeroSectionResponses = heroSectionRepository.getActivityHeroSectionDetailsByActivityId(activityId);

            if (activityDetailsHeroSectionResponses.isEmpty()) {
                LOGGER.warn("No activity hero section data by activity id : {} found in database", activityId);
                throw new DataNotFoundErrorExceptionHandler("No activity hero section data by activity id : " + activityId);
            }

            List<ActivityDetailsHeroSectionResponse> activityDetailsHeroSectionResponseList = activityDetailsHeroSectionResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getStatus()))
                    .toList();

            if (activityDetailsHeroSectionResponseList.isEmpty()) {
                LOGGER.warn("No active activity hero section data by activity id : {} found in database", activityId);
                throw new DataNotFoundErrorExceptionHandler("No active activity hero section data by activity id : "+ activityId);
            }

            LOGGER.info("Fetched {} active activity hero section data by activity id : {} successfully",activityDetailsHeroSectionResponseList.size(), activityId);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    activityDetailsHeroSectionResponseList,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active activity hero section data by activity id : {} , {}", activityId, e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch activity hero section data by activity id : " + activityId);
        } finally {
            LOGGER.info("End fetching activity hero section data from by activity id : {} repository", activityId);
        }
    }

}
