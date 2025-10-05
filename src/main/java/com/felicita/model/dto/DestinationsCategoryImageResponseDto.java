package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationsCategoryImageResponseDto {
    private int imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String imageStatus;
    private LocalDateTime imageCreatedAt;

}
