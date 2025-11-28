package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDetailsResponse {
    private Integer couponId;
    private String couponCode;
    private String couponName;
    private String couponDescription;
    private String couponType;
    private String couponStatus;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minimumCartValue;
    private BigDecimal maximumDiscount;
    private String applicableFor;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private Integer usageLimitPerCoupon;
    private Integer usageLimitPerUser;
    private Integer totalUsageCount;
    private LocalDateTime couponCreatedAt;

    private List<PackageDetail> applicablePackages;
    private List<UserTypeDetail> applicableUserTypes;
    private List<CouponRuleDetail> couponRules;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageDetail {
        private Integer packageId;
        private String packageName;
        private BigDecimal packagePrice;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserTypeDetail {
        private Integer userTypeId;
        private String userTypeName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponRuleDetail {
        private String ruleType;
        private String ruleOperator;
        private String ruleValue;
    }
}