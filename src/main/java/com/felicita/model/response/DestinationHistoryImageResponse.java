package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationHistoryImageResponse {
    private Long imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String imageStatusName;
    private LocalDateTime imageCreatedAt;
    private LocalDateTime imageUpdatedAt;
    private LocalDateTime imageTerminatedAt;
    private UserDto imageCreatedBy;
    private UserDto imageUpdatedBy;
    private UserDto imageTerminatedBy;
    private HistoryDto history;
    private DestinationDto destination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private String username;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryDto {
        private Long historyId;
        private String title;
        private String description;
        private LocalDate eventDate;
        private String historyStatusName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DestinationDto {
        private Long destinationId;
        private String name;
        private String location;
        private BigDecimal latitude;
        private BigDecimal longitude;
    }
}
