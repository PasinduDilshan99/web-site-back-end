package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PackageDetailsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService {
    ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> getAllPackages();

    ResponseEntity<CommonResponse<List<PackageDetailsResponse>>> getAllActivePackages();
}
