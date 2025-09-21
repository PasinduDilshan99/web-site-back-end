package com.felicita.service;

import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs();

    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs();
}
