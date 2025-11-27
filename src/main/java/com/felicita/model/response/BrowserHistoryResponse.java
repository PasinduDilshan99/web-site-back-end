package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BrowserHistoryResponse {
    private Long id;
    private String type;
    private Long dataId;
    private Long userId;
    private LocalDateTime createdAt;
    private String statusName;
}
