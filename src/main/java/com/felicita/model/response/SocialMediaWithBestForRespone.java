package com.felicita.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialMediaWithBestForRespone {
    private Long socialMediaId;
    private String socialMediaName;
    private String socialMediaUsername;
    private String socialMediaDescription;
    private String link;
    private String iconUrl;
    private String color;
    private String hoverColor;
    private String socialMediaStatus;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

    private List<BestFor> bestForList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BestFor {
        private Long bestForId;
        private String bestForName;
        private String bestForDescription;
        private String bestForStatus;
    }
}
