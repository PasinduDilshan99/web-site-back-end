package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingBasicDetailsDto {
    private Long bookingId;
    private String bookingReference;

    // Invoice fields
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double subtotal;
    private Double taxAmount;
    private Double discountAmount;
    private Double insuranceAmount;
    private Double packagePrice;
    private Double totalAmount;
    private Double amountPaid;
    private Double balanceDue;

    // Billing info
    private String billingFullName;
    private String billingAddress;
    private String billingEmail;
    private String billingPhone;

    // Package info
    private String packageName;
    private Long packageScheduleId;
    private LocalDate assumeStartDate;
    private LocalDate assumeEndDate;

    // Tour info
    private String tourName;
    private String tourDescription;

    // Booking info
    private Double finalAmount;
    private LocalDate bookingDate;
    private String bookingStatus;
}
