package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.PackageImageDto;
import com.felicita.model.dto.PackageTypeDto;
import com.felicita.model.dto.TourDto;
import com.felicita.model.response.PackageDetailsResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.queries.PackageQueries;
import com.felicita.queries.PartnerQueries;
import com.felicita.repository.PackageRepository;
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
public class PackageRepositoryImpl implements PackageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PackageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PackageDetailsResponse> getAllPackages() {
        String GET_ALL_PACKAGES = PackageQueries.GET_ALL_PACKAGES;
        try {
            LOGGER.info("Executing query to fetch all packages...");

            List<PackageDetailsResponse> results = jdbcTemplate.query(GET_ALL_PACKAGES, (rs, rowNum) -> {
                PackageDetailsResponse response = new PackageDetailsResponse();

                // Map basic package details
                response.setPackageId(rs.getInt("package_id"));
                response.setPackageName(rs.getString("package_name"));
                response.setPackageDescription(rs.getString("package_description"));
                response.setTotalPrice(rs.getBigDecimal("total_price"));
                response.setDiscountPercentage(rs.getBigDecimal("discount_percentage"));
                response.setPackageStartDate(rs.getDate("package_start_date").toLocalDate());
                response.setPackageEndDate(rs.getDate("package_end_date").toLocalDate());
                response.setColor(rs.getString("color"));
                response.setHoverColor(rs.getString("hover_color"));
                response.setMinPersonCount(rs.getInt("min_person_count"));
                response.setMaxPersonCount(rs.getInt("max_person_count"));
                response.setPackageStatus(rs.getString("package_status"));

                // Map PackageTypeDto
                PackageTypeDto packageType = new PackageTypeDto();
                packageType.setId(rs.getInt("package_type_id"));
                packageType.setName(rs.getString("package_type_name"));
                packageType.setDescription(rs.getString("package_type_description"));
                packageType.setStatus(rs.getString("package_type_status"));
                response.setPackageType(packageType);

                // Map TourDto
                TourDto tour = new TourDto();
                tour.setTourId(rs.getInt("tour_id"));
                tour.setTourName(rs.getString("tour_name"));
                tour.setTourDescription(rs.getString("tour_description"));
                tour.setTourStartDate(rs.getDate("tour_start_date").toLocalDate());
                tour.setTourEndDate(rs.getDate("tour_end_date").toLocalDate());
                tour.setDurationDays(rs.getInt("duration_days"));
                tour.setMaxPeople(rs.getInt("tour_max_people"));
                tour.setMinPeople(rs.getInt("tour_min_people"));
                tour.setPricePerPerson(rs.getBigDecimal("price_per_person"));
                tour.setTourStatus(rs.getString("tour_status"));
                response.setTour(tour);

                // Map PackageImageDto (will be handled in post-processing)
                response.setImages(new ArrayList<>());

                return response;
            });

            // Post-process to aggregate images for each package
            results = aggregatePackageImages(results);

            LOGGER.info("Successfully fetched {} packages.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching packages: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch packages from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching packages: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching packages");
        }
    }

    private List<PackageDetailsResponse> aggregatePackageImages(List<PackageDetailsResponse> packages) {
        Map<Integer, PackageDetailsResponse> packageMap = new LinkedHashMap<>();

        for (PackageDetailsResponse pkg : packages) {
            if (!packageMap.containsKey(pkg.getPackageId())) {
                packageMap.put(pkg.getPackageId(), pkg);
            }

            // Add image if it exists (check if image_id is not null)
            Integer imageId = pkg.getImages().isEmpty() ? null : pkg.getImages().get(0).getImageId();
            if (imageId != null) {
                PackageImageDto image = pkg.getImages().get(0);
                packageMap.get(pkg.getPackageId()).getImages().add(image);
            }
        }

        return new ArrayList<>(packageMap.values());
    }
}
