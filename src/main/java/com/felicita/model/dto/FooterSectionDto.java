package com.felicita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FooterSectionDto {
    private int id;
    private String title;
    private String description;
    private String color;
    private String status;
    private List<FooterSubItemDto> subItems;
}
