package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.FooterResponse;

public interface FooterService {

    CommonResponse<FooterResponse> getAllFooterData();

    CommonResponse<FooterResponse> getActiveFooterData();

}
