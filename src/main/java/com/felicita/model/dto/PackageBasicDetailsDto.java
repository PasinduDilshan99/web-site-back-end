package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageBasicDetailsDto {
    private Long packageId;
    private Date assumeStartDate;
    private Date assumeEndDate;
    private String packageName;
    private String description;
    private Double totalPrice;
    private Double pricePerPerson;
    private Double discountPercentage;
    private String color;
    private String hoverColor;
    private Integer minPersonCount;
    private Integer maxPersonCount;
    private Long tourId;
    private String startLocation;
    private String endLocation;
    private String status;
}
