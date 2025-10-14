package com.felicita.service;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService {

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPackages();

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getActivePackages();

    CommonResponse<PackageResponseDto> getPackageDetailsById(String packageId);
}
