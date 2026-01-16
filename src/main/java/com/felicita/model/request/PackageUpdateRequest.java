package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageUpdateRequest {
    private Long packageId;
    private PackageBasicDetails packageBasicDetails;

    private List<Long> removedImageIds;
    private List<PackageImageInsertRequest> addImages;
    private List<PackageImageUpdateRequest> updatedImages;

    private List<PackageFeaturesInsertRequest> addFeatures;
    private List<Long> removeFeatureIds;
    private List<PackageFeaturesUpdateRequest> updatedFeatures;

    private List<PackageDayAccommodationInsertRequest> addDayAccommodations;
    private List<Long> removeDayAccommodationIds;
    private List<PackageDayAccommodationUpdateRequest> updatedDayAccommodations;

    private List<PackageInclusionInsertRequest> addInclusions;
    private List<Long> removeInclusionIds;
    private List<PackageInclusionUpdateRequest> updatedInclusions;

    private List<PackageExclusionInsertRequest> addExclusions;
    private List<Long> removeExclusionIds;
    private List<PackageExclusionUpdateRequest> updatedExclusions;

    private List<PackageConditionInsertRequest> addConditions;
    private List<Long> removeConditionIds;
    private List<PackageConditionUpdateRequest> updatedConditions;

    private List<PackageTravelTipInsertRequest> addTravelTips;
    private List<Long> removeTravelTipIds;
    private List<PackageTravelTipUpdateRequest> updatedTravelTips;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PackageBasicDetails{
        private Long packageType;
        private Long tourId;
        private String name;
        private String description;
        private BigDecimal totalPrice;
        private BigDecimal discountPercentage;
        private LocalDate startDate;
        private LocalDate endDate;
        private String color;
        private String status;
        private String hoverColor;
        private Integer minPersonCount;
        private Integer maxPersonCount;
        private BigDecimal pricePerPerson;
    }
}
