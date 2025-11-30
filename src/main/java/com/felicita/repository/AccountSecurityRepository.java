package com.felicita.repository;

import com.felicita.model.enums.VerifiedStatus;
import com.felicita.model.request.EmailVerificationRequest;
import com.felicita.model.request.MobileVerificationRequest;
import com.felicita.model.request.VerificationCompareOtpRequest;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.VerificationCompareOtpResponse;
import com.felicita.model.response.VerificationCompareResponse;

public interface AccountSecurityRepository {
    AccountSecurityDetailsResponse getAccountSecurityDetailsById(Long userId);

    VerificationCompareResponse getRealMobileVerificationCode(Long userId, String whichMobile);

    void updateMobileVerification(MobileVerificationRequest mobileVerificationRequest, Long userId, String verifiedStatus);

    VerificationCompareResponse getRealEmailVerificationCode(Long userId, String whichEmail);

    void updateEmailVerification(EmailVerificationRequest emailVerificationRequest, Long userId, String verifiedStatus);

    VerificationCompareOtpResponse getMobileVerificationCompareOtpResponse(VerificationCompareOtpRequest verificationCompareOtpRequest);

    void insertRandomOtpToMobileVerification(Long userId, String whichMobile, String randomOtp);

    VerificationCompareOtpResponse getEmailVerificationCompareOtpResponse(VerificationCompareOtpRequest verificationCompareOtpRequest);

    void insertRandomOtpToEmailVerification(Long userId, String whichEmail, String randomOtp);
}
