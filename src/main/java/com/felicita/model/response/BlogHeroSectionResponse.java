package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogHeroSectionResponse {

    private Integer id;
    private String name;
    private String imageUrl;
    private String title;
    private String subtitle;
    private String description;
    private String primaryButtonText;
    private String primaryButtonLink;
    private String secondaryButtonText;
    private String secondaryButtonLink;
    private Integer order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String statusName;
}
