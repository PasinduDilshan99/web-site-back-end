package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityHeroSectionResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private String title;
    private String subtitle;
    private String description;

    private String primaryButtonText;
    private String primaryButtonLink;

    private String secondaryButtonText;
    private String secondaryButtonLink;

    // Status name from common_status table
    private String status;

    private Integer order;

    private LocalDateTime createdAt;
    private Integer createdBy;

    private LocalDateTime updatedAt;
    private Integer updatedBy;

    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
