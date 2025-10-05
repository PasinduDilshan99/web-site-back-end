package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourReviewResponse {
    private Long reviewId;
    private String reviewerName;
    private String review;
    private BigDecimal rating;
    private String reviewDescription;
    private Integer numberOfParticipate;
    private LocalDateTime reviewCreatedAt;

    private Long scheduleId;
    private String scheduleName;
    private LocalDateTime assumeStartDate;
    private LocalDateTime assumeEndDate;

    private Long tourId;
    private String tourName;
    private String tourDescription;
    private String startLocation;
    private String endLocation;

    private Long userId;
    private String userFullName;
    private String userEmail;

    private String reviewStatus;

    private List<TourReviewImageResponse> images;
}
