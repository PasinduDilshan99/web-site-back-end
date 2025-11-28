package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CouponAllocationResponse;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.service.CouponService;
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
@RequestMapping(path = "/api/v0/coupon")
public class CouponController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<List<CouponDetailsResponse>>> getAllCouponsDetails(){
        LOGGER.info("{} Start execute get coupon details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<CouponDetailsResponse>> response = couponService.getAllCouponsDetails();
        LOGGER.info("{} End execute get coupon details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/details/{couponId}")
    public ResponseEntity<CommonResponse<CouponDetailsResponse>> getCouponDetailsById(@PathVariable String couponId){
        LOGGER.info("{} Start execute get coupon details by id {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<CouponDetailsResponse> response = couponService.getCouponDetailsById(couponId);
        LOGGER.info("{} End execute get coupon details by id{}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user-details")
    public ResponseEntity<CommonResponse<List<CouponAllocationResponse>>> getAllCouponsDetailsByUser(){
        LOGGER.info("{} Start execute get coupon details for user {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<CouponAllocationResponse>> response = couponService.getAllCouponsDetailsByUser();
        LOGGER.info("{} End execute get coupon details for user {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
