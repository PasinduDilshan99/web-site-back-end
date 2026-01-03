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
public class PackageExtrasResponse {
    private Long packageId;
    private List<PackageExtrasResponse.PackageInclusion> inclusions;
    private List<PackageExtrasResponse.PackageExclusion> exclusions;
    private List<PackageExtrasResponse.PackageCondition> conditions;
    private List<PackageExtrasResponse.PackageTravelTip> travelTips;

    // ================= INCLUSIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageInclusion {
        private Long id;
        private String description;
        private Integer displayOrder;
        private String status;
    }

    // ================= EXCLUSIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageExclusion {
        private Long id;
        private String description;
        private Integer displayOrder;
        private String status;
    }

    // ================= CONDITIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageCondition {
        private Long id;
        private String description;
        private Integer displayOrder;
        private String status;
    }

    // ================= TRAVEL TIPS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PackageTravelTip {
        private Long id;
        private String title;
        private String description;
        private Integer displayOrder;
        private String status;
    }
}
