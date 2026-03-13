package com.felicita.repository;

import com.felicita.model.response.*;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeWithSocialMediaResponse> getEmployeeWithSocailMedia();

    List<EmployeeWithSocialMediaResponse> getALlEmployeeWithSocailMedia();

    List<EmployeeGuideResponse> getEmployeeGuideDetails();

    TourAssignedEmployeeResponse getEmployeeAssignToTourId(Long tourId);

    List<TourAssignedEmployeeResponse.RelatedOtherTours> getOtherRelatedToursByTourId(Long tourId);

    List<Long> getEmployeeIdsForAssignTour();

    List<EmployeesForAssignTourResponse> getEmployeeDetailsForAssignTour();

    CeoDetailsReponse getCeoDetails();
}
