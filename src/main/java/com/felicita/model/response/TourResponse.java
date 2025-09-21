package com.felicita.model.response;

import com.felicita.model.dto.TourImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourResponse {
    private Integer tourId;
    private String tourName;
    private String tourDescription;
    private String tourType;
    private String tourCategory;
    private Integer durationDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startLocation;
    private String endLocation;
    private Integer maxPeople;
    private Integer minPeople;
    private BigDecimal pricePerPerson;
    private String tourStatus;
    private List<TourImageDto> tourImages;
    private List<DestinationResponse> destinations;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
