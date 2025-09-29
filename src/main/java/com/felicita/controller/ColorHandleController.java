package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PageColorsResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.ColorHandleService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/colors")
public class ColorHandleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColorHandleController.class);

    private final ColorHandleService colorHandleService;

    @Autowired
    public ColorHandleController(ColorHandleService colorHandleService) {
        this.colorHandleService = colorHandleService;
    }

    @GetMapping(path = "/{pageName}")
    public ResponseEntity<CommonResponse<PageColorsResponse>> getHomePageColors(@PathVariable String pageName){
        LOGGER.info("{} Start execute get home page colors {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<PageColorsResponse>> response = colorHandleService.getHomePageColors(pageName);
        LOGGER.info("{} End execute get home page colors {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
