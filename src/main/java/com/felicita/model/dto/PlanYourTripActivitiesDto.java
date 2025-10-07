package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanYourTripActivitiesDto {
    private Integer destinationId;       // d.destination_id
    private String name;                 // a.name
    private String description;          // a.description
    private String activitiesCategory;   // a.activities_category
    private LocalTime availableFrom;     // a.available_from
    private LocalTime availableTo;       // a.available_to
    private BigDecimal durationHours;    // a.duration_hours
    private BigDecimal priceLocal;       // a.price_local
    private BigDecimal priceForeigners;  // a.price_foreigners
    private Integer minParticipate;      // a.min_participate
    private Integer maxParticipate;
}
