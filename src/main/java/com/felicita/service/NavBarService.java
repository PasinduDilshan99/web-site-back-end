package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.NavBarResponse;
import java.util.List;

public interface NavBarService {

    CommonResponse<List<NavBarResponse>> getAllNavBarData();

    CommonResponse<List<NavBarResponse>> getActiveNavBarData();

}
