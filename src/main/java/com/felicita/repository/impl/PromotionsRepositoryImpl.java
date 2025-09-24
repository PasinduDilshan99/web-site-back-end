package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PromotionTourResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.PromotionsQueries;
import com.felicita.repository.PromotionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromotionsRepositoryImpl implements PromotionsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionsRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PromotionsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PromotionTourResponse> getAllPromotions() {
        String GET_ALL_PROMOTIONS = PromotionsQueries.GET_ALL_PROMOTIONS;
        try {
            LOGGER.info("Executing query to fetch all tour promotions...");

            List<PromotionTourResponse> results = jdbcTemplate.query(GET_ALL_PROMOTIONS, (rs, rowNum) -> {
                PromotionTourResponse promotion = new PromotionTourResponse();

                promotion.setPromotionId(rs.getLong("promotion_id"));
                promotion.setPromotionCode(rs.getString("promotion_code"));
                promotion.setPromotionDescription(rs.getString("promotion_description"));
                promotion.setStartDate(rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null);
                promotion.setEndDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null);
                promotion.setApplyTo(rs.getString("apply_to"));
                promotion.setIsPublic(rs.getBoolean("is_public"));
                promotion.setPriority(rs.getInt("priority"));
                promotion.setMaxUsageLimit(rs.getInt("max_usage_limit"));
                promotion.setPerUserLimit(rs.getInt("per_user_limit"));

                promotion.setTourId(rs.getLong("tour_id"));
                promotion.setTourName(rs.getString("tour_name"));
                promotion.setTourDescription(rs.getString("tour_description"));
                promotion.setTourStartDate(rs.getDate("tour_start_date") != null ? rs.getDate("tour_start_date").toLocalDate() : null);
                promotion.setTourEndDate(rs.getDate("tour_end_date") != null ? rs.getDate("tour_end_date").toLocalDate() : null);
                promotion.setPricePerPerson(rs.getBigDecimal("price_per_person"));

                promotion.setPromotionTypeName(rs.getString("promotion_type"));
                promotion.setPromotionTypeDescription(rs.getString("promotion_type_description"));

                promotion.setMinPerson(rs.getInt("min_person"));
                promotion.setMaxPerson(rs.getInt("max_person"));
                promotion.setMinPrice(rs.getBigDecimal("min_price"));
                promotion.setMaxPrice(rs.getBigDecimal("max_price"));
                promotion.setDiscountType(rs.getString("discount_type"));
                promotion.setDiscountValue(rs.getBigDecimal("discount_value"));
                promotion.setEligibleCustomerGroup(rs.getString("eligible_customer_group"));
                promotion.setPaymentMethodRestriction(rs.getString("payment_method_restriction"));
                promotion.setBookingPeriodStart(rs.getDate("booking_period_start") != null ? rs.getDate("booking_period_start").toLocalDate() : null);
                promotion.setBookingPeriodEnd(rs.getDate("booking_period_end") != null ? rs.getDate("booking_period_end").toLocalDate() : null);
                promotion.setTravelPeriodStart(rs.getDate("travel_period_start") != null ? rs.getDate("travel_period_start").toLocalDate() : null);
                promotion.setTravelPeriodEnd(rs.getDate("travel_period_end") != null ? rs.getDate("travel_period_end").toLocalDate() : null);

                // Promotion status
                promotion.setPromotionStatus(rs.getString("promotion_status"));

                return promotion;
            });

            LOGGER.info("Successfully fetched {} tour promotions.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tour promotions: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch tour promotions from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tour promotions: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tour promotions");
        }
    }

}
