package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBookingSummaryResponse {

    private Long bookingId;
    private String bookingReference;
    private String bookingInvoiceNumber;
    private String packageName;
    private String packageScheduleName;
    private String tourName;
}
