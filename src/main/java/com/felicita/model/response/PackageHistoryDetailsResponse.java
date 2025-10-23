package com.felicita.model.response;

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
public class PackageHistoryDetailsResponse {

    private Integer packageHistoryId;
    private Integer packageScheduleId;
    private String packageScheduleName;
    private LocalDate assumeStartDate;
    private LocalDate assumeEndDate;
    private Integer durationStart;
    private Integer durationEnd;

    private PackageInfo packageInfo;

    private Integer numberOfParticipate;
    private BigDecimal rating;
    private Integer duration;
    private String historyDescription;
    private String color;
    private String hoverColor;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime historyCreatedAt;

    private UserInfo createdByUser;
    private LocalDateTime historyUpdatedAt;
    private UserInfo updatedByUser;
    private LocalDateTime historyTerminatedAt;
    private UserInfo terminatedByUser;

    private List<ImageInfo> images;

    // --- Inner Classes ---
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageInfo {
        private Integer packageId;
        private String packageName;
        private Integer tourId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private String fullName;
        private String imageUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageInfo {
        private Integer imageId;
        private String name;
        private String description;
        private String imageUrl;
        private String color;
    }
}
