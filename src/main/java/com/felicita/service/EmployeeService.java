package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.EmployeeGuideResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;

import java.util.List;

public interface EmployeeService {
    CommonResponse<List<EmployeeWithSocialMediaResponse>> getEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeWithSocialMediaResponse>> getALlEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeGuideResponse>> getEmployeeGiudeDetails();
}
