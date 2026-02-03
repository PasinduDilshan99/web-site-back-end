package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.WhyChooseUsResponse;
import java.util.List;

public interface WhyChooseUsService {

    CommonResponse<List<WhyChooseUsResponse>> getAllWhyChooseUsData();

    CommonResponse<List<WhyChooseUsResponse>> getActiveWhyChooseUsData();

}
