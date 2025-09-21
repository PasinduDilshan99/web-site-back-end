package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    private Integer tourId;
    private String tourName;
    private String tourDescription;
    private LocalDate tourStartDate;
    private LocalDate tourEndDate;
    private Integer durationDays;
    private Integer maxPeople;
    private Integer minPeople;
    private BigDecimal pricePerPerson;
    private String tourStatus;
}
