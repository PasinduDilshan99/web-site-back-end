package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurStoryAndValuesResponse;

import java.util.List;

public interface OurStoryService {
    CommonResponse<OurStoryAndValuesResponse> getOurStoryAndKeyValueDetails();
}
