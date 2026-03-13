package com.felicita.repository;

import com.felicita.model.response.SeasonBasicResponse;
import com.felicita.model.response.SeasonDetailsResponse;

import java.util.List;

public interface SeasonRepository {
    List<SeasonDetailsResponse> getSeasonDetailsBySeasonId(String seasonId);

    List<SeasonBasicResponse> getActiveSeasonDetails();
}
