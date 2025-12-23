package com.felicita.repository;

import com.felicita.model.dto.*;
import com.felicita.model.request.BookingRequest;
import com.felicita.model.response.CancelledToursResponse;
import com.felicita.model.response.CompleteToursResponse;
import com.felicita.model.response.RequestedToursResponse;
import com.felicita.model.response.UpcomingToursResponse;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    List<CompleteToursResponse> getCompletedBookingToursDetailsById(Long userId);

    List<UpcomingToursResponse> getUpcomingBookingToursDetailsById(Long userId);

    List<RequestedToursResponse> getRequstedToursDetailsById(Long userId);

    List<CancelledToursResponse> getCancelledToursDetailsById(Long userId);

    Long bookingTourBasicDetails(InsertBookingRequestDto insertBookingRequestDto);

    void bookingTransportation(Long bookingId, VehicleBasicDetailsDto vehicleBasicDetailsDto, LocalDate date, Long userId);

    void insertBookingPriceBreakdown(Long bookingId, PackageActivityPriceDto a, int totalParticipants,Long userId);

    void insertBookingParticipant(Long bookingId, BookingRequest.Participant participant, Long userId);

    void insertBookingNote(Long bookingId, BookingRequest.BookingNote note, Long userId);

    void insertBookingItinerary(Long bookingId, PackageDayAccommodationPriceDto p, LocalDate date, Long userId);

    void insertBookingActivities(Long bookingId, PackageActivityPriceDto a, int totalParticipants, Long userId);

    void insertBookingInvoice(Long bookingId, String invoiceNumber, LocalDate invoiceDate, LocalDate invoiceDueDate, Double totalAmount, Double taxAmount, Double discountAmount, Double finalAmount,BookingRequest.BookingInvoice invoices, Long userId);

    BookingBasicDetailsDto getBookingBasicDetailsByBookingId(Long bookingId);

    List<BookingActivityDto> getBookingActivityByBookingId(Long bookingId);

    List<BookingParticipantDto> getBookingParticipantByBookingId(Long bookingId);
}
