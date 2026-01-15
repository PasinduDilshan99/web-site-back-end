package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityInsertRequest {
    private Long destinationId;
    private String name;
    private String description;
    private String activitiesCategory;
    private BigDecimal durationHours;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private BigDecimal priceLocal;
    private BigDecimal priceForeigners;
    private Integer minParticipate;
    private Integer maxParticipate;
    private String season;
    private String status;

    private List<ActivityImageInsertRequest> images;

    private List<ActivityRequirementInsertRequest> requirements;


}
