package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.*;
import com.felicita.queries.PackageQueries;
import com.felicita.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<PackageResponseDto> getAllPackages() {
        try {
            return jdbcTemplate.query(PackageQueries.GET_ALL_PACKAGES, (ResultSet rs) -> {
                Map<Integer, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    int packageId = rs.getInt("package_id");

                    // Get or create package
                    PackageResponseDto pkg = packageMap.get(packageId);
                    if (pkg == null) {
                        pkg = new PackageResponseDto();
                        pkg.setPackageId(packageId);
                        pkg.setPackageName(rs.getString("package_name"));
                        pkg.setPackageDescription(rs.getString("package_description"));
                        pkg.setTotalPrice(rs.getBigDecimal("total_price"));
                        pkg.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                        pkg.setStartDate(rs.getObject("start_date", LocalDate.class));
                        pkg.setEndDate(rs.getObject("end_date", LocalDate.class));
                        pkg.setColor(rs.getString("color"));
                        pkg.setHoverColor(rs.getString("hover_color"));
                        pkg.setMinPersonCount(rs.getObject("min_person_count", Integer.class));
                        pkg.setMaxPersonCount(rs.getObject("max_person_count", Integer.class));
                        pkg.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                        pkg.setPackageStatus(rs.getString("package_status"));

                        // New fields
                        pkg.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                        pkg.setCreatedBy(rs.getObject("created_by", Integer.class));
                        pkg.setPackageTypeName(rs.getString("package_type_name"));
                        pkg.setPackageTypeDescription(rs.getString("package_type_description"));
                        pkg.setPackageTypeStatus(rs.getString("package_type_status"));

                        // Tour info
                        pkg.setTourId(rs.getInt("tour_id"));
                        pkg.setTourName(rs.getString("tour_name"));
                        pkg.setTourDescription(rs.getString("tour_description"));
                        pkg.setDuration(rs.getObject("duration", Integer.class));
                        pkg.setLatitude(rs.getObject("latitude", Double.class));
                        pkg.setLongitude(rs.getObject("longitude", Double.class));
                        pkg.setStartLocation(rs.getString("start_location"));
                        pkg.setEndLocation(rs.getString("end_location"));
                        pkg.setTourStatus(rs.getString("tour_status"));

                        pkg.setSchedules(new ArrayList<>());
                        pkg.setFeatures(new ArrayList<>());
                        pkg.setImages(new ArrayList<>());
                        packageMap.put(packageId, pkg);
                    }

                    // Schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (scheduleId != 0 && rs.getString("schedule_name") != null) {
                        PackageScheduleResponseDto schedule = new PackageScheduleResponseDto();
                        schedule.setScheduleId(scheduleId);
                        schedule.setScheduleName(rs.getString("schedule_name"));
                        schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                        schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                        schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                        schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                        schedule.setSpecialNote(rs.getString("schedule_special_note"));
                        schedule.setScheduleDescription(rs.getString("schedule_description"));

                        if (pkg.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            pkg.getSchedules().add(schedule);
                        }
                    }

                    // Feature
                    int featureId = rs.getInt("feature_id");
                    if (featureId != 0 && rs.getString("feature_name") != null) {
                        PackageFeatureResponseDto feature = new PackageFeatureResponseDto();
                        feature.setFeatureId(featureId);
                        feature.setFeatureName(rs.getString("feature_name"));
                        feature.setFeatureValue(rs.getString("feature_value"));
                        feature.setFeatureDescription(rs.getString("feature_description"));
                        feature.setColor(rs.getString("feature_color"));
                        feature.setSpecialNote(rs.getString("feature_special_note"));

                        if (pkg.getFeatures().stream().noneMatch(f -> f.getFeatureId() == featureId)) {
                            pkg.getFeatures().add(feature);
                        }
                    }

                    // Image
                    int imageId = rs.getInt("image_id");
                    if (imageId != 0 && rs.getString("image_url") != null) {
                        PackageImageResponseDto image = new PackageImageResponseDto();
                        image.setImageId(imageId);
                        image.setImageName(rs.getString("image_name"));
                        image.setImageDescription(rs.getString("image_description"));
                        image.setImageUrl(rs.getString("image_url"));
                        image.setColor(rs.getString("image_color"));

                        if (pkg.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            pkg.getImages().add(image);
                        }
                    }
                }

                return new ArrayList<>(packageMap.values());
            });
        } catch (DataAccessException ex) {
            LOGGER.error(ex.toString());
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching packages");
        } catch (Exception ex) {
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching packages");
        }
    }

    @Override
    public PackageResponseDto getPackageDetailsById(String packageId) {
        try {
            return jdbcTemplate.query(PackageQueries.GET_PACKAGE_DETAILS_BY_PACKAGE_ID, new Object[]{packageId}, (ResultSet rs) -> {
                Map<Integer, PackageResponseDto> packageMap = new HashMap<>();

                while (rs.next()) {
                    int pkgId = rs.getInt("package_id");

                    // Get or create package object
                    PackageResponseDto pkg = packageMap.get(pkgId);
                    if (pkg == null) {
                        pkg = new PackageResponseDto();
                        pkg.setPackageId(pkgId);
                        pkg.setPackageName(rs.getString("package_name"));
                        pkg.setPackageDescription(rs.getString("package_description"));
                        pkg.setTotalPrice(rs.getBigDecimal("total_price"));
                        pkg.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                        pkg.setStartDate(rs.getObject("start_date", LocalDate.class));
                        pkg.setEndDate(rs.getObject("end_date", LocalDate.class));
                        pkg.setColor(rs.getString("color"));
                        pkg.setHoverColor(rs.getString("hover_color"));
                        pkg.setMinPersonCount(rs.getObject("min_person_count", Integer.class));
                        pkg.setMaxPersonCount(rs.getObject("max_person_count", Integer.class));
                        pkg.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                        pkg.setPackageStatus(rs.getString("package_status"));
                        pkg.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                        pkg.setCreatedBy(rs.getObject("created_by", Integer.class));

                        // Package type info
                        pkg.setPackageTypeName(rs.getString("package_type_name"));
                        pkg.setPackageTypeDescription(rs.getString("package_type_description"));
                        pkg.setPackageTypeStatus(rs.getString("package_type_status"));

                        // Tour info
                        pkg.setTourId(rs.getObject("tour_id", Integer.class));
                        pkg.setTourName(rs.getString("tour_name"));
                        pkg.setTourDescription(rs.getString("tour_description"));
                        pkg.setDuration(rs.getObject("duration", Integer.class));
                        pkg.setLatitude(rs.getObject("latitude", Double.class));
                        pkg.setLongitude(rs.getObject("longitude", Double.class));
                        pkg.setStartLocation(rs.getString("start_location"));
                        pkg.setEndLocation(rs.getString("end_location"));
                        pkg.setTourStatus(rs.getString("tour_status"));

                        // Initialize nested lists
                        pkg.setSchedules(new ArrayList<>());
                        pkg.setFeatures(new ArrayList<>());
                        pkg.setImages(new ArrayList<>());

                        packageMap.put(pkgId, pkg);
                    }

                    // Schedule
                    int scheduleId = rs.getInt("schedule_id");
                    if (!rs.wasNull() && rs.getString("schedule_name") != null) {
                        if (pkg.getSchedules().stream().noneMatch(s -> s.getScheduleId() == scheduleId)) {
                            PackageScheduleResponseDto schedule = new PackageScheduleResponseDto();
                            schedule.setScheduleId(scheduleId);
                            schedule.setScheduleName(rs.getString("schedule_name"));
                            schedule.setAssumeStartDate(rs.getObject("assume_start_date", LocalDate.class));
                            schedule.setAssumeEndDate(rs.getObject("assume_end_date", LocalDate.class));
                            schedule.setDurationStart(rs.getObject("duration_start", Integer.class));
                            schedule.setDurationEnd(rs.getObject("duration_end", Integer.class));
                            schedule.setSpecialNote(rs.getString("schedule_special_note"));
                            schedule.setScheduleDescription(rs.getString("schedule_description"));
                            pkg.getSchedules().add(schedule);
                        }
                    }

                    // Feature
                    int featureId = rs.getInt("feature_id");
                    if (!rs.wasNull() && rs.getString("feature_name") != null) {
                        if (pkg.getFeatures().stream().noneMatch(f -> f.getFeatureId() == featureId)) {
                            PackageFeatureResponseDto feature = new PackageFeatureResponseDto();
                            feature.setFeatureId(featureId);
                            feature.setFeatureName(rs.getString("feature_name"));
                            feature.setFeatureValue(rs.getString("feature_value"));
                            feature.setFeatureDescription(rs.getString("feature_description"));
                            feature.setColor(rs.getString("feature_color"));
                            feature.setSpecialNote(rs.getString("feature_special_note"));
                            pkg.getFeatures().add(feature);
                        }
                    }

                    // Image
                    int imageId = rs.getInt("image_id");
                    if (!rs.wasNull() && rs.getString("image_url") != null) {
                        if (pkg.getImages().stream().noneMatch(i -> i.getImageId() == imageId)) {
                            PackageImageResponseDto image = new PackageImageResponseDto();
                            image.setImageId(imageId);
                            image.setImageName(rs.getString("image_name"));
                            image.setImageDescription(rs.getString("image_description"));
                            image.setImageUrl(rs.getString("image_url"));
                            image.setColor(rs.getString("image_color"));
                            pkg.getImages().add(image);
                        }
                    }
                }

                // Return the first (and only) package
                return packageMap.values().stream().findFirst().orElse(null);
            });
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching package details", ex);
            throw new DataNotFoundErrorExceptionHandler("Database error while fetching package details");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching package details", ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error while fetching package details");
        }
    }



}
