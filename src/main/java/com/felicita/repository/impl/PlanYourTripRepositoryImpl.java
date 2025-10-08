package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.PlanYourTripActivitiesDto;
import com.felicita.model.dto.PlanYourTripDestinationDto;
import com.felicita.model.dto.PlanYourTripDestinationsDto;
import com.felicita.model.dto.PlanYourTripNearDestinationsDto;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.PlanYourTripQueries;
import com.felicita.repository.PlanYourTripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlanYourTripRepositoryImpl implements PlanYourTripRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanYourTripRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanYourTripRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlanYourTripDestinationsDto> getAllDestinationsForPlanYouTrip() {
        String sql = PlanYourTripQueries.GET_ALL_DESTINATIONS_FOR_PLAN_YOUR_TRIP;

        try {
            return jdbcTemplate.query(sql, new DestinationResultSetExtractor());
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while fetching destinations for plan your trip", ex);
        }
    }

    @Override
    public List<PlanYourTripDestinationDto> getAllPlanYourTripDestination() {
        String sql = PlanYourTripQueries.GET_ALL_DESTINATION_FOR_PLAN_YOUR_TRIP;

        return jdbcTemplate.query(sql, new RowMapper<PlanYourTripDestinationDto>() {
            @Override
            public PlanYourTripDestinationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlanYourTripDestinationDto dto = new PlanYourTripDestinationDto();
                dto.setId(rs.getLong("destination_id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setCategory(rs.getString("category"));
                dto.setLatitude(rs.getBigDecimal("latitude"));
                dto.setLongitude(rs.getBigDecimal("longitude"));
                return dto;
            }
        });
    }

    @Override
    public List<PlanYourTripActivitiesDto> getAllPlanYourTripActivitiesDtos() {
        String sql = PlanYourTripQueries.GET_ALL_PLAN_YOUR_TRIP_ACTIVITIES;

        return jdbcTemplate.query(sql, new RowMapper<PlanYourTripActivitiesDto>() {
            @Override
            public PlanYourTripActivitiesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlanYourTripActivitiesDto dto = new PlanYourTripActivitiesDto();
                dto.setDestinationId(rs.getInt("destination_id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setActivitiesCategory(rs.getString("activities_category"));
                dto.setAvailableFrom(rs.getTime("available_from") != null ? rs.getTime("available_from").toLocalTime() : null);
                dto.setAvailableTo(rs.getTime("available_to") != null ? rs.getTime("available_to").toLocalTime() : null);
                dto.setDurationHours(rs.getBigDecimal("duration_hours"));
                dto.setPriceLocal(rs.getBigDecimal("price_local"));
                dto.setPriceForeigners(rs.getBigDecimal("price_foreigners"));
                dto.setMinParticipate(rs.getInt("min_participate"));
                dto.setMaxParticipate(rs.getInt("max_participate"));
                return dto;
            }
        });
    }

    @Override
    public List<PlanYourTripNearDestinationsDto> getAllPlanYourTripNearDestinationsDtos() {
        String sql = PlanYourTripQueries.GET_ALL_PLAN_YOUR_TRIP_NEAR_DESTINATIONS;

        return jdbcTemplate.query(sql, new RowMapper<PlanYourTripNearDestinationsDto>() {
            @Override
            public PlanYourTripNearDestinationsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlanYourTripNearDestinationsDto dto = new PlanYourTripNearDestinationsDto();
                dto.setDestinationId(rs.getInt("destination_id"));
                String nearbyCsv = rs.getString("nearby_destinations");
                dto.setNearbyDestinations(PlanYourTripNearDestinationsDto.fromCsv(nearbyCsv));
                return dto;
            }
        });
    }

    private static class DestinationResultSetExtractor implements ResultSetExtractor<List<PlanYourTripDestinationsDto>> {

        @Override
        public List<PlanYourTripDestinationsDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, PlanYourTripDestinationsDto> destinationMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long destinationId = rs.getLong("destination_id");

                PlanYourTripDestinationsDto destination = destinationMap.computeIfAbsent(destinationId, id -> {
                    PlanYourTripDestinationsDto dto = new PlanYourTripDestinationsDto();
                    dto.setDestinationId(id);
                    try {
                        dto.setDestinationName(rs.getString("destination_name"));
                        dto.setDestinationDescription(rs.getString("destination_description"));
                        dto.setLocation(rs.getString("location"));
                        dto.setLatitude(rs.getBigDecimal("latitude"));
                        dto.setLongitude(rs.getBigDecimal("longitude"));
                        dto.setCategoryName(rs.getString("category_name"));
                        dto.setCategoryDescription(rs.getString("category_description"));
                        dto.setStatusName(rs.getString("status_name"));
                        dto.setActivities(new ArrayList<>());
                        dto.setImages(new ArrayList<>());
                        dto.setNearbyDestinations(new ArrayList<>());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return dto;
                });

                // --- Activities ---
                Long activityId = rs.getLong("activity_id");
                if (activityId != 0 && destination.getActivities().stream().noneMatch(a -> a.getId().equals(activityId))) {
                    PlanYourTripDestinationsDto.Activity activity = new PlanYourTripDestinationsDto.Activity();
                    activity.setId(activityId);
                    activity.setName(rs.getString("activity_name"));
                    activity.setDescription(rs.getString("activity_description"));
                    activity.setActivitiesCategory(rs.getString("activities_category"));
                    activity.setDurationHours(rs.getBigDecimal("duration_hours"));
                    activity.setAvailableFrom(rs.getTime("available_from") != null ? rs.getTime("available_from").toLocalTime() : null);
                    activity.setAvailableTo(rs.getTime("available_to") != null ? rs.getTime("available_to").toLocalTime() : null);
                    activity.setPriceLocal(rs.getBigDecimal("price_local"));
                    activity.setPriceForeigners(rs.getBigDecimal("price_foreigners"));
                    activity.setMinParticipate(rs.getInt("min_participate"));
                    activity.setMaxParticipate(rs.getInt("max_participate"));
                    activity.setSeason(rs.getString("season"));
                    destination.getActivities().add(activity);
                }

                // --- Images ---
                Long imageId = rs.getLong("image_id");
                if (imageId != 0 && destination.getImages().stream().noneMatch(img -> img.getId().equals(imageId))) {
                    PlanYourTripDestinationsDto.DestinationImage image = new PlanYourTripDestinationsDto.DestinationImage();
                    image.setId(imageId);
                    image.setName(rs.getString("image_name"));
                    image.setDescription(rs.getString("image_description"));
                    image.setImageUrl(rs.getString("image_url"));
                    destination.getImages().add(image);
                }

                // --- Nearby Destinations ---
                Long nearbyId = rs.getLong("nearby_id");
                if (nearbyId != 0 && destination.getNearbyDestinations().stream().noneMatch(nd -> nd.getId().equals(nearbyId))) {
                    PlanYourTripDestinationsDto.NearbyDestination nearby = new PlanYourTripDestinationsDto.NearbyDestination();
                    nearby.setId(nearbyId);
                    nearby.setDestinationId(rs.getLong("nearby_destination_id"));
                    nearby.setDestinationName(rs.getString("nearby_destination_name"));
                    nearby.setLocation(rs.getString("nearby_destination_location"));
                    nearby.setLatitude(rs.getBigDecimal("nearby_destination_latitude"));
                    nearby.setLongitude(rs.getBigDecimal("nearby_destination_longitude"));
                    nearby.setDistanceKm(rs.getBigDecimal("distance_km"));
                    destination.getNearbyDestinations().add(nearby);
                }
            }

            return new ArrayList<>(destinationMap.values());
        }
    }
}
