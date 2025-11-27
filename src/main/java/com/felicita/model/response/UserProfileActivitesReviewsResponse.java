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
public class UserProfileActivitesReviewsResponse {
    private Long reviewId;
    private Long activityScheduleId;
    private Long activityId;
    private String activityName;
    private String reviewName;
    private String review;
    private BigDecimal rating;
    private String description;
    private String reviewStatus;
    private Integer numberOfParticipate;
    private Long reviewCreatedBy;
    private LocalDateTime reviewCreatedAt;
    private Long reviewUpdatedBy;
    private LocalDateTime reviewUpdatedAt;

    private List<UserProfileActivitesReviewsResponse.ReviewImage> images;
    private List<UserProfileActivitesReviewsResponse.ReviewReaction> reactions;
    private List<UserProfileActivitesReviewsResponse.Comment> comments;

    @Data
    @Builder
    public static class ReviewImage {
        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String imageStatus;
        private Long imageCreatedBy;
        private LocalDateTime imageCreatedAt;
    }

    @Data
    @Builder
    public static class ReviewReaction {
        private Long reviewReactionId;
        private Long reactionReviewId;
        private Long userId;
        private String userName;
        private String reactionType;
        private String reviewReactionStatus;
        private LocalDateTime reactionCreatedAt;
    }

    @Data
    @Builder
    public static class Comment {
        private Long commentId;
        private Long commentReviewId;
        private Long userId;
        private String userName;
        private Long parentCommentId;
        private String comment;
        private String commentStatus;
        private LocalDateTime commentCreatedAt;
        private Long commentCreatedBy;
        private List<UserProfileActivitesReviewsResponse.Comment.CommentReaction> commentReactions;

        @Data
        @Builder
        public static class CommentReaction {
            private Long commentReactionId;
            private Long commentReactionCommentId;
            private Long userId;
            private String userName;
            private String commentReactionType;
            private String commentReactionStatus;
            private Long commentReactionCreatedBy;
            private LocalDateTime commentReactionCreatedAt;
        }
    }
}
