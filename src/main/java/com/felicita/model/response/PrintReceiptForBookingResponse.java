package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintReceiptForBookingResponse {
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

    List<ParticipentDetails> participentDetails;
    List<ActivityDetails> activityDetailsList;
    List<DestiantionDetails> destiantionDetails;
    List<AccommodationDetails> accommodationDetailsList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ParticipentDetails{
        private Long bookingId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String gender;          // g.name
        private String passportNumber;
        private String nationality;     // c.name
        private String email;
        private String mobileNumber;
        private String medicalConditions;
        private String allergies;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityDetails {
        private String activityName;
        private String activityDescription;
        private Integer numberOfParticipants;
        private Double pricePerPerson;
        private Double totalPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DestiantionDetails{
        private Double extraPrice;
        private String extraPriceNote;
        private String destinationName;
        private String destinationDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AccommodationDetails{
        private Integer dayNumber;
        private String hotelName;
        private Double transportPrice;
        private Double price;
        private Double discount;
        private Double serviceCharge;
        private Double tax;
        private Double extraCharge;
        private String extraChargeNote;
    }


}
