package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationActivityResponseDto {
    private int activityId;
    private String activityName;
    private String activityDescription;
    private String activitiesCategory;
    private Double durationHours;
    private String availableFrom;
    private String availableTo;
    private Double priceLocal;
    private Double priceForeigners;
    private Integer minParticipate;
    private Integer maxParticipate;
    private String season;
}
