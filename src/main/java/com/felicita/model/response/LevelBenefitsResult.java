package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelBenefitsResult {
    private Integer userLevelId;
    private String levelName;
    private Integer benefitId;
    private String benefitName;
    private String benefitDescription;
    private BigDecimal benefitValue;
    private String benefitType;
    private String benefitTypeDescription;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String benefitStatus;
}