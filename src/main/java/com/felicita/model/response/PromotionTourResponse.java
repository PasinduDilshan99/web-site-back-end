package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionTourResponse {
    private Long promotionId;
    private String promotionCode;
    private String promotionDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String applyTo;
    private Boolean isPublic;
    private Integer priority;
    private Integer maxUsageLimit;
    private Integer perUserLimit;

    private Long tourId;
    private String tourName;
    private String tourDescription;
    private LocalDate tourStartDate;
    private LocalDate tourEndDate;
    private BigDecimal pricePerPerson;

    private String promotionTypeName;
    private String promotionTypeDescription;

    private Integer minPerson;
    private Integer maxPerson;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String discountType;
    private BigDecimal discountValue;
    private String eligibleCustomerGroup;
    private String paymentMethodRestriction;
    private LocalDate bookingPeriodStart;
    private LocalDate bookingPeriodEnd;
    private LocalDate travelPeriodStart;
    private LocalDate travelPeriodEnd;

    private String promotionStatus;
}
