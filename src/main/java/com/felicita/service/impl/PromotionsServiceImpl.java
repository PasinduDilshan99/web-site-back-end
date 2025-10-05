package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.PackageResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.model.response.PromotionTourResponse;
import com.felicita.repository.PackageRepository;
import com.felicita.repository.PromotionsRepository;
import com.felicita.service.PromotionsService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class PromotionsServiceImpl implements PromotionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionsServiceImpl.class);

    private final PromotionsRepository promotionsRepository;
    private final PackageRepository packageRepository;

    @Autowired
    public PromotionsServiceImpl(PromotionsRepository promotionsRepository,
                                 PackageRepository packageRepository) {
        this.promotionsRepository = promotionsRepository;
        this.packageRepository = packageRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllPromotions() {
        LOGGER.info("Start fetching all tour promotions from repository");
        try {
            List<PackageResponseDto> packageResponseDtos = packageRepository.getAllPackages();

            List<PackageResponseDto> list = packageResponseDtos.stream()
                    .filter(item -> item.getDiscountPercentage().compareTo(BigDecimal.ZERO) > 0)
                    .toList();

            if (list.isEmpty()) {
                LOGGER.warn("No tour promotions found in database");
                throw new DataNotFoundErrorExceptionHandler("No tour promotions found");
            }

            LOGGER.info("Fetched {} tour promotions successfully", list.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            list,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching tour promotions: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching tour promotions: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour promotions from database");
        } finally {
            LOGGER.info("End fetching all tour promotions from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PackageResponseDto>>> getAllActivePromotions() {
        LOGGER.info("Start fetching all visible tour promotions from repository");

        try {
            List<PackageResponseDto> packageResponseDtos = packageRepository.getAllPackages();

            List<PackageResponseDto> list = packageResponseDtos.stream()
                    .filter(item -> item.getDiscountPercentage().compareTo(BigDecimal.ZERO) > 0)
                    .filter(item -> item.getPackageStatus().equalsIgnoreCase(CommonStatus.ACTIVE.name()))
                    .toList();


            if (list.isEmpty()) {
                LOGGER.warn("No visible tour promotions found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible tour promotions found");
            }

            LOGGER.info("Fetched {} visible tour promotions successfully", list.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            list,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible tour promotions: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible tour promotions: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch tour promotions from database");
        } finally {
            LOGGER.info("End fetching all visible tour promotions from repository");
        }
    }
}
