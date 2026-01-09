package com.felicita.repository;

import com.felicita.model.response.EmployeeGuideResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;
import com.felicita.model.response.EmployeesForAssignTourResponse;
import com.felicita.model.response.TourAssignedEmployeeResponse;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeWithSocialMediaResponse> getEmployeeWithSocailMedia();

    List<EmployeeWithSocialMediaResponse> getALlEmployeeWithSocailMedia();

    List<EmployeeGuideResponse> getEmployeeGuideDetails();

    TourAssignedEmployeeResponse getEmployeeAssignToTourId(Long tourId);

    List<TourAssignedEmployeeResponse.RelatedOtherTours> getOtherRelatedToursByTourId(Long tourId);

    List<Long> getEmployeeIdsForAssignTour();

    List<EmployeesForAssignTourResponse> getEmployeeDetailsForAssignTour();
}
