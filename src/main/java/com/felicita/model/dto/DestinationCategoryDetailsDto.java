package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationCategoryDetailsDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isPrimary;
}
