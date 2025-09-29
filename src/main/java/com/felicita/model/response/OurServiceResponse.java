package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OurServiceResponse {
    private Integer serviceId;
    private String serviceTitle;
    private String serviceSubTitle;
    private String serviceDescription;
    private String serviceImageUrl;
    private String serviceColor;
    private String serviceStatus;
    private String serviceStatusStatus;
    private LocalDateTime serviceCreatedAt;
    private Integer serviceCreatedBy;
    private LocalDateTime serviceUpdatedAt;
    private Integer serviceUpdatedBy;
    private LocalDateTime serviceTerminatedAt;
    private Integer serviceTerminatedBy;
    private String serviceIconUrl;
}
