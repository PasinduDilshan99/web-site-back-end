package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityHistoryDetailsResponse {
    private Long historyId;
    private ActivityInfo activity;
    private ScheduleInfo schedule;
    private HistoryInfo history;
    private List<ImageInfo> images;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityInfo {
        private Long activityId;
        private String activityName;
        private String activityDescription;
        private String activityCategory;
        private Integer durationHours;
        private LocalDateTime availableFrom;
        private LocalDateTime availableTo;
        private Double priceLocal;
        private Double priceForeigners;
        private Integer minParticipate;
        private Integer maxParticipate;
        private String season;
        private DestinationInfo destination;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DestinationInfo {
        private String destinationId;
        private String destinationName;
        private String destinationDescription;
        private String destinationLocation;
        private Double latitude;
        private Double longitude;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleInfo {
        private Long scheduleId;
        private String scheduleName;
        private String scheduleDescription;
        private LocalDateTime assumeStartDate;
        private LocalDateTime assumeEndDate;
        private Integer durationHoursStart;
        private Integer durationHoursEnd;
        private String specialNote;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryInfo {
        private String historyName;
        private String historyDescription;
        private Integer numberOfParticipate;
        private LocalDateTime activityStart;
        private LocalDateTime activityEnd;
        private Double rating;
        private String specialNote;
        private String statusName;
        private String createdByUsername;
        private String updatedByUsername;
        private String terminatedByUsername;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime terminatedAt;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageInfo {
        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String statusName;
        private String createdByUsername;
        private String updatedByUsername;
        private String terminatedByUsername;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime terminatedAt;
    }
}
