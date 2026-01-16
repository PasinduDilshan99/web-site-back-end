package com.felicita.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felicita.model.dto.TourImageResponseDto;
import com.felicita.model.dto.TourScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourAllDetailsResponse {

    private int tourId;
    private String tourName;
    private String tourDescription;
    private Integer duration;
    private Double latitude;
    private Double longitude;
    private String startLocation;
    private String endLocation;

    private String tourTypeName;
    private String tourTypeDescription;
    private String tourCategoryName;
    private String tourCategoryDescription;
    private String seasonName;
    private String seasonDescription;
    private String statusName;

    private Long assignTo;
    private String assignToName;
    private String assignMessage;

    private List<TourScheduleResponseDto> schedules;
    private List<TourImageResponseDto> images;
    private List<TourExtrasResponse.TourInclusion> inclusions;
    private List<TourExtrasResponse.TourExclusion> exclusions;
    private List<TourExtrasResponse.TourCondition> conditions;
    private List<TourExtrasResponse.TourTravelTip> travelTips;
    private List<DayToDayResponse> dayToDayResponses;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DayToDayResponse {
        private Integer dayNumber;
        private List<DestinationPerDayResponse> destinations;
    }

    // ================= DESTINATION PER DAY =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestinationPerDayResponse {

        private DestinationDetailsPerDay destination;
        private List<ActivityPerDay> activities;
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
        private String category;
        private String location;
        private Double latitude;
        private Double longitude;
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
    public static class ActivityPerDay {

        private Long activityId;
        private Long destinationId;
        private String activityName;
        private String activityDescription;
        private String activitiesCategory;
        private BigDecimal durationHours;
        private Time availableFrom;
        private Time availableTo;
        private Integer minParticipate;
        private Integer maxParticipate;
        private String season;
        private String categoryName;
        private List<ActivityImagePerDay> images;
    }


    // ================= ACTIVITY IMAGES =================

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityImagePerDay {

        private Long imageId;
        private String imageName;
        private String imageDescription;
        @JsonProperty("image_url")
        private String imageUrl;
    }

}
