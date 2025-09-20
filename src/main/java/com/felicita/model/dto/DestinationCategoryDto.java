package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationCategoryDto {
    private String categoryName;
    private String categoryDescription;
    private String categoryStatus;
    private String categoryImageUrl;
}
