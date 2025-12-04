package com.felicita.controller;

import com.felicita.model.response.AboutUsHeroSectionResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.StatisticsResponse;
import com.felicita.service.StatisticsService;
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
@RequestMapping(path = "/api/v0/statistics")
public class StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(path = "/about-us")
    public ResponseEntity<CommonResponse<List<StatisticsResponse>>> getAboutUsStatisticsDetails(){
        LOGGER.info("{} Start execute get all about us statistics data {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<StatisticsResponse>> response = statisticsService.getAboutUsStatisticsDetails();
        LOGGER.info("{} End execute get all about us statistics data {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
