package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ServiceProviderDetailsResponse;

import java.util.List;

public interface ServiceProviderService {
    CommonResponse<List<ServiceProviderDetailsResponse>> getServiceProviderDetailsById(String serviceProviderId);
}
