package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.FaqResponse;
import com.felicita.model.response.FeatureResponse;
import com.felicita.queries.FaqQueries;
import com.felicita.queries.OurFeaturesQueries;
import com.felicita.repository.OurFeaturesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OurFeaturesRepositoryImpl implements OurFeaturesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurFeaturesRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OurFeaturesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FeatureResponse> getOurFeaturesDetails() {
        String GET_OUR_FEATURES_DETAILS = OurFeaturesQueries.GET_OUR_FEATURES_DETAILS;
        try {
            LOGGER.info("Executing query to fetch all feature items...");

            List<FeatureResponse> results = jdbcTemplate.query(GET_OUR_FEATURES_DETAILS, (rs, rowNum) -> FeatureResponse.builder()
                    .featureId(rs.getLong("feature_id"))
                    .featureName(rs.getString("feature_name"))
                    .iconUrl(rs.getString("icon_url"))
                    .color(rs.getString("color"))
                    .hoverColor(rs.getString("hover_color"))
                    .description(rs.getString("description"))
                    .statusId(rs.getInt("status_id"))
                    .statusName(rs.getString("status_name"))
                    .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                    .createdBy(rs.getLong("created_by"))
                    .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                    .updatedBy(rs.getLong("updated_by"))
                    .terminatedAt(rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null)
                    .terminatedBy(rs.getLong("terminated_by"))
                    .build()
            );

            LOGGER.info("Successfully fetched {} feature items.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching feature items: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch feature items from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching feature items: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching feature items");
        }
    }

}
