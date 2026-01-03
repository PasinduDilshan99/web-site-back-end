package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertBookingRequestDto {
    private String bookingReference;
    private Long userId;
    private Long packageScheduleId;

    private Integer totalPersons;

    private Double totalAmount;
    private Double discountAmount;
    private Double taxAmount;
    private Double insuranceAmount;
    private Double finalAmount;

    private LocalDate bookingDate;
    private Date travelStartDate;
    private Date travelEndDate;

    private String bookingStatus;
    private String specialRequirements;
    private String dietaryRestrictions;
    private Boolean insuranceRequired;

    private List<Transport> transports;
    private List<BookingPrice> bookingPrices;
    private List<Participant> participants;
    private List<BookingNote> bookingNotes;
    private List<BookingItinerary> itineraries;
    private List<BookingActivity> activities;
    private List<BookingAccommodation> accommodations;
    private List<BookingInvoice> invoices;

    /* -------------------- TRANSPORT -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Transport {
        private Long bookingId;
        private String transportType;

        private LocalDate departureDate;
        private LocalTime departureTime;
        private LocalDate arrivalDate;
        private LocalTime arrivalTime;

        private String departureLocation;
        private String arrivalLocation;

        private String carrierName;
        private String referenceNumber;
        private String seatNumbers;
    }

    /* -------------------- BOOKING PRICE -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingPrice {
        private Long bookingId;
        private String itemType;
        private String itemName;
        private String itemDescription;

        private Integer quantity;
        private Double unitPrice;
        private Double totalPrice;
    }

    /* -------------------- PARTICIPANT -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Participant {
        private Long bookingId;

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
        private Long bookingId;
        private String noteType;
        private String noteText;
        private Boolean important;

        private LocalDate followUpDate;
        private Boolean followUpCompleted;
    }

    /* -------------------- ITINERARY -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingItinerary {
        private Long bookingId;
        private Integer dayNumber;

        private LocalDate itineraryDate;
        private String title;
        private String description;

        private LocalTime startTime;
        private LocalTime endTime;

        private String location;
        private String includedMeals;
    }

    /* -------------------- ACTIVITIES -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingActivity {
        private Long bookingId;
        private Long activityId;
        private Long activityScheduleId;

        private LocalDate activityDate;
        private LocalTime startTime;
        private LocalTime endTime;

        private Integer numberOfParticipants;
        private Double pricePerPerson;
        private Double totalPrice;

        private String status;
    }

    /* -------------------- ACCOMMODATION -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingAccommodation {
        private Long bookingId;

        private LocalDate checkInDate;
        private LocalDate checkOutDate;

        private String hotelName;
        private String roomType;
        private String roomNumber;
        private String confirmationNumber;
    }

    /* -------------------- INVOICE -------------------- */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingInvoice {
        private Long bookingId;

        private String invoiceNumber;
        private LocalDate invoiceDate;
        private LocalDate dueDate;

        private Double subTotal;
        private Double taxAmount;
        private Double totalAmount;
        private Double amountPaid;
        private Double balanceDue;

        private String billingFullName;
        private String billingAddress;
        private String billingEmail;
        private String billingPhone;

        private String status;
    }
}