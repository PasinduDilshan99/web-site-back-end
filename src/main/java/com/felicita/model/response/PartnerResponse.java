package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerResponse {
    private Integer partnerId;
    private String partnerName;
    private String partnerCompanyName;
    private String partnerCompanyDescription;
    private String partnerLogoUrl;
    private String partnerWebsiteUrl;
    private String partnerAgreement;
    private String partnerStatus;
    private String partnerStatusStatus;
    private LocalDate partnerFromDate;
    private LocalDate partnerToDate;
    private LocalDateTime partnerCreatedAt;
    private Integer partnerCreatedBy;
    private LocalDateTime partnerUpdatedAt;
    private Integer partnerUpdatedBy;
    private LocalDateTime partnerTerminatedAt;
    private Integer partnerTerminatedBy;
}
