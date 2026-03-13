package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendingDestinationResponseDto {
    private int popularId;
    private double rating;
    private int popularity;
    private LocalDateTime popularCreatedAt;

    private int destinationId;
    private String destinationName;
    private String destinationDescription;
    private String location;
    private Double latitude;
    private Double longitude;
    private String destinationStatus;

    List<DestinationCategoryDetailsDto> destinationCategoryDetailsDtos;

    private List<DestinationImageResponseDto> images;
    private List<DestinationActivityResponseDto> activities;
}
