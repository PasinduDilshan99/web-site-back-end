package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDetailsHeroSectionResponse {
    private Long activityId;
    private Long imageId;
    private String name;
    private String imageUrl;
    private String description;
    private String status;
}