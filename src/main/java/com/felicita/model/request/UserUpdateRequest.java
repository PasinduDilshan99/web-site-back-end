package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String country;

    private String mobileNumber1;
    private String mobileNumber2;
    private String email;

    private String nic;
    private String passportNumber;
    private String drivingLicenseNumber;

    private Long addressId;
    private String addressNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String province;
    private String postalCode;

    private String imageUrl;
}
