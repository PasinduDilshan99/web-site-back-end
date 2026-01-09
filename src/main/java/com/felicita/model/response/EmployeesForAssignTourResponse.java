package com.felicita.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesForAssignTourResponse {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String email;
    private String mobileNumber1;
    private String designationName;
    private List<Tour> tours;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tour {
        @JsonProperty("tour_id")
        private Long tourId;
        private String name;
    }
}
