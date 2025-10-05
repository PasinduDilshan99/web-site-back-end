package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationResponseDto {
    private int destinationId;
    private String destinationName;
    private String destinationDescription;
    private String location;
    private Double latitude;
    private Double longitude;

    private String categoryName;
    private String categoryDescription;
    private String statusName;

    private List<DestinationActivityResponseDto> activities;
    private List<DestionationImageResponseDto> images;
}
