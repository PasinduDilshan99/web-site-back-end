package com.felicita.repository;

import com.felicita.model.response.BlogResponse;

import java.util.List;

public interface BlogRepository {
    List<BlogResponse> getAllBlogs();
}
