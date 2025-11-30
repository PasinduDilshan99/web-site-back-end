package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSecurityDetailsResponse {

    // User info
    private String username;
    private String email;
    private String mobileNumber1;
    private String mobileNumber2;

    // Multiple email verifications
    private List<EmailVerificationDetails> emailVerifications;

    // Multiple mobile verifications
    private List<MobileVerificationDetails> mobileVerifications;

    // -------------------------------
    // Inner class for email verification
    // -------------------------------
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EmailVerificationDetails {
        private String whichEmail;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String statusName;
        private String statusDescription;
    }

    // -------------------------------
    // Inner class for mobile verification
    // -------------------------------
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MobileVerificationDetails {
        private String whichMobile;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String statusName;
        private String statusDescription;
    }
}

