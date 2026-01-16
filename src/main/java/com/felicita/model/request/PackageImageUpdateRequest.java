package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageImageUpdateRequest {
    private Long imageId;
    private String imageName;
    private String imageDescription;
    private String status;
    private String imageUrl;
    private String color;
}
