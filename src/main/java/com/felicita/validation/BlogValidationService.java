package com.felicita.validation;

import com.felicita.model.request.CreateBlogRequest;

public interface BlogValidationService {
    void validateCreateBlogRequest(CreateBlogRequest createBlogRequest);
}
