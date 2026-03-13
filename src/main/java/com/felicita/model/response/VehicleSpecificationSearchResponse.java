package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class VehicleSpecificationSearchResponse {

    private Long totalRecords;
    private Integer pageNumber;
    private Integer pageSize;
    private List<VehicleSpecificationBasic> vehicles;

    @Data
    @Builder
    @AllArgsConstructor
    public static class VehicleSpecificationBasic {
        private Long specificationId;
        private String make;
        private String model;
        private Integer year;
        private String bodyType;
        private BigDecimal price;
        private Integer horsepowerHp;
        private Integer seatCapacity;
        private String sunroofType;
        private String acTypeName;
        private String imageUrl;
    }
}