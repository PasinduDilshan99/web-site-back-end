package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDayAccommodationUpdateRequest {
    private Long packageDayAccommodationId;
    private Integer dayNumber;

    private Boolean breakfast;
    private String breakfastDescription;

    private Boolean lunch;
    private String lunchDescription;

    private Boolean dinner;
    private String dinnerDescription;

    private Boolean morningTea;
    private String morningTeaDescription;

    private Boolean eveningTea;
    private String eveningTeaDescription;

    private Boolean snacks;
    private String snackNote;

    private Long hotelId;
    private Long transportId;
    private String otherNotes;
    private String status;
}
