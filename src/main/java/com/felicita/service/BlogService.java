package com.felicita.service;

import com.felicita.model.request.*;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.BlogTagResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs();

    ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs();

    CommonResponse<BlogResponse> getBlogDetailsById(BlogDetailsRequest blogDetailsRequest);

    CommonResponse<InsertResponse> createBlog(CreateBlogRequest createBlogRequest);

    CommonResponse<List<BlogResponse>> getBlogsByWriter(String writerName);

    CommonResponse<List<BlogResponse>> getBlogsByTagName(String tagName);

    CommonResponse<List<BlogTagResponse>> getAllBlogTags();

    CommonResponse<InsertResponse> addBookmarkToBlog(BlogBookmarkRequest blogBookmarkRequest);

    CommonResponse<InsertResponse> addReactToBlog(BlogReactRequest blogReactRequest);

    CommonResponse<InsertResponse> addCommentToBlog(BlogCommentRequest blogCommentRequest);

    CommonResponse<InsertResponse> addReactToBlogComment(BlogCommentReactRequest blogCommentReactRequest);

    CommonResponse<List<BlogTagResponse>> getAllBlogTagsByBLogId(Long blogId);
}
