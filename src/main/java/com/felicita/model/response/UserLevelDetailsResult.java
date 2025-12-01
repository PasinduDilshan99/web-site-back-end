package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDetailsResult {
    private Integer userId;
    private String username;
    private String firstName;
    private String lastName;
    private Integer benefitsPointsCount;

    private Integer currentLevelId;
    private String currentLevelName;
    private Integer currentLevelPoints;
    private String currentLevelDescription;

    private Integer previousLevelId;
    private String previousLevelName;
    private Integer previousLevelPoints;
    private String previousLevelDescription;

    private Integer nextLevelId;
    private String nextLevelName;
    private Integer nextLevelPoints;
    private String nextLevelDescription;

    private BigDecimal progressPercentage;
    private Integer pointsNeededForNextLevel;
}