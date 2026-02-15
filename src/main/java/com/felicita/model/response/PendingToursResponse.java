package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PendingToursResponse {
    private Long bookingId;
    private String bookingReference;
    private LocalDate bookingDate;
    private String bookingStatus;

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

    // User Details
    private String username;
    private String userFullName;
    private String email;
    private String mobileNumber1;


}
