package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlowResponse {
    private int id;
    private String title;
    private String description;
    private int orderNumber;
    private String iconUrl;
    private String iconColor;
    private String bgColor;
    private String connectText;
    private String linkUrl;
    private String status;          // from work_flow_status.name
    private String statusStatus;    // from common_status.name
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
