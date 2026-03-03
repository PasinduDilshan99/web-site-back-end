package com.felicita.controller;

import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.SeasonBasicResponse;
import com.felicita.model.response.SeasonDetailsResponse;
import com.felicita.service.SeasonService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/seasons")
public class SeasonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonController.class);

    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping(path = "/{seasonId}")
    public ResponseEntity<CommonResponse<List<SeasonDetailsResponse>>> getSeasonDetailsBySeasonId(@PathVariable String seasonId) {
        LOGGER.info("{} Start execute get season details by season id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<SeasonDetailsResponse>> response = seasonService.getSeasonDetailsBySeasonId(seasonId);
        LOGGER.info("{} End execute get season details by season id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/basic-details")
    public ResponseEntity<CommonResponse<List<SeasonBasicResponse>>> getActiveSeasonDetails() {
        LOGGER.info("{} Start execute get active season details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<SeasonBasicResponse>> response = seasonService.getActiveSeasonDetails();
        LOGGER.info("{} End execute get active season details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
