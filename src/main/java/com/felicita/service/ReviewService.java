package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ReviewResponse;
import com.felicita.model.response.TourReviewResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<CommonResponse<List<TourReviewResponse>>> getAllTourReviews();

    ResponseEntity<CommonResponse<List<TourReviewResponse>>> getActiveTourReviews();
}
