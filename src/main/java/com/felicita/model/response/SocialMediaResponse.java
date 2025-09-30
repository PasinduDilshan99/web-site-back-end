package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaResponse {
    private int id;
    private String name;
    private String description;
    private String link;
    private String iconUrl;
    private String color;
    private String hoverColor;
    private String status;
}
