package com.felicita.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityImageDto {
    private Integer id;
    private String name;
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    private Integer status;

}
