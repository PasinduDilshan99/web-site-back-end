package com.felicita.model.response;

import com.felicita.model.dto.DestinationCategoryDto;
import com.felicita.model.dto.DestinationImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationResponse {
    private int destinationId;
    private String destinationName;
    private String destinationDescription;
    private String destinationStatus;
    private DestinationCategoryDto category;
    private String location;
    private Double rating;
    private Integer popularity;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
    private List<DestinationImageDto> images;
}
