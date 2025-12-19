package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.EmployeeGuideResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;
import com.felicita.model.response.TourAssignedEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    CommonResponse<List<EmployeeWithSocialMediaResponse>> getEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeWithSocialMediaResponse>> getALlEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeGuideResponse>> getEmployeeGiudeDetails();

    CommonResponse<TourAssignedEmployeeResponse> getEmployeeAssignToTourId(Long tourId);
}
