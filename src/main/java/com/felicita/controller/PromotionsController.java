package com.felicita.controller;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PromotionTourResponse;
import com.felicita.service.PromotionsService;
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
@RequestMapping(path = "/api/v0/promotions")
public class PromotionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionsController.class);

    private final PromotionsService promotionsService;

    @Autowired
    public PromotionsController(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }


    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPromotions(){
        LOGGER.info("{} Start execute get all tour promotions {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageResponseDto>>> response = promotionsService.getAllPromotions();
        LOGGER.info("{} End execute get all tour promotions {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllActivePromotions(){
        LOGGER.info("{} Start execute get all active tour promotions {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageResponseDto>>> response = promotionsService.getAllActivePromotions();
        LOGGER.info("{} End execute get all active tour promotions {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
