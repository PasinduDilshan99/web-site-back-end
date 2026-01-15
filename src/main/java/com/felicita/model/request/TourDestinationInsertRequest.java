package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDestinationInsertRequest {
    private Long destinationId;
    private Long activityId;
    private Integer dayNumber;
}
