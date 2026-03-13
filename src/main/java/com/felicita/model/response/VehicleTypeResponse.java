package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeResponse {

    private Long vehicleTypeId;
    private String name;
    private String description;
    private String status;
    private List<Image> images;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {

        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;

    }
}