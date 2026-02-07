package com.felicita.controller;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.request.UserUpdateRequest;
import com.felicita.model.response.*;
import com.felicita.service.UserProfileService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/user-profile")
public class UserProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileController.class);

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(path = "/update-account")
    public ResponseEntity<CommonResponse<UpdateResponse>> updateUserProfileDetails(@RequestBody UserUpdateRequest userUpdateRequest) {
        LOGGER.info("{} Start execute update user profile details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = userProfileService.updateUserProfileDetails(userUpdateRequest);
        LOGGER.info("{} End execute update user profile details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/side-bar")
    public ResponseEntity<CommonResponse<List<UserProfileSidebarResponse>>> getUserProfileSideBar() {
        LOGGER.info("{} Start execute get user profile side bar details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileSidebarResponse>> response = userProfileService.getUserProfileSideBar();
        LOGGER.info("{} End execute get user profile side bar details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<CommonResponse<UserProfileDetailsResponse>> getUserProfileDetails() {
        LOGGER.info("{} Start execute get user profile details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UserProfileDetailsResponse> response = userProfileService.getUserProfileDetails();
        LOGGER.info("{} End execute get user profile details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews")
    public ResponseEntity<CommonResponse<List<UserProfileReviewResponse>>> getUserProfileReviews() {
        LOGGER.info("{} Start execute get user profile reviews details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileReviewResponse>> response = userProfileService.getUserProfileReviews();
        LOGGER.info("{} End execute get user profile reviews details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/package-reviews")
    public ResponseEntity<CommonResponse<List<UserProfilePackageReviewResponse>>> getUserProfilePackageReviews() {
        LOGGER.info("{} Start execute get user profile package reviews details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfilePackageReviewResponse>> response = userProfileService.getUserProfilePackageReviews();
        LOGGER.info("{} End execute get user profile package reviews details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/destination-reviews")
    public ResponseEntity<CommonResponse<List<UserProfileDestinationReviewResponse>>> getUserProfileDestiantionsReviews() {
        LOGGER.info("{} Start execute get user profile destinations reviews details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileDestinationReviewResponse>> response = userProfileService.getUserProfileDestiantionsReviews();
        LOGGER.info("{} End execute get user profile destinations reviews details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/activity-reviews")
    public ResponseEntity<CommonResponse<List<UserProfileActivitesReviewsResponse>>> getUserProfileActivitesReviews() {
        LOGGER.info("{} Start execute get user profile activities reviews details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileActivitesReviewsResponse>> response = userProfileService.getUserProfileActivitesReviews();
        LOGGER.info("{} End execute get user profile activities reviews details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tour-reviews")
    public ResponseEntity<CommonResponse<List<UserProfileTourReviewResponse>>> getUserProfileTourReviews() {
        LOGGER.info("{} Start execute get user profile tour reviews details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileTourReviewResponse>> response = userProfileService.getUserProfileTourReviews();
        LOGGER.info("{} End execute get user profile tour reviews details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/wallet")
    public ResponseEntity<CommonResponse<UserProfileWalletResponse>> getUserProfileWalletDetails() {
        LOGGER.info("{} Start execute get user profile wallet details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UserProfileWalletResponse> response = userProfileService.getUserProfileWalletDetails();
        LOGGER.info("{} End execute get user profile wallet details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}











