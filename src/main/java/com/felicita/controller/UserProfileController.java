package com.felicita.controller;

import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.model.response.UserProfileSidebarResponse;
import com.felicita.model.response.VehicleResponse;
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

    @GetMapping(path = "/side-bar")
    public ResponseEntity<CommonResponse<List<UserProfileSidebarResponse>>> getUserProfileSideBar(){
        LOGGER.info("{} Start execute get user profile side bar details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserProfileSidebarResponse>> response = userProfileService.getUserProfileSideBar();
        LOGGER.info("{} End execute get user profile side bar details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<CommonResponse<UserProfileDetailsResponse>> getUserProfileDetails(@RequestBody UserProfileDetailsRequest userProfileDetailsRequest){
        LOGGER.info("{} Start execute get user profile details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UserProfileDetailsResponse> response = userProfileService.getUserProfileDetails(userProfileDetailsRequest);
        LOGGER.info("{} End execute get user profile details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
