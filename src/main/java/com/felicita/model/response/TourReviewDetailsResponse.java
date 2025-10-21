package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourReviewDetailsResponse {

    private Integer reviewId;
    private Integer tourScheduleId;
    private Integer tourId;
    private String tourName;

    private String reviewName;
    private String review;
    private BigDecimal rating;
    private String reviewDescription;
    private String reviewStatus;
    private Integer numberOfParticipate;

    private Integer reviewCreatedBy;
    private String reviewCreatedUser;
    private LocalDateTime reviewCreatedAt;
    private Integer reviewUpdatedBy;
    private LocalDateTime reviewUpdatedAt;

    private List<Image> images;
    private List<Reaction> reactions;
    private List<Comment> comments;

    // 🖼️ Inner Class — Review Images
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private Integer imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String imageStatus;
        private Integer imageCreatedBy;
        private LocalDateTime imageCreatedAt;
    }

    // ❤️ Inner Class — Review Reactions
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Reaction {
        private Integer reviewReactionId;
        private Integer reactionReviewId;
        private Integer reactionUserId;
        private String reactionUserName;
        private String reactionType;
        private String reviewReactionStatus;
        private LocalDateTime reactionCreatedAt;
    }

    // 💬 Inner Class — Comments
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Comment {
        private Integer commentId;
        private Integer commentReviewId;
        private Integer commentUserId;
        private String commentUserName;
        private Integer parentCommentId;
        private String comment;
        private String commentStatus;
        private LocalDateTime commentCreatedAt;
        private Integer commentCreatedBy;
        private List<CommentReaction> commentReactions;
    }

    // 🔁 Inner Class — Comment Reactions
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CommentReaction {
        private Integer commentReactionId;
        private Integer commentReactionCommentId;
        private Integer commentReactionUserId;
        private String commentReactionUserName;
        private String commentReactionType;
        private String commentReactionStatus;
        private Integer commentReactionCreatedBy;
        private LocalDateTime commentReactionCreatedAt;
    }
}
