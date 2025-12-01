package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.*;
import com.felicita.queries.UserBenefitsQueries;
import com.felicita.queries.UserLevelQueries;
import com.felicita.repository.UserBenefitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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



    @Override
    public UserLevelDetailsResponse getUserBenifitsDetailsForUserId(Long userId) {
        LOGGER.info("Executing query to fetch user level details for user ID: {}", userId);

        try {
            // Step 1: Get user level details
            UserLevelDetailsResult levelDetails = getUserLevelDetails(userId);
            if (levelDetails == null) {
                throw new DataAccessErrorExceptionHandler("User not found with ID: " + userId);
            }

            // Step 2: Collect level IDs
            List<Integer> levelIds = Arrays.asList(
                            levelDetails.getCurrentLevelId(),
                            levelDetails.getPreviousLevelId(),
                            levelDetails.getNextLevelId()
                    ).stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Step 3: Get benefits for all levels
            Map<Integer, List<LevelBenefitsResult>> benefitsByLevel = getBenefitsByLevelIds(levelIds);

            // Step 4: Build the response
            return buildUserLevelDetailsResponse(levelDetails, benefitsByLevel);

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user level details for user {}: {}", userId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user level details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user level details for user {}: {}", userId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user level details");
        }
    }

    private UserLevelDetailsResult getUserLevelDetails(Long userId) {
        String query = UserBenefitsQueries.GET_USER_LEVEL_DETAILS;

        return jdbcTemplate.query(query, new Object[]{userId}, rs -> {
            if (rs.next()) {
                return UserLevelDetailsResult.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .benefitsPointsCount(rs.getInt("benefits_points_count"))
                        .currentLevelId(rs.getInt("current_level_id"))
                        .currentLevelName(rs.getString("current_level_name"))
                        .currentLevelPoints(rs.getInt("current_level_points"))
                        .currentLevelDescription(rs.getString("current_level_description"))
                        .previousLevelId(getNullableInt(rs, "previous_level_id"))
                        .previousLevelName(rs.getString("previous_level_name"))
                        .previousLevelPoints(getNullableInt(rs, "previous_level_points"))
                        .previousLevelDescription(rs.getString("previous_level_description"))
                        .nextLevelId(getNullableInt(rs, "next_level_id"))
                        .nextLevelName(rs.getString("next_level_name"))
                        .nextLevelPoints(getNullableInt(rs, "next_level_points"))
                        .nextLevelDescription(rs.getString("next_level_description"))
                        .progressPercentage(getNullableBigDecimal(rs, "progress_percentage"))
                        .pointsNeededForNextLevel(getNullableInt(rs, "points_needed_for_next_level"))
                        .build();
            }
            return null;
        });
    }

    private Map<Integer, List<LevelBenefitsResult>> getBenefitsByLevelIds(List<Integer> levelIds) {
        if (levelIds.isEmpty()) {
            return new HashMap<>();
        }

        String query = UserBenefitsQueries.GET_BENEFITS_BY_LEVEL_IDS;
        String placeholders = levelIds.stream().map(id -> "?").collect(Collectors.joining(","));
        query = query.replace(":levelIds", placeholders);

        List<LevelBenefitsResult> benefits = jdbcTemplate.query(query, levelIds.toArray(), (rs, rowNum) ->
                LevelBenefitsResult.builder()
                        .userLevelId(rs.getInt("user_level_id"))
                        .levelName(rs.getString("level_name"))
                        .benefitId(rs.getInt("benefit_id"))
                        .benefitName(rs.getString("benefit_name"))
                        .benefitDescription(rs.getString("benefit_description"))
                        .benefitValue(rs.getBigDecimal("benefit_value"))
                        .benefitType(rs.getString("benefit_type"))
                        .benefitTypeDescription(rs.getString("benefit_type_description"))
                        .validFrom(getNullableLocalDate(rs, "valid_from"))
                        .validTo(getNullableLocalDate(rs, "valid_to"))
                        .benefitStatus(rs.getString("benefit_status"))
                        .build()
        );

        return benefits.stream().collect(Collectors.groupingBy(LevelBenefitsResult::getUserLevelId));
    }

    private UserLevelDetailsResponse buildUserLevelDetailsResponse(
            UserLevelDetailsResult levelDetails,
            Map<Integer, List<LevelBenefitsResult>> benefitsByLevel) {

        return UserLevelDetailsResponse.builder()
                .userDetails(buildUserDetails(levelDetails))
                .currentUserLevel(buildUserLevelDetails(
                        levelDetails.getCurrentLevelId(),
                        levelDetails.getCurrentLevelName(),
                        levelDetails.getCurrentLevelPoints(),
                        levelDetails.getCurrentLevelDescription(),
                        benefitsByLevel.get(levelDetails.getCurrentLevelId())
                ))
                .previousUserLevel(buildUserLevelDetails(
                        levelDetails.getPreviousLevelId(),
                        levelDetails.getPreviousLevelName(),
                        levelDetails.getPreviousLevelPoints(),
                        levelDetails.getPreviousLevelDescription(),
                        benefitsByLevel.get(levelDetails.getPreviousLevelId())
                ))
                .nextUserLevel(buildUserLevelDetails(
                        levelDetails.getNextLevelId(),
                        levelDetails.getNextLevelName(),
                        levelDetails.getNextLevelPoints(),
                        levelDetails.getNextLevelDescription(),
                        benefitsByLevel.get(levelDetails.getNextLevelId())
                ))
                .progress(buildProgressDetails(levelDetails))
                .build();
    }

    private UserLevelDetailsResponse.UserDetails buildUserDetails(UserLevelDetailsResult result) {
        return UserLevelDetailsResponse.UserDetails.builder()
                .userId(result.getUserId())
                .username(result.getUsername())
                .firstName(result.getFirstName())
                .lastName(result.getLastName())
                .benefitsPointsCount(result.getBenefitsPointsCount())
                .build();
    }

    private UserLevelDetailsResponse.UserLevelDetails buildUserLevelDetails(
            Integer levelId, String levelName, Integer pointsNeeded,
            String description, List<LevelBenefitsResult> benefits) {

        if (levelId == null) {
            return null;
        }

        List<UserLevelDetailsResponse.BenefitDetails> benefitDetails = Optional.ofNullable(benefits)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::buildBenefitDetails)
                .collect(Collectors.toList());

        return UserLevelDetailsResponse.UserLevelDetails.builder()
                .levelId(levelId)
                .levelName(levelName)
                .pointsNeeded(pointsNeeded)
                .description(description)
                .benefits(benefitDetails)
                .build();
    }

    private UserLevelDetailsResponse.BenefitDetails buildBenefitDetails(LevelBenefitsResult result) {
        return UserLevelDetailsResponse.BenefitDetails.builder()
                .benefitId(result.getBenefitId())
                .benefitName(result.getBenefitName())
                .benefitDescription(result.getBenefitDescription())
                .benefitValue(result.getBenefitValue())
                .benefitType(result.getBenefitType())
                .benefitTypeDescription(result.getBenefitTypeDescription())
                .validFrom(result.getValidFrom())
                .validTo(result.getValidTo())
                .benefitStatus(result.getBenefitStatus())
                .build();
    }

    private UserLevelDetailsResponse.ProgressDetails buildProgressDetails(UserLevelDetailsResult result) {
        return UserLevelDetailsResponse.ProgressDetails.builder()
                .progressPercentage(result.getProgressPercentage() != null ?
                        result.getProgressPercentage() : BigDecimal.ZERO)
                .pointsNeededForNextLevel(result.getPointsNeededForNextLevel() != null ?
                        result.getPointsNeededForNextLevel() : 0)
                .build();
    }

    // Utility methods for handling null values
    private Integer getNullableInt(java.sql.ResultSet rs, String columnName) throws java.sql.SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : value;
    }

    private BigDecimal getNullableBigDecimal(java.sql.ResultSet rs, String columnName) throws java.sql.SQLException {
        BigDecimal value = rs.getBigDecimal(columnName);
        return rs.wasNull() ? null : value;
    }

    private LocalDate getNullableLocalDate(java.sql.ResultSet rs, String columnName) throws java.sql.SQLException {
        java.sql.Date date = rs.getDate(columnName);
        return date != null ? date.toLocalDate() : null;
    }



}