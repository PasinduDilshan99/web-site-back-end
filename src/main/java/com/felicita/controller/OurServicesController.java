package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurServiceResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.service.OurServicesService;
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
@RequestMapping(path = "/v0/api/our-service")
public class OurServicesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OurServicesController.class);

    private final OurServicesService ourServicesService;

    @Autowired
    public OurServicesController(OurServicesService ourServicesService) {
        this.ourServicesService = ourServicesService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllOurServices(){
        LOGGER.info("{} Start execute get all our services {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<OurServiceResponse>>> response = ourServicesService.getAllOurServices();
        LOGGER.info("{} End execute get all our services {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<CommonResponse<List<OurServiceResponse>>> getAllVisibleOurServices(){
        LOGGER.info("{} Start execute get all visible our services {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<OurServiceResponse>>> response = ourServicesService.getAllVisibleOurServices();
        LOGGER.info("{} End execute get all visible our services {}", Constant.DOTS, Constant.DOTS);
        return response;
    }
}
