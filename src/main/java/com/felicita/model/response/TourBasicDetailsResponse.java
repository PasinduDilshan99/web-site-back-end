package com.felicita.model.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourBasicDetailsResponse {
    private TourBasicDetails tourDetails;
    private List<TourImageDetails> images;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TourBasicDetails {

        private Long tourId;
        private String tourName;
        private String tourDescription;
        private List<String> tourTypes;
        private List<String> tourCategories;
        private Integer duration;
        private Double latitude;
        private Double longitude;
        private String startLocation;
        private String endLocation;
        private String season;
        private String status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TourImageDetails {

        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
    }
}
