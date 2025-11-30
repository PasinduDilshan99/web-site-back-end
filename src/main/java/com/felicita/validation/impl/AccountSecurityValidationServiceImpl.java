package com.felicita.validation.impl;

import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.model.enums.VerifiedStatus;
import com.felicita.model.request.*;
import com.felicita.model.response.ValidationFailedResponse;
import com.felicita.model.response.ValidationResultResponse;
import com.felicita.model.response.VerificationCompareOtpResponse;
import com.felicita.repository.AccountSecurityRepository;
import com.felicita.service.CommonService;
import com.felicita.service.PreProcessService;
import com.felicita.util.Constant;
import com.felicita.validation.AccountSecurityValidationService;
import com.felicita.validation.CommonValidationService;
import com.felicita.validation.PreProcessForValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountSecurityValidationServiceImpl implements AccountSecurityValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSecurityValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final PreProcessForValidationService preProcessForValidationService;
    private final AccountSecurityRepository accountSecurityRepository;
    private final CommonService commonService;

    @Autowired
    public AccountSecurityValidationServiceImpl(CommonValidationService commonValidationService,
                                                PreProcessForValidationService preProcessForValidationService,
                                                AccountSecurityRepository accountSecurityRepository,
                                                CommonService commonService) {
        this.commonValidationService = commonValidationService;
        this.preProcessForValidationService = preProcessForValidationService;
        this.accountSecurityRepository = accountSecurityRepository;
        this.commonService = commonService;
    }

    @Override
    public void validateMobileVerificationRequest(MobileVerificationRequest mobileVerificationRequest) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse code = commonValidationService.validateNotNullOrEmpty("code", mobileVerificationRequest.getCode());
        if (!code.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(code.getField()).value(code.getMessage()).build());
        }
        ValidationResultResponse whichMobile = commonValidationService.validateNotNullOrEmpty("which mobile", mobileVerificationRequest.getWhichMobile());
        if (!whichMobile.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(whichMobile.getField()).value(whichMobile.getMessage()).build());
        }else if (!mobileVerificationRequest.getWhichMobile().equalsIgnoreCase(Constant.PRIMARY) &&
                !mobileVerificationRequest.getWhichMobile().equalsIgnoreCase(Constant.SECONDARY) ){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("which mobile").value("Not a correct choice").build());
        }
        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : mobileVerificationRequest", validationFailedResponses);
        }
    }

    @Override
    public void validateEmailVerificationRequest(EmailVerificationRequest emailVerificationRequest) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse code = commonValidationService.validateNotNullOrEmpty("code", emailVerificationRequest.getCode());
        if (!code.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(code.getField()).value(code.getMessage()).build());
        }
        ValidationResultResponse whichEmail = commonValidationService.validateNotNullOrEmpty("which email", emailVerificationRequest.getWhichEmail());
        if (!whichEmail.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(whichEmail.getField()).value(whichEmail.getMessage()).build());
        }else if (!emailVerificationRequest.getWhichEmail().equalsIgnoreCase(Constant.PRIMARY) &&
                !emailVerificationRequest.getWhichEmail().equalsIgnoreCase(Constant.SECONDARY) ){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("which email").value("Not a correct choice").build());
        }
        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : emailVerificationRequest", validationFailedResponses);
        }
    }

    @Override
    public void validateMobileVerificationOtpRequest(MobileVerificationOtpRequest mobileVerificationOtpRequest) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse mobileNumber = commonValidationService.validateNotNullOrEmpty("mobile number", mobileVerificationOtpRequest.getMobileNumber());
        if (!mobileNumber.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(mobileNumber.getField()).value(mobileNumber.getMessage()).build());
        }
        ValidationResultResponse whichMobile = commonValidationService.validateNotNullOrEmpty("which mobile", mobileVerificationOtpRequest.getWhichMobile());
        if (!whichMobile.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(whichMobile.getField()).value(whichMobile.getMessage()).build());
        }else if (!mobileVerificationOtpRequest.getWhichMobile().equalsIgnoreCase(Constant.PRIMARY) &&
                !mobileVerificationOtpRequest.getWhichMobile().equalsIgnoreCase(Constant.SECONDARY) ){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("which mobile").value("Not a correct choice").build());
        }
        Long userId = commonService.getUserIdBySecurityContext();
        VerificationCompareOtpResponse verificationCompareOtpResponse = accountSecurityRepository.getMobileVerificationCompareOtpResponse(
                new VerificationCompareOtpRequest(
                        userId,
                        mobileVerificationOtpRequest.getWhichMobile()
        ));
        if(mobileNumber.isValid() && !verificationCompareOtpResponse.getParameter().equalsIgnoreCase(mobileVerificationOtpRequest.getMobileNumber())){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("mobile number").value("Not a correct mobile number").build());
        } else if (verificationCompareOtpResponse.toString().equalsIgnoreCase(VerifiedStatus.VERIFIED.toString())) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field("current status").value("You cant update this mobile").build());
        }
        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : mobileVerificationOtpRequest", validationFailedResponses);
        }
    }

    @Override
    public void validateEmailVerificationOtpRequest(EmailVerificationOtpRequest emailVerificationOtpRequest) {
        List<ValidationFailedResponse> validationFailedResponses = new ArrayList<>();
        ValidationResultResponse email = commonValidationService.validateNotNullOrEmpty("email", emailVerificationOtpRequest.getEmail());
        if (!email.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(email.getField()).value(email.getMessage()).build());
        }
        ValidationResultResponse whichEmail = commonValidationService.validateNotNullOrEmpty("which email", emailVerificationOtpRequest.getWhichEmail());
        if (!whichEmail.isValid()) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field(whichEmail.getField()).value(whichEmail.getMessage()).build());
        }else if (!emailVerificationOtpRequest.getWhichEmail().equalsIgnoreCase(Constant.PRIMARY) &&
                !emailVerificationOtpRequest.getWhichEmail().equalsIgnoreCase(Constant.SECONDARY) ){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("which email").value("Not a correct choice").build());
        }
        Long userId = commonService.getUserIdBySecurityContext();
        VerificationCompareOtpResponse verificationCompareOtpResponse = accountSecurityRepository.getEmailVerificationCompareOtpResponse(
                new VerificationCompareOtpRequest(
                        userId,
                        emailVerificationOtpRequest.getWhichEmail()
                ));
        if(email.isValid() && !verificationCompareOtpResponse.getParameter().equalsIgnoreCase(emailVerificationOtpRequest.getEmail())){
            validationFailedResponses.add(ValidationFailedResponse.builder().field("email").value("Not a correct email").build());
        } else if (verificationCompareOtpResponse.toString().equalsIgnoreCase(VerifiedStatus.VERIFIED.toString())) {
            validationFailedResponses.add(ValidationFailedResponse.builder().field("current status").value("You cant update this email").build());
        }
        LOGGER.info(validationFailedResponses.toString());
        if (!validationFailedResponses.isEmpty()) {
            throw new ValidationFailedErrorExceptionHandler("Validation failed : emailVerificationOtpRequest", validationFailedResponses);
        }
    }
}
