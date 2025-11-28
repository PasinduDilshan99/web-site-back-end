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
public class CouponAllocationResponse {

    private Long allocationId;
    private Long userId;
    private String username;
    private String userFullName;

    private CouponDetails couponDetails;
    private AllocationStatus allocationStatus;
    private DiscountInfo discountInfo;
    private TimingInfo timingInfo;
    private UsageInfo usageInfo;
    private ApplicabilityInfo applicabilityInfo;
    private CalculatedStatus calculatedStatus;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponDetails {
        private Long couponId;
        private String couponCode;
        private String couponName;
        private String couponDescription;
        private String couponType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllocationStatus {
        private String allocationStatus;
        private String statusCategory;
        private String statusDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscountInfo {
        private String discountType;
        private BigDecimal discountValue;
        private String discountDisplay;
        private BigDecimal minimumCartValue;
        private BigDecimal maximumDiscount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimingInfo {
        private LocalDateTime allocatedAt;
        private LocalDateTime expiresAt;
        private LocalDateTime usedAt;
        private LocalDateTime couponValidFrom;
        private LocalDateTime couponValidUntil;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsageInfo {
        private Integer usageLimitPerUser;
        private Integer usageLimitPerCoupon;
        private Integer totalUsageCount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplicabilityInfo {
        private String applicableFor;
        private List<String> applicablePackages;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalculatedStatus {
        private String effectiveStatus;
        private Long daysRemaining;
        private Boolean isUsable;
        private String statusDescription;
    }

    // Helper methods
    public Boolean isActive() {
        return "ACTIVE".equals(this.calculatedStatus.getEffectiveStatus());
    }

    public Boolean isExpired() {
        return this.calculatedStatus.getDaysRemaining() != null
                && this.calculatedStatus.getDaysRemaining() < 0;
    }

    public Boolean isUsed() {
        return "USED".equals(this.calculatedStatus.getEffectiveStatus());
    }
}