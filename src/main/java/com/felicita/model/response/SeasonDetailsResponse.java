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
public class SeasonDetailsResponse {
    private Long id;
    private String name;
    private String standardName;
    private String localName;
    private Integer startMonth;
    private Integer endMonth;
    private String monsoonType;
    private String weatherSummary;
    private Integer temperatureMin;
    private Integer temperatureMax;
    private String rainfallPattern;
    private Boolean isPeak;
    private Integer displayOrder;
    private String description;
    private Integer status;

    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;

    private List<SeasonImage> seasonImages;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeasonImage {

        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private Integer status;

        private LocalDateTime createdAt;
        private Integer createdBy;
        private LocalDateTime updatedAt;
        private Integer updatedBy;
    }
}
