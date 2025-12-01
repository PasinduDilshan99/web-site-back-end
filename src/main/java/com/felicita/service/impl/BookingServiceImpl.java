package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityResponseDto;
import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CompleteToursResponse;
import com.felicita.model.response.UpcomingToursResponse;
import com.felicita.repository.BookingRepository;
import com.felicita.service.BookingService;
import com.felicita.service.CommonService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final CommonService commonService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CommonService commonService) {
        this.bookingRepository = bookingRepository;
        this.commonService=commonService;
    }

    @Override
    public CommonResponse<List<CompleteToursResponse>> getCompletedBookingToursDetailsById() {
        LOGGER.info("Start fetching all completed booking tours details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<CompleteToursResponse> completeToursResponses = bookingRepository.getCompletedBookingToursDetailsById(userId);

            if (completeToursResponses.isEmpty()) {
                LOGGER.warn("No completed booking tours details found in database");
                throw new DataNotFoundErrorExceptionHandler("No completed booking tours details found");
            }

            LOGGER.info("Fetched {} completed booking tours details successfully", completeToursResponses.size());
            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            completeToursResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching completed booking tours details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching completed booking tours details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch completed booking tours details from database");
        } finally {
            LOGGER.info("End fetching all completed booking tours details from repository");
        }
    }

    @Override
    public CommonResponse<List<UpcomingToursResponse>> getUpcomingBookingToursDetailsById() {
        LOGGER.info("Start fetching all upcoming booking tours details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<UpcomingToursResponse> upcomingToursResponses = bookingRepository.getUpcomingBookingToursDetailsById(userId);

            if (upcomingToursResponses.isEmpty()) {
                LOGGER.warn("No upcoming booking tours details found in database");
                throw new DataNotFoundErrorExceptionHandler("No upcoming booking tours details found");
            }

            LOGGER.info("Fetched {} upcoming booking tours details successfully", upcomingToursResponses.size());
            return(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            upcomingToursResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching upcoming booking tours details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching upcoming booking tours details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch upcoming booking tours details from database");
        } finally {
            LOGGER.info("End fetching all upcoming booking tours details from repository");
        }
    }
}
