package com.felicita.service;

import com.felicita.model.request.BlogDetailsRequest;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs();

    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs();

    CommonResponse<BlogResponse> getBlogDetailsById(BlogDetailsRequest blogDetailsRequest);

    CommonResponse<InsertResponse> createBlog(CreateBlogRequest createBlogRequest);
}
