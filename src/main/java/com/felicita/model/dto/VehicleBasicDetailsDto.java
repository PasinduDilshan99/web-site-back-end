package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleBasicDetailsDto {
    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleMake;
    private String vehicleModel;
}
