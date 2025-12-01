package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelledToursResponse {
    private Long bookingId;
    private String bookingReference;
    private LocalDate bookingDate;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private Integer totalPersons;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal insuranceAmount;
    private BigDecimal finalAmount;
    private String bookingStatus;
    private String cancellationReason;
    private LocalDateTime cancellationDate;
    private BigDecimal refundAmount;
    private String refundStatus;
    private BigDecimal refundedAmount;
    private LocalDateTime refundProcessedDate;

    // Tour Details
    private Long tourId;
    private String tourName;
    private String tourDescription;
    private Integer tourDuration;
    private String startLocation;
    private String endLocation;
    private String tourType;
    private String tourCategory;

    // Package Details
    private String packageName;
    private String packageDescription;
    private BigDecimal packageTotalPrice;
    private BigDecimal discountPercentage;
    private BigDecimal packagePricePerPerson;

    // Package Schedule
    private String packageScheduleName;
    private LocalDate assumeStartDate;
    private LocalDate assumeEndDate;

    // User Details
    private String username;
    private String userFullName;
    private String email;
    private String mobileNumber1;

    // Cancellation Info
    private String cancellationStage;
    private Long daysBeforeTravel;
    private BigDecimal cancellationFee;
    private BigDecimal cancellationPenaltyPercentage;
    private String cancellationNotes;

    // Nested Lists
    private List<Participant> participants;
    private List<Activity> activities;
    private List<Payment> payments;
    private List<Document> documents;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Participant {
        private Long bookingId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private Integer age;
        private String gender;
        private String passportNumber;
        private String nationality;
        private String email;
        private String mobileNumber;
        private String emergencyContactName;
        private String emergencyContactPhone;
        private String emergencyContactRelationship;
        private String medicalConditions;
        private String allergies;
        private Boolean specialAssistanceRequired;
        private String assistanceDetails;
        private String roomSharingWithFirstName;
        private String roomSharingWithLastName;
        private Boolean refundIssued;
        private BigDecimal participantRefundAmount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Activity {
        private Long bookingId;
        private String activityName;
        private String activityDescription;
        private String activityCategory;
        private LocalDate activityDate;
        private String startTime;
        private String endTime;
        private Integer numberOfParticipants;
        private BigDecimal pricePerPerson;
        private BigDecimal totalPrice;
        private String destinationName;
        private BigDecimal durationHours;
        private BigDecimal priceLocal;
        private BigDecimal priceForeigners;
        private String activityStatus;
        private Boolean activityRefundable;
        private BigDecimal activityRefundAmount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payment {
        private Long bookingId;
        private String paymentReference;
        private BigDecimal amount;
        private String paymentMethod;
        private String paymentStatus;
        private Integer installmentNumber;
        private Integer totalInstallments;
        private LocalDateTime paymentDate;
        private LocalDate dueDate;
        private String transactionId;
        private String invoiceNumber;
        private LocalDate invoiceDate;
        private BigDecimal invoiceTotal;
        private BigDecimal amountPaid;
        private BigDecimal balanceDue;

        // Refund fields
        private String refundReference;
        private BigDecimal refundAmount;
        private LocalDateTime refundDate;
        private String refundStatus;  // Changed from refundMethod to match query

        // Payment info
        private String paymentPriority;
        private Boolean depositRequired;
        private BigDecimal depositAmount;
        private String refundStatusInfo;

        // Optional fields (will be null)
        private String refundMethod;
        private String refundTransactionId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {
        private Long bookingId;
        private String documentType;
        private String documentName;
        private String documentUrl;
        private Long fileSize;
        private String documentStatus;
        private Boolean cancellationRelated;
    }
}