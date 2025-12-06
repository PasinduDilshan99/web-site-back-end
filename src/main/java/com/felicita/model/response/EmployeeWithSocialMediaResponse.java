package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithSocialMediaResponse {

    private Long employeeId;
    private String profilePictureUrl;
    private String employeeCode;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String employeeType;
    private String departmentName;
    private String designationName;
    private LocalDate hireDate;
    private String workLocation;
    private Double salary;
    private List<SocialMediaProfile> socialMediaProfiles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocialMediaProfile {
        private String platformName;
        private String username;
        private String profileUrl;
        private Boolean isPrimary;
        private Boolean isPublic;
        private Boolean verified;
        private Integer followerCount;
    }
}