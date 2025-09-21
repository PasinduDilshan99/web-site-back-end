package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendingDestinationResponse {
    private int trendingId;
    private String trendingStatus;
    private LocalDateTime trendingCreatedAt;
    private Integer trendingCreatedBy;
    private LocalDateTime trendingUpdatedAt;
    private Integer trendingUpdatedBy;
    private LocalDateTime trendingTerminatedAt;
    private Integer trendingTerminatedBy;
    private DestinationResponse destination;
}
