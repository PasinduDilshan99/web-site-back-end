package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FeatureResponse;

import java.util.List;

public interface OurFeaturesService {
    CommonResponse<List<FeatureResponse>> getOurFeaturesDetails();
}
