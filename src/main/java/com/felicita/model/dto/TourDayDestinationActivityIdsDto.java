package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDayDestinationActivityIdsDto {
    private Integer day;
    private Integer destinationId;
    private List<Integer> activityIds;
}
