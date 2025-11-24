package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.request.UserProfileDetailsRequest;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.UserProfileDetailsResponse;
import com.felicita.model.response.UserProfileSidebarResponse;
import com.felicita.queries.PartnerQueries;
import com.felicita.queries.UserProfileQueries;
import com.felicita.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProfileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserProfileDetailsResponse getUserProfileDetails(UserProfileDetailsRequest req) {
        String GET_USER_PROFILE_DETAILS = UserProfileQueries.GET_USER_PROFILE_DETAILS;
        try {
            LOGGER.info("Executing query to fetch user profile details...");
            return jdbcTemplate.queryForObject(
                    GET_USER_PROFILE_DETAILS,
                    new Object[]{req.getUserId()},
                    (rs, rowNum) -> {
                        UserProfileDetailsResponse response = new UserProfileDetailsResponse();
                        response.setUserId(rs.getInt("user_id"));
                        response.setUsername(rs.getString("username"));
                        response.setFirstName(rs.getString("first_name"));
                        response.setMiddleName(rs.getString("middle_name"));
                        response.setLastName(rs.getString("last_name"));
                        response.setNic(rs.getString("nic"));
                        response.setPassportNumber(rs.getString("passport_number"));
                        response.setDrivingLicenseNumber(rs.getString("driving_license_number"));
                        response.setEmail(rs.getString("email"));
                        response.setMobileNumber1(rs.getString("mobile_number1"));
                        response.setMobileNumber2(rs.getString("mobile_number2"));
                        response.setDateOfBirth(rs.getString("date_of_birth"));
                        response.setImageUrl(rs.getString("image_url"));
                        response.setCreatedAt(rs.getString("created_at"));
                        response.setUpdatedAt(rs.getString("updated_at"));
                        response.setBenefitsPointsCount(rs.getInt("benefits_points_count"));
                        response.setAddressNumber(rs.getString("address_number"));
                        response.setAddressLine1(rs.getString("address_line1"));
                        response.setAddressLine2(rs.getString("address_line2"));
                        response.setCity(rs.getString("city"));
                        response.setDistrict(rs.getString("district"));
                        response.setProvince(rs.getString("province"));
                        response.setCountryName(rs.getString("country_name"));
                        response.setPostalCode(rs.getString("postal_code"));
                        response.setGender(rs.getString("gender"));
                        response.setReligion(rs.getString("religion"));
                        response.setUserType(rs.getString("user_type"));
                        response.setUserTypeDescription(rs.getString("user_type_description"));
                        response.setUserStatus(rs.getString("user_status"));
                        response.setUserStatusDescription(rs.getString("user_status_description"));
                        response.setWalletNumber(rs.getString("wallet_number"));
                        response.setWalletAmount(rs.getDouble("amount"));
                        response.setWalletStatus(rs.getString("wallet_status"));
                        return response;
                    }
            );
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching user profile details: {}", ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch user profile from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching user profile details: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching user profile");
        }
    }

    @Override
    public List<UserProfileSidebarResponse> getUserProfileSideBar() {
        String GET_USER_PROFILE_SIDEBAR = UserProfileQueries.GET_USER_PROFILE_SIDEBAR;
        try {
            List<UserProfileSidebarResponse> flatList = jdbcTemplate.query(GET_USER_PROFILE_SIDEBAR, (rs, rowNum) -> {
                UserProfileSidebarResponse resp = new UserProfileSidebarResponse();
                resp.setId(rs.getInt("id"));
                resp.setParentId(rs.getObject("parent_id") != null ? rs.getInt("parent_id") : null);
                resp.setName(rs.getString("name"));
                resp.setDescription(rs.getString("description"));
                resp.setPrivilegeName(rs.getString("privilege_name"));
                resp.setStatus(rs.getString("status_name"));
                return resp;
            });
            return buildSidebarTree(flatList);
        } catch (Exception ex) {
            LOGGER.error("Error fetching sidebar: {}", ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Failed to fetch sidebar data");
        }    }

    private List<UserProfileSidebarResponse> buildSidebarTree(List<UserProfileSidebarResponse> flatList) {
        Map<Integer, UserProfileSidebarResponse> map = new HashMap<>();
        flatList.forEach(item -> map.put(item.getId(), item));
        List<UserProfileSidebarResponse> rootItems = new ArrayList<>();
        for (UserProfileSidebarResponse item : flatList) {
            if (item.getParentId() == null) {
                rootItems.add(item);
            } else {
                UserProfileSidebarResponse parent = map.get(item.getParentId());
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(item);
            }
        }
        return rootItems;
    }

}
