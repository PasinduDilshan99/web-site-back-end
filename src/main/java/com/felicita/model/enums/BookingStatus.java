package com.felicita.model.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {

    PENDING("Booking is pending confirmation"),
    CONFIRMED("Booking has been confirmed"),
    PAID("Booking has been fully paid"),
    IN_PROGRESS("Tour is currently ongoing"),
    BOOKING_COMPLETED("Booking has been completed"),
    CANCELLED("Booking has been cancelled"),
    TOUR_COMPLETED("Tour experience finished");

    private final String description;

    BookingStatus(String description) {
        this.description = description;
    }
}
