package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.DestinationResponseDto;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PackageReviewResponse;
import com.felicita.repository.PackageRepository;
import com.felicita.service.PackageService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
}
