package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCategoryImageResponseDto {
    private int imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String imageStatus;
    private Timestamp createdAt;
    private Integer createdBy;
    private Timestamp updatedAt;
    private Integer updatedBy;
    private Timestamp terminatedAt;
    private Integer terminatedBy;
}
