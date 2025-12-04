package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.AboutUsHeroSectionResponse;
import com.felicita.model.response.StatisticsResponse;
import com.felicita.queries.HeroSectionQueries;
import com.felicita.queries.StatisticsQueries;
import com.felicita.repository.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatisticsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StatisticsResponse> getAboutUsStatisticsDetails() {
        String GET_ABOUT_US_STATISTICS_DETAILS = StatisticsQueries.GET_ABOUT_US_STATISTICS_DETAILS;
        try {
            LOGGER.info("Executing query to fetch all statistics items...");

            List<StatisticsResponse> results = jdbcTemplate.query(GET_ABOUT_US_STATISTICS_DETAILS, (rs, rowNum) -> {
                StatisticsResponse statistics = StatisticsResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .imageUrl(rs.getString("image_url"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .color(rs.getString("color"))
                        .hoverColor(rs.getString("hover_color"))
                        .value(rs.getInt("value"))
                        .statusName(rs.getString("status")) // Note: This is from common_status table
                        .createdAt(rs.getTimestamp("created_at") != null ?
                                rs.getTimestamp("created_at").toLocalDateTime() : null)
                        .createdByName(rs.getString("created_by_name"))
                        .updatedAt(rs.getTimestamp("updated_at") != null ?
                                rs.getTimestamp("updated_at").toLocalDateTime() : null)
                        .updatedByName(rs.getString("updated_by_name"))
                        .terminatedAt(rs.getTimestamp("terminated_at") != null ?
                                rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                        .terminatedByName(rs.getString("terminated_by_name"))
                        .build();

                return statistics;
            });

            LOGGER.info("Successfully fetched {} statistics items.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching statistics items: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch statistics items from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching statistics items: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching statistics items");
        }
    }
}
