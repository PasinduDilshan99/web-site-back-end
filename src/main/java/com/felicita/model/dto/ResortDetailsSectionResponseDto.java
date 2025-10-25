package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResortDetailsSectionResponseDto {
    private Long resortId;
    private String resortName;
    private String resortDescription;
    private String address;
    private String contactNumber;
    private String email;
    private String websiteUrl;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer starRating;
    private String currencyCode;
    private String cancellationPolicy;
    private Integer totalRooms;
    private Boolean parkingFacility;
    private Boolean wifiAvailable;
    private Boolean petFriendly;
    private String resortType;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String googlePlaceId;
    private List<ResortImage> resortImages;
    private List<DiningOption> diningOptions;
    private List<Accommodation> accommodations;
    private List<Availability> availability;
    private List<ResortFacility> resortFacilities;
    private List<Amenity> amenities;
    private GuestReviews guestReviews;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResortImage {
        private String imageUrl;
        private String caption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiningOption {
        private String mealType;
        private String mealDescription;
        private BigDecimal localPrice;
        private String cuisineType;
        private Boolean available;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Accommodation {
        private String roomType;
        private String roomDescription;
        private Integer capacity;
        private String bedType;
        private BigDecimal localPricePerNight;
        private Boolean hasAirConditioning;
        private Boolean hasTv;
        private Boolean internetAccess;
        private Boolean hasBalcony;
        private String viewType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Availability {
        private String date;
        private Integer availableRooms;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResortFacility {
        private String facilityName;
        private String description;
        private Boolean isAvailable;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amenity {
        private String name;
        private String description;
        private BigDecimal localAdditionalCharge;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GuestReviews {
        private Double averageRating;
        private Long totalReviews;
        private List<RecentReview> recentReviews;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentReview {
        private Integer rating;
        private String comment;
        private String guestName;
    }
}