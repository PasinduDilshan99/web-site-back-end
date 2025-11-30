package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationWishResponseDto {
    private Long destinationId;
    private String destinationName;
    private String destinationDescription;
    private String destinationLocation;
    private String destinationCategory;
    private List<String> destinationImages;
    private String destinationUrl;
    private String status;
    private String createdAt;
}
