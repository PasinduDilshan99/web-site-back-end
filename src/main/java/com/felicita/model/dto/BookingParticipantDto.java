package com.felicita.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingParticipantDto {
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
