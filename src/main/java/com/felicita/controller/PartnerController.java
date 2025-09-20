package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.HeroSectionResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.HeroSectionService;
import com.felicita.service.PartnerService;
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
@RequestMapping(path = "/v0/api/partner")
public class PartnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);

    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllPartners(){
        LOGGER.info("{} Start execute get all partners {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PartnerResponse>>> response = partnerService.getAllPartners();
        LOGGER.info("{} End execute get all partners {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<PartnerResponse>>> getAllVisiblePartners(){
        LOGGER.info("{} Start execute get all visible partners {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PartnerResponse>>> response = partnerService.getAllVisiblePartners();
        LOGGER.info("{} End execute get all visible partners {}", Constant.DOTS, Constant.DOTS);
        return response;
    }
}
