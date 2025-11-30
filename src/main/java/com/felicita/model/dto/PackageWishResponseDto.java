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
public class PackageWishResponseDto {
    private Long packageId;
    private String packageName;
    private String packageDescription;
    private String packageDate;
    private List<String> packageImages;
    private Double packagePrice;
    private String packageColor;
    private String packageUrl;
    private String tourName;
    private Double discount;
    private String status;
    private String createdAt;
}
