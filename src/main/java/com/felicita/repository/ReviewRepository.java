package com.felicita.repository;

import com.felicita.model.response.ReviewResponse;

import java.util.List;

public interface ReviewRepository {
    List<ReviewResponse> getAllReviews();
}
