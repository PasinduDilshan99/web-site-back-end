package com.felicita.validation;

import com.felicita.model.request.BlogBookmarkRequest;
import com.felicita.model.request.CreateBlogRequest;

public interface BlogValidationService {
    void validateCreateBlogRequest(CreateBlogRequest createBlogRequest);

    void validateBlogBookmarkRequest(BlogBookmarkRequest blogBookmarkRequest, Long userId);
}
