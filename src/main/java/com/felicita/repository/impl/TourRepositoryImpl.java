package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ReviewResponseDto;
import com.felicita.model.dto.TourImageDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PopularTourResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.TourQueries;
import com.felicita.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public List<PopularTourResponse> getPopularTours() {
        String GET_POPULAR_TOURS = TourQueries.GET_POPULAR_TOURS;
        try {
            LOGGER.info("Executing query to fetch all popular tours...");

            // Use a map to collect tours by tourId
            Map<Integer, PopularTourResponse> tourMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_POPULAR_TOURS, (rs) -> {
                Integer tourId = rs.getInt("tour_id");

                // Check if this tour is already in the map
                PopularTourResponse tour = tourMap.get(tourId);
                if (tour == null) {
                    tour = new PopularTourResponse();
                    tour.setTourId(tourId);
                    tour.setTourName(rs.getString("tour_name"));
                    tour.setTourDescription(rs.getString("tour_description"));
                    tour.setTourType(rs.getString("tour_type"));
                    tour.setTourCategory(rs.getString("tour_category"));
                    tour.setDurationDays(rs.getInt("duration_days"));
                    tour.setStartDate(rs.getObject("start_date", LocalDate.class));
                    tour.setEndDate(rs.getObject("end_date", LocalDate.class));
                    tour.setStartLocation(rs.getString("start_location"));
                    tour.setEndLocation(rs.getString("end_location"));
                    tour.setMaxPeople(rs.getInt("max_people"));
                    tour.setMinPeople(rs.getInt("min_people"));
                    tour.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                    tour.setTourStatus(rs.getString("tour_status"));
                    tour.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                    tour.setCreatedBy((Integer) rs.getObject("created_by"));
                    tour.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
                    tour.setUpdatedBy((Integer) rs.getObject("updated_by"));
                    tour.setTerminatedAt(rs.getObject("terminated_at", LocalDateTime.class));
                    tour.setTerminatedBy((Integer) rs.getObject("terminated_by"));

                    tour.setTourImages(new ArrayList<>());
                    tour.setReviews(new ArrayList<>());
                    tour.setDestinations(new ArrayList<>()); // if destinations are handled separately

                    tourMap.put(tourId, tour);
                }

                // Add TourImageDto if image exists
                Integer imageId = rs.getObject("image_id", Integer.class);
                if (imageId != null) {
                    TourImageDto image = new TourImageDto();
                    image.setId(imageId);
                    image.setName(rs.getString("image_name"));
                    image.setImageUrl(rs.getString("image_url"));
                    image.setDescription(rs.getString("image_description"));
                    image.setStatus(rs.getString("image_status"));
                    if (!tour.getTourImages().contains(image)) {
                        tour.getTourImages().add(image);
                    }
                }

                // Add ReviewResponseDto if review exists
                Integer reviewId = rs.getObject("review_id", Integer.class);
                if (reviewId != null) {
                    ReviewResponseDto review = new ReviewResponseDto();
                    review.setReviewId(reviewId);
                    review.setUserId(rs.getInt("user_id"));
                    review.setRating(rs.getInt("rating"));
                    review.setComment(rs.getString("comment"));
                    review.setReviewStatusId(rs.getInt("review_status_id"));
                    review.setReviewStatusName(rs.getString("review_status_name"));
                    review.setCreatedAt(rs.getObject("review_created_at", LocalDateTime.class));
                    review.setUpdatedAt(rs.getObject("review_updated_at", LocalDateTime.class));

                    if (!tour.getReviews().contains(review)) {
                        tour.getReviews().add(review);
                    }
                }

            });

            List<PopularTourResponse> results = new ArrayList<>(tourMap.values());
            LOGGER.info("Successfully fetched {} popular tours.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching popular tours: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch popular tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching popular tours: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching popular tours");
        }
    }


}
