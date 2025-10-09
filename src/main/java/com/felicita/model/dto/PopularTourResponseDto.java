package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularTourResponseDto {
    private int tourId;
    private String tourName;
    private String tourDescription;
    private Integer duration;
    private Double latitude;
    private Double longitude;
    private String startLocation;
    private String endLocation;
    private String tourType;
    private String tourCategory;
    private String season;
    private String tourStatus;

    private  List<PopularTourImagesDto> images;
    private List<popularTourScheduleResponseDto> schedules; // List of schedules
}
