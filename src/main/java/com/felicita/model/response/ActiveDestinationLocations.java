package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveDestinationLocations {

    private Long id;
    private String name;
    private String category;
    private double lat;
    private double lng;
    private String description;

}
