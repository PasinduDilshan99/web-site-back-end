package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourTravelTipInsertRequest {
    private String tipTitle;
    private String tipDescription;
    private Integer displayOrder;
    private String status;
}
