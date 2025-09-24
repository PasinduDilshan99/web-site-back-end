package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.BenefitTypeResponse;
import com.felicita.model.response.UserBenefitResponse;
import com.felicita.model.response.UserLevelWithBenefitsResponse;
import com.felicita.queries.UserBenefitsQueries;
import com.felicita.queries.UserLevelQueries;
import com.felicita.repository.UserBenefitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBenefitsRepositoryImpl implements UserBenefitsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBenefitsRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserBenefitsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserBenefitResponse> getAllUserBenefits() {
        String GET_ALL_USER_BENEFITS = UserBenefitsQueries.GET_ALL_USER_BENEFITS;
        try {
            LOGGER.info("Executing query to fetch all user benefits...");

            List<UserBenefitResponse> results = jdbcTemplate.query(GET_ALL_USER_BENEFITS, (rs, rowNum) -> {
                UserBenefitResponse response = new UserBenefitResponse();
                response.setBenefitId(rs.getInt("benefit_id"));
                response.setBenefitName(rs.getString("benefit_name"));
                response.setBenefitDescription(rs.getString("benefit_description"));
                response.setBenefitValue(rs.getBigDecimal("benefit_value"));
                response.setValidFrom(rs.getDate("valid_from"));
                response.setValidTo(rs.getDate("valid_to"));
                response.setBenefitStatus(rs.getString("benefit_status"));

                BenefitTypeResponse benefitType = new BenefitTypeResponse();
                benefitType.setBenefitTypeId(rs.getInt("benefit_type_id"));
                benefitType.setBenefitTypeName(rs.getString("benefit_type_name"));
                benefitType.setBenefitTypeDescription(rs.getString("benefit_type_description"));
                benefitType.setBenefitTypeStatus(rs.getString("benefit_type_status"));

                response.setBenefitType(benefitType);

                return response;
            });

            LOGGER.info("Successfully fetched {} user benefits.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user benefits: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user benefits from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user benefits: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user benefits");
        }
    }


}