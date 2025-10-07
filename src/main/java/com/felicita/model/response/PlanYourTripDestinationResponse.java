package com.felicita.model.response;

import com.felicita.model.dto.PlanYourTripActivitiesDto;
import com.felicita.model.dto.PlanYourTripDestinationDto;
import com.felicita.model.dto.PlanYourTripNearDestinationsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanYourTripDestinationResponse {
    private List<PlanYourTripDestinationDto> planYourTripDestinationDtos;
    private List<PlanYourTripActivitiesDto> planYourTripActivitiesDtos;
    private List<PlanYourTripNearDestinationsDto> planYourTripNearDestinationsDtos;
}
