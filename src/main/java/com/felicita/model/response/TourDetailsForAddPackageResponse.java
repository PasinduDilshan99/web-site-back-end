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
public class TourDetailsForAddPackageResponse {
    private Long tourId;
    private String name;
    private String description;
    private String tourType;
    private String tourCategory;
    private String startLocation;
    private String endLocation;
    private String status;
    private String season;
    private AssignedUser assignedUser;
    private String assignMessage;

    private List<TourDay> days;

    private List<String> inclusions;
    private List<String> exclusions;
    private List<String> conditions;
    private List<TravelTip> travelTips;

    @Data
    public static class TravelTip {
        private String tipTitle;
        private String tipDescription;
    }

    @Data
    public static class AssignedUser {
        private Long userId;
        private String firstName;
        private String lastName;
        private String username;
    }

    @Data
    public static class TourDay {
        private Integer day;
        private List<Destination> destinations;
    }

    @Data
    public static class Destination {
        private Long destinationId;
        private String name;
        private String description;
        private List<Activity> activities;
    }

    @Data
    public static class Activity {
        private Long activityId;
        private String name;
        private String description;
    }
}
