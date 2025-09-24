package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long reviewId;
    private Integer rating;
    private String reviewDescription;
    private String reviewStatus;
    private LocalDateTime reviewCreatedAt;
    private LocalDateTime reviewUpdatedAt;

    private UserInfo reviewer;
    private TourInfo tour;
    private PackageInfo packageInfo;

    private List<ReviewImage> images;
    private List<ReviewReaction> reactions;
    private List<ReviewComment> comments;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String username;
        private String firstName;
        private String lastName;
        private String avatarUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TourInfo {
        private Long tourId;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageInfo {
        private Long packageId;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewImage {
        private Long id;
        private String imageUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewReaction {
        private Long id;
        private String reactionType;
        private Long reactedByUserId;
        private String reactedByUsername;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewComment {
        private Long id;
        private String commentText;
        private Long parentId;
        private UserInfo commentedBy;
        private LocalDateTime createdAt;
    }
}