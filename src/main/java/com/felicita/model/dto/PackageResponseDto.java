package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponseDto {
    private Long packageId;
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

    // New fields for audit info
    private LocalDateTime createdAt;
    private Integer createdBy;

    // New fields for package type
    private String packageTypeName;
    private String packageTypeDescription;
    private String packageTypeStatus;

    // Related Tour Info
    private Long tourId;
    private String tourName;
    private String tourDescription;
    private Integer duration;
    private Double latitude;
    private Double longitude;
    private String startLocation;
    private String endLocation;
    private String tourStatus;

    private Boolean wish;

    private List<PackageScheduleResponseDto> schedules;
    private List<PackageFeatureResponseDto> features;
    private List<PackageImageResponseDto> images;
}
