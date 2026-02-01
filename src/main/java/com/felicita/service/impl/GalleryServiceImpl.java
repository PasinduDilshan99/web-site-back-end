package com.felicita.service.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.enums.ImageStatus;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.GalleryResponse;
import com.felicita.repository.GalleryRepository;
import com.felicita.service.GalleryService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonResponse<List<GalleryResponse>> getAllImages() {
        LOGGER.info("Start fetching all images from repository");
        try {
            List<GalleryResponse> galleryResponses = galleryRepository.getAllImages();

            if (galleryResponses.isEmpty()) {
                LOGGER.warn("No images found in database");
                throw new DataNotFoundErrorExceptionHandler("No images found");
            }

            LOGGER.info("Fetched {} images successfully", galleryResponses.size());
            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            galleryResponses,
                            Instant.now());

        }catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching images: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch images from database");
        } finally {
            LOGGER.info("End fetching all images from repository");
        }
    }

    @Override
    public CommonResponse<List<GalleryResponse>> getOpenImages() {
        LOGGER.info("Start fetching active images from repository");

        try {
            List<GalleryResponse> galleryResponses = getAllImages().getData();

            List<GalleryResponse> galleryResponseList = galleryResponses.stream()
                    .filter(item -> ImageStatus.ACTIVE.toString().equalsIgnoreCase(item.getImageStatus()))
                    .toList();

            if (galleryResponseList.isEmpty()) {
                LOGGER.warn("No active images found in database");
                throw new DataNotFoundErrorExceptionHandler("No active images found");
            }

            LOGGER.info("Fetched {} active images successfully", galleryResponseList.size());

            return new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            galleryResponseList,
                            Instant.now());

        }catch (DataNotFoundErrorExceptionHandler | DataAccessErrorExceptionHandler e) {
            throw e;
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active images: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch active images from database");
        } finally {
            LOGGER.info("End fetching active images from repository");
        }
    }

}
