package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementResponse {

    private Long achievementId;
    private String name;
    private String color;
    private String hoverColor;
    private String description;
    private String status;
    private List<AchievementImageResponse> images;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AchievementImageResponse {
        private Long imageId;
        private String name;
        private String description;
        private String imageUrl;
        private String status;
    }
}
