package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.TourImageDto;
import com.felicita.model.dto.TourResponseDto;
import com.felicita.model.enums.CommonStatus;
import com.felicita.model.enums.PartnerStatus;
import com.felicita.model.response.*;
import com.felicita.repository.TourRepository;
import com.felicita.service.DestinationService;
import com.felicita.service.TourService;
import com.felicita.util.CommonResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourRepository tourRepository;
    private final DestinationService destinationService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, DestinationService destinationService) {
        this.tourRepository = tourRepository;
        this.destinationService = destinationService;
    }

    @Override
    public ResponseEntity<CommonResponse<List<TourResponse>>> getAllTours() {
        LOGGER.info("Start fetching all partners from repository");
        try {
            List<TourResponse> tourResponses = new ArrayList<>();
            List<TourResponseDto> tourResponseDtos = tourRepository.getAllTours();

            for (TourResponseDto responseDto : tourResponseDtos) {
                List<Integer> destinationIds = responseDto.getDestinations();
                List<DestinationResponse> destinationResponse = new ArrayList<>();
                if (destinationIds != null && !destinationIds.isEmpty()) {
                    destinationResponse = destinationService.getDestinationByIds(destinationIds);
                }
                TourResponse tourResponse = new TourResponse();
                tourResponse.setTourId(responseDto.getTourId());
                tourResponse.setTourName(responseDto.getTourName());
                tourResponse.setTourDescription(responseDto.getTourDescription());
                tourResponse.setTourType(responseDto.getTourType());
                tourResponse.setTourCategory(responseDto.getTourCategory());
                tourResponse.setDurationDays(responseDto.getDurationDays());
                tourResponse.setStartDate(responseDto.getStartDate());
                tourResponse.setEndDate(responseDto.getEndDate());
                tourResponse.setStartLocation(responseDto.getStartLocation());
                tourResponse.setEndLocation(responseDto.getEndLocation());
                tourResponse.setMaxPeople(responseDto.getMaxPeople());
                tourResponse.setMinPeople(responseDto.getMinPeople());
                tourResponse.setPricePerPerson(responseDto.getPricePerPerson());
                tourResponse.setTourStatus(responseDto.getTourStatus());
                tourResponse.setTourImages(responseDto.getTourImages());
                tourResponse.setDestinations(destinationResponse);
                tourResponse.setCreatedAt(responseDto.getCreatedAt());
                tourResponse.setCreatedBy(responseDto.getCreatedBy());
                tourResponse.setUpdatedAt(responseDto.getUpdatedAt());
                tourResponse.setUpdatedBy(responseDto.getUpdatedBy());
                tourResponse.setTerminatedAt(responseDto.getTerminatedAt());
                tourResponse.setTerminatedBy(responseDto.getTerminatedBy());
                tourResponses.add(tourResponse);
            }

            if (tourResponses.isEmpty()) {
                LOGGER.warn("No partners found in database");
                throw new DataNotFoundErrorExceptionHandler("No partners found");
            }

            LOGGER.info("Fetched {} partners successfully", tourResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching partners: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all partners from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<TourResponse>>> getAllActiveTours() {
        LOGGER.info("Start fetching all active tours from repository");

        try {
            List<TourResponse> tourResponses = new ArrayList<>();
            List<TourResponseDto> tourResponseDtos = tourRepository.getAllTours();

            for (TourResponseDto responseDto : tourResponseDtos) {
                List<Integer> destinationIds = responseDto.getDestinations();
                List<DestinationResponse> destinationResponse = new ArrayList<>();
                if (destinationIds != null && !destinationIds.isEmpty()) {
                    destinationResponse = destinationService.getDestinationByIds(destinationIds);
                }
                TourResponse tourResponse = new TourResponse();
                tourResponse.setTourId(responseDto.getTourId());
                tourResponse.setTourName(responseDto.getTourName());
                tourResponse.setTourDescription(responseDto.getTourDescription());
                tourResponse.setTourType(responseDto.getTourType());
                tourResponse.setTourCategory(responseDto.getTourCategory());
                tourResponse.setDurationDays(responseDto.getDurationDays());
                tourResponse.setStartDate(responseDto.getStartDate());
                tourResponse.setEndDate(responseDto.getEndDate());
                tourResponse.setStartLocation(responseDto.getStartLocation());
                tourResponse.setEndLocation(responseDto.getEndLocation());
                tourResponse.setMaxPeople(responseDto.getMaxPeople());
                tourResponse.setMinPeople(responseDto.getMinPeople());
                tourResponse.setPricePerPerson(responseDto.getPricePerPerson());
                tourResponse.setTourStatus(responseDto.getTourStatus());
                tourResponse.setTourImages(responseDto.getTourImages());
                tourResponse.setDestinations(destinationResponse);
                tourResponse.setCreatedAt(responseDto.getCreatedAt());
                tourResponse.setCreatedBy(responseDto.getCreatedBy());
                tourResponse.setUpdatedAt(responseDto.getUpdatedAt());
                tourResponse.setUpdatedBy(responseDto.getUpdatedBy());
                tourResponse.setTerminatedAt(responseDto.getTerminatedAt());
                tourResponse.setTerminatedBy(responseDto.getTerminatedBy());
                tourResponses.add(tourResponse);
            }


            if (tourResponses.isEmpty()) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            List<TourResponse> tourResponseList = tourResponses.stream()
                    .filter(item -> CommonStatus.ACTIVE.toString().equalsIgnoreCase(item.getTourStatus()))
                    .toList();

            if (tourResponseList.isEmpty()) {
                LOGGER.warn("No active tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No active tours found");
            }

            LOGGER.info("Fetched {} active tours successfully", tourResponseList.size());

            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            tourResponseList,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching active tours: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch partners from database");
        } finally {
            LOGGER.info("End fetching all active tours from repository");
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<PopularTourResponse>>> getPopularTours() {
        LOGGER.info("Start fetching popular tours from repository");
        try {
            List<PopularTourResponse> popularTourResponses = tourRepository.getPopularTours();

            // logic


            if (popularTourResponses.isEmpty()) {
                LOGGER.warn("No tours found in database");
                throw new DataNotFoundErrorExceptionHandler("No tours found");
            }

            LOGGER.info("Fetched {} popular tour successfully", popularTourResponses.size());
            return ResponseEntity.ok(
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            popularTourResponses,
                            Instant.now()
                    )
            );

        }catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching popular tour: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        }catch (Exception e) {
            LOGGER.error("Error occurred while fetching popular tour: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch popular tour from database");
        } finally {
            LOGGER.info("End fetching popular tour from repository");
        }
    }

}
