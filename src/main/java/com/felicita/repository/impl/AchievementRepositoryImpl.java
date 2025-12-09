package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.AchievementResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.queries.AchievementQueries;
import com.felicita.queries.OurFeaturesQueries;
import com.felicita.repository.AchievementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AchievementRepositoryImpl implements AchievementRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AchievementRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AchievementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AchievementResponse> getAchievementDetails() {
        String GET_ACHIEVEMENT_DETAILS = AchievementQueries.GET_ACHIEVEMENT_DETAILS;

        try {
            LOGGER.info("Executing query to fetch all achievements...");

            // Map to hold achievements temporarily, key = achievementId
            Map<Long, AchievementResponse> achievementMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ACHIEVEMENT_DETAILS, rs -> {
                Long achievementId = rs.getLong("achievement_id");

                // Check if achievement already exists in map
                AchievementResponse achievement = achievementMap.get(achievementId);
                if (achievement == null) {
                    achievement = AchievementResponse.builder()
                            .achievementId(achievementId)
                            .name(rs.getString("achievement_name"))
                            .color(rs.getString("color"))
                            .hoverColor(rs.getString("hover_color"))
                            .description(rs.getString("achievement_description"))
                            .status(rs.getString("achievement_status"))
                            .images(new ArrayList<>()) // initialize empty list
                            .build();
                    achievementMap.put(achievementId, achievement);
                }

                // Add image if present
                Long imageId = rs.getLong("image_id");
                if (imageId != 0) { // 0 means NULL in getLong()
                    AchievementResponse.AchievementImageResponse image = AchievementResponse.AchievementImageResponse.builder()
                            .imageId(imageId)
                            .name(rs.getString("image_name"))
                            .description(rs.getString("image_description"))
                            .imageUrl(rs.getString("image_url"))
                            .status(rs.getString("image_status"))
                            .build();
                    achievement.getImages().add(image);
                }
            });

            List<AchievementResponse> results = new ArrayList<>(achievementMap.values());
            LOGGER.info("Successfully fetched {} achievements.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching achievements: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch achievements from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching achievements: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching achievements");
        }
    }

}
