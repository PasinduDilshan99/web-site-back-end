package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageFeaturesInsertRequest {
    private String featureName;
    private String featureValue;
    private String featureDescription;
    private String status;
    private String color;
    private String hoverColor;
    private String specialNote;
}
