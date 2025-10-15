package com.felicita.service;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PackageReviewResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService {

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPackages();

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getActivePackages();

    CommonResponse<PackageResponseDto> getPackageDetailsById(String packageId);

    CommonResponse<List<PackageReviewResponse>> getAllPackageReviewDetails();

    CommonResponse<List<PackageReviewResponse>> getPackageReviewDetailsById(String packageId);
}
