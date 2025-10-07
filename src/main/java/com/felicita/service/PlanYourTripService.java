package com.felicita.service;

import com.felicita.model.dto.PlanYourTripDestinationsDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PlanYourTripDestinationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanYourTripService {
    ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getAllDestinationsForPlanYouTrip();

    ResponseEntity<CommonResponse<List<PlanYourTripDestinationsDto>>> getActiveDestinationsForPlanYouTrip();

    ResponseEntity<CommonResponse<PlanYourTripDestinationResponse>> getDestinationsForPlanYourTrip();
}
