package com.felicita.repository;

import com.felicita.model.response.OurServiceResponse;

import java.util.List;

public interface OurServicesRepository {
    List<OurServiceResponse> getAllOurServices();
}
