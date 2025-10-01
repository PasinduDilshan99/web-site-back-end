package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequirementDto {
    private Integer id;
    private String name;
    private String value;
    private String description;
    private String color;
    private Integer status;
}
