package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CouponAllocationResponse;
import com.felicita.model.response.CouponDetailsResponse;

import java.util.List;

public interface CouponService {
    CommonResponse<List<CouponDetailsResponse>> getAllCouponsDetails();

    CommonResponse<List<CouponAllocationResponse>> getAllCouponsDetailsByUser();

    CommonResponse<CouponDetailsResponse> getCouponDetailsById(String couponId);
}
