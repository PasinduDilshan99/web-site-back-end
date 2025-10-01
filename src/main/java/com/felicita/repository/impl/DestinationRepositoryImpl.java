package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.model.response.*;
import com.felicita.queries.DestinationQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.DestinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DestinationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DestinationResponseDto> getAllDestinations() {
        String GET_ALL_DESTINATIONS = DestinationQueries.GET_ALL_DESTINATIONS;
        try {
            LOGGER.info("Executing query to fetch all destinations...");

            return jdbcTemplate.query(GET_ALL_DESTINATIONS, rs -> {
                Map<Integer, DestinationResponseDto> destinationMap = new HashMap<>();

                while (rs.next()) {
                    int destinationId = rs.getInt("destination_id");

                    // Check if destination already exists
                    DestinationResponseDto destination = destinationMap.get(destinationId);
                    if (destination == null) {
                        destination = new DestinationResponseDto();
                        destination.setDestinationId(destinationId);
                        destination.setDestinationName(rs.getString("destination_name"));
                        destination.setDestinationDescription(rs.getString("destination_description"));
                        destination.setLocation(rs.getString("location"));
                        destination.setLatitude(rs.getObject("latitude", Double.class));
                        destination.setLongitude(rs.getObject("longitude", Double.class));

                        destination.setCategoryName(rs.getString("category_name"));
                        destination.setCategoryDescription(rs.getString("category_description"));
                        destination.setStatusName(rs.getString("status_name"));

                        destination.setActivities(new ArrayList<>());
                        destination.setImages(new ArrayList<>());

                        destinationMap.put(destinationId, destination);
                    }

                    // Add activity if exists
                    int activityId = rs.getInt("activity_id");
                    if (activityId != 0 && rs.getString("activity_name") != null) {
                        DestinationActivityResponseDto activity = new DestinationActivityResponseDto();
                        activity.setActivityId(activityId);
                        activity.setActivityName(rs.getString("activity_name"));
                        activity.setActivityDescription(rs.getString("activity_description"));
                        activity.setActivitiesCategory(rs.getString("activities_category"));
                        activity.setDurationHours(rs.getObject("duration_hours", Double.class));
                        activity.setAvailableFrom(rs.getString("available_from"));
                        activity.setAvailableTo(rs.getString("available_to"));
                        activity.setPriceLocal(rs.getObject("price_local", Double.class));
                        activity.setPriceForeigners(rs.getObject("price_foreigners", Double.class));
                        activity.setMinParticipate(rs.getObject("min_participate", Integer.class));
                        activity.setMaxParticipate(rs.getObject("max_participate", Integer.class));
                        activity.setSeason(rs.getString("season"));

                        // Avoid duplicates
                        if (destination.getActivities().stream().noneMatch(a -> a.getActivityId() == activityId)) {
                            destination.getActivities().add(activity);
                        }
                    }

                    // Add image if exists
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        DestionationImageResponseDto image = new DestionationImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));

                        // Avoid duplicates
                        if (destination.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            destination.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(destinationMap.values());
            });

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching destinations: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch destinations from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching destinations: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching destinations");
        }
    }


}
