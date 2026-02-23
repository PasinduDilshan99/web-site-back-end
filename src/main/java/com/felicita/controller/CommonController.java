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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/common")
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    private final CommonService commonService;

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

}
