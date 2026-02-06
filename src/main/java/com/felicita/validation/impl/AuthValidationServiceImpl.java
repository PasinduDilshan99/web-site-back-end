package com.felicita.validation.impl;

import com.felicita.model.request.PasswordChangeRequest;
import com.felicita.model.request.ResetPasswordRequest;
import com.felicita.model.request.SecretQuestionsUpdateRequest;
import com.felicita.validation.AuthValidationService;
import com.felicita.validation.CommonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;

    @Autowired
    public AuthValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }


    @Override
    public void validateResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {

    }

    @Override
    public void validatePasswordChangeRequest(PasswordChangeRequest passwordChangeRequest) {

    }

    @Override
    public void validateSecretQuestionsUpdateRequest(SecretQuestionsUpdateRequest secretQuestionsUpdateRequest) {

    }
}
