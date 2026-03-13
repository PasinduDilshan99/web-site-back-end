package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleSpecificationDetailsResponse {

    private Long specificationId;
    private String make;
    private String model;
    private Integer year;
    private String generation;
    private String bodyType;
    private BigDecimal price;
    private String engineType;
    private String engineCapacity;
    private BigDecimal horsepowerHp;
    private BigDecimal torqueNm;
    private BigDecimal electricRangeKm;
    private String drivetrain;
    private BigDecimal topSpeedKmh;
    private BigDecimal acceleration0To100;
    private BigDecimal co2EmissionsGKm;
    private Integer doors;
    private Integer seatCapacity;
    private String dimensions;
    private BigDecimal wheelbaseMm;
    private BigDecimal weightKg;
    private String wheelSize;
    private String tireType;
    private String upholsteryType;
    private String sunroofType;
    private String cruiseControlType;
    private String entertainmentFeatures;
    private String comfortFeatures;
    private Integer ncapSafetyRating;
    private Integer airbagsCount;
    private String parkingCamera;
    private Boolean laneDepartureWarning;
    private String safetyFeatures;
    private BigDecimal fuelTankCapacityLiters;
    private Integer warrantyYears;
    private String imageUrl;
    private Boolean airCondition;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Transmission transmission;
    private FuelType fuelType;
    private AirConditioningType airConditioningType;

    private List<VehicleImage> images;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transmission {
        private Integer transmissionTypeId;
        private String transmissionTypeName;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FuelType {
        private Integer fuelTypeId;
        private String fuelTypeName;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AirConditioningType {
        private Integer acTypeId;
        private String acTypeName;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleImage {
        private Long imageId;
        private String imageUrl;
        private String imageName;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
