package com.felicita.model.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private Long packageScheduleId;
    private String specialRequirements;
    private String dietaryRestrictions;
    private Boolean insuranceRequired;
    private Transport transport;
//    private List<BookingPrice> bookingPrices;
    private List<Participant> participants;
    private List<BookingNote> bookingNotes;
//    private List<BookingActivity> activities;
    private BookingInvoice invoices;

    /* -------------------- TRANSPORT -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Transport {
        private LocalDate departureDate;
        private LocalTime departureTime;
        private LocalDate arrivalDate;
        private LocalTime arrivalTime;
        private String departureLocation;
        private String arrivalLocation;
    }

    /* -------------------- BOOKING PRICE -------------------- */
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class BookingPrice {
//        private Long bookingId;
//        private String itemType;
//        private String itemName;
//        private String itemDescription;
//        private Integer quantity;
//        private Double unitPrice;
//        private Double totalPrice;
//    }

    /* -------------------- PARTICIPANT -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Participant {
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String gender;
        private String passportNumber;
        private String country;
        private String email;
        private String mobileNumber;
        private String emergencyContactName;
        private String emergencyContactPhone;
        private String emergencyContactRelationship;
        private String medicalConditions;
        private String allergies;
        private Boolean specialAssistanceRequired;
        private String assistantDetails;
        private String roomSharingWith;
    }

    /* -------------------- BOOKING NOTES -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingNote {
        private String noteType ;
        private String noteText;
    }


    /* -------------------- ACTIVITIES -------------------- */
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class BookingActivity {
//        private Long activityScheduleId;
//        private Integer numberOfParticipants;
//    }


    /* -------------------- INVOICE -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingInvoice {
        private String billingFullName;
        private String billingAddress;
        private String billingEmail;
        private String billingPhone;
    }
}
