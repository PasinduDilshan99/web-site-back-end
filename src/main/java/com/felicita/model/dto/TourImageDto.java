package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourImageDto {
    private Integer id;
    private String name;
    private String imageUrl;
    private String description;
    private String status;
}
