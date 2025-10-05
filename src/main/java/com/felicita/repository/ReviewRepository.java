package com.felicita.repository;

import com.felicita.model.response.ReviewResponse;
import com.felicita.model.response.TourReviewResponse;

import java.util.List;

public interface ReviewRepository {

    List<TourReviewResponse> getAllTourReviews();
}
