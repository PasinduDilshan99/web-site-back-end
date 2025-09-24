package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.UserLevelWithBenefitsResponse;
import com.felicita.model.response.UserLevelsResponse;
import com.felicita.service.UserLevelService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/user-level")
public class UserLevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLevelController.class);

    private final UserLevelService userLevelService;

    @Autowired
    public UserLevelController(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllUserLevel(){
        LOGGER.info("{} Start execute get all user levels {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserLevelsResponse>>> response = userLevelService.getAllUserLevel();
        LOGGER.info("{} End execute get all user levels {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<UserLevelsResponse>>> getAllActiveUserLevel(){
        LOGGER.info("{} Start execute get all active user levels {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserLevelsResponse>>> response = userLevelService.getAllActiveUserLevel();
        LOGGER.info("{} End execute get all active user levels {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/all-with-benefits")
    public ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserLevelWithBenefits(){
        LOGGER.info("{} Start execute get all user levels with benefits {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> response = userLevelService.getAllUserLevelWithBenefits();
        LOGGER.info("{} End execute get all user levels with benefits {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-with-benefits")
    public ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> getAllUserActiveLevelWithBenefits(){
        LOGGER.info("{} Start execute get all user active levels with benefits {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<UserLevelWithBenefitsResponse>>> response = userLevelService.getAllUserActiveLevelWithBenefits();
        LOGGER.info("{} End execute get all user active levels with benefits {}", Constant.DOTS, Constant.DOTS);
        return response;
    }



}
