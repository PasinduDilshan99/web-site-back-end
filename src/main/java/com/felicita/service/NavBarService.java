package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.NavBarResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NavBarService {
    ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllNavBarItems();

    ResponseEntity<CommonResponse<List<NavBarResponse>>> getAllVisibleNavBarItems();
}
