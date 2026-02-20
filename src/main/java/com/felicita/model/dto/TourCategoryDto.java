package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourCategoryDto {
    private Long tourCategoryId;
    private String tourCategoryName;
    private String tourCategoryDescription;
}
