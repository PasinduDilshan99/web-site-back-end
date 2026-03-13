package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeasonBasicResponse {

    private Long id;
    private String name;
    private String standardName;
    private String localName;
    private Integer startMonth;
    private Integer endMonth;
    private Boolean isPeak;
    private Integer displayOrder;

    private List<SeasonImage> seasonImages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SeasonImage {
        private Long id;
        private String name;
        private String imageUrl;
    }
}