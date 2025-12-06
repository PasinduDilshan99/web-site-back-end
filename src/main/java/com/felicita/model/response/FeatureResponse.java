package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureResponse {

    private Long featureId;
    private String featureName;
    private String iconUrl;
    private String color;
    private String hoverColor;
    private String description;
    private Integer statusId;
    private String statusName;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private LocalDateTime terminatedAt;
    private Long terminatedBy;
}
