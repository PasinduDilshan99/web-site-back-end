package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageHistoryImageResponse {

    private Integer imageId;
    private String imageName;
    private String imageDescription;
    private String imageUrl;
    private String color;
    private String imageStatusName;
    private LocalDateTime createdAt;

    private PackageScheduleInfo packageSchedule;
    private PackageInfo packageInfo;
    private UserInfo createdByUser;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageInfo {
        private Integer packageId;
        private String packageName;
        private Integer tourId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageScheduleInfo {
        private Integer packageScheduleId;
        private String packageScheduleName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private String fullName;
        private String imageUrl;
    }
}
