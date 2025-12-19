package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailsDto {
    private Long packageId;
    private String packageName;
    private String packageDescription;
    private Double totalPrice;
    private Double pricePerPerson;
    private Double discount;
    private String color;
    private String hoverColor;
}
