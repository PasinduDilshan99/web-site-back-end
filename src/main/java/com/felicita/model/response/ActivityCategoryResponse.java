package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCategoryResponse {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private String color;
    private String hoverColor;
    private String statusName;
    private String createdAt;
    private Integer createdBy;
    private String updatedAt;
    private Integer updatedBy;
}
