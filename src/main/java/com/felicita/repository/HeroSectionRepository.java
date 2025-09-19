package com.felicita.repository;

import com.felicita.model.response.HeroSectionResponse;

import java.util.List;

public interface HeroSectionRepository {
    List<HeroSectionResponse> getAllHeroSectionItems();
}
