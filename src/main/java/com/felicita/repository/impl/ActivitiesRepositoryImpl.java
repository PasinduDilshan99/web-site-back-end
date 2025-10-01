package com.felicita.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityImageDto;
import com.felicita.model.dto.ActivityRequirementDto;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.dto.ActivityScheduleDto;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.repository.ActivitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ActivitiesRepositoryImpl implements ActivitiesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;  // ADD THIS

    @Autowired
    public ActivitiesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = new ObjectMapper();  // ADD THIS
    }

    @Override
    public List<ActivityResponseDto> getAllActivities() {
        String GET_ALL_ACTIVITIES = ActivitiesQueries.GET_ALL_ACTIVITIES;

        return jdbcTemplate.query(GET_ALL_ACTIVITIES, new ActivityRowMapper());
    }

    private class ActivityRowMapper implements RowMapper<ActivityResponseDto> {
        @Override
        public ActivityResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActivityResponseDto activity = new ActivityResponseDto();

            // Map basic fields
            activity.setId(rs.getInt("id"));
            activity.setDestinationId(rs.getInt("destination_id"));
            activity.setName(rs.getString("name"));
            activity.setDescription(rs.getString("description"));
            activity.setActivitiesCategory(rs.getString("activities_category"));
            activity.setDurationHours(rs.getBigDecimal("duration_hours"));
            activity.setAvailableFrom(rs.getTime("available_from"));
            activity.setAvailableTo(rs.getTime("available_to"));
            activity.setPriceLocal(rs.getBigDecimal("price_local"));
            activity.setPriceForeigners(rs.getBigDecimal("price_foreigners"));
            activity.setMinParticipate(rs.getInt("min_participate"));
            activity.setMaxParticipate(rs.getInt("max_participate"));
            activity.setSeason(rs.getString("season"));
            activity.setStatus(rs.getString("status_name"));
            activity.setCreatedAt(rs.getTimestamp("created_at"));
            activity.setUpdatedAt(rs.getTimestamp("updated_at"));

            // Map category details
            activity.setCategoryName(rs.getString("category_name"));
            activity.setCategoryDescription(rs.getString("category_description"));

            // Parse JSON arrays
            try {
                // Parse schedules
                String schedulesJson = rs.getString("schedules");
                if (schedulesJson != null && !schedulesJson.equals("[]")) {
                    List<ActivityScheduleDto> schedules = objectMapper.readValue(
                            schedulesJson,
                            new TypeReference<List<ActivityScheduleDto>>() {}
                    );
                    activity.setSchedules(schedules);
                } else {
                    activity.setSchedules(List.of());
                }

                // Parse requirements
                String requirementsJson = rs.getString("requirements");
                if (requirementsJson != null && !requirementsJson.equals("[]")) {
                    List<ActivityRequirementDto> requirements = objectMapper.readValue(
                            requirementsJson,
                            new TypeReference<List<ActivityRequirementDto>>() {}
                    );
                    activity.setRequirements(requirements);
                } else {
                    activity.setRequirements(List.of());
                }

                // Parse images
                String imagesJson = rs.getString("images");
                if (imagesJson != null && !imagesJson.equals("[]")) {
                    List<ActivityImageDto> images = objectMapper.readValue(
                            imagesJson,
                            new TypeReference<List<ActivityImageDto>>() {}
                    );
                    activity.setImages(images);
                } else {
                    activity.setImages(List.of());
                }

            } catch (Exception e) {
                LOGGER.error("Error parsing JSON data for activity id: {}", activity.getId(), e);
                throw new SQLException("Error parsing JSON data", e);
            }

            return activity;
        }
    }
}