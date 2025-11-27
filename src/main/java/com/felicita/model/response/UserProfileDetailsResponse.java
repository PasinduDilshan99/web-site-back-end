package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDetailsResponse {
    private Integer userId;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nic;
    private String passportNumber;
    private String drivingLicenseNumber;
    private String email;
    private String mobileNumber1;
    private String mobileNumber2;
    private String dateOfBirth;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private Integer benefitsPointsCount;
    private String addressNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String province;
    private String countryName;
    private String postalCode;
    private String gender;
    private String religion;
    private String userType;
    private String userTypeDescription;
    private String userStatus;
    private String userStatusDescription;
    private String walletNumber;
    private Double walletAmount;
    private String walletStatus;
}
