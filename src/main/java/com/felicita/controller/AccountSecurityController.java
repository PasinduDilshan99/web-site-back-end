package com.felicita.controller;

import com.felicita.model.request.EmailVerificationOtpRequest;
import com.felicita.model.request.EmailVerificationRequest;
import com.felicita.model.request.MobileVerificationOtpRequest;
import com.felicita.model.request.MobileVerificationRequest;
import com.felicita.model.response.AccountSecurityDetailsResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.UpdateResponse;
import com.felicita.service.AccountSecurityService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v0/account-security")
public class AccountSecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSecurityController.class);

    private final AccountSecurityService accountSecurityService;

    @Autowired
    public AccountSecurityController(AccountSecurityService accountSecurityService) {
        this.accountSecurityService = accountSecurityService;
    }

    @GetMapping(path = "/details")
    public ResponseEntity<CommonResponse<AccountSecurityDetailsResponse>> getAccountSecurityDetailsById() {
        LOGGER.info("{} Start execute get user account security details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<AccountSecurityDetailsResponse> response = accountSecurityService.getAccountSecurityDetailsById();
        LOGGER.info("{} End execute get user account security details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/mobile-verify")
    public ResponseEntity<CommonResponse<UpdateResponse>> sendOtpToMobileVerification(@RequestBody MobileVerificationOtpRequest mobileVerificationOtpRequest) {
        LOGGER.info("{} Start execute mobile verification send otp {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = accountSecurityService.sendOtpToMobileVerification(mobileVerificationOtpRequest);
        LOGGER.info("{} End execute update mobile verification otp send {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(path = "/mobile-update")
    public ResponseEntity<CommonResponse<UpdateResponse>> updateMobileVerification(@RequestBody MobileVerificationRequest mobileVerificationRequest) {
        LOGGER.info("{} Start execute update mobile verification details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = accountSecurityService.updateMobileVerification(mobileVerificationRequest);
        LOGGER.info("{} End execute update mobile verification details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/email-verify")
    public ResponseEntity<CommonResponse<UpdateResponse>> sendOtpToEmailVerification(@RequestBody EmailVerificationOtpRequest emailVerificationOtpRequest) {
        LOGGER.info("{} Start execute email verification send otp {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = accountSecurityService.sendOtpToEmailVerification(emailVerificationOtpRequest);
        LOGGER.info("{} End execute update email verification otp send {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/email-update")
    public ResponseEntity<CommonResponse<UpdateResponse>> updateEmailVerification(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        LOGGER.info("{} Start execute update email verification details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<UpdateResponse> response = accountSecurityService.updateEmailVerification(emailVerificationRequest);
        LOGGER.info("{} End execute update email verification details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
