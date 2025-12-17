package com.felicita.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felicita.model.dto.DestinationCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDetailsWithDayToDayResponse {

    /** Day number (1, 2, 3...) */
    private Integer dayNumber;
    private Accommodations accommodations;
    /** Destinations for the given day */
    private List<DestinationPerDayResponse> destinations;

    // ================= DESTINATION PER DAY =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestinationPerDayResponse {

        private DestinationDetailsPerDay destination;
        private List<ActivityPerDayResponse> activities;
    }

    // ================= DESTINATION DETAILS =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestinationDetailsPerDay {

        private Long destinationId;
        private String destinationName;
        private String destinationDescription;
        private String destinationStatus;
        private String category;
        private String categoryDescription;
        private String location;
        private Double latitude;
        private Double longitude;
        private LocalDateTime createdAt;
        private String createdBy;
        private String createrImageUrl;
        private LocalDateTime updatedAt;
        private String updatedBy;
        private String updaterImageUrl;


        private List<DestinationImagePerDay> images;
    }

    // ================= DESTINATION IMAGES =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestinationImagePerDay {

        private Integer imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String imageStatus;
    }

    // ================= ACTIVITIES =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityPerDayResponse {

        private Long id;
        private Long destinationId;
        private String name;
        private String description;
        private String activitiesCategory;

        private BigDecimal durationHours;
        private Time availableFrom;
        private Time availableTo;

        private BigDecimal priceLocal;
        private BigDecimal priceForeigners;

        private Integer minParticipate;
        private Integer maxParticipate;

        private String season;
        private String status;

        private Timestamp createdAt;
        private Timestamp updatedAt;

        private String categoryName;
        private String categoryDescription;

        private List<ActivityRequirementPerDay> requirements;
        private List<ActivityImagePerDay> images;
    }

    // ================= ACTIVITY REQUIREMENTS =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityRequirementPerDay {

        private Long id;
        private String name;
        private String value;
        private String description;
        private String color;
        private Integer status;
    }

    // ================= ACTIVITY IMAGES =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityImagePerDay {

        private Long id;
        private String name;
        private String description;
        @JsonProperty("image_url")
        private String imageUrl;
        private Integer status;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Accommodations {
        private Integer day;
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
        private HotelAccommodation hotel;
        private TransportAccommodation transport;
        private String otherNotes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotelAccommodation {

        private Long hotelId;
        private String hotelName;
        private String hotelType;       // Resort, Villa, Hotel
        private String hotelCategory;   // 3 Star, 4 Star, Luxury
        private Double longitude;
        private Double latitude;
        private String location;
        private String description;
        private List<String> facilities; // WiFi, Pool, AC, Gym
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TransportAccommodation {
        private Long transportId;
        private String transportType;   // Car, Van, Bus, Train
        private String vehicleModel;
        private Integer seatCount;
        private Boolean airConditioned;
        private Boolean driverIncluded;
        private Boolean fuelIncluded;
        private String description;
    }


}
