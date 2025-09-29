package com.felicita.model.response;

import com.felicita.model.dto.DestinationDto;
import com.felicita.model.dto.PackageImageDto;
import com.felicita.model.dto.PackageTypeDto;
import com.felicita.model.dto.TourDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailsResponse {
    private Integer packageId;
    private String packageName;
    private String packageDescription;
    private BigDecimal totalPrice;
    private BigDecimal discountPercentage;
    private LocalDate packageStartDate;
    private LocalDate packageEndDate;
    private String color;
    private String hoverColor;
    private Integer minPersonCount;
    private Integer maxPersonCount;
    private String packageStatus;

    private PackageTypeDto packageType;
    private TourDto tour;
    private List<DestinationDto> destinationDto;
    private List<PackageImageDto> images;
}
