package com.felicita.repository;

import com.felicita.model.response.AccommodationResponse;

import java.util.List;

public interface AccommodationRepository {
    List<AccommodationResponse> getAllAccommodations();
}
