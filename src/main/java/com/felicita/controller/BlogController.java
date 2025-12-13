package com.felicita.controller;

import com.felicita.model.request.*;
import com.felicita.model.response.*;
import com.felicita.service.BlogService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/blog")
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs() {
        LOGGER.info("{} Start execute get all blogs {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<BlogResponse>>> response = blogService.getAllBlogs();
        LOGGER.info("{} End execute get all blogs {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs() {
        LOGGER.info("{} Start execute get all active blogs {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<BlogResponse>>> response = blogService.getAllActiveBlogs();
        LOGGER.info("{} End execute get all active blogs {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/tags")
    public ResponseEntity<CommonResponse<List<BlogTagResponse>>> getAllBlogTags() {
        LOGGER.info("{} Start execute get all blogs tags {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BlogTagResponse>> response = blogService.getAllBlogTags();
        LOGGER.info("{} End execute get all blogs tags {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/writer/{writerName}")
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getBlogsByWriter(@PathVariable String writerName) {
        LOGGER.info("{} Start execute get all blogs by writer {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BlogResponse>> response = blogService.getBlogsByWriter(writerName);
        LOGGER.info("{} End execute get all blogs by writer {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/tag/{tagName}")
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getBlogsByTagName(@PathVariable String tagName) {
        LOGGER.info("{} Start execute get all blogs by tag {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BlogResponse>> response = blogService.getBlogsByTagName(tagName);
        LOGGER.info("{} End execute get all blogs by tag {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/blog-details")
    public ResponseEntity<CommonResponse<BlogResponse>> getBlogDetailsById(@RequestBody BlogDetailsRequest blogDetailsRequest) {
        LOGGER.info("{} Start execute get blog details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<BlogResponse> response = blogService.getBlogDetailsById(blogDetailsRequest);
        LOGGER.info("{} End execute get blog details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CommonResponse<InsertResponse>> createBlog(@RequestBody CreateBlogRequest createBlogRequest) {
        LOGGER.info("{} Start execute create blog {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.createBlog(createBlogRequest);
        LOGGER.info("{} End execute create blog {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/bookmark")
    public ResponseEntity<CommonResponse<InsertResponse>> addBookmarkToBlog(@RequestBody BlogBookmarkRequest blogBookmarkRequest) {
        LOGGER.info("{} Start execute add bookmark to blog {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.addBookmarkToBlog(blogBookmarkRequest);
        LOGGER.info("{} End execute add bookmark to blog {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/react")
    public ResponseEntity<CommonResponse<InsertResponse>> addReactToBlog(@RequestBody BlogReactRequest blogReactRequest) {
        LOGGER.info("{} Start execute add react to blog {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.addReactToBlog(blogReactRequest);
        LOGGER.info("{} End execute add react to blog {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/comment-react")
    public ResponseEntity<CommonResponse<InsertResponse>> addReactToBlogComment(@RequestBody BlogCommentReactRequest blogCommentReactRequest) {
        LOGGER.info("{} Start execute add react to blog comment {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.addReactToBlogComment(blogCommentReactRequest);
        LOGGER.info("{} End execute add react to blog comment {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<CommonResponse<InsertResponse>> addCommentToBlog(@RequestBody BlogCommentRequest blogCommentRequest) {
        LOGGER.info("{} Start execute add comment to blog {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.addCommentToBlog(blogCommentRequest);
        LOGGER.info("{} End execute add comment to blog {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
