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

}
