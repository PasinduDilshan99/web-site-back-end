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
public class UserProfileDestinationReviewResponse {
    private Integer reviewId;
    private Integer destinationId;
    private String destinationName;
    private Integer reviewUserId;
    private String reviewUserName;
    private String reviewText;
    private BigDecimal reviewRating;
    private String reviewStatus;
    private Integer reviewCreatedBy;
    private LocalDateTime reviewCreatedAt;
    private Integer reviewUpdatedBy;
    private LocalDateTime reviewUpdatedAt;

    private List<UserProfileDestinationReviewResponse.Image> images;
    private List<UserProfileDestinationReviewResponse.Reaction> reactions;
    private List<UserProfileDestinationReviewResponse.Comment> comments;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder    public static class Reaction {
        private Integer reviewReactionId;
        private Integer reactionReviewId;
        private Integer reactionUserId;
        private String reactionUserName;
        private String reactionType;
        private String reviewReactionStatus;
        private LocalDateTime reactionCreatedAt;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder    public static class Comment {
        private Integer commentId;
        private Integer commentReviewId;
        private Integer commentUserId;
        private String commentUserName;
        private Integer parentCommentId;
        private String commentText;
        private String commentStatus;
        private LocalDateTime commentCreatedAt;
        private Integer commentCreatedBy;
        private List<UserProfileDestinationReviewResponse.Comment.CommentReaction> commentReactions;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
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
}
