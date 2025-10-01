package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponseDto {
    private int packageId;
    private String packageName;
    private String packageDescription;
    private BigDecimal totalPrice;
    private BigDecimal discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String color;
    private String hoverColor;
    private Integer minPersonCount;
    private Integer maxPersonCount;
    private BigDecimal pricePerPerson;
    private String packageStatus;

    // Related Tour Info
    private int tourId;
    private String tourName;
    private String tourDescription;
    private Integer duration;
    private Double latitude;
    private Double longitude;
    private String startLocation;
    private String endLocation;
    private String tourStatus;

    private List<PackageScheduleResponseDto> schedules;
    private List<PackageFeatureResponseDto> features;
    private List<PackageImageResponseDto> images;
}
