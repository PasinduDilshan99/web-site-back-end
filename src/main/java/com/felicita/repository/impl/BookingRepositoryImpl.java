package com.felicita.repository.impl;

import com.felicita.exception.DataAccessErrorExceptionHandler;
import com.felicita.exception.InternalServerErrorExceptionHandler;
import com.felicita.model.dto.ActivityCategoryImageResponseDto;
import com.felicita.model.dto.ActivityCategoryResponseDto;
import com.felicita.model.response.CancelledToursResponse;
import com.felicita.model.response.CompleteToursResponse;
import com.felicita.model.response.RequestedToursResponse;
import com.felicita.model.response.UpcomingToursResponse;
import com.felicita.queries.ActivitiesQueries;
import com.felicita.queries.BookingQueries;
import com.felicita.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CompleteToursResponse> getCompletedBookingToursDetailsById(Long userId) {
        String GET_COMPLETE_BOOKING_DETAILS_BY_ID = BookingQueries.GET_COMPLETE_BOOKING_DETAILS_BY_ID;
        String GET_COMPLETE_BOOKING_PARTICIPANTS = BookingQueries.GET_COMPLETE_BOOKING_PARTICIPANTS;
        String GET_COMPLETE_BOOKING_ACTIVITIES = BookingQueries.GET_COMPLETE_BOOKING_ACTIVITIES;
        String GET_COMPLETE_BOOKING_PAYMENTS = BookingQueries.GET_COMPLETE_BOOKING_PAYMENTS;
        String GET_COMPLETE_BOOKING_DOCUMENTS = BookingQueries.GET_COMPLETE_BOOKING_DOCUMENTS;

        try {
            LOGGER.info("Executing query to fetch completed booking tours for user ID: {}", userId);

            // Create a map to store bookings by ID
            Map<Long, CompleteToursResponse> bookingMap = new LinkedHashMap<>();

            // 1. Fetch main booking details
            jdbcTemplate.query(GET_COMPLETE_BOOKING_DETAILS_BY_ID, new Object[]{userId}, (rs) -> {
                Long bookingId = rs.getLong("booking_id");

                CompleteToursResponse booking = CompleteToursResponse.builder()
                        .bookingId(bookingId)
                        .bookingReference(rs.getString("booking_reference"))
                        .bookingDate(rs.getDate("booking_date").toLocalDate())
                        .travelStartDate(rs.getDate("travel_start_date").toLocalDate())
                        .travelEndDate(rs.getDate("travel_end_date").toLocalDate())
                        .totalPersons(rs.getInt("total_persons"))
                        .totalAmount(rs.getBigDecimal("total_amount"))
                        .discountAmount(rs.getBigDecimal("discount_amount"))
                        .taxAmount(rs.getBigDecimal("tax_amount"))
                        .insuranceAmount(rs.getBigDecimal("insurance_amount"))
                        .finalAmount(rs.getBigDecimal("final_amount"))
                        .bookingStatus(rs.getString("booking_status"))
                        .cancellationReason(rs.getString("cancellation_reason"))
                        .cancellationDate(rs.getTimestamp("cancellation_date") != null ?
                                rs.getTimestamp("cancellation_date").toLocalDateTime() : null)
                        .refundAmount(rs.getBigDecimal("refund_amount"))
                        .tourId(rs.getLong("tour_id"))
                        .tourName(rs.getString("tour_name"))
                        .tourDescription(rs.getString("tour_description"))
                        .tourDuration(rs.getInt("tour_duration"))
                        .startLocation(rs.getString("start_location"))
                        .endLocation(rs.getString("end_location"))
                        .tourType(rs.getString("tour_type"))
                        .tourCategory(rs.getString("tour_category"))
                        .packageName(rs.getString("package_name"))
                        .packageDescription(rs.getString("package_description"))
                        .packageTotalPrice(rs.getBigDecimal("package_total_price"))
                        .discountPercentage(rs.getBigDecimal("discount_percentage"))
                        .packagePricePerPerson(rs.getBigDecimal("package_price_per_person"))
                        .packageScheduleName(rs.getString("package_schedule_name"))
                        .assumeStartDate(rs.getDate("assume_start_date") != null ?
                                rs.getDate("assume_start_date").toLocalDate() : null)
                        .assumeEndDate(rs.getDate("assume_end_date") != null ?
                                rs.getDate("assume_end_date").toLocalDate() : null)
                        .username(rs.getString("username"))
                        .userFullName(rs.getString("user_full_name"))
                        .email(rs.getString("email"))
                        .mobileNumber1(rs.getString("mobile_number1"))
                        .actualDurationDays(rs.getInt("actual_duration_days"))
                        .completionTime(rs.getString("completion_time"))
                        .participants(new ArrayList<>())
                        .activities(new ArrayList<>())
                        .payments(new ArrayList<>())
                        .documents(new ArrayList<>())
                        .build();

                bookingMap.put(bookingId, booking);
            });

            // 2. Fetch participants for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_COMPLETE_BOOKING_PARTICIPANTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CompleteToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CompleteToursResponse.Participant participant = CompleteToursResponse.Participant.builder()
                                .bookingId(bookingId)
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .dateOfBirth(rs.getDate("date_of_birth") != null ?
                                        rs.getDate("date_of_birth").toLocalDate() : null)
                                .age(rs.getInt("age"))
                                .gender(rs.getString("gender"))
                                .passportNumber(rs.getString("passport_number"))
                                .nationality(rs.getString("nationality"))
                                .email(rs.getString("email"))
                                .mobileNumber(rs.getString("mobile_number"))
                                .emergencyContactName(rs.getString("emergency_contact_name"))
                                .emergencyContactPhone(rs.getString("emergency_contact_phone"))
                                .emergencyContactRelationship(rs.getString("emergency_contact_relationship"))
                                .medicalConditions(rs.getString("medical_conditions"))
                                .allergies(rs.getString("allergies"))
                                .specialAssistanceRequired(rs.getBoolean("special_assistance_required"))
                                .assistanceDetails(rs.getString("assistance_details"))
                                .roomSharingWithFirstName(rs.getString("room_sharing_with_first_name"))
                                .roomSharingWithLastName(rs.getString("room_sharing_with_last_name"))
                                .build();

                        booking.getParticipants().add(participant);
                    }
                });
            }

            // 3. Fetch activities for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_COMPLETE_BOOKING_ACTIVITIES, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CompleteToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CompleteToursResponse.Activity activity = CompleteToursResponse.Activity.builder()
                                .bookingId(bookingId)
                                .activityName(rs.getString("activity_name"))
                                .activityDescription(rs.getString("activity_description"))
                                .activityCategory(rs.getString("activity_category"))
                                .activityDate(rs.getDate("activity_date") != null ?
                                        rs.getDate("activity_date").toLocalDate() : null)
                                .startTime(rs.getString("start_time"))
                                .endTime(rs.getString("end_time"))
                                .numberOfParticipants(rs.getInt("number_of_participants"))
                                .pricePerPerson(rs.getBigDecimal("price_per_person"))
                                .totalPrice(rs.getBigDecimal("total_price"))
                                .destinationName(rs.getString("destination_name"))
                                .durationHours(rs.getBigDecimal("duration_hours"))
                                .priceLocal(rs.getBigDecimal("price_local"))
                                .priceForeigners(rs.getBigDecimal("price_foreigners"))
                                .activityStatus(rs.getString("activity_status"))
                                .build();

                        booking.getActivities().add(activity);
                    }
                });
            }

            // 4. Fetch payments for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_COMPLETE_BOOKING_PAYMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CompleteToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CompleteToursResponse.Payment payment = CompleteToursResponse.Payment.builder()
                                .bookingId(bookingId)
                                .paymentReference(rs.getString("payment_reference"))
                                .amount(rs.getBigDecimal("amount"))
                                .paymentMethod(rs.getString("payment_method"))
                                .paymentStatus(rs.getString("payment_status"))
                                .installmentNumber(rs.getInt("installment_number"))
                                .totalInstallments(rs.getInt("total_installments"))
                                .paymentDate(rs.getTimestamp("payment_date") != null ?
                                        rs.getTimestamp("payment_date").toLocalDateTime() : null)
                                .dueDate(rs.getDate("due_date") != null ?
                                        rs.getDate("due_date").toLocalDate() : null)
                                .transactionId(rs.getString("transaction_id"))
                                .invoiceNumber(rs.getString("invoice_number"))
                                .invoiceDate(rs.getDate("invoice_date") != null ?
                                        rs.getDate("invoice_date").toLocalDate() : null)
                                .invoiceTotal(rs.getBigDecimal("invoice_total"))
                                .amountPaid(rs.getBigDecimal("amount_paid"))
                                .balanceDue(rs.getBigDecimal("balance_due"))
                                .build();

                        booking.getPayments().add(payment);
                    }
                });
            }

            // 5. Fetch documents for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_COMPLETE_BOOKING_DOCUMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CompleteToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CompleteToursResponse.Document document = CompleteToursResponse.Document.builder()
                                .bookingId(bookingId)
                                .documentType(rs.getString("document_type"))
                                .documentName(rs.getString("document_name"))
                                .documentUrl(rs.getString("document_url"))
                                .fileSize(rs.getLong("file_size"))
                                .build();

                        booking.getDocuments().add(document);
                    }
                });
            }

            LOGGER.info("Successfully fetched {} completed booking tours for user ID: {}",
                    bookingMap.size(), userId);

            return new ArrayList<>(bookingMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching completed booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch completed booking tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching completed booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching completed booking tours");
        }
    }

    @Override
    public List<UpcomingToursResponse> getUpcomingBookingToursDetailsById(Long userId) {
        String GET_UPCOMING_BOOKING_DETAILS_BY_ID = BookingQueries.GET_UPCOMING_BOOKING_DETAILS_BY_ID;
        String GET_UPCOMING_BOOKING_PARTICIPANTS = BookingQueries.GET_UPCOMING_BOOKING_PARTICIPANTS;
        String GET_UPCOMING_BOOKING_ACTIVITIES = BookingQueries.GET_UPCOMING_BOOKING_ACTIVITIES;
        String GET_UPCOMING_BOOKING_PAYMENTS = BookingQueries.GET_UPCOMING_BOOKING_PAYMENTS;
        String GET_UPCOMING_BOOKING_DOCUMENTS = BookingQueries.GET_UPCOMING_BOOKING_DOCUMENTS;

        try {
            LOGGER.info("Executing query to fetch upcoming booking tours for user ID: {}", userId);

            // Create a map to store bookings by ID
            Map<Long, UpcomingToursResponse> bookingMap = new LinkedHashMap<>();

            // 1. Fetch main booking details
            jdbcTemplate.query(GET_UPCOMING_BOOKING_DETAILS_BY_ID, new Object[]{userId}, (rs) -> {
                Long bookingId = rs.getLong("booking_id");

                UpcomingToursResponse booking = UpcomingToursResponse.builder()
                        .bookingId(bookingId)
                        .bookingReference(rs.getString("booking_reference"))
                        .bookingDate(rs.getDate("booking_date").toLocalDate())
                        .travelStartDate(rs.getDate("travel_start_date").toLocalDate())
                        .travelEndDate(rs.getDate("travel_end_date").toLocalDate())
                        .totalPersons(rs.getInt("total_persons"))
                        .totalAmount(rs.getBigDecimal("total_amount"))
                        .discountAmount(rs.getBigDecimal("discount_amount"))
                        .taxAmount(rs.getBigDecimal("tax_amount"))
                        .insuranceAmount(rs.getBigDecimal("insurance_amount"))
                        .finalAmount(rs.getBigDecimal("final_amount"))
                        .bookingStatus(rs.getString("booking_status"))
                        .cancellationReason(rs.getString("cancellation_reason"))
                        .tourId(rs.getLong("tour_id"))
                        .tourName(rs.getString("tour_name"))
                        .tourDescription(rs.getString("tour_description"))
                        .tourDuration(rs.getInt("tour_duration"))
                        .startLocation(rs.getString("start_location"))
                        .endLocation(rs.getString("end_location"))
                        .tourType(rs.getString("tour_type"))
                        .tourCategory(rs.getString("tour_category"))
                        .packageName(rs.getString("package_name"))
                        .packageDescription(rs.getString("package_description"))
                        .packageTotalPrice(rs.getBigDecimal("package_total_price"))
                        .discountPercentage(rs.getBigDecimal("discount_percentage"))
                        .packagePricePerPerson(rs.getBigDecimal("package_price_per_person"))
                        .packageScheduleName(rs.getString("package_schedule_name"))
                        .assumeStartDate(rs.getDate("assume_start_date") != null ?
                                rs.getDate("assume_start_date").toLocalDate() : null)
                        .assumeEndDate(rs.getDate("assume_end_date") != null ?
                                rs.getDate("assume_end_date").toLocalDate() : null)
                        .username(rs.getString("username"))
                        .userFullName(rs.getString("user_full_name"))
                        .email(rs.getString("email"))
                        .mobileNumber1(rs.getString("mobile_number1"))
                        .daysUntilTravel(rs.getLong("days_until_travel"))
                        .travelUrgency(rs.getString("travel_urgency"))
                        .countdown(rs.getString("countdown"))
                        .participants(new ArrayList<>())
                        .activities(new ArrayList<>())
                        .payments(new ArrayList<>())
                        .documents(new ArrayList<>())
                        .build();

                bookingMap.put(bookingId, booking);
            });

            // 2. Fetch participants for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_UPCOMING_BOOKING_PARTICIPANTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    UpcomingToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        UpcomingToursResponse.Participant participant = UpcomingToursResponse.Participant.builder()
                                .bookingId(bookingId)
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .dateOfBirth(rs.getDate("date_of_birth") != null ?
                                        rs.getDate("date_of_birth").toLocalDate() : null)
                                .age(rs.getInt("age"))
                                .gender(rs.getString("gender"))
                                .passportNumber(rs.getString("passport_number"))
                                .passportProvided(rs.getString("passport_number") != null)
                                .nationality(rs.getString("nationality"))
                                .email(rs.getString("email"))
                                .mobileNumber(rs.getString("mobile_number"))
                                .emergencyContactName(rs.getString("emergency_contact_name"))
                                .emergencyContactPhone(rs.getString("emergency_contact_phone"))
                                .emergencyContactRelationship(rs.getString("emergency_contact_relationship"))
                                .medicalConditions(rs.getString("medical_conditions"))
                                .allergies(rs.getString("allergies"))
                                .specialAssistanceRequired(rs.getBoolean("special_assistance_required"))
                                .assistanceDetails(rs.getString("assistance_details"))
                                .roomSharingWithFirstName(rs.getString("room_sharing_with_first_name"))
                                .roomSharingWithLastName(rs.getString("room_sharing_with_last_name"))
                                .participantReadiness(
                                        (rs.getString("passport_number") != null && rs.getString("medical_conditions") != null)
                                                ? "READY" : "PENDING_DOCS"
                                )
                                .build();

                        booking.getParticipants().add(participant);
                    }
                });
            }

            // 3. Fetch activities for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_UPCOMING_BOOKING_ACTIVITIES, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    UpcomingToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        UpcomingToursResponse.Activity activity = UpcomingToursResponse.Activity.builder()
                                .bookingId(bookingId)
                                .activityName(rs.getString("activity_name"))
                                .activityDescription(rs.getString("activity_description"))
                                .activityCategory(rs.getString("activity_category"))
                                .activityDate(rs.getDate("activity_date") != null ?
                                        rs.getDate("activity_date").toLocalDate() : null)
                                .startTime(rs.getString("start_time"))
                                .endTime(rs.getString("end_time"))
                                .numberOfParticipants(rs.getInt("number_of_participants"))
                                .pricePerPerson(rs.getBigDecimal("price_per_person"))
                                .totalPrice(rs.getBigDecimal("total_price"))
                                .destinationName(rs.getString("destination_name"))
                                .durationHours(rs.getBigDecimal("duration_hours"))
                                .priceLocal(rs.getBigDecimal("price_local"))
                                .priceForeigners(rs.getBigDecimal("price_foreigners"))
                                .daysUntilActivity(rs.getLong("days_until_activity"))
                                .activityTiming(rs.getString("activity_timing"))
                                .build();

                        booking.getActivities().add(activity);
                    }
                });
            }

            // 4. Fetch payments for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_UPCOMING_BOOKING_PAYMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    UpcomingToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        UpcomingToursResponse.Payment payment = UpcomingToursResponse.Payment.builder()
                                .bookingId(bookingId)
                                .paymentReference(rs.getString("payment_reference"))
                                .amount(rs.getBigDecimal("amount"))
                                .paymentMethod(rs.getString("payment_method"))
                                .paymentStatus(rs.getString("payment_status"))
                                .installmentNumber(rs.getInt("installment_number"))
                                .totalInstallments(rs.getInt("total_installments"))
                                .paymentDate(rs.getTimestamp("payment_date") != null ?
                                        rs.getTimestamp("payment_date").toLocalDateTime() : null)
                                .dueDate(rs.getDate("due_date") != null ?
                                        rs.getDate("due_date").toLocalDate() : null)
                                .transactionId(rs.getString("transaction_id"))
                                .invoiceNumber(rs.getString("invoice_number"))
                                .invoiceDate(rs.getDate("invoice_date") != null ?
                                        rs.getDate("invoice_date").toLocalDate() : null)
                                .invoiceTotal(rs.getBigDecimal("invoice_total"))
                                .amountPaid(rs.getBigDecimal("amount_paid"))
                                .balanceDue(rs.getBigDecimal("balance_due"))
                                .paymentUrgency(rs.getString("payment_urgency"))
                                .build();

                        booking.getPayments().add(payment);
                    }
                });
            }

            // 5. Fetch documents for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_UPCOMING_BOOKING_DOCUMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    UpcomingToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        UpcomingToursResponse.Document document = UpcomingToursResponse.Document.builder()
                                .bookingId(bookingId)
                                .documentType(rs.getString("document_type"))
                                .documentName(rs.getString("document_name"))
                                .documentUrl(rs.getString("document_url"))
                                .fileSize(rs.getLong("file_size"))
                                .documentCategory(rs.getString("document_category"))
                                .build();

                        booking.getDocuments().add(document);
                    }
                });
            }

            LOGGER.info("Successfully fetched {} upcoming booking tours for user ID: {}",
                    bookingMap.size(), userId);

            return new ArrayList<>(bookingMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching upcoming booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch upcoming booking tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching upcoming booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching upcoming booking tours");
        }
    }

    @Override
    public List<RequestedToursResponse> getRequstedToursDetailsById(Long userId) {
        String GET_REQUESTED_BOOKING_DETAILS_BY_ID = BookingQueries.GET_REQUESTED_BOOKING_DETAILS_BY_ID;
        String GET_REQUESTED_BOOKING_PARTICIPANTS = BookingQueries.GET_REQUESTED_BOOKING_PARTICIPANTS;
        String GET_REQUESTED_BOOKING_ACTIVITIES = BookingQueries.GET_REQUESTED_BOOKING_ACTIVITIES;
        String GET_REQUESTED_BOOKING_PAYMENTS = BookingQueries.GET_REQUESTED_BOOKING_PAYMENTS;
        String GET_REQUESTED_BOOKING_DOCUMENTS = BookingQueries.GET_REQUESTED_BOOKING_DOCUMENTS;

        try {
            LOGGER.info("Executing query to fetch requested booking tours for user ID: {}", userId);

            // Create a map to store bookings by ID
            Map<Long, RequestedToursResponse> bookingMap = new LinkedHashMap<>();

            // 1. Fetch main booking details
            jdbcTemplate.query(GET_REQUESTED_BOOKING_DETAILS_BY_ID, new Object[]{userId}, (rs) -> {
                Long bookingId = rs.getLong("booking_id");

                RequestedToursResponse booking = RequestedToursResponse.builder()
                        .bookingId(bookingId)
                        .bookingReference(rs.getString("booking_reference"))
                        .bookingDate(rs.getDate("booking_date").toLocalDate())
                        .travelStartDate(rs.getDate("travel_start_date").toLocalDate())
                        .travelEndDate(rs.getDate("travel_end_date").toLocalDate())
                        .totalPersons(rs.getInt("total_persons"))
                        .totalAmount(rs.getBigDecimal("total_amount"))
                        .discountAmount(rs.getBigDecimal("discount_amount"))
                        .taxAmount(rs.getBigDecimal("tax_amount"))
                        .insuranceAmount(rs.getBigDecimal("insurance_amount"))
                        .finalAmount(rs.getBigDecimal("final_amount"))
                        .bookingStatus(rs.getString("booking_status"))
                        .cancellationReason(rs.getString("cancellation_reason"))
                        .cancellationDate(rs.getTimestamp("cancellation_date") != null ?
                                rs.getTimestamp("cancellation_date").toLocalDateTime() : null)
                        .refundAmount(rs.getBigDecimal("refund_amount"))
                        .tourId(rs.getLong("tour_id"))
                        .tourName(rs.getString("tour_name"))
                        .tourDescription(rs.getString("tour_description"))
                        .tourDuration(rs.getInt("tour_duration"))
                        .startLocation(rs.getString("start_location"))
                        .endLocation(rs.getString("end_location"))
                        .tourType(rs.getString("tour_type"))
                        .tourCategory(rs.getString("tour_category"))
                        .packageName(rs.getString("package_name"))
                        .packageDescription(rs.getString("package_description"))
                        .packageTotalPrice(rs.getBigDecimal("package_total_price"))
                        .discountPercentage(rs.getBigDecimal("discount_percentage"))
                        .packagePricePerPerson(rs.getBigDecimal("package_price_per_person"))
                        .packageScheduleName(rs.getString("package_schedule_name"))
                        .assumeStartDate(rs.getDate("assume_start_date") != null ?
                                rs.getDate("assume_start_date").toLocalDate() : null)
                        .assumeEndDate(rs.getDate("assume_end_date") != null ?
                                rs.getDate("assume_end_date").toLocalDate() : null)
                        .username(rs.getString("username"))
                        .userFullName(rs.getString("user_full_name"))
                        .email(rs.getString("email"))
                        .mobileNumber1(rs.getString("mobile_number1"))
                        .requestStatus(rs.getString("request_status"))
                        .approvalStatus(rs.getString("approval_status"))
                        .daysUntilTravel(rs.getLong("days_until_travel"))
                        .requestUrgency(rs.getString("request_urgency"))
                        .requestAge(rs.getString("request_age"))
                        .participants(new ArrayList<>())
                        .activities(new ArrayList<>())
                        .payments(new ArrayList<>())
                        .documents(new ArrayList<>())
                        .build();

                bookingMap.put(bookingId, booking);
            });

            // 2. Fetch participants for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_REQUESTED_BOOKING_PARTICIPANTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    RequestedToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        RequestedToursResponse.Participant participant = RequestedToursResponse.Participant.builder()
                                .bookingId(bookingId)
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .dateOfBirth(rs.getDate("date_of_birth") != null ?
                                        rs.getDate("date_of_birth").toLocalDate() : null)
                                .age(rs.getInt("age"))
                                .gender(rs.getString("gender"))
                                .passportNumber(rs.getString("passport_number"))
                                .nationality(rs.getString("nationality"))
                                .email(rs.getString("email"))
                                .mobileNumber(rs.getString("mobile_number"))
                                .emergencyContactName(rs.getString("emergency_contact_name"))
                                .emergencyContactPhone(rs.getString("emergency_contact_phone"))
                                .emergencyContactRelationship(rs.getString("emergency_contact_relationship"))
                                .medicalConditions(rs.getString("medical_conditions"))
                                .allergies(rs.getString("allergies"))
                                .specialAssistanceRequired(rs.getBoolean("special_assistance_required"))
                                .assistanceDetails(rs.getString("assistance_details"))
                                .roomSharingWithFirstName(rs.getString("room_sharing_with_first_name"))
                                .roomSharingWithLastName(rs.getString("room_sharing_with_last_name"))
                                .documentStatus(
                                        rs.getString("passport_number") != null ? "COMPLETE" : "PENDING"
                                )
                                .build();

                        booking.getParticipants().add(participant);
                    }
                });
            }

            // 3. Fetch activities for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_REQUESTED_BOOKING_ACTIVITIES, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    RequestedToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        RequestedToursResponse.Activity activity = RequestedToursResponse.Activity.builder()
                                .bookingId(bookingId)
                                .activityName(rs.getString("activity_name"))
                                .activityDescription(rs.getString("activity_description"))
                                .activityCategory(rs.getString("activity_category"))
                                .activityDate(rs.getDate("activity_date") != null ?
                                        rs.getDate("activity_date").toLocalDate() : null)
                                .startTime(rs.getString("start_time"))
                                .endTime(rs.getString("end_time"))
                                .numberOfParticipants(rs.getInt("number_of_participants"))
                                .pricePerPerson(rs.getBigDecimal("price_per_person"))
                                .totalPrice(rs.getBigDecimal("total_price"))
                                .destinationName(rs.getString("destination_name"))
                                .durationHours(rs.getBigDecimal("duration_hours"))
                                .priceLocal(rs.getBigDecimal("price_local"))
                                .priceForeigners(rs.getBigDecimal("price_foreigners"))
                                .activityStatus(rs.getString("activity_status"))
                                .availabilityStatus(rs.getString("availability_status"))
                                .build();

                        booking.getActivities().add(activity);
                    }
                });
            }

            // 4. Fetch payments for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_REQUESTED_BOOKING_PAYMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    RequestedToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        RequestedToursResponse.Payment payment = RequestedToursResponse.Payment.builder()
                                .bookingId(bookingId)
                                .paymentReference(rs.getString("payment_reference"))
                                .amount(rs.getBigDecimal("amount"))
                                .paymentMethod(rs.getString("payment_method"))
                                .paymentStatus(rs.getString("payment_status"))
                                .installmentNumber(rs.getInt("installment_number"))
                                .totalInstallments(rs.getInt("total_installments"))
                                .paymentDate(rs.getTimestamp("payment_date") != null ?
                                        rs.getTimestamp("payment_date").toLocalDateTime() : null)
                                .dueDate(rs.getDate("due_date") != null ?
                                        rs.getDate("due_date").toLocalDate() : null)
                                .transactionId(rs.getString("transaction_id"))
                                .invoiceNumber(rs.getString("invoice_number"))
                                .invoiceDate(rs.getDate("invoice_date") != null ?
                                        rs.getDate("invoice_date").toLocalDate() : null)
                                .invoiceTotal(rs.getBigDecimal("invoice_total"))
                                .amountPaid(rs.getBigDecimal("amount_paid"))
                                .balanceDue(rs.getBigDecimal("balance_due"))
                                .paymentPriority(rs.getString("payment_priority"))
                                .depositRequired(rs.getBoolean("deposit_required"))
                                .depositAmount(rs.getBigDecimal("deposit_amount"))
                                .build();

                        booking.getPayments().add(payment);
                    }
                });
            }

            // 5. Fetch documents for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_REQUESTED_BOOKING_DOCUMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    RequestedToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        RequestedToursResponse.Document document = RequestedToursResponse.Document.builder()
                                .bookingId(bookingId)
                                .documentType(rs.getString("document_type"))
                                .documentName(rs.getString("document_name"))
                                .documentUrl(rs.getString("document_url"))
                                .fileSize(rs.getLong("file_size"))
                                .documentStatus(rs.getString("document_status"))
                                .requiredForApproval(rs.getBoolean("required_for_approval"))
                                .build();

                        booking.getDocuments().add(document);
                    }
                });
            }

            LOGGER.info("Successfully fetched {} requested booking tours for user ID: {}",
                    bookingMap.size(), userId);

            return new ArrayList<>(bookingMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching requested booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch requested booking tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching requested booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching requested booking tours");
        }
    }

    @Override
    public List<CancelledToursResponse> getCancelledToursDetailsById(Long userId) {
        String GET_CANCELLED_BOOKING_DETAILS_BY_ID = BookingQueries.GET_CANCELLED_BOOKING_DETAILS_BY_ID;
        String GET_CANCELLED_BOOKING_PARTICIPANTS = BookingQueries.GET_CANCELLED_BOOKING_PARTICIPANTS;
        String GET_CANCELLED_BOOKING_ACTIVITIES = BookingQueries.GET_CANCELLED_BOOKING_ACTIVITIES;
        String GET_CANCELLED_BOOKING_PAYMENTS = BookingQueries.GET_CANCELLED_BOOKING_PAYMENTS;
        String GET_CANCELLED_BOOKING_DOCUMENTS = BookingQueries.GET_CANCELLED_BOOKING_DOCUMENTS;

        try {
            LOGGER.info("Executing query to fetch cancelled booking tours for user ID: {}", userId);

            // Create a map to store bookings by ID
            Map<Long, CancelledToursResponse> bookingMap = new LinkedHashMap<>();

            // 1. Fetch main booking details
            jdbcTemplate.query(GET_CANCELLED_BOOKING_DETAILS_BY_ID, new Object[]{userId}, (rs) -> {
                Long bookingId = rs.getLong("booking_id");

                CancelledToursResponse booking = CancelledToursResponse.builder()
                        .bookingId(bookingId)
                        .bookingReference(rs.getString("booking_reference"))
                        .bookingDate(rs.getDate("booking_date").toLocalDate())
                        .travelStartDate(rs.getDate("travel_start_date").toLocalDate())
                        .travelEndDate(rs.getDate("travel_end_date").toLocalDate())
                        .totalPersons(rs.getInt("total_persons"))
                        .totalAmount(rs.getBigDecimal("total_amount"))
                        .discountAmount(rs.getBigDecimal("discount_amount"))
                        .taxAmount(rs.getBigDecimal("tax_amount"))
                        .insuranceAmount(rs.getBigDecimal("insurance_amount"))
                        .finalAmount(rs.getBigDecimal("final_amount"))
                        .bookingStatus(rs.getString("booking_status"))
                        .cancellationReason(rs.getString("cancellation_reason"))
                        .cancellationDate(rs.getTimestamp("cancellation_date") != null ?
                                rs.getTimestamp("cancellation_date").toLocalDateTime() : null)
                        .refundAmount(rs.getBigDecimal("refund_amount"))
                        .refundStatus(rs.getString("refund_status"))
                        .refundedAmount(rs.getBigDecimal("refunded_amount"))
                        .refundProcessedDate(rs.getTimestamp("refund_processed_date") != null ?
                                rs.getTimestamp("refund_processed_date").toLocalDateTime() : null)
                        .tourId(rs.getLong("tour_id"))
                        .tourName(rs.getString("tour_name"))
                        .tourDescription(rs.getString("tour_description"))
                        .tourDuration(rs.getInt("tour_duration"))
                        .startLocation(rs.getString("start_location"))
                        .endLocation(rs.getString("end_location"))
                        .tourType(rs.getString("tour_type"))
                        .tourCategory(rs.getString("tour_category"))
                        .packageName(rs.getString("package_name"))
                        .packageDescription(rs.getString("package_description"))
                        .packageTotalPrice(rs.getBigDecimal("package_total_price"))
                        .discountPercentage(rs.getBigDecimal("discount_percentage"))
                        .packagePricePerPerson(rs.getBigDecimal("package_price_per_person"))
                        .packageScheduleName(rs.getString("package_schedule_name"))
                        .assumeStartDate(rs.getDate("assume_start_date") != null ?
                                rs.getDate("assume_start_date").toLocalDate() : null)
                        .assumeEndDate(rs.getDate("assume_end_date") != null ?
                                rs.getDate("assume_end_date").toLocalDate() : null)
                        .username(rs.getString("username"))
                        .userFullName(rs.getString("user_full_name"))
                        .email(rs.getString("email"))
                        .mobileNumber1(rs.getString("mobile_number1"))
                        .cancellationStage(rs.getString("cancellation_stage"))
                        .daysBeforeTravel(rs.getLong("days_before_travel"))
                        .cancellationFee(rs.getBigDecimal("cancellation_fee"))
                        .cancellationPenaltyPercentage(rs.getBigDecimal("cancellation_penalty_percentage"))
                        .cancellationNotes(rs.getString("cancellation_notes"))
                        .participants(new ArrayList<>())
                        .activities(new ArrayList<>())
                        .payments(new ArrayList<>())
                        .documents(new ArrayList<>())
                        .build();

                bookingMap.put(bookingId, booking);
            });

            // 2. Fetch participants for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_CANCELLED_BOOKING_PARTICIPANTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CancelledToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CancelledToursResponse.Participant participant = CancelledToursResponse.Participant.builder()
                                .bookingId(bookingId)
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .dateOfBirth(rs.getDate("date_of_birth") != null ?
                                        rs.getDate("date_of_birth").toLocalDate() : null)
                                .age(rs.getInt("age"))
                                .gender(rs.getString("gender"))
                                .passportNumber(rs.getString("passport_number"))
                                .nationality(rs.getString("nationality"))
                                .email(rs.getString("email"))
                                .mobileNumber(rs.getString("mobile_number"))
                                .emergencyContactName(rs.getString("emergency_contact_name"))
                                .emergencyContactPhone(rs.getString("emergency_contact_phone"))
                                .emergencyContactRelationship(rs.getString("emergency_contact_relationship"))
                                .medicalConditions(rs.getString("medical_conditions"))
                                .allergies(rs.getString("allergies"))
                                .specialAssistanceRequired(rs.getBoolean("special_assistance_required"))
                                .assistanceDetails(rs.getString("assistance_details"))
                                .roomSharingWithFirstName(rs.getString("room_sharing_with_first_name"))
                                .roomSharingWithLastName(rs.getString("room_sharing_with_last_name"))
                                .refundIssued(rs.getBoolean("refund_issued"))
                                .participantRefundAmount(rs.getBigDecimal("participant_refund_amount"))
                                .build();

                        booking.getParticipants().add(participant);
                    }
                });
            }

            // 3. Fetch activities for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_CANCELLED_BOOKING_ACTIVITIES, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CancelledToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CancelledToursResponse.Activity activity = CancelledToursResponse.Activity.builder()
                                .bookingId(bookingId)
                                .activityName(rs.getString("activity_name"))
                                .activityDescription(rs.getString("activity_description"))
                                .activityCategory(rs.getString("activity_category"))
                                .activityDate(rs.getDate("activity_date") != null ?
                                        rs.getDate("activity_date").toLocalDate() : null)
                                .startTime(rs.getString("start_time"))
                                .endTime(rs.getString("end_time"))
                                .numberOfParticipants(rs.getInt("number_of_participants"))
                                .pricePerPerson(rs.getBigDecimal("price_per_person"))
                                .totalPrice(rs.getBigDecimal("total_price"))
                                .destinationName(rs.getString("destination_name"))
                                .durationHours(rs.getBigDecimal("duration_hours"))
                                .priceLocal(rs.getBigDecimal("price_local"))
                                .priceForeigners(rs.getBigDecimal("price_foreigners"))
                                .activityStatus(rs.getString("activity_status"))
                                .activityRefundable(rs.getBoolean("activity_refundable"))
                                .activityRefundAmount(rs.getBigDecimal("activity_refund_amount"))
                                .build();

                        booking.getActivities().add(activity);
                    }
                });
            }

            // 4. Fetch payments for these bookings
            // 4. Fetch payments for these bookings (UPDATED MAPPING)
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_CANCELLED_BOOKING_PAYMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CancelledToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CancelledToursResponse.Payment payment = CancelledToursResponse.Payment.builder()
                                .bookingId(bookingId)
                                .paymentReference(rs.getString("payment_reference"))
                                .amount(rs.getBigDecimal("amount"))
                                .paymentMethod(rs.getString("payment_method"))
                                .paymentStatus(rs.getString("payment_status"))
                                .installmentNumber(rs.getInt("installment_number"))
                                .totalInstallments(rs.getInt("total_installments"))
                                .paymentDate(rs.getTimestamp("payment_date") != null ?
                                        rs.getTimestamp("payment_date").toLocalDateTime() : null)
                                .dueDate(rs.getDate("due_date") != null ?
                                        rs.getDate("due_date").toLocalDate() : null)
                                .transactionId(rs.getString("transaction_id"))
                                .invoiceNumber(rs.getString("invoice_number"))
                                .invoiceDate(rs.getDate("invoice_date") != null ?
                                        rs.getDate("invoice_date").toLocalDate() : null)
                                .invoiceTotal(rs.getBigDecimal("invoice_total"))
                                .amountPaid(rs.getBigDecimal("amount_paid"))
                                .balanceDue(rs.getBigDecimal("balance_due"))
                                .refundReference(rs.getString("refund_reference"))
                                .refundAmount(rs.getBigDecimal("refund_amount"))
                                .refundDate(rs.getTimestamp("refund_date") != null ?
                                        rs.getTimestamp("refund_date").toLocalDateTime() : null)
                                .refundStatus(rs.getString("refund_status"))  // FIXED: Now matches query
                                .paymentPriority(rs.getString("payment_priority"))
                                .depositRequired(rs.getBoolean("deposit_required"))
                                .depositAmount(rs.getBigDecimal("deposit_amount"))
                                .refundStatusInfo(rs.getString("refund_status_info"))
                                .paymentMethodId(rs.getLong("payment_method_id"))
                                .paymentStatusId(rs.getLong("payment_status_id"))
                                .refundStatusId(rs.getLong("refund_status_id"))
                                .build();

                        booking.getPayments().add(payment);
                    }
                });
            }

            // 5. Fetch documents for these bookings
            if (!bookingMap.isEmpty()) {
                jdbcTemplate.query(GET_CANCELLED_BOOKING_DOCUMENTS, new Object[]{userId}, (rs) -> {
                    Long bookingId = rs.getLong("booking_id");
                    CancelledToursResponse booking = bookingMap.get(bookingId);

                    if (booking != null) {
                        CancelledToursResponse.Document document = CancelledToursResponse.Document.builder()
                                .bookingId(bookingId)
                                .documentType(rs.getString("document_type"))
                                .documentName(rs.getString("document_name"))
                                .documentUrl(rs.getString("document_url"))
                                .fileSize(rs.getLong("file_size"))
                                .documentStatus(rs.getString("document_status"))
                                .cancellationRelated(rs.getBoolean("cancellation_related"))
                                .build();

                        booking.getDocuments().add(document);
                    }
                });
            }

            LOGGER.info("Successfully fetched {} cancelled booking tours for user ID: {}",
                    bookingMap.size(), userId);

            return new ArrayList<>(bookingMap.values());

        } catch (DataAccessException ex) {
            LOGGER.error("Database error while fetching cancelled booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new DataAccessErrorExceptionHandler("Failed to fetch cancelled booking tours from database");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while fetching cancelled booking tours for user {}: {}",
                    userId, ex.getMessage(), ex);
            throw new InternalServerErrorExceptionHandler("Unexpected error occurred while fetching cancelled booking tours");
        }
    }
}
