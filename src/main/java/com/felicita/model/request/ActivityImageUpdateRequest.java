package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityImageUpdateRequest {
    private Long imageId;
    private String name;
    private String description;
    private String imageUrl;
    private String status;
}
