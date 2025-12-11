package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OurStoryAndValuesResponse {

    private List<Timeline> timelines;
    private List<CoreValue> coreValues;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Timeline {
        private Integer storyId;
        private String yearLabel;
        private String title;
        private String description;
        private String iconName;
        private String color;
        private Integer orderNo;

        private Integer statusId;
        private Timestamp createdAt;
        private Integer createdBy;
        private Timestamp updatedAt;
        private Integer updatedBy;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoreValue {
        private Integer valueId;
        private String title;
        private String description;
        private String iconName;
        private String color;
        private Integer orderNo;

        private Integer statusId;
        private Timestamp createdAt;
        private Integer createdBy;
        private Timestamp updatedAt;
        private Integer updatedBy;
    }
}
