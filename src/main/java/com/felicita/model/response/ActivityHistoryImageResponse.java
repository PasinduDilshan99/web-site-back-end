package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityHistoryImageResponse {
    private Long imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String imageStatusName;
    private String imageCreatedByUsername;
    private String imageUpdatedByUsername;
    private String imageTerminatedByUsername;
    private LocalDateTime imageCreatedAt;
    private LocalDateTime imageUpdatedAt;
    private LocalDateTime imageTerminatedAt;

    private History history;
    private Schedule schedule;
    private Activity activity;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class History {
        private Long historyId;
        private String historyName;
        private String historyDescription;
        private Integer numberOfParticipate;
        private LocalDateTime activityStart;
        private LocalDateTime activityEnd;
        private Double rating;
        private String historySpecialNote;
        private String historyStatusName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schedule {
        private Long scheduleId;
        private String scheduleName;
        private String scheduleDescription;
        private LocalDateTime assumeStartDate;
        private LocalDateTime assumeEndDate;
        private Integer durationHoursStart;
        private Integer durationHoursEnd;
        private String scheduleSpecialNote;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Activity {
        private Long activityId;
        private String activityName;
        private String activityDescription;
        private String activityCategory;
        private Integer durationHours;
        private Double priceLocal;
        private Double priceForeigners;
        private Integer minParticipate;
        private Integer maxParticipate;
    }
}
