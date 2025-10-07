package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanYourTripDestinationsDto {
    private Long destinationId;
    private String destinationName;
    private String destinationDescription;
    private String location;
    private BigDecimal latitude;
    private BigDecimal longitude;

    // Category
    private String categoryName;
    private String categoryDescription;

    // Status
    private String statusName;

    // Activities
    private List<Activity> activities;

    // Images
    private List<DestinationImage> images;

    // Nearby destinations
    private List<NearbyDestination> nearbyDestinations;

    // --- Nested Classes ---
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Activity {
        private Long id;
        private String name;
        private String description;
        private String activitiesCategory;
        private BigDecimal durationHours;
        private LocalTime availableFrom;
        private LocalTime availableTo;
        private BigDecimal priceLocal;
        private BigDecimal priceForeigners;
        private Integer minParticipate;
        private Integer maxParticipate;
        private String season;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DestinationImage {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NearbyDestination {
        private Long id; // nearby_destinations.id
        private Long destinationId; // nearby destination's ID
        private String destinationName;
        private String location;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private BigDecimal distanceKm;
    }
}
