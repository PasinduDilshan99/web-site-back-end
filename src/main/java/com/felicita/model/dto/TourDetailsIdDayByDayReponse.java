package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDetailsIdDayByDayReponse {
    private int day;
    private List<DestinationDetails> destinationDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestinationDetails{
        private Long destinationId;
        private List<Long> activityIds;
    }
}
