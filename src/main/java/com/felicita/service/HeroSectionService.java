package com.felicita.service;

import com.felicita.model.response.AboutUsHeroSectionResponse;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.HeroSectionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HeroSectionService {
    ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllHeroSectionItems();

    ResponseEntity<CommonResponse<List<HeroSectionResponse>>> getAllVisibleHeroSectionItems();

    CommonResponse<List<AboutUsHeroSectionResponse>> getAboutUsHeroSectionDetails();
}
