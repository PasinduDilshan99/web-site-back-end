package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CouponAllocationResponse;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.UserProfileTourReviewResponse;
import com.felicita.repository.CouponRepository;
import com.felicita.service.CommonService;
import com.felicita.service.CouponService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository couponRepository;
    private final CommonService commonService;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository,
                             CommonService commonService) {
        this.couponRepository = couponRepository;
        this.commonService =  commonService;
    }

    @Override
    public CommonResponse<List<CouponDetailsResponse>> getAllCouponsDetails() {
        LOGGER.info("Start fetching coupon details from repository");
        try {
            List<CouponDetailsResponse> couponsDetails = couponRepository.getAllCouponsDetails();
            LOGGER.info("Fetched coupon details successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            couponsDetails,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching coupon details : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching coupon details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile coupon details from database");
        } finally {
            LOGGER.info("End fetching coupon details from repository");
        }
    }

    @Override
    public CommonResponse<List<CouponAllocationResponse>> getAllCouponsDetailsByUser() {
        LOGGER.info("Start fetching coupon details from repository for user");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<CouponAllocationResponse> couponAllocationResponseList = couponRepository.getAllCouponsDetailsByUser(userId);
            LOGGER.info("Fetched coupon details successfully for user");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            couponAllocationResponseList,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching coupon details for user: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching coupon details for user: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile coupon details from database for user");
        } finally {
            LOGGER.info("End fetching coupon details from repository for user");
        }
    }

    @Override
    public CommonResponse<CouponDetailsResponse> getCouponDetailsById(String couponId) {
        LOGGER.info("Start fetching coupon details by id from repository");
        try {
            CouponDetailsResponse couponDetailsResponse = couponRepository.getCouponDetailsById(couponId);
            LOGGER.info("Fetched coupon details by id successfully");
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            couponDetailsResponse,
                            Instant.now()
                    )
            );
        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching coupon details by id : {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching coupon details by id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch user profile coupon details by id from database");
        } finally {
            LOGGER.info("End fetching coupon details by id from repository");
        }
    }


}
