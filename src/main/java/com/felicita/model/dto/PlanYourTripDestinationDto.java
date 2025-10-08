package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanYourTripDestinationDto {
    private long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
