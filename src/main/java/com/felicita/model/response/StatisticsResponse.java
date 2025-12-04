package com.felicita.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private String title;
    private String description;
    private String color;
    private String hoverColor;
    private Integer value;
    private String statusName;
    private LocalDateTime createdAt;
    private String createdByName;
    private LocalDateTime updatedAt;
    private String updatedByName;
    private LocalDateTime terminatedAt;
    private String terminatedByName;
}