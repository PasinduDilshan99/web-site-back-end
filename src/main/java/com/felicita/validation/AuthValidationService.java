package com.felicita.validation;

import com.felicita.model.request.PasswordChangeRequest;
import com.felicita.model.request.ResetPasswordRequest;
import com.felicita.model.request.SecretQuestionsUpdateRequest;

public interface AuthValidationService {
    void validateResetPasswordRequest(ResetPasswordRequest resetPasswordRequest);

    void validatePasswordChangeRequest(PasswordChangeRequest passwordChangeRequest);

    void validateSecretQuestionsUpdateRequest(SecretQuestionsUpdateRequest secretQuestionsUpdateRequest);
}
