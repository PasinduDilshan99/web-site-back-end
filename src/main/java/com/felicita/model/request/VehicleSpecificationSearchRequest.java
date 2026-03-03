package com.felicita.model.request;

import lombok.Data;

@Data
public class VehicleSpecificationSearchRequest {

    private String make;
    private String model;
    private Integer year;
    private String bodyType;
    private Integer minHorsepower;
    private Integer maxHorsepower;
    private Integer seatCapacity;
    private String sunroofType;
    private Integer acTypeId;

    // pagination
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
}