package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurStoryAndValuesResponse;

public interface OurStoryService {

    CommonResponse<OurStoryAndValuesResponse> getOurStoryAndKeyValueDetails();

}
