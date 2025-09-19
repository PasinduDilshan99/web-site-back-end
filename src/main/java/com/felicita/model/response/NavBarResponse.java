package com.felicita.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavBarResponse {
    private String name;
    private String description;
    private String status;
    private String navBarStatusStatus;
    private String linkUrl;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private LocalDateTime terminatedAt;
    private Integer terminatedBy;
}
