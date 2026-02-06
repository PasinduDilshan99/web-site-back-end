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
    private Integer addressId;
    private String nic;
    private Integer genderId;
    private String passportNumber;
    private String drivingLicenseNumber;
    private String email;
    private String email2;
    private String mobileNumber1;
    private String mobileNumber2;
    private Integer regionId;
    private Integer religionId;
    private Date dateOfBirth;
    private String imageUrl;
}
