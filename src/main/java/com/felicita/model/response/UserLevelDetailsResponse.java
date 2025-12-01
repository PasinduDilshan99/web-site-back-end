package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDetailsResponse {

    private UserDetails userDetails;
    private UserLevelDetails currentUserLevel;
    private UserLevelDetails previousUserLevel;
    private UserLevelDetails nextUserLevel;
    private ProgressDetails progress;

    // Inner Classes
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetails {
        private Integer userId;
        private String username;
        private String firstName;
        private String lastName;
        private Integer benefitsPointsCount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLevelDetails {
        private Integer levelId;
        private String levelName;
        private Integer pointsNeeded;
        private String description;
        private List<BenefitDetails> benefits;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BenefitDetails {
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgressDetails {
        private BigDecimal progressPercentage;
        private Integer pointsNeededForNextLevel;
    }
}