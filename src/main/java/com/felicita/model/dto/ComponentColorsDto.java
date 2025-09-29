package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentColorsDto {
    private Long componentId;
    private String componentName;
    private String componentDescription;
    private List<ComponentThemeColorsDto> themes;
}
