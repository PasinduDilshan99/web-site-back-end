package com.felicita.repository;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.response.PackageReviewResponse;

import java.util.List;

public interface PackageRepository {
    List<PackageResponseDto> getAllPackages();

    PackageResponseDto getPackageDetailsById(String packageId);

    List<PackageReviewResponse> getAllPackageReviewDetails();

    List<PackageReviewResponse> getPackageReviewDetailsById(String packageId);
}
