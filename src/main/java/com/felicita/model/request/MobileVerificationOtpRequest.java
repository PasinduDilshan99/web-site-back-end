package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileVerificationOtpRequest {
    private String mobileNumber;
    private String whichMobile;
}
