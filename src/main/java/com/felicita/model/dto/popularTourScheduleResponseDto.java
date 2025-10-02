package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class popularTourScheduleResponseDto {
    private int scheduleId;
    private String scheduleName;
    private LocalDate assumeStartDate;
    private LocalDate assumeEndDate;
    private Integer durationStart;
    private Integer durationEnd;
    private String specialNote;
    private String scheduleDescription;
    private String scheduleStatus;

    private List<TourDestinationResponseDto> destinations; // List of destinations
    private List<TourReviewResponseDto> reviews;
}
