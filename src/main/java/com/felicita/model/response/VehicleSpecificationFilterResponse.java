package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSpecificationFilterResponse {

    private List<String> makes;
    private List<String> models;
    private List<Integer> years;
    private List<String> bodyTypes;
    private HorsePowerRange horsePowerRange;
    private List<Integer> seats;
    private List<String> roofTypes;
    private List<String> acTypes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HorsePowerRange {
        private Integer min;
        private Integer max;
    }
}