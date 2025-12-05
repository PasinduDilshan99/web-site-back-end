package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;

import java.util.List;

public interface EmployeeService {
    CommonResponse<List<EmployeeWithSocialMediaResponse>> getEmployeeWithSocailMedia();
}
