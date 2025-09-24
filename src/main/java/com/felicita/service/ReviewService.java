package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ReviewResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    ResponseEntity<CommonResponse<List<ReviewResponse>>> getAllReviews();

    ResponseEntity<CommonResponse<List<ReviewResponse>>> getAllActiveReviews();
}
