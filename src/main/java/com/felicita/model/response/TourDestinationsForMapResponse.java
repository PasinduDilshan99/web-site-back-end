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
public class TourDestinationsForMapResponse {
    private Integer id;
    private String name;
    private String description;
    private Double lat;
    private Double lng;
    private List<Image> images;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private Integer id;
        private String url;
        private String name;
        private String description;
    }
}
