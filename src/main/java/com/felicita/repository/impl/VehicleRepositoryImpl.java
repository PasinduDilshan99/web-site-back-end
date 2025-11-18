package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.VehicleDetailResponse;
import com.felicita.model.response.VehicleResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.VehicleQueries;
import com.felicita.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.felicita.queries.VehicleQueries.GET_VEHICLE_ASSIGNMENTS;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VehicleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<VehicleResponse> getActiveVehicles() {
        String GET_VEHICLES_DETAILS = VehicleQueries.GET_VEHICLES_DETAILS;
        try {
            LOGGER.info("Executing query to fetch all vehicles...");

            // Use a LinkedHashMap to maintain insertion order
            Map<Long, VehicleResponse> vehicleMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_VEHICLES_DETAILS, (rs) -> {
                Long vehicleId = rs.getLong("vehicle_id");

                // Get or create VehicleResponse
                VehicleResponse vehicle = vehicleMap.computeIfAbsent(vehicleId, id -> {
                    VehicleResponse v = new VehicleResponse();
                    v.setVehicleId(vehicleId);
                    try {
                        v.setRegistrationNumber(rs.getString("registration_number"));
                        v.setSpecificationId(rs.getLong("specification_id"));
                        v.setStatus(rs.getString("status_name"));
                        v.setPurchaseDate(rs.getDate("purchase_date") != null ? rs.getDate("purchase_date").toLocalDate() : null);
                        v.setPurchasePrice(rs.getBigDecimal("purchase_price"));
                        v.setCreatedAt(rs.getTimestamp("vehicle_created_at") != null ? rs.getTimestamp("vehicle_created_at").toLocalDateTime() : null);
                        v.setCreatedBy(rs.getLong("vehicle_created_by"));
                        v.setUpdatedAt(rs.getTimestamp("vehicle_updated_at") != null ? rs.getTimestamp("vehicle_updated_at").toLocalDateTime() : null);
                        v.setUpdatedBy(rs.getLong("vehicle_updated_by"));
                        v.setTerminatedAt(rs.getTimestamp("vehicle_terminated_at") != null ? rs.getTimestamp("vehicle_terminated_at").toLocalDateTime() : null);
                        v.setTerminatedBy(rs.getLong("vehicle_terminated_by"));

                        // Specification
                        VehicleResponse.Specification spec = new VehicleResponse.Specification();
                        spec.setMake(rs.getString("make"));
                        spec.setModel(rs.getString("model"));
                        spec.setYear(rs.getInt("year"));
                        spec.setGeneration(rs.getString("generation"));
                        spec.setBodyType(rs.getString("body_type"));
                        spec.setPrice(rs.getBigDecimal("specification_price"));
                        spec.setEngineType(rs.getString("engine_type"));
                        spec.setEngineCapacity(rs.getString("engine_capacity"));
                        spec.setHorsepowerHp(rs.getBigDecimal("horsepower_hp"));
                        spec.setTorqueNm(rs.getBigDecimal("torque_nm"));
                        spec.setTransmissionTypeId(rs.getLong("transmission_type_id"));
                        spec.setFuelTypeId(rs.getLong("fuel_type_id"));
                        spec.setElectricRangeKm(rs.getBigDecimal("electric_range_km"));
                        spec.setDrivetrain(rs.getString("drivetrain"));
                        spec.setTopSpeedKmh(rs.getBigDecimal("top_speed_kmh"));
                        spec.setAcceleration0To100(rs.getBigDecimal("acceleration_0_100"));
                        spec.setCo2EmissionsGKm(rs.getBigDecimal("co2_emissions_g_km"));
                        spec.setDoors(rs.getInt("doors"));
                        spec.setSeatCapacity(rs.getInt("seat_capacity"));
                        spec.setDimensions(rs.getString("dimensions"));
                        spec.setWheelbaseMm(rs.getBigDecimal("wheelbase_mm"));
                        spec.setWeightKg(rs.getBigDecimal("weight_kg"));
                        spec.setWheelSize(rs.getString("wheel_size"));
                        spec.setTireType(rs.getString("tire_type"));
                        spec.setUpholsteryType(rs.getString("upholstery_type"));
                        spec.setAcTypeId(rs.getLong("ac_type_id"));
                        spec.setSunroofType(rs.getString("sunroof_type"));
                        spec.setCruiseControlType(rs.getString("cruise_control_type"));
                        spec.setEntertainmentFeatures(rs.getString("entertainment_features"));
                        spec.setComfortFeatures(rs.getString("comfort_features"));
                        spec.setNcapSafetyRating(rs.getInt("ncap_safety_rating"));
                        spec.setAirbagsCount(rs.getInt("airbags_count"));
                        spec.setParkingCamera(rs.getString("parking_camera"));
                        spec.setLaneDepartureWarning(rs.getBoolean("lane_departure_warning"));
                        spec.setSafetyFeatures(rs.getString("safety_features"));
                        spec.setFuelTankCapacityLiters(rs.getBigDecimal("fuel_tank_capacity_liters"));
                        spec.setWarrantyYears(rs.getInt("warranty_years"));
                        spec.setImageUrl(rs.getString("specification_image_url"));
                        spec.setIsActive(rs.getBoolean("specification_active"));
                        spec.setCreatedAt(rs.getTimestamp("specification_created_at") != null ? rs.getTimestamp("specification_created_at").toLocalDateTime() : null);
                        spec.setCreatedBy(rs.getLong("specification_created_by"));
                        spec.setUpdatedAt(rs.getTimestamp("specification_updated_at") != null ? rs.getTimestamp("specification_updated_at").toLocalDateTime() : null);
                        spec.setUpdatedBy(rs.getLong("specification_updated_by"));
                        spec.setTerminatedAt(rs.getTimestamp("specification_terminated_at") != null ? rs.getTimestamp("specification_terminated_at").toLocalDateTime() : null);
                        spec.setTerminatedBy(rs.getLong("specification_terminated_by"));
                        v.setSpecification(spec);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    // Initialize lists
                    v.setImages(new ArrayList<>());
                    v.setUsageLogs(new ArrayList<>());

                    return v;
                });

                // Add vehicle image if exists
                if (rs.getLong("vehicle_image_id") != 0) {
                    VehicleResponse.VehicleImage img = new VehicleResponse.VehicleImage();
                    img.setImageId(rs.getLong("vehicle_image_id"));
                    img.setImageUrl(rs.getString("vehicle_image_url"));
                    img.setImageName(rs.getString("vehicle_image_name"));
                    img.setDescription(rs.getString("vehicle_image_description"));
                    img.setCreatedAt(rs.getTimestamp("image_created_at") != null ? rs.getTimestamp("image_created_at").toLocalDateTime() : null);
                    img.setCreatedBy(rs.getLong("image_created_by"));
                    img.setUpdatedAt(rs.getTimestamp("image_updated_at") != null ? rs.getTimestamp("image_updated_at").toLocalDateTime() : null);
                    img.setUpdatedBy(rs.getLong("image_updated_by"));
                    img.setTerminatedAt(rs.getTimestamp("image_terminated_at") != null ? rs.getTimestamp("image_terminated_at").toLocalDateTime() : null);
                    img.setTerminatedBy(rs.getLong("image_terminated_by"));
                    vehicle.getImages().add(img);
                }

                // Add usage log if exists
                if (rs.getLong("usage_id") != 0) {
                    VehicleResponse.UsageLog log = new VehicleResponse.UsageLog();
                    log.setUsageId(rs.getLong("usage_id"));
                    log.setPackageId(rs.getLong("package_id"));
                    log.setTourId(rs.getLong("tour_id"));
                    log.setStartDatetime(rs.getTimestamp("start_datetime") != null ? rs.getTimestamp("start_datetime").toLocalDateTime() : null);
                    log.setEndDatetime(rs.getTimestamp("end_datetime") != null ? rs.getTimestamp("end_datetime").toLocalDateTime() : null);
                    log.setStartOdometer(rs.getInt("start_odometer"));
                    log.setEndOdometer(rs.getInt("end_odometer"));
                    log.setRouteDescription(rs.getString("route_description"));
                    log.setPurpose(rs.getString("usage_purpose"));
                    log.setFuelUsedLiters(rs.getBigDecimal("fuel_used_liters"));
                    log.setRemarks(rs.getString("usage_remarks"));
                    log.setCreatedAt(rs.getTimestamp("usage_created_at") != null ? rs.getTimestamp("usage_created_at").toLocalDateTime() : null);
                    log.setCreatedBy(rs.getLong("usage_created_by"));
                    log.setUpdatedAt(rs.getTimestamp("usage_updated_at") != null ? rs.getTimestamp("usage_updated_at").toLocalDateTime() : null);
                    log.setUpdatedBy(rs.getLong("usage_updated_by"));
                    log.setTerminatedAt(rs.getTimestamp("usage_terminated_at") != null ? rs.getTimestamp("usage_terminated_at").toLocalDateTime() : null);
                    log.setTerminatedBy(rs.getLong("usage_terminated_by"));
                    vehicle.getUsageLogs().add(log);
                }
            });

            List<VehicleResponse> results = new ArrayList<>(vehicleMap.values());
            LOGGER.info("Successfully fetched {} vehicles.", results.size());
            return results;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching vehicles: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch vehicles from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching vehicles: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching vehicles");
        }
    }

    @Override
    public List<VehicleDetailResponse> getVehicleDetailsById(String vehicleId) {
        String GET_VEHICLES_DETAILS_BY_ID = VehicleQueries.GET_VEHICLES_DETAILS_BY_ID;
        try {
            LOGGER.info("Executing query to fetch vehicle details for vehicle ID: {}", vehicleId);

            List<VehicleDetailResponse> results = jdbcTemplate.query(GET_VEHICLES_DETAILS_BY_ID,
                    new Object[]{vehicleId},
                    (rs, rowNum) -> {
                        // Map basic vehicle information
                        VehicleDetailResponse response = VehicleDetailResponse.builder()
                                .vehicleId(rs.getInt("vehicle_id"))
                                .registrationNumber(rs.getString("registration_number"))
                                .statusId(rs.getInt("status_id"))
                                .statusName(rs.getString("status_name"))
                                .vehiclePurchaseDate(rs.getDate("vehicle_purchase_date") != null ?
                                        rs.getDate("vehicle_purchase_date").toLocalDate() : null)
                                .vehiclePurchasePrice(rs.getBigDecimal("vehicle_purchase_price"))
                                .vehicleCreatedAt(rs.getTimestamp("vehicle_created_at") != null ?
                                        rs.getTimestamp("vehicle_created_at").toLocalDateTime() : null)
                                .vehicleCreatedBy(rs.getInt("vehicle_created_by"))
                                .vehicleUpdatedAt(rs.getTimestamp("vehicle_updated_at") != null ?
                                        rs.getTimestamp("vehicle_updated_at").toLocalDateTime() : null)
                                .vehicleUpdatedBy(rs.getInt("vehicle_updated_by"))
                                .vehicleTerminatedAt(rs.getTimestamp("vehicle_terminated_at") != null ?
                                        rs.getTimestamp("vehicle_terminated_at").toLocalDateTime() : null)
                                .vehicleTerminatedBy(rs.getInt("vehicle_terminated_by"))
                                .ownerId(rs.getInt("owner_id"))
                                .assignedDriverId(rs.getInt("assigned_driver_id"))
                                .build();

                        // Map VehicleSpecification
                        VehicleDetailResponse.VehicleSpecification specification = VehicleDetailResponse.VehicleSpecification.builder()
                                .specificationId(rs.getInt("specification_id"))
                                .make(rs.getString("make"))
                                .model(rs.getString("model"))
                                .vehicleYear(rs.getInt("vehicle_year"))
                                .generation(rs.getString("generation"))
                                .bodyType(rs.getString("body_type"))
                                .specificationPrice(rs.getBigDecimal("specification_price"))
                                .engineType(rs.getString("engine_type"))
                                .engineCapacity(rs.getString("engine_capacity"))
                                .horsepowerHp(rs.getBigDecimal("horsepower_hp"))
                                .torqueNm(rs.getBigDecimal("torque_nm"))
                                .transmissionTypeId(rs.getInt("transmission_type_id"))
                                .transmissionTypeName(rs.getString("transmission_type_name"))
                                .fuelTypeId(rs.getInt("fuel_type_id"))
                                .fuelTypeName(rs.getString("fuel_type_name"))
                                .electricRangeKm(rs.getBigDecimal("electric_range_km"))
                                .drivetrain(rs.getString("drivetrain"))
                                .topSpeedKmh(rs.getBigDecimal("top_speed_kmh"))
                                .acceleration0100(rs.getBigDecimal("acceleration_0_100"))
                                .co2EmissionsGkm(rs.getBigDecimal("co2_emissions_g_km"))
                                .doors(rs.getInt("doors"))
                                .seatCapacity(rs.getInt("seat_capacity"))
                                .dimensions(rs.getString("dimensions"))
                                .wheelbaseMm(rs.getBigDecimal("wheelbase_mm"))
                                .weightKg(rs.getBigDecimal("weight_kg"))
                                .wheelSize(rs.getString("wheel_size"))
                                .tireType(rs.getString("tire_type"))
                                .upholsteryType(rs.getString("upholstery_type"))
                                .acTypeId(rs.getInt("ac_type_id"))
                                .acType(rs.getString("ac_type"))
                                .sunroofType(rs.getString("sunroof_type"))
                                .cruiseControlType(rs.getString("cruise_control_type"))
                                .entertainmentFeatures(rs.getString("entertainment_features"))
                                .comfortFeatures(rs.getString("comfort_features"))
                                .ncapSafetyRating(rs.getInt("ncap_safety_rating"))
                                .airbagsCount(rs.getInt("airbags_count"))
                                .parkingCamera(rs.getString("parking_camera"))
                                .laneDepartureWarning(rs.getBoolean("lane_departure_warning"))
                                .safetyFeatures(rs.getString("safety_features"))
                                .fuelTankCapacityLiters(rs.getBigDecimal("fuel_tank_capacity_liters"))
                                .warrantyYears(rs.getInt("warranty_years"))
                                .specificationImageUrl(rs.getString("specification_image_url"))
                                .specificationActive(rs.getBoolean("specification_active"))
                                .build();
                        response.setSpecification(specification);

                        // Map VehicleDetails
                        VehicleDetailResponse.VehicleDetails details = VehicleDetailResponse.VehicleDetails.builder()
                                .chassisNumber(rs.getString("chassis_number"))
                                .engineNumber(rs.getString("engine_number"))
                                .insurancePolicyNumber(rs.getString("insurance_policy_number"))
                                .insuranceExpiryDate(rs.getDate("insurance_expiry_date") != null ?
                                        rs.getDate("insurance_expiry_date").toLocalDate() : null)
                                .emissionTestNumber(rs.getString("emission_test_number"))
                                .emissionExpiryDate(rs.getDate("emission_expiry_date") != null ?
                                        rs.getDate("emission_expiry_date").toLocalDate() : null)
                                .permitNumber(rs.getString("permit_number"))
                                .permitExpiryDate(rs.getDate("permit_expiry_date") != null ?
                                        rs.getDate("permit_expiry_date").toLocalDate() : null)
                                .warrantyExpiryDate(rs.getDate("warranty_expiry_date") != null ?
                                        rs.getDate("warranty_expiry_date").toLocalDate() : null)
                                .gpsTrackingId(rs.getString("gps_tracking_id"))
                                .build();
                        response.setDetails(details);

                        // Map VehicleImage (will be aggregated later)
                        if (rs.getInt("vehicle_image_id") != 0) {
                            VehicleDetailResponse.VehicleImage vehicleImage = VehicleDetailResponse.VehicleImage.builder()
                                    .vehicleImageId(rs.getInt("vehicle_image_id"))
                                    .vehicleImageUrl(rs.getString("vehicle_image_url"))
                                    .vehicleImageName(rs.getString("vehicle_image_name"))
                                    .vehicleImageDescription(rs.getString("vehicle_image_description"))
                                    .build();
                            response.setVehicleImages(new ArrayList<>());
                            response.getVehicleImages().add(vehicleImage);
                        }

                        // Map SpecificationImage (will be aggregated later)
                        if (rs.getInt("specification_image_id") != 0) {
                            VehicleDetailResponse.SpecificationImage specImage = VehicleDetailResponse.SpecificationImage.builder()
                                    .specificationImageId(rs.getInt("specification_image_id"))
                                    .specificationImageUrl(rs.getString("specification_image_url"))
                                    .specificationImageName(rs.getString("specification_image_name"))
                                    .specificationImageDescription(rs.getString("specification_image_description"))
                                    .build();
                            response.setSpecificationImages(new ArrayList<>());
                            response.getSpecificationImages().add(specImage);
                        }

                        // Parse latest usage logs
                        String usageLogsStr = rs.getString("latest_usage_logs");
                        if (usageLogsStr != null && !usageLogsStr.trim().isEmpty()) {
                            List<VehicleDetailResponse.UsageLog> usageLogs = parseUsageLogs(usageLogsStr);
                            response.setUsageLogs(usageLogs);
                        }

                        // Map ServiceHistory
                        if (rs.getInt("service_id") != 0) {
                            VehicleDetailResponse.ServiceHistory serviceHistory = VehicleDetailResponse.ServiceHistory.builder()
                                    .serviceId(rs.getInt("service_id"))
                                    .serviceDate(rs.getDate("service_date") != null ?
                                            rs.getDate("service_date").toLocalDate() : null)
                                    .serviceCenter(rs.getString("service_center"))
                                    .serviceType(rs.getString("service_type"))
                                    .serviceOdometer(rs.getInt("service_odometer"))
                                    .serviceCost(rs.getBigDecimal("service_cost"))
                                    .serviceDescription(rs.getString("service_description"))
                                    .nextServiceDue(rs.getDate("next_service_due") != null ?
                                            rs.getDate("next_service_due").toLocalDate() : null)
                                    .serviceImageUrl(rs.getString("service_image_url"))
                                    .serviceImageDescription(rs.getString("service_image_description"))
                                    .build();
                            response.setLatestService(serviceHistory);
                        }

                        // Map FuelRecord
                        if (rs.getInt("fuel_record_id") != 0) {
                            VehicleDetailResponse.FuelRecord fuelRecord = VehicleDetailResponse.FuelRecord.builder()
                                    .fuelRecordId(rs.getInt("fuel_record_id"))
                                    .refuelDate(rs.getDate("refuel_date") != null ?
                                            rs.getDate("refuel_date").toLocalDate() : null)
                                    .refuelFuelTypeId(rs.getInt("refuel_fuel_type_id"))
                                    .refuelFuelType(rs.getString("refuel_fuel_type"))
                                    .quantityLiters(rs.getBigDecimal("quantity_liters"))
                                    .fuelCost(rs.getBigDecimal("fuel_cost"))
                                    .fuelOdometer(rs.getInt("fuel_odometer"))
                                    .refuelStation(rs.getString("refuel_station"))
                                    .build();
                            response.setLatestFuelRecord(fuelRecord);
                        }

                        return response;
                    });

            // Aggregate results to combine images from multiple rows
            List<VehicleDetailResponse> aggregatedResults = aggregateVehicleDetails(results);

            // Fetch assignments separately for each vehicle and add to response
            for (VehicleDetailResponse vehicle : aggregatedResults) {
                List<VehicleDetailResponse.Assignment> assignments = getVehicleAssignments(vehicle.getVehicleId());
                vehicle.setAssignments(assignments);
            }

            LOGGER.info("Successfully fetched vehicle details for vehicle ID: {}. Found {} records.", vehicleId, aggregatedResults.size());
            return aggregatedResults;

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching vehicle details for vehicle ID {}: {}", vehicleId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch vehicle details from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching vehicle details for vehicle ID {}: {}", vehicleId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching vehicle details");
        }
    }

    // Helper method to get vehicle assignments separately
    private List<VehicleDetailResponse.Assignment> getVehicleAssignments(Integer vehicleId) {
        String GET_VEHICLE_ASSIGNMENTS = """
        SELECT 
            assignment_id,
            driver_id,
            start_date,
            end_date,
            purpose,
            remarks
        FROM vehicle_assignments 
        WHERE vehicle_id = ?
        ORDER BY start_date DESC
        LIMIT 10
        """;

        try {
            return jdbcTemplate.query(GET_VEHICLE_ASSIGNMENTS,
                    new Object[]{vehicleId},
                    (rs, rowNum) -> VehicleDetailResponse.Assignment.builder()
                            .assignmentId(rs.getInt("assignment_id"))
                            .driverId(rs.getInt("driver_id"))
                            .assignmentStartDate(rs.getDate("start_date") != null ?
                                    rs.getDate("start_date").toLocalDate() : null)
                            .assignmentEndDate(rs.getDate("end_date") != null ?
                                    rs.getDate("end_date").toLocalDate() : null)
                            .assignmentPurpose(rs.getString("purpose"))
                            .assignmentRemarks(rs.getString("remarks"))
                            .build()
            );
        } catch (Exception e) {
            LOGGER.warn("Error fetching assignments for vehicle ID {}: {}", vehicleId, e.getMessage());
            return new ArrayList<>();
        }
    }

    // Helper method to parse usage logs from concatenated string
    private List<VehicleDetailResponse.UsageLog> parseUsageLogs(String usageLogsStr) {
        List<VehicleDetailResponse.UsageLog> usageLogs = new ArrayList<>();
        try {
            String[] usageGroups = usageLogsStr.split("\\|\\|\\|\\|");
            for (String group : usageGroups) {
                String[] parts = group.split("\\|\\|\\|");
                if (parts.length >= 11) {
                    VehicleDetailResponse.UsageLog usageLog = VehicleDetailResponse.UsageLog.builder()
                            .usageId(Integer.parseInt(parts[0]))
                            .packageId(parts[1].isEmpty() ? null : Integer.parseInt(parts[1]))
                            .tourId(parts[2].isEmpty() ? null : Integer.parseInt(parts[2]))
                            .usageStartDatetime(parts[3].isEmpty() ? null : LocalDateTime.parse(parts[3].replace(" ", "T")))
                            .usageEndDatetime(parts[4].isEmpty() ? null : LocalDateTime.parse(parts[4].replace(" ", "T")))
                            .startOdometer(parts[5].isEmpty() ? null : Integer.parseInt(parts[5]))
                            .endOdometer(parts[6].isEmpty() ? null : Integer.parseInt(parts[6]))
                            .routeDescription(parts[7].isEmpty() ? null : parts[7])
                            .usagePurpose(parts[8].isEmpty() ? null : parts[8])
                            .fuelUsedLiters(parts[9].isEmpty() ? null : new BigDecimal(parts[9]))
                            .usageRemarks(parts[10].isEmpty() ? null : parts[10])
                            .build();
                    usageLogs.add(usageLog);
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Error parsing usage logs string: {}", usageLogsStr, e);
        }
        return usageLogs;
    }

    // Helper method to aggregate vehicle details from multiple rows
    private List<VehicleDetailResponse> aggregateVehicleDetails(List<VehicleDetailResponse> results) {
        if (results.isEmpty()) {
            return results;
        }

        Map<Integer, VehicleDetailResponse> aggregatedMap = new HashMap<>();

        for (VehicleDetailResponse response : results) {
            Integer vehicleId = response.getVehicleId();

            if (!aggregatedMap.containsKey(vehicleId)) {
                // First time seeing this vehicle, initialize lists
                if (response.getVehicleImages() == null) {
                    response.setVehicleImages(new ArrayList<>());
                } else {
                    // Ensure we don't have duplicates in the first entry
                    response.setVehicleImages(new ArrayList<>(response.getVehicleImages()));
                }

                if (response.getSpecificationImages() == null) {
                    response.setSpecificationImages(new ArrayList<>());
                } else {
                    // Ensure we don't have duplicates in the first entry
                    response.setSpecificationImages(new ArrayList<>(response.getSpecificationImages()));
                }
                aggregatedMap.put(vehicleId, response);
            } else {
                // Merge images from duplicate rows
                VehicleDetailResponse existing = aggregatedMap.get(vehicleId);

                // Merge vehicle images
                if (response.getVehicleImages() != null && !response.getVehicleImages().isEmpty()) {
                    VehicleDetailResponse.VehicleImage newImage = response.getVehicleImages().get(0);
                    boolean imageExists = existing.getVehicleImages().stream()
                            .anyMatch(img -> img.getVehicleImageId() != null &&
                                    img.getVehicleImageId().equals(newImage.getVehicleImageId()));
                    if (!imageExists) {
                        existing.getVehicleImages().add(newImage);
                    }
                }

                // Merge specification images
                if (response.getSpecificationImages() != null && !response.getSpecificationImages().isEmpty()) {
                    VehicleDetailResponse.SpecificationImage newSpecImage = response.getSpecificationImages().get(0);
                    boolean specImageExists = existing.getSpecificationImages().stream()
                            .anyMatch(img -> img.getSpecificationImageId() != null &&
                                    img.getSpecificationImageId().equals(newSpecImage.getSpecificationImageId()));
                    if (!specImageExists) {
                        existing.getSpecificationImages().add(newSpecImage);
                    }
                }
            }
        }

        return new ArrayList<>(aggregatedMap.values());
    }

}
