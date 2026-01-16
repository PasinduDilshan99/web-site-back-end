package com.felicita.service;

import com.felicita.model.dto.*;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.request.PackageInsertRequest;
import com.felicita.model.request.PackageTerminateRequest;
import com.felicita.model.request.PackageUpdateRequest;
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

    CommonResponse<PackageScheduleDetailsResponse> getPackageSchedulesForId(Long packageId);

    CommonResponse<List<PackageComapreResponse>> getDayToPackageDetailsForComapreByTourId(Long tourId);

    PackageBasicDetailsDto getPackageBasicDetailsByScheduleId(Long packageScheduleId);

    List<PackageActivityPriceDto> getPackageActivityPriceByScheduleId(Long packageScheduleId);

    List<PackageDestinationExtraPriceDto> getPackageDestinationExtraPriceByScheduleId(Long packageScheduleId);

    List<PackageDayAccommodationPriceDto> getPackageDayAccommodationPriceByScheduleId(Long packageScheduleId);

    CommonResponse<List<PackageForTerminateResponse>> getPackagesForTerminate();

    CommonResponse<TerminateResponse> terminatePackage(PackageTerminateRequest packageTerminateRequest);

    CommonResponse<InsertResponse> insertPackage(PackageInsertRequest packageInsertRequest);

    CommonResponse<UpdateResponse> updatePackage(PackageUpdateRequest packageUpdateRequest);

    CommonResponse<PackageAllDetailsResponse> getPackageAllDetailsById(Long packageId);

    CommonResponse<List<PackageIdAndPackageNameResponse>> getPackageIdsAndPackageNames();
}
