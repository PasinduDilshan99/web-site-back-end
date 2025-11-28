package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CouponAllocationResponse;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.UserProfileReviewResponse;
import com.felicita.model.response.UserProfileSidebarResponse;
import com.felicita.queries.CouponQueries;
import com.felicita.queries.UserProfileQueries;
import com.felicita.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CouponRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CouponDetailsResponse> getAllCouponsDetails() {

        String GET_ALL_COUPONS_DETAILS = CouponQueries.GET_ALL_COUPONS_DETAILS;

        try {

            Map<Integer, CouponDetailsResponse> couponMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_COUPONS_DETAILS, rs -> {

                Integer couponId = rs.getInt("coupon_id");

                // Create parent object if first time seeing this coupon ID
                CouponDetailsResponse coupon = couponMap.computeIfAbsent(couponId, id ->
                        {
                            try {
                                return CouponDetailsResponse.builder()
                                        .couponId(id)
                                        .couponCode(getString(rs, "coupon_code"))
                                        .couponName(getString(rs, "coupon_name"))
                                        .couponDescription(getString(rs, "coupon_description"))
                                        .couponType(getString(rs, "coupon_type"))
                                        .couponStatus(getString(rs, "coupon_status"))
                                        .discountType(getString(rs, "discount_type"))
                                        .discountValue(rs.getBigDecimal("discount_value"))
                                        .minimumCartValue(rs.getBigDecimal("minimum_cart_value"))
                                        .maximumDiscount(rs.getBigDecimal("maximum_discount"))
                                        .applicableFor(getString(rs, "applicable_for"))
                                        .validFrom(getLocalDateTime(rs, "valid_from"))
                                        .validUntil(getLocalDateTime(rs, "valid_until"))
                                        .usageLimitPerCoupon(rs.getObject("usage_limit_per_coupon", Integer.class))
                                        .usageLimitPerUser(rs.getObject("usage_limit_per_user", Integer.class))
                                        .totalUsageCount(rs.getObject("total_usage_count", Integer.class))
                                        .couponCreatedAt(getLocalDateTime(rs, "coupon_created_at"))
                                        .applicablePackages(new ArrayList<>())
                                        .applicableUserTypes(new ArrayList<>())
                                        .couponRules(new ArrayList<>())
                                        .build();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

                /** -------------------------
                 *  ADD PACKAGE DETAILS
                 * ------------------------- */
                Integer packageId = rs.getObject("package_id", Integer.class);
                if (packageId != null) {
                    boolean exists = coupon.getApplicablePackages().stream()
                            .anyMatch(p -> p.getPackageId().equals(packageId));
                    if (!exists) {
                        coupon.getApplicablePackages().add(
                                CouponDetailsResponse.PackageDetail.builder()
                                        .packageId(packageId)
                                        .packageName(getString(rs, "package_name"))
                                        .packagePrice(rs.getBigDecimal("package_price"))
                                        .build()
                        );
                    }
                }

                /** -------------------------
                 *  ADD USER TYPE DETAILS
                 * ------------------------- */
                Integer userTypeId = rs.getObject("user_type_id", Integer.class);
                if (userTypeId != null) {
                    boolean exists = coupon.getApplicableUserTypes().stream()
                            .anyMatch(u -> u.getUserTypeId().equals(userTypeId));
                    if (!exists) {
                        coupon.getApplicableUserTypes().add(
                                CouponDetailsResponse.UserTypeDetail.builder()
                                        .userTypeId(userTypeId)
                                        .userTypeName(getString(rs, "user_type_name"))
                                        .build()
                        );
                    }
                }

                /** -------------------------
                 *  ADD COUPON RULE DETAILS
                 * ------------------------- */
                String ruleType = rs.getString("rule_type");
                if (ruleType != null) {
                    coupon.getCouponRules().add(
                            CouponDetailsResponse.CouponRuleDetail.builder()
                                    .ruleType(ruleType)
                                    .ruleOperator(getString(rs, "rule_operator"))
                                    .ruleValue(getString(rs, "rule_value"))
                                    .build()
                    );
                }
            });

            return new ArrayList<>(couponMap.values());

        } catch (Exception ex) {
            LOGGER.error("Error fetching coupon details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch coupon details");
        }
    }

    @Override
    public List<CouponAllocationResponse> getAllCouponsDetailsByUser(Long userId) {

        String GET_ALL_COUPONS_DETAILS_BY_USER = CouponQueries.GET_ALL_COUPONS_DETAILS_BY_USER;

        try {
            LOGGER.info("Executing query to fetch all coupon details for user {}", userId);

            Map<Long, CouponAllocationResponse> allocationMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_COUPONS_DETAILS_BY_USER, new Object[]{userId}, (rs) -> {

                Long allocationId = rs.getLong("allocation_id");

                // If already exists (because applicable packages may repeat)
                CouponAllocationResponse response = allocationMap.get(allocationId);
                if (response == null) {

                    response = CouponAllocationResponse.builder()
                            .allocationId(allocationId)
                            .userId(rs.getLong("user_id"))
                            .username(rs.getString("username"))
                            .userFullName(rs.getString("user_full_name"))

                            .couponDetails(
                                    CouponAllocationResponse.CouponDetails.builder()
                                            .couponId(rs.getLong("coupon_id"))
                                            .couponCode(rs.getString("coupon_code"))
                                            .couponName(rs.getString("coupon_name"))
                                            .couponDescription(rs.getString("coupon_description"))
                                            .couponType(rs.getString("coupon_type"))
                                            .build()
                            )

                            .allocationStatus(
                                    CouponAllocationResponse.AllocationStatus.builder()
                                            .allocationStatus(rs.getString("allocation_status"))
                                            .statusCategory(rs.getString("status_category"))
                                            .statusDescription(rs.getString("status_description"))
                                            .build()
                            )

                            .discountInfo(
                                    CouponAllocationResponse.DiscountInfo.builder()
                                            .discountType(rs.getString("discount_type"))
                                            .discountValue(rs.getBigDecimal("discount_value"))
                                            .discountDisplay(rs.getString("discount_display"))
                                            .minimumCartValue(rs.getBigDecimal("minimum_cart_value"))
                                            .maximumDiscount(rs.getBigDecimal("maximum_discount"))
                                            .build()
                            )

                            .timingInfo(
                                    CouponAllocationResponse.TimingInfo.builder()
                                            .allocatedAt(rs.getTimestamp("allocated_at") != null ? rs.getTimestamp("allocated_at").toLocalDateTime() : null)
                                            .expiresAt(rs.getTimestamp("expires_at") != null ? rs.getTimestamp("expires_at").toLocalDateTime() : null)
                                            .usedAt(rs.getTimestamp("used_at") != null ? rs.getTimestamp("used_at").toLocalDateTime() : null)
                                            .couponValidFrom(rs.getTimestamp("coupon_valid_from") != null ? rs.getTimestamp("coupon_valid_from").toLocalDateTime() : null)
                                            .couponValidUntil(rs.getTimestamp("coupon_valid_until") != null ? rs.getTimestamp("coupon_valid_until").toLocalDateTime() : null)
                                            .build()
                            )

                            .usageInfo(
                                    CouponAllocationResponse.UsageInfo.builder()
                                            .usageLimitPerUser(rs.getInt("usage_limit_per_user"))
                                            .usageLimitPerCoupon(rs.getInt("usage_limit_per_coupon"))
                                            .totalUsageCount(rs.getInt("total_usage_count"))
                                            .build()
                            )

                            .applicabilityInfo(
                                    CouponAllocationResponse.ApplicabilityInfo.builder()
                                            .applicableFor(rs.getString("applicable_for"))
                                            .applicablePackages(new ArrayList<>())
                                            .build()
                            )

                            .calculatedStatus(
                                    CouponAllocationResponse.CalculatedStatus.builder()
                                            .effectiveStatus(rs.getString("effective_status"))
                                            .daysRemaining(rs.getLong("days_remaining"))
                                            .isUsable("ACTIVE".equals(rs.getString("effective_status")))
                                            .statusDescription(rs.getString("status_description"))
                                            .build()
                            )

                            .build();

                    allocationMap.put(allocationId, response);
                }

                // Add applicable packages (comma-separated)
                String pkgList = rs.getString("applicable_packages");
                if (pkgList != null && !pkgList.isBlank()) {
                    List<String> packages = response.getApplicabilityInfo().getApplicablePackages();

                    for (String pkg : pkgList.split(",")) {
                        if (!packages.contains(pkg))
                            packages.add(pkg);
                    }
                }
            });

            return new ArrayList<>(allocationMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching coupon details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch coupon details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching coupon details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching coupon details");
        }
    }

    @Override
    public CouponDetailsResponse getCouponDetailsById(String couponId) {
        String GET_ALL_COUPONS_DETAILS_BY_ID = CouponQueries.GET_ALL_COUPONS_DETAILS_BY_ID;

        try {
            Map<Integer, CouponDetailsResponse> couponMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_COUPONS_DETAILS_BY_ID, new Object[]{couponId}, (rs) -> {

                Integer cid = rs.getInt("coupon_id");

                // Create parent object if not yet created
                CouponDetailsResponse coupon = couponMap.computeIfAbsent(cid, id -> {
                    try {
                        return CouponDetailsResponse.builder()
                                .couponId(id)
                                .couponCode(getString(rs, "coupon_code"))
                                .couponName(getString(rs, "coupon_name"))
                                .couponDescription(getString(rs, "coupon_description"))
                                .couponType(getString(rs, "coupon_type"))
                                .couponStatus(getString(rs, "coupon_status"))
                                .discountType(getString(rs, "discount_type"))
                                .discountValue(rs.getBigDecimal("discount_value"))
                                .minimumCartValue(rs.getBigDecimal("minimum_cart_value"))
                                .maximumDiscount(rs.getBigDecimal("maximum_discount"))
                                .applicableFor(getString(rs, "applicable_for"))
                                .validFrom(getLocalDateTime(rs, "valid_from"))
                                .validUntil(getLocalDateTime(rs, "valid_until"))
                                .usageLimitPerCoupon(rs.getObject("usage_limit_per_coupon", Integer.class))
                                .usageLimitPerUser(rs.getObject("usage_limit_per_user", Integer.class))
                                .totalUsageCount(rs.getObject("total_usage_count", Integer.class))
                                .couponCreatedAt(getLocalDateTime(rs, "coupon_created_at"))
                                .applicablePackages(new ArrayList<>())
                                .applicableUserTypes(new ArrayList<>())
                                .couponRules(new ArrayList<>())
                                .build();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                /** -------------------------
                 *  ADD PACKAGE DETAILS
                 * ------------------------- */
                Integer packageId = rs.getObject("package_id", Integer.class);
                if (packageId != null) {
                    boolean exists = coupon.getApplicablePackages().stream()
                            .anyMatch(p -> p.getPackageId().equals(packageId));

                    if (!exists) {
                        coupon.getApplicablePackages().add(
                                CouponDetailsResponse.PackageDetail.builder()
                                        .packageId(packageId)
                                        .packageName(getString(rs, "package_name"))
                                        .packagePrice(rs.getBigDecimal("package_price"))
                                        .build()
                        );
                    }
                }

                /** -------------------------
                 *  ADD USER TYPE DETAILS
                 * ------------------------- */
                Integer userTypeId = rs.getObject("user_type_id", Integer.class);
                if (userTypeId != null) {
                    boolean exists = coupon.getApplicableUserTypes().stream()
                            .anyMatch(u -> u.getUserTypeId().equals(userTypeId));

                    if (!exists) {
                        coupon.getApplicableUserTypes().add(
                                CouponDetailsResponse.UserTypeDetail.builder()
                                        .userTypeId(userTypeId)
                                        .userTypeName(getString(rs, "user_type_name"))
                                        .build()
                        );
                    }
                }

                /** -------------------------
                 *  ADD RULE DETAILS
                 * ------------------------- */
                String ruleType = rs.getString("rule_type");
                if (ruleType != null) {
                    coupon.getCouponRules().add(
                            CouponDetailsResponse.CouponRuleDetail.builder()
                                    .ruleType(ruleType)
                                    .ruleOperator(getString(rs, "rule_operator"))
                                    .ruleValue(getString(rs, "rule_value"))
                                    .build()
                    );
                }
            });

            // Return the only coupon in the map
            return couponMap.values().stream().findFirst().orElse(null);

        } catch (Exception ex) {
            LOGGER.error("Error fetching coupon details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch coupon details");
        }
    }



    /** Utility Methods */
    private String getString(ResultSet rs, String col) {
        try { return rs.getString(col); } catch (Exception e) { return null; }
    }

    private LocalDateTime getLocalDateTime(ResultSet rs, String col) {
        try {
            Timestamp ts = rs.getTimestamp(col);
            return ts != null ? ts.toLocalDateTime() : null;
        } catch (Exception e) { return null; }
    }


}
