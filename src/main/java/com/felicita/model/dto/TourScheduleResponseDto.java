package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourScheduleResponseDto {
    private int scheduleId;
    private String scheduleName;
    private LocalDate assumeStartDate;
    private LocalDate assumeEndDate;
    private Integer durationStart;
    private Integer durationEnd;
    private String specialNote;
    private String scheduleDescription;
}
