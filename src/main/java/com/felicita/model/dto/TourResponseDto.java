package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourResponseDto {
    private int tourId;
    private String tourName;
    private String tourDescription;
    private Integer duration;
    private Double latitude;
    private Double longitude;
    private String startLocation;
    private String endLocation;

    private String tourTypeName;
    private String tourTypeDescription;
    private String tourCategoryName;
    private String tourCategoryDescription;
    private String seasonName;
    private String seasonDescription;
    private String statusName;

    private List<TourScheduleResponseDto> schedules;
    private List<TourImageResponseDto> images;
}
