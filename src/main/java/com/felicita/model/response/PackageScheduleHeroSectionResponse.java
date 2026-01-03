package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageScheduleHeroSectionResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String color;
}
