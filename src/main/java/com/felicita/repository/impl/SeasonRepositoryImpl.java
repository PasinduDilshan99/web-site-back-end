package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityCategoryImageResponseDto;
import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.response.SeasonBasicResponse;
import com.felicita.model.response.SeasonDetailsResponse;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.queries.SeasonQueries;
import com.felicita.repository.SeasonRepository;
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
public class SeasonRepositoryImpl implements SeasonRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SeasonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SeasonDetailsResponse> getSeasonDetailsBySeasonId(String seasonId) {

        try {
            LOGGER.info("Executing query to fetch season details by season id.");

            Map<Long, SeasonDetailsResponse> seasonMap = new LinkedHashMap<>();

            jdbcTemplate.query(
                    SeasonQueries.GET_SEASON_DETAILS_BY_SEASON_ID,
                    new Object[]{seasonId},
                    (rs) -> {

                        Long id = rs.getLong("id");

                        SeasonDetailsResponse season = seasonMap.get(id);

                        if (season == null) {

                            season = SeasonDetailsResponse.builder()
                                    .id(id)
                                    .name(rs.getString("name"))
                                    .standardName(rs.getString("standard_name"))
                                    .localName(rs.getString("local_name"))
                                    .startMonth(rs.getObject("start_month", Integer.class))
                                    .endMonth(rs.getObject("end_month", Integer.class))
                                    .monsoonType(rs.getString("monsoon_type"))
                                    .weatherSummary(rs.getString("weather_summary"))
                                    .temperatureMin(rs.getObject("temperature_min", Integer.class))
                                    .temperatureMax(rs.getObject("temperature_max", Integer.class))
                                    .rainfallPattern(rs.getString("rainfall_pattern"))
                                    .isPeak(rs.getBoolean("is_peak"))
                                    .displayOrder(rs.getObject("display_order", Integer.class))
                                    .description(rs.getString("description"))
                                    .status(rs.getObject("status", Integer.class))
                                    .createdAt(rs.getTimestamp("created_at") != null ?
                                            rs.getTimestamp("created_at").toLocalDateTime() : null)
                                    .createdBy(rs.getObject("created_by", Integer.class))
                                    .updatedAt(rs.getTimestamp("updated_at") != null ?
                                            rs.getTimestamp("updated_at").toLocalDateTime() : null)
                                    .updatedBy(rs.getObject("updated_by", Integer.class))
                                    .seasonImages(new ArrayList<>())
                                    .build();

                            seasonMap.put(id, season);
                        }

                        // Map image (if exists)
                        Long imageId = rs.getObject("image_id", Long.class);

                        if (imageId != null) {

                            SeasonDetailsResponse.SeasonImage image =
                                    SeasonDetailsResponse.SeasonImage.builder()
                                            .id(imageId)
                                            .name(rs.getString("image_name"))
                                            .description(rs.getString("image_description"))
                                            .imageUrl(rs.getString("image_url"))
                                            .status(rs.getObject("image_status", Integer.class))
                                            .createdAt(rs.getTimestamp("image_created_at") != null ?
                                                    rs.getTimestamp("image_created_at").toLocalDateTime() : null)
                                            .createdBy(rs.getObject("image_created_by", Integer.class))
                                            .updatedAt(rs.getTimestamp("image_updated_at") != null ?
                                                    rs.getTimestamp("image_updated_at").toLocalDateTime() : null)
                                            .updatedBy(rs.getObject("image_updated_by", Integer.class))
                                            .build();

                            season.getSeasonImages().add(image);
                        }
                    });

            return new ArrayList<>(seasonMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching season details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch season details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching season details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching season details");
        }
    }

    @Override
    public List<SeasonBasicResponse> getActiveSeasonDetails() {
        try {
            LOGGER.info("Executing query to fetch all seasons with images.");

            Map<Long, SeasonBasicResponse> seasonMap = new LinkedHashMap<>();

            jdbcTemplate.query(
                    SeasonQueries.GET_ALL_SEASONS_BASIC,
                    (rs) -> {

                        Long seasonId = rs.getLong("id");

                        SeasonBasicResponse season = seasonMap.get(seasonId);

                        if (season == null) {

                            season = SeasonBasicResponse.builder()
                                    .id(seasonId)
                                    .name(rs.getString("name"))
                                    .standardName(rs.getString("standard_name"))
                                    .localName(rs.getString("local_name"))
                                    .startMonth(rs.getObject("start_month", Integer.class))
                                    .endMonth(rs.getObject("end_month", Integer.class))
                                    .isPeak(rs.getBoolean("is_peak"))
                                    .displayOrder(rs.getObject("display_order", Integer.class))
                                    .seasonImages(new ArrayList<>())
                                    .build();

                            seasonMap.put(seasonId, season);
                        }

                        Long imageId = rs.getObject("image_id", Long.class);

                        if (imageId != null) {

                            SeasonBasicResponse.SeasonImage image =
                                    SeasonBasicResponse.SeasonImage.builder()
                                            .id(imageId)
                                            .name(rs.getString("image_name"))
                                            .imageUrl(rs.getString("image_url"))
                                            .build();

                            season.getSeasonImages().add(image);
                        }
                    });

            return new ArrayList<>(seasonMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching seasons: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch seasons from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching seasons: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching seasons");
        }    }
}
