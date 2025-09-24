package com.felicita.model.response;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private Long id;
    private String name;
    private String description;
    private Integer durationHours;
    private BigDecimal price;
    private Integer maxParticipants;
    private Integer minParticipants;

    private ActivityCategory category;
    private Destination destination;
    private CommonStatus status;

    private List<ActivityRequirement> requirements;
    private List<ActivityHistoryResponse> historyList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityCategory {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Destination {
        private Long id;
        private String name;
        private String description;
        private String location;
        private BigDecimal rating;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommonStatus {
        private Long id;
        private String name;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityRequirement {
        private String type;
        private String value;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityHistoryResponse {
        private Long id;
        private String name;
        private Date startDate;
        private Date endDate;
        private BigDecimal price;
        private Integer participantsCount;
        private String guide;
        private List<ActivityHistoryImage> images;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityHistoryImage {
        private Long id;
        private String name;
        private String imageUrl;
        private String imageOwner;
    }
}