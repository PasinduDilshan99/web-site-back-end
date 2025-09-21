package com.felicita.model.response;

import com.felicita.model.dto.BlogCommentDto;
import com.felicita.model.dto.BlogImageDto;
import com.felicita.model.dto.BlogLikeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private Long writerId;
    private String writerName;
    private String status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private LocalDateTime terminatedAt;
    private Long terminatedBy;

    private List<BlogImageDto> images;
    private List<BlogLikeDto> likes;
    private List<BlogCommentDto> comments;
}
