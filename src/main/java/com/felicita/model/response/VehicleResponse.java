package com.felicita.model.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VehicleResponse {

    // Vehicle basic info
    private Long vehicleId;
    private String registrationNumber;
    private Long specificationId;
    private String status;
    private LocalDate purchaseDate;
    private BigDecimal purchasePrice;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private LocalDateTime terminatedAt;
    private Long terminatedBy;

    private Specification specification;
    private List<VehicleImage> images;
    private List<UsageLog> usageLogs;

    @Data
    public static class Status {
        private Long statusId;
        private String statusName;
    }

    @Data
    public static class Specification {
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
        private Long transmissionTypeId;
        private Long fuelTypeId;
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
        private Long acTypeId;
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
        private Boolean isActive;
        private LocalDateTime createdAt;
        private Long createdBy;
        private LocalDateTime updatedAt;
        private Long updatedBy;
        private LocalDateTime terminatedAt;
        private Long terminatedBy;
    }

    @Data
    public static class VehicleImage {
        private Long imageId;
        private String imageUrl;
        private String imageName;
        private String description;
        private LocalDateTime createdAt;
        private Long createdBy;
        private LocalDateTime updatedAt;
        private Long updatedBy;
        private LocalDateTime terminatedAt;
        private Long terminatedBy;
    }

    @Data
    public static class UsageLog {
        private Long usageId;
        private Long packageId;
        private Long tourId;
        private LocalDateTime startDatetime;
        private LocalDateTime endDatetime;
        private Integer startOdometer;
        private Integer endOdometer;
        private String routeDescription;
        private String purpose;
        private BigDecimal fuelUsedLiters;
        private String remarks;
        private LocalDateTime createdAt;
        private Long createdBy;
        private LocalDateTime updatedAt;
        private Long updatedBy;
        private LocalDateTime terminatedAt;
        private Long terminatedBy;
    }
}
