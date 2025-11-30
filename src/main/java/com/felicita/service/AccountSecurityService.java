package com.felicita.service;

import com.felicita.model.request.EmailVerificationOtpRequest;
import com.felicita.model.request.EmailVerificationRequest;
import com.felicita.model.request.MobileVerificationOtpRequest;
import com.felicita.model.request.MobileVerificationRequest;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UpdateResponse;

public interface AccountSecurityService {
    CommonResponse<AccountSecurityDetailsResponse> getAccountSecurityDetailsById();

    CommonResponse<UpdateResponse> updateMobileVerification(MobileVerificationRequest mobileVerificationRequest);

    CommonResponse<UpdateResponse> updateEmailVerification(EmailVerificationRequest emailVerificationRequest);

    CommonResponse<UpdateResponse> sendOtpToMobileVerification(MobileVerificationOtpRequest mobileVerificationOtpRequest);

    CommonResponse<UpdateResponse> sendOtpToEmailVerification(EmailVerificationOtpRequest emailVerificationOtpRequest);
}
