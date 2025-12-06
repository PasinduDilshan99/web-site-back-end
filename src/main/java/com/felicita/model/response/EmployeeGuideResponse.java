package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeGuideResponse {

    // ================= EMPLOYEE BASIC DETAILS =================
    private Long employeeId;
    private String employeeCode;
    private String fullName;
    private String email;
    private String imageUrl;
    private String phone;
    private LocalDate dateOfBirth;
    private String employeeType;
    private String departmentName;
    private String designationName;
    private LocalDate hireDate;
    private String workLocation;
    private BigDecimal salary;

    // ================= GUIDE SPECIALIZATION =================
    private List<GuideSpecialization> guideSpecialization;

    // ================= SOCIAL MEDIA LIST =================
    private List<SocialMedia> socialMediaAccounts;



    // =========================================================
    // ================= INNER CLASSES =========================
    // =========================================================

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocialMedia {
        private String platformName;
        private String username;
        private String profileUrl;
        private Boolean isPrimary;
        private Boolean isPublic;
        private Boolean verified;
        private Long followerCount;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GuideSpecialization {
        private String specializationType;
        private String regions;
        private String languages;
        private String certifications;
        private Integer experienceYears;
        private Double rating;
        private Boolean isAvailable;
    }
}
