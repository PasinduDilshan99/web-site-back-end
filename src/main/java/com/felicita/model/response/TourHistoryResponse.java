package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourHistoryResponse {
    private Long historyId;
    private String historyName;
    private String historyDescription;
    private Integer numberOfParticipate;
    private BigDecimal rating;
    private Integer historyDuration;
    private Date startDate;
    private Date endDate;
    private String vehicleNumber;
    private Integer driverId;
    private Integer guideId;
    private String historyColor;
    private String hoverColor;
    private Integer historyStatus;

    private TourSchedule tourSchedule;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TourSchedule {
        private Long scheduleId;
        private String scheduleName;
        private Date assumeStartDate;
        private Date assumeEndDate;
        private Integer durationStart;
        private Integer durationEnd;
        private String specialNote;
        private String scheduleDescription;
        private Integer scheduleStatus;

        private Tour tour;
        private List<TourHistoryImage> images;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tour {
        private Long tourId;
        private String tourName;
        private String tourDescription;
        private Integer tourDuration;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private String startLocation;
        private String endLocation;
        private Integer tourStatus;
        private Integer tourType;
        private Integer tourCategory;
        private Integer season;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TourHistoryImage {
        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String imageColor;
        private Integer imageStatus;
    }
}
