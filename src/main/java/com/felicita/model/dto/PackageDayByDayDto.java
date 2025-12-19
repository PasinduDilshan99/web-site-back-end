package com.felicita.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDayByDayDto {
    @JsonIgnore
    private Long packageId;
    private Long packageDayAccommodationId;
    private Integer dayNumber;
    private Boolean breakfast;
    private String breakfastDescription;
    private Boolean lunch;
    private String lunchDescription;
    private Boolean dinner;
    private String dinnerDescription;
    private Boolean morningTea;
    private String morningTeaDescription;
    private Boolean eveningTea;
    private String eveningTeaDescription;
    private Boolean snacks;
    private String snackNote;
    private String otherNotes;
    private Long hotelId;
    private String hotelName;
    private String hotelDescription;
    private String hotelWebsite;
    private Integer hotelCategory;
    private String hotelType;
    private String hotelLocation;
    private Double hotelLatitude;
    private Double hotelLongitude;
    private Long transportId;
    private String vehicleRegistrationNumber;
    private String vehicleTypeName;
    private String vehicleModel;
    private Integer seatCapacity;
    private Boolean airCondition;
}
