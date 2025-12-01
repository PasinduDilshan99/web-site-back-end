package com.felicita.repository;

import com.felicita.model.response.CancelledToursResponse;
import com.felicita.model.response.CompleteToursResponse;
import com.felicita.model.response.RequestedToursResponse;
import com.felicita.model.response.UpcomingToursResponse;

import java.util.List;

public interface BookingRepository {
    List<CompleteToursResponse> getCompletedBookingToursDetailsById(Long userId);

    List<UpcomingToursResponse> getUpcomingBookingToursDetailsById(Long userId);

    List<RequestedToursResponse> getRequstedToursDetailsById(Long userId);

    List<CancelledToursResponse> getCancelledToursDetailsById(Long userId);
}
