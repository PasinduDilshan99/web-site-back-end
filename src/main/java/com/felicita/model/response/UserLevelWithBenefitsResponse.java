package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelWithBenefitsResponse {
    private Integer userLevelId;
    private String userLevelName;
    private String userLevelDescription;
    private String userLevelStatus;
    private UserBenefitResponse userBenefitResponse;
}
