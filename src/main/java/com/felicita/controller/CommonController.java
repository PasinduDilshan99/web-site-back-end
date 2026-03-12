package com.felicita.controller;

import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.AllCategoriesResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.service.CommonService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v0/common")
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    private final CommonService commonService;

    private final RestTemplate restTemplate = new RestTemplate();


    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping(path = "/all-categories")
    public ResponseEntity<CommonResponse<AllCategoriesResponse>> getAllCategories() {
        LOGGER.info("{} Start execute get all categories {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<AllCategoriesResponse> response = commonService.getAllCategories();
        LOGGER.info("{} End execute get all categories {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/weather")
    public ResponseEntity<CommonResponse<Map<String, Object>>> getWeather(
            @RequestParam(required = true) Double latitude,
            @RequestParam(required = true) Double longitude) {

        LOGGER.info("{} Start fetching weather data {} for lat={}, lon={}", Constant.DOTS, Constant.DOTS, latitude, longitude);

        CommonResponse<Map<String, Object>> response = new CommonResponse<>();

        try {
            String url = String.format(
                    "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true",
                    latitude, longitude);

            Map<String, Object> weatherData = restTemplate.getForObject(url, HashMap.class);

            response.setStatus("success");
            response.setMessage("Weather fetched successfully");
            response.setData(weatherData);

            LOGGER.info("{} Weather data fetched successfully {}", Constant.DOTS, Constant.DOTS);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RestClientException e) {
            LOGGER.error("Weather API error: {}", e.getMessage(), e);

            response.setStatus("error");
            response.setMessage("Failed to fetch weather data");
            response.setData(null);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
