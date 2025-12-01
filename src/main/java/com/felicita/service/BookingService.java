package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.CompleteToursResponse;
import com.felicita.model.response.UpcomingToursResponse;

import java.util.List;

public interface BookingService {
    CommonResponse<List<CompleteToursResponse>> getCompletedBookingToursDetailsById();

    CommonResponse<List<UpcomingToursResponse>> getUpcomingBookingToursDetailsById();
}
