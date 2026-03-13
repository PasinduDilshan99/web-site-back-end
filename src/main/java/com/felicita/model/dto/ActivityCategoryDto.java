package com.felicita.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityCategoryDto {
    private Long id;
    private String name;
    private String description;

    @JsonProperty("is_primary")
    private Boolean isPrimary; // <-- important
}