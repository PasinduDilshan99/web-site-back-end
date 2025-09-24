package com.felicita.controller;

import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PackageDetailsResponse;
import com.felicita.service.BlogService;
import com.felicita.service.PackageService;
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
@RequestMapping(path = "/v0/api/package")
public class PackageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageController.class);

    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> getAllPackages() {
        LOGGER.info("{} Start execute get all packages {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> response = packageService.getAllPackages();
        LOGGER.info("{} End execute get all packages {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> getAllActivePackages() {
        LOGGER.info("{} Start execute get all active packages {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> response = packageService.getAllActivePackages();
        LOGGER.info("{} End execute get all active packages {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
