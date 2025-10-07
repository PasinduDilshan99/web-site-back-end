package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanYourTripNearDestinationsDto {
    private Integer destinationId;         // destination_id
    private List<Integer> nearbyDestinations; // list of nearby destination IDs

    /**
     * Utility method to convert comma-separated string to List<Integer>
     * Can be used in RowMapper
     */
    public static List<Integer> fromCsv(String csv) {
        if (csv == null || csv.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(csv.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
