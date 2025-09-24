package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitTypeResponse {
    private Integer benefitTypeId;
    private String benefitTypeName;
    private String benefitTypeDescription;
    private String benefitTypeStatus;
}
