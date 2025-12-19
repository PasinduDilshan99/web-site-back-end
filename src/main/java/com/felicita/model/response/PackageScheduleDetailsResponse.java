package com.felicita.model.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageScheduleDetailsResponse {
    private PackageBasicDetails packageDetails;
    private List<PackageScheduleDetails> schedules;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PackageBasicDetails {

        private Long packageId;
        private String packageName;
        private String packageDescription;
        private Integer duration;
        private Double latitude;
        private Double longitude;
        private String startLocation;
        private String endLocation;
        private String status;
        private List<PackageImageDetails> images;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PackageScheduleDetails {

        private Long scheduleId;
        private String scheduleName;
        private LocalDate assumeStartDate;
        private LocalDate assumeEndDate;
        private Integer durationStart;
        private Integer durationEnd;
        private String specialNote;
        private String description;
        private Long statusId;
        private String statusName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PackageImageDetails {

        private Long imageId;
        private String imageName;
        private String imageDescription;
        private String imageUrl;
        private String imageStatus;
    }
}
