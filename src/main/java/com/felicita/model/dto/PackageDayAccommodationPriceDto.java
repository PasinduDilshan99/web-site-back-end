package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDayAccommodationPriceDto {
    private Long packageId;
    private Long packageDayAccommodationId;
    private Integer dayNumber;
    private Long hotelId;
    private String hotelName;
    private Long vehicleId;
    private Double transportPrice;
    private Double localPrice;
    private Double price;
    private Double discount;
    private Double serviceCharge;
    private Double tax;
    private Double extraCharge;
    private String extraChargeNote;
    private String tourName;
    private String tourDescription;
}
