package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityUpdateRequest {
    private Long activityId;
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
    private List<Long> removeImagesIds;
    private List<ActivityImageInsertRequest> addImages;
    private List<ActivityImageUpdateRequest> updatedImages;
    private List<Long> removeRequirementsIds;
    private List<ActivityRequirementInsertRequest> addRequirements;
    private List<ActivityRequirementsUpdateRequest> updatedRequirements;

}
