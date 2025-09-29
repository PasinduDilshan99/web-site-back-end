package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageComponentColorsDto {
    private Long pageComponentId;
    private int orderIndex;
    private boolean isVisible;
    private ComponentColorsDto component;
}

