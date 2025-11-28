package com.felicita.repository;

import com.felicita.model.response.CouponAllocationResponse;
import com.felicita.model.response.CouponDetailsResponse;

import java.util.List;

public interface CouponRepository {
    List<CouponDetailsResponse> getAllCouponsDetails();

    List<CouponAllocationResponse> getAllCouponsDetailsByUser(Long userId);

    CouponDetailsResponse getCouponDetailsById(String couponId);
}
