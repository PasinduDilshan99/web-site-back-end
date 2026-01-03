package com.felicita.model.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDestinationExtraPriceDto {

    private Long packageId;
    private Long destinationId;
    private Double extraPrice;
    private String extraPriceNote;
    private String destinationName;
    private String destinationDescription;
}
