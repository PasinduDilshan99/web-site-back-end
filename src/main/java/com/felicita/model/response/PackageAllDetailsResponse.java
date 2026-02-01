package com.felicita.model.response;

import com.felicita.model.dto.PackageFeatureResponseDto;
import com.felicita.model.dto.PackageImageResponseDto;
import com.felicita.model.dto.PackageScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageAllDetailsResponse {
    private Long packageId;
    private String packageName;
    private String packageDescription;
    private BigDecimal totalPrice;
    private BigDecimal discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String color;
    private String hoverColor;
    private Integer minPersonCount;
    private Integer maxPersonCount;
    private BigDecimal pricePerPerson;
    private String packageStatus;
    private String packageTypeName;
    private String startLocation;
    private Integer duration;

    private Long tourId;
    private String tourName;

    private List<PackageFeatureResponseDto> packageFeatures;
    private List<PackageImageResponseDto> packageImages;

    private List<PackageExtrasResponse.PackageInclusion> inclusions;
    private List<PackageExtrasResponse.PackageExclusion> exclusions;
    private List<PackageExtrasResponse.PackageCondition> conditions;
    private List<PackageExtrasResponse.PackageTravelTip> travelTips;

    private PackageDayAccommodationResponse dayAccommodationResponses;
}
