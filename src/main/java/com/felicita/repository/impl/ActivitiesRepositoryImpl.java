package com.felicita.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.ActivityCategoryResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.queries.PartnerQueries;
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

    @Override
    public List<ActivityCategoryResponseDto> getAllActivityCategories() {
        String GET_ALL_ACTIVITY_CATEGORIES = ActivitiesQueries.GET_ALL_ACTIVITY_CATEGORIES;

        try {
            LOGGER.info("Executing query to fetch all activity categories...");

            // Use a LinkedHashMap to maintain insertion order and group images under categories
            Map<Integer, ActivityCategoryResponseDto> categoryMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_ACTIVITY_CATEGORIES, (rs) -> {
                int categoryId = rs.getInt("category_id");

                ActivityCategoryResponseDto category = categoryMap.get(categoryId);
                if (category == null) {
                    category = new ActivityCategoryResponseDto();
                    category.setCategoryId(categoryId);
                    category.setCategoryName(rs.getString("category_name"));
                    category.setCategoryDescription(rs.getString("category_description"));
                    category.setCategoryStatus(rs.getString("category_status"));
                    category.setCreatedAt(rs.getTimestamp("category_created_at"));
                    category.setCreatedBy(rs.getObject("category_created_by", Integer.class));
                    category.setUpdatedAt(rs.getTimestamp("category_updated_at"));
                    category.setUpdatedBy(rs.getObject("category_updated_by", Integer.class));
                    category.setTerminatedAt(rs.getTimestamp("category_terminated_at"));
                    category.setTerminatedBy(rs.getObject("category_terminated_by", Integer.class));
                    category.setImages(new ArrayList<>());

                    categoryMap.put(categoryId, category);
                }

                int imageId = rs.getInt("image_id");
                if (!rs.wasNull()) {
                    ActivityCategoryImageResponseDto image = new ActivityCategoryImageResponseDto();
                    image.setImageId(imageId);
                    image.setImageName(rs.getString("image_name"));
                    image.setImageDescription(rs.getString("image_description"));
                    image.setImageUrl(rs.getString("image_url"));
                    image.setImageStatus(rs.getString("image_status"));
                    image.setCreatedAt(rs.getTimestamp("image_created_at"));
                    image.setCreatedBy(rs.getObject("image_created_by", Integer.class));
                    image.setUpdatedAt(rs.getTimestamp("image_updated_at"));
                    image.setUpdatedBy(rs.getObject("image_updated_by", Integer.class));
                    image.setTerminatedAt(rs.getTimestamp("image_terminated_at"));
                    image.setTerminatedBy(rs.getObject("image_terminated_by", Integer.class));

                    category.getImages().add(image);
                }
            });

            return new ArrayList<>(categoryMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch activity categories from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching activity categories: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching activity categories");
        }
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