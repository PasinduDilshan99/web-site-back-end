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
public class TourResponseDto {
    private Integer tourId;
    private String tourName;
    private String tourDescription;
    private String tourType;
    private String tourCategory;
    private Integer durationDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startLocation;
    private String endLocation;
    private Integer maxPeople;
    private Integer minPeople;
    private BigDecimal pricePerPerson;
    private String tourStatus;
    private List<TourImageDto> tourImages;
    private List<Integer> destinations;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
