package com.felicita.service;

import com.felicita.model.response.*;

import java.util.List;

public interface EmployeeService {
    CommonResponse<List<EmployeeWithSocialMediaResponse>> getEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeWithSocialMediaResponse>> getALlEmployeeWithSocailMedia();

    CommonResponse<List<EmployeeGuideResponse>> getEmployeeGiudeDetails();

    CommonResponse<TourAssignedEmployeeResponse> getEmployeeAssignToTourId(Long tourId);

    CommonResponse<List<EmployeesForAssignTourResponse>> getEmployeeDetailsForAssignTour();
}
