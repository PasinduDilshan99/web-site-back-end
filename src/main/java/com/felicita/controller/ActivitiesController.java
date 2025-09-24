package com.felicita.controller;

import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.ActivityResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.ActivitiesService;
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
@RequestMapping(path = "api/v0/activities")
public class ActivitiesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesController.class);

    private final ActivitiesService activitiesService;

    @Autowired
    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<ActivityResponse>>> getAllActivities(){
        LOGGER.info("{} Start execute get all activities {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityResponse>>> response = activitiesService.getAllActivities();
        LOGGER.info("{} End execute get all activities {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<ActivityResponse>>> getActiveActivities(){
        LOGGER.info("{} Start execute get all active activities {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityResponse>>> response = activitiesService.getActiveActivities();
        LOGGER.info("{} End execute get all active activities {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/category")
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActivityCategories(){
        LOGGER.info("{} Start execute get activity categories {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> response = activitiesService.getActivityCategories();
        LOGGER.info("{} End execute get activity categories {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-category")
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> getActiveActivityCategories(){
        LOGGER.info("{} Start execute get activity categories {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityCategoryResponse>>> response = activitiesService.getActiveActivityCategories();
        LOGGER.info("{} End execute get activity categories {}", Constant.DOTS, Constant.DOTS);
        return response;
    }


}
