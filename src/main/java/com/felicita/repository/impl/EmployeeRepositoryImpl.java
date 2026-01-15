package com.felicita.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.*;
import com.felicita.queries.CouponQueries;
import com.felicita.queries.EmployeeQueries;
import com.felicita.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeWithSocialMediaResponse> getEmployeeWithSocailMedia() {

        String GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS = EmployeeQueries.GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS;

        try {

            Map<Long, EmployeeWithSocialMediaResponse> employeeMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS, rs -> {

                while (rs.next()) {

                    Long employeeId = rs.getLong("employee_id");

                    EmployeeWithSocialMediaResponse employee = employeeMap.get(employeeId);

                    // ✅ Create employee ONLY once
                    if (employee == null) {

                        java.sql.Date dobDate = rs.getDate("date_of_birth");
                        java.sql.Date hireDate = rs.getDate("hire_date");

                        employee = new EmployeeWithSocialMediaResponse(
                                employeeId,
                                rs.getString("image_url"),
                                rs.getString("employee_code"),
                                rs.getString("full_name"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                dobDate != null ? dobDate.toLocalDate() : null,           // ✅ NULL SAFE
                                rs.getString("employee_type"),
                                rs.getString("department_name"),
                                rs.getString("designation_name"),
                                hireDate != null ? hireDate.toLocalDate() : null,         // ✅ NULL SAFE
                                rs.getString("work_location"),
                                rs.getObject("salary") != null ? rs.getDouble("salary") : null, // ✅ NULL SAFE
                                new ArrayList<>()
                        );

                        employeeMap.put(employeeId, employee);
                    }

                    // ✅ Add social media only if exists
                    String platformName = rs.getString("platform_name");

                    if (platformName != null) {

                        EmployeeWithSocialMediaResponse.SocialMediaProfile socialProfile =
                                new EmployeeWithSocialMediaResponse.SocialMediaProfile(
                                        platformName,
                                        rs.getString("username"),
                                        rs.getString("profile_url"),
                                        rs.getBoolean("is_primary"),
                                        rs.getBoolean("is_public"),
                                        rs.getBoolean("verified"),
                                        rs.getInt("follower_count")
                                );

                        employee.getSocialMediaProfiles().add(socialProfile);
                    }
                }
            });

            return new ArrayList<>(employeeMap.values());

        } catch (Exception ex) {
            LOGGER.error("Error fetching employee with social media details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee with social media details");
        }
    }

    @Override
    public List<EmployeeWithSocialMediaResponse> getALlEmployeeWithSocailMedia() {
        String GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS = EmployeeQueries.GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS;

        try {

            Map<Long, EmployeeWithSocialMediaResponse> employeeMap = new LinkedHashMap<>();

            jdbcTemplate.query(GET_EMPLOYEES_WITH_SOCIAL_MEDIA_LINKS, rs -> {

                while (rs.next()) {

                    Long employeeId = rs.getLong("employee_id");

                    EmployeeWithSocialMediaResponse employee = employeeMap.get(employeeId);

                    // ✅ Create employee ONLY once
                    if (employee == null) {

                        java.sql.Date dobDate = rs.getDate("date_of_birth");
                        java.sql.Date hireDate = rs.getDate("hire_date");

                        employee = new EmployeeWithSocialMediaResponse(
                                employeeId,
                                rs.getString("image_url"),
                                rs.getString("employee_code"),
                                rs.getString("full_name"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                dobDate != null ? dobDate.toLocalDate() : null,           // ✅ NULL SAFE
                                rs.getString("employee_type"),
                                rs.getString("department_name"),
                                rs.getString("designation_name"),
                                hireDate != null ? hireDate.toLocalDate() : null,         // ✅ NULL SAFE
                                rs.getString("work_location"),
                                rs.getObject("salary") != null ? rs.getDouble("salary") : null, // ✅ NULL SAFE
                                new ArrayList<>()
                        );

                        employeeMap.put(employeeId, employee);
                    }

                    // ✅ Add social media only if exists
                    String platformName = rs.getString("platform_name");

                    if (platformName != null) {

                        EmployeeWithSocialMediaResponse.SocialMediaProfile socialProfile =
                                new EmployeeWithSocialMediaResponse.SocialMediaProfile(
                                        platformName,
                                        rs.getString("username"),
                                        rs.getString("profile_url"),
                                        rs.getBoolean("is_primary"),
                                        rs.getBoolean("is_public"),
                                        rs.getBoolean("verified"),
                                        rs.getInt("follower_count")
                                );

                        employee.getSocialMediaProfiles().add(socialProfile);
                    }
                }
            });

            return new ArrayList<>(employeeMap.values());

        } catch (Exception ex) {
            LOGGER.error("Error fetching employee with social media details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee with social media details");
        }
    }

    @Override
    public List<EmployeeGuideResponse> getEmployeeGuideDetails() {

        String GET_EMPLOYEES_GUIDE_DETAILS = EmployeeQueries.GET_EMPLOYEES_GUIDE_DETAILS;

        try {
            return jdbcTemplate.query(GET_EMPLOYEES_GUIDE_DETAILS, rs -> {

                Map<Long, EmployeeGuideResponse> employeeMap = new LinkedHashMap<>();

                while (rs.next()) {

                    Long employeeId = rs.getLong("employee_id");

                    // ================== MAIN EMPLOYEE OBJECT ==================
                    EmployeeGuideResponse employee =
                            employeeMap.computeIfAbsent(employeeId, id -> {
                                try {
                                    return EmployeeGuideResponse.builder()
                                            .employeeId(employeeId)
                                            .employeeCode(rs.getString("employee_code"))
                                            .fullName(rs.getString("full_name"))
                                            .email(rs.getString("email"))
                                            .imageUrl(rs.getString("image_url"))
                                            .phone(rs.getString("phone"))
                                            .dateOfBirth(rs.getDate("date_of_birth") != null
                                                    ? rs.getDate("date_of_birth").toLocalDate()
                                                    : null)
                                            .employeeType(rs.getString("employee_type"))
                                            .departmentName(rs.getString("department_name"))
                                            .designationName(rs.getString("designation_name"))
                                            .hireDate(rs.getDate("hire_date") != null
                                                    ? rs.getDate("hire_date").toLocalDate()
                                                    : null)
                                            .workLocation(rs.getString("work_location"))
                                            .salary(rs.getBigDecimal("salary"))
                                            .guideSpecialization(new ArrayList<>())
                                            .socialMediaAccounts(new ArrayList<>())
                                            .build();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                    // ================== GUIDE SPECIALIZATION (DEDUPED) ==================
                    if (rs.getString("specialization_type") != null) {

                        boolean specializationExists =
                                employee.getGuideSpecialization().stream()
                                        .anyMatch(gs ->
                                                {
                                                    try {
                                                        return gs.getSpecializationType().equals(rs.getString("specialization_type")) &&
                                                                gs.getRegions().equals(rs.getString("regions"));
                                                    } catch (SQLException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                        );

                        if (!specializationExists) {
                            employee.getGuideSpecialization().add(
                                    EmployeeGuideResponse.GuideSpecialization.builder()
                                            .specializationType(rs.getString("specialization_type"))
                                            .regions(rs.getString("regions"))
                                            .languages(rs.getString("languages"))
                                            .certifications(rs.getString("certifications"))
                                            .experienceYears(rs.getObject("experience_years") != null
                                                    ? rs.getInt("experience_years") : null)
                                            .rating(rs.getObject("rating") != null
                                                    ? rs.getDouble("rating") : null)
                                            .isAvailable(rs.getObject("is_available") != null
                                                    ? rs.getBoolean("is_available") : null)
                                            .build()
                            );
                        }
                    }

                    // ================== SOCIAL MEDIA (DEDUPED) ==================
                    if (rs.getString("platform_name") != null) {

                        boolean socialExists =
                                employee.getSocialMediaAccounts().stream()
                                        .anyMatch(sm ->
                                                {
                                                    try {
                                                        return sm.getProfileUrl().equals(rs.getString("profile_url"));
                                                    } catch (SQLException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                        );

                        if (!socialExists) {
                            employee.getSocialMediaAccounts().add(
                                    EmployeeGuideResponse.SocialMedia.builder()
                                            .platformName(rs.getString("platform_name"))
                                            .username(rs.getString("username"))
                                            .profileUrl(rs.getString("profile_url"))
                                            .isPrimary(rs.getObject("is_primary") != null
                                                    ? rs.getBoolean("is_primary") : null)
                                            .isPublic(rs.getObject("is_public") != null
                                                    ? rs.getBoolean("is_public") : null)
                                            .verified(rs.getObject("verified") != null
                                                    ? rs.getBoolean("verified") : null)
                                            .followerCount(rs.getObject("follower_count") != null
                                                    ? rs.getLong("follower_count") : null)
                                            .build()
                            );
                        }
                    }
                }

                return new ArrayList<>(employeeMap.values());
            });

        } catch (Exception ex) {
            LOGGER.error("Error fetching employee guide details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee guide details");
        }
    }

    @Override
    public TourAssignedEmployeeResponse getEmployeeAssignToTourId(Long tourId) {
        String sql = EmployeeQueries.GET_EMPLOYEE_ASSIGNED_TO_TOUR_ID;

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{tourId}, (rs, rowNum) ->
                    TourAssignedEmployeeResponse.builder()
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .imageUrl(rs.getString("image_url"))
                            .email(rs.getString("email"))
                            .mobileNumber(rs.getString("mobile_number1"))
                            .designationName(rs.getString("designation_name"))
                            .assignMessage(rs.getString("assign_message"))
                            .build()
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new DataNotFoundErrorExceptionHandler("No employee assigned to tour found");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching employee assigned to tour", ex);
            throw new InternalServerErrorExceptionHandler(
                    "Unexpected error occurred while fetching employee assigned to tour"
            );
        }
    }

    @Override
    public List<TourAssignedEmployeeResponse.RelatedOtherTours> getOtherRelatedToursByTourId(Long tourId) {
        String sql = EmployeeQueries.GET_OTHER_RELATED_TOURS_BY_TOUR_ID;
        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{tourId},
                    (rs, rowNum) -> TourAssignedEmployeeResponse.RelatedOtherTours.builder()
                            .tourId(rs.getLong("tour_id"))
                            .tourName(rs.getString("name"))
                            .build()
            );
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Long> getEmployeeIdsForAssignTour() {
        String GET_EMPLOYEE_IDS_FOR_ASSIGN_TOUR = EmployeeQueries.GET_EMPLOYEE_IDS_FOR_ASSIGN_TOUR;

        try {
            return jdbcTemplate.queryForList(GET_EMPLOYEE_IDS_FOR_ASSIGN_TOUR, Long.class);

        } catch (DataAccessException dae) {
            LOGGER.error("DB error while fetching employee IDs for assign tour", dae);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee IDs");
        }
    }

    @Override
    public List<EmployeesForAssignTourResponse> getEmployeeDetailsForAssignTour() {
        String GET_EMPLOYEE_DETAILS_FOR_ASSIGN_TOUR = EmployeeQueries.GET_EMPLOYEE_DETAILS_FOR_ASSIGN_TOUR;

        try {
            return jdbcTemplate.query(GET_EMPLOYEE_DETAILS_FOR_ASSIGN_TOUR, (rs, rowNum) -> {
                EmployeesForAssignTourResponse employee = new EmployeesForAssignTourResponse();
                employee.setEmployeeId(rs.getLong("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setImageUrl(rs.getString("image_url"));
                employee.setEmail(rs.getString("email"));
                employee.setMobileNumber1(rs.getString("mobile_number1"));
                employee.setDesignationName(rs.getString("designation_name"));

                // Parse JSON array from MySQL
                String toursJson = rs.getString("tours");
                if (toursJson != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<EmployeesForAssignTourResponse.Tour> tours = null;
                    try {
                        tours = objectMapper.readValue(
                                toursJson,
                                objectMapper.getTypeFactory().constructCollectionType(
                                        List.class,
                                        EmployeesForAssignTourResponse.Tour.class
                                )
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    employee.setTours(tours);
                }

                return employee;
            });

        } catch (DataAccessException dae) {
            LOGGER.error("DB error while fetching employee details for assign tour", dae);
            throw new InternalServerErrorExceptionHandler("Failed to fetch employee details");
        } catch (Exception e) {
            LOGGER.error("Error parsing tours JSON", e);
            throw new InternalServerErrorExceptionHandler("Failed to parse employee tours");
        }
    }



}
