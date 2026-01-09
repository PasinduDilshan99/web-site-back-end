package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ServiceProviderDetailsResponse;
import com.felicita.model.response.ServiceProviderIdsAndNamesReponse;

import java.util.List;

public interface ServiceProviderService {
    CommonResponse<ServiceProviderDetailsResponse> getServiceProviderDetailsById(String serviceProviderId);

    CommonResponse<List<ServiceProviderIdsAndNamesReponse>> getServiceProviderNamesAndIds();
}
