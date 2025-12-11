package com.felicita.repository;

import com.felicita.model.request.BlogDetailsRequest;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.BlogResponse;

import java.util.List;

public interface BlogRepository {
    List<BlogResponse> getAllBlogs();

    BlogResponse getBlogDetailsById(BlogDetailsRequest blogDetailsRequest);

    void createBlog(CreateBlogRequest createBlogRequest, Long userId);
}
