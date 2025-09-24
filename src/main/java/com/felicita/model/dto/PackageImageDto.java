package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageImageDto {
    private Integer imageId;
    private String imageUrl;
    private String imageStatus;
}
