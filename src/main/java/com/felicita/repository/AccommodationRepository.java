package com.felicita.repository;

import com.felicita.model.dto.*;
import com.felicita.model.response.AccommodationResponse;

import java.util.List;

public interface AccommodationRepository {
    List<AccommodationResponse> getAllAccommodations();

    List<HotelDetailsSectionResponseDto> getHotelsDetailsForSection();

    List<ResortDetailsSectionResponseDto> getResortsDetailsForSection();

    List<HostelDetailsSectionResponseDto> getHostelsDetailsForSection();

    List<VillaDetailsSectionResponseDto> getVillasDetailsForSection();

    List<RestaurantDetailsSectionResponseDto> getRestaurantsDetailsForSection();
}
