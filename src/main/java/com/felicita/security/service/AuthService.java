package com.felicita.security.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.security.model.LoginRequest;
import com.felicita.security.model.LoginResponse;
import com.felicita.security.model.RegisterUser;
import com.felicita.security.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    CommonResponse<String> signup(RegisterUser registerUser);

    CommonResponse<LoginResponse> login(LoginRequest loginRequest, HttpServletResponse response);

    CommonResponse<String> logout(HttpServletRequest request, HttpServletResponse response);

    CommonResponse<User> getCurrentUserProfile();
}
