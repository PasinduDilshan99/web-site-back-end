package com.felicita.service;


import com.felicita.model.response.AllCategoriesResponse;
import com.felicita.model.response.CommonResponse;

public interface CommonService {
    Long getUserIdBySecurityContext();
    Long getUserIdBySecurityContextWithOutException();
    String generateRandomOtp();

    CommonResponse<AllCategoriesResponse> getAllCategories();
}
