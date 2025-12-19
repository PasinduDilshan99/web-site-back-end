package com.felicita.repository;

import com.felicita.model.dto.PackageDayByDayDto;
import com.felicita.model.dto.PackageDetailsDto;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.response.*;

import java.util.List;

public interface PackageRepository {
    List<PackageResponseDto> getAllPackages();

    PackageResponseDto getPackageDetailsById(String packageId);

    List<PackageReviewResponse> getAllPackageReviewDetails();

    List<PackageReviewResponse> getPackageReviewDetailsById(String packageId);

    List<PackageHistoryImageResponse> getPackageHistoryImagesById(String packageId);

    List<PackageHistoryImageResponse> getAllPackageHistoryImages();

    List<PackageHistoryDetailsResponse> getPackageHistoryDetailsById(String packageId);

    List<PackageHistoryDetailsResponse> getAllPackageHistoryDetails();

    PackageWithParamsResponse getPackagesWithParams(PackageDataRequest packageDataRequest);

    List<PackageDetailsDto> getDayToPackageDetailsById(Long tourId);

    List<PackageDayByDayDto> getPackagesAccoamdationsByIds(List<Long> packgeIds);

    List<Long> getPackageIdsByTourId(Long tourId);

    List<PackageExtrasResponse.PackageExclusion> getPackageExclusions(Long packageId);

    List<PackageExtrasResponse.PackageInclusion> getPackageInclusions(Long packageId);

    List<PackageExtrasResponse.PackageCondition> getPackageConditions(Long packageId);

    List<PackageExtrasResponse.PackageTravelTip> getPackageTravelTips(Long packageId);

    List<Long> getPackageSchedulesIdsByTourId(Long tourId);

    List<PackageScheduleResponse.PackageScheduleDetails> getPackageSchedulesById(Long packageId);

    List<PackageScheduleDetailsResponse.PackageScheduleDetails> getPackageSchedulesForId(Long packageId);

    PackageScheduleDetailsResponse.PackageBasicDetails getPackageBasicDetails(Long packageId);
}
