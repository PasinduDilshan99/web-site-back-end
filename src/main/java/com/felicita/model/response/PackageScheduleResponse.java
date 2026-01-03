package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageScheduleResponse {

    private Long packageId;
    private List<PackageScheduleDetails> packageSchedules;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageScheduleDetails {
        private Long packageScheduleId;
        private Long packageId;
        private String name;
        private LocalDate assumeStartDate;
        private LocalDate assumeEndDate;
        private String description;
        private String specialNote;
        private String status;
        private Integer durationStart;
        private Integer durationEnd;
    }
}
