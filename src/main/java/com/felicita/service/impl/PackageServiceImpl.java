package com.felicita.service.impl;

import com.felicita.exception.*;
import com.felicita.model.dto.*;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.request.PackageDataRequest;
import com.felicita.model.request.PackageInsertRequest;
import com.felicita.model.request.PackageTerminateRequest;
import com.felicita.model.request.PackageUpdateRequest;
import com.felicita.model.response.*;
import com.felicita.repository.PackageRepository;
import com.felicita.service.CommonService;
import com.felicita.service.PackageService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.PackageValidationService;
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
    private final PackageValidationService packageValidationService;
    private final CommonService commonService;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, PackageValidationService packageValidationService, CommonService commonService) {
        this.packageRepository = packageRepository;
        this.packageValidationService = packageValidationService;
        this.commonService = commonService;
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

            if (packageWithParamsResponse == null) {
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

            List<PackageDayAccommodationResponse> responses = new ArrayList<>();

            for (PackageDetailsDto packageDetailsDto : packageDetailsDtos) {
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
                for (PackageDayByDayDto packageDayByDayDto : packageDayByDayDtos) {
                    if (packageDayByDayDto.getPackageId().equals(packageDetailsDto.getPackageId())) {
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
            for (Long packageId : packageIds) {
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
            for (Long packageId : packageIds) {
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

    @Override
    public CommonResponse<PackageScheduleDetailsResponse> getPackageSchedulesForId(Long packageId) {
        LOGGER.info("Start fetching tour schedules from the repository");
        try {
            List<PackageScheduleDetailsResponse.PackageScheduleDetails> scheduleDetails =
                    packageRepository.getPackageSchedulesForId(packageId);
            PackageScheduleDetailsResponse.PackageBasicDetails packageBasicDetails =
                    packageRepository.getPackageBasicDetails(packageId);


            PackageScheduleDetailsResponse response = new PackageScheduleDetailsResponse(
                    packageBasicDetails,
                    scheduleDetails
            );

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    response,
                    Instant.now()
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching package schedules by package id: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching package schedules by package id: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch package schedules by package id from database");
        } finally {
            LOGGER.info("End fetching all package schedules by package id from repository");
        }
    }

    @Override
    public CommonResponse<List<PackageComapreResponse>> getDayToPackageDetailsForComapreByTourId(Long tourId) {
        LOGGER.info("Start fetching all package from repository");
        try {
            List<PackageComapreResponse> comapreResponses = new ArrayList<>();
            List<PackageExtrasResponse> extrasResponses = getPackageExtraDetailsDayByDay(tourId).getData();
            List<PackageDayAccommodationResponse> accommodationResponses = getDayToPackageDetailsByTourId(tourId).getData();
            List<PackageComapreResponse.PackageImages> images = packageRepository.getAllPackagesImages(tourId);

            for (PackageDayAccommodationResponse accommodationResponse: accommodationResponses){
                PackageComapreResponse packageComapreResponse = new PackageComapreResponse();
                packageComapreResponse.setPackageId(accommodationResponse.getPackageId());
                packageComapreResponse.setPackageName(accommodationResponse.getPackageName());
                packageComapreResponse.setPackageDescription(accommodationResponse.getPackageDescription());
                packageComapreResponse.setTotalPrice(accommodationResponse.getTotalPrice());
                packageComapreResponse.setPricePerPerson(accommodationResponse.getPricePerPerson());
                packageComapreResponse.setDiscount(accommodationResponse.getDiscount());
                packageComapreResponse.setColor(accommodationResponse.getColor());
                packageComapreResponse.setHoverColor(accommodationResponse.getHoverColor());
                packageComapreResponse.setPackageDayByDayDtoList(accommodationResponse.getPackageDayByDayDtoList());

                for (PackageExtrasResponse extrasResponse: extrasResponses){
                    if (extrasResponse.getPackageId().equals(accommodationResponse.getPackageId())){
                        packageComapreResponse.setExtraDetails(extrasResponse);
                    }
                }

                List<PackageComapreResponse.PackageImages> packageImages = new ArrayList<>();
                for (PackageComapreResponse.PackageImages image: images){
                    if (image.getPackageId().equals(accommodationResponse.getPackageId())){
                        packageImages.add(image);
                    }
                }
                packageComapreResponse.setImages(packageImages);
                comapreResponses.add(packageComapreResponse);
            }

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    comapreResponses,
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

    @Override
    public PackageBasicDetailsDto getPackageBasicDetailsByScheduleId(Long packageScheduleId) {
        LOGGER.info("Start fetching package basic details by schedule id from repository");
        try {
            PackageBasicDetailsDto response = packageRepository.getPackageBasicDetailsByScheduleId(packageScheduleId);
            LOGGER.info("Successfully fetched package basic details by schedule id from repository.");
            return response;
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
    public List<PackageActivityPriceDto> getPackageActivityPriceByScheduleId(Long packageScheduleId) {
        LOGGER.info("Start fetching package basic details by schedule id from repository");
        try {
            List<PackageActivityPriceDto> response = packageRepository.getPackageActivityPriceByScheduleId(packageScheduleId);
            LOGGER.info("Successfully fetched package basic details by schedule id from repository.");
            return response;
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
    public List<PackageDestinationExtraPriceDto> getPackageDestinationExtraPriceByScheduleId(Long packageScheduleId) {
        LOGGER.info("Start fetching package basic details by schedule id from repository");
        try {
            List<PackageDestinationExtraPriceDto> response = packageRepository.getPackageDestinationExtraPriceByScheduleId(packageScheduleId);
            LOGGER.info("Successfully fetched package basic details by schedule id from repository.");
            return response;
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
    public List<PackageDayAccommodationPriceDto> getPackageDayAccommodationPriceByScheduleId(Long packageScheduleId) {
        LOGGER.info("Start fetching package basic details by schedule id from repository");
        try {
            List<PackageDayAccommodationPriceDto> response = packageRepository.getPackageDayAccommodationPriceByScheduleId(packageScheduleId);
            LOGGER.info("Successfully fetched package basic details by schedule id from repository.");
            return response;
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
    public CommonResponse<List<PackageForTerminateResponse>> getPackagesForTerminate() {
        LOGGER.info("Start fetching active package from repository");
        try {
            List<PackageForTerminateResponse> packageForTerminateResponses =
                    packageRepository.getPackagesForTerminate();

            if (packageForTerminateResponses.isEmpty()) {
                LOGGER.warn("No active package found in database");
                throw new DataNotFoundErrorExceptionHandler("No active package found");
            }

            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            packageForTerminateResponses,
                            Instant.now()
                    )
                    ;

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching active package: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active package from database");
        } finally {
            LOGGER.info("End fetching active package from repository");
        }
    }

    @Override
    public CommonResponse<TerminateResponse> terminatePackage(PackageTerminateRequest packageTerminateRequest) {
        LOGGER.info("Start execute terminate package request.");
        try {
            packageValidationService.validateTerminatePackageRequest(packageTerminateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            packageRepository.terminatePackage(packageTerminateRequest, userId);
            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            new TerminateResponse("Successfully terminate package request"),
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the terminate package request", vfe.getValidationFailedResponses());
        } catch (TerminateFailedErrorExceptionHandler tfe) {
            throw new TerminateFailedErrorExceptionHandler(tfe.getMessage());
        }catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<InsertResponse> insertPackage(PackageInsertRequest packageInsertRequest) {
        LOGGER.info("Start execute insert package request.");
        try {
            packageValidationService.validatePackageInsertRequest(packageInsertRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            Long packageId = packageRepository.insertPackageDeails(packageInsertRequest, userId);
            packageRepository.insertPackageImages(packageId, packageInsertRequest.getImages(), userId);
            packageRepository.insertPackageInclusions(packageId, packageInsertRequest.getInclusions(), userId);
            packageRepository.insertPackageExclusions(packageId, packageInsertRequest.getExclusions(), userId);
            packageRepository.insertPackageConditions(packageId, packageInsertRequest.getConditions(), userId);
            packageRepository.insertPackageTravelTips(packageId, packageInsertRequest.getTravelTips(), userId);
            packageRepository.insertDayByDayAccommodations(packageId, packageInsertRequest.getDayAccommodations(), userId);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    new InsertResponse("Successfully insert package request"),
                    Instant.now());

        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert package request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler(uae.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<UpdateResponse> updatePackage(PackageUpdateRequest packageUpdateRequest) {
        LOGGER.info("Start execute update package request.");
        try {
            packageValidationService.validatePackageUpdateRequest(packageUpdateRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            packageRepository.updatePackageBasicDetails(packageUpdateRequest.getPackageId(), packageUpdateRequest.getPackageBasicDetails(), userId);

            packageRepository.insertPackageImages(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddImages(), userId);
            packageRepository.removePackageImages(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemovedImageIds(), userId);
            packageRepository.updatePackageImages(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedImages(), userId);

            packageRepository.insertDayByDayAccommodations(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddDayAccommodations(), userId);
            packageRepository.removeDayByDayAccommodations(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemoveDayAccommodationIds(), userId);
            packageRepository.updateDayByDayAccommodations(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedDayAccommodations(), userId);

            packageRepository.insertPackageInclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddInclusions(), userId);
            packageRepository.removePcakageInclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemoveInclusionIds(), userId);
            packageRepository.updatePackageInclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedInclusions(), userId);

            packageRepository.insertPackageExclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddExclusions(), userId);
            packageRepository.removePackageExclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemoveExclusionIds(), userId);
            packageRepository.updatePackageExclusions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedExclusions(), userId);

            packageRepository.insertPackageConditions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddConditions(), userId);
            packageRepository.removePcakageConditions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemoveConditionIds(), userId);
            packageRepository.updatePackageConditions(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedConditions(), userId);

            packageRepository.insertPackageTravelTips(packageUpdateRequest.getPackageId(), packageUpdateRequest.getAddTravelTips(), userId);
            packageRepository.removePcakageTravelTips(packageUpdateRequest.getPackageId(), packageUpdateRequest.getRemoveTravelTipIds(), userId);
            packageRepository.updatePackageTravelTips(packageUpdateRequest.getPackageId(), packageUpdateRequest.getUpdatedTravelTips(), userId);

            return new CommonResponse<>(
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                    CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                    new UpdateResponse("Successfully update package request", packageUpdateRequest.getPackageId()),
                    Instant.now());
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the update package request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());
        } catch (UnAuthenticateErrorExceptionHandler uae) {
            throw new UnAuthenticateErrorExceptionHandler("before login to update the package details");
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }
}
