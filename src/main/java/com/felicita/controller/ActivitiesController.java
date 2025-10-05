package com.felicita.controller;

import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
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
    public ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getAllActivities(){
        LOGGER.info("{} Start execute get all activities {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityResponseDto>>> response = activitiesService.getAllActivities();
        LOGGER.info("{} End execute get all activities {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<ActivityResponseDto>>> getActiveActivities(){
        LOGGER.info("{} Start execute get active activities {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityResponseDto>>> response = activitiesService.getActiveActivities();
        LOGGER.info("{} End execute get active activities {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/category")
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getAllActivityCategories(){
        LOGGER.info("{} Start execute get activities categories {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> response = activitiesService.getAllActivityCategories();
        LOGGER.info("{} End execute get activities categories {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active-category")
    public ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> getActiveActivityCategories(){
        LOGGER.info("{} Start execute get active activities categories {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ActivityCategoryResponseDto>>> response = activitiesService.getActiveActivityCategories();
        LOGGER.info("{} End execute get active activities categories {}", Constant.DOTS, Constant.DOTS);
        return response;
    }


}
