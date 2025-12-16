package com.felicita.controller;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.response.*;
import com.felicita.service.PackageService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPackages() {
        LOGGER.info("{} Start execute get all package {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageResponseDto>>> response = packageService.getAllPackages();
        LOGGER.info("{} End execute get all package {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getActivePackages() {
        LOGGER.info("{} Start execute get all active package {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<PackageResponseDto>>> response = packageService.getActivePackages();
        LOGGER.info("{} End execute get all active package {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/packages")
    public ResponseEntity<CommonResponse<PackageWithParamsResponse>> getPackagesWithParams(@RequestBody PackageDataRequest packageDataRequest) {
        LOGGER.info("{} Start execute get active package with params {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<PackageWithParamsResponse> response = packageService.getPackagesWithParams(packageDataRequest);
        LOGGER.info("{} End execute get active packages with params {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{packageId}")
    public ResponseEntity<CommonResponse<PackageResponseDto>> getPackageDetailsById(@PathVariable String packageId) {
        LOGGER.info("{} Start execute get package details by id  {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<PackageResponseDto> response = packageService.getPackageDetailsById(packageId);
        LOGGER.info("{} End execute get package details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews")
    public ResponseEntity<CommonResponse<List<PackageReviewResponse>>> getAllPackageReviewDetails() {
        LOGGER.info("{} Start execute get all package review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageReviewResponse>> response = packageService.getAllPackageReviewDetails();
        LOGGER.info("{} End execute get all package review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reviews/{packageId}")
    public ResponseEntity<CommonResponse<List<PackageReviewResponse>>> getPackageReviewDetailsById(@PathVariable String packageId) {
        LOGGER.info("{} Start execute get all package review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageReviewResponse>> response = packageService.getPackageReviewDetailsById(packageId);
        LOGGER.info("{} End execute get all package review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history")
    public ResponseEntity<CommonResponse<List<PackageHistoryDetailsResponse>>> getAllPackageHistoryDetails() {
        LOGGER.info("{} Start execute get all packages review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHistoryDetailsResponse>> response = packageService.getAllPackageHistoryDetails();
        LOGGER.info("{} End execute get all packages review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history/{packageId}")
    public ResponseEntity<CommonResponse<List<PackageHistoryDetailsResponse>>> getPackageHistoryDetailsById(@PathVariable String packageId) {
        LOGGER.info("{} Start execute get all packages review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHistoryDetailsResponse>> response = packageService.getPackageHistoryDetailsById(packageId);
        LOGGER.info("{} End execute get all packages review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images")
    public ResponseEntity<CommonResponse<List<PackageHistoryImageResponse>>> getAllPackageHistoryImages() {
        LOGGER.info("{} Start execute get all packages review details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHistoryImageResponse>> response = packageService.getAllPackageHistoryImages();
        LOGGER.info("{} End execute get all packages review details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/history-images/{packageId}")
    public ResponseEntity<CommonResponse<List<PackageHistoryImageResponse>>> getPackageHistoryImagesById(@PathVariable String packageId) {
        LOGGER.info("{} Start execute get all packages review details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<PackageHistoryImageResponse>> response = packageService.getPackageHistoryImagesById(packageId);
        LOGGER.info("{} End execute get all packages review details by id {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
