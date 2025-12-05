package com.felicita.repository.impl;

import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CouponDetailsResponse;
import com.felicita.model.response.EmployeeWithSocialMediaResponse;
import com.felicita.queries.CouponQueries;
import com.felicita.queries.EmployeeQueries;
import com.felicita.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


}
