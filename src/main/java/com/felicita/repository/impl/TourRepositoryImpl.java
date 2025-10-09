package com.felicita.repository.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.queries.TourQueries;
import com.felicita.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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
            return jdbcTemplate.query(GET_ALL_TOURS, (ResultSet rs) -> {
                Map<Integer, TourResponseDto> tourMap = new HashMap<>();

                while (rs.next()) {
                    int tourId = rs.getInt("tour_id");

                    // If we haven't seen this tour before, create it
                    TourResponseDto tour = tourMap.get(tourId);
                    if (tour == null) {
                        tour = new TourResponseDto();
                        tour.setTourId(tourId);
                        tour.setTourName(rs.getString("tour_name"));
                        tour.setTourDescription(rs.getString("tour_description"));
                        tour.setDuration(rs.getObject("duration", Integer.class));
                        tour.setLatitude(rs.getObject("latitude", Double.class));
                        tour.setLongitude(rs.getObject("longitude", Double.class));
                        tour.setStartLocation(rs.getString("start_location"));
                        tour.setEndLocation(rs.getString("end_location"));

                        tour.setTourTypeName(rs.getString("tour_type_name"));
                        tour.setTourTypeDescription(rs.getString("tour_type_description"));
                        tour.setTourCategoryName(rs.getString("tour_category_name"));
                        tour.setTourCategoryDescription(rs.getString("tour_category_description"));
                        tour.setSeasonName(rs.getString("season_name"));
                        tour.setSeasonDescription(rs.getString("season_description"));
                        tour.setStatusName(rs.getString("status_name"));

                        tour.setSchedules(new ArrayList<>());
                        tour.setImages(new ArrayList<>());

                        tourMap.put(tourId, tour);
                    }

                    // Handle schedules
                    int scheduleId = rs.getInt("schedule_id");
                    if (scheduleId != 0 && rs.getString("schedule_name") != null) {
                        TourScheduleResponseDto schedule = new TourScheduleResponseDto();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                        schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                        schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                        schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                        schedule.setSpecialNote(rs.getString("special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));

                        // Avoid duplicates
                        if (tour.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            tour.getSchedules().add(schedule);
                        }
                    }

                    // Handle images
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        TourImageResponseDto image = new TourImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));

                        // Avoid duplicates
                        if (tour.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            tour.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(tourMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }

    @Override
    public List<PopularTourResponseDto> getPopularTours() {
        String GET_POPULAR_TOURS = TourQueries.GET_POPULAR_TOURS;

        try {
            return jdbcTemplate.query(GET_POPULAR_TOURS, rs -> {
                // Map to hold tours by tourId
                Map<Integer, PopularTourResponseDto> tourMap = new LinkedHashMap<>();

                while (rs.next()) {
                    int tourId = rs.getInt("tour_id");

                    // Create or get existing tour DTO
                    PopularTourResponseDto tour = tourMap.computeIfAbsent(tourId, id -> {
                        try {
                            return new PopularTourResponseDto(
                                    tourId,
                                    rs.getString("tour_name"),
                                    rs.getString("tour_description"),
                                    rs.getObject("tour_duration") != null ? rs.getInt("tour_duration") : null,
                                    rs.getObject("latitude") != null ? rs.getDouble("latitude") : null,
                                    rs.getObject("longitude") != null ? rs.getDouble("longitude") : null,
                                    rs.getString("start_location"),
                                    rs.getString("end_location"),
                                    rs.getString("tour_type"),
                                    rs.getString("tour_category"),
                                    rs.getString("season"),
                                    rs.getString("tour_status"),
                                    new ArrayList<>(), // images list
                                    new ArrayList<>()  // schedules list
                            );
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    // Handle images (avoid duplicates)
                    String imageUrl = rs.getString("tour_image");
                    String imageName = rs.getString("tour_name"); // careful: this was reused in SQL as ti.name AS tour_name
                    if (imageUrl != null && imageName != null) {
                        boolean imageExists = tour.getImages().stream()
                                .anyMatch(img -> img.getImageUrl().equals(imageUrl));
                        if (!imageExists) {
                            PopularTourImagesDto image = new PopularTourImagesDto(imageName, imageUrl);
                            tour.getImages().add(image);
                        }
                    }

                    // Handle schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (!rs.wasNull()) {
                        popularTourScheduleResponseDto schedule = tour.getSchedules().stream()
                                .filter(s -> s.getScheduleId() == scheduleId)
                                .findFirst()
                                .orElseGet(() -> {
                                    popularTourScheduleResponseDto s;
                                    try {
                                        s = new popularTourScheduleResponseDto(
                                                scheduleId,
                                                rs.getString("schedule_name"),
                                                rs.getObject("assume_start_date") != null ? rs.getDate("assume_start_date").toLocalDate() : null,
                                                rs.getObject("assume_end_date") != null ? rs.getDate("assume_end_date").toLocalDate() : null,
                                                rs.getObject("duration_start") != null ? rs.getInt("duration_start") : null,
                                                rs.getObject("duration_end") != null ? rs.getInt("duration_end") : null,
                                                rs.getString("special_note"),
                                                rs.getString("schedule_description"),
                                                rs.getString("schedule_status"),
                                                new ArrayList<>(), // destinations
                                                new ArrayList<>()  // reviews
                                        );
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    tour.getSchedules().add(s);
                                    return s;
                                });

                        // Handle destination
                        int destinationId = rs.getInt("destination_id");
                        if (!rs.wasNull() && destinationId > 0) {
                            if (schedule.getDestinations().stream().noneMatch(d -> d.getDestinationId() == destinationId)) {
                                TourDestinationResponseDto destination = new TourDestinationResponseDto(
                                        destinationId,
                                        rs.getString("destination_name"),
                                        rs.getString("destination_description"),
                                        rs.getString("destination_location"),
                                        rs.getString("destination_status")
                                );
                                schedule.getDestinations().add(destination);
                            }
                        }

                        // Handle review
                        int reviewId = rs.getInt("review_id");
                        if (!rs.wasNull() && reviewId > 0) {
                            if (schedule.getReviews().stream().noneMatch(r -> r.getReviewId() == reviewId)) {
                                TourReviewResponseDto review = new TourReviewResponseDto(
                                        reviewId,
                                        rs.getString("reviewer_name"),
                                        rs.getString("review"),
                                        rs.getObject("rating") != null ? rs.getDouble("rating") : null,
                                        rs.getString("review_description"),
                                        rs.getObject("number_of_participate") != null ? rs.getInt("number_of_participate") : null,
                                        rs.getString("review_status"),
                                        rs.getTimestamp("review_created_at") != null ? rs.getTimestamp("review_created_at").toLocalDateTime() : null
                                );
                                schedule.getReviews().add(review);
                            }
                        }
                    }
                }

                return new ArrayList<>(tourMap.values());
            });

        } catch (DataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching tours");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching tours");
        }
    }


}
