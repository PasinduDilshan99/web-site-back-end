package com.felicita.repository;

import com.felicita.model.response.PromotionTourResponse;

import java.util.List;

public interface PromotionsRepository {
    List<PromotionTourResponse> getAllPromotions();
}
