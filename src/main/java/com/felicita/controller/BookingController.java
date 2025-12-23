package com.felicita.controller;

import com.felicita.model.request.BookingRequest;
import com.felicita.model.response.*;
import com.felicita.service.BookingService;
import com.felicita.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/booking")
public class BookingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/completed")
    public ResponseEntity<CommonResponse<List<CompleteToursResponse>>> getCompletedBookingToursDetailsById() {
        LOGGER.info("{} Start execute get completed booking tours details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<CompleteToursResponse>> response = bookingService.getCompletedBookingToursDetailsById();
        LOGGER.info("{} End execute get completed booking tours details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/upcoming")
    public ResponseEntity<CommonResponse<List<UpcomingToursResponse>>> getUpcomingBookingToursDetailsById() {
        LOGGER.info("{} Start execute get upcoming booking tours details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UpcomingToursResponse>> response = bookingService.getUpcomingBookingToursDetailsById();
        LOGGER.info("{} End execute get upcoming booking tours details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/requested")
    public ResponseEntity<CommonResponse<List<RequestedToursResponse>>> getRequstedToursDetailsById() {
        LOGGER.info("{} Start execute get requested booking tours details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<RequestedToursResponse>> response = bookingService.getRequstedToursDetailsById();
        LOGGER.info("{} End execute get requested booking tours details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/cancelled")
    public ResponseEntity<CommonResponse<List<CancelledToursResponse>>> getCancelledToursDetailsById() {
        LOGGER.info("{} Start execute get cancelled booking tours details {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<CancelledToursResponse>> response = bookingService.getCancelledToursDetailsById();
        LOGGER.info("{} End execute get cancelled booking tours details {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/book-tour-filter")
    public ResponseEntity<CommonResponse<List<BookingFilterResponse>>> getBookingFilter() {
        LOGGER.info("{} Start execute get filters in the booking {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<BookingFilterResponse>> response = bookingService.getBookingFilter();
        LOGGER.info("{} End execute get filters in the booking {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/booked-tours")
    public ResponseEntity<CommonResponse<List<UserBookingSummaryResponse>>> getBookedTours() {
        LOGGER.info("{} Start execute get booked tours {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<List<UserBookingSummaryResponse>> response = bookingService.getBookedTours();
        LOGGER.info("{} End execute get booked tours {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/book-tour")
    public ResponseEntity<CommonResponse<BookInsertResponse>> bookingTour(@RequestBody BookingRequest bookingRequest) {
        LOGGER.info("{} Start execute booking a tour {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<BookInsertResponse> response = bookingService.bookingTour(bookingRequest);
        LOGGER.info("{} End execute booking a tour {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/book-receipt/{bookingId}")
    public ResponseEntity<CommonResponse<PrintReceiptForBookingResponse>> createReceiptForBooking(@PathVariable Long bookingId) {
        LOGGER.info("{} Start execute create receipt for booking a tour {}", Constant.DOTS, Constant.DOTS);
        CommonResponse<PrintReceiptForBookingResponse> response = bookingService.createReceiptForBooking(bookingId);
        LOGGER.info("{} End execute create receipt for booking a tour {}", Constant.DOTS, Constant.DOTS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
