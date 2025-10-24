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
public class DestinationHistoryDetailsResponse {
    private Integer historyId;
    private Destination destination;
    private String title;
    private String description;
    private LocalDate eventDate;
    private Status historyStatus;
    private UserSummary createdBy;
    private UserSummary updatedBy;
    private UserSummary terminatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime terminatedAt;
    private List<HistoryImage> images;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Destination {
        private Integer destinationId;
        private String name;
        private String description;
        private String location;
        private BigDecimal latitude;
        private BigDecimal longitude;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {
        private Integer id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummary {
        private Integer userId;
        private String username;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryImage {
        private Integer imageId;
        private String name;
        private String description;
        private String imageUrl;
        private Status imageStatus;
        private UserSummary createdBy;
        private UserSummary updatedBy;
        private UserSummary terminatedBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime terminatedAt;
    }
}
