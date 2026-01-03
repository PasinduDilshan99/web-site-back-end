package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingActivityDto {
    private Long bookingId;
    private Long activityId;
    private String name;
    private String description;
    private Integer numberOfParticipants;
    private Double pricePerPerson;
    private Double totalPrice;
}
