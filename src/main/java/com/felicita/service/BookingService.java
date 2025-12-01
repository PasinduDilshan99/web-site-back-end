package com.felicita.service;

import com.felicita.model.response.*;

import java.util.List;

public interface BookingService {
    CommonResponse<List<CompleteToursResponse>> getCompletedBookingToursDetailsById();

    CommonResponse<List<UpcomingToursResponse>> getUpcomingBookingToursDetailsById();

    CommonResponse<List<RequestedToursResponse>> getRequstedToursDetailsById();

    CommonResponse<List<CancelledToursResponse>> getCancelledToursDetailsById();
}
