package com.felicita.controller;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.ReviewResponse;
import com.felicita.service.ReviewService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/api/review")
public class ReviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CommonResponse<List<ReviewResponse>>> getAllReviews(){
        LOGGER.info("{} Start execute get all reviews {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ReviewResponse>>> response = reviewService.getAllReviews();
        LOGGER.info("{} End execute get all reviews {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/active")
    public ResponseEntity<CommonResponse<List<ReviewResponse>>> getAllActiveReviews(){
        LOGGER.info("{} Start execute get all active reviews {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<ReviewResponse>>> response = reviewService.getAllActiveReviews();
        LOGGER.info("{} End execute get all active reviews {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
