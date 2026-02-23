package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationsForTourMapDto {
    private Long destinationId;
    private String destinationName;
    private String destinationDescription;
    private String destinationStatus;
    private List<String> destinationCategories;
    private String destinationLocation;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private LocalDateTime destinationCreatedAt;
    private Long destinationCreatedBy;

    private List<DestinationImagesForTourMapDto> destinationImagesForTourMapDtos;
    private List<DestinationCategoryImageForTourMapDto> destinationCategoryImageForTourMapDtos;

}
