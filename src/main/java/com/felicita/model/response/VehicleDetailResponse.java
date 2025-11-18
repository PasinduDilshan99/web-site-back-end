package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailResponse {
    private Integer vehicleId;
    private String registrationNumber;
    private Integer statusId;
    private String statusName;
    private LocalDate vehiclePurchaseDate;
    private BigDecimal vehiclePurchasePrice;
    private LocalDateTime vehicleCreatedAt;
    private Integer vehicleCreatedBy;
    private LocalDateTime vehicleUpdatedAt;
    private Integer vehicleUpdatedBy;
    private LocalDateTime vehicleTerminatedAt;
    private Integer vehicleTerminatedBy;
    private Integer ownerId;
    private Integer assignedDriverId;

    private VehicleSpecification specification;
    private VehicleDetails details;
    private List<VehicleImage> vehicleImages;
    private List<SpecificationImage> specificationImages;
    private List<Assignment> assignments;
    private List<UsageLog> usageLogs;
    private ServiceHistory latestService;
    private FuelRecord latestFuelRecord;

    // Inner classes for nested objects

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleSpecification {
        private Integer specificationId;
        private String make;
        private String model;
        private Integer vehicleYear;
        private String generation;
        private String bodyType;
        private BigDecimal specificationPrice;
        private String engineType;
        private String engineCapacity;
        private BigDecimal horsepowerHp;
        private BigDecimal torqueNm;
        private Integer transmissionTypeId;
        private String transmissionTypeName;
        private Integer fuelTypeId;
        private String fuelTypeName;
        private BigDecimal electricRangeKm;
        private String drivetrain;
        private BigDecimal topSpeedKmh;
        private BigDecimal acceleration0100;
        private BigDecimal co2EmissionsGkm;
        private Integer doors;
        private Integer seatCapacity;
        private String dimensions;
        private BigDecimal wheelbaseMm;
        private BigDecimal weightKg;
        private String wheelSize;
        private String tireType;
        private String upholsteryType;
        private Integer acTypeId;
        private String acType;
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
        private String specificationImageUrl;
        private Boolean specificationActive;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleDetails {
        private String chassisNumber;
        private String engineNumber;
        private String insurancePolicyNumber;
        private LocalDate insuranceExpiryDate;
        private String emissionTestNumber;
        private LocalDate emissionExpiryDate;
        private String permitNumber;
        private LocalDate permitExpiryDate;
        private LocalDate warrantyExpiryDate;
        private String gpsTrackingId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleImage {
        private Integer vehicleImageId;
        private String vehicleImageUrl;
        private String vehicleImageName;
        private String vehicleImageDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpecificationImage {
        private Integer specificationImageId;
        private String specificationImageUrl;
        private String specificationImageName;
        private String specificationImageDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Assignment {
        private Integer assignmentId;
        private Integer driverId;
        private LocalDate assignmentStartDate;
        private LocalDate assignmentEndDate;
        private String assignmentPurpose;
        private String assignmentRemarks;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsageLog {
        private Integer usageId;
        private Integer packageId;
        private Integer tourId;
        private LocalDateTime usageStartDatetime;
        private LocalDateTime usageEndDatetime;
        private Integer startOdometer;
        private Integer endOdometer;
        private String routeDescription;
        private String usagePurpose;
        private BigDecimal fuelUsedLiters;
        private String usageRemarks;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceHistory {
        private Integer serviceId;
        private LocalDate serviceDate;
        private String serviceCenter;
        private String serviceType;
        private Integer serviceOdometer;
        private BigDecimal serviceCost;
        private String serviceDescription;
        private LocalDate nextServiceDue;
        private String serviceImageUrl;
        private String serviceImageDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FuelRecord {
        private Integer fuelRecordId;
        private LocalDate refuelDate;
        private Integer refuelFuelTypeId;
        private String refuelFuelType;
        private BigDecimal quantityLiters;
        private BigDecimal fuelCost;
        private Integer fuelOdometer;
        private String refuelStation;
    }
}