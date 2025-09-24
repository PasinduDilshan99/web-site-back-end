package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.*;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.UserLevelQueries;
import com.felicita.repository.UserLevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLevelRepositoryImpl implements UserLevelRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLevelRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserLevelRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserLevelWithBenefitsResponse> getAllUserLevelWithBenefits() {
        String GET_ALL_USER_LEVELS_WITH_BENEFITS = UserLevelQueries.GET_ALL_USER_LEVELS_WITH_BENEFITS;
        try {
            LOGGER.info("Executing query to fetch all user levels...");

            List<UserLevelWithBenefitsResponse> results = jdbcTemplate.query(GET_ALL_USER_LEVELS_WITH_BENEFITS, (rs, rowNum) -> {
                // Map BenefitTypeResponse
                BenefitTypeResponse benefitType = new BenefitTypeResponse(
                        rs.getInt("benefit_type_id"),
                        rs.getString("benefit_type"),
                        rs.getString("benefit_type_description"),
                        rs.getString("benefit_type_status")
                );

                UserBenefitResponse benefit = new UserBenefitResponse(
                        rs.getInt("benefit_id"),
                        rs.getString("benefit_name"),
                        rs.getString("benefit_description"),
                        rs.getBigDecimal("benefit_value"),
                        rs.getDate("valid_from"),
                        rs.getDate("valid_to"),
                        rs.getString("benefit_status"),
                        benefitType
                );

                return new UserLevelWithBenefitsResponse(
                        rs.getInt("user_level_id"),
                        rs.getString("user_level_name"),
                        rs.getString("user_level_description"),
                        rs.getString("user_level_status"),
                        benefit
                );
            });

            LOGGER.info("Successfully fetched {} user levels.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user levels: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user levels from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user levels: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user levels");
        }
    }

    @Override
    public List<UserLevelsResponse> getAllUserLevel() {
        String GET_ALL_USER_LEVELS = UserLevelQueries.GET_ALL_USER_LEVELS;
        try {
            LOGGER.info("Executing query to fetch all user levels...");

            List<UserLevelsResponse> results = jdbcTemplate.query(GET_ALL_USER_LEVELS, (rs, rowNum) -> {
                UserLevelsResponse response = new UserLevelsResponse();
                response.setUserLevelId(rs.getInt("user_level_id"));
                response.setUserLevelName(rs.getString("user_level_name"));
                response.setUserLevelDescription(rs.getString("user_level_description"));
                response.setStatusName(rs.getString("status_name"));
                response.setCreatedAt(rs.getTimestamp("created_at"));
                response.setCreatedBy(rs.getInt("created_by"));
                response.setCreatedByUser(rs.getString("created_by_user"));
                response.setUpdatedAt(rs.getTimestamp("updated_at"));
                response.setUpdatedBy(rs.getInt("updated_by"));
                response.setUpdatedByUser(rs.getString("updated_by_user"));
                response.setTerminatedAt(rs.getTimestamp("terminated_at"));
                response.setTerminatedBy(rs.getInt("terminated_by"));
                response.setTerminatedByUser(rs.getString("terminated_by_user"));
                return response;
            });

            LOGGER.info("Successfully fetched {} user levels.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user levels: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user levels from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user levels: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user levels");
        }
    }


}
