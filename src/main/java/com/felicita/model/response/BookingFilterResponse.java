package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingFilterResponse {
    private Long tourId;
    private String tourName;
    private String tourDescription;
    private List<PackageDetails> packageDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageDetails{
        private Long packageId;
        private String packageName;
        private String packageDescription;
        private List<PackageSchedulesDetails> packageSchedulesDetails;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageSchedulesDetails{
        private Long packageScheduleId;
        private String packageScheduleName;
        private String packageScheduleDescription;
        private Date startDate;
        private Date endDate;
    }

}
