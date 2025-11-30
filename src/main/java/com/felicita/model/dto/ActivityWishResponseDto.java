package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityWishResponseDto {
    private Long activityId;
    private String activityName;
    private String activityDescription;
    private String activitiesCategory;
    private String season;
    private List<String> activityImages;
    private String activityUrl;
    private Double activityDuration;
    private String status;
    private String createdAt;
}
