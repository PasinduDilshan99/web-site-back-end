package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UserBenefitResponse;
import com.felicita.model.response.UserLevelDetailsResponse;
import com.felicita.model.response.UserLevelsResponse;
import com.felicita.service.UserBenefitsService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/user-benefits")
public class UserBenefitsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBenefitsController.class);

    private final UserBenefitsService userBenefitsService;

    @Autowired
    public UserBenefitsController(UserBenefitsService userBenefitsService) {
        this.userBenefitsService = userBenefitsService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllUserBenefits(){
        LOGGER.info("{} Start execute get all user benefits {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserBenefitResponse>>> response = userBenefitsService.getAllUserBenefits();
        LOGGER.info("{} End execute get all user benefits {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<UserBenefitResponse>>> getAllActiveUserBenefits(){
        LOGGER.info("{} Start execute get all active user benefits {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserBenefitResponse>>> response = userBenefitsService.getAllActiveUserBenefits();
        LOGGER.info("{} End execute get all active user benefits {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/user-profile")
    public ResponseEntity<CommonResponse<UserLevelDetailsResponse>> getUserBenifitsDetailsForUserId(){
        LOGGER.info("{} Start execute get all user benefits {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UserLevelDetailsResponse> response = userBenefitsService.getUserBenifitsDetailsForUserId();
        LOGGER.info("{} End execute get all user benefits {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
