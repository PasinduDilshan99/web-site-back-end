package com.felicita.repository;

import com.felicita.model.request.BlogBookmarkRequest;
import com.felicita.model.request.BlogDetailsRequest;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.BlogTagResponse;

import java.util.List;

public interface BlogRepository {
    List<BlogResponse> getAllBlogs();

    BlogResponse getBlogDetailsById(BlogDetailsRequest blogDetailsRequest);

    void createBlog(CreateBlogRequest createBlogRequest, Long userId);

    List<BlogResponse> getBlogsByWriter(String writerName);

    List<Long> getAllBlogIdsByTagName(String tagName);

    List<BlogResponse> getBlogsByBlogsIdList(List<Long> blogIds);

    List<BlogTagResponse> getAllBlogTags();

    void updateBlogViewCountByOne(BlogDetailsRequest blogDetailsRequest);

    Boolean isBlogExists(Long blogId);

    void addBookmarkToBlog(BlogBookmarkRequest blogBookmarkRequest, Long userId);

    Boolean isBlogAlreadyBookmarked(Long blogId, Long userId);

    void removeBookmarkToBlog(BlogBookmarkRequest blogBookmarkRequest, Long userId);
}
