package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestionationImageResponseDto {
    private int imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
}
