package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourTypeDto {
    private Long tourTypeId;
    private String tourTypeName;
    private String tourTypeDescription;
}
