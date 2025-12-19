package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.PackageDayByDayDto;
import com.felicita.model.dto.PackageDetailsDto;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.response.*;
import com.felicita.repository.PackageRepository;
import com.felicita.service.PackageService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageServiceImpl.class);

    private final PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }


    @Override
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPackages() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageResponseDto> packageResponseDtos = packageRepository.getAllPackages();

            if (packageResponseDtos.isEmpty()) {
                LOGGER.warn("No package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            LOGGER.info("Fetched {} package successfully", packageResponseDtos.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageResponseDtos,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getActivePackages() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<PackageResponseDto> packageResponseDtos = packageRepository.getAllPackages();

            if (packageResponseDtos.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            List<PackageResponseDto> packageResponseDtoList = packageResponseDtos.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getPackageStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active package successfully", packageResponseDtoList.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageResponseDtoList,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active package from database");
        } finally {
            LOGGER.info("End fetching all active package from repository");
        }
    }

    @Override
    public CommonResponse<PackageResponseDto> getPackageDetailsById(String packageId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            PackageResponseDto packageResponseDto = packageRepository.getPackageDetailsById(packageId);

            if (packageResponseDto == null) {
                LOGGER.warn("No package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageResponseDto,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageReviewResponse>> getAllPackageReviewDetails() {
        LOGGER.info("Start fetching all active package from repository");
        try {
            List<PackageReviewResponse> packageReviewResponses = packageRepository.getAllPackageReviewDetails();

            if (packageReviewResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            List<PackageReviewResponse> packageResponseDtoList = packageReviewResponses.stream()
                    .filter(data -> CommonStatus.ACTIVE.name().equalsIgnoreCase(data.getStatus()))
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} active package successfully", packageResponseDtoList.size());
            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageResponseDtoList,
                            Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active package from database");
        } finally {
            LOGGER.info("End fetching all active package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageReviewResponse>> getPackageReviewDetailsById(String packageId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageReviewResponse> packageReviewResponse = packageRepository.getPackageReviewDetailsById(packageId);

            if (packageReviewResponse.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageReviewResponse,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageHistoryDetailsResponse>> getAllPackageHistoryDetails() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageHistoryDetailsResponse> packageHistoryDetailsResponses = packageRepository.getAllPackageHistoryDetails();

            if (packageHistoryDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageHistoryDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageHistoryDetailsResponse>> getPackageHistoryDetailsById(String packageId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageHistoryDetailsResponse> packageHistoryDetailsResponses = packageRepository.getPackageHistoryDetailsById(packageId);

            if (packageHistoryDetailsResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageHistoryDetailsResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageHistoryImageResponse>> getAllPackageHistoryImages() {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageHistoryImageResponse> packageHistoryImageResponse = packageRepository.getAllPackageHistoryImages();

            if (packageHistoryImageResponse.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageHistoryImageResponse,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageHistoryImageResponse>> getPackageHistoryImagesById(String packageId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageHistoryImageResponse> packageHistoryImageResponse = packageRepository.getPackageHistoryImagesById(packageId);

            if (packageHistoryImageResponse.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageHistoryImageResponse,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<PackageWithParamsResponse> getPackagesWithParams(PackageDataRequest packageDataRequest) {
        LOGGER.info("Start fetching packages with params from repository");
        try {
            PackageWithParamsResponse packageWithParamsResponse = packageRepository.getPackagesWithParams(packageDataRequest);

            if (packageWithParamsResponse == null){
                return new CommonResponse<>(
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                        CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                        null,
                        Instant.now()
                );
            }

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageWithParamsResponse,
                            Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching packages with params: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching packages with params: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch packages with params from database");
        } finally {
            LOGGER.info("End fetching all packages with params from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageDayAccommodationResponse>> getDayToPackageDetailsByTourId(Long tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageDetailsDto> packageDetailsDtos = packageRepository.getDayToPackageDetailsById(tourId);
            LOGGER.info(packageDetailsDtos.toString());
            List<Long> packgeIds = packageDetailsDtos.stream()
                    .map(PackageDetailsDto::getPackageId)
                    .toList();

            List<PackageDayByDayDto> packageDayByDayDtos = packageRepository.getPackagesAccoamdationsByIds(packgeIds);

            List<PackageDayAccommodationResponse>  responses = new ArrayList<>();

            for (PackageDetailsDto packageDetailsDto : packageDetailsDtos){
                PackageDayAccommodationResponse packageDayAccommodationResponse = new PackageDayAccommodationResponse();
                packageDayAccommodationResponse.setPackageId(packageDetailsDto.getPackageId());
                packageDayAccommodationResponse.setPackageName(packageDetailsDto.getPackageName());
                packageDayAccommodationResponse.setPackageDescription(packageDetailsDto.getPackageDescription());
                packageDayAccommodationResponse.setTotalPrice(packageDetailsDto.getTotalPrice());
                packageDayAccommodationResponse.setPricePerPerson(packageDetailsDto.getPricePerPerson());
                packageDayAccommodationResponse.setDiscount(packageDetailsDto.getDiscount());
                packageDayAccommodationResponse.setColor(packageDetailsDto.getColor());
                packageDayAccommodationResponse.setHoverColor(packageDetailsDto.getHoverColor());

                List<PackageDayByDayDto> packageDayByDayDtoList = new ArrayList<>();
                for (PackageDayByDayDto packageDayByDayDto : packageDayByDayDtos){
                    if (packageDayByDayDto.getPackageId().equals(packageDetailsDto.getPackageId())){
                        packageDayByDayDtoList.add(packageDayByDayDto);
                    }
                }
                packageDayAccommodationResponse.setPackageDayByDayDtoList(packageDayByDayDtoList);
                responses.add(packageDayAccommodationResponse);
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            responses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageExtrasResponse>> getPackageExtraDetailsDayByDay(Long tourId) {
        LOGGER.info("Start fetching package extra details by tour id from repository");
        try {

            List<Long> packageIds = packageRepository.getPackageIdsByTourId(tourId);

            List<PackageExtrasResponse> responses = new ArrayList<>();
            for (Long packageId : packageIds){
                List<PackageExtrasResponse.PackageInclusion> inclusions = packageRepository.getPackageInclusions(packageId);
                List<PackageExtrasResponse.PackageExclusion> exclusions = packageRepository.getPackageExclusions(packageId);
                List<PackageExtrasResponse.PackageCondition> conditions = packageRepository.getPackageConditions(packageId);
                List<PackageExtrasResponse.PackageTravelTip> travelTips = packageRepository.getPackageTravelTips(packageId);

                PackageExtrasResponse packageExtrasResponse = new PackageExtrasResponse(
                        packageId,
                        inclusions,
                        exclusions,
                        conditions,
                        travelTips
                );

                responses.add(packageExtrasResponse);
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    responses,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package extra details by tour id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package extra details by tour id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package extra details by tour id from database");
        } finally {
            LOGGER.info("End fetching all package extra details by tour id from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageScheduleResponse>> getPackageSchedulesByTourId(Long tourId) {
        LOGGER.info("Start fetching all package schedules from repository");
        try {
            List<Long> packageIds = packageRepository.getPackageIdsByTourId(tourId);

            List<PackageScheduleResponse> responses = new ArrayList<>();
            for (Long packageId : packageIds){
                List<PackageScheduleResponse.PackageScheduleDetails> scheduleDetails =
                        packageRepository.getPackageSchedulesById(packageId);

                PackageScheduleResponse packageScheduleResponse = new PackageScheduleResponse(
                        packageId,
                        scheduleDetails
                );

                responses.add(packageScheduleResponse);
            }

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            responses,
                            Instant.now()
                    );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package from database");
        } finally {
            LOGGER.info("End fetching all package from repository");
        }
    }
}
