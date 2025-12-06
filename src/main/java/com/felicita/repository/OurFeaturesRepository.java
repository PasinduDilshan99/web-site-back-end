package com.felicita.repository;

import com.felicita.model.response.FeatureResponse;

import java.util.List;

public interface OurFeaturesRepository {
    List<FeatureResponse> getOurFeaturesDetails();
}
