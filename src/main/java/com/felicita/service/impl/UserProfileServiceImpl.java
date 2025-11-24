package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.model.response.UserProfileSidebarResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.repository.UserProfileRepository;
import com.felicita.security.model.CustomUserDetails;
import com.felicita.security.model.User;
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

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public CommonResponse<UserProfileDetailsResponse> getUserProfileDetails(
            UserProfileDetailsRequest userProfileDetailsRequest) {
        LOGGER.info("Start fetching user profile details from repository");
        try {
            UserProfileDetailsResponse userProfileDetailsResponse =
                    userProfileRepository.getUserProfileDetails(userProfileDetailsRequest);
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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authenticated user");
            }
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            User user = principal.getDomainUser();
            String userPrivilege = user.getPrivileges().toString();

            LOGGER.info("Fetching user profile side bar for privilege: {}", userPrivilege);
            List<UserProfileSidebarResponse> userProfileSidebarResponses =
                    userProfileRepository.getUserProfileSideBar();

            LOGGER.info("Fetched user profile side bar successfully, applying filters");

            // Apply both status and privilege filters in a single stream
            List<UserProfileSidebarResponse> filteredResponses =
                    userProfileSidebarResponses
                            .stream()
                            .filter(data -> data.getStatus().equalsIgnoreCase(CommonStatus.ACTIVE.toString()))
                            .filter(data -> data.getPrivilegeName().equalsIgnoreCase(userPrivilege))
                            .toList();

            LOGGER.info("Filtered {} items for user privilege: {}", filteredResponses.size(), userPrivilege);

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            filteredResponses,
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
}
