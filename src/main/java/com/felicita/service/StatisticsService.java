package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.StatisticsResponse;

import java.util.List;

public interface StatisticsService {
    CommonResponse<List<StatisticsResponse>> getAboutUsStatisticsDetails();
}
