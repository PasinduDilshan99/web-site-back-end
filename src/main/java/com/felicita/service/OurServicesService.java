package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.OurServiceResponse;
import java.util.List;

public interface OurServicesService {

    CommonResponse<List<OurServiceResponse>> getAllOurServices();

    CommonResponse<List<OurServiceResponse>> getActiveOurServices();

}
