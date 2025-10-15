package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageReviewResponse {

    private Long reviewId;
    private Long packageId;
    private Long packageScheduleId;
    private String name;
    private String review;
    private Double rating;
    private String description;
    private String status;
    private Integer numberOfParticipate;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;

    private List<Image> images;
    private List<Reaction> reactions;
    private List<Comment> comments;

    // 🔹 Inner class for images
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private String status;
        private Long createdBy;
        private LocalDateTime createdAt;
    }

    // 🔹 Inner class for reactions
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Reaction {
        private Long id;
        private Long packageReviewId;
        private Long userId;
        private String userName;
        private String reactionType;
        private String status;
        private LocalDateTime createdAt;
    }

    // 🔹 Inner class for comments
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Comment {
        private Long id;
        private Long packageReviewId;
        private Long userId;
        private String userName;
        private Long parentCommentId;
        private String comment;
        private String status;
        private LocalDateTime createdAt;
        private Long createdBy;
        private List<CommentReaction> reactions; // nested list for comment reactions
    }

    // 🔹 Inner class for comment reactions
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CommentReaction {
        private Long id;
        private Long commentId;
        private Long userId;
        private String userName;
        private String reactionType;
        private String status;
        private Long createdBy;
        private LocalDateTime createdAt;
    }
}