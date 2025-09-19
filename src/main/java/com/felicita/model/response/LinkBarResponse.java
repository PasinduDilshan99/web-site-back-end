package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkBarResponse {
    private String name;
    private String description;
    private String typeName;
    private String typeStatus;
    private String iconUrl;
    private String linkUrl;
    private String itemStatus;
    private String itemStatusStatus;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
