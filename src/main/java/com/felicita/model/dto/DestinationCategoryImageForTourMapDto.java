package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationCategoryImageForTourMapDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String status;
}
