package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.*;
import com.felicita.repository.UserProfileRepository;
import com.felicita.security.model.CustomUserDetails;
import com.felicita.security.model.User;
import com.felicita.service.CommonService;
import com.felicita.service.UserProfileService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final UserProfileRepository userProfileRepository;
    private final CommonService commonService;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,
                                  CommonService commonService) {
        this.userProfileRepository = userProfileRepository;
        this.commonService = commonService;
    }

    @Override
    public CommonResponse<UserProfileDetailsResponse> getUserProfileDetails(
    ) {
        LOGGER.info("Start fetching user profile details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            UserProfileDetailsResponse userProfileDetailsResponse =
                    userProfileRepository.getUserProfileDetails(new UserProfileDetailsRequest(userId));
            LOGGER.info("Fetched user profile details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileDetailsResponse,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile details from database");
        } finally {
            LOGGER.info("End fetching user profile details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfileSidebarResponse>> getUserProfileSideBar() {
        LOGGER.info("Start fetching user profile side bar from repository");
        try {
            List<UserProfileSidebarResponse> userProfileSidebarResponses =
                    userProfileRepository.getUserProfileSideBar();

            LOGGER.info("Fetched user profile side bar successfully");


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authenticated user");
            }
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            User user = principal.getDomainUser();

            Set<String> userPrivileges = user.getPrivileges()
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            List<UserProfileSidebarResponse> userProfileSidebarResponseList = userProfileSidebarResponses
                    .stream()
                    .filter(data -> data.getStatus().equalsIgnoreCase(CommonStatus.ACTIVE.toString()))
                    .filter(data -> userPrivileges.contains(data.getPrivilegeName().toLowerCase()))
                    .toList();

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileSidebarResponseList,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile side bar: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile side bar details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile side bar details from database");
        } finally {
            LOGGER.info("End fetching user profile side bar details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfileReviewResponse>> getUserProfileReviews() {
        LOGGER.info("Start fetching user profile reviews from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();

            List<UserProfileReviewResponse> userProfileReviewResponses =
                    userProfileRepository.getUserProfileReviews(userId);

            LOGGER.info("Fetched user profile reviews successfully");

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileReviewResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile reviews details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile reviews details from database");
        } finally {
            LOGGER.info("End fetching user profile reviews details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfilePackageReviewResponse>> getUserProfilePackageReviews() {
        LOGGER.info("Start fetching user profile packages reviews from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<UserProfilePackageReviewResponse> userProfilePackageReviewResponses =
                    userProfileRepository.getUserProfilePackageReviews(userId);
            LOGGER.info("Fetched user profile packages reviews successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfilePackageReviewResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile packages reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile packages reviews details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile packages reviews details from database");
        } finally {
            LOGGER.info("End fetching user profile packages reviews details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfileDestinationReviewResponse>> getUserProfileDestiantionsReviews() {
        LOGGER.info("Start fetching user profile destinations reviews from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();

            List<UserProfileDestinationReviewResponse> userProfileDestinationReviewResponses =
                    userProfileRepository.getUserProfileDestiantionsReviews(userId);
            LOGGER.info("Fetched user profile destinations reviews successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileDestinationReviewResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile destinations reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile destinations reviews details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile destinations reviews details from database");
        } finally {
            LOGGER.info("End fetching user profile destinations reviews details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfileActivitesReviewsResponse>> getUserProfileActivitesReviews() {
        LOGGER.info("Start fetching user profile activities reviews from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();

            List<UserProfileActivitesReviewsResponse> userProfileActivitiesReviewsResponses =
                    userProfileRepository.getUserProfileActivitesReviews(userId);
            LOGGER.info("Fetched user profile activities reviews successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileActivitiesReviewsResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile activities reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile activities reviews details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile activities reviews details from database");
        } finally {
            LOGGER.info("End fetching user profile activities reviews details from repository");
        }
    }

    @Override
    public CommonResponse<List<UserProfileTourReviewResponse>> getUserProfileTourReviews() {
        LOGGER.info("Start fetching user profile tour reviews from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();

            List<UserProfileTourReviewResponse> userProfileTourReviewResponses =
                    userProfileRepository.getUserProfileTourReviews(userId);
            LOGGER.info("Fetched user profile tour reviews successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileTourReviewResponses,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile tour reviews: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile tour reviews details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile tour reviews details from database");
        } finally {
            LOGGER.info("End fetching user profile tour reviews details from repository");
        }
    }

    @Override
    public CommonResponse<UserProfileWalletResponse> getUserProfileWalletDetails() {
        LOGGER.info("Start fetching user profile wallet details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            UserProfileWalletResponse userProfileWalletResponse =
                    userProfileRepository.getUserProfileWalletDetails(userId);
            LOGGER.info("Fetched user profile wallet details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            userProfileWalletResponse,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching user profile wallet details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user profile wallet details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile wallet details from database");
        } finally {
            LOGGER.info("End fetching user profile wallet details from repository");
        }
    }

}
