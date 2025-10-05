package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDestinationResponseDto {
    private int destinationId;
    private String destinationName;
    private String destinationDescription;
    private String location;
    private String destinationStatus;
}
