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
public class TourWishResponsesDto {
    private Long tourId;
    private String tourName;
    private String tourDescription;
    private String tourStartLocation;
    private String tourEndLocation;
    private List<String> tourImages;
    private String season;
    private String tourUrl;
    private String status;
    private String createdAt;
}
