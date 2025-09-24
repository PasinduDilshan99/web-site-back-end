package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Integer reviewId;
    private Integer userId;
    private Integer rating;
    private String comment;
    private Integer reviewStatusId;
    private String reviewStatusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
