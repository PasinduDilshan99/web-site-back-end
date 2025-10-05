package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageImageResponseDto {
    private int imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String color;
}
