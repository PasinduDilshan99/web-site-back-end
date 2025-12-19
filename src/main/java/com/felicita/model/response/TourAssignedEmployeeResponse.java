package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourAssignedEmployeeResponse {

    private String firstName;
    private String lastName;
    private String imageUrl;
    private String email;
    private String mobileNumber;
    private String designationName;
    private String assignMessage;
    private List<RelatedOtherTours> relatedOtherTours;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RelatedOtherTours{
        private Long tourId;
        private String tourName;
    }
}
