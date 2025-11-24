package com.felicita.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUser {
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber1;
    private String mobileNumber2;

    private Integer addressId;
    private String nic;
    private Integer genderId;
    private String passportNumber;
    private String drivingLicenseNumber;
    private Integer regionId;
    private Integer religionId;
    private String dateOfBirth;
    private String imageUrl;
    private Integer userStatusId;
    private Integer walletId;
    private Integer userTypeId;

    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @Builder.Default
    private Set<String> privileges = new HashSet<>();
}
