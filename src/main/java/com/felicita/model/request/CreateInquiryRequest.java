package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInquiryRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String country;
    private String preferredContactMethod;
    private String preferredDestination;
    private Integer adults;
    private Integer kids;
    private String arrivalDate;
    private String departureDate;
    private String message;
}
