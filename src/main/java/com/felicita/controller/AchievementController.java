package com.felicita.controller;

import com.felicita.model.response.AchievementResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.service.AchievementService;
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
@RequestMapping("/api/v0/achievement")
public class AchievementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AchievementController.class);

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<List<AchievementResponse>>> getAchievementDetails() {
        LOGGER.info("{} Start execute get achievements details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<AchievementResponse>> response = achievementService.getAchievementDetails();
        LOGGER.info("{} End execute get achievements details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
