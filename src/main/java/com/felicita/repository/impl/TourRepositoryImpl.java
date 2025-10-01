package com.felicita.repository.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.TourImageResponseDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.dto.TourScheduleResponseDto;
import com.felicita.queries.TourQueries;
import com.felicita.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
