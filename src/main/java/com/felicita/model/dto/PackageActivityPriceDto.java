package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageActivityPriceDto {

    private Long packageId;
    private Long activityId;
    private Double priceForeigners;
    private String name;
    private String description;
}