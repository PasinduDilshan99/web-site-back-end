package com.felicita.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityScheduleDto {
    private Integer id;
    private String name;

    @JsonProperty("assume_start_date")
    private String assumeStartDate;

    @JsonProperty("assume_end_date")
    private String assumeEndDate;

    @JsonProperty("duration_hours_start")
    private BigDecimal durationHoursStart;

    @JsonProperty("duration_hours_end")
    private BigDecimal durationHoursEnd;

    @JsonProperty("special_note")
    private String specialNote;

    private String description;
    private Integer status;
}
