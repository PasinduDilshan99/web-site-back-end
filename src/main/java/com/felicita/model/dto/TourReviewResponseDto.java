package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourReviewResponseDto {
    private int reviewId;
    private String reviewerName;
    private String review;
    private Double rating;
    private String reviewDescription;
    private Integer numberOfParticipate;
    private String reviewStatus;
    private LocalDateTime reviewCreatedAt;
}
