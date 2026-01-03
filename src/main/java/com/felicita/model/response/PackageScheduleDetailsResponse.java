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
        private Double totalPrice;
        private Double pricePerPerson;
        private Double discount;
        private String color;
        private String hoverColor;
        private Integer minPersonCount;
        private Integer maxPersonCount;
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
        private String status;
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
    }
}
