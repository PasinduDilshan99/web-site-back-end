package com.felicita.service.impl;

import com.felicita.exception.DataNotFoundErrorExceptionHandler;
import com.felicita.exception.InsertFailedErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.exception.ValidationFailedErrorExceptionHandler;
import com.felicita.helper.BookingHelperService;
import com.felicita.model.dto.*;
import com.felicita.model.enums.BookingStatus;
import com.felicita.model.request.BookingRequest;
import com.felicita.model.response.*;
import com.felicita.repository.BookingRepository;
import com.felicita.service.BookingService;
import com.felicita.service.CommonService;
import com.felicita.service.PackageService;
import com.felicita.service.VehicleService;
import com.felicita.util.CommonResponseMessages;
import com.felicita.validation.BookingValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final CommonService commonService;
    private final BookingValidationService bookingValidationService;
    private final BookingHelperService bookingHelperService;
    private final PackageService packageService;
    private final VehicleService vehicleService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CommonService commonService,
                              BookingValidationService bookingValidationService,
                              BookingHelperService bookingHelperService,
                              PackageService packageService,
                              VehicleService vehicleService) {
        this.bookingRepository = bookingRepository;
        this.commonService = commonService;
        this.bookingValidationService = bookingValidationService;
        this.bookingHelperService = bookingHelperService;
        this.packageService = packageService;
        this.vehicleService = vehicleService;
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
            return (
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
            return (
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

    @Override
    public CommonResponse<List<RequestedToursResponse>> getRequstedToursDetailsById() {
        LOGGER.info("Start fetching all requested  booking tours details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<RequestedToursResponse> requestedToursResponses = bookingRepository.getRequstedToursDetailsById(userId);

            if (requestedToursResponses.isEmpty()) {
                LOGGER.warn("No requested booking tours details found in database");
                throw new DataNotFoundErrorExceptionHandler("No requested booking tours details found");
            }

            LOGGER.info("Fetched {} requested booking tours details successfully", requestedToursResponses.size());
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            requestedToursResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching requested booking tours details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching requested booking tours details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch requested booking tours details from database");
        } finally {
            LOGGER.info("End fetching all requested booking tours details from repository");
        }
    }

    @Override
    public CommonResponse<List<CancelledToursResponse>> getCancelledToursDetailsById() {
        LOGGER.info("Start fetching all cancelled booking tours details from repository");
        try {
            Long userId = commonService.getUserIdBySecurityContext();
            List<CancelledToursResponse> cancelledToursResponses = bookingRepository.getCancelledToursDetailsById(userId);

            if (cancelledToursResponses.isEmpty()) {
                LOGGER.warn("No cancelled booking tours details found in database");
                throw new DataNotFoundErrorExceptionHandler("No cancelled booking tours details found");
            }

            LOGGER.info("Fetched {} cancelled booking tours details successfully", cancelledToursResponses.size());
            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            cancelledToursResponses,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching cancelled booking tours details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching cancelled booking tours details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch cancelled booking tours details from database");
        } finally {
            LOGGER.info("End fetching all cancelled booking tours details from repository");
        }
    }

    @Override
    public CommonResponse<BookInsertResponse> bookingTour(BookingRequest bookingRequest) {
        try {
            PackageBasicDetailsDto packageBasicDetailsDto = packageService.getPackageBasicDetailsByScheduleId(bookingRequest.getPackageScheduleId());
            LocalDate tourStartDate = packageBasicDetailsDto.getAssumeStartDate().toLocalDate();
            List<PackageActivityPriceDto> packageActivityPriceDto = packageService.getPackageActivityPriceByScheduleId(bookingRequest.getPackageScheduleId());
            List<PackageDestinationExtraPriceDto> packageDestinationExtraPriceDto = packageService.getPackageDestinationExtraPriceByScheduleId(bookingRequest.getPackageScheduleId());
            List<PackageDayAccommodationPriceDto> packageDayAccommodationPriceDto = packageService.getPackageDayAccommodationPriceByScheduleId(bookingRequest.getPackageScheduleId());
            BookInsertResponse bookInsertResponse = new BookInsertResponse();

            Double tourTotalAccommodtionAmountPerPerson = bookingHelperService.calculateTotalAccomodationAmountPerPerson(packageDayAccommodationPriceDto);
            Double tourActivitiesAmountPerPerson = bookingHelperService.calculateTotalActivityAmountPerPerson(packageActivityPriceDto);
            Double tourDestinationExtraAmountPerPerson = bookingHelperService.calculateTotalDestinationExtraAmountPerPerson(packageDestinationExtraPriceDto);

            bookingValidationService.validateBookingRequest(bookingRequest);
            Long userId = commonService.getUserIdBySecurityContext();
            //
            InsertBookingRequestDto insertBookingRequestDto = new InsertBookingRequestDto();
            insertBookingRequestDto.setBookingReference(bookingHelperService.generateUniqueBookingReferance());
            insertBookingRequestDto.setUserId(userId);
            insertBookingRequestDto.setPackageScheduleId(bookingRequest.getPackageScheduleId());
            insertBookingRequestDto.setTotalPersons(bookingRequest.getParticipants().size());

//            Double totalAmount = bookingHelperService.calculateTotalAmount(bookingRequest, packageBasicDetailsDto);
            Double totalAmount = (tourTotalAccommodtionAmountPerPerson + tourActivitiesAmountPerPerson + tourDestinationExtraAmountPerPerson) * bookingRequest.getParticipants().size();
//            Double discountAmount = bookingHelperService.calculateDiscountAmount(bookingRequest, packageBasicDetailsDto);
            Double discountAmount = (totalAmount * packageBasicDetailsDto.getDiscountPercentage()) / 100;
//            Double taxAmount = bookingHelperService.calculateTaxAmount(bookingRequest, packageBasicDetailsDto);
            Double taxAmount = (totalAmount * 18) / 100;
            Double insuranceAmount = 0.0;

            if (bookingRequest.getInsuranceRequired() == true) {
                insuranceAmount = bookingHelperService.calculateInsuranceAmount(bookingRequest, packageBasicDetailsDto);
            }
            insertBookingRequestDto.setTotalAmount(totalAmount);
            insertBookingRequestDto.setDiscountAmount(discountAmount);
            insertBookingRequestDto.setTaxAmount(taxAmount);
            insertBookingRequestDto.setInsuranceAmount(insuranceAmount);

            Double finalAmount = totalAmount - discountAmount + taxAmount + insuranceAmount;

            insertBookingRequestDto.setFinalAmount(finalAmount);

            insertBookingRequestDto.setBookingDate(java.time.LocalDate.now());
            insertBookingRequestDto.setTravelStartDate(packageBasicDetailsDto.getAssumeStartDate());
            insertBookingRequestDto.setTravelEndDate(packageBasicDetailsDto.getAssumeEndDate());
            insertBookingRequestDto.setBookingStatus(BookingStatus.PENDING.name());
            insertBookingRequestDto.setSpecialRequirements(bookingRequest.getSpecialRequirements());
            insertBookingRequestDto.setDietaryRestrictions(bookingRequest.getDietaryRestrictions());
            insertBookingRequestDto.setInsuranceRequired(bookingRequest.getInsuranceRequired());

            //
            Long bookingId = bookingRepository.bookingTourBasicDetails(insertBookingRequestDto);
            bookInsertResponse.setBookingId(bookingId);


            // insert transport vehicel
            for (PackageDayAccommodationPriceDto p : packageDayAccommodationPriceDto) {
                VehicleBasicDetailsDto vehicleBasicDetailsDto = vehicleService.getVehicleBasicDetailsById(p.getVehicleId());
                LocalDate date = tourStartDate.plusDays(p.getDayNumber() - 1);
                bookingRepository.bookingTransportation(bookingId, vehicleBasicDetailsDto, date, userId);

                // insert booking itinerary
                bookingRepository.insertBookingItinerary(bookingId, p, date, userId);
            }

            // insert activities
            for (PackageActivityPriceDto a : packageActivityPriceDto) {
                int totalParticipants = bookingRequest.getParticipants().size();
                bookingRepository.insertBookingPriceBreakdown(bookingId, a, totalParticipants, userId);

                // insert booking activities
                bookingRepository.insertBookingActivities(bookingId, a, totalParticipants, userId);
            }

            // insert participants
            for (BookingRequest.Participant participant : bookingRequest.getParticipants()) {
                bookingRepository.insertBookingParticipant(bookingId, participant, userId);
            }

            // insert booking notes
            for (BookingRequest.BookingNote note : bookingRequest.getBookingNotes()) {
                bookingRepository.insertBookingNote(bookingId, note, userId);
            }

            // booking accommodation not done

            // booking invoice
            LocalDate invoiceDate = java.time.LocalDate.now();
            LocalDate invoiceDueDate = invoiceDate.plusDays(7);
            String invoiceNumber = bookingHelperService.generateUniqueBookingInvoiceReference(bookingId);
            bookingRepository.insertBookingInvoice(bookingId, invoiceNumber, invoiceDate, invoiceDueDate, totalAmount, taxAmount, discountAmount, finalAmount, bookingRequest.getInvoices(), userId);


            return
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_INSERT_CODE,
                            CommonResponseMessages.SUCCESSFULLY_INSERT_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_INSERT_MESSAGE,
                            bookInsertResponse,
                            Instant.now()
                    );
        } catch (ValidationFailedErrorExceptionHandler vfe) {
            throw new ValidationFailedErrorExceptionHandler("validation failed in the insert booking request", vfe.getValidationFailedResponses());
        } catch (InsertFailedErrorExceptionHandler ife) {
            throw new InsertFailedErrorExceptionHandler(ife.getMessage());

        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new InternalServerErrorExceptionHandler("Something went wrong");
        }
    }

    @Override
    public CommonResponse<PrintReceiptForBookingResponse> createReceiptForBooking(Long bookingId) {
        try {
            BookingBasicDetailsDto bookingBasicDetailsDto = bookingRepository.getBookingBasicDetailsByBookingId(bookingId);
            List<BookingActivityDto> bookingActivityDtos = bookingRepository.getBookingActivityByBookingId(bookingId);
            List<BookingParticipantDto> bookingParticipantDtos = bookingRepository.getBookingParticipantByBookingId(bookingId);
            List<PackageDestinationExtraPriceDto> packageDestinationExtraPriceDto = packageService.getPackageDestinationExtraPriceByScheduleId(bookingBasicDetailsDto.getPackageScheduleId());
            List<PackageDayAccommodationPriceDto> packageDayAccommodationPriceDto = packageService.getPackageDayAccommodationPriceByScheduleId(bookingBasicDetailsDto.getPackageScheduleId());
            PrintReceiptForBookingResponse response = bookingHelperService.createReceiptForBooking(
                    bookingBasicDetailsDto,
                    bookingActivityDtos,
                    bookingParticipantDtos,
                    packageDestinationExtraPriceDto,
                    packageDayAccommodationPriceDto);
            LOGGER.info("bookingBasicDetailsDto {}", bookingBasicDetailsDto);
            LOGGER.info("bookingActivityDtos {}", bookingActivityDtos);
            LOGGER.info("bookingParticipantDtos {}", bookingParticipantDtos);

            return (
                    new CommonResponse<>(
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            CommonResponseMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            response,
                            Instant.now()
                    )
            );

        } catch (DataNotFoundErrorExceptionHandler e) {
            LOGGER.error("Error occurred while fetching cancelled booking tours details: {}", e.getMessage(), e);
            throw new DataNotFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching cancelled booking tours details: {}", e.getMessage(), e);
            throw new InternalServerErrorExceptionHandler("Failed to fetch cancelled booking tours details from database");
        } finally {
            LOGGER.info("End fetching all cancelled booking tours details from repository");
        }
    }


}
