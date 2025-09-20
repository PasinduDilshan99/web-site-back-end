package com.felicita.repository;

import com.felicita.model.response.FaqResponse;

import java.util.List;

public interface FaqRepository {
    List<FaqResponse> getAllFaqItems();
}
