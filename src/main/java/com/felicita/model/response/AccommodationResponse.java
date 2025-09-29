package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationResponse {
    private Integer accommodationId;
    private String accommodationTitle;
    private String accommodationSubTitle;
    private String accommodationDescription;
    private String accommodationIconUrl;
    private String accommodationImageUrl;
    private String accommodationColor;
    private String accommodationHoverColor;
    private String accommodationLinkUrl;
    private String accommodationStatus;
    private String accommodationStatusStatus;
    private LocalDateTime accommodationCreatedAt;
    private Integer accommodationCreatedBy;
    private LocalDateTime accommodationUpdatedAt;
    private Integer accommodationUpdatedBy;
    private LocalDateTime accommodationTerminatedAt;
    private Integer accommodationTerminatedBy;
}
