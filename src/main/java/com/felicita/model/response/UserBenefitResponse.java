package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBenefitResponse {
    private Integer benefitId;
    private String benefitName;
    private String benefitDescription;
    private BigDecimal benefitValue;
    private Date validFrom;
    private Date validTo;
    private String benefitStatus;
    private BenefitTypeResponse benefitType;
}
