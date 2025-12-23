package com.felicita.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PackageComapreResponse {
    private Long packageId;
    private String packageName;
    private String packageDescription;
    private Double totalPrice;
    private Double pricePerPerson;
    private Double discount;
    private String color;
    private String hoverColor;
    private List<PackageImages> images;
    private List<PackageDayByDayDto> packageDayByDayDtoList;
    private PackageExtrasResponse extraDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageImages {
        @JsonIgnore
        private Long packageId;
        private Long imageId;
        private String name;
        private String description;
        private String url;
    }
}
