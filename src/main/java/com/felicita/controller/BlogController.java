package com.felicita.controller;

import com.felicita.model.request.BlogDetailsRequest;
import com.felicita.model.request.CreateBlogRequest;
import com.felicita.model.response.BlogResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.InsertResponse;
import com.felicita.model.response.PartnerResponse;
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
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllBlogs(){
        LOGGER.info("{} Start execute get all blogs {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<BlogResponse>>> response = blogService.getAllBlogs();
        LOGGER.info("{} End execute get all blogs {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<BlogResponse>>> getAllActiveBlogs(){
        LOGGER.info("{} Start execute get all active blogs {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<BlogResponse>>> response = blogService.getAllActiveBlogs();
        LOGGER.info("{} End execute get all active blogs {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/blog-details")
    public ResponseEntity<CommonResponse<BlogResponse>> getBlogDetailsById(@RequestBody BlogDetailsRequest blogDetailsRequest){
        LOGGER.info("{} Start execute get blog details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<BlogResponse> response = blogService.getBlogDetailsById(blogDetailsRequest);
        LOGGER.info("{} End execute get blog details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CommonResponse<InsertResponse>> createBlog(@RequestBody CreateBlogRequest createBlogRequest){
        LOGGER.info("{} Start execute create blog {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<InsertResponse> response = blogService.createBlog(createBlogRequest);
        LOGGER.info("{} End execute create blog {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
