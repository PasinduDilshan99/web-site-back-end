package com.felicita.service;

import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.SeasonBasicResponse;
import com.felicita.model.response.SeasonDetailsResponse;

import java.util.List;

public interface SeasonService {
    CommonResponse<List<SeasonDetailsResponse>> getSeasonDetailsBySeasonId(String seasonId);

    CommonResponse<List<SeasonBasicResponse>> getActiveSeasonDetails();
}
