package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageFeatureResponseDto {
    private int featureId;
    private String featureName;
    private String featureValue;
    private String featureDescription;
    private String color;
    private String specialNote;
}
