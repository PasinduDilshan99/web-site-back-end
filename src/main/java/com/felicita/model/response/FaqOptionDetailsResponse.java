package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaqOptionDetailsResponse {
    private Long optionId;
    private String optionKey;
    private String optionValue;
    private String optionType;
    private String optionTypeDescription;
    private String optionDescription;
    private String commonStatusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
