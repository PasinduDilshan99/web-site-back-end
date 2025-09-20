package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqResponse {
    private Long faqId;
    private String faqQuestion;
    private String faqAnswer1;
    private String faqAnswer2;
    private String faqAnswer3;
    private String faqAnswer4;
    private String faqAnswer5;
    private String faqDisplayAnswer;
    private String faqStatus;
    private String faqStatusStatus;
    private LocalDateTime faqCreatedAt;
    private Long faqCreatedBy;
    private LocalDateTime faqUpdatedAt;
    private Long faqUpdatedBy;
    private LocalDateTime faqTerminatedAt;
    private Long faqTerminatedBy;
    private Integer faqViewCount;
    private LocalDateTime faqLastView;
}
