package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentThemeColorsDto {
    private Long themeId;
    private String themeName;
    private String themeDescription;
    private String primaryColor;
    private String secondaryColor;
    private String backgroundColor;
    private boolean gradientEnabled;
    private String gradientType;
    private String gradientDirection;
    private String gradientStart;
    private String gradientEnd;
    private String textPrimary;
    private String textSecondary;
    private String bannerImage;
    private String customCss;
}
