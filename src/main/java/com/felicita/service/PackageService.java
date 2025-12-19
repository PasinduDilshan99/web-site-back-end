package com.felicita.service;

import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService {

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPackages();

    ResponseEntity<CommonResponse<List<PackageResponseDto>>> getActivePackages();

    CommonResponse<PackageResponseDto> getPackageDetailsById(String packageId);

    CommonResponse<List<PackageReviewResponse>> getAllPackageReviewDetails();

    CommonResponse<List<PackageReviewResponse>> getPackageReviewDetailsById(String packageId);

    CommonResponse<List<PackageHistoryDetailsResponse>> getAllPackageHistoryDetails();

    CommonResponse<List<PackageHistoryDetailsResponse>> getPackageHistoryDetailsById(String packageId);

    CommonResponse<List<PackageHistoryImageResponse>> getAllPackageHistoryImages();

    CommonResponse<List<PackageHistoryImageResponse>> getPackageHistoryImagesById(String packageId);

    CommonResponse<PackageWithParamsResponse> getPackagesWithParams(PackageDataRequest packageDataRequest);

    CommonResponse<List<PackageDayAccommodationResponse>> getDayToPackageDetailsByTourId(Long tourId);

    CommonResponse<List<PackageExtrasResponse>> getPackageExtraDetailsDayByDay(Long tourId);

    CommonResponse<List<PackageScheduleResponse>> getPackageSchedulesByTourId(Long tourId);
}
