package com.felicita.repository;

import com.felicita.model.dto.PlanYourTripActivitiesDto;
import com.felicita.model.dto.PlanYourTripDestinationDto;
import com.felicita.model.dto.PlanYourTripDestinationsDto;
import com.felicita.model.dto.PlanYourTripNearDestinationsDto;

import java.util.List;

public interface PlanYourTripRepository {
    List<PlanYourTripDestinationsDto> getAllDestinationsForPlanYouTrip();

    List<PlanYourTripDestinationDto> getAllPlanYourTripDestination();

    List<PlanYourTripActivitiesDto> getAllPlanYourTripActivitiesDtos();

    List<PlanYourTripNearDestinationsDto> getAllPlanYourTripNearDestinationsDtos();
}
