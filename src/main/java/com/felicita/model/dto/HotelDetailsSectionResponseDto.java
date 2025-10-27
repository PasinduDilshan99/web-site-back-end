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
public class HotelDetailsSectionResponseDto {
    private Long hotelId;
    private String hotelName;
    private String hotelDescription;
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
    private String hotelType;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String googlePlaceId;
    private List<HotelImage> hotelImages;
    private List<Meal> meals;
    private List<Room> rooms;
    private List<RoomAvailability> roomAvailability;
    private ReviewSummary reviews;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HotelImage {
        private String imageUrl;
        private String caption;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meal {
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
    public static class Room {
        private String roomType;
        private String roomDescription;
        private Integer capacity;
        private String bedType;
        private BigDecimal localPricePerNight;
        private Boolean hasAirConditioning;
        private Boolean hasTv;
        private Boolean internetAccess;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomAvailability {
        private String date; // Using String for simplicity, can be LocalDate
        private Integer availableRooms;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewSummary {
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
    }
}
