package com.felicita.repository;

import com.felicita.model.response.StatisticsResponse;

import java.util.List;

public interface StatisticsRepository {
    List<StatisticsResponse> getAboutUsStatisticsDetails();
}
