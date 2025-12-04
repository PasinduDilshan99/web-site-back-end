package com.felicita.repository;

import com.felicita.model.response.AboutUsHeroSectionResponse;
import com.felicita.model.response.HeroSectionResponse;

import java.util.List;

public interface HeroSectionRepository {
    List<HeroSectionResponse> getAllHeroSectionItems();

    List<AboutUsHeroSectionResponse> getAboutUsHeroSectionDetails();
}
