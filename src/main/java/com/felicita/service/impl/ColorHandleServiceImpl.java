package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.PageColorsResponse;
import com.felicita.model.response.PartnerResponse;
import com.felicita.repository.ColorHandleRepository;
import com.felicita.service.ColorHandleService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ColorHandleServiceImpl implements ColorHandleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColorHandleServiceImpl.class);

    private final ColorHandleRepository colorHandleRepository;

    @Autowired
    public ColorHandleServiceImpl(ColorHandleRepository colorHandleRepository) {
        this.colorHandleRepository = colorHandleRepository;
    }

    @Override
    public ResponseEntity<CommonResponse<PageColorsResponse>> getHomePageColors(String pageName) {
        LOGGER.info("Start fetching all home page colors from repository");
        try {
            PageColorsResponse pageColorsResponse = colorHandleRepository.getHomePageColors(pageName);

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            pageColorsResponse,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching home page colors: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching home page colors: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch home page colors from database");
        } finally {
            LOGGER.info("End fetching all home page colors from repository");
        }
    }

}
