package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourInsertRequest {
    private String name;
    private String description;
    private Long tourType;       // FK -> tour_type.id
    private Long tourCategory;   // FK -> tour_category.id
    private Integer duration;       // e.g. days / hours (as per your logic)
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String startLocation;
    private String endLocation;
    private Long season;
    private String status;
    private Long assignTo;
    private String assignMessage;
    private List<TourDestinationInsertRequest> destinations;
    private List<TourImageInsertRequest> images;
    private List<TourInclusionInsertRequest> inclusions;
    private List<TourExclusionInsertRequest> exclusions;
    private List<TourConditionInsertRequest> conditions;
    private List<TourTravelTipInsertRequest> travelTips;

}
