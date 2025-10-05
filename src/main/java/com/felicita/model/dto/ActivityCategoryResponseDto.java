package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCategoryResponseDto {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryStatus;
    private Timestamp createdAt;
    private Integer createdBy;
    private Timestamp updatedAt;
    private Integer updatedBy;
    private Timestamp terminatedAt;
    private Integer terminatedBy;

    private List<ActivityCategoryImageResponseDto> images;
}

