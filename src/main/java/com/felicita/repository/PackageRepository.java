package com.felicita.repository;

import com.felicita.model.dto.*;
import com.felicita.model.request.*;
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

    List<PackageComapreResponse.PackageImages> getAllPackagesImages(Long tourId);

    PackageBasicDetailsDto getPackageBasicDetailsByScheduleId(Long packageScheduleId);

    List<PackageDayAccommodationPriceDto> getPackageDayAccommodationPriceByScheduleId(Long packageScheduleId);

    List<PackageDestinationExtraPriceDto> getPackageDestinationExtraPriceByScheduleId(Long packageScheduleId);

    List<PackageActivityPriceDto> getPackageActivityPriceByScheduleId(Long packageScheduleId);

    List<PackageForTerminateResponse> getPackagesForTerminate();

    void terminatePackage(PackageTerminateRequest packageTerminateRequest, Long userId);

    Long insertPackageDeails(PackageInsertRequest packageInsertRequest, Long userId);

    void insertTourImages(Long packageId, List<PackageImageInsertRequest> images, Long userId);

    void insertTourInclusions(Long packageId, List<PackageInclusionInsertRequest> inclusions, Long userId);

    void insertTourExclusions(Long packageId, List<PackageExclusionInsertRequest> exclusions, Long userId);

    void insertTourConditions(Long packageId, List<PackageConditionInsertRequest> conditions, Long userId);

    void insertTourTravelTips(Long packageId, List<PackageTravelTipInsertRequest> travelTips, Long userId);

    void insertDayByDayAccommodations(Long packageId, List<PackageDayAccommodationInsertRequest> dayAccommodations, Long userId);
}
