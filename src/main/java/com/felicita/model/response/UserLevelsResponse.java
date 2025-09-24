package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelsResponse {
    private Integer userLevelId;
    private String userLevelName;
    private String userLevelDescription;
    private String statusName;
    private Date createdAt;
    private Integer createdBy;
    private String createdByUser;
    private Date updatedAt;
    private Integer updatedBy;
    private String updatedByUser;
    private Date terminatedAt;
    private Integer terminatedBy;
    private String terminatedByUser;
}
