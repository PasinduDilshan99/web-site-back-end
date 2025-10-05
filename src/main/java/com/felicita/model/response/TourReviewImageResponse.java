package com.felicita.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourReviewImageResponse {
    private Long imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
}
