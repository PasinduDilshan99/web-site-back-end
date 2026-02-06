package com.felicita.security.service;

import com.felicita.model.request.PasswordChangeRequest;
import com.felicita.model.request.ResetPasswordRequest;
import com.felicita.model.request.SecretQuestionsUpdateRequest;
import com.felicita.model.request.UsernamePasswordValidationRequest;
import com.felicita.model.response.*;
import com.felicita.security.model.LoginRequest;
import com.felicita.security.model.LoginResponse;
import com.felicita.security.model.RegisterUser;
import com.felicita.security.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface AuthService {
    CommonResponse<String> signup(RegisterUser registerUser);

    CommonResponse<LoginResponse> login(LoginRequest loginRequest, HttpServletResponse response);

    CommonResponse<String> logout(HttpServletRequest request, HttpServletResponse response);

    CommonResponse<User> getCurrentUserProfile();

    CommonResponse<ResetPasswordResponse> resetPassword(ResetPasswordRequest resetPasswordRequest);

    CommonResponse<PasswordChangeResponse> changePassword(PasswordChangeRequest passwordChangeRequest);

    CommonResponse<UpdateResponse> updateSecretQuestions(SecretQuestionsUpdateRequest secretQuestionsUpdateRequest);

    CommonResponse<List<SecretQuestionResponse>> getActiveScretQuestions();

    CommonResponse<Boolean> usernamePasswordValidation(UsernamePasswordValidationRequest usernamePasswordValidationRequest);
}
