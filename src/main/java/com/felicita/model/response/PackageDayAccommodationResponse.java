package com.felicita.model.response;

import com.felicita.model.dto.PackageDayByDayDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDayAccommodationResponse {

    private Long packageId;
    private String packageName;
    private String packageDescription;
    private Double totalPrice;
    private Double pricePerPerson;
    private Double discount;
    private String color;
    private String hoverColor;
    private List<PackageDayByDayDto> packageDayByDayDtoList;

}
