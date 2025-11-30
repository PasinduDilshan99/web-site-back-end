package com.felicita.validation;

import com.felicita.model.request.EmailVerificationOtpRequest;
import com.felicita.model.request.EmailVerificationRequest;
import com.felicita.model.request.MobileVerificationOtpRequest;
import com.felicita.model.request.MobileVerificationRequest;

public interface AccountSecurityValidationService {
    void validateMobileVerificationRequest(MobileVerificationRequest mobileVerificationRequest);

    void validateEmailVerificationRequest(EmailVerificationRequest emailVerificationRequest);

    void validateMobileVerificationOtpRequest(MobileVerificationOtpRequest mobileVerificationOtpRequest);

    void validateEmailVerificationOtpRequest(EmailVerificationOtpRequest emailVerificationOtpRequest);
}
