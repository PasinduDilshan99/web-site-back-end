package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.ImageStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.GalleryResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.GalleryRepository;
import com.felicita.service.GalleryService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryServiceImpl.class);

    private final GalleryRepository galleryRepository;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<List<GalleryResponse>>> getAllImages() {
        LOGGER.info("Start fetching all images from repository");
        try {
            List<GalleryResponse> galleryResponses = galleryRepository.getAllImages();

            if (galleryResponses.isEmpty()) {
                LOGGER.warn("No images found in database");
                throw new DataNotFoundErrorExceptionHandler("No images found");
            }

            LOGGER.info("Fetched {} images successfully", galleryResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            galleryResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching images: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching images: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch images from database");
        } finally {
            LOGGER.info("End fetching all images from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<GalleryResponse>>> getOpenImages() {
        LOGGER.info("Start fetching all visible images from repository");

        try {
            List<GalleryResponse> galleryResponses = galleryRepository.getAllImages();

            if (galleryResponses.isEmpty()) {
                LOGGER.warn("No images found in database");
                throw new DataNotFoundErrorExceptionHandler("No images found");
            }

            List<GalleryResponse> galleryResponseList = galleryResponses.stream()
                    .filter(item -> ImageStatus.OPEN.toString().equalsIgnoreCase(item.getImageStatus()))
                    .toList();

            if (galleryResponseList.isEmpty()) {
                LOGGER.warn("No visible images found in database");
                throw new DataNotFoundErrorExceptionHandler("No visible images found");
            }

            LOGGER.info("Fetched {} visible images successfully", galleryResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            galleryResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching visible images: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching visible images: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch images from database");
        } finally {
            LOGGER.info("End fetching all visible images from repository");
        }
    }
}
