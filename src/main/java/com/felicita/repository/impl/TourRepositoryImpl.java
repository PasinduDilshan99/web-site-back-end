package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.TourImageDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.TourQueries;
import com.felicita.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TourRepositoryImpl implements TourRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TourRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TourResponseDto> getAllTours() {
        String GET_ALL_TOURS = TourQueries.GET_ALL_TOURS;
        try {
            LOGGER.info("Executing query to fetch all tours...");

            // Map to store tours by tourId to handle multiple rows per tour (due to images)
            Map<Integer, TourResponseDto> tourMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_ALL_TOURS, rs -> {
                int tourId = rs.getInt("tour_id");

                // Parse destination IDs CSV into List<Integer>
                String destCsv = rs.getString("destination_ids");
                List<Integer> destinationIds = new ArrayList<>();
                if (destCsv != null && !destCsv.isEmpty()) {
                    destinationIds = Arrays.stream(destCsv.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                }

                // Build TourImageDto if image exists
                List<TourImageDto> images = new ArrayList<>();
                Integer imageId = rs.getObject("image_id", Integer.class);
                if (imageId != null) {
                    images.add(new TourImageDto(
                            imageId,
                            rs.getString("image_name"),
                            rs.getString("image_url"),
                            rs.getString("image_description"),
                            rs.getString("image_status")
                    ));
                }

                // Check if tour already exists in map
                if (tourMap.containsKey(tourId)) {
                    // Add image to existing tour
                    TourResponseDto existingTour = tourMap.get(tourId);
                    existingTour.getTourImages().addAll(images);
                } else {
                    // Create new tour
                    TourResponseDto tour = new TourResponseDto(
                            tourId,
                            rs.getString("tour_name"),
                            rs.getString("tour_description"),
                            rs.getString("tour_type"),
                            rs.getString("tour_category"),
                            rs.getInt("duration_days"),
                            rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null,
                            rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null,
                            rs.getString("start_location"),
                            rs.getString("end_location"),
                            rs.getObject("max_people", Integer.class),
                            rs.getObject("min_people", Integer.class),
                            rs.getBigDecimal("price_per_person"),
                            rs.getString("tour_status"),
                            images,
                            destinationIds,
                            rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            rs.getObject("created_by", Integer.class),
                            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                            rs.getObject("updated_by", Integer.class),
                            rs.getTimestamp("terminated_at") != null ? rs.getTimestamp("terminated_at").toLocalDateTime() : null,
                            rs.getObject("terminated_by", Integer.class)
                    );
                    tourMap.put(tourId, tour);
                }
            });

            List<TourResponseDto> results = new ArrayList<>(tourMap.values());
            LOGGER.info("Successfully fetched {} tours.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching tours: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching tours: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

}
