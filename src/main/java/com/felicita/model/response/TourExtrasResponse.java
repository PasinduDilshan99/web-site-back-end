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
public class TourExtrasResponse {

    private List<TourInclusion> inclusions;
    private List<TourExclusion> exclusions;
    private List<TourCondition> conditions;
    private List<TourTravelTip> travelTips;

    // ================= INCLUSIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TourInclusion {
        private Long id;               // tour_inclusion_id
        private String description;    // inclusion_text
        private Integer displayOrder;  // display_order
        private String status;         // ACTIVE / INACTIVE / TERMINATED
    }

    // ================= EXCLUSIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TourExclusion {
        private Long id;               // tour_exclusion_id
        private String description;    // exclusion_text
        private Integer displayOrder;  // display_order
        private String status;         // ACTIVE / INACTIVE / TERMINATED
    }

    // ================= CONDITIONS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TourCondition {
        private Long id;               // tour_condition_id
        private String description;    // condition_text
        private Integer displayOrder;  // display_order
        private String status;         // ACTIVE / INACTIVE / TERMINATED
    }

    // ================= TRAVEL TIPS =================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TourTravelTip {
        private Long id;               // tour_travel_tip_id
        private String title;          // tip_title
        private String description;    // tip_description
        private Integer displayOrder;  // display_order
        private String status;         // ACTIVE / INACTIVE / TERMINATED
    }
}
